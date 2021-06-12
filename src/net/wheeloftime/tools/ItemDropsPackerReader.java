package net.wheeloftime.tools;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import net.wheeloftime.cache.Cache;

public class ItemDropsPackerReader {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		Cache.init();
		new DataInputStream(new FileInputStream("data/npcs/packedDrops.d"));

	}
}