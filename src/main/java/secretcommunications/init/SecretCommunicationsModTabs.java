
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package secretcommunications.init;

import secretcommunications.SecretCommunicationsMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

public class SecretCommunicationsModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SecretCommunicationsMod.MODID);
	public static final RegistryObject<CreativeModeTab> COMPONENTS = REGISTRY.register("components",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.secret_communications.components")).icon(() -> new ItemStack(SecretCommunicationsModBlocks.TEXT_INTERFACE.get())).displayItems((parameters, tabData) -> {
				tabData.accept(SecretCommunicationsModBlocks.ENCRYPTER.get().asItem());
				tabData.accept(SecretCommunicationsModBlocks.PASSWORD_BINARY_MAKER.get().asItem());
				tabData.accept(SecretCommunicationsModItems.BINARY_PASSWORD_CODE.get());
				tabData.accept(SecretCommunicationsModBlocks.TEXT_INTERFACE.get().asItem());
			}).withSearchBar().build());
}
