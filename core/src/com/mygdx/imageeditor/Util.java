package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

public class Util {
	public static int bytesToInt(byte[] bytes) {
		int result = 0;
		int[] ints = unsignBytes(bytes);
		for(int i = 0; i <ints.length; i++) {
			result += (int) ints[i] << (8 * i);
		}
		return result;
	}
	
	public static int[] unsignBytes(byte[] bytes) {
		int[] ints = new int[bytes.length];
		for(int i  = 0; i < bytes.length; i++) {
			if(bytes[i] >= 0) ints[i] = bytes[i];
			else ints[i] = ((bytes[i] + 129) + 127);
		}
		return ints;
	}
	
	public static byte[] intToSignedBytes(int value) {
		byte[] result = new byte[4];
		for(int i = 0; i <4; i++) {result[i] = (byte) ((value << 8*i) >> 24);}
		return result;
	}
	
	public static Pixmap scalePixmap(Pixmap source, Vector2 desiredSize) {
		Pixmap target = new Pixmap((int) desiredSize.x, (int) desiredSize.y, Pixmap.Format.RGBA8888);
		for(int x = 0; x < target.getWidth(); x++) {
			for(int y = 0; y < target.getHeight(); y ++) {
				target.drawPixel(x, y, 
						source.getPixel((int)Math.round(x/desiredSize.x * source.getWidth()),
								(int)Math.round(y/desiredSize.y * source.getHeight())
						)
				);
			}
		}
		return target;
	}
}
