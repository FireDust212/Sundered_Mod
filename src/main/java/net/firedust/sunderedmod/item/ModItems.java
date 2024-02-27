package net.firedust.sunderedmod.item;

import net.firedust.sunderedmod.SunderedMod;
import net.firedust.sunderedmod.item.custom.ScannerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
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
            () -> new ScannerItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> PITTOOTH = ITEMS.register("pittooth",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PITFLESH = ITEMS.register("pit_flesh",
            () -> new Item(new Item.Properties().food(ModFoods.PIT_FLESH)));
    public static final RegistryObject<Item> COOKEDPITFLESH = ITEMS.register("cooked_pit_flesh",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_PIT_FLESH)));

    public static final RegistryObject<Item> PITTOOTHSWORD = ITEMS.register("pit_tooth_sword",
            () -> new SwordItem(Tiers.IRON, 3, -1.4F, new Item.Properties()));




    // Register the register
    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }

}
