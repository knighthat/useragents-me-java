package me.knighthat.useragents;

import me.knighthat.useragents.useragent.CommonDesktopUserAgent;
import me.knighthat.useragents.useragent.CommonMobileUserAgent;
import me.knighthat.useragents.useragent.UserAgent;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;

public class UserAgents {

    public static @NotNull Set<CommonDesktopUserAgent> commonDesktop() {
        try {
            return Scraper.desktopCommon();
        } catch ( NullPointerException e ) {
            System.out.println( "failed to fetch common desktop user agents" );
            return Collections.emptySet();
        }
    }

    public static @NotNull Set<CommonMobileUserAgent> commonMobile() {
        try {
            return Scraper.mobileCommon();
        } catch ( NullPointerException e ) {
            System.out.println( "failed to fetch common mobile user agents" );
            return Collections.emptySet();
        }
    }

    public static @NotNull Set<UserAgent> windowsLatest() {
        try {
            return Scraper.desktopLatest( Scraper.LATEST_WINDOWS_DESKTOP );
        } catch ( NullPointerException e ) {
            System.out.println( "failed to fetch latest windows user agents" );
            return Collections.emptySet();
        }
    }

    public static @NotNull Set<UserAgent> macosLatest() {
        try {
            return Scraper.desktopLatest( Scraper.LATEST_MACOS_DESKTOP );
        } catch ( NullPointerException e ) {
            System.out.println( "failed to fetch latest macOS user agents" );
            return Collections.emptySet();
        }
    }

    public static @NotNull Set<UserAgent> linuxLatest() {
        try {
            return Scraper.desktopLatest( Scraper.LATEST_LINUX_DESKTOP );
        } catch ( NullPointerException e ) {
            System.out.println( "failed to fetch latest linux user agents" );
            return Collections.emptySet();
        }
    }

    public static @NotNull Set<UserAgent> iphoneLatest() {
        try {
            return Scraper.desktopLatest( Scraper.LATEST_IPHONE );
        } catch ( NullPointerException e ) {
            System.out.println( "failed to fetch latest iphone user agents" );
            return Collections.emptySet();
        }
    }

    public static @NotNull Set<UserAgent> ipodLatest() {
        try {
            return Scraper.desktopLatest( Scraper.LATEST_IPOD );
        } catch ( NullPointerException e ) {
            System.out.println( "failed to fetch latest ipod user agents" );
            return Collections.emptySet();
        }
    }

    public static @NotNull Set<UserAgent> ipadLatest() {
        try {
            return Scraper.desktopLatest( Scraper.LATEST_IPAD );
        } catch ( NullPointerException e ) {
            System.out.println( "failed to fetch latest ipad user agents" );
            return Collections.emptySet();
        }
    }

    public static @NotNull Set<UserAgent> androidLatest() {
        try {
            return Scraper.desktopLatest( Scraper.LATEST_ANDROID_MOBILE );
        } catch ( NullPointerException e ) {
            System.out.println( "failed to fetch latest android user agents" );
            return Collections.emptySet();
        }
    }

    public static void update() { Scraper.updateDocument(); }
}
