package io.bluestaggo.unused13restoration.mixin;

import io.bluestaggo.unused13restoration.DevFeaturesScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin extends Screen {
	@Unique private ButtonWidget u13r$devFeaturesButton;

	@Inject(
		method = "init",
		at = @At("TAIL")
	)
	private void addDevFeaturesButton(CallbackInfo ci) {
		this.buttons.add(this.u13r$devFeaturesButton
			= new ButtonWidget(0, this.width / 2 - 152, this.height / 6 + 144 - 6, 150, 20, "Dev Features..."));
	}

	@Inject(
		method = "buttonClicked",
		at = @At("HEAD"),
		cancellable = true
	)
	private void devFeaturesButtonClicked(ButtonWidget button, CallbackInfo ci) {
		if (button == this.u13r$devFeaturesButton) {
			this.minecraft.openScreen(new DevFeaturesScreen(this));
			ci.cancel();
		}
	}
}
