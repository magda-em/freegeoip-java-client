package com.fabahaba.freegeoip.client;

import com.fabahaba.freegeoip.data.GeoIP;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.AbstractLoadBalancer;
import com.netflix.loadbalancer.AvailabilityFilteringRule;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.ribbon.LoadBalancingTarget;
import feign.slf4j.Slf4jLogger;

import java.util.Collection;
import java.util.stream.Collectors;

public class LoadBalancedFreeGeoIPClient implements FreeGeoIP {

  public static final String DEFAULT_APPNAME = "freegeoip";
  public static final String DEFAULT_NAMESPACE = "ribbon";

  private final LoadBalancingTarget<FreeGeoIP> lbTarget;
  private final FreeGeoIP freeGeoIp;

  public LoadBalancedFreeGeoIPClient() {
    this("http://" + DEFAULT_APPNAME);
  }

  public LoadBalancedFreeGeoIPClient(final String schemeName) {
    this.lbTarget = LoadBalancingTarget.create(FreeGeoIP.class, schemeName);
    this.freeGeoIp =
        Feign.builder().logger(new Slf4jLogger(FreeGeoIP.class)).decoder(new GsonDecoder())
            .target(lbTarget);
  }

  public static LoadBalancedFreeGeoIPClient createDefault(final Collection<String> hostPorts) {
    return LoadBalancedFreeGeoIPClient.createDefault(hostPorts.stream().collect(
        Collectors.joining(",")));
  }

  /**
   *
   * @param hostPorts comma separated list of host:ports.
   * @return a new LoadBalancedFreeGeoIPClient.
   * @see <a
   *      href="https://github.com/Netflix/ribbon/wiki/Working-with-load-balancers">github.com/Netflix/ribbon/wiki/Working-with-load-balancers</a>
   **/
  public static LoadBalancedFreeGeoIPClient createDefault(final String hostPorts) {
    ConfigurationManager.getConfigInstance().setProperty(
        DEFAULT_APPNAME + "." + DEFAULT_NAMESPACE + ".listOfServers", hostPorts);
    ConfigurationManager.getConfigInstance().setProperty(
        DEFAULT_APPNAME + "." + DEFAULT_NAMESPACE + ".NFLoadBalancerRuleClassName",
        AvailabilityFilteringRule.class.getName());
    ConfigurationManager.getConfigInstance().setProperty(
        "niws.loadbalancer." + DEFAULT_APPNAME + ".connectionFailureCountThreshold", "2");
    ConfigurationManager.getConfigInstance().setProperty(
        "niws.loadbalancer." + DEFAULT_APPNAME + ".circuitTripMaxTimeoutSeconds", "60");
    ConfigurationManager.getConfigInstance().setProperty(
        DEFAULT_APPNAME + "." + DEFAULT_NAMESPACE + ".ActiveConnectionsLimit", "2");

    return new LoadBalancedFreeGeoIPClient();
  }

  public AbstractLoadBalancer getLoadBalancer() {
    return lbTarget.lb();
  }

  @Override
  public GeoIP getGeoIP(final String ip) {
    return freeGeoIp.getGeoIP(ip);
  }

  @Override
  public GeoIP getGeoIP() {
    return freeGeoIp.getGeoIP();
  }
}
