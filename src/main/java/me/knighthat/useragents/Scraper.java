package me.knighthat.useragents;

import jdk.jfr.Percentage;
import me.knighthat.useragents.useragent.CommonDesktopUserAgent;
import me.knighthat.useragents.useragent.CommonMobileUserAgent;
import me.knighthat.useragents.useragent.MobileUserAgent;
import me.knighthat.useragents.useragent.UserAgent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

class Scraper {

    @NotNull
    static final String URL = "https://useragents.me";

    @NotNull
    static final String DESKTOP_COMMON = "most-common-desktop-useragents";
    @NotNull
    static final String MOBILE_COMMON  = "most-common-mobile-useragents";

    @NotNull
    static final String LATEST_WINDOWS_DESKTOP = "latest-windows-desktop-useragents";
    @NotNull
    static final String LATEST_MACOS_DESKTOP   = "latest-mac-desktop-useragents";
    @NotNull
    static final String LATEST_LINUX_DESKTOP   = "latest-linux-desktop-useragents";
    @NotNull
    static final String LATEST_IPHONE          = "latest-iphone-useragents";
    @NotNull
    static final String LATEST_IPOD            = "latest-ipod-useragents";
    @NotNull
    static final String LATEST_IPAD            = "latest-ipad-useragents";
    @NotNull
    static final String LATEST_ANDROID_MOBILE  = "latest-android-mobile-useragents";

    @Nullable
    private static Document document;
    private static long     lastUpdate;

    static {
        int thirtyMinutes = 1800000;

        // Schedule update to run every minute
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool( 1 );
        scheduler.scheduleAtFixedRate( () -> {
            long timeSinceLastUpdate = System.currentTimeMillis() - lastUpdate;

            // Only run if document is null,
            // or it's been 30 minutes since last update.
            if ( document == null || (timeSinceLastUpdate > thirtyMinutes) )
                updateDocument();
        }, 0, 1, TimeUnit.MINUTES );
    }

    static void updateDocument() {
        try {
            Scraper.document = Jsoup.connect( URL ).get();
            Scraper.lastUpdate = System.currentTimeMillis();
        } catch ( IOException e ) {
            System.out.println( "failed to fetch data from: " + URL );
            Scraper.document = null;
        }
    }

    static @NotNull Element table( @NotNull String id ) {
        if ( document == null )
            updateDocument();

        Element h2 = document.getElementById( id );
        if ( h2 == null )
            throw new NullPointerException( "there is no h2 element with id " + id );
        else
            return h2.parent().select( "table" ).first();
    }

    static @NotNull Set<CommonDesktopUserAgent> desktopCommon() {
        Function<Elements, CommonDesktopUserAgent> handler = td -> new CommonDesktopUserAgent() {
            @Override
            public @Percentage float share() { return Float.parseFloat( td.get( 0 ).text() ); }

            @Override
            public @NotNull String browser() { return td.get( 1 ).text(); }

            @Override
            public @NotNull String useragent() { return td.get( 2 ).text(); }
        };
        return eachRowToSet( table( DESKTOP_COMMON ), handler );
    }

    static @NotNull Set<CommonMobileUserAgent> mobileCommon() {
        Function<Elements, CommonMobileUserAgent> handler = td -> new CommonMobileUserAgent() {
            @Override
            public float share() { return Float.parseFloat( td.get( 0 ).text() ); }

            @Override
            public @NotNull String device() { return td.get( 1 ).text(); }

            @Override
            public @NotNull String browser() { return td.get( 2 ).text(); }

            @Override
            public @NotNull String useragent() { return td.get( 3 ).text(); }
        };
        return eachRowToSet( table( MOBILE_COMMON ), handler );
    }

    static @NotNull Set<UserAgent> desktopLatest( @NotNull String id ) {
        Function<Elements, UserAgent> handler = td -> new UserAgent() {
            @Override
            public @NotNull String browser() { return td.get( 0 ).text(); }

            @Override
            public @NotNull String useragent() { return td.get( 1 ).text(); }
        };
        return eachRowToSet( table( id ), handler );
    }

    static @NotNull Set<MobileUserAgent> mobileLatest( @NotNull String id ) {
        Function<Elements, MobileUserAgent> handler = td -> new MobileUserAgent() {
            @Override
            public @NotNull String device() { return td.get( 0 ).text(); }

            @Override
            public @NotNull String browser() { return td.get( 1 ).text(); }

            @Override
            public @NotNull String useragent() { return td.get( 2 ).text(); }
        };
        return eachRowToSet( table( id ), handler );
    }

    private static <T extends UserAgent> @NotNull Set<T> eachRowToSet( @NotNull Element table, @NotNull Function<Elements, T> function ) {
        Set<T> results = new HashSet<>();

        Elements tr = table.select( "tbody tr" );
        for (Element row : tr) {
            Elements td = row.select( "td" );
            T userAgent = function.apply( td );
            results.add( userAgent );
        }

        return results;
    }
}
