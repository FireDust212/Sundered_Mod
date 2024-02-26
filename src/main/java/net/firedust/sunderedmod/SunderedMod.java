package net.firedust.sunderedmod;

import com.mojang.logging.LogUtils;
import net.firedust.sunderedmod.block.ModBlocks;
import net.firedust.sunderedmod.item.ModCreativeModeTabs;
import net.firedust.sunderedmod.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SunderedMod.MOD_ID)
public class SunderedMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "sundered_mod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public SunderedMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Set up the custom creative mode tabs
        ModCreativeModeTabs.register(modEventBus);

        // Setup items
        ModItems.register(modEventBus);

        // Setup Blocks
        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the items to a vanilla tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
//        // Find the right tab
//        if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
//            // Add the item
//            event.accept(ModItems.SCANNER);
//        }
//        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
//            // Add the item
//            event.accept(ModItems.PITTOOTH);
//        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
