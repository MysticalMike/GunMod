package io.github.mysticalmike.gunmod.common.items;

import io.github.mysticalmike.gunmod.common.entities.BulletEntity;
import io.github.mysticalmike.gunmod.core.registries.ItemRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

public class EMPGun extends Item {

	public EMPGun(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		if (playerIn.isCreative() || playerIn.inventory.hasItemStack(ItemRegistry.EXAMPLE_ITEM.get().getDefaultInstance()))
		{
			if (!playerIn.isCreative())
				playerIn.inventory.decrStackSize(playerIn.inventory.getSlotFor(ItemRegistry.EXAMPLE_ITEM.get().getDefaultInstance()), 1);
			
			worldIn.playSound((PlayerEntity)null, playerIn.getPosition(), SoundEvents.ITEM_CROSSBOW_LOADING_END, SoundCategory.PLAYERS, 0.5F, 1.0F);
			
			// Create forward velocity vector
			Vector3d vector3d1 = playerIn.getUpVector(1.0F);
            Quaternion quaternion = new Quaternion(new Vector3f(vector3d1), 0, true);
            Vector3d vector3d = playerIn.getLook(1.0F);
            Vector3f vector3f = new Vector3f(vector3d);
            vector3f.transform(quaternion);
			
			// Spawn projectile
			ProjectileEntity projectile;
			projectile = new BulletEntity(worldIn, playerIn);
			projectile.setNoGravity(true);
			projectile.shoot((double)vector3f.getX(), (double)vector3f.getY(), (double)vector3f.getZ(), 10, 0);
			
			worldIn.addEntity(projectile);
		}
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
