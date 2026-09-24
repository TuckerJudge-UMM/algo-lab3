// note for me: go for 3 rn at 1 passes and go for total storage with bins or go for loop( go for this)
class Approximation1 {
    public Approximation1(){}

    public int findClosestValue(int localSum, int target, int idxOfArray, int[] sourceArr){
        int l = 0;
        int r = idxOfArray;
        int midpoint = 0;
        while(l <= r){
            midpoint = l + (r-l) / 2;
            int midVal = sourceArr[midpoint];
            if (midVal+localSum < target){ l = midpoint; }
            else if (midVal+localSum > target){ r = midpoint; }
            else { return midpoint; }
        }
        return midpoint;
    }




    public int[][] putIntoBins(int[] arr, int target){
        // for all items in arr I expect n > 0
        // worst case 1-1-1-1-1-1-1 to target sum adding one for a sum that i keep in arr
        int[][] bins = new int[arr.length][target+1];
        int j = 0;
        int k = 0;
        int sumOfSubArrIdx = target+1; 
        for(int i = arr.length-1; i>=0; --i){
            if(bins[j][k] != 0){
                int sumPlusNewItem = arr[i] + bins[j][sumOfSubArrIdx];
                if(sumPlusNewItem < target){
                    bins[j][k] = arr[i];
                } else if (sumPlusNewItem == target){
                    bins[j][k] = arr[i];
                    j++;
                    k++;
                } else if (sumPlusNewItem > target){
                    if (i == 0){ bins[i+1][0] = arr[i]; return bins; }
                    int closestVal = findClosestValue(bins[j][sumOfSubArrIdx], target, i, arr);
                    bins[j][k] = arr[closestVal];
                    arr[closestVal] = arr[i];
                    // destroying the array here...
                    // pretty sure i can fix it
                }
            } else {
                bins[j][k] = arr[i];
                bins[j][sumOfSubArrIdx] = arr[i];
            }

        }
        return bins;


    }
       // Removed the swap-with-last-item logic because it compared mismatched values (leftover space vs. total item size) and always read from an empty array slot, so instead of improving the packing it was silently corrupting data (zeroing out items)
    public static void main(String [] args){
        Approximation1 approx = new Approximation1();
        int[] data = {12, 4, 8, 15, 9, 3, 1, 10};
        // main never called MergeSort so added this so it does
        MergeSort ms = new MergeSort();
        ms.sort(data, 0, data.length -1);

        int[][] test = approx.putIntoBins(data, 20);
        int totalUnused = 0;
        for(int i = 0; i<test.length;i++){
            int used = 0;
            for(int j = 0; j<test[i].length;j++) { // changed test.length to test[i].length
                if (test[i][j] == 0){continue;}
                System.out.println("" +i+": "+ test[i][j]);
                used += test[i][j];  // for unused space
            }
            int unused = 20 - used; // all this is for unused space which is what he wanted in our lab
            totalUnused += unused;
            System.out.println("bin " + i+ " unused space: " + unused);
        }
        System.out.println("total unused space: " + totalUnused);
    }
}
/*
 * the plan is to start from the biggest
 * then do a find on the set
 * can even do logic to prevent extra space so i actually compute to find the bins
 *
 */
