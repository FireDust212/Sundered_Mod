package net.firedust.sunderedmod.entity.custom;

import com.mojang.blaze3d.shaders.Effect;
import net.firedust.sunderedmod.entity.ai.PitCreatureAttackGoal;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;


public class PitCreatureEntity extends Monster {
    public int size = 2;
    // Bool that synchronizes between server and client
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(PitCreatureEntity.class, EntityDataSerializers.BOOLEAN);

    public PitCreatureEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public final AnimationState idleAnimState = new AnimationState();
    private int idleAnimTimeout = 0;
    public final AnimationState attackAnimState = new AnimationState();
    public int attackAnimTimeout = 0;

    // Override tick to set up animations
    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates(){
        if (this.idleAnimTimeout <= 0){
            this.idleAnimTimeout = this.random.nextInt(40) + 80;
            this.idleAnimState.start(this.tickCount);
        }
        else{
            --this.idleAnimTimeout;
        }

        // When are we attacking?
        if (this.isAttacking() && attackAnimTimeout <=0){
            this.attackAnimTimeout = 25; // Time of animation in ticks
            attackAnimState.start(this.tickCount);
        }
        else{
            --this.attackAnimTimeout;
        }

        if (!this.isAttacking()){
            this.attackAnimState.stop();
        }
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        // Taken from camel
        float f;
        if (this.getPose() == Pose.STANDING) f = Math.min(pPartialTick * 6f, 1f);
        else f = 0f;
        this.walkAnimation.update(f, 0.2f);
    }

    public void setAttacking(boolean attacking){
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking(){
        return this.entityData.get(ATTACKING);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
    }

    @Override
    protected void registerGoals() {
        //this.goalSelector.addGoal(0, new FloatGoal(this)); // Without this the entity sinks
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        //this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(1, new PitCreatureAttackGoal(this, 1.0D, true));

        // Target selector is like "what can I attack"
        // What hit me
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(new Class[0]));
        // Everything alive
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<LivingEntity>(this, LivingEntity.class, 0, true, true, null));

    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        this.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 9999, this.size*2, false, false));
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    public boolean isPushable() {
        return false;
    }

    public boolean removeWhenFarAway(double pDistanceToClosestPlayer) {
        return false;
    }


    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
            .add(Attributes.MOVEMENT_SPEED, 0D)
            .add(Attributes.MAX_HEALTH, 50D)
            .add(Attributes.FOLLOW_RANGE, 16.0)
            .add(Attributes.ATTACK_DAMAGE, 5f)
            .add(Attributes.ATTACK_KNOCKBACK, 0F)
            .add(Attributes.ATTACK_SPEED, 0f)
            .add(Attributes.KNOCKBACK_RESISTANCE, 10f);
    }


    // Set these up
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.HUSK_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.RAVAGER_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.DONKEY_HURT;
    }
}
