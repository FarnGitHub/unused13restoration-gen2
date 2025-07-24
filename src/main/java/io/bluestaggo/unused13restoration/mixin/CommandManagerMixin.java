package io.bluestaggo.unused13restoration.mixin;

import net.minecraft.server.command.Command;
import net.minecraft.server.command.handler.CommandManager;
import net.minecraft.server.command.handler.CommandRegistry;
import net.minecraft.unmapped.C_3506615;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandManager.class)
public class CommandManagerMixin extends CommandRegistry {
	@Inject(
		method = "<init>",
		at = @At("CTOR_HEAD")
	)
	public void registerUnusedCommands(CallbackInfo ci) {
		this.register(new C_3506615() {
			public int compareTo(@NotNull Object object) {
				if (object instanceof Command) {
					return this.getName().compareTo(((Command)object).getName());
				}
				return 0;
			}
		});
	}
}
