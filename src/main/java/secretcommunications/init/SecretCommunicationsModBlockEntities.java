
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package secretcommunications.init;

import secretcommunications.block.entity.PasswordBinaryMakerBlockEntity;
import secretcommunications.block.entity.EncrypterBlockEntity;
import secretcommunications.block.entity.BinaryComputerBlockEntity;

import secretcommunications.SecretCommunicationsMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

public class SecretCommunicationsModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SecretCommunicationsMod.MODID);
	public static final RegistryObject<BlockEntityType<?>> TEXT_INTERFACE = register("text_interface", SecretCommunicationsModBlocks.TEXT_INTERFACE, BinaryComputerBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> ENCRYPTER = register("encrypter", SecretCommunicationsModBlocks.ENCRYPTER, EncrypterBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> PASSWORD_BINARY_MAKER = register("password_binary_maker", SecretCommunicationsModBlocks.PASSWORD_BINARY_MAKER, PasswordBinaryMakerBlockEntity::new);

	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
