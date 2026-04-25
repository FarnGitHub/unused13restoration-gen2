package io.bluestaggo.unused13restoration;

import net.minecraft.entity.SpawnerEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpawnerEntityWandItem extends Item {
	public SpawnerEntityWandItem(int id) {
		super(id);
		this.setMaxDamage(0);
		this.setMaxStackSize(1);
	}

	@Override
	public ItemStack startUsing(ItemStack stack, World world, PlayerEntity player) {
		if (world.isMultiplayer) {
			return stack;
		}

		int delay = stack.getDamage();
		if (player.isSneaking()) {
			delay -= 20;
			if (delay < 20) {
				delay = 20;
			}
		} else {
			if (delay < 32000) {
				delay += 20;
			}
		}

		stack.setDamage(delay);
		player.sendMessage("Set delay to " + (delay / 20) + (delay == 20 ? " second" : " seconds"));
		return stack;
	}

	@Override
	public boolean interact(ItemStack stack, MobEntity entity) {
		if (entity.world.isMultiplayer) {
			return true;
		}

		if (stack.getDamage() < 20) {
			stack.setDamage(20);
		}

		SpawnerEntity delayEntity = new SpawnerEntity(entity.world, entity, stack.getDamage());
		entity.world.removeEntityNow(entity);
		entity.removed = false;
		entity.world.addEntity(delayEntity);
		return true;
	}

	@Override
	public boolean hasEnchantmentGlint(ItemStack stack) {
		return true;
	}
}
