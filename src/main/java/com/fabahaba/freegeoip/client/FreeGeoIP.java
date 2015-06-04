package com.fabahaba.freegeoip.client;

import com.fabahaba.freegeoip.data.GeoIP;

import feign.Param;
import feign.RequestLine;

public interface FreeGeoIP {

  @RequestLine("GET /json/{ip}")
  GeoIP getGeoIP(@Param("ip") final String ip);

  @RequestLine("GET /json")
  GeoIP getGeoIP();
}
