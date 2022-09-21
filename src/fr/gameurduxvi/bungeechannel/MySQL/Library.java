package fr.gameurduxvi.bungeechannel.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.gameurduxvi.bungeechannel.Main;

public class Library {
	public String Table_Accounts = "accounts";
		public String Field_Accounts_ID = "id";
		public String Field_Accounts_UUID = "uuid";
		public String Field_Accounts_USERNAME = "username";
		public String Field_Accounts_LANGUAGE = "lang";
		public String Field_Accounts_MONEY_BASE = "money_base";
		public String Field_Accounts_MONEY_SPECIAL = "money_special";
		public String Field_Accounts_LAST_CONNEXION_DATE = "last_connexion_date";
		public String Field_Accounts_LAST_CONNEXION_HOUR = "last_connexion_hour";
		public String Field_Accounts_IP = "last_ip";
	
		
	public String Table_Stats_Ascension = "stats_ascension";
		public String Field_Stats_Ascension_ID = "id";
		public String Field_Stats_Ascension_UUID = "uuid";
		public String Field_Stats_Ascension_KILLS = "kills";
		public String Field_Stats_Ascension_WINS = "wins";
	
	public String Table_Stats_TNTSmash = "stats_tntsmash";
		public String Field_Stats_TNTSmash_ID = "id";
		public String Field_Stats_TNTSmash_UUID = "uuid";
		public String Field_Stats_TNTSmash_KILLS = "kills";
		public String Field_Stats_TNTSmash_WINS = "wins";
		public String Field_Stats_TNTSmash_METHOD_EXPLOSION = "method_explosion";
		public String Field_Stats_TNTSmash_METHOD_STICK = "method_stick";
		public String Field_Stats_TNTSmash_METHOD_BLAZE_ROD = "method_blaze_rod";
		public String Field_Stats_TNTSmash_METHOD_FISHING = "method_fishing";
		public String Field_Stats_TNTSmash_METHOD_SNOWBALL = "method_snowball";
		public String Field_Stats_TNTSmash_METHOD_ARROW = "method_arrow";
		
	public String Table_Game_Joiner = "games_joiner";
		public String Field_Game_Joiner_ID = "id";
		public String Field_Game_Joiner_SERVER = "server";
		public String Field_Game_Joiner_UUID = "uuid";
		public String Field_Game_Joiner_GAME_NAME = "game_name";
		public String Field_Game_Joiner_GAME_NUMBER = "game_number";
		
	public String Table_Games = "games";
		public String Field_Games_ID = "id";
		public String Field_Games_GAME_TYPE = "game_type";
		public String Field_Games_GAME_ID = "game";
		public String Field_Games_GAME_STATUS = "status";
	
	public String Table_Games_Content = "games_content";
		public String Field_Games_Content_ID = "id";
		public String Field_Games_Content_GAME_ID = "game";
		public String Field_Games_Content_UUID = "uuid";
	
	
	
	
	//=======================================================================================================
	// 
	// Insert
	// 
	//=======================================================================================================
	
	
	
	
	public void INSERT_ACCOUNT(String uuid, String username, int lang, int moneyBase, int moneySpecial, String lastConnexionDate, String lastConnexionHour, String ip) {
		synchronized (this) {
			try {
				PreparedStatement statement = Main.getFunctions().MySQLConnection().prepareStatement("INSERT INTO `" + Table_Accounts + "` (`" + Field_Accounts_ID + "`, `" + Field_Accounts_UUID + "`, `" + Field_Accounts_USERNAME + "`, `" + Field_Accounts_LANGUAGE + "`, `" + Field_Accounts_MONEY_BASE + "`, `" + Field_Accounts_MONEY_SPECIAL + "`, `" + Field_Accounts_LAST_CONNEXION_DATE + "`, `" + Field_Accounts_LAST_CONNEXION_HOUR + "`, `" + Field_Accounts_IP + "`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?);");
				statement.setString(1, uuid + "");
				statement.setString(2, username + "");
				statement.setString(3, lang + "");
				statement.setString(4, moneyBase + "");
				statement.setString(5, moneySpecial + "");
				statement.setString(6, lastConnexionDate + "");
				statement.setString(7, lastConnexionHour + "");
				statement.setString(8, ip + "");
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void INSERT_ASCENSION(String uuid, int kills, int wins) {
		synchronized (this) {
			try {
				PreparedStatement statement = Main.getFunctions().MySQLConnection().prepareStatement("INSERT INTO `" + Table_Stats_Ascension + "` (`" + Field_Stats_Ascension_ID + "`, `" + Field_Stats_Ascension_UUID + "`, `" + Field_Stats_Ascension_KILLS + "`, `" + Field_Stats_Ascension_WINS + "`) VALUES (NULL, ?,?,?);");
				statement.setString(1, uuid + "");
				statement.setString(2, kills + "");
				statement.setString(3, wins + "");
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void INSERT_GAME_JOINER(String uuid, String server, String gameName, int gameNumber) {
		synchronized (this) {
			try {
				PreparedStatement statement = Main.getFunctions().MySQLConnection().prepareStatement("INSERT INTO `" + Table_Game_Joiner + "` (`" + Field_Game_Joiner_ID + "`, `" + Field_Game_Joiner_SERVER + "`, `" + Field_Game_Joiner_UUID + "`, `" + Field_Game_Joiner_GAME_NAME + "`, `" + Field_Game_Joiner_GAME_NUMBER + "`) VALUES (NULL, ?,?,?,?);");
				statement.setString(1, uuid + "");
				statement.setString(2, server + "");
				statement.setString(3, gameName + "");
				statement.setString(4, gameNumber + "");
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void INSERT_TNTSMASH(String uuid, int kills, int wins, int method_explosion, int method_stick, int method_blaze_rod, int method_fishing, int method_snowball, int method_arrow) {
		synchronized (this) {
			try {
				PreparedStatement statement = Main.getFunctions().MySQLConnection().prepareStatement("INSERT INTO `" + Table_Stats_TNTSmash + "` (`" + Field_Stats_TNTSmash_ID + "`, `" + Field_Stats_TNTSmash_UUID + "`, `" + Field_Stats_TNTSmash_KILLS + "`, `" + Field_Stats_TNTSmash_WINS + "`, `" + Field_Stats_TNTSmash_METHOD_EXPLOSION + "`, `" + Field_Stats_TNTSmash_METHOD_STICK + "`, `" + Field_Stats_TNTSmash_METHOD_BLAZE_ROD + "`, `" + Field_Stats_TNTSmash_METHOD_FISHING + "`, `" + Field_Stats_TNTSmash_METHOD_SNOWBALL + "`, `" + Field_Stats_TNTSmash_METHOD_ARROW + "`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
				statement.setString(1, uuid + "");
				statement.setString(2, kills + "");
				statement.setString(3, wins + "");
				statement.setString(4, method_explosion + "");
				statement.setString(5, method_stick + "");
				statement.setString(6, method_blaze_rod + "");
				statement.setString(7, method_fishing + "");
				statement.setString(8, method_snowball + "");
				statement.setString(9, method_arrow + "");
				System.out.println("INSERT INTO `" + Table_Stats_TNTSmash + "` (`" + Field_Stats_TNTSmash_ID + "`, `" + Field_Stats_TNTSmash_UUID + "`, `" + Field_Stats_TNTSmash_KILLS + "`, `" + Field_Stats_TNTSmash_WINS + "`, `" + Field_Stats_TNTSmash_METHOD_EXPLOSION + "`, `" + Field_Stats_TNTSmash_METHOD_STICK + "`, `" + Field_Stats_TNTSmash_METHOD_BLAZE_ROD + "`, `" + Field_Stats_TNTSmash_METHOD_FISHING + "`, `" + Field_Stats_TNTSmash_METHOD_SNOWBALL + "`, `" + Field_Stats_TNTSmash_METHOD_ARROW + "`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void INSERT_GAMES(String game_type, int gameNum, int status) {
		synchronized (this) {
			try {
				PreparedStatement statement = Main.getFunctions().MySQLConnection().prepareStatement("INSERT INTO `" + Table_Games + "` (`" + Field_Games_ID + "`, `" + Field_Games_GAME_TYPE + "`, `" + Field_Games_GAME_ID + "`, `" + Field_Games_GAME_STATUS + "`) VALUES (NULL, ?,?,?);");
				statement.setString(1, game_type + "");
				statement.setString(2, gameNum + "");
				statement.setString(3, status + "");
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	

	//=======================================================================================================
	// 
	// Add - Remove
	// 
	//=======================================================================================================
	
	
	
	
	public void ADD_INT(String table, String fieldWhere, String valueWhere, String field, int value) {
		int totalValue = Main.getInstance().MySQLLibrary.GET_DATA_Int(table, fieldWhere, valueWhere, field);
		totalValue += value;
		UPDATE_DATA(table, fieldWhere, valueWhere, field, "" + totalValue);
	}
	
	public void REMOVE_INT(String table, String fieldWhere, String valueWhere, String field, int value) {
		int totalValue = Main.getInstance().MySQLLibrary.GET_DATA_Int(table, fieldWhere, valueWhere, field);
		totalValue -= value;
		UPDATE_DATA(table, fieldWhere, valueWhere, field, "" + totalValue);
	}
	
	
	
	
	//=======================================================================================================
	// 
	// Truncate
	// 
	//=======================================================================================================
	
	
	
	
	public void Truncate(String table) {
		synchronized (this) {
			try {
				PreparedStatement statement = Main.getFunctions().MySQLConnection().prepareStatement("TRUNCATE `" + table + "`;");
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	//=======================================================================================================
	// 
	// Update
	// 
	//=======================================================================================================
	
	
	
	
	public void UPDATE_DATA(String uuid, String table, String field, String value) {
		synchronized (this) {
			try {
				PreparedStatement statement = Main.getFunctions().MySQLConnection().prepareStatement("UPDATE `" + table + "` SET `" + field + "`=? WHERE " + Field_Accounts_UUID + "=?;");
				statement.setString(1, value + "");
				statement.setString(2, uuid + "");
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	public void UPDATE_DATA(String table, String fieldWhere, String valueWhere, String field, String value) {
		synchronized (this) {
			try {
				PreparedStatement statement = Main.getFunctions().MySQLConnection().prepareStatement("UPDATE `" + table + "` SET `" + field + "`=? WHERE " + fieldWhere + "=?;");
				statement.setString(1, value + "");
				statement.setString(2, valueWhere + "");
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	//=======================================================================================================
	// 
	// Exist
	// 
	//=======================================================================================================
	
	
	
	
	public boolean EXIST_DATA(String table, String field, String value) {
		synchronized (this) {
			try {
				PreparedStatement statement = Main.getFunctions().MySQLConnection().prepareStatement("SELECT * FROM `" + table + "` WHERE " + field + "=?;");
				statement.setString(1, value + "");
				ResultSet result = statement.executeQuery();
				if(result.next()) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	
	
	//=======================================================================================================
	// 
	// Delete
	// 
	//=======================================================================================================
	
	
	
	
	public void DELETE_DATA(String table, String fieldWhere, String valueWhere) {
		synchronized (this) {
			try {
				PreparedStatement statement = Main.getFunctions().MySQLConnection().prepareStatement("DELETE FROM `" + table + "` WHERE `" + fieldWhere + "`=?;");
				statement.setString(1, valueWhere + "");
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	//=======================================================================================================
	// 
	// Get
	// 
	//=======================================================================================================
	
	
	
	
	public String GET_DATA_String(String table, String fieldWhere, String valueWhere, String field) {
		synchronized (this) {
			try {
				PreparedStatement statement = Main.getFunctions().MySQLConnection().prepareStatement("SELECT * FROM `" + table + "` WHERE " + fieldWhere + "=?;");
				statement.setString(1, valueWhere);
				ResultSet result = statement.executeQuery();
				if(result.next()) {
					return result.getString(field);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	
	public ResultSet GET_RESULT(String table, String fieldWhere, String valueWhere) {
		synchronized (this) {
			try {
				PreparedStatement statement = Main.getFunctions().MySQLConnection().prepareStatement("SELECT * FROM `" + table + "` WHERE " + fieldWhere + "=?;");
				statement.setString(1, valueWhere);
				ResultSet result = statement.executeQuery();
				return result;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	
	public int GET_DATA_Int(String table, String fieldWhere, String valueWhere, String field) {
		synchronized (this) {
			try {
				PreparedStatement statement = Main.getFunctions().MySQLConnection().prepareStatement("SELECT * FROM `" + table + "` WHERE " + fieldWhere + "=?;");
				statement.setString(1, valueWhere);
				ResultSet result = statement.executeQuery();
				if(result.next()) {
					return result.getInt(field);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
}
