import Simulator.Simulator;

public class Main {

    public static void main(String[] args) {
        String tasksFilePath="C:\\Users\\hamza\\Desktop\\atypon\\Processor-Execution-Simulator\\src\\tasksFile.txt";
        try{
            Simulator simulator = new Simulator(4,12,tasksFilePath);
            simulator.startSimulation();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
