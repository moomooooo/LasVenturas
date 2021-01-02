package me.srgantmoomoo.lasventuras.api.event.alpineevent.events;

import me.srgantmoomoo.lasventuras.api.event.alpineevent.Event;
import net.minecraft.entity.Entity;

public class TotemPopEvent extends Event {

	private final Entity entity;

	public TotemPopEvent(Entity entity) {
		super();
		this.entity = entity;
	}

	public Entity getEntity() {
		return this.entity;
	}
}