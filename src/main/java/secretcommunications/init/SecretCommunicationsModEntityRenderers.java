
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package secretcommunications.init;

import secretcommunications.client.renderer.ComphusRenderer;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SecretCommunicationsModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(SecretCommunicationsModEntities.COMPHUS.get(), ComphusRenderer::new);
	}
}
