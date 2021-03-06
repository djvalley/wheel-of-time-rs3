package net.wheeloftime.game.player.cutscenes;

import java.util.HashMap;

import net.wheeloftime.utils.Logger;

public class CutscenesHandler {

	private static final HashMap<Object, Class<? extends Cutscene>> handledCutscenes = new HashMap<Object, Class<? extends Cutscene>>();

	@SuppressWarnings("unchecked")
	public static final void init() {
		try {
			Class<Cutscene> value2 = (Class<Cutscene>) Class
					.forName(DTPreview.class.getCanonicalName());
			handledCutscenes.put("DTPreview", value2);
			Class<Cutscene> value3 = (Class<Cutscene>) Class
					.forName(NexCutScene.class.getCanonicalName());
			handledCutscenes.put("NexCutScene", value3);
			Class<Cutscene> value5 = (Class<Cutscene>) Class
					.forName(HomeCutScene.class.getCanonicalName());
			handledCutscenes.put("HomeCutScene", value5);
			Class<Cutscene> value6 = (Class<Cutscene>) Class
					.forName(MasterOfFear.class.getCanonicalName());
			handledCutscenes.put("MasterOfFear", value6);
			Class<Cutscene> value7 = (Class<Cutscene>) Class
					.forName(TrollCutscene.class.getCanonicalName());
			handledCutscenes.put("TrollCutscene", value7);
			handledCutscenes.put("HomeCutScene3", HomeCutScene3.class);
			handledCutscenes.put("DZGuideScene", DZGuideScene.class);
			handledCutscenes.put("CorporealBeastScene",
					CorporealBeastScene.class);
		} catch (Throwable e) {
			Logger.handle(e);
		}
	}

	public static final void reload() {
		handledCutscenes.clear();
		init();
	}

	public static final Cutscene getCutscene(Object key) {
		Class<? extends Cutscene> classC = handledCutscenes.get(key);
		if (classC == null)
			return null;
		try {
			return classC.newInstance();
		} catch (Throwable e) {
			Logger.handle(e);
		}
		return null;
	}
}
