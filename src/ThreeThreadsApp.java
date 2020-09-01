public class ThreeThreadsApp {

    public static void main(String[] args) {

        RunnableObject runA = new RunnableObject('A', 'B');
        RunnableObject runB = new RunnableObject('B', 'C');
        RunnableObject runC = new RunnableObject('C', 'A');


        new Thread(runA).start();
        new Thread(runB).start();
        new Thread(runC).start();


    }

}
