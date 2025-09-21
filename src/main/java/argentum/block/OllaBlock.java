package argentum.block;

import argentum.recipe.FritarRecipe;
import argentum.registry.ModRecipes;
import argentum.registry.ModSounds;
import argentum.registry.ModTags;
// IMPORT NUEVO
import net.minecraft.util.ItemActionResult;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class OllaBlock extends Block {

    public static final IntProperty PROGRESS = IntProperty.of("progress", 0, 3);
    public static final BooleanProperty LIT = BooleanProperty.of("lit");

    private static final VoxelShape SHAPE = Block.createCuboidShape(2, 0, 2, 14, 6, 14);
    private static final int TICK_STEP = 20;
    private static final int PLAYER_COOLDOWN_TICKS = 20;

    private static final class Pending {
        final Identifier resultId;
        final float exp;
        final int steps;
        final UUID playerId;
        Pending(Identifier resultId, float exp, int steps, UUID playerId) {
            this.resultId = resultId; this.exp = exp; this.steps = steps; this.playerId = playerId;
        }
    }
    private static final Map<BlockPos, Pending> PENDING = new HashMap<>();

    public OllaBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(PROGRESS, 0).with(LIT, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PROGRESS, LIT);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    // Random calificado para evitar imports erróneos
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, net.minecraft.util.math.random.Random random) {
        if (state.get(LIT)) {
            double x = pos.getX() + 0.5;
            double y = pos.getY() + 0.6;
            double z = pos.getZ() + 0.5;
            world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0, 0.02, 0.0);
            world.addParticle(ParticleTypes.BUBBLE_POP,
                    x + (random.nextDouble() - 0.5) * 0.4,
                    y,
                    z + (random.nextDouble() - 0.5) * 0.4,
                    0, 0.01, 0);
        }
    }

    // **Este reemplaza tu lógica de click derecho con ítem en mano**
    @Override
    public ItemActionResult onUseWithItem(
            ItemStack stack,
            BlockState state,
            World world,
            BlockPos pos,
            PlayerEntity player,
            Hand hand,
            BlockHitResult hit
    ) {
        if (stack.isEmpty()) return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        // ¿Ocupada?
        if (state.get(PROGRESS) > 0 || PENDING.containsKey(pos)) {
            if (!world.isClient) player.sendMessage(Text.translatable("block.argentum.olla.busy"), true);
            return ItemActionResult.SUCCESS;
        }

        // Cooldown anti-spam por ítem
        if (player.getItemCooldownManager().isCoolingDown(stack.getItem())) {
            return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        // Receta para el ítem exacto
        Optional<RecipeEntry<FritarRecipe>> match = findFritarRecipe(world, stack.getItem());
        if (match.isEmpty()) {
            if (!world.isClient) {
                Identifier id = Registries.ITEM.getId(stack.getItem());
                player.sendMessage(Text.literal("No hay receta de fritar para: " + id), true);
            }
            return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        FritarRecipe recipe = match.get().value();

        // ¿Tenemos aceite (#argentum:aceites)?
        ItemStack aceite = findAceite(player);
        if (aceite.isEmpty()) {
            if (!world.isClient) player.sendMessage(Text.translatable("block.argentum.olla.no_oil"), true);
            player.getItemCooldownManager().set(stack.getItem(), PLAYER_COOLDOWN_TICKS);
            return ItemActionResult.SUCCESS;
        }

        if (!world.isClient) {
            // Consumimos al inicio
            aceite.decrement(1);
            stack.decrement(1);

            int steps = Math.max(1, Math.min(3, Math.max(1, recipe.getCookingTime() / TICK_STEP)));
            PENDING.put(pos.toImmutable(), new Pending(recipe.getResultId(), recipe.getExperience(), steps, player.getUuid()));

            // Feedback y arranque
            world.setBlockState(pos, state.with(PROGRESS, 1).with(LIT, true), Block.NOTIFY_ALL);
            world.playSound(null, pos, ModSounds.OLLA_FRY_START, SoundCategory.BLOCKS, 1.0f, 1.0f);

            ((ServerWorld) world).scheduleBlockTick(pos, this, TICK_STEP);
        }

        // Cooldown corto
        player.getItemCooldownManager().set(stack.getItem(), PLAYER_COOLDOWN_TICKS);
        return ItemActionResult.SUCCESS;
    }


    // Random calificado para evitar imports erróneos
    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, net.minecraft.util.math.random.Random random) {
        int p = state.get(PROGRESS);
        if (p <= 0) {
            if (state.get(LIT)) world.setBlockState(pos, state.with(LIT, false), Block.NOTIFY_LISTENERS);
            PENDING.remove(pos);
            return;
        }

        Pending pend = PENDING.get(pos);
        int targetSteps = (pend != null) ? pend.steps : 3;

        int next = p + 1;
        if (next <= targetSteps) {
            world.setBlockState(pos, state.with(PROGRESS, next).with(LIT, true), Block.NOTIFY_LISTENERS);
            world.playSound(null, pos, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 0.5f, 1.2f);
            world.scheduleBlockTick(pos, this, TICK_STEP);
        } else {
            world.playSound(null, pos, ModSounds.OLLA_FRY_END, SoundCategory.BLOCKS, 1.0f, 1.0f);

            if (pend != null) {
                ItemStack out = new ItemStack(Registries.ITEM.get(pend.resultId));
                PlayerEntity target = world.getPlayerByUuid(pend.playerId);
                boolean given = false;
                if (target != null && target.isAlive() &&
                        target.squaredDistanceTo(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) <= 64) {
                    if (!target.getInventory().insertStack(out)) {
                        Block.dropStack(world, pos, out);
                    }
                    target.addExperience((int) Math.max(0, Math.floor(pend.exp)));
                    given = true;
                }
                if (!given) Block.dropStack(world, pos, out);
            }

            PENDING.remove(pos);
            world.setBlockState(pos, state.with(PROGRESS, 0).with(LIT, false), Block.NOTIFY_ALL);
        }
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            PENDING.remove(pos);
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    // ===== Helpers =====
    private Optional<RecipeEntry<FritarRecipe>> findFritarRecipe(World world, Item ingredient) {
        SingleStackRecipeInput input = new SingleStackRecipeInput(new ItemStack(ingredient));
        return world.getRecipeManager().getFirstMatch(ModRecipes.FRITAR_TYPE, input, world);
    }

    private ItemStack findAceite(PlayerEntity player) {
        ItemStack off = player.getOffHandStack();
        if (isAceite(off)) return off;
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack s = player.getInventory().getStack(i);
            if (isAceite(s)) return s;
        }
        return ItemStack.EMPTY;
    }

    private boolean isAceite(ItemStack s) {
        if (s.isEmpty()) return false;
        // 1) Vía tag (cuando ya lo tengas bien)
        if (s.isIn(ModTags.ACEITES)) return true;
        // 2) Fallback por nombre (para no quedarte bloqueado)
        var id = net.minecraft.registry.Registries.ITEM.getId(s.getItem());
        var path = id.getPath();
        return "argentum".equals(id.getNamespace()) && (path.contains("aceite") || path.contains("grasa"));
    }

}
