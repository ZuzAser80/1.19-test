package net.fabricmc.example.item.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class Test01EntityModel extends Model {
    private final ModelPart bb_main;
    private final ModelPart scopePivot;
    private final ModelPart barrelPivot;
    private final ModelPart underBarrelPivot;
    private final ModelPart cube_r1;
    private final ModelPart cube_r2;
    public static EntityModelLayer layer;
    public Test01EntityModel(ModelPart root) {
        super(RenderLayer::getEntitySolid);
        this.bb_main = root.getChild("bb_main");
        this.barrelPivot = root.getChild("barrelPivot");
        this.underBarrelPivot = root.getChild("underBarrelPivot");
        this.scopePivot = root.getChild("scopePivot");
        this.cube_r1 = bb_main.getChild("cube_r1");
        this.cube_r2 = bb_main.getChild("cube_r2");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(2, 17).cuboid(-9.5F, -9.5F, 6.0F, 3.0F, 3.0F, 12.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-9.9F, -8.265F, 9.3F, 3.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData scopePivot = modelPartData.addChild("scopePivot", ModelPartBuilder.create(), ModelTransform.pivot(-8.0F, 14.5F, 17.0F));

        ModelPartData barrelPivot = modelPartData.addChild("barrelPivot", ModelPartBuilder.create(), ModelTransform.pivot(-8.0F, 16.0F, 6.0F));

        ModelPartData underBarrelPivot = modelPartData.addChild("underBarrelPivot", ModelPartBuilder.create(), ModelTransform.pivot(-8.0F, 17.5F, 8.0F));

        ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(0, 20).cuboid(-1.55F, -0.1F, -0.5F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, -6.0F, 15.0F, 0.3927F, 0.0F, 0.0F));

        ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(0, 14).cuboid(-2.0F, -1.7F, -2.1F, 4.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, -7.5F, 16.5F, 0.3927F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }
    @Environment(EnvType.CLIENT)
    public static void createLayer()
    {
        layer = new EntityModelLayer(new Identifier("fbg", "test_01"), "main");
        EntityModelLayerRegistry.registerModelLayer(layer, Test01EntityModel::getTexturedModelData);
    }

    public ModelPart getScopePivot()
    {
        return this.scopePivot;
    }
    public ModelPart getBarrelPivot(){return this.barrelPivot;}

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        //scopePivot.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        this.bb_main.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
