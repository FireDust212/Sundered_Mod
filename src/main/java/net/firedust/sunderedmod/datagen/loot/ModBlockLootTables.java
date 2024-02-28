package net.firedust.sunderedmod.datagen.loot;

import net.firedust.sunderedmod.block.ModBlocks;
import net.firedust.sunderedmod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        // this.dropSelf(ModBlocks.PIT_BLOCK.get());        // To have the block drop itself

        // this.add(ModBlocks.PIT_BLOCK.get(), block -> createCopperOreDrops());    // To have it drop like copper ore

        this.add(ModBlocks.PIT_BLOCK.get(), block -> createCopperLikeOreDrops(ModBlocks.PIT_BLOCK.get(), ModItems.PITTOOTH.get(), 0.0F, 3.0F));
        this.add(ModBlocks.PIT_BLOCK.get(), block -> createCopperLikeOreDrops(ModBlocks.PIT_BLOCK.get(), ModItems.PITFLESH.get(), 1.0F, 2.0F));

    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item, float min, float max) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    public Iterable<Block> getKnownBlocks() {
        // Return all blocks in BLOCKS that don't have noLootTable() called on them
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
