package net.firedust.sunderedmod.block.custom;

import net.firedust.sunderedmod.block.entity.ModBlockEntities;
import net.firedust.sunderedmod.block.entity.PitCoreBlockEntity;
import net.firedust.sunderedmod.block.entity.SunderedSpreaderBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public abstract class SunderedSpreaderBlock<T extends SunderedSpreaderBlockEntity> extends BaseEntityBlock {
    BlockEntityType<T> blockEntity;

    public SunderedSpreaderBlock(Properties pProperties, BlockEntityType<T> blockEntity) {
        super(pProperties);
        this.blockEntity = blockEntity;
    }

    public RenderShape getRenderShape(BlockState p_222120_) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide){
            return null;
        }
        return createTickerHelper(pBlockEntityType, blockEntity,
                (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
    }
}
