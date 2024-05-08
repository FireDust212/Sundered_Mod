package net.firedust.sunderedmod.entity.ai;

import net.firedust.sunderedmod.entity.custom.PitCreatureEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class PitCreatureAttackGoal extends MeleeAttackGoal {
    private final PitCreatureEntity entity;
    private int attackDelay = 12;
    private int ticksUntilNextAttack = 13;
    private boolean shouldCountTillNextAttack = false;

    public PitCreatureAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        entity = ((PitCreatureEntity) pMob);
    }

    @Override
    public void start() {
        super.start();
        attackDelay = 12;
        ticksUntilNextAttack = 13;
    }

    @Override
    public void stop() {
        entity.setAttacking(false);
        super.stop();
    }

    @Override
    public void tick() {
        super.tick();
        if (shouldCountTillNextAttack){
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
        }
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity pTarget) {
        // If it can see the enemy and is within attack range
        if(this.mob.isWithinMeleeAttackRange(pTarget) && this.mob.getSensing().hasLineOfSight(pTarget)){
            shouldCountTillNextAttack = true;

            if(isTimeToStartAttackAnim()){
                entity.setAttacking(true);
            }

            if (isTimeToAttack()){
                this.mob.getLookControl().setLookAt(pTarget.getX(), pTarget.getY(), pTarget.getZ());
                performAttack(pTarget);
            }
        }
        else {
            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            entity.setAttacking(false);
            entity.attackAnimTimeout = 0;
        }
    }

    protected void resetAttackCooldown(){
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay*2+1);
    }

    protected boolean isTimeToAttack(){
        return this.ticksUntilNextAttack <= 0;
    }

    protected boolean isTimeToStartAttackAnim(){
        return this.ticksUntilNextAttack <= attackDelay;
    }

    protected void performAttack(LivingEntity pEntity){
        this.resetAttackCooldown();
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.doHurtTarget(pEntity);
    }
}
