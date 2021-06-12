package net.wheeloftime.tools;

import java.io.IOException;

import net.wheeloftime.cache.Cache;
import net.wheeloftime.cache.loaders.NPCDefinitions;
import net.wheeloftime.utils.Utils;

public class NPCCheck {

	public static void main(String[] args) throws IOException {
		Cache.init();
		for (int id = 0; id < Utils.getNPCDefinitionsSize(); id++) {
			NPCDefinitions def = NPCDefinitions.getNPCDefinitions(id);
			if (def.name.contains("Elemental")) {
				System.out.println(id + " - " + def.name);
			}
		}
	}
}