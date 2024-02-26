package net.firedust.sunderedmod.block;

import net.firedust.sunderedmod.SunderedMod;
import net.firedust.sunderedmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, SunderedMod.MOD_ID);

    // Actually create a block
    public static final RegistryObject<Block> PIT_BLOCK = registerBlock("pit_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.POWDER_SNOW)));
    // Duplicate the above two lines to create a new block (and teh relevant json files and such

    // Helper methods

    // Returns a block from a supplier
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        // Make the block
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        // Make the block's item
        registerBlockItem(name, toReturn);
        // Return the block
        return toReturn;
    }

    // Function that takes a block and makes an item <- T must be a block
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        //     New item               name = name      from the passed block       with these properties
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    // Register the register
    public static void register(IEventBus bus){
        BLOCKS.register(bus);
    }
}
