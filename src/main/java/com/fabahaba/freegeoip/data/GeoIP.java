package com.fabahaba.freegeoip.data;

import com.fabahaba.fava.geo.Geo.Coordinate;
import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Objects;

public class GeoIP {

  private final String ip;
  @SerializedName("country_code")
  private final String countryCode;
  @SerializedName("country_name")
  private final String countryName;
  @SerializedName("region_code")
  private final String regionCode;
  @SerializedName("region_name")
  private final String regionName;
  private final String city;
  @SerializedName("zip_code")
  private final String zipcode;
  @SerializedName("time_zone")
  private final String timeZone;
  private final BigDecimal latitude;
  private final BigDecimal longitude;
  @SerializedName("metro_code")
  private final String metroCode;
  @SerializedName("area_code")
  private final String areaCode;

  public GeoIP(final String ip, final String countryCode, final String countryName,
      final String regionCode, final String regionName, final String city, final String zipcode,
      final String timeZone, final BigDecimal latitude, final BigDecimal longitude,
      final String metroCode, final String areaCode) {
    this.ip = ip;
    this.countryCode = countryCode;
    this.countryName = countryName;
    this.regionCode = regionCode;
    this.regionName = regionName;
    this.city = city;
    this.zipcode = zipcode;
    this.timeZone = timeZone;
    this.latitude = latitude;
    this.longitude = longitude;
    this.metroCode = metroCode;
    this.areaCode = areaCode;
  }

  public String getIp() {
    return ip;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public String getCountryName() {
    return countryName;
  }

  public String getRegionCode() {
    return regionCode;
  }

  public String getRegionName() {
    return regionName;
  }

  public String getCity() {
    return city;
  }

  public String getZipcode() {
    return zipcode;
  }

  public String getTimeZone() {
    return timeZone;
  }

  public BigDecimal getLatitude() {
    return latitude;
  }

  public BigDecimal getLongitude() {
    return longitude;
  }

  public Coordinate getCoordinates() {
    return new Coordinate(getLatitude().doubleValue(), getLongitude().doubleValue());
  }

  public String getMetroCode() {
    return metroCode;
  }

  public String getAreaCode() {
    return areaCode;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("ip", ip).add("countryCode", countryCode)
        .add("countryName", countryName).add("regionCode", regionCode)
        .add("regionName", regionName).add("city", city).add("zipcode", zipcode)
        .add("timeZone", timeZone).add("latitude", latitude).add("longitude", longitude)
        .add("metroCode", metroCode).add("areaCode", areaCode).toString();
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other)
      return true;
    if (other == null)
      return false;
    if (!getClass().equals(other.getClass()))
      return false;
    final GeoIP castOther = (GeoIP) other;
    return Objects.equals(ip, castOther.ip) && Objects.equals(countryCode, castOther.countryCode)
        && Objects.equals(countryName, castOther.countryName)
        && Objects.equals(regionCode, castOther.regionCode)
        && Objects.equals(regionName, castOther.regionName) && Objects.equals(city, castOther.city)
        && Objects.equals(zipcode, castOther.zipcode)
        && Objects.equals(timeZone, castOther.timeZone)
        && Objects.equals(latitude, castOther.latitude)
        && Objects.equals(longitude, castOther.longitude)
        && Objects.equals(metroCode, castOther.metroCode)
        && Objects.equals(areaCode, castOther.areaCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ip, countryCode, countryName, regionCode, regionName, city, zipcode,
        timeZone, latitude, longitude, metroCode, areaCode);
  }
}
