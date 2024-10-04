
package secretcommunications.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class BinaryRuleCodeItem extends Item {
	public BinaryRuleCodeItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
