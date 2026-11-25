package Lock.Feast;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Este exercício é a solução para a Semana 4!
public class TableLock {
	private Queue<WildBoar> boars = new LinkedList<>();
	public static final int CAPACIDADE=10;
	private final Lock lock = new ReentrantLock();
	private final Condition notFull = lock.newCondition();
	private final Condition notEmpty = lock.newCondition();

	public void put(WildBoar boar) throws InterruptedException {
		lock.lock();
		try {
			while (boars.size() == CAPACIDADE) {
				notFull.await();
			}
			boars.add(boar);
			notEmpty.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public WildBoar take () throws InterruptedException {
		lock.lock();
		try{
			while(boars.isEmpty()){
				notEmpty.await();
			}
			WildBoar boar = boars.remove();
			notFull.signalAll();
			return boar;
		}finally {
            lock.unlock();
        }
	}

	public static void main(String[] args) {
		Vector<Thread> threads=new Vector<>();
		TableLock m=new TableLock();
		for(int i=0; i!=10;i++) {
			threads.add(new Gauls(m));
			threads.lastElement().start();
		}
		for(int i=0; i!=5;i++) {
			threads.add(new Cook(i,m));
			threads.lastElement().start();
		}
	}
}
