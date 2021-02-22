package io.github.mysticalmike.gunmod.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import io.github.mysticalmike.gunmod.GunMod;
import io.github.mysticalmike.gunmod.common.entities.BulletEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BulletEntityRender extends EntityRenderer<BulletEntity>
{
	private final ItemRenderer itemRenderer;
	public static final ResourceLocation RES_BULLET_ENTITY = new ResourceLocation(GunMod.MOD_ID, "textures/entity/bullet_entity.png");
	
	public BulletEntityRender(EntityRendererManager renderManagerIn) 
	{
		super(renderManagerIn);
		this.itemRenderer = Minecraft.getInstance().getItemRenderer();
	}
	
	public void render(BulletEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) 
	{
		matrixStackIn.push();
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(entityIn.rotationYaw - 90.0F));
		
		this.itemRenderer.renderItem(entityIn.getItem(), ItemCameraTransforms.TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
		matrixStackIn.pop();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
	
	public ResourceLocation getEntityTexture(BulletEntity entity) 
	{
		return RES_BULLET_ENTITY;
	}
}
