package fr.spefire.multispe.models;

import java.util.List;

public class Spe {
	private final SpeType type;
	private final String defaultFr;
	private final String defaultEn;
	private String nameFr;
	private String nameEn;
	private List<Skill> skills;

	public Spe(SpeType type, String fr, String en, List<Skill> skills) {
		this.type = type;
		this.defaultFr = fr;
		this.defaultEn = en;
		this.nameFr = fr;
		this.nameEn = en;
		this.skills = skills;
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

	public Skill getSkill(Integer index) {
		return this.skills.get(index);
	}

	public static List<Spe> getAllSpes() {
		List<Skill> skillsWAR = List.of( //
				Skill.getSkill("NULL"), //
				Skill.getSkill("WAR_01"), //
				Skill.getSkill("WAR_02"), //
				Skill.getSkill("WAR_03"), //
				Skill.getSkill("WAR_04"));
		List<Skill> skillsARC = List.of( //
				Skill.getSkill("NULL"), //
				Skill.getSkill("ARC_01"), //
				Skill.getSkill("ARC_02"), //
				Skill.getSkill("ARC_03"), //
				Skill.getSkill("ARC_04"));
		List<Skill> skillsPRI = List.of( //
				Skill.getSkill("NULL"), //
				Skill.getSkill("PRI_01"), //
				Skill.getSkill("PRI_02"), //
				Skill.getSkill("PRI_03"), //
				Skill.getSkill("PRI_04"));
		List<Skill> skillsWIZ = List.of( //
				Skill.getSkill("NULL"), //
				Skill.getSkill("WIZ_01"), //
				Skill.getSkill("WIZ_02"), //
				Skill.getSkill("WIZ_03"), //
				Skill.getSkill("WIZ_04"));
		List<Skill> skillsNEC = List.of( //
				Skill.getSkill("NULL"), //
				Skill.getSkill("NEC_01"), //
				Skill.getSkill("NEC_02"), //
				Skill.getSkill("NEC_03"), //
				Skill.getSkill("NEC_04"));
		List<Skill> skillsVAM = List.of( //
				Skill.getSkill("NULL"), //
				Skill.getSkill("VAM_01"), //
				Skill.getSkill("VAM_02"), //
				Skill.getSkill("VAM_03"), //
				Skill.getSkill("VAM_04"));
		List<Skill> skillsDRU = List.of( //
				Skill.getSkill("NULL"), //
				Skill.getSkill("DRU_01"), //
				Skill.getSkill("DRU_02"), //
				Skill.getSkill("DRU_03"), //
				Skill.getSkill("DRU_04"));
		List<Skill> skillsBUT = List.of( //
				Skill.getSkill("NULL"), //
				Skill.getSkill("BUT_01"), //
				Skill.getSkill("BUT_02"), //
				Skill.getSkill("BUT_03"), //
				Skill.getSkill("BUT_04"));
		List<Skill> skillsASS = List.of( //
				Skill.getSkill("NULL"), //
				Skill.getSkill("ASS_01"), //
				Skill.getSkill("ASS_02"), //
				Skill.getSkill("ASS_03"), //
				Skill.getSkill("ASS_04"));
		List<Spe> spes = List.of(
				// --- SPES ---
				new Spe(SpeType.WAR, "Guerrier", "Warrior", skillsWAR),
				new Spe(SpeType.ARC, "Archer", "Archer", skillsARC),
				new Spe(SpeType.PRI, "Prêtre", "Priest", skillsPRI),
				new Spe(SpeType.WIZ, "Sorcier", "Wizard", skillsWIZ),
				new Spe(SpeType.VAM, "Vampire", "Vampire", skillsVAM),
				new Spe(SpeType.NEC, "Nécromancien", "Necromancer", skillsNEC),
				new Spe(SpeType.DRU, "Druide", "Druid", skillsDRU),
				new Spe(SpeType.BUT, "Boucher", "Butcher", skillsBUT),
				new Spe(SpeType.ASS, "Assassin", "Assassin", skillsASS));
		return spes;
	}
}
