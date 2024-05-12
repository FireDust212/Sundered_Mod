package net.firedust.sunderedmod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class PitTriggerEntity extends SunderedSpreaderBlockEntity{
    public PitTriggerEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.PIT_TRIGGER_BE.get(), pPos, pBlockState);
    }
}
