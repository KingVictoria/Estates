package com.gmail.therealkingvictoria.estate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.gmail.therealkingvictoria.multiblock.MultiBlock;
import com.gmail.therealkingvictoria.multiblock.MultiBlockManager;
import com.gmail.therealkingvictoria.multiblock.Structure;

/**
 * Manages Estates, loading, saving, etc.
 * @author KingVictoria
 */
public class EstateManager {
	
	private static File configFile;
	private static FileConfiguration config;

	public static List<Estate> estates;

	/**
	 * Loads from config and creates the estates List
	 * @param file config File
	 * @author KingVictoria
	 */
	public static void init(File file) {
		configFile = file;

		try {
			config = YamlConfiguration.loadConfiguration(configFile);
			estates = loadEstates();
		} catch (IllegalArgumentException e1) {
			Bukkit.getLogger().severe("Estates config does not exist! Creating...");
			try {
				configFile.createNewFile();
				estates = new ArrayList<>();
			} catch (IOException e2) {
				Bukkit.getLogger().severe("Unable to create Estates config!");
				e2.printStackTrace();
			} // try/catch
		} // try/catch
	} // init
	
	/**
	 * Loads the Estates from config
	 * @return List of Estates
	 */
	private static List<Estate> loadEstates() {
		List<Estate> configEstates = new ArrayList<>();
		for (String estateKey : config.getKeys(false)) {
			ConfigurationSection estateSection = config.getConfigurationSection(estateKey);

			String name = estateKey;
			
			ConfigurationSection locationSection = estateSection.getConfigurationSection("location");
			int x = locationSection.getInt("x");
			int y = locationSection.getInt("y");
			int z = locationSection.getInt("z");
			World world = Bukkit.getWorld(locationSection.getString("world"));
			Location location = new Location(world, x, y, z);
			
			MultiBlock multiBlock = MultiBlockManager.getByName(estateSection.getString("multiBlock"));
			Structure structure = multiBlock.getByLocation(location);

			configEstates.add(new Estate(name, location, structure));
		} // for

		return configEstates;
	} // loadAlembics

} // class
