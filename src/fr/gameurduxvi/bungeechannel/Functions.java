package fr.gameurduxvi.bungeechannel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Functions {	
	
	public void updateOnlinePlayers() {
		Main.getInstance().getProxy().getScheduler().schedule(Main.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				Main.getInstance().MySQLLibrary.Truncate("online");
				for(ProxiedPlayer lp: Main.getInstance().getProxy().getPlayers()) {
					synchronized (this) {
						try {
							PreparedStatement statement = Main.getFunctions().MySQLConnection().prepareStatement("INSERT INTO `online` (`id`, `uuid`, `username`, `server`) VALUES (NULL, ?,?,?);");
							statement.setString(1, lp.getUniqueId().toString() + "");
							statement.setString(2, lp.getName() + "");
							statement.setString(3, lp.getServer().getInfo().getName() + "");
							statement.executeUpdate();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}, 2, TimeUnit.SECONDS);
		
	}
	
	public Connection MySQLConnection() {
		return Main.getInstance().con;
	}	
	
	
	public boolean isEmpty(Object obj) {
		try {
			if(!obj.equals(null)) {
				return false;
			}
		} catch (NullPointerException e) {
			return true;
		}
		return false;
	}
	
	
	public String getDateFromDate(Date date) {
		String stringBetweenDate = "/";
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd" + stringBetweenDate + "MM" + stringBetweenDate + "yyyy");
		String stringDate = dateFormatter.format(c.getTime());
		return stringDate;		
	}
	
	
	public String getDateFromDate(Date date, String betweenDate) {
		String stringBetweenDate = "/";
		if(!betweenDate.equals("")) {
			stringBetweenDate = betweenDate;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd" + stringBetweenDate + "MM" + stringBetweenDate + "yyyy");
		String stringDate = dateFormatter.format(c.getTime());
		return stringDate;		
	}
	
	
	public String getHourFromDate(Date date, String betweenHour, boolean includeSeconds) {
		String stringBetweenHour = ":";
		if(!betweenHour.equals("")) {
			stringBetweenHour = betweenHour;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if(includeSeconds) {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("HH" + stringBetweenHour + "mm" + stringBetweenHour + "ss");
			return dateFormatter.format(c.getTime());
		}
		SimpleDateFormat dateFormatter = new SimpleDateFormat("HH" + stringBetweenHour + "mm");
		return dateFormatter.format(c.getTime());
	}
	
	
	public String getHourFromDate(Date date, boolean includeSeconds) {
		String stringBetweenHour = ":";
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if(includeSeconds) {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("HH" + stringBetweenHour + "mm" + stringBetweenHour + "ss");
			return dateFormatter.format(c.getTime());
		}
		SimpleDateFormat dateFormatter = new SimpleDateFormat("HH" + stringBetweenHour + "mm");
		return dateFormatter.format(c.getTime());
	}
	
	
}
