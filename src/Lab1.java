import java.util.concurrent.ThreadLocalRandom;

public class Lab1 {
    /**
     * Sorting algorithms
     **/

    // Insertion sort.
    public static void insertionSort(int[] array) {
        if (array != null && array.length > 1) {
            for (int i = 1; i < array.length; i++) {
                insert(array, 0, i);
            }
        }
    }

    private static void insert(int[] array, int beg, int end) {
        if (array[end] < array[end - 1]) {

            int insertValue = array[end];
            int high = end - 1;
            while (high >= beg && array[high] > insertValue) {
                array[high + 1] = array[high];
                high--;
            }
            array[high + 1] = insertValue;

        }

    }

    // Quicksort.

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }


    // Quicksort part of an array
    private static void quickSort(int[] array, int low, int high) {

        if (low < high) {
            //new pivot
            int pivot = partition(array, low, high);


            //pivot is now in correct Position
            quickSort(array, low, pivot - 1);
            quickSort(array, pivot + 1, high);
        }
    }

    // Partition part of an array, and return the index where the pivot
    // ended up.
    private static int partition(int[] array, int begin, int end) {

        int tmp = (end + begin) / 2;

        swap(array, end, tmp);
        int pivot = array[end];

        int pivotIndex = begin - 1;


        for (int i = begin; i < end; i++) {
            if (array[i] < pivot) {
                pivotIndex++;
                swap(array, i, pivotIndex);
            }
        }
        swap(array, pivotIndex + 1, end);

        return pivotIndex + 1;


    }



    /*
    public static void quickSort(int[] array) {
        if (array != null) {
            quickSort(array, 0, array.length-1);
        }
    }

    // Quicksort part of an array
    private static void quickSort(int[] array, int begin, int end) {
        if (begin < end) {
            int pivot = partition(array, begin, end);

            quickSort(array, begin, pivot-1);
            quickSort(array,pivot+1, end);
        }

    }

    // Partition part of an array, and return the index where the pivot
    // ended up.
    private static int partition(int[] array, int begin, int end) {
        int low = begin + 1;
        int high = end;
        //medianPivot(array, begin, end);
        randomPivot(array, begin, end);
        int pivot = array[begin];

        while (true) {
            while (low <= high && array[low] < pivot)
                low++;
            while (low <= high && array[high] > pivot)
                high--;

            if (low > high) {
                swap(array, begin, high);
                return high;
            }
            swap(array, low, high);
            low++;
            high--;
        }
    }

     */

    // Swaps a random element in the list with the first element in the list.
    private static void randomPivot(int[] array, int begin, int end) {
        int randomIndex = ThreadLocalRandom.current().nextInt(begin, end + 1);
        swap(array, begin, randomIndex);
    }

    private static void medianPivot(int[] array, int begin, int end) {
        int mid = (begin + end) / 2;
        if (array[begin] > array[end]) {
            if (array[end] > array[mid]) {
                swap(array, end, begin);
                return;
            } else if (array[begin] > array[mid]) {
                swap(array, mid, begin);
                return;
            } else {
                return; // begin is median
            }
        } else {
            if (array[begin] > array[mid]) {
                return; // begin is median
            } else if (array[end] > array[mid]) {
                swap(array, mid, begin);
                return;
            } else {
                swap(array, end, begin);
                return;
            }
        }

    }

    // Swap two elements in an array
    private static void swap(int[] array, int i, int j) {
        int x = array[i];
        array[i] = array[j];
        array[j] = x;
    }

    // Mergesort.

    public static int[] mergeSort(int[] array) {
        if (array == null || array.length == 0) {
            return array;

        } else {
            return mergeSort(array, 0, array.length - 1);
        }
    }

    // Mergesort part of an array
    private static int[] mergeSort(int[] array, int begin, int end) {
        if (begin < end) {
            int split = (end + begin) / 2;
            return merge(mergeSort(array, begin, split), mergeSort(array, split + 1, end));

        } else {
            return new int[]{array[begin]};
        }
    }

    // Merge two sorted arrays into one
    private static int[] merge(int[] left, int[] right) {
        int[] newArray = new int[left.length + right.length];
        int leftIndex = 0;
        int rightIndex = 0;

        for (int i = 0; i < newArray.length; ) {

            if (left[leftIndex] < right[rightIndex]) {
                newArray[i++] = left[leftIndex++];

            } else {
                newArray[i++] = right[rightIndex++];
            }

            if (leftIndex == left.length) {
                transfer(right, rightIndex, newArray, i);
                break;

            } else if (rightIndex == right.length) {
                transfer(left, leftIndex, newArray, i);
                break;
            }

        }
        return newArray;
    }

    private static void transfer(int[] sourceArray, int sourceIndex, int[] targetArray, int targetIndex) {
        while (targetIndex < targetArray.length) {
            targetArray[targetIndex++] = sourceArray[sourceIndex++];
        }
    }
}





