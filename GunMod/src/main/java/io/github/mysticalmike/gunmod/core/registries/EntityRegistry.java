package io.github.mysticalmike.gunmod.core.registries;

import io.github.mysticalmike.gunmod.GunMod;
import io.github.mysticalmike.gunmod.common.entities.BulletEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityRegistry 
{
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, GunMod.MOD_ID);
	
	public static final RegistryObject<EntityType<BulletEntity>> BULLET_ENTITY = ENTITIES
			.register("bullet_entity", 
					() -> EntityType.Builder.<BulletEntity>create(BulletEntity::new, EntityClassification.MISC)
							.size(0.5F, 0.5F)
							.build(new ResourceLocation(GunMod.MOD_ID, "bullet_entity").toString()));
}
