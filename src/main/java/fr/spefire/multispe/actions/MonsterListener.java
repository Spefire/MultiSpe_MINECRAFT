package fr.spefire.multispe.actions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

import fr.spefire.multispe.MultiSpe;

public class MonsterListener implements Listener {

	@SuppressWarnings("unused")
	private final MultiSpe plugin;

	public MonsterListener(MultiSpe plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onEntityTarget(EntityTargetLivingEntityEvent event) {
		if (!event.getEntity().hasMetadata("ms_owner")) {
			return;
		}

		if (event.getTarget() == null) {
			return;
		}

		String ownerId = event.getEntity().getMetadata("ms_owner").get(0).asString();
		if (event.getTarget().getUniqueId().toString().equals(ownerId)) {
			event.setCancelled(true);
		}
	}
}