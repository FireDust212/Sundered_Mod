package net.firedust.sunderedmod.entity.client;
// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class PitCreature<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart creature;
	private final ModelPart base;
	private final ModelPart jaw1;
	private final ModelPart jaw2;

	public PitCreature(ModelPart root) {
		this.creature = root.getChild("creature");
		this.base = root.getChild("creature").getChild("Base");
		this.jaw1 = root.getChild("creature").getChild("jaw1");
		this.jaw2 = root.getChild("creature").getChild("jaw2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition creature = partdefinition.addOrReplaceChild("creature", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Base = creature.addOrReplaceChild("Base", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -1.0F, -7.0F, 14.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(0, 15).addBox(-6.0F, -2.0F, -6.0F, 12.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition jaw1 = creature.addOrReplaceChild("jaw1", CubeListBuilder.create().texOffs(75, 44).addBox(4.0F, -15.0F, -6.0F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(79, 47).addBox(5.0F, -14.0F, -6.0F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(87, 24).addBox(4.0F, -18.0F, -4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(101, 24).addBox(4.0F, -18.0F, 3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(109, 47).addBox(5.0F, -14.0F, 5.0F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(105, 50).addBox(6.0F, -13.0F, 4.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(83, 50).addBox(6.0F, -13.0F, -5.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(113, 44).addBox(4.0F, -15.0F, 5.0F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(85, 82).addBox(4.0F, -4.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(89, 5).addBox(4.0F, -19.0F, -3.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(85, 69).addBox(5.0F, -6.0F, -5.0F, 1.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(85, 57).addBox(6.0F, -7.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(87, 20).addBox(5.0F, -17.0F, -4.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(89, 13).addBox(5.0F, -18.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(91, 0).addBox(4.0F, -20.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(85, 31).addBox(6.0F, -15.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(87, 43).addBox(7.0F, -13.0F, -4.0F, 1.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(85, 38).addBox(4.0F, -16.0F, -5.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(101, 38).addBox(4.0F, -16.0F, 4.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 0.0F, 0.0F));

		PartDefinition teeth = jaw1.addOrReplaceChild("teeth", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition teeth_top = teeth.addOrReplaceChild("teeth_top", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Tooth5 = teeth_top.addOrReplaceChild("Tooth5", CubeListBuilder.create().texOffs(113, 36).addBox(2.0F, -17.0F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(114, 34).addBox(1.0F, -16.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, 1.0F));

		PartDefinition Tooth10 = teeth_top.addOrReplaceChild("Tooth10", CubeListBuilder.create().texOffs(113, 36).addBox(2.0F, -17.0F, 5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(114, 34).addBox(1.0F, -16.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, -3.0F));

		PartDefinition teeth_1 = teeth.addOrReplaceChild("teeth_1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Tooth4 = teeth_1.addOrReplaceChild("Tooth4", CubeListBuilder.create().texOffs(113, 36).addBox(2.0F, -15.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(114, 34).addBox(1.0F, -14.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, 1.0F));

		PartDefinition Tooth9 = teeth_1.addOrReplaceChild("Tooth9", CubeListBuilder.create().texOffs(113, 36).addBox(2.0F, -15.0F, 4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(114, 34).addBox(1.0F, -14.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, -1.0F));

		PartDefinition teeth_2 = teeth.addOrReplaceChild("teeth_2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Tooth3 = teeth_2.addOrReplaceChild("Tooth3", CubeListBuilder.create().texOffs(112, 38).addBox(2.0F, -8.0F, -6.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(114, 34).addBox(1.0F, -8.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -4.0F, 1.0F));

		PartDefinition Tooth8 = teeth_2.addOrReplaceChild("Tooth8", CubeListBuilder.create().texOffs(112, 38).addBox(2.0F, -8.0F, 3.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(114, 34).addBox(1.0F, -8.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -4.0F, 1.0F));

		PartDefinition teeth_3 = teeth.addOrReplaceChild("teeth_3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Tooth2 = teeth_3.addOrReplaceChild("Tooth2", CubeListBuilder.create().texOffs(112, 38).addBox(2.0F, -6.0F, -6.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(114, 34).addBox(1.0F, -6.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -3.0F, 1.0F));

		PartDefinition Tooth7 = teeth_3.addOrReplaceChild("Tooth7", CubeListBuilder.create().texOffs(112, 38).addBox(2.0F, -6.0F, 3.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(114, 34).addBox(1.0F, -6.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -3.0F, 1.0F));

		PartDefinition teeth_bot = teeth.addOrReplaceChild("teeth_bot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Tooth = teeth_bot.addOrReplaceChild("Tooth", CubeListBuilder.create().texOffs(113, 36).addBox(2.0F, -6.0F, -6.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(114, 34).addBox(1.0F, -6.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, 1.0F));

		PartDefinition Tooth6 = teeth_bot.addOrReplaceChild("Tooth6", CubeListBuilder.create().texOffs(113, 36).addBox(2.0F, -6.0F, 3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(114, 34).addBox(1.0F, -6.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, 1.0F));

		PartDefinition jaw2 = creature.addOrReplaceChild("jaw2", CubeListBuilder.create().texOffs(75, 44).addBox(3.0F, -15.0F, -6.0F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(79, 47).addBox(4.0F, -14.0F, -6.0F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(87, 24).addBox(3.0F, -18.0F, -4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(101, 24).addBox(3.0F, -18.0F, 3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(109, 47).addBox(4.0F, -14.0F, 5.0F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(105, 50).addBox(5.0F, -13.0F, 4.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(83, 50).addBox(5.0F, -13.0F, -5.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(113, 44).addBox(3.0F, -15.0F, 5.0F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(85, 82).addBox(3.0F, -4.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(89, 5).addBox(3.0F, -19.0F, -3.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(85, 69).addBox(4.0F, -6.0F, -5.0F, 1.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(85, 57).addBox(5.0F, -7.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(87, 20).addBox(4.0F, -17.0F, -4.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(89, 13).addBox(4.0F, -18.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(91, 0).addBox(3.0F, -20.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(85, 31).addBox(5.0F, -15.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(87, 43).addBox(6.0F, -13.0F, -4.0F, 1.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(85, 38).addBox(3.0F, -16.0F, -5.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(101, 38).addBox(3.0F, -16.0F, 4.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition teeth2 = jaw2.addOrReplaceChild("teeth2", CubeListBuilder.create(), PartPose.offset(-3.0F, 0.0F, 0.0F));

		PartDefinition teeth_top2 = teeth2.addOrReplaceChild("teeth_top2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Tooth11 = teeth_top2.addOrReplaceChild("Tooth11", CubeListBuilder.create().texOffs(113, 36).addBox(4.0F, -17.0F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(114, 34).addBox(3.0F, -16.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, 1.0F));

		PartDefinition Tooth12 = teeth_top2.addOrReplaceChild("Tooth12", CubeListBuilder.create().texOffs(113, 36).addBox(4.0F, -17.0F, 5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(114, 34).addBox(3.0F, -16.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, -3.0F));

		PartDefinition teeth_4 = teeth2.addOrReplaceChild("teeth_4", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Tooth13 = teeth_4.addOrReplaceChild("Tooth13", CubeListBuilder.create().texOffs(113, 36).addBox(4.0F, -15.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(114, 34).addBox(3.0F, -14.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, 1.0F));

		PartDefinition Tooth14 = teeth_4.addOrReplaceChild("Tooth14", CubeListBuilder.create().texOffs(113, 36).addBox(4.0F, -15.0F, 4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(114, 34).addBox(3.0F, -14.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, -1.0F));

		PartDefinition teeth_5 = teeth2.addOrReplaceChild("teeth_5", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Tooth15 = teeth_5.addOrReplaceChild("Tooth15", CubeListBuilder.create().texOffs(112, 38).addBox(4.0F, -8.0F, -6.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(114, 34).addBox(3.0F, -8.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -4.0F, 1.0F));

		PartDefinition Tooth16 = teeth_5.addOrReplaceChild("Tooth16", CubeListBuilder.create().texOffs(112, 38).addBox(4.0F, -8.0F, 3.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(114, 34).addBox(3.0F, -8.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -4.0F, 1.0F));

		PartDefinition teeth_6 = teeth2.addOrReplaceChild("teeth_6", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Tooth17 = teeth_6.addOrReplaceChild("Tooth17", CubeListBuilder.create().texOffs(112, 38).addBox(4.0F, -6.0F, -6.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(114, 34).addBox(3.0F, -6.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -3.0F, 1.0F));

		PartDefinition Tooth18 = teeth_6.addOrReplaceChild("Tooth18", CubeListBuilder.create().texOffs(112, 38).addBox(4.0F, -6.0F, 3.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(114, 34).addBox(3.0F, -6.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -3.0F, 1.0F));

		PartDefinition teeth_bot2 = teeth2.addOrReplaceChild("teeth_bot2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Tooth19 = teeth_bot2.addOrReplaceChild("Tooth19", CubeListBuilder.create().texOffs(113, 36).addBox(4.0F, -6.0F, -6.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(114, 34).addBox(3.0F, -6.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, 1.0F));

		PartDefinition Tooth20 = teeth_bot2.addOrReplaceChild("Tooth20", CubeListBuilder.create().texOffs(113, 36).addBox(4.0F, -6.0F, 3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(114, 34).addBox(3.0F, -6.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, 1.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		creature.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return creature;
	}
}