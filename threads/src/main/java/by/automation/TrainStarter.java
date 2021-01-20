package by.automation;

import by.automation.models.Train;
import by.automation.models.Tunnel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrainStarter implements Runnable {

    private final BlockingQueue<Train> trains;

    public TrainStarter(BlockingQueue<Tunnel> tunnels, int numberOfTrains) {
        trains = new LinkedBlockingQueue<>();
        for (int i = 0; i < numberOfTrains; i++) {
            if (i % 2 == 0) {
                trains.add((new Train(tunnels, "east")));
            } else {
                trains.add((new Train(tunnels, "west")));
            }
        }
    }

    @Override
    public void run() {
        try {
            do {
                new Thread(trains.take()).start();
            } while (!trains.isEmpty());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
