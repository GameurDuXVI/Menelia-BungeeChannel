package fr.gameurduxvi.bungeechannel;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class sql {
	private static String SQLHost;
	private static String SQLDatabase; 
	private static String SQLUsername; 
	private static String SQLPasseword;
	private static int SQLPort = 3306;
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public sql() {
		try {
			Main.getInstance().getProxy().getConsole().sendMessage(Main.getInstance().pluginPrefix + " §eSetting up sql configuration...");
			
			Object obj = new JSONParser().parse(new FileReader("../../data/config.json"));
			
			JSONObject jo = (JSONObject) obj;
			
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
			Main.getInstance().getProxy().getConsole().sendMessage(Main.getInstance().pluginPrefix + " §aSql configuration done");
		} catch (IOException | ParseException e1) {
			Main.getInstance().getProxy().getConsole().sendMessage(Main.getInstance().pluginPrefix + " §cAn error occurs while setting up the sql configuration");
			e1.printStackTrace();
		}
	}
	
	//=======================================================================================================
	// 
	// Initialise Connection
	// 
	//=======================================================================================================
	
	
	
	public static Connection initConnection() {
		try {
			synchronized (Main.getInstance()) {
				Class.forName("com.mysql.jdbc.Driver");
				return DriverManager.getConnection("jdbc:mysql://" + SQLHost + ":" + SQLPort + "/" + SQLDatabase + "?autoReconnect=true&useSSL=false", SQLUsername, SQLPasseword);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public static void execute(String line, String... fields) {
		synchronized (Main.getInstance()) {
			try {
				Connection con = initConnection();
				PreparedStatement statement = con.prepareStatement(line);
				int i = 0;
				for(String field: fields) {
					i++;
					statement.setString(i, field);
				}				
				statement.executeUpdate();
			} catch (SQLException e) {
				Main.getInstance().getProxy().getConsole().sendMessage(Main.getInstance().pluginPrefix + " §cAn error occurs with an sql line: ");
				Main.getInstance().getProxy().getConsole().sendMessage(Main.getInstance().pluginPrefix + " " + line);
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static boolean exist(String line, String... fields) {
		synchronized (Main.getInstance()) {
			try {
				Connection con = initConnection();
				PreparedStatement statement = con.prepareStatement(line);
				int i = 0;
				for(String field: fields) {
					i++;
					statement.setString(i, field);
				}	
				ResultSet result = statement.executeQuery();
				if(result.next()) {
					return true;
				}
			} catch (SQLException e) {
				Main.getInstance().getProxy().getConsole().sendMessage(Main.getInstance().pluginPrefix + " §cAn error occurs with an sql line: ");
				Main.getInstance().getProxy().getConsole().sendMessage(Main.getInstance().pluginPrefix + " " + line);
				e.printStackTrace();
			}
		}
		return false;
	}	
	
	@SuppressWarnings("deprecation")
	public static ResultSet getResult(String line, String... fields) {
		synchronized (Main.getInstance()) {
			try {
				Connection con = initConnection();
				PreparedStatement statement = con.prepareStatement(line);
				int i = 0;
				for(String field: fields) {
					i++;
					statement.setString(i, field);
				}	
				ResultSet result = statement.executeQuery();
				return result;
			} catch (SQLException e) {
				Main.getInstance().getProxy().getConsole().sendMessage(Main.getInstance().pluginPrefix + " §cAn error occurs with an sql line: ");
				Main.getInstance().getProxy().getConsole().sendMessage(Main.getInstance().pluginPrefix + " " + line);
				e.printStackTrace();
			}
		}
		return null;
	}
}
