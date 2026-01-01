package fr.spefire.multispe.commands;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import fr.spefire.multispe.MultiSpe;
import fr.spefire.multispe.models.Language;
import fr.spefire.multispe.models.Spe;

public class ConsoleCommands implements Listener {

	private final MultiSpe plugin;

	public ConsoleCommands(MultiSpe plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void PlayerConnected(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		File playersFile = new File("plugins/MultiSpe/players.yml");
		FileConfiguration playersConfig = YamlConfiguration.loadConfiguration(playersFile);
		String pLanguagePath = p.getName() + ".language";
		String pLanguage = playersConfig.getString(pLanguagePath);
		if (pLanguage == null) {
			FileConfiguration pluginConfig = plugin.getConfig();
			String defLanguage = pluginConfig.getString("language");
			playersConfig.set(pLanguagePath, defLanguage);
		}
		playersConfig.set(p.getName() + ".loaded", true);
		try {
			playersConfig.save(playersFile);
		} catch (IOException error) {
			error.printStackTrace();
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void PlayerTchat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		FileConfiguration pluginConfig = plugin.getConfig();
		Boolean tchat = pluginConfig.getBoolean("tchat");
		if (!tchat)
			return;
		//
		File playersFile = new File("plugins/MultiSpe/players.yml");
		FileConfiguration playersConfig = YamlConfiguration.loadConfiguration(playersFile);
		String pSpe = playersConfig.getString(p.getName() + ".class");
		String pLanguage = playersConfig.getString(p.getName() + ".language");
		Boolean pIsFrench = Language.FR.toString().equals(pLanguage);

		List<Spe> spes = Spe.getAllSpes();
		Spe spe = (pSpe != null) ? spes.stream().filter(s -> pSpe.equals(s.getId())).findFirst().orElse(null) : null;
		if (spe == null)
			return;
		//
		String mess = e.getMessage();
		e.setMessage(
				ChatColor.AQUA + "(" + (pIsFrench ? spe.getNameFr() : spe.getNameEn()) + ") " + ChatColor.WHITE + mess);
	}
}