package iotsimulator.device.behavior;

import java.time.LocalDateTime;

public class IncrementNumber implements Behavior<Double> {

    /**
     * Valeur de départ
     */
    private double m_start;

    /**
     * Valeur de l'incrementation a effectuer
     */
    private double m_step;

    /**
     * Constructeur
     * @param start :   Valeur de départ
     * @param step  :   Valeur de l'incrémentation
     */
    public IncrementNumber(double start, double step) {
        m_start = start;
        m_step = step;
    }

    @Override
    public Double startValue() {
        return m_start;
    }

    @Override
    public Double nextValue(Double oldVal, LocalDateTime time) {
        return oldVal + m_step;
    }

}
