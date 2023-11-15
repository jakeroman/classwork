public class Multithreading {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("ONE: "+i);
                }
            }
        };

        Thread thread2 = new Thread() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("TWO: "+i);
                }
            }
        };

        // start threads
        thread1.start();
        thread2.start();

        // wait for thread end
        thread1.join();
        thread2.join();

        // continue
        System.out.println("EXECUTION CONTINUE");
    }
}
