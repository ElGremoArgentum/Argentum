package argentum.block;

import argentum.block.entity.OllaBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class OllaBlock extends AbstractFurnaceBlock {

    // === Codec obligatorio en 1.21 ===
    public static final MapCodec<OllaBlock> CODEC = createCodec(OllaBlock::new);

    public OllaBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(Properties.HORIZONTAL_FACING, Direction.SOUTH)
                .with(Properties.LIT, false));
    }

    @Override
    public MapCodec<? extends AbstractFurnaceBlock> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new OllaBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                .with(Properties.LIT, false);
    }

    @Nullable
    @Override
    @SuppressWarnings("unchecked")
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            World world, BlockState state, BlockEntityType<T> type
    ) {
        if (world.isClient) return null;
        // si el tipo no es el nuestro, no hay ticker
        if (type != argentum.Argentum.OLLA_BE) return null;

        // delega al tick vanilla de hornos
        return (BlockEntityTicker<T>) (BlockEntityTicker<OllaBlockEntity>)
                ((w, pos, st, be) -> AbstractFurnaceBlockEntity.tick(w, pos, st, be));
    }


    // Método que abre la GUI (llamado por AbstractFurnaceBlock.onUse)
    @Override
    protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
        NamedScreenHandlerFactory factory = (NamedScreenHandlerFactory) world.getBlockEntity(pos);
        if (factory != null) {
            player.openHandledScreen(factory);
        }
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void appendProperties(StateManager.Builder<net.minecraft.block.Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING, Properties.LIT);
    }
}
