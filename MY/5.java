// Program for sliding window protocol
import java.util.LinkedList;
import java.util.Queue;

class SlidingWindowProtocol {
    public static void main(String[] args) {
        int windowSize = 4; // Example window size
        int totalPackets = 10; // Total packets to send

        Queue<Integer> window = new LinkedList<>();
        for (int i = 1; i <= totalPackets; i++) {
            if (window.size() < windowSize) {
                window.add(i);
                System.out.println("Packet " + i + " sent.");
            } else {
                window.poll(); // Simulating acknowledgment
                window.add(i);
                System.out.println("Packet " + i + " sent.");
            }
        }
    }
}
