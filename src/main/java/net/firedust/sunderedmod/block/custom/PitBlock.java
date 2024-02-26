package net.firedust.sunderedmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockState;

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
}
