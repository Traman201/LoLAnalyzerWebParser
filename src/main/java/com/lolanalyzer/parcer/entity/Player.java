package com.lolanalyzer.parcer.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Slf4j
@Setter
@Getter
public class Player {
    @Id
    private int id;
}
