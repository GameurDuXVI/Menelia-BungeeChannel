package fr.gameurduxvi.bungeechannel.Listeners;

import fr.gameurduxvi.bungeechannel.Main;
import net.md_5.bungee.api.ServerPing.Protocol;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyPingListener implements Listener {
	
	@EventHandler
	public void onPing(ProxyPingEvent e) {
		e.getResponse().setVersion(new Protocol("Fermé §7" + Main.getInstance().getProxy().getPlayers().size() + "§8/§7500", 999));
		e.getResponse().setDescriptionComponent(new TextComponent(Main.getInstance().MOTD.replace("&", "§")));
	}
	
}
