package project04;

import java.util.PriorityQueue;
import java.util.Scanner;

class Task{
    private int taskId;
    private String taskName;
    private int priority;

    public Task(int taskId, String taskName, int priority){
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
    }
    public int getTaskId(){
        return taskId;
    }
    public String getTaskName(){
        return taskName;
    }
    public int getPriority(){
        return priority;
    }

    @Override
    public String toString(){
        return "Task ID: " + taskId + ", Task Name: " + taskName + " Priority: " + priority;
    }
}

public class prioritytaskscheduler{
    public static void main(String[] args){
       Scanner sc = new Scanner(System.in);

       PriorityQueue<Task> taskQueue = new PriorityQueue<>(
       (t1,t2) -> Integer.compare(t2.getPriority(), t1.getPriority()));

       while (true) {
        System.out.println("\n===================TASK SCHEDULER===================");
        System.out.println("1. Add Task");
        System.out.println("2. Execute Task");
        System.out.println("3. View next Task");
        System.out.println("4. View all Pending Tasks");
        System.out.println("5. Exit");


        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch(choice){
            case 1:
                System.out.print("Enter Task ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.println("Enter Task Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Priority : ");
                int priority = sc.nextInt();
                sc.nextLine();

                taskQueue.offer(new Task(id, name, priority));

                System.out.println("Task Added successfully.");
                
                case 2:
                    if(taskQueue.isEmpty()){
                        System.out.println("No tasks Available.");
                    } else {
                        Task task = taskQueue.poll();

                        System.out.println("\nExecuting Task: " );

                        System.out.println(task);
                    }
                    break;


            case 3:
                if(taskQueue.isEmpty()){
                    System.out.println("No tasks Available.");
                } else {
                    System.out.println("\nNext Task: " );
                    
                    System.out.println(taskQueue.peek());

                }
                break;
            case 4:
                if(taskQueue.isEmpty()){
                    System.out.println("No tasks Available.");
                } else {
                    System.out.println("\nPending Tasks: " );

                    for(Task task : taskQueue){
                        System.out.println(task);
                    }
                }
                break;
            case 5:
                System.out.println("scheduler closed...");
                sc.close();

                System.exit(0);

                default:
                System.out.println("Invalid choice. Please try again.");
        }
       }
    }

}