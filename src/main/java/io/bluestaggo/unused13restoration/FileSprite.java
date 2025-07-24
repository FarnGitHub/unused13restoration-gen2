package io.bluestaggo.unused13restoration;

import net.minecraft.client.render.texture.TextureAtlas;

import java.nio.ByteBuffer;

public class FileSprite extends TextureAtlas {
	public FileSprite(int sprite, int type, int[] colors) {
		super(sprite);
		this.type = type;

		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		byteBuffer.asIntBuffer().put(colors);
		this.buffer = byteBuffer.array();
	}
}
