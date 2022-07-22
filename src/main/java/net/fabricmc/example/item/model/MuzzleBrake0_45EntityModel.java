package net.fabricmc.example.item.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.example.item.model.general.AbstractBarrelModel;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class MuzzleBrake0_45EntityModel extends AbstractBarrelModel {

    private ModelPart muzzlePivot;
    private final ModelPart bb_main;
    private final ModelPart cube_r1;
    private final ModelPart cube_r2;
    private final ModelPart cube_r3;
    private final ModelPart cube_r4;
    private final ModelPart cube_r5;
    private final ModelPart cube_r6;
    private final ModelPart cube_r7;
    private final ModelPart cube_r8;

    public static EntityModelLayer layer;

    public MuzzleBrake0_45EntityModel(ModelPart root) {
        super(root);
        muzzlePivot = root.getChild("muzzle_pivot");
        this.bb_main = root.getChild("bb_main");
        cube_r1 = bb_main.getChild("cube_r1");
        cube_r2 = bb_main.getChild("cube_r2");
        cube_r3 = bb_main.getChild("cube_r3");
        cube_r4 = bb_main.getChild("cube_r4");
        cube_r5 = bb_main.getChild("cube_r5");
        cube_r6 = bb_main.getChild("cube_r6");
        cube_r7 = bb_main.getChild("cube_r7");
        cube_r8 = bb_main.getChild("cube_r8");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData muzzlePivot = modelPartData.addChild("muzzle_pivot", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 23.25F, -4.0F));

        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-0.5F, -1.25F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-1.2071F, -0.5429F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(0.2071F, -0.5429F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-0.5F, 0.1642F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-1.0F, -1.75F, -3.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(0.7071F, -1.0429F, -2.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-1.7071F, -1.0429F, -2.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(0.7071F, -1.0429F, -4.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(0.7071F, -1.0429F, -6.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-1.7071F, -1.0429F, -6.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-1.7071F, -1.0429F, -4.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-1.0F, -1.75F, -6.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-1.0F, 0.6642F, -6.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-1.0F, 0.6642F, -3.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -1.0F, -4.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 1.6642F, -2.0F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, 0.0F, -4.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.25F, -2.0F, 0.0F, 0.0F, -0.7854F));

        ModelPartData cube_r3 = bb_main.addChild("cube_r3", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -3.0F, -4.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(1.1213F, 0.3713F, -2.0F, 0.0F, 0.0F, -0.7854F));

        ModelPartData cube_r4 = bb_main.addChild("cube_r4", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -3.0F, -4.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-0.4142F, 1.0784F, -2.0F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cube_r5 = bb_main.addChild("cube_r5", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, 1.1642F, -1.0F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cube_r6 = bb_main.addChild("cube_r6", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.2071F, 0.4571F, -1.0F, 0.0F, 0.0F, -0.7854F));

        ModelPartData cube_r7 = bb_main.addChild("cube_r7", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.2071F, -0.5429F, -1.0F, 0.0F, 0.0F, -0.7854F));

        ModelPartData cube_r8 = bb_main.addChild("cube_r8", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.2071F, 0.8713F, 0.0F, 0.0F, 0.0F, 0.7854F));
        return TexturedModelData.of(modelData, 16, 16);
    }

    @Environment(EnvType.CLIENT)
    public static void createLayer() {
        layer = new EntityModelLayer(new Identifier("fbg", "muzzle_brake_0.45"), "main");
        EntityModelLayerRegistry.registerModelLayer(layer, MuzzleBrake0_45EntityModel::getTexturedModelData);
    }

    public ModelPart getMuzzlePivot() {
        return muzzlePivot;
    }

    @Override
    public ModelPart getMain()
    {
        return bb_main;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}
