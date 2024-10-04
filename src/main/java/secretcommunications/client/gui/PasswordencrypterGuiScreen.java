package secretcommunications.client.gui;

import secretcommunications.world.inventory.PasswordencrypterGuiMenu;

import secretcommunications.procedures.PasswordencrypterCheckProcedure;
import secretcommunications.procedures.EncrypterOnBlockRightClickedProcedure;

import secretcommunications.network.PasswordencrypterGuiButtonMessage;

import secretcommunications.SecretCommunicationsMod;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class PasswordencrypterGuiScreen extends AbstractContainerScreen<PasswordencrypterGuiMenu> {
	private final static HashMap<String, Object> guistate = PasswordencrypterGuiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	public static EditBox comp;
	public static Checkbox dysplaypassword;
	Button button_set;

	public PasswordencrypterGuiScreen(PasswordencrypterGuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("secret_communications:textures/screens/passwordencrypter_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		comp.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 5 && mouseX < leftPos + 29 && mouseY > topPos + 57 && mouseY < topPos + 81)
			guiGraphics.renderTooltip(font, Component.translatable("gui.secret_communications.passwordencrypter_gui.tooltip_enter_the_en"), mouseX, mouseY);
		if (mouseX > leftPos + 138 && mouseX < leftPos + 162 && mouseY > topPos + 58 && mouseY < topPos + 82)
			guiGraphics.renderTooltip(font, Component.translatable("gui.secret_communications.passwordencrypter_gui.tooltip_set_the_password"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	public static HashMap<String, String> getTextboxValues() {
		textstate.put("textin:comp", comp.getValue());
		textstate.put("checkboxin:dysplaypassword", dysplaypassword.selected() ? "true" : "false");
		return textstate;
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (comp.isFocused())
			return comp.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		comp.tick();
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String compValue = comp.getValue();
		super.resize(minecraft, width, height);
		comp.setValue(compValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font,

				PasswordencrypterCheckProcedure.execute(entity, guistate), 40, 19, -12829636, false);
		guiGraphics.drawString(this.font,

				EncrypterOnBlockRightClickedProcedure.execute(world, x, y, z), 6, 6, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		comp = new EditBox(this.font, this.leftPos + 8, this.topPos + 61, 118, 18, Component.translatable("gui.secret_communications.passwordencrypter_gui.comp"));
		comp.setMaxLength(32767);
		guistate.put("text:comp", comp);
		this.addWidget(this.comp);
		button_set = Button.builder(Component.translatable("gui.secret_communications.passwordencrypter_gui.button_set"), e -> {
			if (true) {
				textstate.put("textin:comp", comp.getValue());
				textstate.put("checkboxin:dysplaypassword", dysplaypassword.selected() ? "true" : "false");
				SecretCommunicationsMod.PACKET_HANDLER.sendToServer(new PasswordencrypterGuiButtonMessage(0, x, y, z, textstate));
				PasswordencrypterGuiButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 129, this.topPos + 60, 40, 20).build();
		guistate.put("button:button_set", button_set);
		this.addRenderableWidget(button_set);
		dysplaypassword = new Checkbox(this.leftPos + 78, this.topPos + 34, 20, 20, Component.translatable("gui.secret_communications.passwordencrypter_gui.dysplaypassword"), false);
		guistate.put("checkbox:dysplaypassword", dysplaypassword);
		this.addRenderableWidget(dysplaypassword);
	}
}
