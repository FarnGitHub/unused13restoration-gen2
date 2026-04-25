package io.bluestaggo.unused13restoration;

import net.minecraft.client.render.texture.DynamicTexture;

import java.nio.ByteBuffer;

public class FileSprite extends DynamicTexture {
	public FileSprite(int sprite, int type, int[] colors) {
		super(sprite);
		this.atlas = type;

		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		byteBuffer.asIntBuffer().put(colors);
		this.pixels = byteBuffer.array();
	}
}
