package net.firedust.sunderedmod.event;

import net.firedust.sunderedmod.SunderedMod;
import net.firedust.sunderedmod.entity.client.ModModelLayers;
import net.firedust.sunderedmod.entity.client.PitCreature;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// These events are not Forge events, they are mod events
// Middle mouse click on an event to determine its type
// Implements IModBusEvent means it goes in this file
@Mod.EventBusSubscriber(modid = SunderedMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayers.PIT_CREATURE_LAYER, PitCreature::createBodyLayer);
    }
}
