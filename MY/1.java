// Program to simulate a point-to-point network with duplex links
// Calculates the number of packets dropped based on queue size and bandwidth

import java.util.Random;

class PointToPointNetwork {
    static class Node {
        String name;

        Node(String name) {
            this.name = name;
        }
    }

    static class Link {
        Node node1, node2;
        int bandwidth; // in Mbps
        int queueSize; // in packets

        Link(Node node1, Node node2, int bandwidth, int queueSize) {
            this.node1 = node1;
            this.node2 = node2;
            this.bandwidth = bandwidth;
            this.queueSize = queueSize;
        }

        int simulateTraffic(int packetsSent) {
            int packetsDropped = 0;
            if (packetsSent > queueSize) {
                packetsDropped = packetsSent - queueSize;
            }
            return packetsDropped;
        }
    }

    public static void main(String[] args) {
        Node nodeA = new Node("Node A");
        Node nodeB = new Node("Node B");
        Node nodeC = new Node("Node C");

        Link link1 = new Link(nodeA, nodeB, 10, 50); // Bandwidth: 10 Mbps, Queue Size: 50
        Link link2 = new Link(nodeB, nodeC, 20, 100); // Bandwidth: 20 Mbps, Queue Size: 100

        Random random = new Random();
        int packetsSent1 = random.nextInt(100); // Random traffic
        int packetsSent2 = random.nextInt(150);

        System.out.println("Packets dropped on Link 1: " + link1.simulateTraffic(packetsSent1));
        System.out.println("Packets dropped on Link 2: " + link2.simulateTraffic(packetsSent2));
    }
}
