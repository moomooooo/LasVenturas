package me.srgantmoomoo.external.com.lukflug.panelstudio.settings;

import me.srgantmoomoo.external.com.lukflug.panelstudio.Context;
import me.srgantmoomoo.external.com.lukflug.panelstudio.FocusableComponent;
import me.srgantmoomoo.external.com.lukflug.panelstudio.Interface;
import me.srgantmoomoo.external.com.lukflug.panelstudio.theme.Renderer;

/**
 * Component representing a boolean-valued setting.
 * @author lukflug
 */
public class BooleanComponent extends FocusableComponent {
	/**
	 * The setting in question.
	 */
	protected Toggleable setting;
	
	/**
	 * Constructor.
	 * @param title name of the setting
	 * @param renderer {@link Renderer} for the component
	 * @param setting the setting in question
	 */
	public BooleanComponent(String title, Renderer renderer, Toggleable setting) {
		super(title,renderer);
		this.setting=setting;
	}
	
	/**
	 * Render the component, by drawing an active box, if the boolean is true, and an inactive one, if the boolean is false. 
	 */
	@Override
	public void render (Context context) {
		super.render(context);
		renderer.renderTitle(context,title,hasFocus(context),setting.isOn());
	}
	
	/**
	 * Handle a mouse button state change.
	 * Inverts the boolean, if clicked.
	 */
	@Override
	public void handleButton (Context context, int button) {
		super.handleButton(context,button);
		if (button==Interface.LBUTTON && context.isClicked()) {
			setting.toggle();
		}
	}
}