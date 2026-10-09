package fr.spefire.multispe.actions;

import java.io.File;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import fr.spefire.multispe.Cooldown;
import fr.spefire.multispe.MultiSpe;
import fr.spefire.multispe.models.Language;
import fr.spefire.multispe.models.Skill;
import fr.spefire.multispe.models.Spe;
import fr.spefire.multispe.models.SpeCode;

public class ZoneSkills implements Listener {

	private final MultiSpe plugin;
	private final int range = 8;
	private final int second = 20;

	public ZoneSkills(MultiSpe plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getHand() != EquipmentSlot.HAND
				|| (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK))
			return;
		//
		Player p = event.getPlayer();
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
		List<LivingEntity> entities = p.getNearbyEntities(range, range, range).stream()
				.filter(e -> e instanceof LivingEntity).map(e -> (LivingEntity) e).toList();
		ItemStack item = p.getInventory().getItemInMainHand();
		Skill skill = spe.getSkill(pIndexSkill);
		Boolean needLoading = false;
		if (pSpe.equals(SpeCode.WIZ.toString()) && item.getType().equals(Material.STICK)) {
			if (skill.getId().equals("WIZ_01")) {
				needLoading = true;
				for (LivingEntity entity : entities) {
					entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 5 * second, 3));
					entity.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 5 * second, 3));
					entity.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 5 * second, 3));
				}
			}
			if (skill.getId().equals("WIZ_02")) {
				needLoading = true;
				for (LivingEntity entity : entities) {
					p.getWorld().strikeLightningEffect(entity.getLocation());
					entity.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 10 * second, 20));
					entity.damage(4);
				}
			}
			if (skill.getId().equals("WIZ_03")) {
				needLoading = true;
				for (LivingEntity entity : entities) {
					entity.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, second / 3, 20));
					entity.setFireTicks(10 * second);
				}
			}
			if (skill.getId().equals("WIZ_04")) {
				needLoading = true;
				p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 10 * second, 20));
				p.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, p.getLocation(), 80, 2, 1, 2, 0.01);
				p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 0.5f, 0.5f);
				for (LivingEntity entity : entities) {
					entity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 2 * second, 20));
				}
			}
		}
		if (pSpe.equals(SpeCode.PRI.toString()) && item.getType().equals(Material.BOOK)) {
			if (skill.getId().equals("PRI_02")) {
				needLoading = true;
				p.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, second, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5 * second, 0));
				for (LivingEntity entity : entities) {
					entity.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, second, 0));
					entity.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5 * second, 0));
				}
			}
			if (skill.getId().equals("PRI_03")) {
				needLoading = true;
				p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 20 * second, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 20 * second, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 10 * second, 0));
				for (LivingEntity entity : entities) {
					entity.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 20 * second, 0));
					entity.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 20 * second, 0));
					entity.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 10 * second, 0));
				}
			}
			if (skill.getId().equals("PRI_04")) {
				needLoading = true;
				for (LivingEntity entity : entities) {
					Vector direction = entity.getLocation().toVector().subtract(p.getLocation().toVector()).normalize();
					direction.multiply(2);
					direction.setY(1.25);
					entity.setVelocity(direction);
				}
			}
		}
		if (pSpe.equals(SpeCode.NEC.toString()) && (item.getType().equals(Material.WOODEN_HOE)
				|| item.getType().equals(Material.STONE_HOE) || item.getType().equals(Material.COPPER_HOE)
				|| item.getType().equals(Material.IRON_HOE) || item.getType().equals(Material.GOLDEN_HOE)
				|| item.getType().equals(Material.DIAMOND_HOE) || item.getType().equals(Material.NETHERITE_HOE))) {
			if (skill.getId().equals("NEC_03")) {
				needLoading = true;
				this._spawnSkeletons(p);
			}
			if (skill.getId().equals("NEC_04")) {
				needLoading = true;
				for (LivingEntity entity : entities) {
					entity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 10 * second, 0));
					entity.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 * second, 0));
					entity.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 20 * second, 0));
				}
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

	private void _spawnSkeletons(Player p) {
		int count = 2;
		double radius = 3.0;

		Location center = p.getLocation();
		World world = p.getWorld();

		for (int i = 0; i < count; i++) {
			double angle = Math.random() * 2 * Math.PI;
			double dx = Math.cos(angle) * radius;
			double dz = Math.sin(angle) * radius;

			Location spawnLoc = center.clone().add(dx, 0, dz);
			spawnLoc.setY(world.getHighestBlockYAt(spawnLoc) + 1);

			Skeleton sk = world.spawn(spawnLoc, Skeleton.class);
			sk.setCustomName(p.getName());
			sk.setCustomNameVisible(false);
			sk.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 30 * second, 0));
			sk.setMetadata("ms_owner", new FixedMetadataValue(plugin, p.getUniqueId().toString()));
		}
	}
}