package me.srgantmoomoo.lasventuras.api.event.alpineevent;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.lwjgl.input.Mouse;

import com.google.common.collect.Maps;
import com.google.common.eventbus.Subscribe;

import me.srgantmoomoo.lasventuras.api.event.alpineevent.events.PacketEvent;
import me.srgantmoomoo.lasventuras.api.event.alpineevent.events.PlayerJoinEvent;
import me.srgantmoomoo.lasventuras.api.event.alpineevent.events.PlayerLeaveEvent;
import me.srgantmoomoo.lasventuras.api.event.customevent.listeners.TickEvent;
import me.srgantmoomoo.lasventuras.client.LasVenturas;
import me.srgantmoomoo.lasventuras.client.module.ModuleManager;
import me.srgantmoomoo.external.me.zero.alpine.listener.EventHandler;
import me.srgantmoomoo.external.me.zero.alpine.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.SPacketPlayerListItem;

public class EventProcessor {

	public static EventProcessor instance;
	Minecraft mc = Minecraft.getMinecraft();

	public EventProcessor() {
		instance = this;
	}
	
	@EventHandler
    private Listener<String> stringListener = new Listener<>(str -> {
        System.out.println(str);
    });

	public void onTick(TickEvent.ClientTickEvent event) {
		if (mc.player != null) {
			ModuleManager.onUpdate();
		}
	}

	public void onWorldRender(RenderWorldLastEvent event) {
		if (event.isCanceled()) {
			return;
		}
		ModuleManager.onWorldRender(event);
	}

	public void onRender(RenderGameOverlayEvent.Post event) {
		LasVenturas.EVENT_BUS.post(event);
		if(event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
			ModuleManager.onRender();
		}
	}

	public void onMouseInput(InputEvent.MouseInputEvent event) {
		if(Mouse.getEventButtonState()) {
			LasVenturas.EVENT_BUS.post(event);
		}
	}

	public void onRenderScreen(RenderGameOverlayEvent.Text event) {
		LasVenturas.EVENT_BUS.post(event);
	}

	public void onChatReceived(ClientChatReceivedEvent event) {
		LasVenturas.EVENT_BUS.post(event);
	}

	public void onAttackEntity(AttackEntityEvent event) {
		LasVenturas.EVENT_BUS.post(event);
	}

	public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
		LasVenturas.EVENT_BUS.post(event);
	}

	public void onDrawBlockHighlight(DrawBlockHighlightEvent event) {
		LasVenturas.EVENT_BUS.post(event);
	}

	public void onRenderBlockOverlay(RenderBlockOverlayEvent event) {
		LasVenturas.EVENT_BUS.post(event);
	}

	public void onLivingDamage(LivingDamageEvent event) {
		LasVenturas.EVENT_BUS.post(event);
	}
	
	public void onLivingEntityUseItemFinish(LivingEntityUseItemEvent.Finish event) {
		LasVenturas.EVENT_BUS.post(event);
	}

	public void onInputUpdate(InputUpdateEvent event) {
		LasVenturas.EVENT_BUS.post(event);
	}

	public void onLivingDeath(LivingDeathEvent event) {
		LasVenturas.EVENT_BUS.post(event);
	}

	public void onPlayerPush(PlayerSPPushOutOfBlocksEvent event) {
		LasVenturas.EVENT_BUS.post(event);
	}

	public void onWorldUnload(WorldEvent.Unload event) {
		LasVenturas.EVENT_BUS.post(event);
	}

	public void onWorldLoad(WorldEvent.Load event) {
		LasVenturas.EVENT_BUS.post(event);
	}

	@EventHandler
	private final Listener<PacketEvent.Receive> receiveListener = new Listener<>(event -> {
		if (event.getPacket() instanceof SPacketPlayerListItem) {
			SPacketPlayerListItem packet = (SPacketPlayerListItem) event.getPacket();
			if (packet.getAction() == SPacketPlayerListItem.Action.ADD_PLAYER) {
				for (SPacketPlayerListItem.AddPlayerData playerData : packet.getEntries()) {
					if (playerData.getProfile().getId() != mc.session.getProfile().getId()) {
						new Thread(() -> {
							String name = resolveName(playerData.getProfile().getId().toString());
							if (name != null) {
								if (mc.player != null && mc.player.ticksExisted >= 1000) {
									LasVenturas.EVENT_BUS.post(new PlayerJoinEvent(name));
								}
							}
						}).start();
					}
				}
			}
			if (packet.getAction() == SPacketPlayerListItem.Action.REMOVE_PLAYER) {
				for (SPacketPlayerListItem.AddPlayerData playerData : packet.getEntries()) {
					if (playerData.getProfile().getId() != mc.session.getProfile().getId()) {
						new Thread(() -> {
							final String name = resolveName(playerData.getProfile().getId().toString());
							if (name != null) {
								if (mc.player != null && mc.player.ticksExisted >= 1000) {
									LasVenturas.EVENT_BUS.post(new PlayerLeaveEvent(name));
								}
							}
						}).start();
					}
				}
			}
		}
	});

	private final Map<String, String> uuidNameCache = Maps.newConcurrentMap();

	public String resolveName(String uuid) {
		uuid = uuid.replace("-", "");
		if (uuidNameCache.containsKey(uuid)) {
			return uuidNameCache.get(uuid);
		}

		final String url = "https://api.mojang.com/user/profiles/" + uuid + "/names";
		try {
			final String nameJson = IOUtils.toString(new URL(url));
			if (nameJson != null && nameJson.length() > 0) {
				final JSONArray jsonArray = (JSONArray) JSONValue.parseWithException(nameJson);
				if (jsonArray != null) {
					final JSONObject latestName = (JSONObject) jsonArray.get(jsonArray.size() - 1);
					if (latestName != null) {
						return latestName.get("name").toString();
					}
				}
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	//public Freecam freecam;
	
	public void init() {
		LasVenturas.EVENT_BUS.subscribe(this);
		LasVenturas.EVENT_BUS.post("eventTest");
	}
}