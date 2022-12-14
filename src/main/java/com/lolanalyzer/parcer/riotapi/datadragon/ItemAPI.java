package com.lolanalyzer.parcer.riotapi.datadragon;

import com.lolanalyzer.parcer.entity.datadragon.ItemStats;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

    public static Map<String, String> championToItemConversion(){
        Map<String, String> conversion = new HashMap<>();

        conversion.put("FlatMagicDamageMod","abilityPower");
        conversion.put("FlatArmorMod","armor");
        conversion.put("FlatPhysicalDamageMod","attackDamage");
        conversion.put("PercentAttackSpeedMod","attackSpeed");
        conversion.put("FlatHPPoolMod","healthMax");
        conversion.put("PercentLifeStealMod","lifesteal");
        conversion.put("FlatSpellBlockMod","magicResist");
        conversion.put("FlatMovementSpeedMod","movementSpeed");
        conversion.put("FlatMPPoolMod","powerMax");

        return conversion;
    }

    public static ItemStats parseItem(JSONObject itemJSON, long id){
        ItemStats item = new ItemStats();

        item.setId(id);
        item.setName(itemJSON.getString("name"));

        item.setTotalGold(itemJSON.getJSONObject("gold").getLong("total"));

        JSONObject statsJSON = itemJSON.getJSONObject("stats");

        for(String key : getStatKeys()){
            double val = 0;
            try{
                val = statsJSON.getDouble(key);
            }catch (JSONException ignored){}
            item.getStats().put(key, val);
        }
        return item;
    }
}
