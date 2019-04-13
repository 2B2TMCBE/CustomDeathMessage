/**
 * MIT License
 *
 * Copyright (c) 2019 2B2TMCBE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org._2b2tmcpe.CustomDeathMessage.Listener;

import org._2b2tmcpe.CustomDeathMessage.Main;
import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.entity.EntityDamageEvent.DamageCause;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;


public class PlayerDeathListener implements Listener {

  private Main plugin;
  private Config conf;

  /**
   * Constructor
   */
  public PlayerDeathListener(Main plugin) {
    this.plugin = plugin;
    this.conf = plugin.getConfig();
  }

  /**
   * This function Listen to PlayerDeathEvent
   *
   * @param event
   */
  @EventHandler
  public void onDeath(PlayerDeathEvent event) {
    String deathMessage = "";
    String playerName = event.getEntity().getName();
    // Get the default death message
    String message = this.convertConfigTags(String.valueOf(this.conf.get("CUSTOM")), playerName);
    // Get a list of damage cause
    EntityDamageEvent ev = event.getEntity().getLastDamageCause();
    DamageCause cause = event.getEntity().getPlayer().getLastDamageCause().getCause();
    // This code is used for debug
    this.plugin.getLogger().debug("debug: cause=" + cause.name());

    // This part is for entity attack entity
    if (ev instanceof EntityDamageByEntityEvent) {
      Entity damager = ((EntityDamageByEntityEvent) ev).getDamager();

      // This condition check is to prevent class cast exception caused by mob attack
      if ((damager instanceof Player) && !(cause == DamageCause.PROJECTILE)) {
        String itemName = ((Player) damager).getInventory().getItemInHand().getName();
        message = this.convertConfigTags(this.conf.getString("KILL_BY_WEAPON"), playerName,
            damager.getName(), itemName);
      } else if (cause == DamageCause.ENTITY_EXPLOSION) {
        deathMessage = this.conf.getString("ENTITY_EXPLOSION");
        message = this.convertConfigTags(deathMessage, playerName, damager.getName());
      } else if (cause == DamageCause.PROJECTILE) {
        deathMessage = this.conf.getString("PROJECTILE");
        message = this.convertConfigTags(deathMessage, playerName, damager.getName());
        // Mob attack
      } else if (cause == DamageCause.ENTITY_ATTACK && !(damager instanceof Player)) {
        deathMessage = this.conf.getString("MOB_ATTACK");
        message = this.convertConfigTags(deathMessage, playerName, damager.getName());
      }
    } else {
      message = this.getDeathMessage(cause, playerName);
    }
    String finalMsg = message;
    event.setDeathMessage(TextFormat.RED + finalMsg);
  }

  /**
   * This method determine the death message of each death case, including the name of the victim.
   * @param cause
   * @return the death message for different cases except ENTITY_EXPLOSION, ENTITY_ATTACK, and PROJECTILE
   */
  public String getDeathMessage(DamageCause cause, String playerName) {
    String deathMessage;
    switch (cause) {
      case SUFFOCATION:
        deathMessage = this.conf.getString("SUFFOCATION");
        break;
      case FALL:
        deathMessage = this.conf.getString("FALL");
        break;
      case FIRE:
        deathMessage = this.conf.getString("FIRE");
        break;
      case FIRE_TICK:
        deathMessage = this.conf.getString("FIRE_TICK");
        break;
      case LAVA:
        deathMessage = this.conf.getString("LAVA");
        break;
      case DROWNING:
        deathMessage = this.conf.getString("DROWNING");
        break;
      case BLOCK_EXPLOSION:
        deathMessage = this.conf.getString("BLOCK_EXPLOSION");
        break;
      case LIGHTNING:
        deathMessage = this.conf.getString("LIGHTNING");
        break;
      case VOID:
        deathMessage = this.conf.getString("VOID");
        break;
      case SUICIDE:
        deathMessage = this.conf.getString("SUICIDE");
        break;
      case MAGIC:
        deathMessage = this.conf.getString("MAGIC");
        break;
      default:
        deathMessage = this.conf.getString("CUSTOM");
    }
    String result = this.convertConfigTags(deathMessage, playerName);
    return result;
  }

  /**
   * This method will convert configuration tags to variables in a provided String
   * 
   * @param String deathMessage
   * @param String playerName
   * @param String Attacker
   * @param String weaponName
   */
  public String convertConfigTags(String deathMessage, String playerName, String Attacker,
      String weaponName) {
    deathMessage.replace("<Player>", playerName);
    deathMessage.replace("<Attacker>", Attacker);
    deathMessage.replace("<WeaponName>", weaponName);
    return deathMessage;
  }

  public String convertConfigTags(String deathMessage, String playerName, String Attacker) {
    deathMessage.replace("<Player>", playerName);
    deathMessage.replace("<Attacker>", Attacker);
    return deathMessage;
  }

  public String convertConfigTags(String deathMessage, String playerName) {
    deathMessage.replace("<Player>", playerName);
    return deathMessage;
  }

}

