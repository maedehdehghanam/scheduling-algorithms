import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class client {
	static final int PORT = 8080;
	public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",PORT);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        int next;
        int capacity = in.readInt();
        FIFO fifo = new FIFO(capacity);
        LRU lru = new LRU(capacity);
        secondChance sch = new secondChance(capacity);
        new Thread(fifo).start();
        new Thread(lru).start();
        new Thread(sch).start();
        next = 5;
        int i = 1;
        do{
            next = in.readInt();  
        	if (next!=0){
		        fifo.requests.add(next);
		        lru.requests.add(next);
		        sch.requests.add(next);
		    }
		    try {
              Thread.sleep(2000);
            } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
            }
            System.out.println(String.format("------CUSTOMERS IN FIFO----- iteration = %d",i));
            for ( Customer c :fifo.tables ) {
                System.out.println((String.format("%d on table: %d",c.name,c.table)));
            }
            System.out.println(String.format("------CUSTOMERS IN LRU-----iteration = %d",i));
            for ( Customer c :lru.tables ) {
                System.out.println((String.format("%d on table: %d",c.name,c.table)));
            }
            System.out.println(String.format("------CUSTOMERS IN SECOND CHANCE-----iteration = %d",i));
            for ( Customer c :sch.tables ) {
                System.out.println((String.format("%d on table: %d",c.name,c.table)));
            }
            i++;
        }while(next!=0);
        System.out.println("FIFO:");
        System.out.println(fifo.pageFaults);
        System.out.println("LRU:");
        System.out.println(lru.pageFaults);
        System.out.println("Second Chance:");
        System.out.println(sch.pageFaults);
        System.exit(0);
    }
}