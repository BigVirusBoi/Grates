package me.bigvirusboi.grates.data.loot;

import com.google.common.collect.ImmutableList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.packs.VanillaLootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootDataId;
import net.minecraft.world.level.storage.loot.LootDataType;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ModLootTableProvider extends LootTableProvider {
    public ModLootTableProvider(PackOutput packOutput) {
        super(packOutput, Collections.emptySet(), VanillaLootTableProvider.create(packOutput).getTables());
    }

    @Override
    public @NotNull List<SubProviderEntry> getTables() {
        return ImmutableList.of(
                new SubProviderEntry(ModBlockLoot::new, LootContextParamSets.BLOCK)
        );
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, @NotNull ValidationContext context) {
        map.forEach((name, table) ->
                table.validate(context.setParams(table.getParamSet()).enterElement("{" + name + "}", new LootDataId<>(LootDataType.TABLE, name))));
    }
}