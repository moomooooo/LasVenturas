package me.srgantmoomoo.lasventuras.api.event.alpineevent.events;

import me.srgantmoomoo.lasventuras.api.event.alpineevent.Event;
import net.minecraft.entity.MoverType;

public class PlayerMoveEvent extends Event {

	MoverType type;
	public double x;
	public double y;
	public double z;

	public PlayerMoveEvent(MoverType moverType, double x, double y, double z) {
		super();
		this.type = moverType;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public MoverType getType() {
		return this.type;
	}

	public void setType(MoverType type) {
		this.type = type;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public double getZ() {
		return this.z;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setZ(double z) {
		this.z = z;
	}
}