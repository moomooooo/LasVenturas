/*package me.srgantmoomoo.lasventuras.client.setting.settings;

import java.awt.Color;

import me.srgantmoomoo.lasventuras.client.module.Module;
import me.srgantmoomoo.lasventuras.client.setting.Setting;

public class ColorSetting extends Setting implements me.srgantmoomoo.external.com.lukflug.panelstudio.settings.ColorSetting {

	private boolean rainbow;
	private JColor value;
	
	public ColorSetting (String name, Module parent, final JColor value) {
		this.name = name;
		this.parent = parent;
		this.value=value;
	}
	
	public JColor getValue() {
		if (rainbow) {
			return JColor.fromHSB((System.currentTimeMillis()%(360*32))/(360f * 32),1,1);
		}
		return this.value;
	}
	
	public void setValue (boolean rainbow, final JColor value) {
		this.rainbow = rainbow;
		this.value = value;
	}
	
	public int toInteger() {
		return this.value.getRGB()&0xFFFFFF+(rainbow?1:0)*0x1000000;
	}
	
	public void fromInteger (int number) {
		this.value = new JColor(number&0xFFFFFF);
		this.rainbow = ((number&0x1000000)!=0);
	}
	
	public JColor getColor() {
		return this.value;
	}

	@Override
	public boolean getRainbow() {
		return this.rainbow;
	}

	@Override
	public void setValue(Color value) {
		setValue(getRainbow(),new JColor(value));
	}

	@Override
	public void setRainbow(boolean rainbow) {
		this.rainbow=rainbow;
	}
}*/