import javautil.LinkedList;
import javautil.Queue;
import javautil.Random;

class Node {
  Queue<Integer> queue = new LinkedList<>();
  int queueSize, packetsDropped = 0;
  Node(int size) {
    this.queueSize = size;
  }
  void sendPacket(int packetId) {
    if (queue.size() < queueSize) {
      queue.add(packetId);
    } else {
      packetsDropped++;
    } 
  } 
  void receivePacket() {
    if (!queue.isEmpty())queue.poll();
    }
    int getDroppedPackets() {
      return packetsDropped;}
  }
  public class EthernetLAN{
    public static void main(String[] args) {
      int numNodes = 3, queueSize = 2, packetsToSend = 100;
      Node[] nodes = new Node[numNodes];
      Random rand = new Random();
      for (int i = 0; i < numNodes; i++)nodes[i] = new Node(queueSize);
        for (int i = 0; i < packetsToSend; i++) {
          int sender = rand.nextInt(numNodes);
          nodes[sender].sendPacket(i);
        }
        for (Node node : nodes) {
          for (int i = 0; i < 10; i++)node.receivePacket();
          }
          System.out.println("Dropped packets per node");
          for (int i = 0; i < numNodes; i++) {
            System.out.println("Node " + i + ":"+ nodes[i].getDroppedPackets());
          }
          System.out.println("\nCongestion window:");
          for (int i = 0; i < numNodes; i++) {
            System.out.println("Node " + i + "congestion window:"+(queueSize-nodes[i].getDroppedPackets()));
          }
        }
      }
