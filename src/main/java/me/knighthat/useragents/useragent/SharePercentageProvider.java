package me.knighthat.useragents.useragent;

import jdk.jfr.Percentage;

public interface SharePercentageProvider {

    @Percentage
    float share();
}
