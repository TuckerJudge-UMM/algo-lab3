import java.util.Scanner;

class tst{
    public tst(){}
    // First pass
    // added a 'count' parameter to know how many real items there are
    public int findNewSumOfCurrentBin(int[] arr, int count, int val, int target){
        // sum will just be kept at -1 index eventually
        int sum = 0;
        for (int i = 0; i < count; i++) { // changed the previous arr.length to count instead
            sum += arr[i];
        }
        return target - (sum + val);
    }
    // public loopPass(){}
    public int[][] firstPass(int[] arr, int target){
        // worst case all 1s or something
        int[][] bins = new int[target+1][arr.length];
        int curr = 0;
        int currI = 0;

        for(int i = arr.length-1; i>=0;i--){
            // ADDED: if we've already run out of bins, everything from here is unpacked
            if (curr >= bins.length) {
                System.out.println("UNPACKED: " + arr[i]);
                continue;
            }

            if(currI != 0) { // now passes currI as the count argument
                int sum = findNewSumOfCurrentBin(bins[curr], currI, arr[i], target);
                // System.out.print("sum: " + sum);

                if(sum <= 0){
                    if (sum != 0 && i > 0){
                        // or just one other item in the array
                        int possSum = sum + bins[curr][currI-1];
                        if ((target - possSum) < (target - ( sum + arr[i]) ) ){
                            int swap = bins[curr][currI-1];
                            bins[curr][currI-1] = arr[i];
                            arr[i] = swap;
                        }
                    }
                    curr++;
                    currI = 0;

                    if (curr >= bins.length) { // added this check so that when curr moves past bin index 2 (i.e. all 3 bins are full)
                        System.out.println("UNPACKED: " + arr[i]);
                        continue;
                    }
                }
            }
            bins[curr][currI] = arr[i];
            currI++;
        }
        return bins;

    }  // removed our old swap block "if(sum <=0)"
       // Removed the swap-with-last-item logic because it compared mismatched values (leftover space vs. total item size) and always read from an empty array slot, so instead of improving the packing it was silently corrupting data (zeroing out items)
    public static void main(String [] args){
        Scanner s;
        tst approx = new tst();

        s = new Scanner(System.in);
        System.out.println("first int is target, the rest are numbers of the set\npress any non int key or ctrl d to continue");
        int[] data = null;
        int target = 0;
        // int[] data = {12, 4, 8, 15, 9, 3, 1, 10};
        int z = 0;
        while(s.hasNextInt()){
            if (target == 0){
                target = s.nextInt();
            } else if (data == null){
                data = new int[s.nextInt()];
            } else {
                data[z] = s.nextInt();
                z++;
            }
        }
        // main never called MergeSort so added this so it does
        MergeSort ms = new MergeSort();
        ms.sort(data, 0, data.length -1);

        int[][] test = approx.firstPass(data, 20);
        int totalUnused = 0;
        for(int i = 0; i<test.length;i++){
            int used = 0;
            for(int j = 0; j<test[i].length;j++) { // changed test.length to test[i].length
                if (test[i][j] == 0){continue;}
                System.out.println("" +i+": "+ test[i][j]);
                used += test[i][j];  // for unused space
            }
            int unused = target - used; // all this is for unused space which is what he wanted in our lab
            totalUnused += unused;
            System.out.println("bin " + i+ " unused space: " + unused);
        }
        System.out.println("total unused space: " + totalUnused);
        s.close();
    }
}
/*
 * the plan is to start from the biggest
 * then do a find on the set
 * can even do logic to prevent extra space so i actually compute to find the bins
 *
 */
