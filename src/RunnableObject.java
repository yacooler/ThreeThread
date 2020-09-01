public class RunnableObject implements  Runnable {
    private Character output;
    private Character next;

    static volatile Character currentChar = 'A';

    public RunnableObject(Character output, Character next) {
        this.output = output;
        this.next = next;
    }


    @Override
    public void run() {

        synchronized (RunnableObject.class) {
            try {
                for (int i = 0; i < 5; i++) {
                    //Проверяем в цикле, т.к. разбудить может кто угодно, возможно придется заснуть снова
                    while (!currentChar.equals(output)){
                        RunnableObject.class.wait();
                    }

                    //Печатаем текущий символ
                    System.out.println(output);
                    //Устанавливаем следующий
                    currentChar = next;
                    //Будим спящих, пусть проверят, не должны ли они обработать новый символ
                    RunnableObject.class.notifyAll();
                }

            } catch (InterruptedException e) {
                throw new RuntimeException("Thread " + output, e);
            }
        }

    }

}

