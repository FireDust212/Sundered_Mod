package net.firedust.sunderedmod.block.entity;

import net.firedust.sunderedmod.block.ModBlocks;
import net.firedust.sunderedmod.entity.custom.PitCreatureEntity;
import net.firedust.sunderedmod.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.BlockPositionSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class PitCoreBlockEntity extends SunderedSpreaderBlockEntity implements GameEventListener.Holder{
    private int size;
    private int consumed;
    private PitCoreListener pitCoreListener;

    public PitCoreBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.PIT_CORE_BE.get(), pPos, pBlockState);
        this.size = 0;
        this.consumed = 0; //5
        this.pitCoreListener = new PitCoreListener(pBlockState, new BlockPositionSource(pPos), this.size + 1);
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
        this.pitCoreListener.radius =  this.size + 1;
    }

    @Override
    protected boolean canSpreadUp(Level pLevel, BlockPos pPos, BlockState pState) {
        // Get block above pit core by size
        BlockState state = pLevel.getBlockState(new BlockPos(pPos.getX(), pPos.getY() + this.size, pPos.getZ()));

        // Spread if block is spreadable
        if (state.isAir()){
            return true;
        }
        return false;
    }
    @Override
    protected boolean canSpreadDown(Level pLevel, BlockPos pPos, BlockState pState) {
        // Spread if pit has consumed enough, only if all spots around it are pit
        if (this.consumed >= this.size &&
                !this.canSpreadNorth(pLevel, pPos, pState) &&
                !this.canSpreadSouth(pLevel, pPos, pState) &&
                !this.canSpreadEast(pLevel, pPos, pState) &&
                !this.canSpreadWest(pLevel, pPos, pState)){
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
    protected boolean canSpreadNorth(Level pLevel, BlockPos pPos, BlockState pState) {
        if(this.size == 0) return false;
        // Get block north of pit core
        BlockState state = pLevel.getBlockState(new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() - 1));

        // Spread if block is spreadable
        if (state.is(ModTags.Blocks.PIT_SPREADABLE)){
            return true;
        }
        return false;
    }
    @Override
    protected boolean canSpreadSouth(Level pLevel, BlockPos pPos, BlockState pState) {
        if(this.size == 0) return false;
        // Get block south of pit core
        BlockState state = pLevel.getBlockState(new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() + 1));

        // Spread if block is spreadable
        if (state.is(ModTags.Blocks.PIT_SPREADABLE)){
            return true;
        }
        return false;
    }
    @Override
    protected boolean canSpreadEast(Level pLevel, BlockPos pPos, BlockState pState) {
        if(this.size == 0) return false;
        // Get block east of pit core
        BlockState state = pLevel.getBlockState(new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ()));

        // Spread if block is spreadable
        if (state.is(ModTags.Blocks.PIT_SPREADABLE)){
            return true;
        }
        return false;
    }
    @Override
    protected boolean canSpreadWest(Level pLevel, BlockPos pPos, BlockState pState) {
        if(this.size == 0) return false;
        // Get block west of pit core
        BlockState state = pLevel.getBlockState(new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ()));

        // Spread if block is spreadable
        if (state.is(ModTags.Blocks.PIT_SPREADABLE)){
            return true;
        }
        return false;
    }

    @Override
    protected void spreadUp(Level pLevel, BlockPos pPos, BlockState pState) {
        super.spreadUp(pLevel, pPos, pState);
    }
    @Override
    protected void spreadDown(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos pos = new BlockPos(pPos.getX(), pPos.getY() - 1, pPos.getZ());

        PitCoreBlockEntity newCore = new PitCoreBlockEntity(pos, pState);
        newCore.size = this.size + 1;
        newCore.consumed = this.consumed - newCore.size;

        this.pitCoreListener = new PitCoreListener(newCore.getBlockState(), new BlockPositionSource(pos), this.size + 1);

        pLevel.setBlock(pos, pState, 3);
        pLevel.setBlockEntity(newCore);


        pLevel.destroyBlock(pPos, false);
        pLevel.removeBlockEntity(pPos);
    }
    @Override
    protected void spreadNorth(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos pos = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() - 1);
        pLevel.setBlock(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), 3);
        pLevel.setBlockEntity(new PitBlockEntity(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), this));
    }
    @Override
    protected void spreadSouth(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos pos = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() + 1);
        pLevel.setBlock(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), 3);
        pLevel.setBlockEntity(new PitBlockEntity(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), this));
    }
    @Override
    protected void spreadEast(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos pos = new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ());
        pLevel.setBlock(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), 3);
        pLevel.setBlockEntity(new PitBlockEntity(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), this));
    }
    @Override
    protected void spreadWest(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos pos = new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ());
        pLevel.setBlock(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), 3);
        pLevel.setBlockEntity(new PitBlockEntity(pos, ModBlocks.PIT_BLOCK.get().defaultBlockState(), this));
    }

    public void grow(){
        ++this.consumed;
    }

    @Override
    public PitCoreListener getListener() {
        return this.pitCoreListener;
    }

    // Taking this from SculkCatlystBlockEntity
    public static class PitCoreListener implements GameEventListener {
        public int radius;
        private final BlockState blockState;
        private final PositionSource positionSource;

        public PitCoreListener(BlockState pBlockState, PositionSource pPositionSource, int radius) {
            this.blockState = pBlockState;
            this.positionSource = pPositionSource;
            this.radius = radius;
        }

        @Override
        public PositionSource getListenerSource() {
            return this.positionSource;
        }

        @Override
        public int getListenerRadius() {
            return this.radius;
        }

        @Override
        public boolean handleGameEvent(ServerLevel serverLevel, GameEvent gameEvent, GameEvent.Context context, Vec3 pos) {
            if (gameEvent == GameEvent.ENTITY_DIE && pos.y > this.positionSource.getPosition(serverLevel).get().y) {
                Entity source = context.sourceEntity();
                if (source instanceof LivingEntity && !(source instanceof PitCreatureEntity)) {
                    LivingEntity livSource = (LivingEntity) source;
                    if (!livSource.wasExperienceConsumed()) {
                        livSource.skipDropExperience();
                    }
                    Vec3 pos2 = positionSource.getPosition(serverLevel).get();
                    PitCoreBlockEntity pE = ((PitCoreBlockEntity) serverLevel.getBlockEntity(new BlockPos((int) pos2.x, (int) pos2.y, (int) pos2.z)));
                    if(pE != null) pE.grow();
                    return true;
                }
            }
            return false;
        }
    }
}

