package fr.spefire.multispe.models;

import java.util.List;

public class Message {
	private final String key;
	private final String fr;
	private final String en;

	private static final List<Message> ALL_MESSAGES = List.of(new Message("touse", "Vous utilisez ", "You use "),
			new Message("tochange", " est devenu ", " became "), new Message("tobe", "Vous êtes ", "You are "),
			new Message("toclass", "Maintenant vous êtes ", "Now you are "),
			new Message("tonoclass", "Désormais vous n'avez plus de classe", "Now you no longer have a class"),
			new Message("nopermission", "Vous n'avez pas la permission", "You don't have the permission"),
			new Message("nopermissiontoclass", "Vous n'avez pas la permission de devenir ",
					"You don't have the permission to become "),
			new Message("noclass", "Vous n'avez pas de classe", "You don't have a class"),
			new Message("alreadyclass", "Vous avez déjà une classe !", "You already have a class!"),
			new Message("reloaded", "Chargé !", "Recharged!"),
			new Message("warningclass", "Attention, vous ne pourrez plus changer de classe",
					"Attention, you won't be able to change your class"),
			new Message("worldtrue", "Dans le monde dans lequel vous êtes, le plugin est actif",
					"In this world, the plugin is enabled"),
			new Message("worldfalse", "Dans le monde dans lequel vous êtes, le plugin est inactif",
					"In this world, the plugin is disabled"),
			new Message("locationtrue", "Là où vous êtes situé, le plugin est actif",
					"At your location, the plugin is enabled"),
			new Message("locationfalse", "Là où vous êtes situé, le plugin est inactif",
					"At your location, the plugin is disabled"));

	public Message(String key, String fr, String en) {
		this.key = key;
		this.fr = fr;
		this.en = en;
	}

	public String getKey() {
		return key;
	}

	public String getFr() {
		return fr;
	}

	public String getEn() {
		return en;
	}

	public static List<Message> getAllMessages() {
		return ALL_MESSAGES;
	}
}
