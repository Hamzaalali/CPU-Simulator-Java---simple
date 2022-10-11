package Parts;

import Simulator.Task;

import java.util.PriorityQueue;
import java.util.Queue;

public class MainMemory {
    Queue<Task> readyQueue= new PriorityQueue<>();

    public Queue<Task> getReadyQueue() {
        return readyQueue;
    }

    public void setReadyQueue(Queue<Task> readyQueue) {
        this.readyQueue = readyQueue;
    }
}
