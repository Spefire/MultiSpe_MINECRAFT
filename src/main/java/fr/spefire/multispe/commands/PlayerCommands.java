package fr.spefire.multispe.commands;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.spefire.multispe.MultiSpe;
import fr.spefire.multispe.models.Language;
import fr.spefire.multispe.models.Spe;
import fr.spefire.multispe.models.SpeCode;

public class PlayerCommands implements CommandExecutor {

	private final MultiSpe plugin;

	public PlayerCommands(MultiSpe plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p;
		String[] params;
		if (sender instanceof Player) {
			// Player command
			p = (Player) sender;
			params = args.clone();
			;
		} else {
			// Box command
			if (args.length > 0) {
				List<Entity> targets = Bukkit.selectEntities(sender, "@p");
				p = targets.stream().filter(e -> e instanceof Player).map(e -> (Player) e).findFirst().orElse(null);
				if (p == null) {
					System.out.println("[MultiSpe] Box command : player not found");
					return true;
				}
				if (args.length > 1) {
					params = java.util.Arrays.copyOfRange(args, 1, args.length);
				} else {
					params = new String[0];
				}
			} else {
				System.out.println("[MultiSpe] Box command : @p not found");
				return true;
			}
		}

		FileConfiguration pluginConfig = plugin.getConfig();
		File playersFile = new File("plugins/MultiSpe/players.yml");
		FileConfiguration playersConfig = YamlConfiguration.loadConfiguration(playersFile);
		String pSpe = playersConfig.getString(p.getName() + ".class");
		String pLanguage = playersConfig.getString(p.getName() + ".language");
		Boolean pIsFrench = Language.FR.toString().equals(pLanguage);
		File messagesFile = new File("plugins/MultiSpe/lang_messages.yml");
		FileConfiguration messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);

		// ----------------------------------------------------------------------------------------------------------------------
		if (label.equalsIgnoreCase("msStatus")) {
			Boolean notFound = true;
			if (pSpe != null) {
				Spe spe = Spe.getSpeById(pSpe);
				if (spe != null) {
					notFound = false;
					p.sendMessage(ChatColor.AQUA + messagesConfig.getString(pLanguage + ".tobe") + ChatColor.WHITE
							+ (pIsFrench ? spe.getNameFr() : spe.getNameEn()));
				}
			}
			if (notFound) {
				p.sendMessage(ChatColor.AQUA + messagesConfig.getString(pLanguage + ".noclass"));
			}
		}

		// ----------------------------------------------------------------------------------------------------------------------
		else if (label.equalsIgnoreCase("msClass")) {
			if ((pSpe == null && plugin.checkPerm(p, "multispe.add.class"))
					|| (pSpe != null || plugin.checkPerm(p, "multispe.change.class"))) {
				Boolean noMatch = true;
				if (params.length == 1) {
					String speWanted = params[0].toLowerCase();
					List<Spe> spes = Spe.getAllSpes();
					if (!plugin.checkPerm(p, "multispe.change.class")) {
						p.sendMessage(ChatColor.RED + messagesConfig.getString(pLanguage + ".warningclass"));
					}
					for (Spe spe : spes) {
						if (speWanted.equalsIgnoreCase(spe.getNameFr())
								|| speWanted.equalsIgnoreCase(spe.getNameEn())) {
							noMatch = false;
							if (plugin.checkPerm(p, "multispe.class." + spe.getId().toLowerCase())) {
								playersConfig.set(p.getName() + ".class", spe.getId());
								try {
									playersConfig.save(playersFile);
								} catch (IOException error) {
									error.printStackTrace();
								}
								p.sendMessage(ChatColor.AQUA + messagesConfig.getString(pLanguage + ".toclass")
										+ ChatColor.WHITE + (pIsFrench ? spe.getNameFr() : spe.getNameEn()));
							} else {
								p.sendMessage(
										ChatColor.RED + messagesConfig.getString(pLanguage + ".nopermissiontoclass")
												+ (pIsFrench ? spe.getNameFr() : spe.getNameEn()));
							}
						}
					}
				}
				if (noMatch) {
					p.sendMessage(
							ChatColor.RED + "/msClass <" + (pIsFrench ? "Guerrier|Archer|Prêtre|Sorcier|Nécromancien"
									: "Warrior|Archer|Priest|Wizard|Necromancer") + ">");
				}
			} else {
				if (pSpe == null && plugin.checkPerm(p, "multispe.add.class")) {
					p.sendMessage(ChatColor.RED + messagesConfig.getString(pLanguage + ".nopermission"));
				}
				if (pSpe != null || plugin.checkPerm(p, "multispe.change.class")) {
					p.sendMessage(ChatColor.RED + messagesConfig.getString(pLanguage + ".alreadyclass"));
				}
			}
		}

		// ----------------------------------------------------------------------------------------------------------------------
		else if (label.equalsIgnoreCase("msUnclass")) {
			if (plugin.checkPerm(p, "multispe.remove.class")) {
				if (pSpe != null) {
					playersConfig.set(p.getName() + ".class", null);
					try {
						playersConfig.save(playersFile);
					} catch (IOException error) {
						error.printStackTrace();
					}
					p.sendMessage(ChatColor.AQUA + messagesConfig.getString(pLanguage + ".tonoclass"));
				} else {
					p.sendMessage(ChatColor.RED + messagesConfig.getString(pLanguage + ".noclass"));
				}
			} else {
				p.sendMessage(ChatColor.RED + messagesConfig.getString(pLanguage + ".nopermission"));
			}
		}

		// ----------------------------------------------------------------------------------------------------------------------
		else if (label.equalsIgnoreCase("msStuff")) {
			if (plugin.checkPerm(p, "multispe.stuff")) {
				if (pSpe != null) {
					ItemStack item;
					if (pSpe.equals(SpeCode.WAR.toString())) {
						item = new ItemStack(Material.STONE_SWORD);
						p.getInventory().addItem(item);
					} else if (pSpe.equals(SpeCode.ARC.toString())) {
						item = new ItemStack(Material.BOW);
						p.getInventory().addItem(item);
						item = new ItemStack(Material.ARROW);
						item.setAmount(64);
						p.getInventory().addItem(item);
					} else if (pSpe.equals(SpeCode.PRI.toString())) {
						item = new ItemStack(Material.PAPER);
						p.getInventory().addItem(item);
					} else if (pSpe.equals(SpeCode.WIZ.toString())) {
						item = new ItemStack(Material.STICK);
						p.getInventory().addItem(item);
					} else if (pSpe.equals(SpeCode.DRU.toString())) {
						item = new ItemStack(Material.BONE);
						p.getInventory().addItem(item);
					} else if (pSpe.equals(SpeCode.NEC.toString())) {
						item = new ItemStack(Material.STONE_HOE);
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
		else if (label.equalsIgnoreCase("msLocation")) {
			boolean isActivated = pluginConfig.getBoolean("worlds." + p.getWorld().getName());
			if (isActivated) {
				p.sendMessage(ChatColor.AQUA + messagesConfig.getString(pLanguage + ".locationtrue"));
			} else {
				p.sendMessage(ChatColor.AQUA + messagesConfig.getString(pLanguage + ".locationfalse"));
			}
		}

		// ----------------------------------------------------------------------------------------------------------------------
		else if (label.equalsIgnoreCase("msSetWorld")) {
			if (plugin.checkPerm(p, "multispe.setworld")) {
				if (params.length == 1 && params[0].equalsIgnoreCase("true")) {
					pluginConfig.set("worlds." + p.getWorld().getName(), true);
					plugin.saveConfig();
					p.sendMessage(ChatColor.AQUA + messagesConfig.getString(pLanguage + ".worldtrue"));
				} else if (params.length == 1 && params[0].equalsIgnoreCase("false")) {
					pluginConfig.set("worlds." + p.getWorld().getName(), false);
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
		else if (label.equalsIgnoreCase("msLanguage")) {
			if (params.length == 1 && params[0].toLowerCase().startsWith("en")) {
				playersConfig.set(p.getName() + ".language", Language.EN.toString());
				try {
					playersConfig.save(playersFile);
				} catch (IOException error) {
					error.printStackTrace();
				}
				p.sendMessage(ChatColor.AQUA + "Now the plugin is in english");
			} else if (params.length == 1 && params[0].toLowerCase().startsWith("fr")) {
				playersConfig.set(p.getName() + ".language", Language.FR.toString());
				try {
					playersConfig.save(playersFile);
				} catch (IOException error) {
					error.printStackTrace();
				}
				p.sendMessage(ChatColor.AQUA + "Le plugin est en français maintenant");
			} else {
				p.sendMessage(ChatColor.RED + "/msLanguage <en|fr>");
			}
		}

		// ----------------------------------------------------------------------------------------------------------------------
		else if (label.equalsIgnoreCase("ms") || label.equalsIgnoreCase("msHelp")) {
			Boolean canClass = plugin.checkPerm(p, "multispe.add.class")
					|| plugin.checkPerm(p, "multispe.change.class");
			Boolean canUnclass = plugin.checkPerm(p, "multispe.remove.class");
			Boolean canStuff = plugin.checkPerm(p, "multispe.stuff");
			Boolean canSetWorld = plugin.checkPerm(p, "multispe.setworld");
			if (pIsFrench) {
				p.sendMessage(ChatColor.AQUA + "                          ");
				p.sendMessage(ChatColor.AQUA + "---------" + ChatColor.WHITE + "[MultiSpe] - Aide" + ChatColor.AQUA
						+ "--------------");
				p.sendMessage(ChatColor.AQUA + "                          ");
				p.sendMessage(ChatColor.AQUA + "/msStatus: " + ChatColor.WHITE + "Savoir sa classe");
				if (canClass) {
					p.sendMessage(ChatColor.AQUA + "/msClass: " + ChatColor.WHITE + "Choisir sa classe");
				}
				if (canUnclass) {
					p.sendMessage(ChatColor.AQUA + "/msUnclass: " + ChatColor.WHITE + "Supprimer sa classe");
				}
				if (canStuff) {
					p.sendMessage(ChatColor.AQUA + "/msStuff: " + ChatColor.WHITE + "Avoir son équipement");
				}
				p.sendMessage(ChatColor.AQUA + "/msLocation: " + ChatColor.WHITE + "Savoir si le plugin est actif ici");
				if (canSetWorld) {
					p.sendMessage(ChatColor.AQUA + "/msSetWorld: " + ChatColor.WHITE
							+ "Activer ou non le plugin dans ce monde");
				}
				p.sendMessage(ChatColor.AQUA + "/msLanguage: " + ChatColor.WHITE + "Choisir sa langue");
			} else {
				p.sendMessage(ChatColor.AQUA + "                          ");
				p.sendMessage(ChatColor.AQUA + "---------" + ChatColor.WHITE + "[MultiSpe] - Help" + ChatColor.AQUA
						+ "--------------");
				p.sendMessage(ChatColor.AQUA + "                          ");
				p.sendMessage(ChatColor.AQUA + "/msStatus: " + ChatColor.WHITE + "To know your class");
				if (canClass) {
					p.sendMessage(ChatColor.AQUA + "/msClass: " + ChatColor.WHITE + "To choice your class");
				}
				if (canUnclass) {
					p.sendMessage(ChatColor.AQUA + "/msUnclass: " + ChatColor.WHITE + "To remove your class");
				}
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
		return true;
	}
}