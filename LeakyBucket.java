import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class LeakyBucket {
    private final int capacity, leakRate;
    private int currentWater;
    private Timer timer;

    public LeakyBucket(int capacity, int leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;
        this.currentWater = 0;
        startLeaking();
    }

    private void startLeaking() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (currentWater > 0) {
                    currentWater = Math.max(0, currentWater - leakRate);
                    System.out.println("Leaked " + leakRate + ". Current water: " + currentWater);
                }
            }
        }, 1000, 1000);
    }

    public void addPacket(int packetSize) {
        if (currentWater + packetSize <= capacity) {
            currentWater += packetSize;
            System.out.println("Added " + packetSize + ". Current water: " + currentWater);
        } else {
            System.out.println("Bucket overflow! Packet of size " + packetSize + " dropped.");
        }
    }

    public void stopLeaking() {
        if (timer != null) {
            timer.cancel();
            System.out.println("Stopped leaking.");
        }
    }

    public static void main(String[] args) {
        LeakyBucket bucket = new LeakyBucket(10, 2);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter packet sizes (0 to stop):");

        while (true) {
            System.out.print("Packet size: ");
            if (scanner.hasNextInt()) {
                int packetSize = scanner.nextInt();
                if (packetSize == 0) break;
                bucket.addPacket(packetSize);
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear invalid input
            }
        }

        bucket.stopLeaking(); // Stop the leaking process
        scanner.close();
    }
}