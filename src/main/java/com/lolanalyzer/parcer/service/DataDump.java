package com.lolanalyzer.parcer.service;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.Match;
import com.lolanalyzer.parcer.entity.ParticipantFrame;
import com.lolanalyzer.parcer.entity.events.ChampionKill;
import com.lolanalyzer.parcer.entity.events.Event;
import com.lolanalyzer.parcer.entytiId.MatchId;
import com.lolanalyzer.parcer.repositiory.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.thymeleaf.standard.expression.Fragment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class DataDump {

    @Autowired
    MatchRepositoryManager manager;

    public String[] getTypes() {
        return new String[]{
                "lla14"
        };
    }

    public boolean dump(String type) {
        if (type.equals("lla14")) {
            return lla14();
        }
        return false;
    }

    public boolean lla14() {
        ArrayList<Match> matches = (ArrayList<Match>) manager.getGameRepository().findAll();
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("datasets/" + "lla14." + getDate() + ".csv"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        String[] stats = {
            "abilityHaste",
                    "abilityPower",
                    "armor",
                    "armorPen",
                    "armorPenPercent",
                    "attackDamage",
                    "attackSpeed",
                    "bonusArmorPenPercent",
                    "bonusMagicPenPercent",
                    "ccReduction",
                    "cooldownReduction",
                    "health",
                    "healthMax",
                    "healthRegen",
                    "lifesteal",
                    "magicPen",
                    "magicPenPercent",
                    "magicResist",
                    "movementSpeed",
                    "omnivamp",
                    "physicalVamp",
                    "power",
                    "powerMax",
                    "powerRegen",
                    "spellVamp"
        };
        mainLoop:
        for (Match match : matches) {

            int[] kills = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] deaths = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] assists = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for (Frame frame : match.getTimeline().getFrames()) {
                ArrayList<Long> line = new ArrayList<>();
                line.add(frame.getId().getTimestamp());

                int[] statsDiff = new int[25];
                for (ParticipantFrame pf : frame.getParticipantFrames()) {
                    for (Event e : frame.getEvents()) {
                        if (e instanceof ChampionKill) {
                            if (((ChampionKill) e).getKillerId() == pf.getId().getParticipantId()) {
                                kills[(int) (pf.getId().getParticipantId() - 1)]++;
                            } else if (((ChampionKill) e).getVictimId() == pf.getId().getParticipantId()) {
                                deaths[(int) (pf.getId().getParticipantId() - 1)]++;
                            }
                            if(((ChampionKill) e).getAssistingParticipantIds().contains(pf.getId().getParticipantId())){
                                assists[(int) pf.getId().getParticipantId() - 1]++;
                            }

                        }
                    }
                    for(int i = 0; i < stats.length; i++){
                        if(pf.getId().getParticipantId() <= 5){
                            statsDiff[i] += pf.getChampionStats().getStats().get(stats[i]);
                        }
                        else{
                            statsDiff[i] -= pf.getChampionStats().getStats().get(stats[i]);
                        }
                    }


                }
                int killsDiff = 0, deathsDiff = 0, assistsDiff = 0;

                for(int i = 0; i < 5; i++){
                    killsDiff+= kills[i];
                    deathsDiff += deaths[i];
                    assistsDiff += assists[i];
                }
                for(int i = 5; i < 10; i++){
                    killsDiff -= kills[i];
                    deathsDiff -= deaths[i];
                    assistsDiff -= assists[i];
                }
                line.add((long) killsDiff);
                line.add((long) deathsDiff);
                line.add((long) assistsDiff);
                for(int stat : statsDiff){
                    line.add((long) stat);
                }
                for(int i = 0; i < line.size(); i++){
                    try {
                        writer.write(Long.toString(line.get(i)));
                        if(i < line.size() - 1){
                            writer.write(",");
                        }

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    writer.newLine();
                    writer.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }


        return true;
    }

    private String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd.hh.mm.ss");
        LocalDateTime now = LocalDateTime.now();
        return (dtf.format(now));
    }
}
