import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Node {
    Queue<Integer> queue = new LinkedList<>();
    int queueSize, packetsDropped = 0;

    Node(int size) {
        this.queueSize = size;
    }

    // Simulate sending a ping message to this node
    void sendPing(int packetId) {
        if (queue.size() < queueSize) {
            queue.add(packetId);
        } else {
            packetsDropped++;
        }
    }

    // Simulate receiving and processing a ping message
    void receivePing() {
        if (!queue.isEmpty()) {
            queue.poll();  // Process one packet
        }
    }
}

public class NetworkSimulation {
    public static void main(String[] args) {
        int numNodes = 6, queueSize = 2, pingsToSend = 300; // High load of pings
        Node[] nodes = new Node[numNodes];
        Random rand = new Random();

        // Initialize nodes with the specified queue size
        for (int i = 0; i < numNodes; i++) {
            nodes[i] = new Node(queueSize);
        }

        // Simulate sending pings between random nodes
        for (int i = 0; i < pingsToSend; i++) {
            int sender = rand.nextInt(numNodes);  // Select random sender
            int receiver = rand.nextInt(numNodes);  // Select random receiver
            while (receiver == sender) {
                receiver = rand.nextInt(numNodes);  // Ensure receiver is not the same as sender
            }
            nodes[receiver].sendPing(i);  // Send ping to the receiver node
        }

        // Simulate processing pings (not all packets will be processed immediately)
        for (Node node : nodes) {
            for (int i = 0; i < 10; i++) {  // Try to process some packets
                node.receivePing();
            }
        }

        // Print the number of dropped packets due to congestion for each node
        for (int i = 0; i < numNodes; i++) {
            System.out.println("Node " + i + " dropped packets: " + nodes[i].packetsDropped);
        }
    }
}
