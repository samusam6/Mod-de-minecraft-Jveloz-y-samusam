
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package secretcommunications.init;

import secretcommunications.client.gui.RuleSetBinaryScreen;
import secretcommunications.client.gui.PasswordencrypterGuiScreen;
import secretcommunications.client.gui.BinarycomputertextuiScreen;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SecretCommunicationsModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(SecretCommunicationsModMenus.RULE_SET_BINARY.get(), RuleSetBinaryScreen::new);
			MenuScreens.register(SecretCommunicationsModMenus.PASSWORDENCRYPTER_GUI.get(), PasswordencrypterGuiScreen::new);
			MenuScreens.register(SecretCommunicationsModMenus.BINARYCOMPUTERTEXTUI.get(), BinarycomputertextuiScreen::new);
		});
	}
}
