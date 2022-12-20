package com.lolanalyzer.parcer.controller;


import com.lolanalyzer.parcer.controller.helpers.response.CrawlerWorkStatus;
import com.lolanalyzer.parcer.datagather.crawler.Crawler;
import com.lolanalyzer.parcer.riotapi.RiotAPIConfiguration;
import com.lolanalyzer.parcer.service.MatchRepositoryManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
* Котроллер страницы сбора датасета посредством паука
* */
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

    /**
    * Вызов макета crawler.html
    * */
    @GetMapping
    public String getMapping(Model model){
        model.addAttribute("count", manager.getGameRepository().count());
        model.addAttribute("crawler", crawler);

        model.addAttribute("apikey", RiotAPIConfiguration.getInstance().getApiKey());
        return "crawler";
    }

    /**
    * Старт сбора датасетов.
    *
    * @param numMatches количество матчей, которые необходимо добавить в базу данных
    * @param puuid ID начального игрока. Зависит от API-ключа
    * @param apiKey API-ключ аккаунта разработчика
    * */
    @PostMapping
    public String startCrawler(@RequestParam long numMatches,
                             @RequestParam String puuid,
                             @RequestParam String apiKey){
        RiotAPIConfiguration.getInstance().setApiKey(apiKey);
        crawler.crawling(puuid, numMatches);
        return "redirect:/crawler";
    }

    /**
    * Запрос остановки работы паука
    * */
    @GetMapping(params = "requestStop")
    public String stopCrawler(@RequestParam String requestStop, Model model){
        if(Objects.equals(requestStop, "yes")){
            crawler.setRequestStop(true);
        }
        return getMapping(model);
    }

    /**
    * Возвращает параметры работы паука в JSON-формате
    * */
    @GetMapping("/status")
    public @ResponseBody CrawlerWorkStatus getStatus(){
        CrawlerWorkStatus dataSaverStatus = new CrawlerWorkStatus();

        dataSaverStatus.inQueue = crawler.getSaver().getQueueSize();
        dataSaverStatus.maxQueue = crawler.getSaver().getMaxQueueSize();
        dataSaverStatus.isBusySaving = crawler.getSaver().isBusySaving();

        dataSaverStatus.isCrawlerWorking = crawler.isCrawling();
        dataSaverStatus.remainingCount = crawler.getRemainingCount();
        dataSaverStatus.datasetsCounter = crawler.getDatasetsCounter();

        dataSaverStatus.crawlerStatus = crawler.getStatus();

        dataSaverStatus.playerPool = crawler.getPlayerPool();
        dataSaverStatus.failedMatches = crawler.getFailedMatches();


        return dataSaverStatus;
    }




}
