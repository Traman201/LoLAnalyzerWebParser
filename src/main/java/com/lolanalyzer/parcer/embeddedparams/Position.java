package com.lolanalyzer.parcer.embeddedparams;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Setter
@Getter
@Slf4j
@Embeddable
public class Position implements Serializable {
    long x;
    long y;
}
