package secretcommunications.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Checkbox;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

public class PasswordencrypterCheckProcedure {
	public static String execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return "";
		if (new Object() {
			public int getAmount(int sltid) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
					if (stack != null)
						return stack.getCount();
				}
				return 0;
			}
		}.getAmount(0) != 0) {
			if ((guistate.containsKey("checkbox:dysplaypassword") && ((Checkbox) guistate.get("checkbox:dysplaypassword")).selected()) == true) {
				if ((guistate.containsKey("text:comp") ? ((EditBox) guistate.get("text:comp")).getValue() : "")
						.equals("" + ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getOrCreateTag()
								.getString("passwordbinary")))) {
					return ("" + ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getOrCreateTag()
							.getString("passwordbinary"))) + "";
				}
				return "";
			}
			return "";
		}
		return "";
	}
}
