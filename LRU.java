import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.*;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Random;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class LRU implements Runnable {
    public int new_data;
    public HashMap<Integer, Long> map = new HashMap<Integer, Long>(); 
    public Queue<Integer> requests = new ConcurrentLinkedQueue<Integer>();
    public LinkedList<Customer> tables = new LinkedList<Customer>();
    public int capacity;
    public int pageFaults=0;
    public int counter=1;

    public LRU(int capacity) {
        this.capacity = capacity;
    }
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
    	Iterator<Integer> iterator = requests.iterator();
    	while(true){
    		iterator = requests.iterator();
			while(iterator.hasNext()) {
                long endTime = System.currentTimeMillis();
				int data = iterator.next();
                if(map.containsKey(data)){
                    map.put(data,endTime-startTime);
                    requests.poll();
                }
				else if(capacity>0){
    				pageFaults++;
    				map.put(data,endTime-startTime);
                    tables.add(new Customer(data, counter));
                    counter++;
    				capacity--;
    				requests.poll();
        		} else {
                    Long min=999999999999999999L;
                    int min_data = 0;
                    for (Map.Entry<Integer,Long> entry : map.entrySet()){
                        if(entry.getValue()<=min){
                            min_data = entry.getKey();
                            min = entry.getValue();
                        }
                    }
                    int page = min_data;
                    int freeTable = 0;
                    int removeIndex = 0;
                    for (int i = 0; i < tables.size(); i++) {
                        if(tables.get(i).name == page){
                            freeTable = tables.get(i).table;
                            removeIndex = i;
                            break;
                        }
                    }
                    map.remove(min_data);
        			pageFaults++;
                    map.put(data,endTime-startTime);
                    tables.remove(removeIndex);
                    tables.add(new Customer(data, freeTable));
        			requests.poll();
        		}
            
			}

		}

    }
}