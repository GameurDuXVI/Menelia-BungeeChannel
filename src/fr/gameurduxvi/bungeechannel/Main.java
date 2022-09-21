package fr.gameurduxvi.bungeechannel;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import fr.gameurduxvi.bungeechannel.Configs.Ban;
import fr.gameurduxvi.bungeechannel.Configs.TempBan;
import fr.gameurduxvi.bungeechannel.Listeners.PlayerDisconnectListener;
import fr.gameurduxvi.bungeechannel.Listeners.ProxyPingListener;
import fr.gameurduxvi.bungeechannel.Listeners.ServerConnectedListener;
import fr.gameurduxvi.bungeechannel.MySQL.Library;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin{
	
	private static Main instance;
	public static Main getInstance() {
		return instance;
	}
	
	
	private static Functions functionsInstance;
	public static Functions getFunctions() {
		return functionsInstance;
	}
	
	
	private static Ban banManagerInstance;
	public static Ban getBanManager() {
		return banManagerInstance;
	}
	
	
	public static TempBan tempBanManagerInstance;
	public static TempBan getTempBanManager() {
		return tempBanManagerInstance;
	}
	
	
	// Plugin properties
	public String pluginPrefix = "§e[Menelia BungeeCord]";
	public String MOTD = "";
	
	//SQL Stuff
	public Library MySQLLibrary;
	private String SQLHost;
	private String SQLDatabase; 
	private String SQLUsername; 
	private String SQLPasseword;
	private int SQLPort = 3306;
	
	Connection con = null;
	
	
	
	
	@SuppressWarnings("deprecation")
	public void onEnable() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+1"));
		
		// Plugin loading Message
		getProxy().getConsole().sendMessage(" ");
		getProxy().getConsole().sendMessage(" ");
		getProxy().getConsole().sendMessage(pluginPrefix + " Enabling BungeeChannel");
		getProxy().getConsole().sendMessage(pluginPrefix + "=========================================================");
		
		// Main plugin instance
		instance = this;
		functionsInstance = new Functions();
		
		
		// Creating if plugin directory doesn't exist
		File dir = new File("plugins/Menelia/");
		if (!dir.exists()) {
			dir.mkdir();
		}
		
		try {
			Object obj = new JSONParser().parse(new FileReader("../../data/config.json"));
			
			JSONObject jo = (JSONObject) obj;
			
			@SuppressWarnings("unchecked")
			Map<String, String> mysql = ((Map<String, String>)jo.get("mysql"));
			
			Iterator<Entry<String, String>> itr1 = mysql.entrySet().iterator(); 
			while (itr1.hasNext()) {
				Entry<String, String> pair = itr1.next();
				switch (pair.getKey()) {
					case "Host":
						SQLHost = pair.getValue();
						break;
					case "DatabaseName":
						SQLDatabase = pair.getValue();
						break;
					case "Username":
						SQLUsername = pair.getValue();
						break;
					case "Password":
						SQLPasseword = pair.getValue();
						break;
					case "Port":
						SQLPort = Integer.parseInt(pair.getValue());
						break;
					default:
						break;
				} 
			}
			
		} catch (IOException | ParseException e1) {
			e1.printStackTrace();
			getProxy().getConsole().sendMessage(e1.getMessage());
		}
		
		try {
			synchronized (this) {
				Class.forName("com.mysql.jdbc.Driver");
				
				con = DriverManager.getConnection("jdbc:mysql://" + SQLHost + ":" + SQLPort + "/" + SQLDatabase, SQLUsername, SQLPasseword);
			}			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Object obj = new JSONParser().parse(new FileReader("../../data/config.json"));
			
			JSONObject jo = (JSONObject) obj;
			
			@SuppressWarnings("unchecked")
			Map<String, String> mysql = ((Map<String, String>)jo.get("bungeecord"));
			
			Iterator<Entry<String, String>> itr1 = mysql.entrySet().iterator(); 
			while (itr1.hasNext()) {
				Entry<String, String> pair = itr1.next();
				switch (pair.getKey()) {
					case "motd":
						MOTD = pair.getValue();
						break;
					default:
						break;
				} 
			}
			
		} catch (IOException | ParseException e1) {
			e1.printStackTrace();
			getProxy().getConsole().sendMessage(e1.getMessage());
		}
		
		// Saving other instances
		banManagerInstance = new Ban();
		getBanManager().load();
		tempBanManagerInstance = new TempBan();
		getTempBanManager().load(true);
		MySQLLibrary = new Library();
		
		// Setting BungeeCord Channeling
		getProxy().registerChannel("MeneliaChannel");

		getProxy().getPluginManager().registerListener(this, new PlayerDisconnectListener());
		getProxy().getPluginManager().registerListener(this, new ServerConnectedListener());
		getProxy().getPluginManager().registerListener(this, new ProxyPingListener());
		getProxy().getPluginManager().registerListener(this, new fr.gameurduxvi.bungeechannel.Listeners.PluginMessageListener());
		

		// Plugin loading Message
		getProxy().getConsole().sendMessage(pluginPrefix + "=========================================================");
		getProxy().getConsole().sendMessage(" ");
		getProxy().getConsole().sendMessage(" ");
	}
	
	
	
	
	@SuppressWarnings("deprecation")
	public void onDisable() {
		getProxy().getConsole().sendMessage("§ePreparing to kick " + getProxy().getPlayers().size() + " players");
		for(ProxiedPlayer p: getProxy().getPlayers()) {
			p.disconnect("§aServer is restarting, rejoin back in 30 sec");
		}
	}	
}
