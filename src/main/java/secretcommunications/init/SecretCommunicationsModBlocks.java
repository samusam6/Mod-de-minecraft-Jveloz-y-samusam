
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package secretcommunications.init;

import secretcommunications.block.PasswordBinaryMakerBlock;
import secretcommunications.block.EncrypterBlock;
import secretcommunications.block.BinaryComputerBlock;

import secretcommunications.SecretCommunicationsMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

public class SecretCommunicationsModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, SecretCommunicationsMod.MODID);
	public static final RegistryObject<Block> TEXT_INTERFACE = REGISTRY.register("text_interface", () -> new BinaryComputerBlock());
	public static final RegistryObject<Block> ENCRYPTER = REGISTRY.register("encrypter", () -> new EncrypterBlock());
	public static final RegistryObject<Block> PASSWORD_BINARY_MAKER = REGISTRY.register("password_binary_maker", () -> new PasswordBinaryMakerBlock());
	// Start of user code block custom blocks
	// End of user code block custom blocks
}
