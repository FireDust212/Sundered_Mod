package net.firedust.sunderedmod.datagen;

import net.firedust.sunderedmod.SunderedMod;
import net.firedust.sunderedmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.Iterator;
import java.util.List;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.PITTOOTHSWORD.get())
                .pattern("p")
                .pattern("p")
                .pattern("s")
                .define('p', ModItems.PITTOOTH.get())
                .define('s', Items.STICK)
                .unlockedBy(getHasName(ModItems.PITTOOTH.get()), has(ModItems.PITTOOTH.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SCANNER.get())
                .pattern("dir")
                .pattern("ipi")
                .pattern("ifi")
                .define('d', Items.DIAMOND)
                .define('i', Items.IRON_NUGGET)
                .define('r', Items.REDSTONE)
                .define('p', ModItems.PITTOOTH.get())
                .define('f', ModItems.PITFLESH.get())
                .unlockedBy(getHasName(ModItems.PITTOOTH.get()), has(ModItems.PITTOOTH.get()))
                .save(recipeOutput);

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ModItems.PITTOOTH.get()),RecipeCategory.FOOD, ModItems.COOKEDPITFLESH.get(), 0.35F, 600);
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ModItems.PITTOOTH.get()),RecipeCategory.FOOD, ModItems.COOKEDPITFLESH.get(), 0.35F, 100);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.PITTOOTH.get()),RecipeCategory.FOOD, ModItems.COOKEDPITFLESH.get(), 0.35F, 200);
    }


    // These methods don't provide a way to customize the mod name, so we add them
    protected static void oreSmelting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    private static void oreCooking(RecipeOutput pRecipeOutput, RecipeSerializer<? extends AbstractCookingRecipe> pSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pSuffix) {
        Iterator var9 = pIngredients.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult,
                    pExperience, pCookingTime, pSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, SunderedMod.MOD_ID + ":" + (pResult) + pSuffix + "_" + getItemName(itemlike));        }

    }
}
