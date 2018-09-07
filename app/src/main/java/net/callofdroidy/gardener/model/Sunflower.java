package net.callofdroidy.gardener.model;

public class Sunflower extends Plant {

    public Sunflower(String id, String nickName, String species,
                     int waterToleranceMin, int waterToleranceMax,
                     int fertilizerToleranceMin, int fertilizerToleranceMax,
                     int illuminationToleranceMin, int illuminationToleranceMax,
                     int temperatureToleranceMin, int temperatureToleranceMax,
                     int waterConsumptionRate, int fertilizerConsumptionRate) {
        super(id, nickName, species,waterToleranceMin, waterToleranceMax,
                fertilizerToleranceMin, fertilizerToleranceMax,
                illuminationToleranceMin, illuminationToleranceMax,
                temperatureToleranceMin, temperatureToleranceMax,
                waterConsumptionRate, fertilizerConsumptionRate);
    }
}
