package Simulator;
import Parts.Clock;
import Parts.HardDisk;
import Parts.MainMemory;
import Parts.Processor;
import Schedulers.LongTermScheduler;
import Schedulers.ShortTermScheduler;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
public class Simulator {
    int simulationTime;
    List<Processor> processorsList=new ArrayList<>();
    MainMemory mainMemory=new MainMemory();
    LongTermScheduler longTermScheduler;
    ShortTermScheduler shortTermScheduler;
    Clock clock=new Clock();
    HardDisk hardDisk;
    public Simulator(int processorsNumber, int simulationTime, String taskFilePath) throws FileNotFoundException {
        for(int i=0;i<processorsNumber;i++){
            processorsList.add(new Processor());
        }
        hardDisk=new HardDisk();
        hardDisk.readTasks(taskFilePath);
        this.simulationTime=simulationTime;
        clock.addObjectToNotify(this);
        shortTermScheduler=new ShortTermScheduler(mainMemory,processorsList);
        longTermScheduler=new LongTermScheduler(hardDisk,mainMemory,clock,shortTermScheduler);
    }
    public synchronized void startSimulation(){
        startThreads();
        while(clock.getCycle()!=simulationTime+1) {
            try {
                wait();
                System.out.println(clock.getCycle());
                synchronized (longTermScheduler){
                    longTermScheduler.notify();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        endThreads();
    }
    private void startThreads(){
        new Thread(clock).start();
        for(Processor processor:processorsList){
           processor.start();
        }
        new Thread(longTermScheduler).start();
        new Thread(shortTermScheduler).start();
    }
    private void endThreads(){
        for(Processor processor:processorsList){
            processor.stop();
        }
        clock.stop();
        longTermScheduler.stop();
        shortTermScheduler.stop();
    }
}
