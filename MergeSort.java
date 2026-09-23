class MergeSort {

    public MergeSort(){}

    public void merge(int[] arr, int r, int p){
        for(int i = p + 1; i <= r; i++){    // CHANGED: p -> p+1, and i < r -> i <= r
            int key = arr[i];
            int j = i - 1;
            while(j >= p && arr[j] - key > 0){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }

    public void sort(int[] arr, int i, int j){
        if (i >= j) {              // ADDED: base case — stops the recursion
            return;
        }

        int mid = i + (j - i) / 2; // FIXED: was i, j/2 and i*2, j — didn't split [i,j] correctly

        sort(arr, i, mid);
        sort(arr, mid + 1, j);
        merge(arr, j, i);          
    }
}