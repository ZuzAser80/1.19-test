package net.fabricmc.example.item.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.example.item.model.general.AttachmentModel;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DefaultMuzzleFlashEntityModel extends AttachmentModel {
    private final ModelPart bb_main;
    private final ModelPart cube_r1;
    private final ModelPart cube_r2;

    public static EntityModelLayer layer;

    public DefaultMuzzleFlashEntityModel(ModelPart root) {
        super(root);
        this.bb_main = root.getChild("bb_main");
        this.cube_r1 = bb_main.getChild("cube_r1");
        this.cube_r2 = bb_main.getChild("cube_r2");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(0, 11).cuboid(-5.0F, -2.0F, -2.0F, 10.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

        ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(24, 20).cuboid(-2.0F, -5.0F, -2.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }
    @Environment(EnvType.CLIENT)
    public static void createLayer()
    {
        layer = new EntityModelLayer(new Identifier("fbg", "def_muzzle_flash"), "main");
        EntityModelLayerRegistry.registerModelLayer(layer, DefaultMuzzleFlashEntityModel::getTexturedModelData);
    }
    public ModelPart getMain()
    {
        return bb_main;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.bb_main.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
