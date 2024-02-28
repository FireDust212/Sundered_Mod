package net.firedust.sunderedmod.datagen;

import net.firedust.sunderedmod.SunderedMod;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, SunderedMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // blockWithItem(ModBlock.____);    // <-- Sets up a block
    }

    // Makes a custom block as well as the item for it, just by passing in a block registry
    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
