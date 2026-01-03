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
			new Skill("WAR_01", "Coup Violent", "Weakness Blow", SkillType.ATK),
			new Skill("WAR_02", "Tranché Explosif", "Explosive Slash", SkillType.ATK),
			new Skill("ARC_01", "Flèche Confuse", "Confusing Arrow", SkillType.ATK),
			new Skill("ARC_02", "Flèche de Foudre", "Lightning Arrow", SkillType.ATK),
			new Skill("ARC_03", "??? Flèche de Glace", "Lightning Arrow", SkillType.ATK),
			new Skill("ARC_04", "Flèche Explosive", "Explosive Arrow", SkillType.ATK),
			new Skill("DRU_01", "Jugement Dernier", "Last Judgment", SkillType.ATK),
			new Skill("NEC_01", "Faucheuse Obscure", "Dark Reaper", SkillType.ATK),
			new Skill("NEC_02", "Drain Vampirique", "Vampire Kiss", SkillType.ATK),

			// --- DEF ---
			new Skill("WAR_03", "Berserker", "Berserker", SkillType.DEF),
			new Skill("WAR_04", "Tank", "Tanker", SkillType.DEF),
			new Skill("PRI_01", "Miniaturisation", "Mini", SkillType.ZONE),
			new Skill("DRU_02", "Titan", "Giant", SkillType.DEF),
			new Skill("DRU_03", "Élémentaire", "Elementary", SkillType.DEF),
			new Skill("DRU_04", "Vol du Lapin", "Bunny Flight", SkillType.DEF),

			// --- ZONE ---
			new Skill("PRI_02", "??? Soin", "Heal", SkillType.ZONE),
			new Skill("PRI_03", "??? Prière", "Prayer", SkillType.ZONE),
			new Skill("PRI_04", "??? Dissipation", "Dispel", SkillType.ZONE),
			new Skill("WIZ_01", "Prison Temporelle", "Temporal Prison", SkillType.ZONE),
			new Skill("WIZ_02", "Orage", "Storm", SkillType.ZONE),
			new Skill("WIZ_03", "Embrasement", "Blaze", SkillType.ZONE),
			new Skill("WIZ_04", "Nuage de fumée", "Smoke", SkillType.ZONE),
			new Skill("NEC_03", "??? Réveil des morts", "Awakening of the dead", SkillType.ZONE),
			new Skill("NEC_04", "??? Malédiction", "Curse", SkillType.ZONE));

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
