package fr.spefire.multispe.actions;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.spefire.multispe.Cooldown;
import fr.spefire.multispe.MultiSpe;
import fr.spefire.multispe.models.Language;
import fr.spefire.multispe.models.Skill;
import fr.spefire.multispe.models.Spe;
import fr.spefire.multispe.models.SpeCode;

public class DefSkills implements Listener {

	private final MultiSpe plugin;
	private final int second = 20;

	public DefSkills(MultiSpe plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getHand() != EquipmentSlot.HAND
				|| (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK))
			return;
		//
		Player p = e.getPlayer();
		FileConfiguration pluginConfig = plugin.getConfig();
		boolean isActivated = pluginConfig.getBoolean("worlds." + p.getWorld().getName());
		if (!isActivated)
			return;
		//
		File playersFile = new File("plugins/MultiSpe/players.yml");
		FileConfiguration playersConfig = YamlConfiguration.loadConfiguration(playersFile);
		String pSpe = playersConfig.getString(p.getName() + ".class");
		String pLanguage = playersConfig.getString(p.getName() + ".language");
		Integer pIndexSkill = playersConfig.getInt(p.getName() + ".skill");
		Boolean pIsLoaded = playersConfig.getBoolean(p.getName() + ".loaded");
		Boolean pIsFrench = Language.FR.toString().equals(pLanguage);
		if (pSpe == null || !pIsLoaded)
			return;
		//
		Spe spe = Spe.getSpeById(pSpe);
		if (spe == null)
			return;
		//
		ItemStack item = p.getInventory().getItemInMainHand();
		Skill skill = spe.getSkill(pIndexSkill);
		Boolean needLoading = false;
		if (pSpe.equals(SpeCode.WAR.toString()) && (item.getType().equals(Material.WOODEN_SWORD)
				|| item.getType().equals(Material.STONE_SWORD) || item.getType().equals(Material.COPPER_SWORD)
				|| item.getType().equals(Material.IRON_SWORD) || item.getType().equals(Material.GOLDEN_SWORD)
				|| item.getType().equals(Material.DIAMOND_SWORD) || item.getType().equals(Material.NETHERITE_SWORD))) {
			if (skill.getId().equals("WAR_03")) {
				needLoading = true;
				p.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 10 * second, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10 * second, 0));
			}
			if (skill.getId().equals("WAR_04")) {
				needLoading = true;
				p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20 * second, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 20 * second, 0));
			}
		}
		if (pSpe.equals(SpeCode.PRI.toString()) && item.getType().equals(Material.BOOK)) {
			if (skill.getId().equals("PRI_01")) {
				needLoading = true;
				this._transScale(p, false);
			}
		}
		if (pSpe.equals(SpeCode.DRU.toString()) && item.getType().equals(Material.FEATHER)) {
			if (skill.getId().equals("DRU_02")) {
				needLoading = true;
				this._transScale(p, true);
			}
			if (skill.getId().equals("DRU_03")) {
				needLoading = true;
				p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * second, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 20 * second, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 20 * second, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20 * second, 0));
			}
			if (skill.getId().equals("DRU_04")) {
				needLoading = true;
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 5 * second, 20));
				p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 5 * second, 2));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5 * second, 2));
			}
		}
		if (needLoading) {
			File messagesFile = new File("plugins/MultiSpe/lang_messages.yml");
			FileConfiguration messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
			p.sendMessage(ChatColor.AQUA + messagesConfig.getString(pLanguage + ".touse") + ChatColor.WHITE
					+ (pIsFrench ? skill.getNameFr() : skill.getNameEn()));
			Cooldown rt = new Cooldown(plugin, p);
			rt.start();
		}
	}

	public void _transScale(Player p, Boolean setGiant) {
		AttributeInstance scaleAttr = p.getAttribute(Attribute.SCALE);
		AttributeInstance speedAttr = p.getAttribute(Attribute.MOVEMENT_SPEED);
		AttributeInstance attackAttr = p.getAttribute(Attribute.ATTACK_DAMAGE);

		if (scaleAttr == null || speedAttr == null || attackAttr == null)
			return;

		if (setGiant) {
			scaleAttr.setBaseValue(scaleAttr.getBaseValue() * 4);
			speedAttr.setBaseValue(speedAttr.getBaseValue() * 3);
			attackAttr.setBaseValue(attackAttr.getBaseValue() * 2);
			p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 20 * second, 0));
		} else {
			scaleAttr.setBaseValue(scaleAttr.getBaseValue() / 8);
			speedAttr.setBaseValue(speedAttr.getBaseValue() * 1.5);
			attackAttr.setBaseValue(attackAttr.getBaseValue() * 2);
		}

		World w = p.getWorld();
		w.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, 0.5f, 0.5f);

		new BukkitRunnable() {
			@Override
			public void run() {
				scaleAttr.setBaseValue(1);
				speedAttr.setBaseValue(0.1);
				attackAttr.setBaseValue(1);
				w.playSound(p.getLocation(), Sound.ENTITY_GENERIC_DRINK, 0.5f, 0.5f);
			}
		}.runTaskLater(plugin, 20 * second);
	}
}
