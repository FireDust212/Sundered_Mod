package net.firedust.sunderedmod.block.custom;

import net.firedust.sunderedmod.block.entity.ModBlockEntities;
import net.firedust.sunderedmod.block.entity.PitBlockEntity;
import net.firedust.sunderedmod.entity.custom.PitCreatureEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PitBlock extends SunderedSpreaderBlock<PitBlockEntity> {
    private final IntProvider xpRange;

    public PitBlock(Properties pProperties, IntProvider pXpRange) {
        super(pProperties, () -> {return ModBlockEntities.PIT_BLOCK_BE.get();});
        this.xpRange = pXpRange;
    }

    // Same stepOn method as magma block, but without the sneaking and frostwalker check
    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (!(pEntity instanceof PitCreatureEntity)) {
            pEntity.hurt(pLevel.damageSources().stalagmite(), 1.0F);
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    // Same fallOn method as pointed dripstone
    @Override
    public void fallOn(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float pFallDistance) {
        pEntity.causeFallDamage(pFallDistance + 2.0F, 2.0F, pLevel.damageSources().stalagmite());
    }

    // Tooltip (slightly different from the item method)
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.sundered_mod.pit_block"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    // Use the methods in a DropExperienceBlock
    public void spawnAfterBreak(BlockState pState, ServerLevel pLevel, BlockPos pPos, ItemStack pStack, boolean pDropExperience) {
        super.spawnAfterBreak(pState, pLevel, pPos, pStack, pDropExperience);
    }

    public int getExpDrop(BlockState state, LevelReader level, RandomSource randomSource, BlockPos pos, int fortuneLevel, int silkTouchLevel) {
        return silkTouchLevel == 0 ? this.xpRange.sample(randomSource) : 0;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new PitBlockEntity(blockPos, blockState);
    }
}
