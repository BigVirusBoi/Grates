package me.bigvirusboi.grates.data;

import me.bigvirusboi.grates.Grates;
import me.bigvirusboi.grates.data.client.ModBlockStateProvider;
import me.bigvirusboi.grates.data.client.ModItemModelProvider;
import me.bigvirusboi.grates.data.loot.ModLootTableProvider;
import me.bigvirusboi.grates.data.recipe.ModRecipeProvider;
import me.bigvirusboi.grates.data.tag.ModBlockTagsProvider;
import me.bigvirusboi.grates.data.tag.ModItemTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Grates.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent e) {
        DataGenerator generator = e.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper helper = e.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = e.getLookupProvider();

        generator.addProvider(e.includeClient(), new ModBlockStateProvider(packOutput, helper));
        generator.addProvider(e.includeClient(), new ModItemModelProvider(packOutput, helper));

        TagsProvider<Block> tagsProvider = generator.addProvider(e.includeServer(), new ModBlockTagsProvider(packOutput, lookupProvider, helper));
        generator.addProvider(e.includeServer(), new ModItemTagsProvider(packOutput, lookupProvider, tagsProvider.contentsGetter(), helper));

        generator.addProvider(e.includeServer(), new ModRecipeProvider(packOutput));
        generator.addProvider(e.includeServer(), new ModLootTableProvider(packOutput));
    }
}
