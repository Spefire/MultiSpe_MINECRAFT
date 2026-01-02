package fr.spefire.multispe;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.spefire.multispe.actions.AtkSkills;
import fr.spefire.multispe.actions.DefSkills;
import fr.spefire.multispe.actions.ZoneSkills;
import fr.spefire.multispe.commands.ConsoleCommands;
import fr.spefire.multispe.commands.PlayerCommands;
import fr.spefire.multispe.commands.SelectionCommands;
import fr.spefire.multispe.models.Language;
import fr.spefire.multispe.models.Message;
import fr.spefire.multispe.models.Skill;
import fr.spefire.multispe.models.Spe;

public class MultiSpe extends JavaPlugin {

	private final MultiSpe plugin;
	private ConsoleCommands consoleCmds;
	private PlayerCommands playerCmds;
	private SelectionCommands selectionCmds;
	private AtkSkills atkSkills;
	private DefSkills defSkills;
	private ZoneSkills zoneSkills;

	public MultiSpe() {
		plugin = this;
	}

	public void onEnable() {
		System.out.println("[MultiSpe] Plugin connected");

		PluginManager pm = getServer().getPluginManager();
		consoleCmds = new ConsoleCommands(this);
		pm.registerEvents(consoleCmds, this);
		playerCmds = new PlayerCommands(this);
		pm.registerEvents(playerCmds, this);
		selectionCmds = new SelectionCommands(this);
		pm.registerEvents(selectionCmds, this);
		atkSkills = new AtkSkills(this);
		pm.registerEvents(atkSkills, this);
		defSkills = new DefSkills(this);
		pm.registerEvents(defSkills, this);
		zoneSkills = new ZoneSkills(this);
		pm.registerEvents(zoneSkills, this);
		plugin.saveConfig();

		// ----------------------------------------------------------------------------------------------------------------------
		try {
			File f = new File("plugins/MultiSpe/config.yml");
			if (f.length() == 0) {
				List<Spe> spes = Spe.getAllSpes();
				String idsAsString = spes.stream().map(Spe::getId).collect(Collectors.joining(", ", "[", "]"));
				FileConfiguration pluginConfig = plugin.getConfig();
				pluginConfig.set("language", Language.EN.toString());
				pluginConfig.set("tchat", true);
				pluginConfig.set("cooldown", 6);
				pluginConfig.set("selection", Material.SLIME_BALL.toString());
				pluginConfig.set("classes", idsAsString);
				plugin.saveConfig();
			}
			System.out.println("[MultiSpe] Config file loaded");
		} catch (Exception e) {
			System.out.println("[MultiSpe] Config file  : " + e.getMessage());
		}

		// ----------------------------------------------------------------------------------------------------------------------
		try {
			File f = new File("plugins/MultiSpe/players.yml");
			if (!f.exists()) {
				f.createNewFile();
				System.out.println("[MultiSpe] Players file created");
			} else {
				System.out.println("[MultiSpe] Players file loaded");
			}
		} catch (Exception e) {
			System.out.println("[MultiSpe] Players file  : " + e.getMessage());
		}

		// ----------------------------------------------------------------------------------------------------------------------
		try {
			File f = new File("plugins/MultiSpe/lang_classes.yml");
			if (!f.exists()) {
				f.createNewFile();
				FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(f);
				ConfigurationSection french = fileConfig.createSection(Language.FR.toString());
				ConfigurationSection english = fileConfig.createSection(Language.EN.toString());
				List<Spe> spes = Spe.getAllSpes().stream().sorted(Comparator.comparing(Spe::getId)).toList();
				for (Spe spe : spes) {
					french.set(spe.getId(), spe.getDefaultFr());
					english.set(spe.getId(), spe.getDefaultEn());
				}
				fileConfig.save(f);
				System.out.println("[MultiSpe] Classes file created");
			} else {
				System.out.println("[MultiSpe] Classes file loaded");
			}
		} catch (Exception e) {
			System.out.println("[MultiSpe] Classes file  : " + e.getMessage());
		}

		// ----------------------------------------------------------------------------------------------------------------------
		try {
			File f = new File("plugins/MultiSpe/lang_skills.yml");
			if (!f.exists()) {
				f.createNewFile();
				FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(f);
				ConfigurationSection french = fileConfig.createSection(Language.FR.toString());
				ConfigurationSection english = fileConfig.createSection(Language.EN.toString());
				List<Skill> skills = Skill.getAllSkills().stream().sorted(Comparator.comparing(Skill::getId)).toList();
				for (Skill skill : skills) {
					french.set(skill.getId(), skill.getDefaultFr());
					english.set(skill.getId(), skill.getDefaultEn());
				}
				fileConfig.save(f);
				System.out.println("[MultiSpe] Skills file created");
			} else {
				System.out.println("[MultiSpe] Skills file loaded");
			}
		} catch (Exception e) {
			System.out.println("[MultiSpe] Skills file : " + e.getMessage());
		}

		// ----------------------------------------------------------------------------------------------------------------------
		try {
			File f = new File("plugins/MultiSpe/lang_messages.yml");
			if (!f.exists()) {
				f.createNewFile();
				FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(f);
				ConfigurationSection french = fileConfig.createSection(Language.FR.toString());
				ConfigurationSection english = fileConfig.createSection(Language.EN.toString());
				List<Message> messages = Message.getAllMessages();
				for (Message msg : messages) {
					french.set(msg.getKey(), msg.getFr());
					english.set(msg.getKey(), msg.getEn());
				}
				fileConfig.save(f);
				System.out.println("[MultiSpe] Messages file created");
			} else {
				System.out.println("[MultiSpe] Messages file loaded");
			}
		} catch (Exception e) {
			System.out.println("[MultiSpe] Messages file  : " + e.getMessage());
		}
	}

	public void onDisable() {
		System.out.println("[MultiSpe] Plugin disconnected");
	}

	public boolean checkPerm(Player p, String perm) {
		if (!p.isOp()) {
			return false;
		} else {
			return true;
		}
	}
}