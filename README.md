#[freegeoip](https://github.com/fiorix/freegeoip) java client [![Build Status](https://travis-ci.org/jamespedwards42/freegeoip-java-client.svg?branch=master)](https://travis-ci.org/jamespedwards42/freegeoip-java-client) [![JCenter](https://api.bintray.com/packages/jamespedwards42/libs/freegeoip-java-client/images/download.svg) ](https://bintray.com/jamespedwards42/libs/freegeoip-java-client/_latestVersion) [![License](http://img.shields.io/badge/license-Apache--2-blue.svg?style=flat) ](http://www.apache.org/licenses/LICENSE-2.0)


###Usage
####Ratelimited Client
```java
final int maxRequestsPerSecond = 1;
final FreeGeoIP freegeoip = new FreeGeoIPClient(maxRequestsPerSecond);

final GeoIP myGeoIp = freegeoip.getGeoIP();
final Coordinate myCoordinates = myGeoIp.getCoordinates();
...
```

####[Netflix Ribbon](https://github.com/Netflix/ribbon) Load Balanced Client
```java
final String hostPorts = "host1:port1,host2:port2,host3:port3";
final LoadBalancedFreeGeoIPClient freegeoip = LoadBalancedFreeGeoIPClient.createDefault(hostPorts);

final GeoIP myGeoIp = freegeoip.getGeoIP();
final Coordinate myCoordinates = myGeoIp.getCoordinates();

System.out.println(freegeoip.getLoadBalancer().getLoadBalancerStats());
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
