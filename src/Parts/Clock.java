package Parts;
import java.util.ArrayList;
import java.util.List;


public class Clock implements Runnable {
    private int cycle;
    boolean isRunning=true;
    List<Object> objectsToNotify=new ArrayList<>();
    @Override
    public void run() {
        cycle=0;
        while(isRunning){
            try {
                Thread.sleep(1000);
                cycle++;
                for(Object object:objectsToNotify){
                    synchronized (object){
                        object.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void addObjectToNotify(Object object){
        objectsToNotify.add(object);
    }

    public  synchronized int getCycle() {
        return cycle;
    }
    public void stop(){
        isRunning=false;
    }
}
