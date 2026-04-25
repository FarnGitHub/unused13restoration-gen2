package io.bluestaggo.unused13restoration;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.unmapped.GeneratorProfiler;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class WorldGenProfilerScreen extends Screen {
	private final Screen parent;
	private final Thread profilerThread;
	private final ByteArrayOutputStream outputStream;
	private final PrintStream printStream;

	public WorldGenProfilerScreen(Screen parent) {
		this.parent = parent;
		this.outputStream = new ByteArrayOutputStream();
		this.printStream = new PrintStream(this.outputStream);
		System.setOut(this.printStream);
		System.setErr(this.printStream);
		this.profilerThread = new Thread(() -> new GeneratorProfiler().run());
		this.profilerThread.setDaemon(true);
		this.profilerThread.start();
	}

	@Override
	public void init() {
		this.buttons.add(new ButtonWidget(0, this.width / 2 - 100, this.height - 30, "Back"));
	}

	@Override
	public void render(int mouseX, int mouseY, float tickDelta) {
		((ButtonWidget)this.buttons.get(0)).active = !this.profilerThread.isAlive();

		this.renderBackground();
		String output = outputStream.toString();
		String[] lines = output.split("\n");
		for (int i = 0; i < lines.length; i++) {
			this.drawString(this.textRenderer, lines[i], 10, 10 + i * 10, 0xFFFFFF);
		}
		super.render(mouseX, mouseY, tickDelta);
	}

	@Override
	protected void keyPressed(char chr, int key) {
	}

	@Override
	protected void buttonClicked(ButtonWidget button) {
		this.minecraft.openScreen(this.parent);
	}

	@Override
	public void removed() {
		System.setOut(null);
		System.setErr(null);
		this.profilerThread.interrupt();
		this.printStream.close();
	}
}
