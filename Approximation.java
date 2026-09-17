
class Approximation {
    public Approximation(){}
    // First pass
    public int findNewSumOfCurrentBin(int[] arr, int val, int target){
        // sum will just be kept at -1 index eventually
        int sum = 0;
        for (int i = 0;i<arr.length;i++){
            sum += arr[i];
        }
        return target - (sum + val);
    }
    public int[][] firstPass(int [] arr, int target){
        // probs need a different method of collection
        int[][] bins = new int[arr.length][arr.length];
        int curr = 0;
        int currI = 0;
        for(int i = 0; i<arr.length;i++){
            if(currI != 0) {
                int sum = findNewSumOfCurrentBin(bins[curr], arr[i], target);
                System.out.print("sum: " + sum);
                if(sum <= 0){
                    if (sum != 0 && i < arr.length - 1){
                        // or just one other item in the array
                        int possSum = sum + bins[curr][currI-1];
                        System.out.println("poss sum: " + possSum);
                        if ((possSum) < (sum + arr[i])){
                            System.out.println("target - poss sum: " + (possSum) + "target - sum + arr[i]: " + (sum + arr[i]));
                            int swap = bins[curr][currI-1];
                            bins[curr][currI-1] = arr[i];
                            arr[i] = swap;
                        }
                    }
                    curr++;
                    currI = 0;
                }
            }
            bins[curr][currI] = arr[i];
            currI++;
        }
        return bins;

    }
    public static void main(String [] args){
        Approximation approx = new Approximation();
        int[] data = {12, 4, 8, 15, 9, 3, 1, 10};
        int[][] test = approx.firstPass(data, 20);
        for(int i = 0; i<test.length;i++){
            for(int j = 0; j<test.length;j++){
                if (test[i][j] == 0){continue;}
                System.out.println("\n " +i+": "+ test[i][j]);
            }
        }
    }
}
