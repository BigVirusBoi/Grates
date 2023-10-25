package me.bigvirusboi.grates.data.client;

import me.bigvirusboi.grates.Grates;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    private final ModelFile ITEM_GENERATED = getExistingFile(mcLoc("item/generated"));

    public ModItemModelProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, Grates.MOD_ID, fileHelper);
    }

    @Override
    protected void registerModels() {
        buildFromParent("iron_grate");
        buildFromParent("gold_grate");
    }

    private void buildItem(String name) {
        buildItem(ITEM_GENERATED, name);
    }

    private void buildItem(ModelFile model, String name) {
        getBuilder(name).parent(model).texture("layer0", "item/" + name);
    }

    private void buildFromParent(String name) {
        withExistingParent(name, modLoc("block/" + name));
    }
}