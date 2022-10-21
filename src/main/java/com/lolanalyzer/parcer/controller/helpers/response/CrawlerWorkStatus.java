package com.lolanalyzer.parcer.controller.helpers.response;


public class CrawlerWorkStatus {
    public boolean isCrawlerWorking;


    public int inQueue;
    public int maxQueue;
    public boolean isBusySaving;

    public long remainingCount;
    public long datasetsCounter;

    public String crawlerStatus;
    public long playerPool;
    public long failedMatches;


}
