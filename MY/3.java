// Program to simulate Ethernet LAN and track congestion window
import java.util.Random;

class EthernetNode {
    String name;

    EthernetNode(String name) {
        this.name = name;
    }
}

class TrafficSimulation {
    static int simulateTraffic(int packetsSent, int windowSize) {
        return packetsSent > windowSize ? packetsSent - windowSize : 0;
    }

    public static void main(String[] args) {
        int n = 5; // Number of nodes
        EthernetNode[] nodes = new EthernetNode[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new EthernetNode("Node " + (i + 1));
        }

        Random random = new Random();
        int congestionWindow = 100; // Example congestion window size
        for (int i = 0; i < n; i++) {
            int packetsSent = random.nextInt(200);
            int droppedPackets = simulateTraffic(packetsSent, congestionWindow);
            System.out.println("Node " + (i + 1) + ": Sent = " + packetsSent + ", Dropped = " + droppedPackets);
        }
    }
}
