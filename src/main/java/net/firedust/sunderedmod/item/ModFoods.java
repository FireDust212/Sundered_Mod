package net.firedust.sunderedmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties PIT_FLESH = new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).meat()
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 400), 0.5F).build();

    public static final FoodProperties COOKED_PIT_FLESH = new FoodProperties.Builder().nutrition(5).saturationMod(0.8F).meat()
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200), 0.05F).build();
}
