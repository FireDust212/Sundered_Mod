package net.firedust.sunderedmod.block.custom;

import net.firedust.sunderedmod.block.entity.ModBlockEntities;
import net.firedust.sunderedmod.block.entity.PitTrapBlockEntity;
import net.firedust.sunderedmod.block.entity.PitTriggerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

import static net.firedust.sunderedmod.block.custom.PitTrigger.GRASS;

public class PitTrapBlock extends SunderedSpreaderBlock<PitTrapBlockEntity> {
    public PitTrapBlock(Properties pProperties) {
        super(pProperties, () -> {
            return ModBlockEntities.PIT_TRAP_BE.get();
        });
        this.registerDefaultState((BlockState) ((BlockState) this.stateDefinition.any()).setValue(GRASS, true));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new PitTriggerBlockEntity(blockPos, blockState);
    }

    // Tooltip (slightly different from the item method)
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.sundered_mod.pit_trap"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }


    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return pFacing == Direction.UP ? (BlockState) pState.setValue(GRASS, true) : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState $$1 = pContext.getLevel().getBlockState(pContext.getClickedPos().below());
        return (BlockState) this.defaultBlockState().setValue(GRASS, isGrassySetting($$1));
    }

    private static boolean isGrassySetting(BlockState pState) {
        return !pState.is(BlockTags.BASE_STONE_OVERWORLD);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(new Property[]{GRASS});
    }
}
