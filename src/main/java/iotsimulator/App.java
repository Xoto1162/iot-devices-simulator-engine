package iotsimulator;

import iotsimulator.device.DataPoint;
import iotsimulator.device.Device;
import iotsimulator.device.behavior.FixedValue;
import iotsimulator.device.behavior.IncrementNumber;
import iotsimulator.device.behavior.RandomInt;
import iotsimulator.output.Console;
import iotsimulator.output.Mqtt;

import java.util.Random;

public class App {

    public static void main(String[] args) {
        simulationExample();
    }

    public static void simulationExample() {
        // Création de la simulation et des outputs
        Simulation simulation = new Simulation("test");
        simulation.addOutput(new Console());
        try {
            Mqtt mqtt = new Mqtt("localhost", 1883, "test");
            simulation.addOutput(mqtt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Création des devices
        Device in_temp = new Device("in_temp", "fgerergr", 0.1);
        in_temp.addDataPoint(new DataPoint<>("temperature", new RandomInt(16, 22)));
        in_temp.addDataPoint(new DataPoint<>("temperature_unit", new FixedValue<>("°C")));
        simulation.addDevice(in_temp);
        Device out_temp = new Device("out_temp", "iriebrg", 0.1);
        out_temp.addDataPoint(new DataPoint<>("temperature", new RandomInt(-5, 28)));
        out_temp.addDataPoint(new DataPoint<>("temperature_unit", new FixedValue<>("°C")));
        simulation.addDevice(out_temp);
        Device nb_persons = new Device("nb_persons", "oifbnriogebrg", 0.1);
        nb_persons.addDataPoint(new DataPoint<>("value", new RandomInt(0, 24)));
        simulation.addDevice(nb_persons);
        Device heating = new Device("heating", "ubgeuber", 0.1);
        heating.addDataPoint(new DataPoint<>("level", new RandomInt(0, 9)));
        simulation.addDevice(heating);

        // Démarrage de la simulation
        Thread thread = new Thread(simulation);
        thread.start();
    }

}
