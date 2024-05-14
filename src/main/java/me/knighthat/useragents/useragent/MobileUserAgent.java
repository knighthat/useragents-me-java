package me.knighthat.useragents.useragent;

import org.jetbrains.annotations.NotNull;

public interface MobileUserAgent extends UserAgent {

    @NotNull String device();
}
