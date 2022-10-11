package Parts;

import Simulator.Task;

public class Processor{

    static int processorsNumber=0;
    private boolean isRunning=true;
    private boolean isBusy=false;
    private int processorId;
    private RunnableProcessor runnableProcessor;
    private int assignedTaskExecutionTime;
    private Task assignedTask;
    public Processor(){
        processorId=processorsNumber+1;
        processorsNumber++;
    }
    public void stop(){
        isRunning=false;
    }
    public boolean isBusy(){
        return isBusy;
    }

    public int getProcessorId() {
        return processorId;
    }

    public synchronized void start(){
        runnableProcessor=new RunnableProcessor();
        new Thread(runnableProcessor).start();
    }
    public void notifyProcessor(){
        if(isBusy){
            assignedTaskExecutionTime--;
            if(assignedTaskExecutionTime==0){
                System.out.println("Processor "+processorId+" Finished Executing Task "+assignedTask.getTaskId());
                isBusy=false;
            }
        }
        synchronized (runnableProcessor){
            runnableProcessor.notify();
        }
    }
    public synchronized void setAssignedTask(Task assignedTask) {
        isBusy=true;
        this.assignedTask = assignedTask;
        assignedTaskExecutionTime=assignedTask.getExecutionTime();
        System.out.println("Assigned Task "+assignedTask.getTaskId()+" To Processor "+processorId);

    }

    private class RunnableProcessor implements Runnable{
        @Override
        public synchronized void run() {
            while (isRunning){
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }
}
