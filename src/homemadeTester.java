import java.util.Arrays;
import java.util.Random;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class homemadeTester {
	public static final int SIZE = 224; //224
	public static void main(String[] args) {
		int[] arr = new int[20000];
		BinomialHeap myheap = new BinomialHeap();
		int tmp0 = 0, tmp1 = 0;
		String tmp3 = "";
		int[] lst = createValues(SIZE); //224
		for(int i=0;i<SIZE;i++) {
			myheap.insert(lst[i]);
			tmp0 = myheap.minTreeRank()+1;
			tmp1 = (int)Math.floor(Math.log(tmp0) / Math.log(2));
			tmp3 = bolToString(myheap.binaryRep());
			if (LSB(tmp3) != tmp1) {
				System.err.print("log(" + (tmp0) + ")=" + (tmp1) + " ~ " + tmp3);
				System.err.println(" ~ LSB is " + LSB(tmp3) + " instead of " + tmp1);
			}
			myheap.debug(myheap); // TODO DEBUG
		}
		System.out.println("Done");
	}
	public static String bolToString(boolean[] bools) {
		String ret = "";
		for (boolean bool : bools) {
			if(bool) {
				ret += "1";
			} else {
				ret += "0";
			}
		}
		return ret;
	}
	public static int LSB(String input) {
		for (int i=0;i<input.length();i++) {
			if (input.substring(i, i+1).equals("1")) {
				return i;
			}
		}
		return -1;
	}
	public static int[] createValues(int n) {
		int[] values = new int[n];
		int maxValue = n * 10;
		Random randomGenerator = new Random();
		for (int i = 0; i < n; ++i){
			while (true) {
				int j, randInt = randomGenerator.nextInt(maxValue);

				for (j = 0; j < i && randInt != values[j]; ++j);
				if (j < i) {
					continue;
				}
				values[i] = randInt;
				break;
			}
		}
		return values;
	}
}

