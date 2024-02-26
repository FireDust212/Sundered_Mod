package net.firedust.sunderedmod.item;

import net.firedust.sunderedmod.SunderedMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    // Register for the creative tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SunderedMod.MOD_ID);

    // A tab
    public static final RegistryObject<CreativeModeTab> SUNDERED_TAB = CREATIVE_MODE_TABS.register("sundered_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.PITTOOTH.get()))                     // Icon for tab
                    .title(Component.translatable("creativetab.sundered_tab"))        // Name for tab
                    .displayItems((itemDisplayParameters, output) -> {                      // Items to display
                        output.accept(ModItems.PITTOOTH.get());                             // Order of items is order in tab
                        output.accept(ModItems.SCANNER.get());
                        // Vanilla items can be added here
                    })
                    .build());                                                              // Build

    // Register the creative tabs
    public static void register(IEventBus bus){
        CREATIVE_MODE_TABS.register(bus);
    }
}
