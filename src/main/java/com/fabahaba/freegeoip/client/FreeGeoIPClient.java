package com.fabahaba.freegeoip.client;

import com.fabahaba.fava.rest.client.RateLimitedFeignClient;
import com.fabahaba.freegeoip.data.GeoIP;

public class FreeGeoIPClient extends RateLimitedFeignClient<FreeGeoIP> implements FreeGeoIP {

  public static final String DEFAULT_URL = "http://freegeoip.net";

  public FreeGeoIPClient(final double maxRequestsPerSecond) {
    this(DEFAULT_URL, maxRequestsPerSecond);
  }

  public FreeGeoIPClient(final String url, final double maxRequestsPerSecond) {
    super(url, FreeGeoIP.class, maxRequestsPerSecond);
  }

  @Override
  public GeoIP getGeoIP(final String ip) {
    return getClient().getGeoIP(ip);
  }

  @Override
  public GeoIP getGeoIP() {
    return getClient().getGeoIP();
  }
}
