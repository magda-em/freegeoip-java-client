package com.fabahaba.freegeoip.client;

import com.fabahaba.freegeoip.data.GeoIP;
import com.google.common.base.Throwables;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class CachedFreeGeoIpClient implements FreeGeoIP {

  private final FreeGeoIP delegate;
  private final LoadingCache<String, Optional<GeoIP>> geoIps;

  public CachedFreeGeoIpClient(final double maxRequestsPerSecond,
      final CacheBuilder<Object, Object> cacheBuilder) {
    this(new FreeGeoIPClient(maxRequestsPerSecond), cacheBuilder);
  }

  public CachedFreeGeoIpClient(final String url, final double maxRequestsPerSecond,
      final CacheBuilder<Object, Object> cacheBuilder) {
    this(new FreeGeoIPClient(url, maxRequestsPerSecond), cacheBuilder);
  }

  public CachedFreeGeoIpClient(final FreeGeoIP client,
      final CacheBuilder<Object, Object> cacheBuilder) {
    this.delegate = client;
    this.geoIps = cacheBuilder.build(CacheLoader.from(ip -> getOptionalGeoIP(ip)));
  }

  public FreeGeoIP getDelegateClient() {
    return delegate;
  }

  private Optional<GeoIP> getOptionalGeoIP() {
    return Optional.of(delegate.getGeoIP());
  }

  private Optional<GeoIP> getOptionalGeoIP(final String ip) {
    return Optional.of(delegate.getGeoIP(ip));
  }

  @Override
  public GeoIP getGeoIP(final String ip) {
    return geoIps.getUnchecked(ip).orElse(null);
  }

  @Override
  public GeoIP getGeoIP() {
    try {
      return geoIps.get("", this::getOptionalGeoIP).orElse(null);
    } catch (final ExecutionException e) {
      throw Throwables.propagate(e);
    }
  }
}
