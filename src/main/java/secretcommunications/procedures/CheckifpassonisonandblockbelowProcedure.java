package secretcommunications.procedures;

import secretcommunications.init.SecretCommunicationsModBlocks;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class CheckifpassonisonandblockbelowProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z) {
		if (SecretCommunicationsModBlocks.ENCRYPTER.get() == (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock()) {
			if ((new Object() {
				public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
					BlockEntity blockEntity = world.getBlockEntity(pos);
					if (blockEntity != null)
						return blockEntity.getPersistentData().getBoolean(tag);
					return false;
				}
			}.getValue(world, BlockPos.containing(x, y - 1, z), "ispasson")) == true) {
				return "Password on";
			}
			return "No password";
		}
		return "No conected";
	}
}
