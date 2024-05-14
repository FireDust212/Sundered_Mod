package net.firedust.sunderedmod.block.entity;

import net.firedust.sunderedmod.block.ModBlocks;
import net.firedust.sunderedmod.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import static net.firedust.sunderedmod.block.custom.PitTrigger.GRASS;

public class PitBlockEntity extends PitComponentBlockEntity{
    private boolean noSpreadUp = false;
    private boolean noSpreadDown = false;
    private boolean noSpreadNorth = false;
    private boolean noSpreadSouth = false;
    private boolean noSpreadEast = false;
    private boolean noSpreadWest = false;

    public PitBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.PIT_BLOCK_BE.get(), pPos, pBlockState);
        this.core = null;
        this.corePos = null;
        this.size = 0;
    }
    public PitBlockEntity(BlockPos pPos, BlockState pBlockState, PitCoreBlockEntity core,
                          boolean noSpreadUp, boolean noSpreadDown, boolean noSpreadNorth,
                          boolean noSpreadSouth, boolean noSpreadEast, boolean noSpreadWest) {
        super(ModBlockEntities.PIT_BLOCK_BE.get(), pPos, pBlockState, core);

        this.noSpreadUp = noSpreadUp;
        this.noSpreadDown = noSpreadDown;
        this.noSpreadNorth = noSpreadNorth;
        this.noSpreadSouth = noSpreadSouth;
        this.noSpreadEast = noSpreadEast;
        this.noSpreadWest = noSpreadWest;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putBoolean("pit_block.noSpreadUp", this.noSpreadUp);
        pTag.putBoolean("pit_block.noSpreadDown", this.noSpreadDown);
        pTag.putBoolean("pit_block.noSpreadNorth", this.noSpreadNorth);
        pTag.putBoolean("pit_block.noSpreadSouth", this.noSpreadSouth);
        pTag.putBoolean("pit_block.noSpreadEast", this.noSpreadEast);
        pTag.putBoolean("pit_block.noSpreadWest", this.noSpreadWest);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.noSpreadUp = pTag.getBoolean("pit_block.noSpreadUp");
        this.noSpreadDown = pTag.getBoolean("pit_block.noSpreadDown");
        this.noSpreadNorth = pTag.getBoolean("pit_block.noSpreadNorth");
        this.noSpreadSouth = pTag.getBoolean("pit_block.noSpreadSouth");
        this.noSpreadEast = pTag.getBoolean("pit_block.noSpreadEast");
        this.noSpreadWest = pTag.getBoolean("pit_block.noSpreadWest");
    }

    @Override
    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(this.isSurrounded(pLevel, pPos, pState)){
            pLevel.destroyBlock(pPos, false);
            pLevel.removeBlockEntity(pPos);
            return;
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
        if (this.noSpreadUp) return false;
        BlockPos aPos = new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ());
        BlockState aboveState = pLevel.getBlockState(aPos);

        if (withinPit(aPos, new BlockPos(this.corePos)) &&
                aboveState.is(ModTags.Blocks.PIT_SPREADABLE) &&
                !(this.core == null)) {
            return true;
        }
        return false;
    }
    @Override
    protected boolean canSpreadDown(Level pLevel, BlockPos pPos, BlockState pState) {
        if (this.noSpreadDown) return false;
        BlockPos bPos = new BlockPos(pPos.getX(), pPos.getY() - 1, pPos.getZ());
        BlockState belowState = pLevel.getBlockState(bPos);

        if (bPos.getY() >= this.corePos.getY() &&
                withinPit(bPos, new BlockPos(this.corePos)) &&
                belowState.is(ModTags.Blocks.PIT_SPREADABLE) &&
                !(this.core == null)) {
            return true;
        }
        return false;
    }
    @Override
    protected boolean canSpreadNorth(Level pLevel, BlockPos pPos, BlockState pState) {
        if (this.noSpreadNorth) return false;
        BlockPos nPos = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() - 1);
        BlockState northState = pLevel.getBlockState(nPos);

        if (nPos.getY() >= this.corePos.getY() &&
                withinPit(nPos, new BlockPos(this.corePos)) &&
                northState.is(ModTags.Blocks.PIT_SPREADABLE) &&
                !(this.core == null)) {
            return true;
        }
        return false;
    }
    @Override
    protected boolean canSpreadSouth(Level pLevel, BlockPos pPos, BlockState pState) {
        if (this.noSpreadSouth) return false;
        BlockPos sPos = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() + 1);
        BlockState southState = pLevel.getBlockState(sPos);

        if (sPos.getY() >= this.corePos.getY() &&
                withinPit(sPos, new BlockPos(this.corePos)) &&
                southState.is(ModTags.Blocks.PIT_SPREADABLE) &&
                !(this.core == null)) {
            return true;
        }
        return false;
    }
    @Override
    protected boolean canSpreadEast(Level pLevel, BlockPos pPos, BlockState pState) {
        if (this.noSpreadEast) return false;
        BlockPos ePos = new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ());
        BlockState eastState = pLevel.getBlockState(ePos);

        if (ePos.getY() >= this.corePos.getY() &&
                withinPit(ePos, new BlockPos(this.corePos)) &&
                eastState.is(ModTags.Blocks.PIT_SPREADABLE) &&
                !(this.core == null)) {
            return true;
        }
        return false;
    }
    @Override
    protected boolean canSpreadWest(Level pLevel, BlockPos pPos, BlockState pState) {
        if (this.noSpreadWest) return false;
        BlockPos wPos = new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ());
        BlockState westState = pLevel.getBlockState(wPos);

        if (wPos.getY() >= this.corePos.getY() &&
                withinPit(wPos, new BlockPos(this.corePos)) &&
                westState.is(ModTags.Blocks.PIT_SPREADABLE) &&
                !(this.core == null)) {
            return true;
        }
        return false;
    }

//    @Override
//    protected void spreadUp(Level pLevel, BlockPos pPos, BlockState pState) {
//        BlockPos pos = new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ());
//        pLevel.setBlock(pos, ModBlocks.PIT_TRAP.get().defaultBlockState(), 3);
//        pLevel.setBlockEntity(new PitTrapBlockEntity(pos, ModBlocks.PIT_TRAP.get().defaultBlockState().setValue(GRASS, this.core.isGrassy()), this.core,
//                true, this.noSpreadDown, this.noSpreadNorth, this.noSpreadSouth, this.noSpreadEast, this.noSpreadWest));
//
//    }
    @Override
    protected void spreadDown(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos pos = new BlockPos(pPos.getX(), pPos.getY() - 1, pPos.getZ());
        pLevel.setBlock(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), 3);
        pLevel.setBlockEntity(new PitBlockEntity(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), this.core,
                true, this.noSpreadDown, this.noSpreadNorth, this.noSpreadSouth, this.noSpreadEast, this.noSpreadWest));
    }
    @Override
    protected void spreadNorth(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos pos = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() - 1);
        pLevel.setBlock(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), 3);
        pLevel.setBlockEntity(new PitBlockEntity(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), this.core,
                this.noSpreadUp, this.noSpreadDown, this.noSpreadNorth, true, this.noSpreadEast, this.noSpreadWest));
    }
    @Override
    protected void spreadSouth(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos pos = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() + 1);
        pLevel.setBlock(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), 3);
        pLevel.setBlockEntity(new PitBlockEntity(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), this.core,
                this.noSpreadUp, this.noSpreadDown, true, this.noSpreadSouth, this.noSpreadEast, this.noSpreadWest));
    }
    @Override
    protected void spreadEast(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos pos = new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ());
        pLevel.setBlock(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), 3);
        pLevel.setBlockEntity(new PitBlockEntity(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), this.core,
                this.noSpreadUp, this.noSpreadDown, this.noSpreadNorth, this.noSpreadSouth, this.noSpreadEast, true));
    }
    @Override
    protected void spreadWest(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos pos = new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ());
        pLevel.setBlock(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), 3);
        pLevel.setBlockEntity(new PitBlockEntity(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), this.core,
                this.noSpreadUp, this.noSpreadDown, this.noSpreadNorth, this.noSpreadSouth, true, this.noSpreadWest));
    }

    protected boolean withinPit(BlockPos newPos, BlockPos cp){
        float x = Math.abs(newPos.getX() - cp.getX());
        float y = Math.abs(newPos.getY() - cp.getY());
        float z = Math.abs(newPos.getZ() - cp.getZ());

        // Square
        //return y <= this.size && x <= (y + 1) / 2 && z <= (y + 1) / 2; // skinny
        //return y <= this.size && x <= y + 1 && z <= y + 1; // regular
        //return y <= this.size && x <= (y + 1) * 2 && z <= (y + 1) * 2; // wide

        // Circle
        return y <= this.size && Math.pow(x, 2) + Math.pow(z, 2) <= Math.pow(y + 1, 2);
    }
}
