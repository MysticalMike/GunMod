package io.github.mysticalmike.gunmod.core.registries;

import io.github.mysticalmike.gunmod.GunMod;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry 
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GunMod.MOD_ID);
	
	public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", 
			() -> new Item(new Item.Properties().group(GunMod.GUNMOD_TAB)));
	public static final RegistryObject<Item> EMPGun = ITEMS.register("empgun", 
			() -> new io.github.mysticalmike.gunmod.common.items.EMPGun(new Item.Properties().group(GunMod.GUNMOD_TAB)));
}