package fr.spefire.multispe.models;

import java.util.List;

public class Skill {
	private final String id;
	private final SkillType type;
	private final String defaultFr;
	private final String defaultEn;
	private String nameFr;
	private String nameEn;

	private static final List<Skill> ALL_SKILLS = List.of(
			// --- PASS ---
			new Skill("XXX_00", "Aucune capacité", "No ability", SkillType.PASS),

			// --- ATK ---
			new Skill("WAR_01", "Coup Puissant", "Powerful Blow", SkillType.ATK),
			new Skill("WAR_02", "Tranché Explosif", "Explosive Slash", SkillType.ATK),
			new Skill("ARC_01", "Flèche Confuse", "Confusing Arrow", SkillType.ATK),
			new Skill("ARC_02", "Flèche de Foudre", "Lightning Arrow", SkillType.ATK),
			new Skill("ARC_03", "Flèche Explosive", "Explosive Arrow", SkillType.ATK),
			new Skill("NEC_01", "Faucheuse Obscure", "Dark Reaper", SkillType.ATK),
			new Skill("VAM_01", "Baiser Vampirique", "Vampire Kiss", SkillType.ATK),
			new Skill("DRU_01", "Jugement Dernier", "Last Judgment", SkillType.ATK),
			new Skill("BUT_01", "Coup Étourdissant", "Stunning Strike", SkillType.ATK),
			new Skill("BUT_02", "Vol de Vie", "Life Steal", SkillType.ATK),
			new Skill("ASS_01", "Lame Empoisonnée", "Poisoned Blade", SkillType.ATK),
			new Skill("ASS_02", "Coup Traître", "Treacherous Strike", SkillType.ATK),

			// --- DEF ---
			new Skill("WAR_03", "Berserker", "Berserker", SkillType.DEF),
			new Skill("VAM_02", "Chauve-souris", "Bat Form", SkillType.DEF),
			new Skill("DRU_02", "Saut Titanesque", "Titanic Leap", SkillType.DEF),
			new Skill("DRU_03", "Vol du Jaguar", "Jaguar Flight", SkillType.DEF),
			new Skill("BUT_03", "Rage", "Rage", SkillType.DEF), new Skill("BUT_04", "Dopage", "Doping", SkillType.DEF),
			new Skill("ASS_03", "Invisibilité", "Invisibility", SkillType.DEF),

			// --- ZONE ---
			new Skill("WIZ_01", "Prison Temporelle", "Temporal Prison", SkillType.ZONE),
			new Skill("WIZ_02", "Orage", "Storm", SkillType.ZONE),
			new Skill("WIZ_03", "Embrasement", "Blaze", SkillType.ZONE),
			new Skill("VAM_03", "Êtres Nocturnes", "Nocturnal Beings", SkillType.ZONE),
			new Skill("NEC_02", "Réveil des morts", "Awakening of the dead", SkillType.ZONE),

			// --- VIEW ---
			new Skill("PRI_01", "Soin", "Heal", SkillType.VIEW),
			new Skill("PRI_02", "Prière", "Prayer", SkillType.VIEW),
			new Skill("PRI_03", "Dissipation", "Dispel", SkillType.VIEW),
			new Skill("NEC_03", "Malédiction", "Curse", SkillType.VIEW),
			new Skill("DRU_04", "Communion Élémentaire", "Elemental Communion", SkillType.VIEW),
			new Skill("ASS_04", "Traque", "Hunt", SkillType.VIEW));

	public Skill(String id, String fr, String en, SkillType type) {
		this.id = id;
		this.type = type;
		this.defaultFr = fr;
		this.defaultEn = en;
		this.nameFr = fr;
		this.nameEn = en;
	}

	public String getId() {
		return id;
	}

	public SkillType getType() {
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

	public static List<Skill> getAllSkills() {
		return ALL_SKILLS;
	}

	public static Skill getSkill(String id) {
		return ALL_SKILLS.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
	}
}
