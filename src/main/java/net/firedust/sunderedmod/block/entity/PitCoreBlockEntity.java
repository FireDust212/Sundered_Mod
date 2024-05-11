package net.firedust.sunderedmod.block.entity;

import net.firedust.sunderedmod.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class PitCoreBlockEntity extends SunderedSpreaderBlockEntity {
    private int size;
    private int consumed;

    public PitCoreBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.PIT_CORE_BE.get(), pPos, pBlockState);
        this.size = 0;
        this.consumed = 5;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putInt("pit_core_block.size", this.size);
        pTag.putInt("pit_core_block.consumed", this.consumed);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        size = pTag.getInt("pit_core_block.size");
        consumed = pTag.getInt("pit_core_block.consumed");
    }

    @Override
    protected boolean canSpreadDown(Level pLevel, BlockPos pPos, BlockState pState) {
        // Spread if pit has consumed enough
        if (new Random().nextInt(20) == 0 && this.consumed >= this.size){
            // Get block below pit
            BlockState state = pLevel.getBlockState(new BlockPos(pPos.getX(), pPos.getY() - 1, pPos.getZ()));

            // Spread if block below pit is spreadable
            if (state.is(ModTags.Blocks.PIT_COMPONENTS) || state.is(ModTags.Blocks.PIT_SPREADABLE)){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    protected void spreadDown(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos pos = new BlockPos(pPos.getX(), pPos.getY() - 1, pPos.getZ());

        PitCoreBlockEntity newCore = new PitCoreBlockEntity(pos, pState);
        newCore.size = this.size + 1;
        newCore.consumed = this.consumed - this.size;

        pLevel.setBlock(pos, pState, 3);
        pLevel.setBlockEntity(newCore);

        pLevel.destroyBlock(pPos, false);
    }
}
