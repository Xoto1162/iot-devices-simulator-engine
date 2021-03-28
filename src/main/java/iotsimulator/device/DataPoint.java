package iotsimulator.device;

import iotsimulator.device.behavior.Behavior;

import java.time.LocalDateTime;

public class DataPoint<T> {

    /**
     * Nom de la mesure
     */
    private String m_name;

    /**
     * Valeur actuelle de la mesure
     */
    private T m_value;

    /**
     * Comportement pour calculer la mesure a chaque étape
     */
    private Behavior<T> m_behavior;

    /**
     * Constructeur
     * @param name      :   Nom du point de données
     * @param behavior  :   Comportement du points de données
     */
    public DataPoint(String name, Behavior<T> behavior) {
        m_name = name;
        m_behavior = behavior;
        m_value = behavior.startValue();
    }

    /**
     * Calcule la prochaine valeur de la mesure
     */
    public void nextStep() {
        m_value = m_behavior.nextValue(m_value, LocalDateTime.now());
    }

    /**
     * Getter nom de la mesure
     * @return  Nom de la mesure
     */
    public String getName() {
        return m_name;
    }

    /**
     * Getter valeur de la mesure
     * @return  Valeur de la mesure
     */
    public T getValue() {
        return m_value;
    }

}
