package net.fabricmc.example.entity.bullet_hole;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.example.entity.basic.BasicRegistry;
import net.fabricmc.example.entity.basic.BulletEntity;
import net.fabricmc.example.entity.basic.BulletEntityModel;
import net.fabricmc.example.entity.basic.BulletEntityRenderer;
import net.fabricmc.example.item.ItemRegistry;
import net.fabricmc.example.networking.BulletEntitySpawnPacket;
import net.fabricmc.example.networking.BulletHoleEntitySpawnPocket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class BulletHoleRegistry {
    public static EntityType<BulletHoleEntity> HoleType;

    public static void registry()
    {
        HoleType = Registry.register(Registry.ENTITY_TYPE, new Identifier("fbg", "bullet_hole"), FabricEntityTypeBuilder.<BulletHoleEntity>create(SpawnGroup.MISC,
                (entity, world) -> new BulletHoleEntity(world, 72000))
                .dimensions(EntityDimensions.fixed(0.1F, 0.1F)).build());
    }
    @Environment(EnvType.CLIENT)
    public static void clientRegistry()
    {
        ClientPlayNetworking.registerGlobalReceiver(BulletHoleEntitySpawnPocket.ID, BulletHoleEntitySpawnPocket::onPacket);
        EntityRendererRegistry.register(HoleType, BulletHoleRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(new EntityModelLayer(new Identifier("fbg", "bullet_hole"), "main"), BulletHoleModel::getTexturedModelData);
    }
}
