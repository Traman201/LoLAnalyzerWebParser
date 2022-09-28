package com.lolanalyzer.parcer.controller;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.Match;
import com.lolanalyzer.parcer.entity.Participant;
import com.lolanalyzer.parcer.entity.ParticipantFrame;
import com.lolanalyzer.parcer.entity.events.ChampionKill;
import com.lolanalyzer.parcer.entity.events.Event;
import com.lolanalyzer.parcer.riotapi.MatchAPI;
import com.lolanalyzer.parcer.riotapi.RiotAPIConfiguration;
import com.lolanalyzer.parcer.service.MatchRepositoryManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@Slf4j
@RequestMapping("/game")
public class GameController {

    @Autowired
    MatchRepositoryManager matchManager;


    @GetMapping
    public String gameForm(Model model){
        ArrayList<Match> games = (ArrayList<Match>) matchManager.getGameRepository().findAll();
        model.addAttribute("games", games);

        return "game";
    }
    @GetMapping("/{matchId}")
    public String matchInfoForm(Model model, @PathVariable String matchId){

        ArrayList<Participant> participants = new ArrayList<>();

        participants = (ArrayList<Participant>) matchManager.getParticipantRepository().findAllByIdMatchId(MatchAPI.constructMatchId(matchId));
        model.addAttribute("participants", participants);

        return "matchInfo";
    }

    @PostMapping()
    public String addGame(@RequestParam String matchID, @RequestParam String apiKey) {
        RiotAPIConfiguration.getInstance().setApiKey(apiKey);

        try {
            Match match = MatchAPI.getMatch(matchID);
            matchManager.saveMatch(match);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

        return "redirect:/game";
    }

    @PostMapping("/save")
    public String saveMatchesLLA14(@RequestParam String path, @RequestParam boolean save) {
        ArrayList<Match> games = (ArrayList<Match>) matchManager.getGameRepository().findAll();
        for(Match m : games){
            if(!m.getTextData().get("gameMode").equals("CLASSIC")){
                continue;
            }
            ArrayList<String> specData = new ArrayList<>();

            String matchWinData = "";
            for(Participant p : m.getParticipants()){
                matchWinData += p.getNumericData().get("participantId") + ",";
                matchWinData += p.getBooleanData().get("win") + ";";
            }
            matchWinData+="\n";

            int[] kills = {0,0,0,0,0,0,0,0,0,0};
            int[] deaths = {0,0,0,0,0,0,0,0,0,0};
            for(Frame frame : m.getTimeline().getFrames()){
                for (ParticipantFrame pf : frame.getParticipantFrames()) {
                    String spec = "";
                    try{
                        spec += (Long.toString(frame.getId().getTimestamp())) + ";";
                        spec += Long.toString(pf.getId().getParticipantId()) + ";";
                        spec += Long.toString(pf.getFrameValues().get("totalGold")) + ";";
                        spec += Long.toString(pf.getChampionStats().getStats().get("abilityPower")) + ";";
                        spec += Long.toString(pf.getChampionStats().getStats().get("attackDamage")) + ";";
                        spec += Long.toString(pf.getChampionStats().getStats().get("magicResist")) + ";";
                        spec += Long.toString(pf.getChampionStats().getStats().get("armor")) + ";";
                    }catch (Exception e){
                        log.info(spec);
                        continue;
                    }



                    for (Event e : frame.getEvents()) {
                        if (e instanceof ChampionKill) {
                            if (((ChampionKill) e).getKillerId() == pf.getId().getParticipantId()) {
                                kills[(int) (pf.getId().getParticipantId() - 1)]++;
                            } else if (((ChampionKill) e).getVictimId() == pf.getId().getParticipantId()) {
                                deaths[(int) (pf.getId().getParticipantId() - 1)]++;
                            }
                        }
                    }
                    spec += kills[(int) (pf.getId().getParticipantId() - 1)] + ";";
                    spec += deaths[(int) (pf.getId().getParticipantId() - 1)] + ";";

                    spec += pf.getChampionStats().getStats().get("healthMax") + ";";
                    spec += pf.getFrameValues().get("level") + ";";
                    spec += "\n";
                    specData.add(spec);
                }
            }

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(path + m.getId().getPlatformId() + m.getId().getGameId() + ".txt"));
                writer.write(matchWinData);

                for(String s : specData){
                    writer.write(s);
                }
                writer.close();


            } catch (IOException e) {
                continue;
            }

        }

        return "redirect:/game";
    }




}
