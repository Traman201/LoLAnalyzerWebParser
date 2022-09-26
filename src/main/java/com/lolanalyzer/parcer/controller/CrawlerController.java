package com.lolanalyzer.parcer.controller;


import com.lolanalyzer.parcer.datagather.crawler.Crawler;
import com.lolanalyzer.parcer.riotapi.RiotAPIConfiguration;
import com.lolanalyzer.parcer.service.MatchRepositoryManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@Slf4j
@RequestMapping("/crawler")
public class CrawlerController {

    Crawler crawler;


    MatchRepositoryManager manager;

    @Autowired
    public CrawlerController(Crawler crawler, MatchRepositoryManager manager){
        this.crawler = crawler;
        this.manager = manager;
    }

    @GetMapping
    public String test(Model model){
        model.addAttribute("count", manager.getGameRepository().count());
        model.addAttribute("crawler", crawler);
        return "crawler";
    }

    @PostMapping
    public String startCrawler(@RequestParam long numMatches,
                             @RequestParam String puuid,
                             @RequestParam String apiKey){
        RiotAPIConfiguration.getInstance().setApiKey(apiKey);
        crawler.crawling(puuid, numMatches);
        return "redirect:/crawler";
    }

    @GetMapping(params = "requestStop")
    public void stopCrawler(@RequestParam String requestStop){
        if(Objects.equals(requestStop, "yes")){
            crawler.setRequestStop(true);
        }
    }




}
