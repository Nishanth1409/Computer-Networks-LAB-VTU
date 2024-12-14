// Program to simulate ping messages and find packet drops in a 6-node network
import java.util.Random;

class NetworkNode {
    String name;

    NetworkNode(String name) {
        this.name = name;
    }
}

class PingSimulation {
    static int simulatePing(int packetsSent, int maxQueueSize) {
        return packetsSent > maxQueueSize ? packetsSent - maxQueueSize : 0;
    }

    public static void main(String[] args) {
        NetworkNode[] nodes = {
            new NetworkNode("Node 1"),
            new NetworkNode("Node 2"),
            new NetworkNode("Node 3"),
            new NetworkNode("Node 4"),
            new NetworkNode("Node 5"),
            new NetworkNode("Node 6")
        };

        int maxQueueSize = 50; // Example max queue size
        Random random = new Random();
        int totalPackets = 0, droppedPackets = 0;

        for (int i = 0; i < nodes.length - 1; i++) {
            int packetsSent = random.nextInt(100);
            totalPackets += packetsSent;
            droppedPackets += simulatePing(packetsSent, maxQueueSize);
        }

        System.out.println("Total packets sent: " + totalPackets);
        System.out.println("Packets dropped due to congestion: " + droppedPackets);
    }
}

