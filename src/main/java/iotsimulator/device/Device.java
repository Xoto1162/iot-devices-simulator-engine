package iotsimulator.device;

import com.google.gson.JsonObject;
import iotsimulator.Simulation;
import iotsimulator.output.Output;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Device {

    /**
     * Nom du device
     */
    private String m_name;

    /**
     * Identifiant unique du device
     */
    private String m_id;

    /**
     * Nombre de message envoyé par seconde par le device
     */
    private double m_messagesBySecond;

    /**
     * Represente le temps auquel le dernier message a été envoyé
     */
    private long m_lastMessageSent;

    /**
     * Date de la dernier mise a jour du device
     */
    private LocalDateTime m_datetime;

    /**
     * Liste des points de données qui seront renvoyés par le device
     * Chaque points de données doit avoir un nom unique
     */
    private Map<String, DataPoint> m_dataPoints;

    /**
     * Constructeur
     * @param name              :   Nom du device
     * @param id                :   Identifiant unique du device
     * @param messagesBySecond  :   Nombre de messages par seconde
     */
    public Device(String name, String id, double messagesBySecond) {
        m_name = name;
        m_id = id;
        m_messagesBySecond = messagesBySecond;
        m_lastMessageSent = 0;
        m_dataPoints = new HashMap<>();
    }

    /**
     * Met à jour l'etat du device et indique si la mise a jour a été faite ou pas
     * @return True si le device a été mis à jour sinon false
     */
    public boolean update() {
        if (System.currentTimeMillis() - m_lastMessageSent > 1000 / m_messagesBySecond) {
            m_lastMessageSent = System.currentTimeMillis();
            m_datetime = LocalDateTime.now();
            nextStep();
            return true;
        }
        return false;
    }

    /**
     * Met a jour l'état du device pour le prochain message
     */
    private void nextStep() {
        for (DataPoint dataPoint : m_dataPoints.values()) {
            dataPoint.nextStep();
        }
    }

    /**
     * Renvoi le message qui represente l'etat actuel du device
     * @return  : Le message courrant sous forme de JsonObject
     */
    public JsonObject getCurrentMessage() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        JsonObject json = new JsonObject();
        json.addProperty("date", formatter.format(m_datetime));
        json.addProperty("deviceName", m_name);
        json.addProperty("deviceId", m_id);
        for (DataPoint dataPoint : m_dataPoints.values()) {
            Object val = dataPoint.getValue();
            if (val instanceof Number) {
                json.addProperty(dataPoint.getName(), (Number) dataPoint.getValue());
            } else {
                json.addProperty(dataPoint.getName(), dataPoint.getValue().toString());
            }
        }
        return json;
    }


    /**
     * Getters identifiant
     * @return  : Identifiant du device
     */
    public String getId() {
        return m_id;
    }

    /**
     * Ajout d'un point de données au device
     * @param dataPoint :   Point de donnée à ajouter
     */
    public void addDataPoint(DataPoint dataPoint) {
        assert(!m_dataPoints.containsKey(dataPoint.getName())) : "Le point de données existe deja";
        m_dataPoints.put(dataPoint.getName(), dataPoint);
    }

}
