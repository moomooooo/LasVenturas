package me.srgantmoomoo.lasventuras.client.setting.settings;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.external.com.lukflug.panelstudio.theme.Renderer;
import me.srgantmoomoo.lasventuras.client.module.Module;
import me.srgantmoomoo.lasventuras.client.setting.Setting;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

public class KeybindSetting extends Setting implements me.srgantmoomoo.external.com.lukflug.panelstudio.settings.KeybindSetting {
	
	public int code;
	
	public KeybindSetting(int code) {
		this.name = "KeyBind";
		this.code = code;
	}
	
	public KeybindSetting(Renderer componentRenderer, Module module) {
		// TODO Auto-generated constructor stub
	}

	public int getKeyCode() {
		return this.code;
	}
	
	public void setKeyCode(int code) {
		this.code = code;
	}

	@Override
	public int getKey() {
		return code;
	}

	@Override
	public String getKeyName() {
		return Keyboard.getKeyName(code);
	}

	@Override
	public void setKey(int key) {
		code=key;
	}

}
