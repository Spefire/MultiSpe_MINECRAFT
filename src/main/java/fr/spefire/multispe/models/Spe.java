package fr.spefire.multispe.models;

import java.util.List;

public class Spe {
	private final SpeCode code;
	private final String defaultFr;
	private final String defaultEn;
	private String nameFr;
	private String nameEn;
	private List<Skill> skills;

	private static final List<Skill> SKILLS_WAR = List.of(Skill.getSkill("XXX_00"), Skill.getSkill("WAR_01"),
			Skill.getSkill("WAR_02"), Skill.getSkill("WAR_03"));

	private static final List<Skill> SKILLS_ARC = List.of(Skill.getSkill("XXX_00"), Skill.getSkill("ARC_01"),
			Skill.getSkill("ARC_02"), Skill.getSkill("ARC_03"));

	private static final List<Skill> SKILLS_PRI = List.of(Skill.getSkill("XXX_00"), Skill.getSkill("PRI_01"),
			Skill.getSkill("PRI_02"), Skill.getSkill("PRI_03"));

	private static final List<Skill> SKILLS_WIZ = List.of(Skill.getSkill("XXX_00"), Skill.getSkill("WIZ_01"),
			Skill.getSkill("WIZ_02"), Skill.getSkill("WIZ_03"));

	private static final List<Skill> SKILLS_NEC = List.of(Skill.getSkill("XXX_00"), Skill.getSkill("NEC_01"),
			Skill.getSkill("NEC_02"), Skill.getSkill("NEC_03"));

	private static final List<Skill> SKILLS_VAM = List.of(Skill.getSkill("XXX_00"), Skill.getSkill("VAM_01"),
			Skill.getSkill("VAM_02"), Skill.getSkill("VAM_03"));

	private static final List<Skill> SKILLS_DRU = List.of(Skill.getSkill("XXX_00"), Skill.getSkill("DRU_01"),
			Skill.getSkill("DRU_02"), Skill.getSkill("DRU_03"));

	private static final List<Skill> SKILLS_BUT = List.of(Skill.getSkill("XXX_00"), Skill.getSkill("BUT_01"),
			Skill.getSkill("BUT_02"), Skill.getSkill("BUT_03"));

	private static final List<Skill> SKILLS_ASS = List.of(Skill.getSkill("XXX_00"), Skill.getSkill("ASS_01"),
			Skill.getSkill("ASS_02"), Skill.getSkill("ASS_03"));

	private static final List<Spe> ALL_SPES = List.of(new Spe(SpeCode.WAR, "Guerrier", "Warrior", SKILLS_WAR),
			new Spe(SpeCode.ARC, "Archer", "Archer", SKILLS_ARC), new Spe(SpeCode.PRI, "Prêtre", "Priest", SKILLS_PRI),
			new Spe(SpeCode.WIZ, "Sorcier", "Wizard", SKILLS_WIZ),
			new Spe(SpeCode.VAM, "Vampire", "Vampire", SKILLS_VAM),
			new Spe(SpeCode.NEC, "Nécromancien", "Necromancer", SKILLS_NEC),
			new Spe(SpeCode.DRU, "Druide", "Druid", SKILLS_DRU), new Spe(SpeCode.BUT, "Boucher", "Butcher", SKILLS_BUT),
			new Spe(SpeCode.ASS, "Assassin", "Assassin", SKILLS_ASS));

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
		return ALL_SPES;
	}

	public static Spe getSpeById(String id) {
		return ALL_SPES.stream().filter(s -> id.equals(s.getId())).findFirst().orElse(null);
	}
}
