import java.util.Arrays;

public class homemadeTester {
	public static void main(String[] args) {
		BinomialHeap myheap = new BinomialHeap();
		for(int i=1;i<10;i++) {
			myheap.insert(i);
			myheap.debug(myheap); // TODO DEBUG
		}
		System.out.println("Binnary" + Arrays.toString(myheap.binaryRep()));
		for(int i=0;i<4;i++) {
			myheap.deleteMin();
			myheap.debug(myheap); // TODO DEBUG
		}
		System.out.println("Binnary" + Arrays.toString(myheap.binaryRep()));
	}
}