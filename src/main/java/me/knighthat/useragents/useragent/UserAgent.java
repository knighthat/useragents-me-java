package me.knighthat.useragents.useragent;

import org.jetbrains.annotations.NotNull;

public interface UserAgent {

    @NotNull String browser();

    @NotNull String useragent();
}
