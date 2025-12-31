package fr.spefire.multispe;

import java.io.File;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.spefire.multispe.models.LangType;
import fr.spefire.multispe.models.Spe;

public class ConsoleCommands implements Listener {

	private MultiSpe plugin;

	public ConsoleCommands(MultiSpe plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void PlayerTchat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		FileConfiguration pluginConfig = plugin.getConfig();
		Boolean tchat = pluginConfig.getBoolean("tchat");
		if (tchat) {
			File playersFile = new File("plugins/MultiSpe/players.yml");
			FileConfiguration playersConfig = YamlConfiguration.loadConfiguration(playersFile);
			String pSpe = playersConfig.getString(p.getName() + ".class");
			String pLanguage = playersConfig.getString(p.getName() + ".language");
			Boolean pIsFrench = LangType.FR.toString().equals(pLanguage);

			List<Spe> spes = Spe.getAllSpes();
			Spe spe = (pSpe != null) ? spes.stream().filter(s -> pSpe.equals(s.getId())).findFirst().orElse(null)
					: null;
			if (spe != null) {
				String mess = e.getMessage();
				e.setMessage(ChatColor.AQUA + "(" + (pIsFrench ? spe.getNameFr() : spe.getNameEn()) + ") "
						+ ChatColor.WHITE + mess);
			}
		}
	}
}