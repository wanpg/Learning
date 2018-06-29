package com.wanpg;

public class TestSort {

    public static void main(String[] args) {
//        int[] array = new int[]{1, 3, 5, 332, 1, 34, 56, 22, 77, 89, 2235};
        int[] array = generateArray(100000000);
        long start;

        int[] copy1 = copy(array);
        start = System.currentTimeMillis();
        int[] bubbleSort = bubbleSort(copy1);
        System.out.println("冒泡排序所需时间：" + (System.currentTimeMillis() - start));
        printArray(bubbleSort);

        int[] copy2 = copy(array);
        start = System.currentTimeMillis();
        int[] quickSort = quickSort(copy2);
        System.out.println("快速排序所需时间：" + (System.currentTimeMillis() - start));
        printArray(quickSort);

        int[] copy3 = copy(array);
        start = System.currentTimeMillis();
        int[] insertSort = insertSort(copy3);
        System.out.println("插入排序所需时间：" + (System.currentTimeMillis() - start));
        printArray(insertSort);

        int[] copy4 = copy(array);
        start = System.currentTimeMillis();
        int[] mergeSort = mergeSort(copy4);
        System.out.println("归并排序所需时间：" + (System.currentTimeMillis() - start));
        printArray(mergeSort);

    }

    public static int[] copy(int[] arr) {
        int[] tmp = new int[arr.length];
        System.arraycopy(arr, 0, tmp, 0, arr.length);
        return tmp;
    }

    public static void printArray(int[] arr) {
        boolean isFirst = true;
        for (int i : arr) {
            if (isFirst) {
                isFirst = false;
            } else {
                System.out.print(", ");
            }
            System.out.print(i);
        }
        System.out.println();
    }

    public static int[] generateArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * length * 10);
        }
        return arr;
    }

    /**
     * 数据结构	数组
     * 最坏时间复杂度	n^2
     * 最优时间复杂度	n
     * 平均时间复杂度	n^2
     * 空间复杂度	总共 n，需要辅助空间 1
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
        return arr;
    }

    /**
     * 数据结构	不定
     * 最坏时间复杂度	n^2
     * 最优时间复杂度	nlogn
     * 平均时间复杂度	nlogn
     * 空间复杂度	根据实现的方式不同而不同
     *
     * @param arr
     * @return
     */
    public static int[] quickSort(int[] arr) {
        quickSortInternal(arr, 0, arr.length - 1);
        return arr;
    }

    public static void quickSortInternal(int[] arr, int left, int right) {
        if (left >= right || arr == null || arr.length <= 1) {
            return;
        }
        int i = left;
        int j = right;
        int pivotValue = arr[(left + right) / 2];
        while (i <= j) {
            while (arr[i] < pivotValue) {
                ++i;
            }
            while (arr[j] > pivotValue) {
                --j;
            }
            if (i < j) {
                swap(arr, i, j);
                ++i;
                --j;
            } else if (i == j) {
                ++i;
            }
        }
        quickSortInternal(arr, left, j);
        quickSortInternal(arr, i, right);
    }

    /**
     * 数据结构	数组
     * 最坏时间复杂度	n^2
     * 最优时间复杂度	n
     * 平均时间复杂度	n^2
     * 空间复杂度	总共 n，需要辅助空间 1
     *
     * @param arr
     * @return
     */
    public static int[] insertSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                swap(arr, j, j + 1);
                j--;
            }
//            arr[j + 1] = key;
        }
        return arr;
    }

    /**
     * 分类	排序算法
     * 数据结构	数组
     * 最坏时间复杂度	nlog n
     * 最优时间复杂度	nlog n
     * 平均时间复杂度	nlog n
     * 空间复杂度	n
     *
     * @param arr
     * @return
     */
    public static int[] mergeSort(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] result = new int[arr.length];
        mergeSortInternal(arr, result, 0, arr.length - 1);
        return arr;
    }

    public static void mergeSortInternal(int[] arr, int[] result, int start, int end) {
        if (start >= end) {
            return;
        }
        // 计算长度
        int len = end - start;
        // 计算中间位置
        int mid = len / 2 + start;
        int start1 = start;
        int end1 = mid;
        int start2 = mid + 1;
        int end2 = end;
        // 递归分组排序
        mergeSortInternal(arr, result, start1, end1);
        mergeSortInternal(arr, result, start2, end2);

        // 排序两组有序数组
        int k = start;
        while (start1 <= end1 && start2 <= end2) {
            if (arr[start1] < arr[start2]) {
                result[k] = arr[start1];
                start1++;
            } else {
                result[k] = arr[start2];
                start2++;
            }
            k++;
        }
        while (start1 <= end1) {
            result[k] = arr[start1];
            k++;
            start1++;
        }
        while (start2 <= end2) {
            result[k] = arr[start2];
            k++;
            start2++;
        }
        for (int i = start; i <= end; i++) {
            arr[i] = result[i];
        }
    }

    private static int[] swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        return arr;
    }
}
