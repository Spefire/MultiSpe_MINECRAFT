package fr.spefire.multispe.actions;

import java.io.File;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.spefire.multispe.Cooldown;
import fr.spefire.multispe.MultiSpe;
import fr.spefire.multispe.models.Language;
import fr.spefire.multispe.models.Skill;
import fr.spefire.multispe.models.Spe;
import fr.spefire.multispe.models.SpeCode;

public class AtkSkills implements Listener {

	private final MultiSpe plugin;
	private final int second = 20;

	public AtkSkills(MultiSpe plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerDamageAtt(EntityDamageByEntityEvent event) {
		Entity e = event.getDamager();
		Entity d = event.getEntity();
		if (!(e instanceof Player) || !(d instanceof LivingEntity))
			return;
		//
		Player p = (Player) e;
		LivingEntity m = (LivingEntity) d;
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
		List<Spe> spes = Spe.getAllSpes();
		Spe spe = (pSpe != null) ? spes.stream().filter(s -> pSpe.equals(s.getId())).findFirst().orElse(null) : null;
		if (spe == null)
			return;
		//
		ItemStack item = p.getInventory().getItemInMainHand();
		Skill skill = spe.getSkill(pIndexSkill);
		Boolean needLoading = false;
		if (pSpe.equals(SpeCode.WAR.toString()) && (item.getType().toString().equals(Material.WOODEN_SWORD.toString())
				|| item.getType().toString().equals(Material.STONE_SWORD.toString())
				|| item.getType().toString().equals(Material.COPPER_SWORD.toString())
				|| item.getType().toString().equals(Material.IRON_SWORD.toString())
				|| item.getType().toString().equals(Material.GOLDEN_SWORD.toString())
				|| item.getType().toString().equals(Material.DIAMOND_SWORD.toString())
				|| item.getType().toString().equals(Material.NETHERITE_SWORD.toString()))) {
			if (skill.getId().equals("WAR_01")) {
				needLoading = true;
				m.damage(1);
				m.setVelocity(p.getEyeLocation().getDirection());
				m.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, second / 3, 10));
			}
			if (skill.getId().equals("WAR_02")) {
				needLoading = true;
				m.getWorld().createExplosion(m.getLocation(), 0);
				m.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, second / 3, 10));
				p.setVelocity(p.getEyeLocation().getDirection().multiply(5));
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

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayer(EntityDamageByEntityEvent event) {
		Entity e = event.getEntity();
		Entity d = event.getDamager();
		if (!(d instanceof Arrow))
			return;
		//
		Arrow a = (Arrow) d;
		if (!(e instanceof LivingEntity) || !(a.getShooter() instanceof Player))
			return;
		//
		LivingEntity m = (LivingEntity) e;
		Player p = (Player) a.getShooter();
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
		List<Spe> spes = Spe.getAllSpes();
		Spe spe = (pSpe != null) ? spes.stream().filter(s -> pSpe.equals(s.getId())).findFirst().orElse(null) : null;
		if (spe == null)
			return;
		//
		Skill skill = spe.getSkill(pIndexSkill);
		Boolean needLoading = false;
		if (pSpe.equals(SpeCode.ARC.toString())) {
			if (skill.getId().equals("ARC_01")) {
				needLoading = true;
				m.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 10 * second, 1));
				m.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 10 * second, 0));
				m.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 10 * second, 0));
			}
			if (skill.getId().equals("ARC_02")) {
				needLoading = true;
				p.getWorld().strikeLightningEffect(m.getLocation());
				m.setFireTicks(5 * second);
				m.damage(2);
			}
			if (skill.getId().equals("ARC_03")) {
				needLoading = true;
				m.getWorld().createExplosion(m.getLocation(), 0);
				m.setVelocity(m.getLocation().getDirection());
				m.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, second / 3, 10));
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
}