package net.firedust.sunderedmod.entity;

import net.firedust.sunderedmod.SunderedMod;
import net.firedust.sunderedmod.entity.custom.PitCreatureEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SunderedMod.MOD_ID);

        public static final RegistryObject<EntityType<PitCreatureEntity>> PIT_CREATURE =
                ENTITY_TYPES.register("pit_creature", () -> EntityType.Builder.of(PitCreatureEntity::new, MobCategory.MONSTER)
                        .sized(1f, 1.3f).build("pit_creature"));

        public static void register(IEventBus bus){
            ENTITY_TYPES.register(bus);
        }
    }
