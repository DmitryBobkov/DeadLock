package by.home.deadlock.main;

/**
 * @author Dmitry Bobkov
 *
 */

public class Main {

	public static void main(String[] args) {
		ResursA resursA = new ResursA();
		ResursB resursB = new ResursB();
		resursA.resursB = resursB;
		resursB.resursA = resursA;
		MyThread1 t1 = new MyThread1();
		MyThread2 t2 = new MyThread2();
		t1.resursA = resursA;
		t2.resursB = resursB;
		t1.start();
		t2.start();
	}
}

class MyThread1 extends Thread {
	ResursA resursA;

	@Override
	public void run() {
		System.out.println(resursA.getI());
	}
}

class MyThread2 extends Thread {
	ResursB resursB;

	@Override
	public void run() {
		System.out.println(resursB.getI());
	}
}

class ResursA {
	ResursB resursB;

	public synchronized int getI() {
		return resursB.returnI();
	}

	public synchronized int returnI() {
		return 1;
	}
}

class ResursB {
	ResursA resursA;

	public synchronized int getI() {
		return resursA.returnI();
	}

	public synchronized int returnI() {
		return 2;
	}

}
