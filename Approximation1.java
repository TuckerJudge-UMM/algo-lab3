// note for me: go for 3 rn at 1 passes and go for total storage with bins or go for loop( go for this)
class Approximation1 {
    public Approximation1(){}

    public int findClosestValue(int localSum, int target, int idxOfArray, int[] sourceArr){
        // this is n log n
        // i have to fix the array because it's sorted
        int l = 0;
        int r = idxOfArray;
        int midpoint = 0;
        // above domain and intentionally out of bounds
        int closestPossible = target + 1;
        int closestIdx = sourceArr.length;
        while(l <= r){
            midpoint = l + (r-l) / 2;
            int midVal = sourceArr[midpoint];
            int sum = midVal + localSum;
            int abs_sum = (sum < 0) ? -sum : sum;
            if ((sum <= target) && ((target - abs_sum) < closestPossible) ){
                closestPossible = midVal;
                System.out.println("closest what the fuck" + closestPossible);
                closestIdx = midpoint;
            }
            if (midVal+localSum < target){ l = midpoint + 1; }
            else if (midVal+localSum > target){ r = midpoint - 1; }
            else { System.out.println("huh" + midVal); return midpoint; }
        }
        // handle edge case in caller
        //
        System.out.println("testrealer" + closestIdx);
        System.out.println("test" + closestPossible);
        System.out.println("test" + localSum);
        return closestIdx;
    }




    public int[][] putIntoBins(int[] arr, int target){
        // for all items in arr I expect n > 0
        // worst case 1-1-1-1-1-1-1 to target sum adding one for a sum that i keep in arr
        int[][] bins = new int[arr.length][target+1];
        int j = 0;
        int k = 0;
        int sumOfSubArrIdx = target; 
        for(int i = arr.length-1; i>=0; i--){
            System.out.println("" + arr[i]);
            if(bins[j][k] != 0){
                int sumPlusNewItem = arr[i] + bins[j][sumOfSubArrIdx];
                System.out.println("looking for a 21" + sumPlusNewItem);
                System.out.println("" + sumPlusNewItem);

                if(sumPlusNewItem < target){
                    bins[j][k] = arr[i];
                    bins[j][sumOfSubArrIdx] = sumPlusNewItem;
                    k++;
                } else if (sumPlusNewItem == target){
                    bins[j][k] = arr[i];
                    j++;
                    k++;
                } else if (sumPlusNewItem > target){
                    System.out.println("i know u" + arr[i]);
                    if (i == 0){ bins[i+1][0] = arr[i]; return bins; }
                    int closestIdx = findClosestValue(bins[j][sumOfSubArrIdx], target, i, arr);
                    if (closestIdx == arr.length) {
                        // honestly dont know?
                        return bins;
                    }
                    System.out.println(arr[closestIdx] + "testing" + i);
                    bins[j][k] = arr[closestIdx];
                    arr[closestIdx] = arr[i];
                    // i chose to prevent further searches
                    j++;
                    k++;
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
       // Removed the swap-with-last-item logic because it compared mismatched values (leftover space vs. total item size) and always read from an empty array slot, 
    // so instead of improving the packing it was silently corrupting data (zeroing out items)
    public static void main(String [] args){
        Approximation1 approx = new Approximation1();
        int[] data = {12, 4, 8, 15, 9, 3, 1, 10};
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
            if (unused == 20){
                continue;
            }
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



// for data algo doesn't do subs so worst case is anything contrived target 20 19 
