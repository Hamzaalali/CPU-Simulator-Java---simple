package Schedulers;

import Parts.Clock;
import Parts.HardDisk;
import Parts.MainMemory;
import task.Task;

public class LongTermScheduler implements Runnable {
    HardDisk hardDisk;
    MainMemory mainMemory;
    Clock clock;
    ShortTermScheduler shortTermScheduler;
    boolean isRunning=true;
    public LongTermScheduler(HardDisk hardDisk, MainMemory mainMemory, Clock clock,ShortTermScheduler shortTermScheduler) {
        this.hardDisk = hardDisk;
        this.mainMemory = mainMemory;
        this.shortTermScheduler=shortTermScheduler;
        this.clock = clock;
    }


    public void fetchTasks(){
        for(Task task: hardDisk.getTasks()){
            if(task.getCreationTime()==clock.getCycle()){
                mainMemory.getReadyQueue().add(task);
            }
        }
    }

    @Override
    public synchronized void run() {
        while(isRunning){
            try {
                wait();
                fetchTasks();
                synchronized (shortTermScheduler){
                    shortTermScheduler.notify();
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void stop(){
        isRunning=false;
    }
}
