package io.bluestaggo.unused13restoration.mixin;

import net.minecraft.entity.Entities;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(Entities.class)
public interface EntitiesAccessor {
	@Accessor("KEY_TO_TYPE") static Map<String, Class<? extends Entity>> getKeyToType() {
		throw new IllegalStateException();
	}
}
