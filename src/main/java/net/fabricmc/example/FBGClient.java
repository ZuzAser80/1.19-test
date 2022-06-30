package net.fabricmc.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.example.armor.model.kevlar.KevlarChestplateModel;
import net.fabricmc.example.armor.model.kevlar.KevlarHelmetModel;
import net.fabricmc.example.armor.model.specnaz.SpecnazChestplateModel;
import net.fabricmc.example.armor.model.specnaz.SpecnazHelmetModel;
import net.fabricmc.example.entity.basic.BasicRegistry;
import net.fabricmc.example.entity.explosive.GrenadeRegistry;
import net.fabricmc.example.item.ItemRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class FBGClient implements ClientModInitializer {

    //DO NOT TOUCH
    //IMPORTANT SHIT
    private BipedEntityModel<LivingEntity> armorModel;

    @Override
    public void onInitializeClient() {

        BasicRegistry.clientRegistry();
        GrenadeRegistry.clientRegistry();

        renderArmor(ItemRegistry.KevlarChest, EquipmentSlot.CHEST, new Identifier("fbg", "textures/entity/armor/kevlar_chest_entity.png"), "chest", new Identifier("fbg", "kevlar_chest_layer"), "kevlar_chest_render_layer", KevlarChestplateModel::getTexturedModelData, false);
        renderArmor(ItemRegistry.KevlarHelm, EquipmentSlot.HEAD, new Identifier("fbg", "textures/entity/armor/kevlar_helmet_entity.png"), "head", new Identifier("fbg", "kevlar_helmet_layer"), "kevlar_helmet_render_layer", KevlarHelmetModel::getTexturedModelData, false);
        renderArmor(ItemRegistry.SpecnazChest, EquipmentSlot.CHEST, new Identifier("fbg", "textures/entity/armor/specnaz_chest_entity.png"), "chest", new Identifier("fbg", "specnaz_chest_layer"), "specnaz_chest_render_layer", SpecnazChestplateModel::getTexturedModelData, true);
        renderArmor(ItemRegistry.SpecnazHelm, EquipmentSlot.HEAD, new Identifier("fbg", "textures/entity/armor/specnaz_helmet_entity.png"), "head", new Identifier("fbg", "specnaz_helmet_layer"), "specnaz_helmet_render_layer", SpecnazHelmetModel::getTexturedModelData, false);
    }
    public void renderArmor(Item item, EquipmentSlot equipmentSlot, Identifier texture, String slotName, Identifier layerId, String layerName, EntityModelLayerRegistry.TexturedModelDataProvider provider, boolean hands) {
        EntityModelLayer layer = new EntityModelLayer(layerId, layerName);
        EntityModelLayerRegistry.registerModelLayer(layer, provider);
        ArmorRenderer.register((matrices, vertexConsumers, stack, entity, slot, light, model) -> {
            armorModel = new BipedEntityModel<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(layer));
            model.setAttributes(armorModel);
            armorModel.setVisible(slot == equipmentSlot);
            ModelPart part = getPart(armorModel, slotName);
            if(!entity.isSneaking()) {
                armorModel.body.pivotY += 13.5f;
            } else
            {
                armorModel.body.setPivot(0, 15f, 5f);
            }
            //if(hands)
            //{
            //    armorModel.rightArm.visible = slot == equipmentSlot;
            //    armorModel.leftArm.visible = slot == equipmentSlot;
            //}
            //part.visible = slot == equipmentSlot;
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, armorModel, texture);
        }, item);
    }
    public ModelPart getPart(BipedEntityModel<LivingEntity> model, String slotName)
    {
        return switch (slotName) {
            case "head" -> model.head;
            case "chest" -> model.body;
            default -> null;
        };
    }
}
