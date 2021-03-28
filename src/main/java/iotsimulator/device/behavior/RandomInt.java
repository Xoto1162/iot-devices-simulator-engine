package iotsimulator.device.behavior;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class RandomInt implements Behavior<Integer> {

    /**
     * Valeur minimum
     */
    private int m_min;

    /**
     * Valeur maximum
     */
    private int m_max;

    /**
     * Constructeur
     * @param min   :   Minimum de l'interval de génération
     * @param max   :   Maximum de l'interval de genération
     */
    public RandomInt(int min, int max) {
        m_min = min;
        m_max = max;
    }

    @Override
    public Integer startValue() {
        return ThreadLocalRandom.current().nextInt(m_min, m_max + 1);
    }

    @Override
    public Integer nextValue(Integer oldVal, LocalDateTime time) {
        return ThreadLocalRandom.current().nextInt(m_min, m_max + 1);
    }

}
