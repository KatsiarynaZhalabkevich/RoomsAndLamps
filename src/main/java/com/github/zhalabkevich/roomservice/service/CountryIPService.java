package com.github.zhalabkevich.roomservice.service;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import java.io.IOException;

public interface CountryIPService {
    String getCountryByIP(String ip) throws IOException, GeoIp2Exception;
}
