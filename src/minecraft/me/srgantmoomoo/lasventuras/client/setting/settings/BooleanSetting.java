package me.srgantmoomoo.lasventuras.client.setting.settings;

import me.srgantmoomoo.external.com.lukflug.panelstudio.settings.Toggleable;
import me.srgantmoomoo.lasventuras.client.LasVenturas;
import me.srgantmoomoo.lasventuras.client.module.Module;
import me.srgantmoomoo.lasventuras.client.setting.Setting;
/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

public class BooleanSetting extends Setting implements Toggleable {
  public boolean enabled;
  
  public BooleanSetting(String name, Module parent, boolean enabled) {
    this.name = name;
    this.parent = parent;
    this.enabled = enabled;
  }
  
  public boolean isEnabled() {
    return this.enabled;
  }
  
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
    
    if(LasVenturas.saveLoad != null) {
    	LasVenturas.saveLoad.save();
	}
  }
  
  public void toggle() {
    this.enabled = !this.enabled;
    
    if(LasVenturas.saveLoad != null) {
    	LasVenturas.saveLoad.save();
	}
  }

@Override
public boolean isOn() {
	return this.isEnabled();
}
}
