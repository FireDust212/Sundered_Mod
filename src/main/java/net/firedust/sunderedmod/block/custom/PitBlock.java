package net.firedust.sunderedmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PitBlock extends DropExperienceBlock {
    public PitBlock(Properties pProperties, IntProvider pXpRange) {
        super(pProperties, pXpRange);
    }

    // Same stepOn method as magma block, but without the sneaking and frostwalker check
    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        pEntity.hurt(pLevel.damageSources().hotFloor(), 1.0F);
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
}
