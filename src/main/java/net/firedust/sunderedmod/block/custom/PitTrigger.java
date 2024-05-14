package net.firedust.sunderedmod.block.custom;

import net.firedust.sunderedmod.block.entity.ModBlockEntities;
import net.firedust.sunderedmod.block.entity.PitTriggerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class PitTrigger extends SunderedSpreaderBlock<PitTriggerBlockEntity> {
    public static final BooleanProperty GRASS;

    public PitTrigger(Properties pProperties) {
        super(pProperties, () -> {return ModBlockEntities.PIT_TRIGGER_BE.get();});
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(GRASS, true));
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        ((PitTriggerBlockEntity) Objects.requireNonNull(pLevel.getBlockEntity(pPos))).tellCoreAboutTrigger();

        pLevel.removeBlockEntity(pPos);
        pLevel.destroyBlock(pPos, false);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new PitTriggerBlockEntity(blockPos, blockState);
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return pFacing == Direction.UP ? (BlockState)pState.setValue(GRASS, true) : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState $$1 = pContext.getLevel().getBlockState(pContext.getClickedPos().below());
        return (BlockState)this.defaultBlockState().setValue(GRASS, isGrassySetting($$1));
    }

    private static boolean isGrassySetting(BlockState pState) {
        return !pState.is(BlockTags.BASE_STONE_OVERWORLD);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(new Property[]{GRASS});
    }

    static {
        GRASS = BooleanProperty.create("grass");
    }
}
