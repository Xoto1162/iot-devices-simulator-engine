package iotsimulator.output;

import org.fusesource.mqtt.client.FutureConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;

public class Mqtt implements Output {

    /**
     * MQTT Host
     */
    private String m_host;

    /**
     * MQQT port
     */
    private int m_port;

    /**
     * MQTT Topic
     */
    private String m_topic;

    /**
     * Connection au serveur MQTT
     */
    private FutureConnection m_connection;

    /**
     * Constructeur
     * @param host      :   Host MQTT
     * @param port      :   Port MQTT
     * @param topic     :   Topic MQTT
     * @throws Exception
     */
    public Mqtt(String host, int port, String topic) throws Exception {
        m_host = host;
        m_port = port;
        m_topic = topic;
        MQTT mqtt = new MQTT();
        mqtt.setHost(m_host, m_port);
        m_connection = mqtt.futureConnection();
        m_connection.connect();
    }

    @Override
    public void sendMessage(String message) {
        try {
            m_connection.publish(m_topic, message.getBytes(), QoS.AT_LEAST_ONCE, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
