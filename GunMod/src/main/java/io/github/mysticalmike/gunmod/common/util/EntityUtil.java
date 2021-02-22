package io.github.mysticalmike.gunmod.common.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;

import java.util.List;

public class EntityUtil 
{
	public static EntityRayTraceResult raytraceEntityPlayerLook(PlayerEntity player, float range) 
	{
		Vector3d eye = new Vector3d(player.getPosX(), player.getPosY() + player.getEyeHeight(), player.getPosZ()); // Entity.getPositionEyes
		Vector3d look = player.getLook(1.0f);

		return raytraceEntity(player, eye, look, range, true);
	}
	
	public static EntityRayTraceResult raytraceEntity(Entity entity, Vector3d start, Vector3d look, double range, boolean ignoreCanBeCollidedWith) 
	{
		Vector3d direction = start.add(look.x * range, look.y * range, look.z * range);

		Entity pointedEntity = null;
		Vector3d hit = null;
		AxisAlignedBB bb = entity.getBoundingBox().expand(look.x * range, look.y * range, look.z * range).expand(1, 1, 1);
		List<Entity> entitiesInArea = entity.getEntityWorld().getEntitiesWithinAABBExcludingEntity(entity, bb);
		double range2 = range;

		for (Entity candidate : entitiesInArea) 
		{
			if (ignoreCanBeCollidedWith || candidate.canBeCollidedWith()) 
			{
				// does our vector go through the entity?
				double colBorder = candidate.getCollisionBorderSize();
				AxisAlignedBB entityBB = candidate.getBoundingBox().expand(colBorder, colBorder, colBorder);
				EntityRayTraceResult movingobjectposition = ProjectileHelper.rayTraceEntities(entity, start, direction, entityBB, (p_215312_0_) -> {
		               return !p_215312_0_.isSpectator() && p_215312_0_.canBeCollidedWith();
				}, range);
				
				// needs special casing: vector starts inside the entity
				if (entityBB.contains(start)) 
				{
					if (0.0D < range2 || range2 == 0.0D) 
					{
						pointedEntity = candidate;
						hit = movingobjectposition == null ? start : movingobjectposition.getHitVec();
						range2 = 0.0D;
					}
				} 
				else if (movingobjectposition != null) 
				{
					double dist = start.distanceTo(movingobjectposition.getHitVec());

					if (dist < range2 || range2 == 0.0D) 
					{
						if (candidate == entity.getRidingEntity() && !entity.canRiderInteract()) 
						{
							if (range2 == 0.0D) 
							{
								pointedEntity = candidate;
								hit = movingobjectposition.getHitVec();
							}
						} 
						else 
						{
							pointedEntity = candidate;
							hit = movingobjectposition.getHitVec();
							range2 = dist;
						}
					}
				}
			}
		}
		
		if (pointedEntity != null && range2 < range) {
			return new EntityRayTraceResult(pointedEntity, hit);
		}
		return null;
	}
}
