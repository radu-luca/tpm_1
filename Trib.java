import java.util.LinkedList;

public class Trib {

    LinkedList<Integer> list ;
    int capacity;
    volatile boolean flagConsumer = false;

    public Trib(int capacity) {
        this.capacity = capacity;
        list = new LinkedList<>();
    }

    public void produce() throws InterruptedException {
        boolean flag = true;
        while(flag)
        {
            synchronized (this)
            {
                while(list.size() > 0)
                    wait();
                for(int i=0; i < capacity; i++)
                        list.add(i);
                System.out.println("produced " + capacity +" items");
                notify();
                //Thread.sleep(1000);
            }
        }

    }
    public void consume() throws InterruptedException {
            synchronized (this)
            {
                if (list.size() == 0)
                    notify();
                while (list.size() == 0)
                    wait();
                int val = list.removeFirst();
                System.out.println("consumed " + val);
                //Thread.sleep(1000);
            }
        }
}
