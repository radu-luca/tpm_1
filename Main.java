import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Trib trib = new Trib(5);
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    trib.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    trib.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        ExecutorService pool = Executors.newFixedThreadPool(10);
        pool.execute(producer);
        for(int i=0; i < 6; i++)
            pool.execute(consumer);

    }
}
