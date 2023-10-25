package me.bigvirusboi.grates.data.recipe;

import me.bigvirusboi.grates.init.BlockInit;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, BlockInit.IRON_GRATE.get())
                .define('I', Tags.Items.INGOTS_IRON).define('B', Blocks.IRON_BARS)
                .pattern(" I ").pattern("IBI").pattern(" I ")
                .unlockedBy("has_result", has(BlockInit.IRON_GRATE.get()))
                .unlockedBy("has_gold_grate", has(BlockInit.GOLD_GRATE.get()))
                .unlockedBy("has_ingot", has(Tags.Items.INGOTS_IRON))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, BlockInit.GOLD_GRATE.get())
                .define('I', Tags.Items.INGOTS_GOLD).define('B', BlockInit.IRON_GRATE.get())
                .pattern(" I ").pattern("IBI").pattern(" I ")
                .unlockedBy("has_result", has(BlockInit.GOLD_GRATE.get()))
                .unlockedBy("has_iron_grate", has(BlockInit.IRON_GRATE.get()))
                .unlockedBy("has_ingot", has(Tags.Items.INGOTS_GOLD))
                .save(consumer);
    }
}