package io.github.mysticalmike.gunmod.common.entities;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneDiodeBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EMPEntity extends ThrowableEntity 
{
	public EMPEntity(EntityType<? extends ThrowableEntity> type, World worldIn) 
	{
		super(type, worldIn);
	}
	
	/*
	public EMPEntity(World worldIn, LivingEntity shooter) 
	{
		super(ItemInit.EMPENTITY.get(), shooter, worldIn);
	}*/

	public static final float explosionPower = 0.75F;
	public static final int empRadius = 4;
	
	private void explode()
	{
		int bx = (int)getPosX();
        int by = (int)getPosY();
        int bz = (int)getPosZ();
        world.createExplosion(this, getPosX(), getPosY(), getPosZ(), 0.75F, Explosion.Mode.NONE);
 
        for (int x = bx - empRadius; x <= empRadius; x++)
        {
            for (int y = by - empRadius; y <= by + empRadius; y++)
            {
                for (int z = bz - empRadius; z <= bz + empRadius; z++)
                {
                    Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
                    if (block == Blocks.REDSTONE_WIRE || block instanceof RedstoneDiodeBlock)
                    {
                    	world.destroyBlock(new BlockPos(x, y, z), true);
                    }
                    if (block == Blocks.REDSTONE_BLOCK)
                    {
                    	world.setBlockState(new BlockPos(x, y, z), Blocks.STONE.getDefaultState());
                    }
                }
            }
        }
        setDead();
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		if (ticksExisted > 40)
        {
            explode();
        }
 
        for (int i = 0; i < 10; i++)
        {
            double x = (double)(rand.nextInt(10) - 5) / 8.0D;
            double y = (double)(rand.nextInt(10) - 5) / 8.0D;
            double z = (double)(rand.nextInt(10) - 5) / 8.0D;
            world.addParticle(ParticleTypes.FIREWORK, x, y, z, x, y, z);
        }
	}
	
    @Override
    protected float getGravityVelocity()
    {
        return 0.005F;
    }
    
    @Override
    protected void onImpact(RayTraceResult result) 
    {
    	explode();
    }
    
	@Override
	protected void registerData() 
	{
		
	}
}
