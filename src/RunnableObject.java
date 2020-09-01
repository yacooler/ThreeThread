public class RunnableObject implements  Runnable {
    private Character output;
    private Character next;

    static volatile Character currentChar = 'A';
    Object sync;

    public RunnableObject(Character output, Character next, Object sync) {
        this.output = output;
        this.next = next;
        this.sync = sync;
    }


    @Override
    public void run() {
        synchronized (sync) {
            try {
                for (int i = 0; i < 5; i++) {
                    //Проверяем в цикле, т.к. разбудить может кто угодно, возможно придется заснуть снова
                    while (!currentChar.equals(output)){
                        sync.wait();
                    }

                    System.out.println(output);
                    currentChar = next;
                    sync.notifyAll();
                }

            } catch (InterruptedException e) {
                throw new RuntimeException("Thread 1", e);
            }
        }

    }

}

