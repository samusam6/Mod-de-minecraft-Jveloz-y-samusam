package secretcommunications.procedures;

import net.minecraft.world.item.ItemStack;

public class BinaryRuleCodePropertyValueProviderProcedure {
	public static double execute(ItemStack itemstack) {
		if ((itemstack.getOrCreateTag().getString("passwordbinary")).isEmpty()) {
			return 0;
		}
		return 1;
	}
}
