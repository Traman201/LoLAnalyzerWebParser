package com.lolanalyzer.parcer.riotapi.datadragon;

import com.lolanalyzer.parcer.entity.datadragon.Item;
import org.json.JSONException;
import org.json.JSONObject;

public class ItemAPI {

    public static String[] getStatKeys(){
        return new String[] {
                "FlatHPPoolMod",
                "rFlatHPModPerLevel",
                "FlatMPPoolMod",
                "rFlatMPModPerLevel",
                "PercentHPPoolMod",
                "PercentMPPoolMod",
                "FlatHPRegenMod",
                "rFlatHPRegenModPerLevel",
                "PercentHPRegenMod",
                "FlatMPRegenMod",
                "rFlatMPRegenModPerLevel",
                "PercentMPRegenMod",
                "FlatArmorMod",
                "rFlatArmorModPerLevel",
                "PercentArmorMod",
                "rFlatArmorPenetrationMod",
                "rFlatArmorPenetrationModPerLevel",
                "rPercentArmorPenetrationMod",
                "rPercentArmorPenetrationModPerLevel",
                "FlatPhysicalDamageMod",
                "rFlatPhysicalDamageModPerLevel",
                "PercentPhysicalDamageMod",
                "FlatMagicDamageMod",
                "rFlatMagicDamageModPerLevel",
                "PercentMagicDamageMod",
                "FlatMovementSpeedMod",
                "rFlatMovementSpeedModPerLevel",
                "PercentMovementSpeedMod",
                "rPercentMovementSpeedModPerLevel",
                "FlatAttackSpeedMod",
                "PercentAttackSpeedMod",
                "rPercentAttackSpeedModPerLevel",
                "rFlatDodgeMod",
                "rFlatDodgeModPerLevel",
                "PercentDodgeMod",
                "FlatCritChanceMod",
                "rFlatCritChanceModPerLevel",
                "PercentCritChanceMod",
                "FlatCritDamageMod",
                "rFlatCritDamageModPerLevel",
                "PercentCritDamageMod",
                "FlatBlockMod",
                "PercentBlockMod",
                "FlatSpellBlockMod",
                "rFlatSpellBlockModPerLevel",
                "PercentSpellBlockMod",
                "FlatEXPBonus",
                "PercentEXPBonus",
                "rPercentCooldownMod",
                "rPercentCooldownModPerLevel",
                "rFlatTimeDeadMod",
                "rFlatTimeDeadModPerLevel",
                "rPercentTimeDeadMod",
                "rPercentTimeDeadModPerLevel",
                "rFlatGoldPer10Mod",
                "rFlatMagicPenetrationMod",
                "rFlatMagicPenetrationModPerLevel",
                "rPercentMagicPenetrationMod",
                "rPercentMagicPenetrationModPerLevel",
                "FlatEnergyRegenMod",
                "rFlatEnergyRegenModPerLevel",
                "FlatEnergyPoolMod",
                "rFlatEnergyModPerLevel",
                "PercentLifeStealMod",
                "PercentSpellVampMod"

        };
    }

    public static Item parseItem(JSONObject itemJSON, long id){
        Item item = new Item();

        item.setId(id);
        item.setName(itemJSON.getString("name"));

        item.setTotalGold(itemJSON.getJSONObject("gold").getLong("total"));

        JSONObject statsJSON = itemJSON.getJSONObject("stats");

        for(String key : getStatKeys()){
            long val = 0;
            try{
                val = statsJSON.getLong(key);
            }catch (JSONException ignored){}
            item.getStats().put(key, val);
        }
        return item;
    }
}
