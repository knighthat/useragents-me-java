<h2 align="center">
UserAgents.me Java Wrapper
</h2>
<p align="center">
Unofficial API to retrieve most common or latest user agents of most devices and browsers
</p>

---

# Installation

## Maven

```xml

<repositories>
    <repository>
        <id>useragent-me-java</id>
        <url>https://repo.knighthat.me/repository/maven-public/</url>
    </repository>
</repositories>
```

```xml

<dependencies>
    <dependency>
        <groupId>me.knighthat</groupId>
        <artifactId>useragents-me-java</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>
</dependencies>
```

## Gradle

```
repositories {
    maven {
        url 'https://repo.knighthat.me/repository/maven-public/'
    }
}

dependencies {
    implementation 'me.knighthat:useragents-me-java:0.0.1-SNAPSHOT'
}
```

---

# Usage

Everything is accessible through class `UserAgents`.

## `UserAgents.commonDesktop()`

Returns a set of most common user agents that are being used on desktop.

## `UserAgents.commonMobile()`

Returns a set of most common user agents that are being used on mobile.

## `UserAgents.windowsLatest()`

Most used recently user agents on Windows machines.

## `UserAgents.linuxLatest()`

Most used recently user agents on GNU/Linux machines.

## `UserAgents.macosLatest()`

Most used recently user agents on MacOS machines.

## `UserAgents.iphoneLatest()`

Most used recently user agents on Iphone.

## `UserAgents.ipodLatest()`

Most used recently user agents on iPod.

## `UserAgents.ipadLatest()`

Most used recently user agents on iPad.

## `UserAgents.androidLatest()`

Most used recently user agents on android phones.