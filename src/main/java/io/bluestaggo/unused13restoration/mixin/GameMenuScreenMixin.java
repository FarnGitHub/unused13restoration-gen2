package io.bluestaggo.unused13restoration.mixin;

import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin extends Screen {
	@Unique private ButtonWidget u13r$panoramaButton;
	@Unique private String u13r$panoramaStatus = "";

	@Inject(
		method = "init",
		at = @At("TAIL")
	)
	private void addPanoramaButton(CallbackInfo ci) {
		this.buttons.add(this.u13r$panoramaButton = new ButtonWidget(1, this.width / 2 - 100, this.height / 4 + 56, 200, 20, "Take Panorama"));
	}

	@Inject(
		method = "buttonClicked",
		at = @At("HEAD"),
		cancellable = true
	)
	protected void panoramaButtonClicked(ButtonWidget button, CallbackInfo ci) {
		if (button == this.u13r$panoramaButton) {
			this.u13r$panoramaStatus = ((MinecraftAccessor)this.minecraft).invokeTakePanorama(this.minecraft.gameDir, 256, 256);
			ci.cancel();
		}
	}

	@Inject(
		method = "render",
		at = @At("TAIL")
	)
	public void renderPanoramaStatus(int mouseX, int mouseY, float tickDelta, CallbackInfo ci) {
		this.drawCenteredString(this.textRenderer, this.u13r$panoramaStatus, this.width / 2, this.height - 20, 0xFFFFFF);
	}
}
