package iotsimulator;

import com.google.gson.JsonObject;
import iotsimulator.device.Device;
import iotsimulator.output.Output;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Simulation implements Runnable {

    /**
     * Nom de la simulation
     */
    private String m_name;

    /**
     * Tout les devices de la simulation
     * avec leur identifiant comme clée
     */
    private Map<String, Device> m_devices;

    /**
     * Toutes les sorties de la simulation
     */
    private List<Output> m_outputs;

    /**
     * Constructeur
     * @param name  :   Nom de la simulation
     */
    public Simulation(String name) {
        m_name = name;
        m_devices = new TreeMap<>();
        m_outputs = new LinkedList<>();
    }

    /**
     * Ajout d'un device dans la simulation
     * @param device    :   Device a ajouter
     */
    public void addDevice(Device device) {
        assert(!m_devices.containsKey(device.getId())) : "L'identifiant du device existe déjà dans cette simulation";
        m_devices.put(device.getId(), device);
    }

    /**
     * Ajout d'une sortie dans la simulation
     * @param output    :   Output a ajouter
     */
    public void addOutput(Output output) {
        m_outputs.add(output);
    }

    /**
     * Appel la méthode update de tout les devices de la simulation
     */
    public void update() {
        for (Device device : m_devices.values()) {
            if (device.update()) {
                JsonObject message = device.getCurrentMessage();
                for (Output output : m_outputs) {
                    output.sendMessage(message.toString());
                }
            }
        }
    }

    /**
     * Lancement de la simulation
     */
    public void run() {
        while (true) {
            update();
        }
    }

    /**
     * Getter des outputs
     * @return  :   Liste des sorties de la simulation
     */
    public List<Output> getOutputs() {
        return m_outputs;
    }

}
