class MergeSort {

    public MergeSort(){}


    public void merge(int[] arr, int r, int p){
        for(int i = p; i < r; i++){
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

        sort(arr, i, j/2);
        sort(arr, i*2, j);
        merge(arr, i, j);
    }
}
