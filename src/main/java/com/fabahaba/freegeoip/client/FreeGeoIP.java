package com.fabahaba.freegeoip.client;

import com.fabahaba.freegeoip.data.GeoIP;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface FreeGeoIP {

  @RequestLine("GET json/{ip}")
  @Headers("Content-Type: application/json")
  GeoIP getGeoIP(@Param("ip") final String ip);

  @RequestLine("GET json")
  @Headers("Content-Type: application/json")
  GeoIP getGeoIP();
}
