package secretcommunications.client.gui;

import secretcommunications.world.inventory.RuleSetBinaryMenu;

import secretcommunications.network.RuleSetBinaryButtonMessage;

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

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class RuleSetBinaryScreen extends AbstractContainerScreen<RuleSetBinaryMenu> {
	private final static HashMap<String, Object> guistate = RuleSetBinaryMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	public static EditBox Password;
	Button button_empty;

	public RuleSetBinaryScreen(RuleSetBinaryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("secret_communications:textures/screens/rule_set_binary.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		Password.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 75 && mouseX < leftPos + 99 && mouseY > topPos + 56 && mouseY < topPos + 80)
			guiGraphics.renderTooltip(font, Component.translatable("gui.secret_communications.rule_set_binary.tooltip_sscxssnysave_the_password_in_a_saf"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (Password.isFocused())
			return Password.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		Password.tick();
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String PasswordValue = Password.getValue();
		super.resize(minecraft, width, height);
		Password.setValue(PasswordValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
	}

	@Override
	public void init() {
		super.init();
		Password = new EditBox(this.font, this.leftPos + 29, this.topPos + 20, 118, 18, Component.translatable("gui.secret_communications.rule_set_binary.Password")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.secret_communications.rule_set_binary.Password").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.secret_communications.rule_set_binary.Password").getString());
				else
					setSuggestion(null);
			}
		};
		Password.setSuggestion(Component.translatable("gui.secret_communications.rule_set_binary.Password").getString());
		Password.setMaxLength(32767);
		guistate.put("text:Password", Password);
		this.addWidget(this.Password);
		button_empty = Button.builder(Component.translatable("gui.secret_communications.rule_set_binary.button_empty"), e -> {
			if (true) {
				textstate.put("textin:Password", Password.getValue());
				SecretCommunicationsMod.PACKET_HANDLER.sendToServer(new RuleSetBinaryButtonMessage(0, x, y, z, textstate));
				RuleSetBinaryButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 75, this.topPos + 61, 25, 20).build();
		guistate.put("button:button_empty", button_empty);
		this.addRenderableWidget(button_empty);
	}
}
