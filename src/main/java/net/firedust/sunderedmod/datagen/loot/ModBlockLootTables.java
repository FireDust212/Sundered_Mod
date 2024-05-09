package net.firedust.sunderedmod.datagen.loot;

import net.firedust.sunderedmod.block.ModBlocks;
import net.firedust.sunderedmod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
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

        //this.add(ModBlocks.PIT_BLOCK.get(), block -> createCopperLikeOreDrops(ModBlocks.PIT_BLOCK.get(), ModItems.PITTOOTH.get(), 0.0F, 3.0F));
//        this.add(ModBlocks.PIT_BLOCK.get(), block -> createCopperLikeOreDrops(ModBlocks.PIT_BLOCK.get(), ModItems.PITFLESH.get(), 1.0F, 2.0F));

        this.add(ModBlocks.PIT_BLOCK.get(), block -> createBlockWithTwoDrops(ModBlocks.PIT_BLOCK.get(), ModItems.PITTOOTH.get(), ModItems.PITFLESH.get(), 0.0F, 3.0F, 1.0F, 2.0F));
        this.add(ModBlocks.PIT_CORE_BLOCK.get(), block -> createBlockWithTwoDrops(ModBlocks.PIT_CORE_BLOCK.get(), ModItems.PITTOOTH.get(), ModItems.PITFLESH.get(), 0.0F, 3.0F, 1.0F, 2.0F));
    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item, float min, float max) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    protected LootTable.Builder createBlockWithTwoDrops(Block pBlock, Item pItem1, Item pItem2, float min1, float max1, float min2, float max2) {
        LootPoolEntryContainer.Builder<?> builder1 = (
                LootItem.lootTableItem(pBlock)                              // Block as item
                        .when(BlockLootSubProvider.HAS_SILK_TOUCH)          // When silk touch
                        .otherwise((applyExplosionDecay(pBlock,    // Otherwise
                                LootItem.lootTableItem(pItem1)              // Item 1
                                        .apply(SetItemCountFunction
                                                .setCount(UniformGenerator.between(min1, max1)))     // In this range
                                        .apply(ApplyBonusCount
                                                .addOreBonusCount(Enchantments.BLOCK_FORTUNE))))));  // Affected by fortune
        LootPoolEntryContainer.Builder<?> builder2 = (
                LootItem.lootTableItem(pBlock)                              // Block as item
                        .when(BlockLootSubProvider.HAS_SILK_TOUCH)          // When silk touch
                        .otherwise((applyExplosionDecay(pBlock,    // Otherwise
                                LootItem.lootTableItem(pItem2)              // Item 2
                                        .apply(SetItemCountFunction
                                                .setCount(UniformGenerator.between(min2, max2)))     // In this range
                                        .apply(ApplyBonusCount
                                                .addOreBonusCount(Enchantments.BLOCK_FORTUNE))))));  // Affected by fortune

        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(builder1))
                .withPool(LootPool.lootPool()
                        .add(builder2));

//        return (createSilkTouchDispatchTable(pBlock,
//                applyExplosionDecay(pBlock,
//                        LootItem.lootTableItem(pItem1)
//                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min1, max1)))
//                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))),
//                createSilkTouchDispatchTable(pBlock,
//                        applyExplosionDecay(pBlock,
//                                LootItem.lootTableItem(pItem2)
//                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(min2, max2)))
//                                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

//    protected LootTable.Builder createDoublePlantWithSeedDrops(Block pBlock, Block pSheared) {
//        LootPoolEntryContainer.Builder<?> builder = ((LootPoolSingletonContainer.Builder)LootItem
//                .lootTableItem(pSheared)
//                .apply(SetItemCountFunction
//                        .setCount(ConstantValue.exactly(2.0F)))
//                .when(BlockLootSubProvider.HAS_SHEARS))
//                .otherwise(((LootPoolSingletonContainer.Builder)this.applyExplosionCondition(pBlock,
//                        LootItem.lootTableItem(Items.WHEAT_SEEDS)))
//                        .when(LootItemRandomChanceCondition.randomChance(0.125F)));
//        return LootTable.lootTable()
//                .withPool(LootPool.lootPool()
//                        .add(builder)
//                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pBlock)
//                                .setProperties(StatePropertiesPredicate.Builder.properties()
//                                        .hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))
//                        .when(LocationCheck.checkLocation(
//                                net.minecraft.advancements.critereon.LocationPredicate.Builder.location()
//                                        .setBlock(net.minecraft.advancements.critereon.BlockPredicate.Builder.block()
//                                                .of(new Block[]{pBlock})
//                                                .setProperties(StatePropertiesPredicate.Builder.properties()
//                                                        .hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER))),
//                                new BlockPos(0, 1, 0))))
//                .withPool(LootPool.lootPool()
//                        .add(builder)
//                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pBlock)
//                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER))).when(LocationCheck.checkLocation(net.minecraft.advancements.critereon.LocationPredicate.Builder.location().setBlock(net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(new Block[]{pBlock}).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))), new BlockPos(0, -1, 0))));
//    }


    @Override
    public Iterable<Block> getKnownBlocks() {
        // Return all blocks in BLOCKS that don't have noLootTable() called on them
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
