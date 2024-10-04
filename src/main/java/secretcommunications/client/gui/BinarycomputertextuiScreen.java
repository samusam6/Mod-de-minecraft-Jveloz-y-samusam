package secretcommunications.client.gui;

import secretcommunications.world.inventory.BinarycomputertextuiMenu;

import secretcommunications.procedures.CheckifpassonisonandblockbelowProcedure;

import secretcommunications.network.BinarycomputertextuiButtonMessage;

import secretcommunications.SecretCommunicationsMod;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import java.util.Set;
import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class BinarycomputertextuiScreen extends AbstractContainerScreen<BinarycomputertextuiMenu> {
	private final static HashMap<String, Object> guistate = BinarycomputertextuiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	public static EditBox Set;
	Button button_send;

	public BinarycomputertextuiScreen(BinarycomputertextuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		Set.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("secret_communications:textures/screens/binarycomputertextui.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (Set.isFocused())
			return Set.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		Set.tick();
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String SetValue = Set.getValue();
		super.resize(minecraft, width, height);
		Set.setValue(SetValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.secret_communications.binarycomputertextui.label_status"), 38, 137, -1, false);
		guiGraphics.drawString(this.font,

				CheckifpassonisonandblockbelowProcedure.execute(world, x, y, z), 77, 137, -1, false);
	}

	@Override
	public void init() {
		super.init();
		Set = new EditBox(this.font, this.leftPos + 33, this.topPos + 47, 118, 18, Component.translatable("gui.secret_communications.binarycomputertextui.Set")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.secret_communications.binarycomputertextui.Set").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.secret_communications.binarycomputertextui.Set").getString());
				else
					setSuggestion(null);
			}
		};
		Set.setSuggestion(Component.translatable("gui.secret_communications.binarycomputertextui.Set").getString());
		Set.setMaxLength(32767);
		guistate.put("text:Set", Set);
		this.addWidget(this.Set);
		button_send = Button.builder(Component.translatable("gui.secret_communications.binarycomputertextui.button_send"), e -> {
			if (true) {
				textstate.put("textin:Set", Set.getValue());
				SecretCommunicationsMod.PACKET_HANDLER.sendToServer(new BinarycomputertextuiButtonMessage(0, x, y, z, textstate));
				BinarycomputertextuiButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 67, this.topPos + 90, 46, 20).build();
		guistate.put("button:button_send", button_send);
		this.addRenderableWidget(button_send);
	}
}
