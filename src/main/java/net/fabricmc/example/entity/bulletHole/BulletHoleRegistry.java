package net.fabricmc.example.entity.bulletHole;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.example.networking.BulletHoleEntitySpawnPocket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BulletHoleRegistry {
    public static EntityType<BulletHoleEntity> bulletHoleEntityType;

    public static void registry()
    {
        bulletHoleEntityType = registerEntityType(new Identifier("fbg", "bullet_hole"), FabricEntityTypeBuilder.<BulletHoleEntity>create(SpawnGroup.MISC,
                BulletHoleEntity::new)
                .dimensions(EntityDimensions.fixed(0.25F, 0.25F)).build());
    }
    @Environment(EnvType.CLIENT)
    public static void clientRegistry()
    {
        ClientPlayNetworking.registerGlobalReceiver(BulletHoleEntitySpawnPocket.ID, BulletHoleEntitySpawnPocket::onPacket);
        EntityRendererRegistry.register(bulletHoleEntityType, BulletHoleRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(new EntityModelLayer(new Identifier("fbg", "bullet_hole"), "main"), BulletHoleEntityModel::getTexturedModelData);
    }
    public static <T extends PersistentProjectileEntity> EntityType<T> registerEntityType(Identifier id, EntityType<T> entityType)
    {
        Registry.register(Registry.ENTITY_TYPE, id, entityType);
        return entityType;
    }
}
