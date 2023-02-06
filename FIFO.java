import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.*;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Random;
import java.util.*;
public class FIFO implements Runnable {
    public int new_data;
    public Queue<Integer> queue = new LinkedList<Integer>();
    public Queue<Integer> requests = new ConcurrentLinkedQueue<Integer>();
    public LinkedList<Customer> tables = new LinkedList<Customer>();
    public int capacity;
    public int pageFaults=0;
    public int counter = 1;
    public FIFO(int capacity) {
        this.capacity = capacity;
    }
    @Override
    public void run() {
    	Iterator<Integer> iterator = requests.iterator();
    	while(true){
    		iterator = requests.iterator();
			while(iterator.hasNext()) {
				int data = iterator.next();
				if(queue.contains(data))
					requests.poll();
				else if(capacity>0){
    				pageFaults++;
    				queue.add(data);
    				tables.add(new Customer(data, counter));
    				counter++;
    				capacity--;
    				requests.poll();
        		} else {
        			pageFaults++;
        			int page = queue.poll();
        			int freeTable = 0;
        			int removeIndex = 0;
        			for (int i = 0; i < tables.size(); i++) {
            			if(tables.get(i).name == page){
            				freeTable = tables.get(i).table;
            				removeIndex = i;
            				break;
            			}
        			}
        			tables.remove(removeIndex);
        			tables.add(new Customer(data, freeTable));
        			requests.poll();
        			queue.add(data);
        		}
			}
		}

    }
}