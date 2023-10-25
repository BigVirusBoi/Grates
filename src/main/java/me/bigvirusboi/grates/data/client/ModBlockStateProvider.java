package me.bigvirusboi.grates.data.client;

import me.bigvirusboi.grates.Grates;
import me.bigvirusboi.grates.block.GrateBlock;
import me.bigvirusboi.grates.init.BlockInit;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    private final ModelFile GRATE = models().getExistingFile(Grates.getResource("block/template/grate"));

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, Grates.MOD_ID, fileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        grateBlock(BlockInit.IRON_GRATE);
        grateBlock(BlockInit.GOLD_GRATE);
    }

    private void grateBlock(RegistryObject<GrateBlock> grate) {
        String name = grate.getId().getPath();
        VariantBlockStateBuilder builder = getVariantBuilder(grate.get());
        BlockModelBuilder model = models().getBuilder(name).parent(GRATE)
                .texture("grate", Grates.getResource("block/" + grate.get()));

        builder.partialState().addModels(new ConfiguredModel(model));
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
}