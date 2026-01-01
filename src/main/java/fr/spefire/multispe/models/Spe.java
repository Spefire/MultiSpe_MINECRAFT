package fr.spefire.multispe.models;

import java.util.List;

public class Spe {
	private final SpeCode code;
	private final String defaultFr;
	private final String defaultEn;
	private String nameFr;
	private String nameEn;
	private List<Skill> skills;

	public Spe(SpeCode code, String fr, String en, List<Skill> skills) {
		this.code = code;
		this.defaultFr = fr;
		this.defaultEn = en;
		this.nameFr = fr;
		this.nameEn = en;
		this.skills = skills;
	}

	public String getId() {
		return code.toString();
	}

	public SpeCode getCode() {
		return code;
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
				Skill.getSkill("XXX_00"), //
				Skill.getSkill("WAR_01"), //
				Skill.getSkill("WAR_02"), //
				Skill.getSkill("WAR_03"), //
				Skill.getSkill("WAR_04"));
		List<Skill> skillsARC = List.of( //
				Skill.getSkill("XXX_00"), //
				Skill.getSkill("ARC_01"), //
				Skill.getSkill("ARC_02"), //
				Skill.getSkill("ARC_03"), //
				Skill.getSkill("ARC_04"));
		List<Skill> skillsPRI = List.of( //
				Skill.getSkill("XXX_00"), //
				Skill.getSkill("PRI_01"), //
				Skill.getSkill("PRI_02"), //
				Skill.getSkill("PRI_03"), //
				Skill.getSkill("PRI_04"));
		List<Skill> skillsWIZ = List.of( //
				Skill.getSkill("XXX_00"), //
				Skill.getSkill("WIZ_01"), //
				Skill.getSkill("WIZ_02"), //
				Skill.getSkill("WIZ_03"), //
				Skill.getSkill("WIZ_04"));
		List<Skill> skillsNEC = List.of( //
				Skill.getSkill("XXX_00"), //
				Skill.getSkill("NEC_01"), //
				Skill.getSkill("NEC_02"), //
				Skill.getSkill("NEC_03"), //
				Skill.getSkill("NEC_04"));
		List<Skill> skillsVAM = List.of( //
				Skill.getSkill("XXX_00"), //
				Skill.getSkill("VAM_01"), //
				Skill.getSkill("VAM_02"), //
				Skill.getSkill("VAM_03"), //
				Skill.getSkill("VAM_04"));
		List<Skill> skillsDRU = List.of( //
				Skill.getSkill("XXX_00"), //
				Skill.getSkill("DRU_01"), //
				Skill.getSkill("DRU_02"), //
				Skill.getSkill("DRU_03"), //
				Skill.getSkill("DRU_04"));
		List<Skill> skillsBUT = List.of( //
				Skill.getSkill("XXX_00"), //
				Skill.getSkill("BUT_01"), //
				Skill.getSkill("BUT_02"), //
				Skill.getSkill("BUT_03"), //
				Skill.getSkill("BUT_04"));
		List<Skill> skillsASS = List.of( //
				Skill.getSkill("XXX_00"), //
				Skill.getSkill("ASS_01"), //
				Skill.getSkill("ASS_02"), //
				Skill.getSkill("ASS_03"), //
				Skill.getSkill("ASS_04"));
		List<Spe> spes = List.of(
				// --- SPES ---
				new Spe(SpeCode.WAR, "Guerrier", "Warrior", skillsWAR),
				new Spe(SpeCode.ARC, "Archer", "Archer", skillsARC),
				new Spe(SpeCode.PRI, "Prêtre", "Priest", skillsPRI),
				new Spe(SpeCode.WIZ, "Sorcier", "Wizard", skillsWIZ),
				new Spe(SpeCode.VAM, "Vampire", "Vampire", skillsVAM),
				new Spe(SpeCode.NEC, "Nécromancien", "Necromancer", skillsNEC),
				new Spe(SpeCode.DRU, "Druide", "Druid", skillsDRU),
				new Spe(SpeCode.BUT, "Boucher", "Butcher", skillsBUT),
				new Spe(SpeCode.ASS, "Assassin", "Assassin", skillsASS));
		return spes;
	}
}
