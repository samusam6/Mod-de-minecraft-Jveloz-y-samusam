package secretcommunications.procedures;

import secretcommunications.init.SecretCommunicationsModBlocks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class EncrypterOnBlockRightClickedProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == SecretCommunicationsModBlocks.TEXT_INTERFACE.get()) {
			return "conected!";
		}
		return "Is not conected";
	}
}
