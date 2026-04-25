package io.bluestaggo.unused13restoration;

import net.minecraft.client.BiomeMap;
import net.minecraft.client.gui.GuiParticle;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.WorldSaveConflictScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.unmapped.*;
import org.lwjgl.input.Mouse;

import javax.swing.*;
import java.util.function.Consumer;

public class DevFeaturesScreen extends Screen {
	private final Screen parent;
	private int mouseX;
	private int mouseY;
	private int prevMouseX;
	private int prevMouseY;

	public DevFeaturesScreen(Screen parent) {
		this.parent = parent;
	}

	@Override
	public void init() {
		this.buttons.add(new ButtonWidget(-1, this.width / 2 - 100, this.height - 40, 200, 20, "Back"));

		Entry[] entries = Entry.values();
		for (int i = 0; i < entries.length; i++) {
			this.buttons.add(new ButtonWidget(i, this.width / 2 - 205 + 210 * (i % 2), 40 + i / 2 * 25, 200, 20, entries[i].name));
		}
	}

	@Override
	protected void buttonClicked(ButtonWidget button) {
		if (button.id == -1) {
			this.minecraft.openScreen(this.parent);
			return;
		}

		Entry.values()[button.id].action.accept(this);
	}

	@Override
	public void tick() {
		if (Mouse.isButtonDown(0)) {
			this.particlesGui.m_46367632(new GuiParticle(
				this.mouseX,
				this.mouseY,
				Math.random() * 5.0 - 2.5 - (this.prevMouseX - this.mouseX),
				Math.random() * 5.0 - 2.5 - (this.prevMouseY - this.mouseY)
			));
		}
	}

	@Override
	public void render(int mouseX, int mouseY, float tickDelta) {
		this.prevMouseX = this.mouseX;
		this.prevMouseY = this.mouseY;
		this.mouseX = mouseX;
		this.mouseY = mouseY;

		this.renderBackground();
		this.drawCenteredString(this.textRenderer, "Dev Features", this.width / 2, 20, 0xFFFFFF);
		super.render(mouseX, mouseY, tickDelta);

		if (this.particlesGui != null) {
			this.particlesGui.render(tickDelta);
		}
	}

	enum Entry {
		BIOME_PREVIEWER("Biome Previewer", screen -> threaded(
			() -> {
				BiomeMap biomePreviewer = new BiomeMap(1200, 800, 1);
				JFrame jFrame = new JFrame("Map test");
				jFrame.add(biomePreviewer);
				jFrame.pack();
				jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				jFrame.setLocationRelativeTo(null);
				jFrame.setVisible(true);
			})),
		LEVEL_SAVE_CONFLICT_SCREEN("Leftover Level Save Conflict Screen",
			screen -> screen.minecraft.openScreen(new WorldSaveConflictScreen())),
		MODEL_TEXTURE_GENERATOR("Model Texture Generator",
			screen -> screen.minecraft.openScreen(new ModelTextureGeneratorScreen(screen))),
		POTION_TESTER("b1.9-pre2 Potion Tester", screen -> threaded(
			() -> new PotionTester().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE))),
		WORLD_GENERATION_PROFILER("World generation Profiler",
			screen -> screen.minecraft.openScreen(new WorldGenProfilerScreen(screen))),
		;

		final String name;
		final Consumer<DevFeaturesScreen> action;

		Entry(String name, Consumer<DevFeaturesScreen> action) {
			this.name = name;
			this.action = action;
		}

		private static void threaded(Runnable runnable) {
			Thread thread = new Thread(runnable);
			thread.setDaemon(true);
			thread.start();
		}
	}
}
