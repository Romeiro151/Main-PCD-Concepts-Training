package CountdownLatch.TextSearching;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class MainLatch {
	static final int NUM_DOCUMENTS_TO_BE_SEARCHED=1000000;
	static final int STRING_LENGTH=1024;
	static final String STRING_TO_BE_FOUND="huik";
	
	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
		SearcherThread[] threads=new SearcherThread[NUM_DOCUMENTS_TO_BE_SEARCHED];
		RandomString rs=new RandomString(STRING_LENGTH);

		CountDownLatch latch= new CountDownLatch(NUM_DOCUMENTS_TO_BE_SEARCHED);
				
		for(int i=0; i!=NUM_DOCUMENTS_TO_BE_SEARCHED;i++)
			threads[i]=new SearcherThread(rs.nextString(), 
					STRING_TO_BE_FOUND, latch);
		final long initTime=System.currentTimeMillis();
		System.out.println("Início:"+initTime);
		for(int i=0; i!=NUM_DOCUMENTS_TO_BE_SEARCHED;i++)
			threads[i].start();
		
		// Este código também podia estar no BarrierAction.
		// Fazemos assim para ser mais fácil comparar o desempenho, e para ser mais parecido 
		// com o funcionamento do CountDownLatch
		latch.await();
		int count=0;
		for(SearcherThread t:threads)
			if(t.getResult()!=-1){
				System.out.println("Found at "+t.getResult());//+" in "+t.getMyText());
				count++;
			}
		System.out.println("Search DONE. Found:"+count+" Time:"+
			(System.currentTimeMillis()-initTime));
	}
}
