package argentum.block.custom;

import argentum.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class YerbaPlantaBlock extends CropBlock {

    public YerbaPlantaBlock(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxAge() {
        return 7; // Edad máxima 7 era 5 pero no funcionaba
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int age = this.getAge(state);
        if (age < this.getMaxAge()) {
            world.setBlockState(pos, this.withAge(age + 1), 3);
        } else {
            // Cuando llegue a 7, colocar el bloque superior
            BlockPos topPos = pos.up();
            if (world.isAir(topPos)) {
                world.setBlockState(topPos, ModBlocks.YERBA_TOP.getDefaultState(), 3);
            }
        }
    }


    public boolean canPlaceAt(BlockState state, World world, BlockPos pos) {
        return super.canPlaceAt(state, world, pos);
    }
}
