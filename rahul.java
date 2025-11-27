import java.util.Scanner;  
import java.util.Random;    
public class rahul {  
    static final int MAX_FRAMES = 100;  
    static final int TIMEOUT = 3;
        public static void main(String[] args) {  
        Scanner sc = new Scanner(System.in);  
        Random rand = new Random();  
        System.out.print("Enter total number of frames to send: ");  
        int totalFrames = sc.nextInt();  
        System.out.print("Enter window size: ");  
        int windowSize = sc.nextInt();  
        int sent = 0;
        int ack = 0;   
        while (ack < totalFrames) {   
            System.out.println("\nSender Window:");  
            for (int i = 0; i < windowSize && sent < totalFrames; i++) {  
                System.out.println("Sending frame " + sent);  
                sent++;  
            }    
            for (int i = 0; i < windowSize && ack < totalFrames; i++) {  
                boolean ackReceived = rand.nextInt(10) < 8;  
                if (ackReceived) {  
                    System.out.println("ACK received for frame " + ack);  
                    ack++;  
                } else {  
                    System.out.println("Timeout/Error: Frame " + ack + " not acknowledged.");  
                    sent = ack;  
                    break;  
                }  
            }  
            try {  
                Thread.sleep(1000);  
            } catch (InterruptedException e) {  
                System.out.println("Interrupted.");  
            }  
        }  
        System.out.println("\nAll frames sent and acknowledged successfully.");  
        sc.close();  
    }  
}   