package by.automation.models;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Train implements Runnable {
    private static int generator = 1;
    private final int id;
    private final BlockingQueue<Tunnel> tunnels;
    private final String direction;

    public Train(BlockingQueue<Tunnel> tunnels, String direction) {
        this.tunnels = tunnels;
        this.direction = direction;
        this.id = generator++;
    }

    @Override
    public void run() {
        try {
            while (true) {
                long throughTunnelTime = 1000L;
                if (!tunnels.isEmpty()) {
                    Tunnel tunnel = tunnels.take();
                    TimeUnit.MILLISECONDS.sleep(throughTunnelTime);
                    if (direction.equals("west")) {
                        System.out.println("Train " + id + " passed trough " + tunnel + " from west side");
                    }
                    if (direction.equals("east")) {
                        System.out.println("Train " + id + " passed trough " + tunnel + " from east side");
                    }
                    tunnels.put(tunnel);
                    break;
                } else {
                    System.out.println("Train " + id + " is waiting to pass through tunnel");
                    TimeUnit.MILLISECONDS.sleep(throughTunnelTime + 100);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                " direction=" + direction +
                '}';
    }
}
