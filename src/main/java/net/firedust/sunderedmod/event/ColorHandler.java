package net.firedust.sunderedmod.event;

import net.firedust.sunderedmod.SunderedMod;
import net.firedust.sunderedmod.block.ModBlocks;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SunderedMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ColorHandler {
    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((p_276237_, p_276238_, p_276239_, p_276240_) -> {
            return p_276238_ != null && p_276239_ != null ? BiomeColors.getAverageGrassColor(p_276238_, p_276239_) : GrassColor.getDefaultColor();
        }, ModBlocks.PIT_TRIGGER.get(), ModBlocks.PIT_TRAP.get());
    }
    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event){
        event.register((p_92687_, p_92688_) -> {
            BlockState blockstate = ((BlockItem)p_92687_.getItem()).getBlock().defaultBlockState();
            return event.getBlockColors().getColor(blockstate, (BlockAndTintGetter)null, (BlockPos)null, p_92688_);
        }, ModBlocks.PIT_TRIGGER.get(), ModBlocks.PIT_TRAP.get());
    }
}
