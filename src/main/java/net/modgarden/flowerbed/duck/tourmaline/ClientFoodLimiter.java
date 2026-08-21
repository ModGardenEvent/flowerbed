package net.modgarden.flowerbed.duck.tourmaline;

public interface ClientFoodLimiter {
    default void doing_it_myself$setMaxFood(int maxFood) {
        throw new UnsupportedOperationException();
    }
}
