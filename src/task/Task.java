package task;

public class Task implements Comparable<Task> {
    int creationTime;
    int executionTime;
    static int tasksNumber=0;
    private int taskId;

    public int getTaskId() {
        return taskId;
    }

    public Task( ){
        taskId=tasksNumber+1;
        tasksNumber++;
    }
    public int getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(int creationTime) {
        this.creationTime = creationTime;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    int priority;

    @Override
    public int compareTo(Task o) {
        if(this.priority>o.priority){
            return -1;
        }
        if(this.priority<o.priority){
            return 1;
        }
        if(this.executionTime>o.executionTime){
            return -1;
        }else{
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", creationTime=" + creationTime +
                ", executionTime=" + executionTime +
                ", priority=" + priority +
                '}';
    }
}
