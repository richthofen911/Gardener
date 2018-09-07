package net.callofdroidy.gardener.model;

public class Gardener {
    public void water(Plant plant, int volume) {
        plant.watered(volume);
    }

    public void fertilize(Plant plant, int amount) {
        plant.fertilized(amount);
    }

    public void illuminate(Plant plant, int luminousFlux) {
        plant.illuminated(luminousFlux);
    }
}
