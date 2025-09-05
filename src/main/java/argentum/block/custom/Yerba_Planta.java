package argentum.block.custom;

import argentum.block.ModBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random; // IMPORT CORRECTO
import net.minecraft.block.CropBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import argentum.item.ModItem;

public class Yerba_Planta extends CropBlock {

    public Yerba_Planta(Settings settings) {
        super(settings);
    }

    // Define tus semillas
    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItem.YERBA;
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);

        int age = state.get(AGE);
        if (age >= 7) {
            BlockPos topPos = pos.up();
            if (world.isAir(topPos)) {
                world.setBlockState(topPos, ModBlocks.YERBA_TOP.getDefaultState().with(Yerba_Top.AGE, age));
            }
        }
    }


    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack) {
        super.afterBreak(world, player, pos, state, blockEntity, stack);

        BlockPos topPos = pos.up();
        if (world.getBlockState(topPos).getBlock() instanceof Yerba_Top) {
            world.breakBlock(topPos, false);
        }
    }
}

