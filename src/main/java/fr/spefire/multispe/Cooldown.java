package fr.spefire.multispe;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Cooldown extends Thread {

	private final MultiSpe plugin;
	private final Player p;

	public Cooldown(MultiSpe plugin, Player p) {
		this.plugin = plugin;
		this.p = p;
	}

	public void run() {
		try {
			FileConfiguration pluginConfig = plugin.getConfig();
			Integer waitingTime = pluginConfig.getInt("cooldown");
			File playersFile = new File("plugins/MultiSpe/players.yml");
			FileConfiguration playersConfig = YamlConfiguration.loadConfiguration(playersFile);
			String pLanguage = playersConfig.getString(p.getName() + ".language");
			//
			this._save(false);
			p.sendMessage((waitingTime) + "...");
			while (waitingTime != 0) {
				if (waitingTime % 5 == 0) {
					p.sendMessage((waitingTime) + "...");
				}
				sleep(1000);
				waitingTime--;
			}
			this._save(true);
			//
			File messagesFile = new File("plugins/MultiSpe/lang_messages.yml");
			FileConfiguration messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
			p.sendMessage(ChatColor.AQUA + messagesConfig.getString(pLanguage + ".reloaded"));
			p.getWorld().playEffect(p.getLocation(), Effect.EXTINGUISH, 1000);
		} catch (InterruptedException error) {
			error.printStackTrace();
		} finally {
			this.interrupt();
		}
	}

	private void _save(Boolean value) {
		File playersFile = new File("plugins/MultiSpe/players.yml");
		FileConfiguration playersConfig = YamlConfiguration.loadConfiguration(playersFile);
		playersConfig.set(p.getName() + ".loaded", value);
		try {
			playersConfig.save(playersFile);
		} catch (IOException error) {
			error.printStackTrace();
		}
	}
}
