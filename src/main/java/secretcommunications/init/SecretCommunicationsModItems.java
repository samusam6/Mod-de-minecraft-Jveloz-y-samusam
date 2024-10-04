
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package secretcommunications.init;

import secretcommunications.procedures.BinaryRuleCodePropertyValueProviderProcedure;

import secretcommunications.item.BinaryRuleCodeItem;

import secretcommunications.SecretCommunicationsMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.item.ItemProperties;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SecretCommunicationsModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, SecretCommunicationsMod.MODID);
	public static final RegistryObject<Item> TEXT_INTERFACE = block(SecretCommunicationsModBlocks.TEXT_INTERFACE);
	public static final RegistryObject<Item> ENCRYPTER = block(SecretCommunicationsModBlocks.ENCRYPTER);
	public static final RegistryObject<Item> BINARY_PASSWORD_CODE = REGISTRY.register("binary_password_code", () -> new BinaryRuleCodeItem());
	public static final RegistryObject<Item> PASSWORD_BINARY_MAKER = block(SecretCommunicationsModBlocks.PASSWORD_BINARY_MAKER);

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}

	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			ItemProperties.register(BINARY_PASSWORD_CODE.get(), new ResourceLocation("secret_communications:binary_password_code_load"),
					(itemStackToRender, clientWorld, entity, itemEntityId) -> (float) BinaryRuleCodePropertyValueProviderProcedure.execute(itemStackToRender));
		});
	}
}
