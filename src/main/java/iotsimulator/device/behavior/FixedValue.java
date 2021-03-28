package iotsimulator.device.behavior;

import java.time.LocalDateTime;

public class FixedValue<T> implements Behavior<T> {

    /**
     * Valeur à retourner
     */
    private T m_value;

    /**
     * Constructeur
     * @param value :   Valeur qui sera retournée
     */
    public FixedValue(T value) {
        m_value = value;
    }

    @Override
    public T startValue() {
        return m_value;
    }

    @Override
    public T nextValue(T oldVal, LocalDateTime time) {
        return m_value;
    }

}
