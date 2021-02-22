package io.github.mysticalmike.gunmod;

import io.github.mysticalmike.gunmod.client.render.entity.BulletEntityRender;
import io.github.mysticalmike.gunmod.core.registries.EntityRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = GunMod.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientProxy 
{
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.BULLET_ENTITY.get(), BulletEntityRender::new);
	}
}
