package net.firedust.sunderedmod.datagen;

import net.firedust.sunderedmod.SunderedMod;
import net.firedust.sunderedmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SunderedMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.COOKEDPITFLESH);
        simpleItem(ModItems.PITFLESH);
        simpleItem(ModItems.PITTOOTH);

        handheldItem(ModItems.PITTOOTHSWORD);
        //simpleItem(ModItems.SCANNER);
    }

    // Custom method
    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),                   // Json file with parent
                new ResourceLocation("item/generated")).texture("layer0",   // In item/generated
                new ResourceLocation(SunderedMod.MOD_ID, "item/" + item.getId().getPath()));    // layer0 is iem/[item name] as texture
    }

    // Custom method
    private ItemModelBuilder handheldItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),                   // Json file with parent
                new ResourceLocation("item/handheld")).texture("layer0",   // In item/handheld
                new ResourceLocation(SunderedMod.MOD_ID, "item/" + item.getId().getPath()));    // layer0 is iem/[item name] as texture
    }
}
