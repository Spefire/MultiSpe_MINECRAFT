package fr.spefire.multispe;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;

import fr.spefire.multispe.models.Spe;
import fr.spefire.multispe.models.SpeType;

public class PlayerCommands implements Listener {

	private MultiSpe plugin;

	public PlayerCommands(MultiSpe plugin) {
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
			String defLanguage = pluginConfig.getString("Language");
			playersConfig.set(pLanguagePath, defLanguage);
		}
		playersConfig.set(p.getName() + ".loading", true);
		try {
			playersConfig.save(playersFile);
		} catch (IOException error) {
			error.printStackTrace();
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void demandeClasse(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		String message = e.getMessage();
		String[] params = message.split(" ");

		if (params[0].startsWith("/ms")) {
			e.setCancelled(true);
			FileConfiguration pluginConfig = plugin.getConfig();
			File playersFile = new File("plugins/MultiSpe/players.yml");
			FileConfiguration playersConfig = YamlConfiguration.loadConfiguration(playersFile);
			String pSpe = playersConfig.getString(p.getName() + ".class");
			String pLanguage = playersConfig.getString(p.getName() + ".language");
			Boolean pIsFrench = "French".equalsIgnoreCase(pLanguage);
			File messagesFile = new File("plugins/MultiSpe/lang_messages.yml");
			FileConfiguration messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);

			// ----------------------------------------------------------------------------------------------------------------------
			if (params[0].equalsIgnoreCase("/msStatus")) {
				List<Spe> spes = Spe.getAllSpes();
				Spe spe = (pSpe != null) ? spes.stream().filter(s -> pSpe.equals(s.getId())).findFirst().orElse(null)
						: null;
				if (spe != null) {
					p.sendMessage(ChatColor.AQUA + messagesConfig.getString(pLanguage + ".tobe") + ChatColor.WHITE
							+ (pIsFrench ? spe.getNameFr() : spe.getNameEn()));
				} else {
					p.sendMessage(ChatColor.AQUA + messagesConfig.getString(pLanguage + ".noclass"));
				}
			}

			// ----------------------------------------------------------------------------------------------------------------------
			else if (params[0].equalsIgnoreCase("/msClass")) {
				if (pSpe == null || plugin.hasPermission(p, "multispe.change.class")
						|| plugin.hasPermission(p, "multispe.admin")) {
					if (params.length == 2) {
						String speWanted = params[1].toLowerCase();
						List<Spe> spes = Spe.getAllSpes();
						if (!plugin.hasPermission(p, "multispe.change.class")) {
							p.sendMessage(ChatColor.RED + messagesConfig.getString(pLanguage + ".warningclass"));
						}
						for (Spe spe : spes) {
							if (speWanted.equalsIgnoreCase(spe.getNameFr())
									|| speWanted.equalsIgnoreCase(spe.getNameEn())) {
								if (plugin.hasPermission(p, "multispe.class." + spe.getId().toLowerCase())
										|| plugin.hasPermission(p, "multispe.class.all")
										|| plugin.hasPermission(p, "multispe.admin")) {
									playersConfig.set(pSpe, spe.getId());
									try {
										playersConfig.save(playersFile);
									} catch (IOException error) {
										error.printStackTrace();
									}
									p.sendMessage(ChatColor.AQUA + messagesConfig.getString(pLanguage + ".toclass")
											+ (pIsFrench ? spe.getNameFr() : spe.getNameEn()));
								} else {
									p.sendMessage(
											ChatColor.RED + messagesConfig.getString(pLanguage + ".nopermissiontoclass")
													+ (pIsFrench ? spe.getNameFr() : spe.getNameEn()));
								}
							}
						}
					} else {
						p.sendMessage(ChatColor.RED + "/msClass <???>");
					}
				} else {
					p.sendMessage(ChatColor.RED + messagesConfig.getString(pLanguage + ".alreadyclass"));
				}
			}

			// ----------------------------------------------------------------------------------------------------------------------
			else if (params[0].equalsIgnoreCase("/msStuff")) {
				if (plugin.hasPermission(p, "multispe.stuff") || plugin.hasPermission(p, "multispe.admin")) {
					if (pSpe != null) {
						ItemStack item;
						if (pSpe.equalsIgnoreCase(SpeType.WAR.toString())) {
							item = new ItemStack(Material.STONE_SWORD);
							p.getInventory().addItem(item);
						} else if (pSpe.equalsIgnoreCase(SpeType.ARC.toString())) {
							item = new ItemStack(Material.BOW);
							p.getInventory().addItem(item);
							item = new ItemStack(Material.ARROW);
							item.setAmount(64);
							p.getInventory().addItem(item);
						} else if (pSpe.equalsIgnoreCase(SpeType.PRI.toString())) {
							item = new ItemStack(Material.ROSE_BUSH);
							p.getInventory().addItem(item);
						} else if (pSpe.equalsIgnoreCase(SpeType.WIZ.toString())) {
							item = new ItemStack(Material.PAPER);
							p.getInventory().addItem(item);
						} else if (pSpe.equalsIgnoreCase(SpeType.VAM.toString())) {
							item = new ItemStack(Material.STONE_HOE);
							p.getInventory().addItem(item);
						} else if (pSpe.equalsIgnoreCase(SpeType.NEC.toString())) {
							item = new ItemStack(Material.FLINT);
							p.getInventory().addItem(item);
						} else if (pSpe.equalsIgnoreCase(SpeType.DRU.toString())) {
							item = new ItemStack(Material.SUNFLOWER);
							p.getInventory().addItem(item);
						} else if (pSpe.equalsIgnoreCase(SpeType.BUT.toString())) {
							item = new ItemStack(Material.STONE_AXE);
							p.getInventory().addItem(item);
						} else if (pSpe.equalsIgnoreCase(SpeType.ASS.toString())) {
							item = new ItemStack(Material.STONE_SWORD);
							p.getInventory().addItem(item);
						}
						item = new ItemStack(Material.SLIME_BALL);
						p.getInventory().addItem(item);
					} else {
						p.sendMessage(ChatColor.RED + messagesConfig.getString(pLanguage + ".noclass"));
					}
				} else {
					p.sendMessage(ChatColor.RED + messagesConfig.getString(pLanguage + ".nopermission"));
				}
			}

			// ----------------------------------------------------------------------------------------------------------------------
			else if (params[0].equalsIgnoreCase("/msLocation")) {
				boolean isActivated = pluginConfig.getBoolean(p.getWorld().toString());
				if (isActivated) {
					p.sendMessage(ChatColor.AQUA + messagesConfig.getString(pLanguage + ".locationtrue"));
				} else {
					p.sendMessage(ChatColor.AQUA + messagesConfig.getString(pLanguage + ".locationfalse"));
				}
			}

			// ----------------------------------------------------------------------------------------------------------------------
			else if (params[0].equalsIgnoreCase("/msSetWorld")) {
				if (plugin.hasPermission(p, "multispe.setworld") || plugin.hasPermission(p, "multispe.admin")) {
					if (params.length == 2 && params[1].equalsIgnoreCase("true")) {
						pluginConfig.set(p.getWorld().toString(), true);
						plugin.saveConfig();
						p.sendMessage(ChatColor.AQUA + messagesConfig.getString(pLanguage + ".worldtrue"));
					} else if (params.length == 2 && params[1].equalsIgnoreCase("false")) {
						pluginConfig.set(p.getWorld().toString(), false);
						plugin.saveConfig();
						p.sendMessage(ChatColor.AQUA + messagesConfig.getString(pLanguage + ".worldfalse"));
					} else {
						p.sendMessage(ChatColor.RED + "/msSetWorld <true|false>");
					}
				} else {
					p.sendMessage(ChatColor.RED + messagesConfig.getString(pLanguage + ".nopermission"));
				}
			}

			// ----------------------------------------------------------------------------------------------------------------------
			else if (params[0].equalsIgnoreCase("/msLanguage")) {
				if (params.length == 2 && params[1].toLowerCase().startsWith("en")) {
					playersConfig.set(p.getName() + ".language", "English");
					try {
						playersConfig.save(playersFile);
					} catch (IOException error) {
						error.printStackTrace();
					}
					p.sendMessage(ChatColor.AQUA + "Now the plugin is in english");
				} else if (params.length == 2 && params[1].toLowerCase().startsWith("fr")) {
					playersConfig.set(p.getName() + ".language", "French");
					try {
						playersConfig.save(playersFile);
					} catch (IOException error) {
						error.printStackTrace();
					}
					p.sendMessage(ChatColor.AQUA + "Le plugin est en français maintenant");
				} else {
					p.sendMessage(ChatColor.RED + "/msLanguage <english|french>");
				}
			}

			// ----------------------------------------------------------------------------------------------------------------------
			else {
				Boolean canStuff = plugin.hasPermission(p, "multispe.stuff")
						|| plugin.hasPermission(p, "multispe.admin");
				Boolean canSetWorld = plugin.hasPermission(p, "multispe.setworld")
						|| plugin.hasPermission(p, "multispe.admin");
				if (pIsFrench) {
					p.sendMessage(ChatColor.AQUA + "---------" + ChatColor.WHITE + "[MultiSpe] - Aide" + ChatColor.AQUA
							+ "--------------");
					p.sendMessage(ChatColor.AQUA + "                          ");
					p.sendMessage(ChatColor.AQUA + "/msStatus: " + ChatColor.WHITE + "Savoir sa classe");
					p.sendMessage(ChatColor.AQUA + "/msClass: " + ChatColor.WHITE + "Choisir sa classe");
					if (canStuff) {
						p.sendMessage(ChatColor.AQUA + "/msStuff: " + ChatColor.WHITE + "Avoir son équipement");
					}
					p.sendMessage(
							ChatColor.AQUA + "/msLocation: " + ChatColor.WHITE + "Savoir si le plugin est actif ici");
					if (canSetWorld) {
						p.sendMessage(ChatColor.AQUA + "/msSetWorld: " + ChatColor.WHITE
								+ "Activer ou non le plugin dans ce monde");
					}
					p.sendMessage(ChatColor.AQUA + "/msLanguage: " + ChatColor.WHITE + "Choisir sa langue");
				} else {
					p.sendMessage(ChatColor.AQUA + "---------" + ChatColor.WHITE + "[MultiSpe] - Help" + ChatColor.AQUA
							+ "--------------");
					p.sendMessage(ChatColor.AQUA + "                          ");
					p.sendMessage(ChatColor.AQUA + "/msStatus: " + ChatColor.WHITE + "To know your class");
					p.sendMessage(ChatColor.AQUA + "/msClass: " + ChatColor.WHITE + "To choice your class");
					if (canStuff) {
						p.sendMessage(ChatColor.AQUA + "/msStuff: " + ChatColor.WHITE + "To get your stuff");
					}
					p.sendMessage(ChatColor.AQUA + "/msLocation: " + ChatColor.WHITE
							+ "To know if the plugin is actived at your location");
					if (canSetWorld) {
						p.sendMessage(ChatColor.AQUA + "/msSetWorld: " + ChatColor.WHITE
								+ "To enable or disable the plugin in this world");
					}
					p.sendMessage(ChatColor.AQUA + "/msLanguage: " + ChatColor.WHITE + "To choice your language");
				}
			}
		}
	}
}