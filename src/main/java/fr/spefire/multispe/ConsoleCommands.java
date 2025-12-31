package fr.spefire.multispe;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class ConsoleCommands implements Listener {

	private MultiSpe plugin;

	public ConsoleCommands(MultiSpe plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void PlayerParle(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		FileConfiguration config = plugin.getConfig();
		String path = "class." + p.getName();
		String category = config.getString(path);
		String chat = "chat";
		boolean tchat = config.getBoolean(chat);
		if (category != null && !category.equalsIgnoreCase(" ") && tchat == true) {
			String mess = e.getMessage();
			e.setMessage(ChatColor.AQUA + "(" + category + ") " + ChatColor.WHITE + mess);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void consoleCom(ServerCommandEvent e) {
		CommandSender p = e.getSender();
		String message = e.getCommand();
		String[] params = message.split(" ");
		if (params[0].equalsIgnoreCase("nplayer")) {
			File f = new File("plugins/NirvaEx/classes.yml");
			FileConfiguration modif = YamlConfiguration.loadConfiguration(f);
			if (params.length == 3) {
				String namePlayer = params[1].toString();
				FileConfiguration config = plugin.getConfig();
				String ldefaut = "languages." + "Default";
				String defaut = config.getString(ldefaut);
				if (params[2].toLowerCase().equalsIgnoreCase(modif.getString("Class.One"))) {
					String path = "class." + namePlayer;
					config.set(path, modif.getString("Class.One"));
					plugin.saveConfig();
					p.sendMessage(ChatColor.AQUA + namePlayer + modif.getString(defaut + ".change")
							+ modif.getString("Class.One"));
				} else if (params[2].toLowerCase().equalsIgnoreCase(modif.getString("Class.Two"))) {
					String path = "class." + namePlayer;
					config.set(path, modif.getString("Class.Two"));
					plugin.saveConfig();
					p.sendMessage(ChatColor.AQUA + namePlayer + modif.getString(defaut + ".change")
							+ modif.getString("Class.Two"));
				} else if (params[2].toLowerCase().equalsIgnoreCase(modif.getString("Class.Three"))) {
					String path = "class." + namePlayer;
					config.set(path, modif.getString("Class.Three"));
					plugin.saveConfig();
					p.sendMessage(ChatColor.AQUA + namePlayer + modif.getString(defaut + ".change")
							+ modif.getString("Class.Three"));
				} else if (params[2].toLowerCase().equalsIgnoreCase(modif.getString("Class.Four"))) {
					String path = "class." + namePlayer;
					config.set(path, modif.getString("Class.Four"));
					plugin.saveConfig();
					p.sendMessage(ChatColor.AQUA + namePlayer + modif.getString(defaut + ".change")
							+ modif.getString("Class.Four"));
				} else if (params[2].toLowerCase().equalsIgnoreCase(modif.getString("Class.Five"))) {
					String path = "class." + namePlayer;
					config.set(path, modif.getString("Class.Five"));
					plugin.saveConfig();
					p.sendMessage(ChatColor.AQUA + namePlayer + modif.getString(defaut + ".change")
							+ modif.getString("Class.Five"));
				} else if (params[2].toLowerCase().equalsIgnoreCase(modif.getString("Class.Six"))) {
					String path = "class." + namePlayer;
					config.set(path, modif.getString("Class.Six"));
					plugin.saveConfig();
					p.sendMessage(ChatColor.AQUA + namePlayer + modif.getString(defaut + ".change")
							+ modif.getString("Class.Six"));
				} else if (params[2].toLowerCase().equalsIgnoreCase(modif.getString("Class.Seven"))) {
					String path = "class." + namePlayer;
					config.set(path, modif.getString("Class.Seven"));
					plugin.saveConfig();
					p.sendMessage(ChatColor.AQUA + namePlayer + modif.getString(defaut + ".change")
							+ modif.getString("Class.Seven"));
				} else if (params[2].toLowerCase().equalsIgnoreCase(modif.getString("Class.Eight"))) {
					String path = "class." + namePlayer;
					config.set(path, modif.getString("Class.Eight"));
					plugin.saveConfig();
					p.sendMessage(ChatColor.AQUA + namePlayer + modif.getString(defaut + ".change")
							+ modif.getString("Class.Eight"));
				} else if (params[2].toLowerCase().equalsIgnoreCase(modif.getString("Class.Nine"))) {
					String path = "class." + namePlayer;
					config.set(path, modif.getString("Class.Nine"));
					plugin.saveConfig();
					p.sendMessage(ChatColor.AQUA + namePlayer + modif.getString(defaut + ".change")
							+ modif.getString("Class.Nine"));
				} else if (params[2].toLowerCase().equalsIgnoreCase(modif.getString("Class.Deleted"))) {
					String path = "class." + namePlayer;
					config.set(path, " ");
					plugin.saveConfig();
					p.sendMessage(ChatColor.AQUA + namePlayer + " deleted !");
				}
			} else {
				p.sendMessage(ChatColor.RED + "!!! Uppercase and lowercase letters !!!");
				p.sendMessage(ChatColor.RED + "nplayer <Player> " + modif.getString("Class.List"));
			}
		}
		if (params[0].equalsIgnoreCase("nchat")) {
			if (params.length == 2 && params[1].toLowerCase().startsWith("t")) {
				FileConfiguration config = plugin.getConfig();
				String chat = "chat";
				config.set(chat, true);
				plugin.saveConfig();
				p.sendMessage(ChatColor.AQUA + "Nirva Chat : True");
			} else if (params.length == 2 && params[1].toLowerCase().startsWith("f")) {
				FileConfiguration config = plugin.getConfig();
				String chat = "chat";
				config.set(chat, false);
				plugin.saveConfig();
				p.sendMessage(ChatColor.AQUA + "Nirva Chat : False");
			} else {
				p.sendMessage(ChatColor.RED + "nchat <true|false>");
			}
		}
		if (params[0].equalsIgnoreCase("nlanguage")) {
			if (params.length == 2 && params[1].toLowerCase().startsWith("en")) {
				FileConfiguration config = plugin.getConfig();
				String path = "languages." + "Default";
				config.set(path, "English");
				plugin.saveConfig();
				p.sendMessage(ChatColor.AQUA + "Now Nirva is in English by default");
			} else if (params.length == 2 && params[1].toLowerCase().startsWith("fr")) {
				FileConfiguration config = plugin.getConfig();
				String path = "languages." + "Default";
				config.set(path, "French");
				plugin.saveConfig();
				p.sendMessage(ChatColor.AQUA + "Nirva est maintenant en Français par défaut");
			} else {
				p.sendMessage(ChatColor.RED + "nlanguage <English|French>");
			}
		}
		if (params[0].equalsIgnoreCase("n") || params[0].equalsIgnoreCase("nhelp")) {
			FileConfiguration config = plugin.getConfig();
			String languages = "languages." + p.getName();
			String language = config.getString(languages);
			String ldefaut = "languages." + "Default";
			String defaut = config.getString(ldefaut);
			if ("French".equalsIgnoreCase(language)
					|| (!"French".equalsIgnoreCase(language) && "French".equalsIgnoreCase(defaut))) {
				p.sendMessage(ChatColor.AQUA + "---------" + ChatColor.WHITE + "Aide: NirvaEx" + ChatColor.AQUA
						+ "--------------");
				p.sendMessage(ChatColor.AQUA + "                          ");
				p.sendMessage(ChatColor.AQUA + "nchat: " + ChatColor.WHITE + "Activer ou non le NirvaChat");
				p.sendMessage(ChatColor.AQUA + "nplayer: " + ChatColor.WHITE + "Choisir la classe d'un joueur");
				p.sendMessage(ChatColor.AQUA + "nlanguage: " + ChatColor.WHITE + "Nirva langage par défaut");
			} else {
				p.sendMessage(ChatColor.AQUA + "---------" + ChatColor.WHITE + "Help: NirvaEx" + ChatColor.AQUA
						+ "--------------");
				p.sendMessage(ChatColor.AQUA + "                          ");
				p.sendMessage(ChatColor.AQUA + "nchat: " + ChatColor.WHITE + "To active or not the NirvaChat");
				p.sendMessage(ChatColor.AQUA + "nplayer: " + ChatColor.WHITE + "To choice the class of a player");
				p.sendMessage(ChatColor.AQUA + "nlanguage: " + ChatColor.WHITE + "Nirva language by default");
			}
		}
	}
}