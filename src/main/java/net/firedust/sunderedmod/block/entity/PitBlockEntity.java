package net.firedust.sunderedmod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class PitBlockEntity extends SunderedSpreaderBlockEntity{
    private PitCoreBlockEntity core;
    private Vec3i corePos;
    private boolean coreLess = false;

    public PitBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.PIT_BLOCK_BE.get(), pPos, pBlockState);
        this.core = null;
        this.corePos = null;
    }
    public PitBlockEntity(BlockPos pPos, BlockState pBlockState, PitCoreBlockEntity core) {
        super(ModBlockEntities.PIT_BLOCK_BE.get(), pPos, pBlockState);
        this.core = core;
        this.corePos = core.getBlockPos();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putInt("pit_block_core.x", this.core.getBlockPos().getX());
        pTag.putInt("pit_block_core.y", this.core.getBlockPos().getY());
        pTag.putInt("pit_block_core.z", this.core.getBlockPos().getZ());
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.core = null;
        this.corePos = new Vec3i(
                pTag.getInt("pit_block_core.x"),
                pTag.getInt("pit_block_core.y"),
                pTag.getInt("pit_block_core.z")
        );
    }

    @Override
    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        // Get the core if possible
        if (this.coreLess) return;
        if (this.core == null){
            if (this.corePos != null){
                int checkingSize = 0;
                while (this.core == null && checkingSize < 3){
                    this.core = (PitCoreBlockEntity) pLevel.getBlockEntity(new BlockPos(
                            this.corePos.getX(),
                            this.corePos.getY()-checkingSize,
                            this.corePos.getZ())
                    );
                    checkingSize++;
                }
                // Stop checking for a core
                if (this.core == null) this.coreLess = true;
            }
            else{
                return;
            }
        }
        super.tick(pLevel, pPos, pState);
    }
}
