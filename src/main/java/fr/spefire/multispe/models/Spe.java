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
		List<Skill> skillsWAR = List.of(Skill.getSkill(SkillType.PASS, "NULL"), Skill.getSkill(SkillType.PASS, "NULL"),
				Skill.getSkill(SkillType.PASS, "NULL"), Skill.getSkill(SkillType.PASS, "NULL"),
				Skill.getSkill(SkillType.PASS, "NULL"));
		List<Skill> skillsARC = List.of(Skill.getSkill(SkillType.PASS, "NULL"), Skill.getSkill(SkillType.PASS, "NULL"),
				Skill.getSkill(SkillType.PASS, "NULL"), Skill.getSkill(SkillType.PASS, "NULL"),
				Skill.getSkill(SkillType.PASS, "NULL"));
		List<Skill> skillsPRI = List.of(Skill.getSkill(SkillType.PASS, "NULL"), Skill.getSkill(SkillType.PASS, "NULL"),
				Skill.getSkill(SkillType.PASS, "NULL"), Skill.getSkill(SkillType.PASS, "NULL"),
				Skill.getSkill(SkillType.PASS, "NULL"));
		List<Skill> skillsWIZ = List.of(Skill.getSkill(SkillType.PASS, "NULL"), Skill.getSkill(SkillType.PASS, "NULL"),
				Skill.getSkill(SkillType.PASS, "NULL"), Skill.getSkill(SkillType.PASS, "NULL"),
				Skill.getSkill(SkillType.PASS, "NULL"));
		List<Skill> skillsNEC = List.of(Skill.getSkill(SkillType.PASS, "NULL"), Skill.getSkill(SkillType.PASS, "NULL"),
				Skill.getSkill(SkillType.PASS, "NULL"), Skill.getSkill(SkillType.PASS, "NULL"),
				Skill.getSkill(SkillType.PASS, "NULL"));
		List<Skill> skillsVAM = List.of(Skill.getSkill(SkillType.PASS, "NULL"), Skill.getSkill(SkillType.PASS, "NULL"),
				Skill.getSkill(SkillType.PASS, "NULL"), Skill.getSkill(SkillType.PASS, "NULL"),
				Skill.getSkill(SkillType.PASS, "NULL"));
		List<Skill> skillsDRU = List.of(Skill.getSkill(SkillType.PASS, "NULL"), Skill.getSkill(SkillType.PASS, "NULL"),
				Skill.getSkill(SkillType.PASS, "NULL"), Skill.getSkill(SkillType.PASS, "NULL"),
				Skill.getSkill(SkillType.PASS, "NULL"));
		List<Skill> skillsBUT = List.of(Skill.getSkill(SkillType.PASS, "NULL"), Skill.getSkill(SkillType.PASS, "NULL"),
				Skill.getSkill(SkillType.PASS, "NULL"), Skill.getSkill(SkillType.PASS, "NULL"),
				Skill.getSkill(SkillType.PASS, "NULL"));
		List<Skill> skillsASS = List.of(Skill.getSkill(SkillType.PASS, "NULL"), Skill.getSkill(SkillType.PASS, "NULL"),
				Skill.getSkill(SkillType.PASS, "NULL"), Skill.getSkill(SkillType.PASS, "NULL"),
				Skill.getSkill(SkillType.PASS, "NULL"));
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
