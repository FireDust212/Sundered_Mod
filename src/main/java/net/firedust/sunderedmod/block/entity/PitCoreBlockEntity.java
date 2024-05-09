package net.firedust.sunderedmod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class PitCoreBlockEntity extends SunderedSpreaderBlockEntity {
    private int size;

    public PitCoreBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.PIT_CORE_BE.get(), pPos, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putInt("pit_core_block.size", this.size);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        size = pTag.getInt("pit_core_block.size");
    }


}
