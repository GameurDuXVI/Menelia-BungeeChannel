package fr.gameurduxvi.bungeechannel.Configs;

import java.io.File;
import java.io.IOException;

import fr.gameurduxvi.bungeechannel.Main;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class Ban {
	
	private static File file;
	private static Configuration fileConfig;

	@SuppressWarnings("deprecation")
	public void load() {
		// Loading plugin config file	
		file = new File("plugins/Menelia/banlist.yml");
		Main.getInstance().getProxy().getConsole().sendMessage(Main.getInstance().pluginPrefix + " Config File " + file.getPath() + " (BanManager.java) has been loaded !");
		
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
	
	public static void saveConfig() {
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(fileConfig, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File getFile() {
		return file;
	}
	
	public static Configuration getConfig() {
		return fileConfig;
	}
}
