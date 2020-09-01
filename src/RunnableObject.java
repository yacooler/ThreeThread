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

                    //Печатаем текущий символ
                    System.out.println(output);
                    //Устанавливаем следующий
                    currentChar = next;
                    //Будим спящих, пусть проверят, не должны ли они обработать новый символ
                    sync.notifyAll();
                }

            } catch (InterruptedException e) {
                throw new RuntimeException("Thread " + output, e);
            }
        }

    }

}

