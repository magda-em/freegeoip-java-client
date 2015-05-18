#freegeoip java client [![Build Status](https://travis-ci.org/jamespedwards42/freegeoip-java-client.svg?branch=master)](https://travis-ci.org/jamespedwards42/freegeoip-java-client) [![JCenter](https://api.bintray.com/packages/jamespedwards42/libs/freegeoip-java-client/images/download.svg) ](https://bintray.com/jamespedwards42/libs/freegeoip-java-client/_latestVersion) [![License](http://img.shields.io/badge/license-Apache--2-blue.svg?style=flat) ](http://www.apache.org/licenses/LICENSE-2.0)


###Usage
```java
final int maxRequestsPerSecond = 1;
final FreeGeoIP freegeoipClient = new FreeGeoIPClient(maxRequestsPerSecond);
final GeoIP myGeoIp = freegeoipClient.getGeoIP();
final Coordinate myCoordinates = myGeoIp.getCoordinates();
...
```

###Dependency Management
####Gradle
```groovy
repositories {
   jcenter()
}

dependencies {
   compile 'com.fabahaba:freegeoip-java-client:0.1.1'
}
```
