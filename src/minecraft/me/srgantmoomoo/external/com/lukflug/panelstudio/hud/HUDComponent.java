package me.srgantmoomoo.external.com.lukflug.panelstudio.hud;

import java.awt.Point;

import me.srgantmoomoo.external.com.lukflug.panelstudio.Context;
import me.srgantmoomoo.external.com.lukflug.panelstudio.FixedComponent;
import me.srgantmoomoo.external.com.lukflug.panelstudio.Interface;
import me.srgantmoomoo.external.com.lukflug.panelstudio.PanelConfig;
import me.srgantmoomoo.external.com.lukflug.panelstudio.theme.Renderer;

/**
 * Base class for HUD elements.
 * @author lukflug
 */
public abstract class HUDComponent implements FixedComponent {
	/**
	 * The caption of this component.
	 */
	protected String title;
	/**
	 * The {@link Renderer} for this component.
	 */
	protected Renderer renderer;
	/**
	 * Current position;
	 */
	protected Point position;
	
	public HUDComponent (String title, Renderer renderer, Point position) {
		this.title=title;
		this.renderer=renderer;
		this.position=position;
	}
	
	@Override
	public String getTitle() {
		return title;
	}

	/**
	 * Set component height.
	 */
	@Override
	public void render(Context context) {
		getHeight(context);
	}

	/**
	 * Do nothing.
	 */
	@Override
	public void handleButton(Context context, int button) {
		getHeight(context);
	}

	/**
	 * Do nothing.
	 */
	@Override
	public void handleKey(Context context, int scancode) {
		getHeight(context);
	}

	/**
	 * Do nothing.
	 */
	@Override
	public void handleScroll(Context context, int diff) {
		getHeight(context);
	}

	/**
	 * Do nothing.
	 */
	@Override
	public void exit(Context context) {
		getHeight(context);
	}

	/**
	 * Do nothing.
	 */
	@Override
	public void releaseFocus() {
	}

	/**
	 * Get the component position.
	 */
	@Override
	public Point getPosition(Interface inter) {
		return new Point(position);
	}

	/**
	 * Set the component position.
	 */
	@Override
	public void setPosition(Interface inter, Point position) {
		this.position=position;
	}

	@Override
	public void saveConfig(Interface inter, PanelConfig config) {
		config.savePositon(position);
	}

	@Override
	public void loadConfig(Interface inter, PanelConfig config) {
		Point pos=config.loadPosition();
		if (pos!=null) position=pos;
	}
}