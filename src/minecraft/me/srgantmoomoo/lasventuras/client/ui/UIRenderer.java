package me.srgantmoomoo.lasventuras.client.ui;

import java.awt.Color;
import java.awt.Font;

import me.srgantmoomoo.lasventuras.api.event.customevent.listeners.EventRenderGUI;
import me.srgantmoomoo.lasventuras.api.utils.fontrenderer.GlyphPage;
import me.srgantmoomoo.lasventuras.api.utils.fontrenderer.GlyphPageFontRenderer;
import me.srgantmoomoo.lasventuras.client.LasVenturas;
import me.srgantmoomoo.lasventuras.client.module.Module;
import me.srgantmoomoo.lasventuras.client.module.ModuleManager;
import me.srgantmoomoo.lasventuras.client.ui.name.Name;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class UIRenderer extends GuiScreen {
	private Minecraft mc = Minecraft.getMinecraft();
	private GlyphPageFontRenderer renderer;
	
	public void draw() {
		ScaledResolution sr = new ScaledResolution(mc);
		FontRenderer fr = mc.fontRendererObj;
		
		char[] chars = new char[256];
		for(int i = 0; i < chars.length; i++) {
			chars[i] = (char) i;
		}
		GlyphPage glyphPage = new GlyphPage(new Font("Comic Sans MS", Font.PLAIN, 20),true,true);
		glyphPage.generateGlyphPage(chars);
		glyphPage.setupTexture();
		renderer = new GlyphPageFontRenderer(glyphPage, glyphPage, glyphPage, glyphPage);
		
		// NAME
		int x = 1;
		final int[] counter = {1};
		for (Name nam : LasVenturas.nameManager.getNameList()) {
			fr.drawStringWithShadow(nam.getName(), x-1, 0, rainbow(counter[0] * -300));
			//renderer.drawString(nam.getName(), x-1, 0, rainbow(counter[0] * -300), true);
		x += fr.getStringWidth(nam.getName());
		counter[0]++;
		}
		
		// ARRAYLIST 
		int y = 1;
		for (Module mod : LasVenturas.moduleManager.getModuleList()) {
			//Gui.drawRect(sr.getScaledWidth() - fr.getStringWidth(module.name) - 5, offset1, sr.getScaledWidth() - fr.getStringWidth(module.name) -3, 1 + fr.FONT_HEIGHT + offset2, 0x90000000); 
			//Gui.drawRect(sr.getScaledWidth() - fr.getStringWidth(module.name) - 3, offset1, sr.getScaledWidth(), 1 + fr.FONT_HEIGHT + offset2, 0x90000000);
			fr.drawStringWithShadow(mod.getName(),sr.getScaledWidth() - fr.getStringWidth(mod.getName()), y, rainbow(counter[0] * -300));
			y += fr.FONT_HEIGHT;
			counter[0]++;
			
		}
		ModuleManager.onEvent(new EventRenderGUI());
	}
	
	public static int rainbow(int delay) {
		double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
		rainbowState %= 360;
		return Color.getHSBColor((float) (rainbowState / 360.0f), 0.5f, 1f).getRGB();
	}
}