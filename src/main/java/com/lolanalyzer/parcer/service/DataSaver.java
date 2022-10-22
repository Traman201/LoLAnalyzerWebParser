package com.lolanalyzer.parcer.service;

import com.lolanalyzer.parcer.entity.Match;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayDeque;

@Getter
public class DataSaver {

    private final int maxQueueSize = 2000;
    boolean isBusySaving = false;
    @Autowired
    MatchRepositoryManager manager;
    ArrayDeque<Match> matches;

    public final Object queueLock = new Object();


    public DataSaver(){
        matches = new ArrayDeque<>();
    }

    public void saveMatch(Match match){
        synchronized (queueLock){
            while(matches.size() >= maxQueueSize){
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            matches.add(match);
        }

    }
    public boolean isFull(){
        return matches.size() >= maxQueueSize;
    }

    @Scheduled(fixedDelay = 10)
    protected void DBWriting(){
        if(matches.isEmpty()){
            return;
        }
        Match m;
        synchronized (queueLock){
            m = matches.pop();
        }
        isBusySaving = true;
        manager.saveMatch(m);
        isBusySaving = false;
    }
    public int getQueueSize(){
        return matches.size();
    }
}
