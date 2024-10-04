package secretcommunications.entity.model;

import software.bernie.geckolib.model.GeoModel;

import secretcommunications.entity.ComphusEntity;

import net.minecraft.resources.ResourceLocation;

public class ComphusModel extends GeoModel<ComphusEntity> {
	@Override
	public ResourceLocation getAnimationResource(ComphusEntity entity) {
		return new ResourceLocation("secret_communications", "animations/comphus.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(ComphusEntity entity) {
		return new ResourceLocation("secret_communications", "geo/comphus.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ComphusEntity entity) {
		return new ResourceLocation("secret_communications", "textures/entities/" + entity.getTexture() + ".png");
	}

}
