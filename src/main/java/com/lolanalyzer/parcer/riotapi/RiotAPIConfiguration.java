package com.lolanalyzer.parcer.riotapi;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RiotAPIConfiguration {
    Region region;
    String apiKey;


    private static RiotAPIConfiguration instance = null;
    private RiotAPIConfiguration(){
        this.region = Region.EUROPE;
    }
    public static RiotAPIConfiguration getInstance(){
        if(instance == null){
            instance = new RiotAPIConfiguration();
        }
        return instance;
    }

    public void setRegion(String region){
        switch (region) {
            case "sea" -> this.region = Region.SEA;
            case "europe" -> this.region = Region.EUROPE;
            case "asia" -> this.region = Region.ASIA;
            case "americas" -> this.region = Region.AMERICAS;
        }
    }


}
