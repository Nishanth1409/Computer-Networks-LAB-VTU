public class CRC16 {
    private static final int POLYNOMIAL = 0xA001; // CRC-CCITT polynomial

    // Method to calculate the CRC
    public static int calculateCRC(byte[] data) {
        int crc = 0xFFFF; // Initial value
        for (byte b : data) {
            crc ^= b & 0xFF; // XOR byte into least significant byte of crc
            for (int i = 0; i < 8; i++) { // Process each bit
                if ((crc & 0x0001) != 0) {
                    crc = (crc >> 1) ^ POLYNOMIAL;
                } else {
                    crc >>= 1;
                }
            }
        }
        return crc & 0xFFFF; // Return the 16-bit CRC value
    }

    // Method to check if the CRC is valid
    public static boolean checkCRC(byte[] data, int receivedCRC) {
        int calculatedCRC = calculateCRC(data);
        return calculatedCRC == receivedCRC;
    }

    public static void main(String[] args) {
        String input = "Hello, CRC!";
        byte[] data = input.getBytes();

        // Calculate CRC for the data
        int crc = calculateCRC(data);
        System.out.printf("CRC-CCITT (16-bit) for '%s': %04X%n", input, crc);

        // Simulate sending data with CRC
        byte[] receivedData = data; // Simulating received data
        int receivedCRC = crc; // Simulating received CRC

        // Check if the received data is valid
        boolean isValid = checkCRC(receivedData, receivedCRC);
        System.out.println("Data valid: " + isValid);
    }
}