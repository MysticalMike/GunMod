package io.github.mysticalmike.gunmod.common.entities;

import io.github.mysticalmike.gunmod.GunMod;
import io.github.mysticalmike.gunmod.core.registries.EntityRegistry;
import io.github.mysticalmike.gunmod.core.registries.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class BulletEntity extends AbstractArrowEntity implements IRendersAsItem, IEntityAdditionalSpawnData 
{
	public BulletEntity(EntityType<? extends BulletEntity> type, World worldIn) 
	{
		super(type, worldIn);
	}
	
	public BulletEntity(World worldIn, double x, double y, double z) 
	{
		super(EntityRegistry.BULLET_ENTITY.get(), x, y, z, worldIn);
	}

	public BulletEntity(World worldIn, LivingEntity shooter) 
	{
		super(EntityRegistry.BULLET_ENTITY.get(), shooter, worldIn);
	}
	
	@Override
    public IPacket<?> createSpawnPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
	
	@Override
	protected ItemStack getArrowStack() 
	{
		return new ItemStack(ItemRegistry.EXAMPLE_ITEM.get());
	}
	
	@Override
	public ItemStack getItem() 
	{
		return new ItemStack(ItemRegistry.EXAMPLE_ITEM.get());
	}
	
	@Override
	public void writeSpawnData(PacketBuffer buffer) 
	{
		double x = this.getMotion().getX();
		double y = this.getMotion().getY();
		double z = this.getMotion().getZ();
		
		GunMod.LOGGER.info("WRITE: " + x + " | " + y + " | " + z);
		
		buffer.writeDouble(x);
		buffer.writeDouble(y);
		buffer.writeDouble(z);
	}
	
	@Override
	public void readSpawnData(PacketBuffer additionalData) 
	{
		double x = additionalData.readDouble();
		double y = additionalData.readDouble();
		double z = additionalData.readDouble();
		
		GunMod.LOGGER.info("READ:  " + x + " | " + y + " | " + z);
		
		this.setMotion(x, y, z); // Doesn't work
	}
}
