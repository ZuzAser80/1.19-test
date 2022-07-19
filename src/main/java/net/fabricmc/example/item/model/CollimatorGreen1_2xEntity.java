package net.fabricmc.example.item.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.example.item.model.general.AbstractBarrelModel;
import net.fabricmc.example.item.model.general.AttachmentModel;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class CollimatorGreen1_2xEntity extends AbstractBarrelModel {

    private final ModelPart bb_main;
    private final ModelPart cube_r1;
    private final ModelPart cube_r2;
    private final ModelPart cube_r3;
    private final ModelPart cube_r4;
    private final ModelPart cube_r5;
    private final ModelPart cube_r6;
    private final ModelPart cube_r7;
    private final ModelPart cube_r8;
    private final ModelPart cube_r9;
    private final ModelPart cube_r10;
    private final ModelPart cube_r11;
    private final ModelPart cube_r12;
    public static EntityModelLayer layer;

    public CollimatorGreen1_2xEntity(ModelPart root) {
        super(root);
        this.bb_main = root.getChild("bb_main");
        cube_r1 = bb_main.getChild("cube_r1");
        cube_r2 = bb_main.getChild("cube_r2");
        cube_r3 = bb_main.getChild("cube_r3");
        cube_r4 = bb_main.getChild("cube_r4");
        cube_r5 = bb_main.getChild("cube_r5");
        cube_r6 = bb_main.getChild("cube_r6");
        cube_r7 = bb_main.getChild("cube_r7");
        cube_r8 = bb_main.getChild("cube_r8");
        cube_r9 = bb_main.getChild("cube_r9");
        cube_r10 = bb_main.getChild("cube_r10");
        cube_r11 = bb_main.getChild("cube_r11");
        cube_r12 = bb_main.getChild("cube_r12");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-2.5F, -2.0F, -2.25F, 5.0F, 2.0F, 5.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-2.5273F, -3.9074F, -1.6486F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(1.5F, -3.9074F, -1.6486F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-0.5137F, -5.9211F, -1.6486F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-2.5F, -1.4929F, 3.7071F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-2.5F, -2.2F, 2.0F, 5.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-2.5F, -0.7858F, 2.0F, 5.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-2.5F, -1.5F, 2.0F, 5.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(4, 11).cuboid(-2.5F, -6.0F, 0.0F, 5.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -5.0F, 0.0F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.5F, 2.3355F, 0.4645F, -0.7854F, 0.0F, 0.0F));

        ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -1.0F, 4.0F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.5F, 2.0426F, 1.1716F, 0.7854F, 0.0F, 0.0F));

        ModelPartData cube_r3 = bb_main.addChild("cube_r3", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -1.0F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0932F, -1.8429F, -1.6486F, 0.0F, 0.0F, 1.1781F));

        ModelPartData cube_r4 = bb_main.addChild("cube_r4", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -6.0F, -2.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(5.2642F, -1.7772F, 0.3514F, 0.0F, 0.0F, -1.1781F));

        ModelPartData cube_r5 = bb_main.addChild("cube_r5", ModelPartBuilder.create().uv(0, 0).cuboid(-5.9134F, 0.9191F, 1.6014F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(4.2346F, -9.6626F, -3.25F, 0.0F, 0.0F, -0.7854F));

        ModelPartData cube_r6 = bb_main.addChild("cube_r6", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -4.5F, -1.5F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-3.9124F, -1.6493F, -0.1486F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cube_r7 = bb_main.addChild("cube_r7", ModelPartBuilder.create().uv(0, 0).cuboid(-2.5F, -4.75F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(5.3209F, -1.0169F, -1.6486F, 0.0F, 0.0F, -0.3927F));

        ModelPartData cube_r8 = bb_main.addChild("cube_r8", ModelPartBuilder.create().uv(0, 0).cuboid(-2.5F, -4.75F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.6527F, 0.5138F, -1.6486F, 0.0F, 0.0F, 0.3927F));

        ModelPartData cube_r9 = bb_main.addChild("cube_r9", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, -3.75F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -0.0303F, 1.5253F, 0.3054F, 0.0F, 0.0F));

        ModelPartData cube_r10 = bb_main.addChild("cube_r10", ModelPartBuilder.create().uv(0, 0).cuboid(-2.5F, -4.0F, -2.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 0.5088F, -0.944F, -0.3054F, 0.0F, 0.0F));

        ModelPartData cube_r11 = bb_main.addChild("cube_r11", ModelPartBuilder.create().uv(0, 0).cuboid(-2.5F, -4.0F, -2.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.0273F, 0.5088F, -0.944F, -0.3054F, 0.0F, 0.0F));

        ModelPartData cube_r12 = bb_main.addChild("cube_r12", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, -3.75F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0273F, -0.0303F, 1.5253F, 0.3054F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 16, 16);
    }
    @Environment(EnvType.CLIENT)
    public static void createLayer()
    {
        layer = new EntityModelLayer(new Identifier("fbg", "collimator_green_1.2x"), "main");
        EntityModelLayerRegistry.registerModelLayer(layer, CollimatorGreen1_2xEntity::getTexturedModelData);
    }
    public ModelPart getMain()
    {
        return bb_main;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}
