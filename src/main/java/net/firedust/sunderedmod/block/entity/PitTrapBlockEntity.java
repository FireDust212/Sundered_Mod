package net.firedust.sunderedmod.block.entity;

import net.firedust.sunderedmod.block.ModBlocks;
import net.firedust.sunderedmod.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class PitTrapBlockEntity extends PitComponentBlockEntity{
    private boolean noSpreadUp = false;
    private boolean noSpreadDown = false;
    private boolean noSpreadNorth = false;
    private boolean noSpreadSouth = false;
    private boolean noSpreadEast = false;
    private boolean noSpreadWest = false;

    public PitTrapBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.PIT_TRAP_BE.get(), pPos, pBlockState);
    }
    public PitTrapBlockEntity(BlockPos pPos, BlockState pBlockState, PitCoreBlockEntity core,
                              boolean noSpreadUp, boolean noSpreadDown, boolean noSpreadNorth,
                              boolean noSpreadSouth, boolean noSpreadEast, boolean noSpreadWest) {
        super(ModBlockEntities.PIT_TRAP_BE.get(), pPos, pBlockState, core);

        this.noSpreadUp = noSpreadUp;
        this.noSpreadDown = noSpreadDown;
        this.noSpreadNorth = noSpreadNorth;
        this.noSpreadSouth = noSpreadSouth;
        this.noSpreadEast = noSpreadEast;
        this.noSpreadWest = noSpreadWest;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putBoolean("PIT_TRAP.noSpreadUp", this.noSpreadUp);
        pTag.putBoolean("PIT_TRAP.noSpreadDown", this.noSpreadDown);
        pTag.putBoolean("PIT_TRAP.noSpreadNorth", this.noSpreadNorth);
        pTag.putBoolean("PIT_TRAP.noSpreadSouth", this.noSpreadSouth);
        pTag.putBoolean("PIT_TRAP.noSpreadEast", this.noSpreadEast);
        pTag.putBoolean("PIT_TRAP.noSpreadWest", this.noSpreadWest);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.noSpreadUp = pTag.getBoolean("PIT_TRAP.noSpreadUp");
        this.noSpreadDown = pTag.getBoolean("PIT_TRAP.noSpreadDown");
        this.noSpreadNorth = pTag.getBoolean("PIT_TRAP.noSpreadNorth");
        this.noSpreadSouth = pTag.getBoolean("PIT_TRAP.noSpreadSouth");
        this.noSpreadEast = pTag.getBoolean("PIT_TRAP.noSpreadEast");
        this.noSpreadWest = pTag.getBoolean("PIT_TRAP.noSpreadWest");
    }


    @Override
    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(this.coreLess || (this.core != null && this.core.isTriggerTriggered())){
            pLevel.destroyBlock(pPos, false);
            pLevel.removeBlockEntity(pPos);
            return;
        }
        super.tick(pLevel, pPos, pState);
    }

    @Override
    protected boolean canSpreadNorth(Level pLevel, BlockPos pPos, BlockState pState) {
        if (this.noSpreadNorth) return false;
        BlockPos nPos = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() - 1);
        BlockState northState = pLevel.getBlockState(nPos);

        if (nPos.getY() >= this.corePos.getY() &&
                abovePit(nPos, new BlockPos(this.corePos)) &&
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
                abovePit(sPos, new BlockPos(this.corePos)) &&
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
                abovePit(ePos, new BlockPos(this.corePos)) &&
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
                abovePit(wPos, new BlockPos(this.corePos)) &&
                westState.is(ModTags.Blocks.PIT_SPREADABLE) &&
                !(this.core == null)) {
            return true;
        }
        return false;
    }



    @Override
    protected void spreadNorth(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos pos = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() - 1);
        pLevel.setBlock(pos, ModBlocks.PIT_TRAP.get().defaultBlockState(), 3);
        pLevel.setBlockEntity(new PitTrapBlockEntity(pos, ModBlocks.PIT_TRAP.get().defaultBlockState(), this.core,
                this.noSpreadUp, this.noSpreadDown, this.noSpreadNorth, true, this.noSpreadEast, this.noSpreadWest));
    }
    @Override
    protected void spreadSouth(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos pos = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() + 1);
        pLevel.setBlock(pos, ModBlocks.PIT_TRAP.get().defaultBlockState(), 3);
        pLevel.setBlockEntity(new PitTrapBlockEntity(pos, ModBlocks.PIT_TRAP.get().defaultBlockState(), this.core,
                this.noSpreadUp, this.noSpreadDown, true, this.noSpreadSouth, this.noSpreadEast, this.noSpreadWest));
    }
    @Override
    protected void spreadEast(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos pos = new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ());
        pLevel.setBlock(pos, ModBlocks.PIT_TRAP.get().defaultBlockState(), 3);
        pLevel.setBlockEntity(new PitTrapBlockEntity(pos, ModBlocks.PIT_TRAP.get().defaultBlockState(), this.core,
                this.noSpreadUp, this.noSpreadDown, this.noSpreadNorth, this.noSpreadSouth, this.noSpreadEast, true));
    }
    @Override
    protected void spreadWest(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos pos = new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ());
        pLevel.setBlock(pos, ModBlocks.PIT_TRAP.get().defaultBlockState(), 3);
        pLevel.setBlockEntity(new PitTrapBlockEntity(pos, ModBlocks.PIT_TRAP.get().defaultBlockState(), this.core,
                this.noSpreadUp, this.noSpreadDown, this.noSpreadNorth, this.noSpreadSouth, true, this.noSpreadWest));
    }

    protected boolean abovePit(BlockPos newPos, BlockPos cp){
        float x = Math.abs(newPos.getX() - cp.getX());
        float y = Math.abs(newPos.getY() - cp.getY());
        float z = Math.abs(newPos.getZ() - cp.getZ());

        y--;

        // Square
        //return y <= this.size && x <= (y + 1) / 2 && z <= (y + 1) / 2; // skinny
        //return y <= this.size && x <= y + 1 && z <= y + 1; // regular
        //return y <= this.size && x <= (y + 1) * 2 && z <= (y + 1) * 2; // wide

        // Circle
        return y <= this.size && Math.pow(x, 2) + Math.pow(z, 2) <= Math.pow(y + 1, 2);
    }
}
