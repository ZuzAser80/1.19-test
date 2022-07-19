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

public class TestSilencerEntityModel extends AbstractBarrelModel {

    private final ModelPart muzzle_Pivot;
    private final ModelPart bb_main;
    private final ModelPart cube_r1;
    private final ModelPart cube_r2;
    private final ModelPart cube_r3;
    private final ModelPart cube_r4;

    public static EntityModelLayer layer;

    public TestSilencerEntityModel(ModelPart root) {
        super(root);
        this.muzzle_Pivot = root.getChild("muzzle_Pivot");
        this.bb_main = root.getChild("bb_main");
        this.cube_r1 = bb_main.getChild("cube_r1");
        this.cube_r2 = bb_main.getChild("cube_r2");
        this.cube_r3 = bb_main.getChild("cube_r3");
        this.cube_r4 = bb_main.getChild("cube_r4");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData muzzle_Pivot = modelPartData.addChild("muzzle_Pivot", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, -8.0F));

        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-1.25F, -1.0F, -8.0F, 1.0F, 2.0F, 8.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(0.1642F, -1.0F, -8.0F, 1.0F, 2.0F, 8.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-0.5429F, 0.7071F, -8.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-0.5429F, -1.7071F, -8.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -8.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-1.25F, 2.4142F, 0.0F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -3.0F, -8.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-0.9571F, 1.1213F, 0.0F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cube_r3 = bb_main.addChild("cube_r3", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, 2.0F, -8.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-2.3713F, 0.2929F, 0.0F, 0.0F, 0.0F, -0.7854F));

        ModelPartData cube_r4 = bb_main.addChild("cube_r4", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -4.0F, -8.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.8713F, 2.5355F, 0.0F, 0.0F, 0.0F, -0.7854F));
        return TexturedModelData.of(modelData, 32, 32);
    }
    @Environment(EnvType.CLIENT)
    public static void createLayer()
    {
        layer = new EntityModelLayer(new Identifier("fbg", "test_01_silencer"), "main");
        EntityModelLayerRegistry.registerModelLayer(layer, TestSilencerEntityModel::getTexturedModelData);
    }
    public ModelPart getMain()
    {
        return bb_main;
    }

    public ModelPart getMuzzlePivot()
    {
        return muzzle_Pivot;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.bb_main.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
