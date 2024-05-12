package net.firedust.sunderedmod.block.entity;

import net.firedust.sunderedmod.block.ModBlocks;
import net.firedust.sunderedmod.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;

import java.util.Random;

public class PitBlockEntity extends SunderedSpreaderBlockEntity{
    private PitCoreBlockEntity core;
    private Vec3i corePos;
    private int size;
    private boolean coreLess = false;

    public PitBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.PIT_BLOCK_BE.get(), pPos, pBlockState);
        this.core = null;
        this.corePos = null;
        this.size = 0;
    }
    public PitBlockEntity(BlockPos pPos, BlockState pBlockState, PitCoreBlockEntity core) {
        super(ModBlockEntities.PIT_BLOCK_BE.get(), pPos, pBlockState);
        this.core = core;
        this.corePos = core.getBlockPos();
        this.size = core.getSize();
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
        if(this.isSurrounded(pLevel, pPos, pState)){
            pLevel.destroyBlock(pPos, false);
            pLevel.removeBlockEntity(pPos);
        }

        // Get the core if possible
        if (this.coreLess) {
            // Decay if no core is found
//            if (new Random().nextInt(SPREAD_TIMER) == 0) {
//                pLevel.destroyBlock(pPos, false);
//            }
            return;
        }
        this.core = (PitCoreBlockEntity) pLevel.getBlockEntity(new BlockPos(this.corePos));
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
                    this.size = 0;
                    return;
                }
                this.corePos = this.core.getBlockPos();
                this.size = this.core.getSize();
            }
            else{
                return;
            }
        }
        super.tick(pLevel, pPos, pState);
    }

    protected boolean isSurrounded(Level pLevel, BlockPos pPos, BlockState pState){
        BlockState aboveState = pLevel.getBlockState(new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ()));
        BlockState belowState = pLevel.getBlockState(new BlockPos(pPos.getX(), pPos.getY() - 1, pPos.getZ()));
        BlockState northState = pLevel.getBlockState(new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() - 1));
        BlockState southState = pLevel.getBlockState(new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() + 1));
        BlockState eastState = pLevel.getBlockState(new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ()));
        BlockState westState = pLevel.getBlockState(new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ()));
        if (
                (aboveState.isAir() || aboveState.is(ModTags.Blocks.PIT_COMPONENTS)) &&
                (belowState.isAir() || belowState.is(ModTags.Blocks.PIT_COMPONENTS)) &&
                (northState.isAir() || northState.is(ModTags.Blocks.PIT_COMPONENTS)) &&
                (southState.isAir() || southState.is(ModTags.Blocks.PIT_COMPONENTS)) &&
                (eastState.isAir() || eastState.is(ModTags.Blocks.PIT_COMPONENTS)) &&
                (westState.isAir() || westState.is(ModTags.Blocks.PIT_COMPONENTS))
        ){
            return true;
        }
        return false;
    }

    @Override
    protected boolean canSpreadUp(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos aPos = new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ());
        BlockState aboveState = pLevel.getBlockState(aPos);

        if (withinPit(aPos, (BlockPos) this.corePos, this.size) &&
                (aboveState.is(ModTags.Blocks.PIT_SPREADABLE) || aboveState.isAir()) &&
                !(this.core == null)) {
            return true;
        }
        return false;
    }
    @Override
    protected boolean canSpreadDown(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos bPos = new BlockPos(pPos.getX(), pPos.getY() - 1, pPos.getZ());
        BlockState belowState = pLevel.getBlockState(bPos);

        if (bPos.getY() >= this.corePos.getY() &&
                withinPit(bPos, (BlockPos) this.corePos, this.size) &&
                (belowState.is(ModTags.Blocks.PIT_SPREADABLE) || belowState.isAir()) &&
                !(this.core == null)) {
            return true;
        }
        return false;
    }
    @Override
    protected boolean canSpreadNorth(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos nPos = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() - 1);
        BlockState northState = pLevel.getBlockState(nPos);

        if (nPos.getY() >= this.corePos.getY() &&
                withinPit(nPos, (BlockPos) this.corePos, this.size) &&
                (northState.is(ModTags.Blocks.PIT_SPREADABLE) || northState.isAir()) &&
                !(this.core == null)) {
            return true;
        }
        return false;
    }
    @Override
    protected boolean canSpreadSouth(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos sPos = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() + 1);
        BlockState southState = pLevel.getBlockState(sPos);

        if (sPos.getY() >= this.corePos.getY() &&
                withinPit(sPos, (BlockPos) this.corePos, this.size) &&
                (southState.is(ModTags.Blocks.PIT_SPREADABLE) || southState.isAir()) &&
                !(this.core == null)) {
            return true;
        }
        return false;
    }
    @Override
    protected boolean canSpreadEast(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos ePos = new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ());
        BlockState eastState = pLevel.getBlockState(ePos);

        if (ePos.getY() >= this.corePos.getY() &&
                withinPit(ePos, (BlockPos) this.corePos, this.size) &&
                (eastState.is(ModTags.Blocks.PIT_SPREADABLE) || eastState.isAir()) &&
                !(this.core == null)) {
            return true;
        }
        return false;
    }
    @Override
    protected boolean canSpreadWest(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos wPos = new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ());
        BlockState westState = pLevel.getBlockState(wPos);

        if (wPos.getY() >= this.corePos.getY() &&
                withinPit(wPos, (BlockPos) this.corePos, this.size) &&
                (westState.is(ModTags.Blocks.PIT_SPREADABLE) || westState.isAir()) &&
                !(this.core == null)) {
            return true;
        }
        return false;
    }

    @Override
    protected void spreadDown(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos pos = new BlockPos(pPos.getX(), pPos.getY() - 1, pPos.getZ());
        pLevel.setBlock(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), 3);
        pLevel.setBlockEntity(new PitBlockEntity(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), this.core));
    }
    @Override
    protected void spreadNorth(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos pos = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() - 1);
        pLevel.setBlock(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), 3);
        pLevel.setBlockEntity(new PitBlockEntity(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), this.core));
    }
    @Override
    protected void spreadSouth(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos pos = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() + 1);
        pLevel.setBlock(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), 3);
        pLevel.setBlockEntity(new PitBlockEntity(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), this.core));
    }
    @Override
    protected void spreadEast(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos pos = new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ());
        pLevel.setBlock(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), 3);
        pLevel.setBlockEntity(new PitBlockEntity(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), this.core));
    }
    @Override
    protected void spreadWest(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos pos = new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ());
        pLevel.setBlock(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), 3);
        pLevel.setBlockEntity(new PitBlockEntity(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), this.core));
    }

    protected boolean withinPit(BlockPos newPos, BlockPos cp, int coreSize){
        float x = Math.abs(newPos.getX() - cp.getX());
        float y = Math.abs(newPos.getY() - cp.getY());
        float z = Math.abs(newPos.getZ() - cp.getZ());

        return y <= size && x <= y + 1 && z <= y + 1;
    }
}
