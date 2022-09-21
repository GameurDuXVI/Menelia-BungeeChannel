package fr.gameurduxvi.bungeechannel.Listeners;

import fr.gameurduxvi.bungeechannel.Main;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerDisconnectListener implements Listener {
	
	@EventHandler
	public void onQuit(PlayerDisconnectEvent e) {
		Main.getFunctions().updateOnlinePlayers();
	}
	
}
