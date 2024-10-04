
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package secretcommunications.init;

import secretcommunications.world.inventory.RuleSetBinaryMenu;
import secretcommunications.world.inventory.PasswordencrypterGuiMenu;
import secretcommunications.world.inventory.BinarycomputertextuiMenu;

import secretcommunications.SecretCommunicationsMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

public class SecretCommunicationsModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, SecretCommunicationsMod.MODID);
	public static final RegistryObject<MenuType<RuleSetBinaryMenu>> RULE_SET_BINARY = REGISTRY.register("rule_set_binary", () -> IForgeMenuType.create(RuleSetBinaryMenu::new));
	public static final RegistryObject<MenuType<PasswordencrypterGuiMenu>> PASSWORDENCRYPTER_GUI = REGISTRY.register("passwordencrypter_gui", () -> IForgeMenuType.create(PasswordencrypterGuiMenu::new));
	public static final RegistryObject<MenuType<BinarycomputertextuiMenu>> BINARYCOMPUTERTEXTUI = REGISTRY.register("binarycomputertextui", () -> IForgeMenuType.create(BinarycomputertextuiMenu::new));
}
