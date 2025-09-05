package argentum.block.custom;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.block.Blocks;

public class Yerba_Top extends Block {

    public static final IntProperty AGE = IntProperty.of("age", 0, 7);

    public Yerba_Top() {
        super(AbstractBlock.Settings.copy(Blocks.WHEAT)); // Copiamos las propiedades de un cultivo
        setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
