package com.lolanalyzer.parcer.riotapi;

public class RiotAPIHelper {

    public static String regionConverter(Region regions){
        return switch (regions){
            case SEA -> "sea";
            case ASIA -> "asia";
            case EUROPE -> "europe";
            case AMERICAS -> "americas";
        };

    }
}
