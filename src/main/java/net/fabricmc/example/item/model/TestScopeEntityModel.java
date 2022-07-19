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

public class TestScopeEntityModel extends AttachmentModel {
    private final ModelPart bb_main;
    private final ModelPart cube_r1;
    private final ModelPart cube_r2;
    private final ModelPart cube_r3;
    private final ModelPart cube_r4;
    public static EntityModelLayer layer;
    public TestScopeEntityModel(ModelPart root) {
        super(root);
        this.bb_main = root.getChild("bb_main");
        cube_r1 = this.bb_main.getChild("cube_r1");
        cube_r2 = this.bb_main.getChild("cube_r2");
        cube_r3 = this.bb_main.getChild("cube_r3");
        cube_r4 = this.bb_main.getChild("cube_r4");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 26).cuboid(-2.0F, -1.0F, -3.0F, 4.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-2.7071F, -3.2929F, -2.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(1.7071F, -3.2929F, -2.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-2.0F, -4.0F, -2.0F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(22, 29).cuboid(-0.5F, -2.5F, -1.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -4.0F, -2.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.1213F, -0.4645F, 0.0F, 0.0F, 0.0F, -0.7854F));

        ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -4.0F, -2.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.1213F, -0.4645F, 0.0F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cube_r3 = bb_main.addChild("cube_r3", ModelPartBuilder.create().uv(20, 0).cuboid(2.0F, -2.0F, -3.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-0.8284F, -1.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cube_r4 = bb_main.addChild("cube_r4", ModelPartBuilder.create().uv(20, 0).cuboid(-3.0F, -2.0F, -3.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.8284F, -1.0F, 0.0F, 0.0F, 0.0F, -0.7854F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Environment(EnvType.CLIENT)
    public static void createLayer()
    {
        layer = new EntityModelLayer(new Identifier("fbg", "test_01_scope"), "main");
        EntityModelLayerRegistry.registerModelLayer(layer, TestScopeEntityModel::getTexturedModelData);
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
