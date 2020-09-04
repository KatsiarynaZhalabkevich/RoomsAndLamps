package com.github.zhalabkevich.roomservice.service.impl;

import com.github.zhalabkevich.roomservice.service.CountryIPService;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Service
public class CountryIPServiceImpl implements CountryIPService {
    private final static Logger logger = LogManager.getLogger(CountryIPServiceImpl.class);

    @Value("${spring.dbLocation}")
    private String dbLocation;
    private DatabaseReader dbReader;


    public CountryIPServiceImpl()  {
    }
    @PostConstruct
    public void init(){
        try {
            File database = new File(dbLocation);
            dbReader = new DatabaseReader.Builder(database).build();
        } catch (IOException e) {
            logger.info("IO Exception in method init()");
        }
    }

    public String getCountryByIP(String ip) throws IOException, GeoIp2Exception {
        //as DB with IP is free and may be rather old let's hardcode ip
        InetAddress ipAddress = InetAddress.getByName("128.65.0.0"); //change IP or make random func()
        CountryResponse response = dbReader.country(ipAddress);
        return response.getCountry().getName();
    }
}
