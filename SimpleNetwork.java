import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Node {
  Queue<Integer> queue = new LinkedList<>();
  int queueSize, packetsDropped = 0;

  Node(int size) {
    this.queueSize = size;
  }

  // Send a packet to this node
  void sendPacket(int packetId) {
    if (queue.size() < queueSize) {
      queue.add(packetId);
    } else {
      packetsDropped++;
    }
  }

  // Process a packet
  void receivePacket() {
    if (!queue.isEmpty())
      queue.poll();
  }
}

public class SimpleNetwork {
  public static void main(String[] args) {
    int numNodes = 3, queueSize = 2, packetsToSend = 100;
    Node[] nodes = new Node[numNodes];
    Random rand = new Random();

    // Initialize nodes with a fixed queue size
    for (int i = 0; i < numNodes; i++)
      nodes[i] = new Node(queueSize);

    // Simulate sending and receiving packets
    for (int i = 0; i < packetsToSend; i++) {
      // Randomly select a node to send the packet
      nodes[rand.nextInt(numNodes)].sendPacket(i);

      // Simulate random processing (receiving) of packets by each node
      for (Node node : nodes) {
        if (rand.nextBoolean())
          node.receivePacket();
      }
    }

    // Display the number of dropped packets for each node
    for (int i = 0; i < numNodes; i++) {
      System.out.println("Node " + i + " dropped packets: " + nodes[i].packetsDropped);
    }
  }
}
