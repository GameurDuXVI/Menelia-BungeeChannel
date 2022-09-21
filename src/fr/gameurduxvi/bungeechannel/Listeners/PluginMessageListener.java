package fr.gameurduxvi.bungeechannel.Listeners;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.gameurduxvi.bungeechannel.Main;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PluginMessageListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void OnPluginMessage(PluginMessageEvent event) {
		if(event.getTag().equals("MeneliaChannel")) {
			ByteArrayDataInput in = ByteStreams.newDataInput(event.getData());
			
			String server = in.readUTF();
			String sub = in.readUTF();
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF(server);
								
			if(sub.equals("AdminPanel_Players.Request")) {
				ProxiedPlayer player = Main.getInstance().getProxy().getPlayer(event.getReceiver().toString());
				
				String listPlayer = "";
				
				Map<String, ServerInfo> servers = Main.getInstance().getProxy().getServers();
				for(Entry<String, ServerInfo> en : servers.entrySet()) {
					ServerInfo test = en.getValue();
					for(ProxiedPlayer loopPlayer: test.getPlayers()) {
						if(listPlayer == "") {
							listPlayer = loopPlayer.getName() + "<" + en.getKey();
						}
						else {
							listPlayer = listPlayer + "#" + loopPlayer.getName() + "<" + en.getKey();
						}
					}
				}
				
				
				out.writeUTF("AdminPanel_Players.Action");
				out.writeUTF(listPlayer);
				player.getServer().sendData("MeneliaChannel", out.toByteArray());
			}	
			
			else if(sub.equals("AdminPanel_Staff.Request")) {
				ProxiedPlayer player = Main.getInstance().getProxy().getPlayer(event.getReceiver().toString());
				
				String listPlayer = "";
				
				for(ProxiedPlayer loopPlayer: Main.getInstance().getProxy().getPlayers()) {
					if(listPlayer == "") {
						listPlayer = loopPlayer.getName();
					}
					else {
						listPlayer = listPlayer + "#" + loopPlayer.getName();
					}
				}
				
				out.writeUTF("AdminPanel_Staff.Action");
				out.writeUTF(listPlayer);
				player.getServer().sendData("MeneliaChannel", out.toByteArray());
			}
			
			else if(sub.equals("AdminPanel_Player_Move.Request")) {
				//ProxiedPlayer player = Main.getInstance().getProxy().getPlayer(event.getReceiver().toString());
				String receivePlayer = in.readUTF();
				String targetServer = in.readUTF();
				ServerInfo target = Main.getInstance().getProxy().getServerInfo(targetServer);
				
				for(ProxiedPlayer loopPlayer: Main.getInstance().getProxy().getPlayers()) {
					if(loopPlayer.getName().equals(receivePlayer)) {
						loopPlayer.connect(target);
					}
				}
			}	
			
			
			else if(sub.equals("AdminPanel_Player_Message.Action")) {
				String uuid = in.readUTF();
				String message = in.readUTF();
				
				System.out.println("1");
				for(ProxiedPlayer loopPlayer: Main.getInstance().getProxy().getPlayers()) {
					System.out.println("2");
					if(loopPlayer.getUniqueId().toString().equals(uuid)) {
						System.out.println("3");
						loopPlayer.sendMessage(message);
					}
				}
			}
			
			else if(sub.equals("AdminPanel_Player_Kick.Request")) {
				//ProxiedPlayer player = Main.getInstance().getProxy().getPlayer(event.getReceiver().toString());
				String receivePlayer = in.readUTF();
				String reason = in.readUTF();
				
				for(ProxiedPlayer loopPlayer: Main.getInstance().getProxy().getPlayers()) {
					if(loopPlayer.getName().equalsIgnoreCase(receivePlayer)) {
						if(reason.equalsIgnoreCase("none")) {
							int lang = 0;//Main.getInstance().MySQLLibrary.GET_DATA_Int(Main.getInstance().MySQLLibrary.Table_Accounts, Main.getInstance().MySQLLibrary.Field_Accounts_UUID, loopPlayer.getUniqueId().toString(), Main.getInstance().MySQLLibrary.Field_Accounts_LANGUAGE);
							if(lang == 0) {
								loopPlayer.disconnect("You have been kicked from the server !");
							}
							/*else {
								loopPlayer.disconnect("Vous avez été expulsé du serveur !");
							}*/
						}
						else {
							loopPlayer.disconnect(reason);
						}
					}
				}
			}
			
			
			
			
			else if(sub.equals("AdminPanel_Server_Restart.Request")) {
				String targetServer = in.readUTF();
				
				Map<String, ServerInfo> servers = Main.getInstance().getProxy().getServers();
				for (Entry<String, ServerInfo> en : servers.entrySet()) {
					if(en.getKey().equalsIgnoreCase(targetServer)) {
						ServerInfo test = en.getValue();
						out.writeUTF("AdminPanel_Server_Restart.Action");
						test.sendData("MeneliaChannel", out.toByteArray());
						System.out.println(en.getValue() + " / " + en.getKey());
					}
				}
			}
			
			
			
			
			else if(sub.equals("AdminPanel_Player_BanTemp.Request")) {
				//ProxiedPlayer player = Main.getInstance().getProxy().getPlayer(event.getReceiver().toString());
				String playerName = in.readUTF();
				String bannedPlayerName = in.readUTF();
				String reason = in.readUTF();
				String[] args1 = reason.split(" ");
				//Accounts bannedAccount = null;
				
				boolean exist = false;
				/*for(Accounts account: Main.getInstance().getAcounts()) {
					if(account.getName().equals(bannedPlayerName)) {
						exist = true;
						bannedAccount = account;
					}
				}*/
				if(exist) {
					if(args1.length >= 2) {
						for(ProxiedPlayer loopPlayer: Main.getInstance().getProxy().getPlayers()) {
							if(loopPlayer.getName().equals(playerName)) {
								
								String[] args = reason.split(" ");
								
								Date date = new Date();
								Calendar c = Calendar.getInstance(); 
								//loopPlayer.sendMessage("§a" + c.getTime().toGMTString() + " " + c.getTime().getSeconds());
								c.setTime(date);
								String messageTimeFR = "";
								String messageTimeEN = "";
								for(int i = 1; i < args.length; i++) {
									String onlyNumber = args[i].replaceAll("[^0-9]", "");
									int number = 0;
									if(onlyNumber.length() != 0) {
										number = Integer.parseInt(onlyNumber);
									}
									
									
									
									String onlyString = args[i].replaceAll("[^a-zA-Z ]", "");
									if(onlyString.equals("s")) {
										reason.replace(number + "s", "");
										c.add(Calendar.SECOND, number);
										if(messageTimeFR.equals("")) {
											messageTimeFR = number + " secondes";
											messageTimeEN = number + " seconds";
										}
										else {
											messageTimeFR = messageTimeFR + ", " + number + " secondes";
											messageTimeEN = messageTimeFR + ", " + number + " seconds";
										}
									}
									else if(onlyString.equals("min")) {
										reason.replace(number + "min", "");
										c.add(Calendar.MINUTE, number);
										if(messageTimeFR.equals("")) {
											messageTimeFR = number + " minutes";
											messageTimeEN = number + " minutes";
										}
										else {
											messageTimeFR = messageTimeFR + ", " + number + " minutes";
											messageTimeEN = messageTimeEN + ", " + number + " minutes";
										}
									}
									else if(onlyString.equals("h")) {
										reason.replace(number + "h", "");
										c.add(Calendar.HOUR, number);
										if(messageTimeFR.equals("")) {
											messageTimeFR = number + " heures";
											messageTimeEN = number + " hours";
										}
										else {
											messageTimeFR = messageTimeFR + ", " + number + " heures";
											messageTimeEN = messageTimeEN + ", " + number + " hours";
										}
									}
									else if(onlyString.equals("d")) {
										reason.replace(number + "d", "");
										c.add(Calendar.DATE, number);
										if(messageTimeFR.equals("")) {
											messageTimeFR = number + " jours";
											messageTimeEN = number + " days";
										}
										else {
											messageTimeFR = messageTimeFR + ", " + number + " jours";
											messageTimeEN = messageTimeEN + ", " + number + " days";
										}
									}
									else if(onlyString.equals("w")) {
										reason.replace(number + "w", "");
										c.add(Calendar.DATE, number * 7);
										if(messageTimeFR.equals("")) {
											messageTimeFR = number + " semaines";
											messageTimeEN = number + " weeks";
										}
										else {
											messageTimeFR = messageTimeFR + ", " + number + " semaines";
											messageTimeEN = messageTimeEN + ", " + number + " weeks";
										}
									}
									else if(onlyString.equals("m")) {
										reason.replace(number + "m", "");
										c.add(Calendar.MONTH, number);
										if(messageTimeFR.equals("")) {
											messageTimeFR = number + " mois";
											messageTimeEN = number + " months";
										}
										else {
											messageTimeFR = messageTimeFR + ", " + number + " mois";
											messageTimeEN = messageTimeEN + ", " + number + " months";
										}
									}
									else if(onlyString.equals("y")) {
										reason.replace(number + "y", "");
										c.add(Calendar.YEAR, number);
										if(messageTimeFR.equals("")) {
											messageTimeFR = number + " année";
											messageTimeEN = number + " years";
										}
										else {
											messageTimeFR = messageTimeFR + ", " + number + " année";
											messageTimeEN = messageTimeEN + ", " + number + " years";
										}
									}
								}
								//loopPlayer.sendMessage("§c" + c.getTime().toGMTString() + " " + c.getTime().getSeconds());
								//loopPlayer.sendMessage(c.getTime().getTime() + "");
								/*for(Accounts account: Main.getInstance().getAcounts()) {
									if(account.getUUID().equals(loopPlayer.getUniqueId())) {
										if(account.getLang().equals("FR")) {
											loopPlayer.sendMessage("§cYou have temporarly banned " + bannedAccount.getName() + " for " + messageTimeFR);
										}
										else {
											loopPlayer.sendMessage("§cYou have temporarly banned " + bannedAccount.getName() + " for " + messageTimeEN);
										}
									}
								}*/
								/*for(ProxiedPlayer loopPlayer2: Main.getInstance().getProxy().getPlayers()) {
									for(Accounts account: Main.getInstance().getAcounts()) {
										if(account.getUUID().equals(loopPlayer2.getUniqueId())) {
											if(account.getLang().equals("FR")) {
												loopPlayer2.sendMessage("§6[§5Menelia§6] §e" + bannedAccount.getName() + "§6 a été banni pendant " + messageTimeFR + " pour:" + reason);
											}
											else {
												loopPlayer2.sendMessage("§6[§5Menelia§6] §e" + bannedAccount.getName() + "§6 has been banned for " + messageTimeEN + " :" + reason);
											}
										}
									}
								}*/
								//Main.getInstance().tempBanManagerClass.getConfig().set(bannedAccount.getUUID() + ".name", bannedAccount.getName());
								//Main.getInstance().tempBanManagerClass.getConfig().set(bannedAccount.getUUID() + ".banned.from", date.getTime());
								//Main.getInstance().tempBanManagerClass.getConfig().set(bannedAccount.getUUID() + ".banned.to", c.getTime().getTime());
								Main.getTempBanManager().saveConfig();
								/*for(ProxiedPlayer loopPlayer2: Main.getInstance().getProxy().getPlayers()) {
									if(loopPlayer2.getName().equals(bannedAccount.getName())) {
										if(bannedAccount.getLang().equals("FR")) {
											loopPlayer2.disconnect("§cVous avez été banni pendant " + messageTimeFR + " pour:" + reason);
										}
										else {
											
										}
										loopPlayer2.disconnect("§cYou have been banned for " + messageTimeEN + " :" + reason);
									}
								}*/
							}
						}
					}
					else {
						for(ProxiedPlayer loopPlayer: Main.getInstance().getProxy().getPlayers()) {
							if(loopPlayer.getName().equals(playerName)) {
								loopPlayer.sendMessage("§c/tempban " + bannedPlayerName + " <time> <reason>");
							}
						}
					}
				}
				else {
					for(ProxiedPlayer loopPlayer: Main.getInstance().getProxy().getPlayers()) {
						if(loopPlayer.getName().equals(playerName)) {
							loopPlayer.sendMessage("§c\"" + bannedPlayerName + "\" don't exist !");
							loopPlayer.sendMessage(reason);
						}
					}
				}
			}
			
		}
	}
	
}
