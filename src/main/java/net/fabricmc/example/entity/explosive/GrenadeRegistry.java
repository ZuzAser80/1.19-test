package net.fabricmc.example.entity.explosive;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.example.entity.basic.BulletEntity;
import net.fabricmc.example.entity.basic.BulletEntityModel;
import net.fabricmc.example.entity.basic.BulletEntityRenderer;
import net.fabricmc.example.entity.explosive.model.HEGrenadeModel;
import net.fabricmc.example.item.ItemRegistry;
import net.fabricmc.example.networking.BulletEntitySpawnPacket;
import net.fabricmc.example.networking.GrenadeEntitySpawnPocket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class GrenadeRegistry {
    public static EntityType<GrenadeEntity> GrenadeType;

    public static void registry()
    {
        GrenadeType = registerEntityType(new Identifier("fbg", "grenade"), FabricEntityTypeBuilder.<GrenadeEntity>create(SpawnGroup.MISC,
                (entity, world) -> new GrenadeEntity(world, Items.DIAMOND, net.fabricmc.example.entity.explosive.GrenadeType.SMOKE, 200))
                .dimensions(EntityDimensions.fixed(0.25F, 0.25F)).build());
    }
    @Environment(EnvType.CLIENT)
    public static void clientRegistry()
    {
        ClientPlayNetworking.registerGlobalReceiver(GrenadeEntitySpawnPocket.ID, GrenadeEntitySpawnPocket::onPacket);
        EntityRendererRegistry.register(GrenadeType, GrenadeRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(new EntityModelLayer(new Identifier("fbg", "he_grenade"), "main"), HEGrenadeModel::getTexturedModelData);
    }
    public static <T extends PersistentProjectileEntity> EntityType<T> registerEntityType(Identifier id, EntityType<T> entityType)
    {
        Registry.register(Registry.ENTITY_TYPE, id, entityType);
        return entityType;
    }
}
