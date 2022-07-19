// Made with Blockbench 4.2.5
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class custom_model extends EntityModel<Entity> {
	private final ModelPart muzzle_Pivot;
	private final ModelPart bb_main;
	private final ModelPart cube_r1;
	private final ModelPart cube_r2;
	private final ModelPart cube_r3;
	private final ModelPart cube_r4;
	public custom_model(ModelPart root) {
		this.muzzle_Pivot = root.getChild("muzzle_Pivot");
		this.bb_main = root.getChild("bb_main");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData muzzle_Pivot = modelPartData.addChild("muzzle_Pivot", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 22.0F, -4.0F));

		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-1.25F, -3.0F, -4.0F, 1.0F, 2.0F, 8.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.1642F, -3.0F, -4.0F, 1.0F, 2.0F, 8.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.5429F, -1.2929F, -4.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.5429F, -3.7071F, -4.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -8.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-1.25F, 0.4142F, 4.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -3.0F, -8.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-0.9571F, -0.8787F, 4.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData cube_r3 = bb_main.addChild("cube_r3", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, 2.0F, -8.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-2.3713F, -1.7071F, 4.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData cube_r4 = bb_main.addChild("cube_r4", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -4.0F, -8.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.8713F, 0.5355F, 4.0F, 0.0F, 0.0F, -0.7854F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		muzzle_Pivot.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}