package net.firedust.sunderedmod.datagen;

import net.firedust.sunderedmod.SunderedMod;
import net.firedust.sunderedmod.block.ModBlocks;
import net.firedust.sunderedmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, SunderedMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Add new block tags
        this.tag(ModTags.Blocks.PIT_COMPONENTS)
                .add(ModBlocks.PIT_CORE_BLOCK.get())
                .add(ModBlocks.PIT_BLOCK.get());    // Can add multiple blocks with one add -> .add(block1, block2 ...)
                //.addTag(Tags.Blocks.ORES);    <- can add tags to this tag

        this.tag(ModTags.Blocks.PIT_SPREADABLE)
                //.addTag(BlockTags.SCULK_REPLACEABLE)
                .addTag(BlockTags.DIRT)
                .add(Blocks.GLASS);

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.PIT_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.PIT_BLOCK.get());
    }
}
