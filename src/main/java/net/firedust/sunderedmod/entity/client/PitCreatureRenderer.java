package net.firedust.sunderedmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.firedust.sunderedmod.SunderedMod;
import net.firedust.sunderedmod.entity.custom.PitCreatureEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PitCreatureRenderer extends MobRenderer<PitCreatureEntity, PitCreature<PitCreatureEntity>> {
    public PitCreatureRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new PitCreature<>(pContext.bakeLayer(ModModelLayers.PIT_CREATURE_LAYER)), .5f);
    }

    @Override
    public ResourceLocation getTextureLocation(PitCreatureEntity pitCreatureEntity) {
        return new ResourceLocation(SunderedMod.MOD_ID, "textures/entity/pit_creature_texture.png");
    }

    @Override
    public void render(PitCreatureEntity pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        pPoseStack.scale(pEntity.size, pEntity.size, pEntity.size);

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
