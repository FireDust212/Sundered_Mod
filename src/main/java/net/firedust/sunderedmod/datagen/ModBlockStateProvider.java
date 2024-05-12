package net.firedust.sunderedmod.datagen;

import net.firedust.sunderedmod.SunderedMod;
import net.firedust.sunderedmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, SunderedMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //blockWithItem(ModBlock.____);    // <-- Sets up a block

        ResourceLocation pitSide = new ResourceLocation("sundered_mod","block/pit_block_side");
        ResourceLocation pitTeeth = new ResourceLocation("sundered_mod","block/pit_block_teeth");

        simpleBlockWithItem(ModBlocks.PIT_BLOCK.get(),
                (ModelFile)this.models().cubeColumn("pit_block", pitSide, pitTeeth));

        ResourceLocation pitCoreTop = new ResourceLocation("sundered_mod","block/pit_core_top");

        simpleBlockWithItem(ModBlocks.PIT_CORE_BLOCK.get(),
                (ModelFile)this.models().cubeColumn("pit_core_block", pitSide, pitCoreTop));

        simpleBlockItem(ModBlocks.PIT_TRIGGER.get(),
                (ModelFile) this.models().getExistingFile(
                        new ResourceLocation("sundered_mod", "block/pit_trigger_block")));

//        simpleBlockWithItem(ModBlocks.PIT_TRIGGER.get(),
//                (ModelFile) this.models().cubeBottomTop("pit_trigger_block",
//                        new ResourceLocation("minecraft", "block/grass_block_side"),
//                        new ResourceLocation("minecraft", "block/dirt"),
//                        new ResourceLocation("minecraft", "block/grass_block_top")
//                ));

    }

    // Makes a custom block as well as the item for it, just by passing in a block registry
    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
