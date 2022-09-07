package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Vladimir";
        byte age = 34;
        short temperature = -15;
        int sum = 2_142_500;
        long earthPopulation = 2_100_115_000;
        float cellCount = 3.72e13f;
        double numberOfMosquitos = 1.92E11;
        char dayOfWeek = '3';
        boolean status = true;
        LOG.debug("User info name : {}, age : {}, temperature : {}, sum : {}, earthPopulation : {}, cellCount : {},"
                + "numberOfMosquitos : {}, dayOfWeek : {}, status : {}",
                name, age, temperature, sum, earthPopulation, cellCount,
                numberOfMosquitos, dayOfWeek, status);
    }
}
