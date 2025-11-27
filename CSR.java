import java.util.Scanner;

    public class CSR {

    static int[] data; // Original data + CRC
    static int[] generator; // Generator polynomial
    static int[] temp; // Temp array for division
    static int n, m; // n = number of data bits, m = generator bits

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter number of data bits: ");
        n = s.nextInt();

        System.out.print("Enter number of generator bits: ");
        m = s.nextInt();

        data = new int[n + m - 1]; // Data with space for CRC
        generator = new int[m]; // Generator polynomial

        // Input data bits
        System.out.println("Enter data bits:");
        for (int i = 0; i < n; i++) {
            data[i] = s.nextInt();
        }

        // Input generator bits
        System.out.println("Enter generator bits:");
        for (int i = 0; i < m; i++) {
            generator[i] = s.nextInt();
        }

        // Copy data for CRC calculation
        temp = data.clone();

        // Perform XOR division to compute CRC
        divide();
        int[] crc = new int[m - 1];
        for (int i = 0; i < m - 1; i++) {
            crc[i] = temp[n + i];
        }

        // Append CRC to original data
        for (int i = n; i < n + m - 1; i++) {
            data[i] = temp[i]; // Append CRC bits
        }

        // Display original data
        System.out.print("\nOriginal data bits: ");
        for (int i = 0; i < n; i++) {
            System.out.print(data[i] + " ");
        }

        // Display CRC bits
        System.out.print("\nCalculated CRC bits: ");
        for (int bit : crc) {
            System.out.print(bit + " ");
        }

        // Display transmitted codeword
        System.out.print("\nTransmitted codeword (data + CRC): ");
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }

        // --- Receiver Side ---

        // Get received data (with possible errors)
        int[] received = new int[n + m - 1];
        System.out.println("\n\nEnter received data bits (with CRC):");
        for (int i = 0; i < received.length; i++) {
            received[i] = s.nextInt();
        }

        // Perform XOR division again on received data
        temp = received.clone();
        divide();

        // Check syndrome (remainder)
        boolean error = false;
        System.out.print("Syndrome bits: ");
        for (int i = n; i < n + m - 1; i++) {
            System.out.print(temp[i] + " ");
            if (temp[i] != 0) {
                error = true;
            }
        }

        // Display result
        if (error) {
            System.out.println("\n\nResult: Error detected in received data.");
        } else {
            System.out.println("\n\nResult: No error detected. Data is valid.");
        }

        s.close();
    }

    // Perform XOR-based division
    static void divide() {
        for (int i = 0; i < n; i++) {
            if (temp[i] == 1) {
                for (int j = 0; j < m; j++) {
                    temp[i + j] ^= generator[j];
                }
            }
        }
    }
}
