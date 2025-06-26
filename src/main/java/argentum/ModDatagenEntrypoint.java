package argentum;

import argentum.datagen.ModLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.minecraft.registry.RegistryBuilder;

public class ModDatagenEntrypoint implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        Pack pack = generator.createPack();
        pack.addProvider(ModLootTableProvider::new); // ← REGISTRA tus tablas de loot
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        // vacía por ahora
    }
}
