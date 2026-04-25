package io.bluestaggo.unused13restoration.mixin;

import net.minecraft.server.command.BookTestCommand;
import net.minecraft.server.command.Command;
import net.minecraft.server.command.handler.CommandManager;
import net.minecraft.server.command.handler.CommandRegistry;
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
		this.register(new BookTestCommand() {
			public int compareTo(@NotNull Object object) {
				if (object instanceof Command) {
					return this.getName().compareTo(((Command)object).getName());
				}
				return 0;
			}
		});
	}
}
