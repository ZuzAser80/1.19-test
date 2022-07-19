package net.fabricmc.example.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.example.AnimationHelper;
import net.fabricmc.example.item.ItemRegistry;
import net.fabricmc.example.item.model.*;
import net.fabricmc.example.item.model.general.AttachmentModel;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Environment(EnvType.CLIENT)
@Mixin(BuiltinModelItemRenderer.class)
public class BuiltinModelItemRendererMixin {

    @Shadow @Final private EntityModelLoader entityModelLoader;
    protected Test01EntityModel test01EntityModel;
    protected TestScopeEntityModel testScopeEntityModel;
    protected TestSilencerEntityModel testSilencerEntityModel;
    protected DefaultMuzzleFlashEntityModel muzzleFlashEntityModel;
    protected CollimatorGreen1_2xEntity collimatorGreen1_2xEntity;

    @Inject(at = @At("HEAD"), method = "reload")
    public void reloadInject(ResourceManager manager, CallbackInfo ci)
    {
        test01EntityModel = new Test01EntityModel(this.entityModelLoader.getModelPart(Test01EntityModel.layer));
        testScopeEntityModel = new TestScopeEntityModel(this.entityModelLoader.getModelPart(TestScopeEntityModel.layer));
        testSilencerEntityModel = new TestSilencerEntityModel(this.entityModelLoader.getModelPart(TestSilencerEntityModel.layer));
        muzzleFlashEntityModel = new DefaultMuzzleFlashEntityModel(this.entityModelLoader.getModelPart(DefaultMuzzleFlashEntityModel.layer));
        collimatorGreen1_2xEntity = new CollimatorGreen1_2xEntity(this.entityModelLoader.getModelPart(CollimatorGreen1_2xEntity.layer));
    }

    @Inject(at = @At("HEAD"), method = "render")
    public void renderInject(ItemStack stack, ModelTransformation.Mode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, CallbackInfo ci)
    {
        if(stack.isOf(ItemRegistry.Collimator_green1_2x))
        {
            VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, this.collimatorGreen1_2xEntity.getLayer( new Identifier("fbg", "textures/entity/collimator_green_1.2x.png")), false, false);
            collimatorGreen1_2xEntity.render(matrices, vertexConsumer, light, overlay, 1, 1, 1, 1);

        }
        if(stack.isOf(ItemRegistry.Test01))
        {
            matrices.multiply(new Quaternion(Vec3f.POSITIVE_Y.getDegreesQuaternion(180)));
            matrices.multiply(new Quaternion(Vec3f.POSITIVE_X.getDegreesQuaternion(180)));
            matrices.translate(0, -1.75, -0.5);
            matrices.scale(0.75f, 0.75f, 0.75f);
            VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, this.test01EntityModel.getLayer( new Identifier("fbg", "textures/entity/test_01.png")), false, false);
            test01EntityModel.render(matrices, vertexConsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
            //make muzzle flash lesser?

            renderScope(stack, vertexConsumers, matrices, light, overlay);
            renderBarrel(stack, vertexConsumers, matrices, light, overlay);


            Random rnd = new Random();
            if(MinecraftClient.getInstance().mouse.wasLeftButtonClicked()) {
                VertexConsumer vertexConsumer2 = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, this.muzzleFlashEntityModel.getLayer(new Identifier("fbg", "textures/entity/default_muzzle_flash.png")), false, false);
                muzzleFlashEntityModel.getMain().roll = rnd.nextInt(0, 180);
                if(stack.getOrCreateNbt().getString("barrel").equals(""))
                {
                    muzzleFlashEntityModel.getMain().setPivot(test01EntityModel.getBarrelPivot().pivotX, test01EntityModel.getBarrelPivot().pivotY, test01EntityModel.getBarrelPivot().pivotZ);
                } else
                {
                    //do diff models per att type
                    AttachmentModel scopeModel = getBarrelModel(stack.getOrCreateNbt().getString("barrel"));
                    muzzleFlashEntityModel.getMain().setPivot(testSilencerEntityModel.getMuzzlePivot().pivotX, testSilencerEntityModel.getMuzzlePivot().pivotY, testSilencerEntityModel.getMuzzlePivot().pivotZ);
                }
                muzzleFlashEntityModel.render(matrices, vertexConsumer2, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }
    public AttachmentModel getBarrelModel(String key)
    {
        AttachmentModel model;
        switch (key) {
            case "item.fbg.collimator_red_1.2x" -> model = testSilencerEntityModel;
            case "item.fbg.collimator_green_1.2x" -> {
                System.out.println("green");
                model = collimatorGreen1_2xEntity;
            }
            default -> model = null;
        }
        return model;
    }
    public void renderScope(ItemStack stack, VertexConsumerProvider vertexConsumers, MatrixStack matrices, int light, int overlay)
    {
        AttachmentModel modelToRender;
        Identifier texture;
        switch (stack.getOrCreateNbt().getString("scope")) {
            case "item.fbg.collimator_red_1.2x" -> {
                modelToRender = testScopeEntityModel;
                texture = new Identifier("fbg", "textures/entity/test_01_scope.png");
            }
            case "item.fbg.collimator_green_1.2x" -> {
                modelToRender = collimatorGreen1_2xEntity;
                texture = new Identifier("fbg", "textures/entity/collimator_green_1.2x.png");
            }
            default -> {
                //System.out.println("default");
                modelToRender = null;
                texture = null;
            }
        }
        if(modelToRender != null) {
            modelToRender.getMain().setPivot(test01EntityModel.getScopePivot().pivotX, test01EntityModel.getScopePivot().pivotY, test01EntityModel.getScopePivot().pivotZ);
            VertexConsumer vertexConsumer1 = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, modelToRender.getLayer(texture), false, false);
            modelToRender.render(matrices, vertexConsumer1, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
    public void renderBarrel(ItemStack stack, VertexConsumerProvider vertexConsumers, MatrixStack matrices, int light, int overlay)
    {
        AttachmentModel modelToRender;
        Identifier texture;
        switch (stack.getOrCreateNbt().getString("barrel")) {
            case "item.fbg.silencer_0.45" -> {
                modelToRender = testSilencerEntityModel;
                texture = new Identifier("fbg", "textures/entity/test_01_silencer.png");
            }
            default -> {
                //System.out.println("default");
                modelToRender = null;
                texture = null;
            }
        }
        if(modelToRender != null) {
            modelToRender.getMain().setPivot(test01EntityModel.getBarrelPivot().pivotX, test01EntityModel.getBarrelPivot().pivotY, test01EntityModel.getBarrelPivot().pivotZ);
            VertexConsumer vertexConsumer1 = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, modelToRender.getLayer(texture), false, false);
            modelToRender.render(matrices, vertexConsumer1, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
