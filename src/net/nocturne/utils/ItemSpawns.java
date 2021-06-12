package net.nocturne.utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

import net.nocturne.game.World;
import net.nocturne.game.WorldTile;
import net.nocturne.game.item.Item;

public final class ItemSpawns {

	public static final void init() {
		if (!new File("data/items/packedSpawns").exists())
			packItemSpawns();
	}

	private static final void packItemSpawns() {
		Logger.log("ItemSpawns", "Packing item spawns...");
		if (!new File("data/items/packedSpawns").mkdir())
			throw new RuntimeException(
					"Couldn't create packedSpawns directory.");
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"data/items/unpackedSpawnsList.txt"));
			while (true) {
				String line = in.readLine();
				if (line == null)
					break;
				if (line.startsWith("//"))
					continue;
				String[] splitedLine = line.split(" - ", 2);
				if (splitedLine.length != 2) {
					in.close();
					throw new RuntimeException("Invalid generated item line: "
							+ line + ", " + splitedLine.length);
				}
				int itemId = Integer.parseInt(splitedLine[0]);
				String[] splitedLine2 = splitedLine[1].split(" ", 3);
				if (splitedLine2.length != 3) {
					in.close();
					throw new RuntimeException("Invalid generated item line: "
							+ line);
				}
				WorldTile tile = new WorldTile(
						Integer.parseInt(splitedLine2[0]),
						Integer.parseInt(splitedLine2[1]),
						Integer.parseInt(splitedLine2[2]));
				addItemSpawn(itemId, tile.getRegionId(), tile);
			}
			in.close();
		} catch (Throwable e) {
			Logger.handle(e);
		}
	}

	@SuppressWarnings("deprecation")
	public static final void loadItemSpawns(int regionId) {
		File file = new File("data/items/packedSpawns/" + regionId + ".is");
		if (!file.exists())
			return;
		try {
			RandomAccessFile in = new RandomAccessFile(file, "r");
			FileChannel channel = in.getChannel();
			ByteBuffer buffer = channel.map(MapMode.READ_ONLY, 0,
					channel.size());
			while (buffer.hasRemaining()) {
				int itemId = buffer.getShort() & 0xffff;
				int plane = buffer.get() & 0xff;
				int x = buffer.getShort() & 0xffff;
				int y = buffer.getShort() & 0xffff;
				World.addGroundItemForever(new Item(itemId, 1), new WorldTile(
						x, y, plane));
			}
			channel.close();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static final void addItemSpawn(int itemId, int regionId,
			WorldTile tile) {
		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(
					"data/items/packedSpawns/" + regionId + ".is", true));
			out.writeShort(itemId);
			out.writeByte(tile.getPlane());
			out.writeShort(tile.getX());
			out.writeShort(tile.getY());
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ItemSpawns() {
	}
}