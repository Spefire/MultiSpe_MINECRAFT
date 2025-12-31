package fr.spefire.multispe.models;

import java.util.List;

public class Spe {
	private final SpeType type;
	private final String defaultFr;
	private final String defaultEn;
	private String nameFr;
	private String nameEn;

	public Spe(SpeType type, String fr, String en) {
		this.type = type;
		this.defaultFr = fr;
		this.defaultEn = en;
		this.nameFr = fr;
		this.nameEn = en;
	}

	public String getId() {
		return type.toString();
	}

	public SpeType getType() {
		return type;
	}

	public String getDefaultFr() {
		return defaultFr;
	}

	public String getDefaultEn() {
		return defaultEn;
	}

	public String getNameFr() {
		return nameFr;
	}

	public void setNameFr(String name) {
		this.nameFr = name;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String name) {
		this.nameEn = name;
	}

	public static List<Spe> getAllSpes() {
		List<Spe> spes = List.of(
				// --- SPES ---
				new Spe(SpeType.WAR, "Guerrier", "Warrior"), new Spe(SpeType.ARC, "Archer", "Archer"),
				new Spe(SpeType.PRI, "Prêtre", "Priest"), new Spe(SpeType.WIZ, "Sorcier", "Wizard"),
				new Spe(SpeType.VAM, "Vampire", "Vampire"), new Spe(SpeType.NEC, "Nécromancien", "Necromancer"),
				new Spe(SpeType.DRU, "Druide", "Druid"), new Spe(SpeType.BUT, "Boucher", "Butcher"),
				new Spe(SpeType.ASS, "Assassin", "Assassin"));
		return spes;
	}
}
