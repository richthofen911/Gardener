package net.callofdroidy.gardener.model;

public class Plant {
    private final String id;
    private String nickName;
    private final String species;
    private final int waterToleranceMin;
    private final int waterToleranceMax;
    private final int fertilizerToleranceMin;
    private final int fertilizerToleranceMax;
    private final int illuminationToleranceMin;
    private final int illuminationToleranceMax;
    private final int temperatureToleranceMin;
    private final int temperatureToleranceMax;
    private int currentWater;
    private int currentFertilizer;
    private int currentIllumination;
    private final int waterConsumptionRate; // per 3 hours
    private final int fertilizerConsumptionRate; // per 3 hours

    public Plant(String id, String nickName, String species,
                 int waterToleranceMin, int waterToleranceMax,
                 int fertilizerToleranceMin, int fertilizerToleranceMax,
                 int illuminationToleranceMin, int illuminationToleranceMax,
                 int temperatureToleranceMin, int temperatureToleranceMax,
                 int waterConsumptionRate, int fertilizerConsumptionRate) {
        this.id = id;
        this.nickName = nickName;
        this.species = species;
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
        this.currentIllumination = illuminationToleranceMax * 85 / 100;
        this.waterConsumptionRate = waterConsumptionRate;
        this.fertilizerConsumptionRate = fertilizerConsumptionRate;
    }

    public void watered(int volume) {
        currentWater += volume;
    }

    public void fertilized(int volume) {
        currentFertilizer += volume;
    }

    public void illuminated(int luminousFlux) {
        currentIllumination += luminousFlux;
    }

    public String getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSpecies() {
        return species;
    }

    public int getWaterToleranceMin() {
        return waterToleranceMin;
    }

    public int getWaterToleranceMax() {
        return waterToleranceMax;
    }

    public int getFertilizerToleranceMin() {
        return fertilizerToleranceMin;
    }

    public int getFertilizerToleranceMax() {
        return fertilizerToleranceMax;
    }

    public int getIlluminationToleranceMin() {
        return illuminationToleranceMin;
    }

    public int getIlluminationToleranceMax() {
        return illuminationToleranceMax;
    }

    public int getTemperatureToleranceMin() {
        return temperatureToleranceMin;
    }

    public int getTemperatureToleranceMax() {
        return temperatureToleranceMax;
    }

    public int getCurrentWater() {
        return currentWater;
    }

    public int getCurrentFertilizer() {
        return currentFertilizer;
    }

    public int getCurrentIllumination() {
        return currentIllumination;
    }

    public int getWaterConsumptionRate() {
        return waterConsumptionRate;
    }

    public int getFertilizerConsumptionRate() {
        return fertilizerConsumptionRate;
    }
}
