package net.fabricmc.example.entity.basic;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.example.item.ItemRegistry;
import net.fabricmc.example.networking.BulletEntitySpawnPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BasicRegistry {
    public static EntityType<BulletEntity> PistolBullet;

    public static void registry()
    {
        PistolBullet = registerEntityType(new Identifier("fbg", "pistol_bullet"), FabricEntityTypeBuilder.<BulletEntity>create(SpawnGroup.MISC,
                (entity, world) -> new BulletEntity(world, ItemRegistry.USP, null))
                .dimensions(EntityDimensions.fixed(0.25F, 0.25F)).build());
    }
    @Environment(EnvType.CLIENT)
    public static void clientRegistry()
    {
        ClientPlayNetworking.registerGlobalReceiver(BulletEntitySpawnPacket.ID, BulletEntitySpawnPacket::onPacket);
        EntityRendererRegistry.register(PistolBullet, BulletEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(new EntityModelLayer(new Identifier("fbg", "bullet"), "main"), BulletEntityModel::getTexturedModelData);
    }
    public static <T extends PersistentProjectileEntity> EntityType<T> registerEntityType(Identifier id, EntityType<T> entityType)
    {
        Registry.register(Registry.ENTITY_TYPE, id, entityType);
        return entityType;
    }
}
