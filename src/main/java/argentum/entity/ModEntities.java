package argentum.entity;

import argentum.Argentum;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModEntities {
    public static final EntityType<PelotaEntity> PELOTA = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(Argentum.MOD_ID, "pelota"),
            FabricEntityTypeBuilder
                    .create(SpawnGroup.MISC,
                            // 👇 Lambda con tipos explícitos = no hay lío de genéricos
                            (EntityType<PelotaEntity> type, World world) -> new PelotaEntity(type, world)
                    )
                    .dimensions(EntityDimensions.fixed(0.6f, 0.6f))
                    .trackRangeBlocks(64)
                    .trackedUpdateRate(10)
                    .build()
    );
}
