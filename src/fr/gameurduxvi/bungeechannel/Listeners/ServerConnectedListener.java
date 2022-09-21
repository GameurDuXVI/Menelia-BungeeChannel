package fr.gameurduxvi.bungeechannel.Listeners;

import fr.gameurduxvi.bungeechannel.Main;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerConnectedListener implements Listener {

	@EventHandler
	public void onChangeServer(ServerConnectedEvent e) {
		Main.getFunctions().updateOnlinePlayers();
	}
	
}
