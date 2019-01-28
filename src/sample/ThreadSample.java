package sample;

class SubThreadExtendsThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(10);
                System.out.println("Called SubThreadExtendsThread.run " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SubThreadImplementsRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(10);
                System.out.println("Called SubThreadImplementsRunnable.run " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadSample {
    public static void extendsThreadSample() {

        // Extends Threadのパターン
        System.out.println("Extends Thread のパターン");
        SubThreadExtendsThread threadExt = new SubThreadExtendsThread();
        threadExt.start();
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(10);
                System.out.println("Called ThreadSamole.main " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Implements Runnableのパターン
        System.out.println("Implements Runnnable のパターン");
        SubThreadImplementsRunnable threadImpl = new SubThreadImplementsRunnable();
        Thread threa = new Thread(threadImpl);
        threa.start();
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(100);
                System.out.println("Called ThreadSamole.main " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
