package Schedulers;

import Parts.MainMemory;
import Parts.Processor;
import java.util.List;

public class ShortTermScheduler implements Runnable {
    boolean isRunning=true;

    MainMemory mainMemory;
    List<Processor> processorList;

    public ShortTermScheduler(MainMemory mainMemory, List<Processor> processorList) {
        this.mainMemory = mainMemory;
        this.processorList = processorList;
    }


    @Override
    public synchronized void run() {
        while (isRunning){
            try {
                wait();
                for(Processor processor:processorList){
                    synchronized (processor){
                        processor.notifyProcessor();
                    }
                }
                assignTasks();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void assignTasks(){
        for(Processor processor:processorList){
            synchronized (processor){
                if(!processor.isBusy()){
                    System.out.println("Processor "+processor.getProcessorId()+" Is Idle");
                }
                if (!processor.isBusy()&& !mainMemory.getReadyQueue().isEmpty()){
                        processor.setAssignedTask(mainMemory.getReadyQueue().remove());
                }
            }
        }
    }
    public void stop(){
        isRunning=false;
    }
}
