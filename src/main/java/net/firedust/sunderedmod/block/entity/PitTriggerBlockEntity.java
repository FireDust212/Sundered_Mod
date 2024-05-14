package net.firedust.sunderedmod.block.entity;

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
}
