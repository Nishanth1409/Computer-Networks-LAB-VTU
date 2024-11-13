import java.util.LinkedList;
import java.util.Queue;

class Sender {
    private final int windowSize;
    private int nextSeqNum = 0;
    private final Queue<Integer> window = new LinkedList<>();

    public Sender(int windowSize) {
        this.windowSize = windowSize;
    }

    public void sendPackets(int totalPackets) {
        for (int i = 0; i < totalPackets; i++) {
            if (window.size() < windowSize) {
                window.add(nextSeqNum);
                System.out.println("Sent packet: " + nextSeqNum);
                nextSeqNum++;
            } else {
                System.out.println("Window full, waiting for ACK...");
                break; // Wait for ACK in real scenarios
            }
        }
    }

    public void receiveAck(int ackNum) {
        if (window.contains(ackNum)) {
            window.remove(ackNum);
            System.out.println("Received ACK for packet: " + ackNum);
        }
    }

    public void printWindow() {
        System.out.println("Current window: " + window);
    }

    public static void main(String[] args) {
        Sender sender = new Sender(4);
        sender.sendPackets(10);
        sender.printWindow();
        sender.receiveAck(0);
        sender.sendPackets(10);
        sender.printWindow();
        sender.receiveAck(1);
        sender.sendPackets(10);
        sender.printWindow();
    }
}