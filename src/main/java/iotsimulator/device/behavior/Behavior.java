package iotsimulator.device.behavior;

import java.time.LocalDateTime;

public interface Behavior<T> {

    /**
     * Renvoi la première valeur pour initialiser le DataPoint
     * @return  Valeur d'initialisation
     */
    T startValue();

    /**
     * Calcul la prochaine valeur en fonction de l'etat précedent
     * @param oldVal    : Ancienne valeur
     * @param time      : Date actuelle
     * @return  Nouvelle valeur du DataPoint
     */
    T nextValue(T oldVal, LocalDateTime time);

}
