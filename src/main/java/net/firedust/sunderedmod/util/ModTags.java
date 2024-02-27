package net.firedust.sunderedmod.util;

import net.firedust.sunderedmod.SunderedMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    // Block tags
    public static class Blocks{
        // Key to use in code
        public static final TagKey<Block> PIT_COMPONENTS = tag("pit_components");

        // Helper to create tag
        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(SunderedMod.MOD_ID, name));
        }
    }

    // Item tags
    public static class Items{

        // Helper to create tag
        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(SunderedMod.MOD_ID, name));
        }
    }
}
