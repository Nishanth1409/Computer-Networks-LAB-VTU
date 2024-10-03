import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Node {
    Queue<Integer> queue = new LinkedList<>();
    int queueSize;
    int packetsDropped = 0;

    // Constructor to initialize the node with a queue size
    Node(int size) {
        this.queueSize = size;
    }

    // Method to send a packet; increment dropped packets if the queue is full
    void sendPacket(int packetId) {
        if (queue.size() < queueSize) {
            queue.add(packetId);
        } else {
            packetsDropped++;
        }
    }

    // Method to receive (remove) a packet from the queue
    void receivePacket() {
        if (!queue.isEmpty()) {
            queue.poll();
        }
    }

    // Getter to retrieve the number of dropped packets
    int getDroppedPackets() {
        return packetsDropped;
    }

    // Method to get the current number of packets in the queue (congestion window)
    int getCurrentQueueSize() {
        return queue.size();
    }
}

public class EthernetLAN {
    public static void main(String[] args) {
        int numNodes = 5; // Number of nodes in the LAN
        int queueSize = 3; // Maximum size of the queue in each node
        int packetsToSend = 50; // Number of packets to be sent

        Node[] nodes = new Node[numNodes]; // Array to hold the nodes
        Random rand = new Random();

        // Initialize the nodes with the given queue size
        for (int i = 0; i < numNodes; i++) {
            nodes[i] = new Node(queueSize);
        }

        // Simulate the sending of packets
        for (int i = 0; i < packetsToSend; i++) {
            int sender = rand.nextInt(numNodes); // Randomly select a sender node
            nodes[sender].sendPacket(i); // Send a packet
        }

        // Simulate packet processing by each node
        for (Node node : nodes) {
            for (int j = 0; j < 5; j++) {
                node.receivePacket(); // Each node processes 5 packets
            }
        }

        // Output the number of dropped packets for each node
        System.out.println("Dropped packets per node:");
        for (int i = 0; i < numNodes; i++) {
            System.out.println("Node " + i + ": " + nodes[i].getDroppedPackets());
        }

        // Output the current congestion window for each node
        System.out.println("\nCongestion Window (current packets in queue):");
        for (int i = 0; i < numNodes; i++) {
            System.out.println("Node " + i + " congestion window: " + nodes[i].getCurrentQueueSize());
        }
    }
}
