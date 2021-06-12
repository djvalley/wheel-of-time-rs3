package net.wheeloftime.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import net.wheeloftime.cache.Cache;
import net.wheeloftime.cache.loaders.NPCDefinitions;
import net.wheeloftime.utils.Utils;

public class NPCListDumper {

	public static void main(String[] args) throws IOException {
		Cache.init();
		File file = new File("information/npcList876.txt");
		if (file.exists())
			file.delete();
		else
			file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.flush();
		for (int id = 0; id < Utils.getNPCDefinitionsSize(); id++) {
			NPCDefinitions def = NPCDefinitions.getNPCDefinitions(id);
			writer.append(id + " - " + def.name);
			writer.newLine();
			System.out.println(id + " - " + def.name);
			writer.flush();
		}
		writer.close();
	}

}