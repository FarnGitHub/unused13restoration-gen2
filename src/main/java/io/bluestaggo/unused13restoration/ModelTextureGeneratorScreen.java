package io.bluestaggo.unused13restoration;

import com.mojang.blaze3d.vertex.BufferBuilder;
import io.bluestaggo.unused13restoration.mixin.EntitiesAccessor;
import io.bluestaggo.unused13restoration.mixin.LivingEntityRendererAccessor;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.model.Model;
import net.minecraft.entity.Entity;
import net.minecraft.unmapped.C_7430114;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ModelTextureGeneratorScreen extends Screen {
	private final Screen parent;
	private TextFieldWidget entityField;
	private int texture;
	private int textureWidth = 256;
	private int textureHeight = 256;

	public ModelTextureGeneratorScreen(Screen parent) {
		this.parent = parent;
	}

	@Override
	public void init() {
		Keyboard.enableRepeatEvents(true);
		this.buttons.add(new ButtonWidget(0, this.width / 2 - 100, this.height - 30, 200, 20, "Back"));
		this.entityField = new TextFieldWidget(this.textRenderer, this.width / 2 - 100, 20, 200, 20);
		this.entityField.setMaxLength(128);
		this.entityField.setFocused(true);
		this.entityField.setText("EnderDragon");
		this.update();
	}

	private void update() {
		if (this.texture > 0) {
			this.minecraft.textureManager.remove(this.texture);
			this.texture = 0;
		}

		String entity = this.entityField.getText();
		Class<? extends Entity> entityClass = EntitiesAccessor.getKeyToType().get(entity);
		if (entityClass == null) {
			return;
		}

		EntityRenderer entityRenderer = EntityRenderDispatcher.INSTANCE.getRenderer(entityClass);
		if (!(entityRenderer instanceof LivingEntityRenderer)) {
			return;
		}

		Model model = ((LivingEntityRendererAccessor)entityRenderer).getModel();
		new C_7430114().m_8176741(model.parts);

		BufferedImage image;
		try {
			image = ImageIO.read(new File("output.png"));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		this.textureWidth = image.getWidth();
		this.textureHeight = image.getHeight();

		this.texture = this.minecraft.textureManager.bind(image);
	}

	@Override
	public void render(int mouseX, int mouseY, float tickDelta) {
		this.renderBackground();
		super.render(mouseX, mouseY, tickDelta);
		this.entityField.render();

		double minX = (this.width - this.textureWidth / 2.0) / 2.0;
		double maxX = (this.width + this.textureWidth / 2.0) / 2.0;
		double minY = (this.height - this.textureHeight / 2.0) / 2.0;
		double maxY = (this.height + this.textureHeight / 2.0) / 2.0;
		BufferBuilder bb = BufferBuilder.INSTANCE;

		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL11.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
		bb.start();
		bb.vertex(minX, maxY, 0.0, 0.0, 1.0);
		bb.vertex(maxX, maxY, 0.0, 1.0, 1.0);
		bb.vertex(maxX, minY, 0.0, 1.0, 0.0);
		bb.vertex(minX, minY, 0.0, 0.0, 0.0);
		bb.end();

		if (this.texture != 0) {
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.texture);
			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
			bb.start();
			bb.vertex(minX, maxY, 0.0, 0.0, 1.0);
			bb.vertex(maxX, maxY, 0.0, 1.0, 1.0);
			bb.vertex(maxX, minY, 0.0, 1.0, 0.0);
			bb.vertex(minX, minY, 0.0, 0.0, 0.0);
			bb.end();
		}
	}

	protected void keyPressed(char chr, int key) {
		if (this.entityField.keyPressed(chr, key)) {
			this.update();
		}
	}

	@Override
	public void removed() {
		Keyboard.enableRepeatEvents(false);
		this.minecraft.textureManager.remove(this.texture);
	}

	@Override
	protected void buttonClicked(ButtonWidget button) {
		this.minecraft.openScreen(this.parent);
	}
}
