package io.github.mysticalmike.gunmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.mysticalmike.gunmod.core.registries.EntityRegistry;
import io.github.mysticalmike.gunmod.core.registries.ItemRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GunMod.MOD_ID)
public class GunMod
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "mysty_gunmod";
    public static final ItemGroup GUNMOD_TAB = new CreateTab("gunmodtab");
    
    public GunMod() 
    {
    	IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        
        ItemRegistry.ITEMS.register(bus);
        EntityRegistry.ENTITIES.register(bus);
    }
    
    public static class CreateTab extends ItemGroup
	{
		public CreateTab(String label)
		{
			super(label);
		}
		
		@Override
		public ItemStack createIcon()
		{
			return ItemRegistry.EXAMPLE_ITEM.get().getDefaultInstance();
		}
	}
}
