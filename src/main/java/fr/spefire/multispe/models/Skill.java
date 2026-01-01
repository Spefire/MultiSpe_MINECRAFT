package fr.spefire.multispe.models;

import java.util.List;

public class Skill {
	private final String id;
	private final SkillType type;
	private final String defaultFr;
	private final String defaultEn;
	private String nameFr;
	private String nameEn;

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
		List<Skill> skills = List.of(
				// --- PASS ---
				new Skill("NULL", "Aucune capacité", "No ability", SkillType.PASS),

				// --- ATK ---
				new Skill("01", "Coup Puissant", "Powerful Blow", SkillType.ATK),
				new Skill("02", "Tranché Explosif", "Explosive Slash", SkillType.ATK),
				new Skill("03", "Renvoi", "Counter", SkillType.ATK),
				new Skill("04", "Faucheuse Obscure", "Dark Reaper", SkillType.ATK),
				new Skill("05", "Baiser Vampirique", "Vampire Kiss", SkillType.ATK),
				new Skill("06", "Jugement Dernier", "Last Judgment", SkillType.ATK),
				new Skill("07", "Flèche Confuse", "Confusing Arrow", SkillType.ATK),
				new Skill("08", "Flèche de Foudre", "Lightning Arrow", SkillType.ATK),
				new Skill("09", "Flèche Dégénérative", "Degenerating Arrow", SkillType.ATK),
				new Skill("10", "Flèche Explosive", "Explosive Arrow", SkillType.ATK),
				new Skill("11", "Coup Étourdissant", "Stunning Strike", SkillType.ATK),
				new Skill("12", "Vol de Vie", "Life Steal", SkillType.ATK),
				new Skill("13", "Lame Empoisonnée", "Poisoned Blade", SkillType.ATK),
				new Skill("14", "Coup Traître", "Treacherous Strike", SkillType.ATK),

				// --- DEF ---
				new Skill("01", "Saut Titanesque", "Titanic Leap", SkillType.DEF),
				new Skill("02", "Vol du Jaguar", "Jaguar Flight", SkillType.DEF),
				new Skill("03", "Berserker", "Berserker", SkillType.DEF),
				new Skill("04", "Saut Éclair", "Flash Jump", SkillType.DEF),
				new Skill("05", "Chauve-souris", "Bat Form", SkillType.DEF),
				new Skill("06", "Rage", "Rage", SkillType.DEF), new Skill("07", "Dopage", "Doping", SkillType.DEF),
				new Skill("08", "Invisibilité", "Invisibility", SkillType.DEF),

				// --- ZONE ---
				new Skill("01", "Êtres Nocturnes", "Nocturnal Beings", SkillType.ZONE),
				new Skill("02", "Prison Temporelle", "Temporal Prison", SkillType.ZONE),
				new Skill("03", "Orage", "Storm", SkillType.ZONE),
				new Skill("04", "Embrasement", "Blaze", SkillType.ZONE),
				new Skill("05", "Drainage", "Drain", SkillType.ZONE),
				new Skill("06", "Révélation", "Revelation", SkillType.ZONE),
				new Skill("07", "Implosion", "Implosion", SkillType.ZONE),

				// --- VIEW ---
				new Skill("01", "Soin", "Heal", SkillType.VIEW), new Skill("02", "Prière", "Prayer", SkillType.VIEW),
				new Skill("03", "Dissipation", "Dispel", SkillType.VIEW),
				new Skill("04", "Luminos", "Luminos", SkillType.VIEW),
				new Skill("05", "Malédiction", "Curse", SkillType.VIEW),
				new Skill("06", "Communion Élémentaire", "Elemental Communion", SkillType.VIEW),
				new Skill("07", "Traque", "Hunt", SkillType.VIEW));
		return skills;
	}

	public static Skill getSkill(SkillType type, String id) {
		return Skill.getAllSkills().stream().filter(s -> s.getType() == type && s.getId().equals(id)).findFirst()
				.orElse(null);
	}
}
