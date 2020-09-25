package basics;

import java.net.SocketOption;

class MyDemonThread extends Thread  {


    @Override
    public void run() {

        if (Thread.currentThread().isDaemon()) {
            System.out.println("This is demon thread");
        }else {
            System.out.println("This is non demon Thread ! ");
        }

    }

}


public class DemonThreadInJava {
    public static void main(String[] args) {
        // creating two threads
        MyDemonThread threadOne = new MyDemonThread();
        MyDemonThread threadTwo = new MyDemonThread();

        threadOne.setDaemon(true); // setting thread to be demon thread

        // stating or launching  the thread..
        threadOne.start();
        threadTwo.start();

        System.out.println("Thread are awesome too..");
    }
}
