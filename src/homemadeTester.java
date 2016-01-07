import java.util.Arrays;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class homemadeTester {
	public static void main(String[] args) {
		int[] arr = new int[20000];
		BinomialHeap myheap = new BinomialHeap();
		for(int i=2;i<20000;i++) {
			myheap.insert(i);
			myheap.debug(myheap); // TODO DEBUG
		}
		
		for(int i=2;i<20000;i++) {
			myheap.deleteMin();
		
			if (myheap.findMin() == 1){
				System.err.println(i);
			}
		}
		System.out.println("done!");
	}
}

