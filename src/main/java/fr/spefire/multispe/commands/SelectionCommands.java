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
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import fr.spefire.multispe.MultiSpe;
import fr.spefire.multispe.models.Language;
import fr.spefire.multispe.models.Skill;
import fr.spefire.multispe.models.Spe;

public class SelectionCommands implements Listener {

	private MultiSpe plugin;
	private int nbSkills = 3;

	public SelectionCommands(MultiSpe plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getHand() != EquipmentSlot.HAND
				|| (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK)) {
			return;
		}
		Player p = e.getPlayer();
		FileConfiguration pluginConfig = plugin.getConfig();
		boolean isActivated = pluginConfig.getBoolean(p.getWorld().toString());
		if (isActivated) {
			String selection = pluginConfig.getString("selection");
			ItemStack item = p.getInventory().getItemInMainHand();
			if (item.getType().toString().equals(selection)) {
				File playersFile = new File("plugins/MultiSpe/players.yml");
				FileConfiguration playersConfig = YamlConfiguration.loadConfiguration(playersFile);
				String pSpe = playersConfig.getString(p.getName() + ".class");
				List<Spe> spes = Spe.getAllSpes();
				Spe spe = (pSpe != null) ? spes.stream().filter(s -> pSpe.equals(s.getId())).findFirst().orElse(null)
						: null;
				if (spe != null) {
					String pLanguage = playersConfig.getString(p.getName() + ".language");
					Boolean pIsFrench = Language.FR.toString().equals(pLanguage);
					String skillPath = p.getName() + ".skill";
					Integer pIndexSkill = playersConfig.getInt(skillPath);
					if (pIndexSkill == nbSkills) {
						pIndexSkill = 0;
					} else {
						pIndexSkill++;
					}
					playersConfig.set(skillPath, pIndexSkill);
					try {
						playersConfig.save(playersFile);
					} catch (IOException error) {
						error.printStackTrace();
					}
					Skill skill = spe.getSkill(pIndexSkill);
					if (pIndexSkill != 0) {
						p.sendMessage(ChatColor.AQUA + "[" + skill.getType().toString() + "] " + ChatColor.WHITE
								+ (pIsFrench ? skill.getNameFr() : skill.getNameEn()));
					} else {
						p.sendMessage(ChatColor.AQUA + (pIsFrench ? skill.getNameFr() : skill.getNameEn()));
					}
				}
			}
		}
	}
}