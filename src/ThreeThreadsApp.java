public class ThreeThreadsApp {

    public static void main(String[] args) {

        //Хотел синхронизироваться по volatile Character, но у него нет сеттера для символа и он пересоздается при присваивании
        Object sync = new Object();

        RunnableObject runA = new RunnableObject('A', 'B', sync);
        RunnableObject runB = new RunnableObject('B', 'C', sync);
        RunnableObject runC = new RunnableObject('C', 'A', sync);


        new Thread(runA).start();
        new Thread(runB).start();
        new Thread(runC).start();


    }

}
