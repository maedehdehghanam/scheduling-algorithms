import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.*;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Random;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class secondChance implements Runnable {
    public int new_data;
    public LinkedList<Page> list = new LinkedList<Page>();
    public Queue<Integer> requests = new ConcurrentLinkedQueue<Integer>();
    public LinkedList<Customer> tables = new LinkedList<Customer>();
    public int capacity;
    public int pageFaults =0 ;
    public int counter = 1;

    public secondChance(int capacity) {
        this.capacity = capacity;
    }
    @Override
    public void run() {
    	Iterator<Integer> iterator = requests.iterator();
    	while(true){
    		iterator = requests.iterator();
			while(iterator.hasNext()) {
				int data = iterator.next();
                boolean in = false;
                for (Page p : list) {
                    if(p.name == data){
                        p.used = true;
                        in = true;
                        requests.poll();
                        break;
                    }
                }
                if(in)
                    break;
				else if(capacity>0){
                    list.add(new Page(data, false));
                    pageFaults++;
                    tables.add(new Customer(data, counter));
                    counter++;
                    capacity--;
                    requests.poll();
                    
        		} else {
                    int pageName = 0;
                    int freeTable = 0;
                    int removeIndexTable = 0;
                    int removeIndexList =0;
                    boolean flag = true; 
                    int i = 0;
                    LinkedList<Page> goToTail = new LinkedList<Page>();
                    while (flag){
                        i = i % (list.size());
                        if(list.get(i).used == true){
                            list.get(i).used = false;
                            goToTail.add(list.get(i));
                        } else {
                            pageName = list.get(i).name;
                            for (int j = 0; j < tables.size(); j++) {
                                if(tables.get(j).name == pageName){
                                    freeTable = tables.get(j).table;
                                    removeIndexTable = j;
                                    break;
                                }
                            }
                            tables.remove(removeIndexTable);
                            tables.add(new Customer(data, freeTable));
                            removeIndexList =i;
                            flag = false;
                            break; 
                        }
                        i++;
                    }
                    //remove from list
                    list.remove(removeIndexList);

                    for (int k=0;k<goToTail.size();k++) {
                        list.remove(goToTail.get(k));
                        list.add(goToTail.get(k));
                    }
                    //add new data
                    list.add(new Page(data, false));
                    pageFaults++;
                    requests.poll();
        		}
			}

		}

    }
}