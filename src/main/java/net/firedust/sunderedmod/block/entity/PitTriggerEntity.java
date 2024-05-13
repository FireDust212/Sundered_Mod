package net.firedust.sunderedmod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class PitTriggerEntity extends SunderedSpreaderBlockEntity{
    private PitCoreBlockEntity core;
    private Vec3i corePos;
    private boolean coreLess = false;


    public PitTriggerEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.PIT_TRIGGER_BE.get(), pPos, pBlockState);
    }
    public PitTriggerEntity(BlockPos pPos, BlockState pBlockState, PitCoreBlockEntity core) {
        super(ModBlockEntities.PIT_TRIGGER_BE.get(), pPos, pBlockState);
        this.core = core;
        this.corePos = core.getBlockPos();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        if (this.core != null) {
            pTag.putInt("pit_trigger.core.x", this.core.getBlockPos().getX());
            pTag.putInt("pit_trigger.core.y", this.core.getBlockPos().getY());
            pTag.putInt("pit_trigger.core.z", this.core.getBlockPos().getZ());
        }

        pTag.putBoolean("pit_trigger.coreLess", this.coreLess);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);

        this.core = null;
        this.coreLess = pTag.getBoolean("pit_trigger.coreLess");
        if(!this.coreLess) {
            this.corePos = new Vec3i(
                    pTag.getInt("pit_trigger.core.x"),
                    pTag.getInt("pit_trigger.core.y"),
                    pTag.getInt("pit_trigger.core.z")
            );
        }
        else this.corePos = null;
    }

    @Override
    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        // Get the core if possible
        if (this.coreLess) {
            return;
        }
        try{
            this.core = (PitCoreBlockEntity) pLevel.getBlockEntity(new BlockPos(this.corePos));
        } catch(Exception e){
            this.core = null;
        }
        if (this.core == null){
            if (this.corePos != null){
                int checkingSize = 0;
                while (this.core == null && checkingSize < 3){
                    try{
                        this.core = (PitCoreBlockEntity) pLevel.getBlockEntity(new BlockPos(
                                this.corePos.getX(),
                                this.corePos.getY()-checkingSize,
                                this.corePos.getZ())
                        );
                    } catch(Exception e){
                        System.out.println(e);
                    }
                    checkingSize++;
                }
                // Stop checking for a core
                if (this.core == null) {
                    this.coreLess = true;
                    this.corePos = null;
                    return;
                }
                this.corePos = this.core.getBlockPos();
            }
            else{
                return;
            }
        }

        super.tick(pLevel, pPos, pState);
    }

    public void tellCoreAboutTrigger(){
        if (this.core != null) this.core.resetTriggerTimer();
    }


}
