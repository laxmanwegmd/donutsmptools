package xyz.laxmanwe.laxstools;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LaxCommands implements CommandExecutor, Listener {
    private final Laxstools plugin;

    public LaxCommands(Laxstools plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("laxstools.admin")) {
            sender.sendMessage(ChatColor.RED + "Bu komutu kullanmak için yetkiniz yok!");
            return true;
        }
        if (command.getName().equalsIgnoreCase("laxpick")) {
            return handleLaxPick(sender, args);
        }
        if (command.getName().equalsIgnoreCase("laxbalta")) {
            return handleLaxBalta(sender, args);
        }
        if (command.getName().equalsIgnoreCase("laxkurek")) {
            return handleLaxKurek(sender, args);
        }
        return false;
    }

    private boolean handleLaxPick(CommandSender sender, String[] args) {
        Player target;
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Konsol için oyuncu ismi belirtmelisiniz!");
                return true;
            }
            target = (Player) sender;
        } else {
            target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Oyuncu bulunamadı!");
                return true;
            }
        }
        ItemStack pick = createDeagonPick();
        target.getInventory().addItem(pick);
        target.sendMessage(ChatColor.GREEN + "Deagon Kazma verildi!");
        if (!sender.equals(target)) {
            sender.sendMessage(ChatColor.GREEN + target.getName() + " oyuncusuna Deagon Kazma verildi!");
        }
        return true;
    }

    private boolean handleLaxBalta(CommandSender sender, String[] args) {
        Player target;
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Konsol için oyuncu ismi belirtmelisiniz!");
                return true;
            }
            target = (Player) sender;
        } else {
            target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Oyuncu bulunamadı!");
                return true;
            }
        }
        ItemStack axe = createDeagonAxe();
        target.getInventory().addItem(axe);
        target.sendMessage(ChatColor.GREEN + "Deagon Balta verildi!");
        if (!sender.equals(target)) {
            sender.sendMessage(ChatColor.GREEN + target.getName() + " oyuncusuna Deagon Balta verildi!");
        }
        return true;
    }

    private boolean handleLaxKurek(CommandSender sender, String[] args) {
        Player target;
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Konsol için oyuncu ismi belirtmelisiniz!");
                return true;
            }
            target = (Player) sender;
        } else {
            target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Oyuncu bulunamadı!");
                return true;
            }
        }
        ItemStack shovel = createDeagonShovel();
        target.getInventory().addItem(shovel);
        target.sendMessage(ChatColor.GREEN + "Deagon Kürek verildi!");
        if (!sender.equals(target)) {
            sender.sendMessage(ChatColor.GREEN + target.getName() + " oyuncusuna Deagon Kürek verildi!");
        }
        return true;
    }

    private ItemStack createDeagonPick() {
        ItemStack pick = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemMeta meta = pick.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED + "Deagon Kazma");
        meta.setLore(Arrays.asList(ChatColor.GRAY + "Sadece www.deagoncraft.com sitesinden satın alınabilir."));
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.EFFICIENCY, 4, true);
        meta.addEnchant(Enchantment.UNBREAKING, 3, true);
        meta.addEnchant(Enchantment.MENDING, 1, true);
        pick.setItemMeta(meta);
        return pick;
    }

    private ItemStack createDeagonAxe() {
        ItemStack axe = new ItemStack(Material.NETHERITE_AXE);
        ItemMeta meta = axe.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED + "Deagon Balta");
        meta.setLore(Arrays.asList(ChatColor.GRAY + "Sadece www.deagoncraft.com sitesinden satın alınabilir."));
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.EFFICIENCY, 4, true);
        meta.addEnchant(Enchantment.UNBREAKING, 3, true);
        meta.addEnchant(Enchantment.MENDING, 1, true);
        axe.setItemMeta(meta);
        return axe;
    }

    private ItemStack createDeagonShovel() {
        ItemStack shovel = new ItemStack(Material.NETHERITE_SHOVEL);
        ItemMeta meta = shovel.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED + "Deagon Kürek");
        meta.setLore(Arrays.asList(ChatColor.GRAY + "Sadece www.deagoncraft.com sitesinden satın alınabilir."));
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.EFFICIENCY, 4, true);
        meta.addEnchant(Enchantment.UNBREAKING, 3, true);
        meta.addEnchant(Enchantment.MENDING, 1, true);
        shovel.setItemMeta(meta);
        return shovel;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null || item.getItemMeta() == null) {
            return;
        }
        String displayName = item.getItemMeta().getDisplayName();
        if (ChatColor.stripColor(displayName).equals("Deagon Kazma")) {
            handlePickaxeBreak(event, player);
        } else if (ChatColor.stripColor(displayName).equals("Deagon Balta")) {
            handleAxeBreak(event, player);
        } else if (ChatColor.stripColor(displayName).equals("Deagon Kürek")) {
            handleShovelBreak(event, player);
        }
    }

    private void spawnAmethystParticles(Block block) {
        World world = block.getWorld();
        Location loc = block.getLocation().add(0.5D, 0.5D, 0.5D);
        Particle.DustTransition dustTransition = new Particle.DustTransition(
                Color.fromRGB(224, 117, 255),
                Color.fromRGB(159, 79, 219),
                1.0F
        );
        world.spawnParticle(Particle.DUST_COLOR_TRANSITION, loc, 5, 0.3D, 0.3D, 0.3D, dustTransition);
    }

    private void handlePickaxeBreak(BlockBreakEvent event, Player player) {
        Block broken = event.getBlock();
        Location loc = broken.getLocation();
        spawnAmethystParticles(broken);
        float yaw = player.getLocation().getYaw();
        float pitch = player.getLocation().getPitch();
        yaw = (yaw + 360.0F) % 360.0F;

        if (Math.abs(pitch) > 45.0F) {
            // Yukarı veya aşağı bakıyor
            if ((yaw >= 315.0F && yaw <= 360.0F) || (yaw >= 0.0F && yaw < 45.0F) || (yaw >= 135.0F && yaw < 225.0F)) {
                // Kuzey veya güney yönünde
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        if (x != 0 || y != 0) {
                            Block block = loc.clone().add(x, y, 0.0D).getBlock();
                            if (block.getType() != Material.AIR && block.getType() != Material.BEDROCK) {
                                spawnAmethystParticles(block);
                                block.breakNaturally(player.getInventory().getItemInMainHand());
                            }
                        }
                    }
                }
            } else {
                // Doğu veya batı yönünde
                for (int z = -1; z <= 1; z++) {
                    for (int y = -1; y <= 1; y++) {
                        if (z != 0 || y != 0) {
                            Block block = loc.clone().add(0.0D, y, z).getBlock();
                            if (block.getType() != Material.AIR && block.getType() != Material.BEDROCK) {
                                spawnAmethystParticles(block);
                                block.breakNaturally(player.getInventory().getItemInMainHand());
                            }
                        }
                    }
                }
            }
        } else if ((yaw >= 315.0F && yaw <= 360.0F) || (yaw >= 0.0F && yaw < 45.0F) || (yaw >= 135.0F && yaw < 225.0F)) {
            // Yatay - kuzey/güney
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    if (x != 0 || y != 0) {
                        Block block = loc.clone().add(x, y, 0.0D).getBlock();
                        if (block.getType() != Material.AIR && block.getType() != Material.BEDROCK) {
                            spawnAmethystParticles(block);
                            block.breakNaturally(player.getInventory().getItemInMainHand());
                        }
                    }
                }
            }
        } else {
            // Yatay - doğu/batı
            for (int z = -1; z <= 1; z++) {
                for (int y = -1; y <= 1; y++) {
                    if (z != 0 || y != 0) {
                        Block block = loc.clone().add(0.0D, y, z).getBlock();
                        if (block.getType() != Material.AIR && block.getType() != Material.BEDROCK) {
                            spawnAmethystParticles(block);
                            block.breakNaturally(player.getInventory().getItemInMainHand());
                        }
                    }
                }
            }
        }
        player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1.0F, 1.0F);
    }

    private void handleAxeBreak(BlockBreakEvent event, Player player) {
        Block startBlock = event.getBlock();
        if (isLog(startBlock.getType())) {
            event.setCancelled(true);
            Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
                Set<Block> treeParts = new HashSet<>();
                findConnectedTreeParts(startBlock, treeParts, 650);
                Bukkit.getScheduler().runTask(this.plugin, () -> {
                    for (Block block : treeParts) {
                        spawnAmethystParticles(block);
                        block.breakNaturally(player.getInventory().getItemInMainHand());
                    }
                    player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1.0F, 1.0F);
                });
            });
        }
    }

    private void handleShovelBreak(BlockBreakEvent event, Player player) {
        Block broken = event.getBlock();
        Location loc = broken.getLocation();
        if (!isShovelBlock(broken.getType())) {
            return;
        }
        spawnAmethystParticles(broken);
        float yaw = player.getLocation().getYaw();
        float pitch = player.getLocation().getPitch();
        yaw = (yaw + 360.0F) % 360.0F;

        if (Math.abs(pitch) > 45.0F) {
            if ((yaw >= 315.0F && yaw <= 360.0F) || (yaw >= 0.0F && yaw < 45.0F) || (yaw >= 135.0F && yaw < 225.0F)) {
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        if (x != 0 || y != 0) {
                            Block block = loc.clone().add(x, y, 0.0D).getBlock();
                            if (isShovelBlock(block.getType()) && block.getType() != Material.AIR && block.getType() != Material.BEDROCK) {
                                spawnAmethystParticles(block);
                                block.breakNaturally(player.getInventory().getItemInMainHand());
                            }
                        }
                    }
                }
            } else {
                for (int z = -1; z <= 1; z++) {
                    for (int y = -1; y <= 1; y++) {
                        if (z != 0 || y != 0) {
                            Block block = loc.clone().add(0.0D, y, z).getBlock();
                            if (isShovelBlock(block.getType()) && block.getType() != Material.AIR && block.getType() != Material.BEDROCK) {
                                spawnAmethystParticles(block);
                                block.breakNaturally(player.getInventory().getItemInMainHand());
                            }
                        }
                    }
                }
            }
        } else if ((yaw >= 315.0F && yaw <= 360.0F) || (yaw >= 0.0F && yaw < 45.0F) || (yaw >= 135.0F && yaw < 225.0F)) {
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    if (x != 0 || y != 0) {
                        Block block = loc.clone().add(x, y, 0.0D).getBlock();
                        if (isShovelBlock(block.getType()) && block.getType() != Material.AIR && block.getType() != Material.BEDROCK) {
                            spawnAmethystParticles(block);
                            block.breakNaturally(player.getInventory().getItemInMainHand());
                        }
                    }
                }
            }
        } else {
            for (int z = -1; z <= 1; z++) {
                for (int y = -1; y <= 1; y++) {
                    if (z != 0 || y != 0) {
                        Block block = loc.clone().add(0.0D, y, z).getBlock();
                        if (isShovelBlock(block.getType()) && block.getType() != Material.AIR && block.getType() != Material.BEDROCK) {
                            spawnAmethystParticles(block);
                            block.breakNaturally(player.getInventory().getItemInMainHand());
                        }
                    }
                }
            }
        }
        player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1.0F, 1.0F);
    }

    private boolean isTreePart(Material material) {
        return isLog(material) || isLeaves(material) || material == Material.VINE;
    }

    private boolean isLog(Material material) {
        String name = material.name();
        return name.endsWith("_LOG") || name.endsWith("_STEM") || name.endsWith("_WOOD") || name.endsWith("_HYPHAE");
    }

    private boolean isLeaves(Material material) {
        return material.name().endsWith("_LEAVES");
    }

    private boolean isShovelBlock(Material material) {
        return material == Material.DIRT ||
                material == Material.GRASS_BLOCK ||
                material == Material.SAND ||
                material == Material.RED_SAND ||
                material == Material.GRAVEL ||
                material == Material.SOUL_SAND ||
                material == Material.SOUL_SOIL ||
                material == Material.CLAY ||
                material == Material.FARMLAND ||
                material == Material.DIRT_PATH ||
                material == Material.COARSE_DIRT ||
                material == Material.PODZOL ||
                material == Material.MYCELIUM ||
                material == Material.SNOW ||
                material == Material.SNOW_BLOCK ||
                material == Material.POWDER_SNOW ||
                material == Material.MUD ||
                material == Material.MUDDY_MANGROVE_ROOTS ||
                material == Material.ROOTED_DIRT ||
                material == Material.MOSS_BLOCK;
    }

    private void findConnectedTreeParts(Block startBlock, Set<Block> found, int maxSize) {
        Queue<Block> toCheck = new LinkedList<>();
        toCheck.add(startBlock);
        found.add(startBlock);
        while (!toCheck.isEmpty()) {
            Block current = toCheck.poll();
            for (int x = -2; x <= 2; x++) {
                for (int y = -2; y <= 2; y++) {
                    for (int z = -2; z <= 2; z++) {
                        if (x != 0 || y != 0 || z != 0) {
                            Block nearby = current.getRelative(x, y, z);
                            if (!found.contains(nearby)) {
                                if (isTreePart(nearby.getType())) {
                                    if (nearby.getLocation().distanceSquared(current.getLocation()) < 12.0D) {
                                        if (found.size() >= maxSize) {
                                            return;
                                        }
                                        found.add(nearby);
                                        toCheck.add(nearby);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItem(event.getNewSlot());
        if (item == null || item.getItemMeta() == null) {
            return;
        }
        String displayName = item.getItemMeta().getDisplayName();
        if (ChatColor.stripColor(displayName).equals("Deagon Kazma") ||
                ChatColor.stripColor(displayName).equals("Deagon Balta") ||
                ChatColor.stripColor(displayName).equals("Deagon Kürek")) {
            player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_PLACE, 0.5F, 1.5F);
        }
    }
}