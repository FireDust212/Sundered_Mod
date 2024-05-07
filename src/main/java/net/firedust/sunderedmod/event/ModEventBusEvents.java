package net.firedust.sunderedmod.event;

import net.firedust.sunderedmod.SunderedMod;
import net.firedust.sunderedmod.entity.ModEntities;
import net.firedust.sunderedmod.entity.custom.PitCreatureEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// These events are not Forge events, they are mod events
// Middle mouse click on an event to determine its type
@Mod.EventBusSubscriber(modid = SunderedMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.PIT_CREATURE.get(), PitCreatureEntity.createAttributes().build());
    }
}
