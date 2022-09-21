package fr.gameurduxvi.bungeechannel.Configs;

import java.io.File;
import java.io.IOException;

import fr.gameurduxvi.bungeechannel.Main;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class TempBan {
	private static File file;
	private static Configuration fileConfig;

	@SuppressWarnings("deprecation")
	public void load(boolean debug) {
		// Loading plugin config file	
		file = new File("plugins/Menelia/tempbanlist.yml");
		if(debug) {
			Main.getInstance().getProxy().getConsole().sendMessage(Main.getInstance().pluginPrefix + " Config File " + file.getPath() + " (TempBanManager.java) has been loaded !");
		}
		
		if(!file.exists()) {
			try {
				file.createNewFile();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			fileConfig = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveConfig() {
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(fileConfig, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public File getFile() {
		return file;
	}
	
	public Configuration getConfig() {
		return fileConfig;
	}
}
