package com.name.vanish;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PlayerVanish extends JavaPlugin implements Listener {

	PluginDescriptionFile pdfFile = null;

	List<String> toggled = new ArrayList<String>();

	ArrayList<Player> cooldown = new ArrayList<Player>();

	ChatColor prefixcolour = ChatColor.valueOf("RED");

	private static PlayerVanish plugin;
	public final Logger logger = Logger.getLogger("Minecraft");


	
	

	@Override
	public void onEnable() {

		plugin = this;

		getCommand("pv").setExecutor(new CommandHandler());

		this.logger
				.info("[PlayerVanish] "
						+ "Edit the config to change certain aspects of the plugin! Check the plugins folder to find it!");

		pdfFile = this.getDescription();

		getConfig().options().copyDefaults(true);
		saveConfig();
		
		if (plugin.getConfig().getString("auto-update") == true){
			
		
		updater = new Updater(this, 82674, this.getFile(), Updater.UpdateType.DEFAULT, false);
	}
		else {
			this.logger.info("[PlayerVanish] Auto-Updating is disabled! To enable it change the configuration to true.")
		}
	}
	
	

	public void hideAllPlayers(Player player) {
		if (plugin.getConfig().getBoolean("enable-/pv-hide") == true) {

			for (Player p : Bukkit.getOnlinePlayers())
				player.hidePlayer(p);
			player.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED
					+ getConfig().getString("prefix") + ChatColor.GRAY + "] "
					+ ChatColor.GOLD + "Hello " + player.getName()
					+ ", Players have now been hidden." + ChatColor.DARK_BLUE
					+ " Use /pv show to show players.");
			if (plugin.getConfig().getBoolean("play-sound-effect") == true) {
				player.playSound(player.getLocation(), Sound.PORTAL_TRIGGER, 1,
						1);
			} else if (plugin.getConfig().getBoolean("play-sound-effect") == false) {
				Bukkit.broadcastMessage("");
			} else if (plugin.getConfig().getBoolean("play-sound-effect") != true
					|| plugin.getConfig().getBoolean("play-sound-effect") != false) {
				player.sendMessage(ChatColor.GRAY
						+ "["
						+ ChatColor.RED
						+ getConfig().getString("prefix")
						+ ChatColor.GRAY
						+ "] "
						+ "¤6There is something wrong with the configuration! Make sure text for ¤e¤lplay-sound-effect: ¤6is either ¤f¤ltrue ¤6or ¤f¤lfalse");
			}
		} else if (plugin.getConfig().getBoolean("enable-/pv-hide") == false) {

			player.sendMessage(ChatColor.GRAY
					+ "["
					+ ChatColor.RED
					+ getConfig().getString("prefix")
					+ ChatColor.GRAY
					+ "] "
					+ ChatColor.GOLD
					+ "This command has been disabled by the server administators, if this is a mistake check the config!");
		} else if (plugin.getConfig().getBoolean("enable-/pv-hide") != true
				|| plugin.getConfig().getBoolean("enable-/pv-hide") != false) {
			player.sendMessage(ChatColor.GRAY
					+ "["
					+ ChatColor.RED
					+ getConfig().getString("prefix")
					+ ChatColor.GRAY
					+ "] "
					+ "¤6There is something wrong with the configuration! Make sure text for ¤e¤lenable-/pv-hide: ¤6is either ¤f¤ltrue ¤6or ¤f¤lfalse");
		}
	}

	public void showAllPlayers(Player player) {

		if (plugin.getConfig().getBoolean("enable-/pv-show") == true) {

			for (Player p : Bukkit.getOnlinePlayers())
				player.showPlayer(p);
			player.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED
					+ getConfig().getString("prefix") + ChatColor.GRAY + "] "
					+ ChatColor.GOLD + "Hello " + player.getName()
					+ ", Players have now been shown. " + ChatColor.DARK_BLUE
					+ "Use /pv hide to hide players.");
			if (plugin.getConfig().getBoolean("play-sound-effect") == true) {
				player.playSound(player.getLocation(), Sound.PORTAL_TRIGGER, 1,
						1);
			} else if (plugin.getConfig().getBoolean("play-sound-effect") == false) {
				Bukkit.broadcastMessage("");
			} else if (plugin.getConfig().getBoolean("play-sound-effect") != true
					|| plugin.getConfig().getBoolean("play-sound-effect") != false) {
				player.sendMessage(ChatColor.GRAY
						+ "["
						+ ChatColor.RED
						+ getConfig().getString("prefix")
						+ ChatColor.GRAY
						+ "] "
						+ "¤6There is something wrong with the configuration! Make sure text for ¤e¤lplay-sound-effect: ¤6is either ¤f¤ltrue ¤6or ¤f¤lfalse");
			}

		} else if (plugin.getConfig().getBoolean("enable-/pv-hide") == false) {

			player.sendMessage(ChatColor.GRAY
					+ "["
					+ ChatColor.RED
					+ getConfig().getString("prefix")
					+ ChatColor.GRAY
					+ "] "
					+ ChatColor.GOLD
					+ "This command has been disabled by the server administators, if this is a mistake check the config!");
		} else {
			player.sendMessage(ChatColor.GRAY
					+ "["
					+ ChatColor.RED
					+ getConfig().getString("prefix")
					+ ChatColor.GRAY
					+ "] "
					+ "¤6There is something wrong with the configuration! Make sure text for ¤e¤lenable-/pv-show: ¤6is either ¤f¤ltrue ¤6or ¤f¤lfalse");
		}
	}

	public void sendMessage(Player player) {
		player.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Name"
				+ ChatColor.GRAY + "] " + ChatColor.GREEN + pdfFile.getName());
		player.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Version"
				+ ChatColor.GRAY + "] " + ChatColor.GREEN
				+ pdfFile.getVersion());
		player.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Coder"
				+ ChatColor.GRAY + "] " + ChatColor.BLUE + "xManfro");
		player.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Description"
				+ ChatColor.GRAY + "] " + ChatColor.GREEN
				+ pdfFile.getDescription());

	}

	public void sendMessage2(Player player) {

		player.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED

		+ getConfig().getString("prefix") + ChatColor.GRAY + "] "
				+ ChatColor.GOLD + "Hello " + player.getName()
				+ ", Your IP Address is: " + ChatColor.RED
				+ player.getAddress().getAddress());
	}

	public void SendPermissionError(Player sender) {
		sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED
				+ getConfig().getString("prefix") + ChatColor.GRAY + "] "
				+ ChatColor.DARK_GREEN
				+ getConfig().getString("permission-error"));
	}

	public void SendCommandError(Player sender) {
		sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED
				+ getConfig().getString("prefix") + ChatColor.GRAY + "] "
				+ ChatColor.DARK_GREEN + getConfig().getString("command-error"));

	}

	public void sendMessage3(Player sender) {
		sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED
				+ "PlayerVanish Commands" + ChatColor.GRAY + "]\n"
				+ ChatColor.GOLD + ChatColor.BOLD + "-/pv hide "
				+ ChatColor.BLUE
				+ "Hides all players! This command can be dis\n"
				+ ChatColor.GOLD + ChatColor.BOLD + "-/pv show "
				+ ChatColor.BLUE + "Shows all players!\n" + ChatColor.GOLD
				+ ChatColor.BOLD + "-/pv ip " + ChatColor.BLUE
				+ "Gives you your ip!\n" + ChatColor.GOLD + ChatColor.BOLD
				+ "-/pv about " + ChatColor.BLUE
				+ "Gives a description of the plugin\n" + ChatColor.GOLD
				+ ChatColor.BOLD + "-/pv toggle " + ChatColor.BLUE
				+ "Toggles player visibility.\n" + ChatColor.GOLD
				+ ChatColor.BOLD + "-/pv info " + ChatColor.BLUE
				+ "Gives information about you!\n" + ChatColor.GOLD
				+ ChatColor.BOLD + "-/pv sinfo " + ChatColor.BLUE
				+ "Gives information about the server!\n" + ChatColor.GOLD
				+ ChatColor.BOLD + "-/pv fleave " + ChatColor.BLUE
				+ "Sends a fake leave message!\n" + ChatColor.GOLD
				+ ChatColor.BOLD + "-/pv fjoin " + ChatColor.BLUE
				+ "Sends a fake join message!\n");

	}

	public void SendCommandErrorPV(Player sender) {
		sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED
				+ getConfig().getString("prefix") + ChatColor.GRAY + "] "
				+ ChatColor.DARK_RED + "Hello " + sender.getName() + ", "
				+ ChatColor.GOLD + "Use /pv help for all the commands.");

	}

	public void sendMessage4(Player sender) {
		sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED
				+ "Player Information" + ChatColor.GRAY + "] " + ChatColor.GOLD
				+ "\nHealth: " + ChatColor.RED + sender.getHealth()
				+ ChatColor.GOLD + "\nXP Level: " + ChatColor.RED
				+ sender.getLevel() + ChatColor.GOLD + "\nHunger: "
				+ ChatColor.RED + sender.getFoodLevel() + ChatColor.GOLD
				+ "\nSaturation: " + ChatColor.RED + sender.getSaturation()
				+ ChatColor.GOLD + "\nGamemode: " + ChatColor.RED
				+ sender.getGameMode() + ChatColor.GOLD + "\nFlight: "
				+ ChatColor.RED + sender.getAllowFlight() + ChatColor.GOLD
				+ "\nItem In Hand: " + ChatColor.RED + sender.getItemInHand());

	}

	public static PlayerVanish getInstance() {
		return plugin;

	}

	public void sendMessage5(Player sender) {
		sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED
				+ "Server Information" + ChatColor.GRAY + "] " + ChatColor.RED
				+ "\n\nBukkit Version: " + ChatColor.GOLD
				+ Bukkit.getBukkitVersion() + ChatColor.RED + "\nServer IP: "
				+ ChatColor.GOLD + Bukkit.getIp() + ChatColor.RED
				+ "\nWorld Type: " + ChatColor.GOLD + Bukkit.getWorldType());

	}

	public boolean sendMessage7(Player player) {
		if (!(player instanceof Player)) {
			player.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED
					+ getConfig().getString("prefix") + ChatColor.RED
					+ "You must be a player to perform this command!");
			return false;
		}

		if (toggled.contains(player.getName())) {
			player.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED
					+ getConfig().getString("prefix") + ChatColor.GRAY + "] "
					+ ChatColor.GOLD + "Players are now visible");
			for (Player p : Bukkit.getOnlinePlayers())
				player.showPlayer(p);
			toggled.remove(player.getName());
			if (plugin.getConfig().getBoolean("play-sound-effect") == true) {
				player.playSound(player.getLocation(), Sound.PORTAL_TRIGGER, 1,
						1);
			} else if (plugin.getConfig().getBoolean("play-sound-effect") == false) {
				Bukkit.broadcastMessage("");
			} else if (plugin.getConfig().getBoolean("play-sound-effect") != true
					|| plugin.getConfig().getBoolean("play-sound-effect") != false) {
				player.sendMessage(ChatColor.GRAY
						+ "["
						+ ChatColor.RED
						+ getConfig().getString("prefix")
						+ ChatColor.GRAY
						+ "] "
						+ "¤6There is something wrong with the configuration! Make sure text for ¤e¤lplay-sound-effect: ¤6is either ¤f¤ltrue ¤6or ¤f¤lfalse");
			}
			return true;
		}

		player.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED
				+ getConfig().getString("prefix") + ChatColor.GRAY + "] "
				+ ChatColor.GOLD + "Players are now hidden");
		for (Player p : Bukkit.getOnlinePlayers())
			player.hidePlayer(p);
		toggled.add(player.getName());
		if (plugin.getConfig().getBoolean("play-sound-effect") == true) {
			player.playSound(player.getLocation(), Sound.PORTAL_TRIGGER, 1, 1);
		} else if (plugin.getConfig().getBoolean("play-sound-effect") == false) {
			Bukkit.broadcastMessage("");
		} else if (plugin.getConfig().getBoolean("play-sound-effect") != true
				|| plugin.getConfig().getBoolean("play-sound-effect") != false) {
			player.sendMessage(ChatColor.GRAY
					+ "["
					+ ChatColor.RED
					+ getConfig().getString("prefix")
					+ ChatColor.GRAY
					+ "] "
					+ "¤6There is something wrong with the configuration! Make sure text for ¤e¤lplay-sound-effect: ¤6is either ¤f¤ltrue ¤6or ¤f¤lfalse");
		}
		return true;

	}

	public void sendMessage9(Player player) {

		player.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED
				+ getConfig().getString("prefix") + ChatColor.GRAY + "] "
				+ ChatColor.GOLD + "You are now invisible.");
		Bukkit.broadcastMessage(ChatColor.YELLOW + player.getDisplayName()
				+ " " + getConfig().getString("fake-leave-message"));
		player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,
				200000, 1));
		player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60,
				3));
		player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 60,
				3));
		if (plugin.getConfig().getBoolean("play-sound-effect") == true) {
			player.playSound(player.getLocation(), Sound.PORTAL_TRIGGER, 1, 1);
		} else if (plugin.getConfig().getBoolean("play-sound-effect") == false) {
			Bukkit.broadcastMessage("");
		}

	}

	public void sendMessage10(Player player) {

		player.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED
				+ getConfig().getString("prefix") + ChatColor.GRAY + "] "
				+ ChatColor.GOLD + "You are now visible.");
		Bukkit.broadcastMessage(ChatColor.YELLOW + player.getDisplayName()
				+ " " + getConfig().getString("fake-join-message"));

		player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60,
				3));
		player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 60,
				3));
		player.removePotionEffect(PotionEffectType.INVISIBILITY);
		if (plugin.getConfig().getBoolean("play-sound-effect") == true) {
			player.playSound(player.getLocation(), Sound.PORTAL_TRIGGER, 1, 1);
		} else if (plugin.getConfig().getBoolean("play-sound-effect") == false) {
			Bukkit.broadcastMessage("");
		}
	}

}
