package net.firedust.sunderedmod.block.entity;

import net.firedust.sunderedmod.block.ModBlocks;
import net.firedust.sunderedmod.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class PitTriggerBlockEntity extends PitComponentBlockEntity{
    public PitTriggerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.PIT_TRIGGER_BE.get(), pPos, pBlockState);
    }
    public PitTriggerBlockEntity(BlockPos pPos, BlockState pBlockState, PitCoreBlockEntity core) {
        super(ModBlockEntities.PIT_TRIGGER_BE.get(), pPos, pBlockState, core);
    }

    public void tellCoreAboutTrigger(){
        if (this.core != null) this.core.resetTriggerTimer();
    }

    @Override
    protected boolean canSpreadNorth(Level pLevel, BlockPos pPos, BlockState pState) {
        if(this.coreLess) return false;
        BlockState state = pLevel.getBlockState(new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() - 1));
        return state.is(ModTags.Blocks.PIT_SPREADABLE);
    }
    @Override
    protected boolean canSpreadSouth(Level pLevel, BlockPos pPos, BlockState pState) {
        if(this.coreLess) return false;
        BlockState state = pLevel.getBlockState(new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() + 1));
        return state.is(ModTags.Blocks.PIT_SPREADABLE);
    }
    @Override
    protected boolean canSpreadEast(Level pLevel, BlockPos pPos, BlockState pState) {
        if(this.coreLess) return false;
        BlockState state = pLevel.getBlockState(new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ()));
        return state.is(ModTags.Blocks.PIT_SPREADABLE);    }
    @Override
    protected boolean canSpreadWest(Level pLevel, BlockPos pPos, BlockState pState) {
        if(this.coreLess) return false;
        BlockState state = pLevel.getBlockState(new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ()));
        return state.is(ModTags.Blocks.PIT_SPREADABLE);    }

    @Override
    protected void spreadNorth(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos pos = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() - 1);
        pLevel.setBlock(pos, ModBlocks.PIT_TRAP.get().defaultBlockState(), 3);
        pLevel.setBlockEntity(new PitTrapBlockEntity(pos, ModBlocks.PIT_TRAP.get().defaultBlockState(), this.core,
                false, false, false,
                true, false, false));
    }
    @Override
    protected void spreadSouth(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos pos = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() + 1);
        pLevel.setBlock(pos, ModBlocks.PIT_TRAP.get().defaultBlockState(), 3);
        pLevel.setBlockEntity(new PitTrapBlockEntity(pos, ModBlocks.PIT_TRAP.get().defaultBlockState(), this.core,
                false, false, true,
                false, false, false));
    }
    @Override
    protected void spreadEast(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos pos = new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ());
        pLevel.setBlock(pos, ModBlocks.PIT_TRAP.get().defaultBlockState(), 3);
        pLevel.setBlockEntity(new PitTrapBlockEntity(pos, ModBlocks.PIT_TRAP.get().defaultBlockState(), this.core,
                false, false, false,
                false, false, true));
    }
    @Override
    protected void spreadWest(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos pos = new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ());
        pLevel.setBlock(pos, ModBlocks.PIT_TRAP.get().defaultBlockState(), 3);
        pLevel.setBlockEntity(new PitTrapBlockEntity(pos, ModBlocks.PIT_TRAP.get().defaultBlockState(), this.core,
                false, false, false,
                false, true, false));
    }
}
