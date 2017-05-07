package org.koushik.javabrains.messenger.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Helper {
	public static String formatDate(long date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
		Date dt = new Date(date);
		
		return sdf.format(dt);
	}
}

class Message {
	String msg;
	
	Message(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}

class Waiter implements Runnable {
	Message msg = null;
	String name = null;
	
	Waiter (Message msg, String name) {
		this.msg = msg;
		this.name = name;
	}
	
	@Override
	public void run() {
		System.out.println("Running thread " + name 
				+ ": " + Helper.formatDate(System.currentTimeMillis()));
		synchronized(msg) {
			try {
					msg.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Done thread " + name + ": " + Helper.formatDate(System.currentTimeMillis()));
	}
}

class Notifier implements Runnable {
	Message msg = null;
	
	Notifier(Message msg) {
		this.msg = msg;
	}
	
	@Override
	public void run() {
		System.out.println("Running thread " + Thread.currentThread().getName() + ": " + Helper.formatDate(System.currentTimeMillis()));
		try {
			Thread.sleep(5000);
			synchronized(msg) {
				msg.setMsg(Thread.currentThread().getName() + "I am done sleeeping");
				msg.notifyAll();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done thread 2: " + Helper.formatDate(System.currentTimeMillis()));
	}
	
}

public class ThreadComm {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int threadPool = Integer.valueOf(sc.nextLine());
		
		ExecutorService es = Executors.newFixedThreadPool(threadPool);

		Message msg = new Message("Process It!");

		Waiter tr1 = new Waiter(msg, "tr1");
		es.execute(tr1);
		Waiter tr2 = new Waiter(msg, "tr2");
		es.execute(tr2);
		Waiter tr3 = new Waiter(msg, "tr3");
		es.execute(tr3);
		Waiter tr4 = new Waiter(msg, "tr4");
		es.execute(tr4);
		Waiter tr5 = new Waiter(msg, "tr5");
		es.execute(tr5);
		Waiter tr6 = new Waiter(msg, "tr6");
		es.execute(tr6);
		Waiter tr7 = new Waiter(msg, "tr7");
		es.execute(tr7);
		Waiter tr8 = new Waiter(msg, "tr8");
		es.execute(tr8);
		Waiter tr9 = new Waiter(msg, "tr9");
		es.execute(tr9);
		Notifier tr10 = new Notifier(msg);
		es.execute(tr10);

	}

}
