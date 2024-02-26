package net.firedust.sunderedmod.item;

import net.firedust.sunderedmod.SunderedMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    // Long list of items, to be registered when forge loads the items
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SunderedMod.MOD_ID);

    // Add an item
    public static final RegistryObject<Item> SCANNER = ITEMS.register("scanner",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PITTOOTH = ITEMS.register("pittooth",
            () -> new Item(new Item.Properties()));


    // Register the register
    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }

}
