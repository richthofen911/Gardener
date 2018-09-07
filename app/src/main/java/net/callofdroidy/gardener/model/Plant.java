package net.callofdroidy.gardener.model;

public abstract class Plant {
    int waterToleranceMin;
    int waterToleranceMax;
    int fertilizerToleranceMin;
    int fertilizerToleranceMax;
    int illuminationToleranceMin;
    int illuminationToleranceMax;
    int temperatureToleranceMin;
    int temperatureToleranceMax;
    int currentWater;
    int currentFertilizer;
    int currentIllumination;
    int waterConsumptionRate; // per 3 hours
    int fertilizerConsumptionRate; // per 3 hours

    public Plant(int waterToleranceMin, int waterToleranceMax,
                 int fertilizerToleranceMin, int fertilizerToleranceMax,
                 int illuminationToleranceMin, int illuminationToleranceMax,
                 int temperatureToleranceMin, int temperatureToleranceMax,
                 int waterConsumptionRate, int fertilizerConsumptionRate) {
        this.waterToleranceMin = waterToleranceMin;
        this.waterToleranceMax = waterToleranceMax;
        this.fertilizerToleranceMin = fertilizerToleranceMin;
        this.fertilizerToleranceMax = fertilizerToleranceMax;
        this.illuminationToleranceMin = illuminationToleranceMin;
        this.illuminationToleranceMax = illuminationToleranceMax;
        this.temperatureToleranceMin = temperatureToleranceMin;
        this.temperatureToleranceMax = temperatureToleranceMax;
        this.currentWater = waterToleranceMax * 85 / 100;
        this.currentFertilizer = fertilizerToleranceMax * 85 / 100;
        this.currentIllumination = currentIllumination * 85 / 100;
        this.waterConsumptionRate = waterConsumptionRate;
        this.fertilizerConsumptionRate = fertilizerConsumptionRate;
    }

    public abstract void watered(int volume);
    public abstract void fertilized(int volume);
    public abstract void illuminated(int luminousFlux);
}
