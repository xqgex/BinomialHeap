import java.util.Random;

public class homemadeTester {
	public static final int SIZE = 112;
	public static void main(String[] args) {
		int[][] lst = new int[][] {{198, 712, 722, 542, 182, 392, 385, 488, 642, 16, 900, 47, 171, 38, 971, 329, 113, 1082, 3,
									15, 348, 670,204, 65, 601, 1023, 425, 759, 988, 945, 101, 260, 976, 834, 585, 252, 942, 181,
									213, 61, 954, 1100, 243, 849, 559, 360, 634, 338, 579, 376, 556, 337, 316, 203, 969, 595, 651,
									263, 660, 309, 458, 754, 863, 430, 237, 629, 857, 809, 832, 804, 1087, 959, 656, 294, 407,
									386, 717, 586, 972, 937, 1028, 888, 931, 909, 1031, 801, 824, 66, 201, 851, 625, 439, 374,
									881, 620, 1030, 875, 1070, 531, 563, 922, 194, 915, 894, 303, 1115, 506, 1002, 955, 584, 1080, 963},
									{}};
		lst[1] = createValues(SIZE);
		for(int j=0;j<2;j++) {
			BinomialHeap myheap = new BinomialHeap();
			int tmp0 = 0, tmp1 = 0;
			String tmp2 = "";
			for(int i=0;i<SIZE;i++) {
				myheap.insert(lst[j][i]);
				tmp0 = myheap.minTreeRank()+1;
				tmp1 = (int)Math.floor(Math.log(tmp0) / Math.log(2));
				tmp2 = bolToString(myheap.binaryRep());
				if (tmp1 != LSB(tmp2)) {
					System.err.print("log(" + (tmp0) + ")=" + (tmp1) + " ~ " + tmp2);
					System.err.println(" ~ LSB is the " + LSB(tmp2) + " digit");
				}
			}
			System.err.println("Finish loop number " + (j+1) + "\r\n");
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

