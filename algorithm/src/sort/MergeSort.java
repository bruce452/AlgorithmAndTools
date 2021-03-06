package sort;

public class MergeSort {

    public static void main(String[] args) {
        int[] array = {8, 2, 4, 9, 3, 1, 6, 7, 5};
        legacyMergeSort(array);
        System.out.println();
        Sort.printArray(array);
    }

    private static void legacyMergeSort(int[] a) {
        int[] aux = a.clone();
        printHashCode(aux, a);
        mergeSort(aux, a, 0, a.length);
    }

    /**
     * 每次递归返回的 dest 都是有序的！！！
     * @param src
     * @param dest
     * @param low
     * @param high
     */
    private static void mergeSort(int[] src, int[] dest, int low, int high) {
        int length = high - low;
        printUnsorted(src, dest, low, high);

        // Insertion sort on smallest arrays
        if (length < 3) {// 这里7是最佳值
            for (int i = low; i < high; i++)// 这里i不是小于等于high喔
                for (int j = i; j > low && dest[j - 1] > dest[j]; j--)
                    Sort.swap(dest, j - 1, j);

            printSorted(src, dest, low);
            return;
        }

        // Recursively sort halves of dest into src
        int mid = (low + high) >>> 1;
        mergeSort(dest, src, low, mid);   //src 与 dest 换位！！，这里把 src 的数字排好序
        mergeSort(dest, src, mid, high);
        System.out.println("---------------------------------------------------------");

        // 两段排好序后要把两段合并后复制

        // If list is already sorted, just copy from src to dest.  This is an
        // optimization that results in faster sorts for nearly ordered lists.
        // 这个是直接两个顺序是排好序的，直接复制
        if (src[mid - 1] <= src[mid]) {
            // src 复制到 dest 上
            System.arraycopy(src, low, dest, low, length);
            return;
        }

        // ！！！为什么要用两个数组因为这一步用这种方法效率比交换高太多！！！
        // ！！！为什么要交换位置，因为这一步后，排序结果数组变成了对方，虽然mergeSort出来的时候src是排序结果！！！
        // 这个是一个个判断大小，合并复制
        // Merge sorted halves (now in src) into dest
        for (int i = low, p = low, q = mid; i < high; i++) {
            if (q >= high || p < mid && src[p] <= src[q])
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }


    // 输出数组 *********************************************************************
    private static final int LENGTH = "Insertion sort   8 2 4 9 3 1 6 5 7   ".length();

    private static void printHashCode(int[] src, int[] dest) {
        System.out.println("                 src: " + src.toString() + "    dest: " + dest.toString());

    }

    private static void printUnsorted(int[] src, int[] dest, int low, int high) {
        System.out.println("                 " + src.toString() + "         " + dest.toString());
        System.out.print("low:" + low + "   high:" + high + "   ");
        Sort.printArray(src);
        System.out.print("  ");
        Sort.printArray(dest);
        System.out.println();
    }

    private static void printSorted(int[] src, int[] dest, int low) {
        System.out.println("                 " + src.toString() + "         " + dest.toString());
        System.out.print("Insertion sort   ");
        Sort.printArray(src);
        System.out.print("  ");
        Sort.printArray(dest);
        System.out.println();
        printArrow(low);
    }

    private static void printArrow(int startPosition) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = LENGTH + startPosition * 2; i > 0; i--) {
            stringBuilder.append(" ");
        }
        stringBuilder.append("↑ ↑");
        System.out.println(stringBuilder.toString());
    }
}

//输出
//                 src: [I@1540e19d    dest: [I@677327b6
//low:0   high:9   8 2 4 9 3 1 6 5 7   8 2 4 9 3 1 6 7 5
//low:0   high:4   8 2 4 9 3 1 6 5 7   8 2 4 9 3 1 6 7 5
//low:0   high:2   8 2 4 9 3 1 6 5 7   8 2 4 9 3 1 6 7 5
//                 [I@1540e19d         [I@677327b6
//Insertion sort   8 2 4 9 3 1 6 5 7   2 8 4 9 3 1 6 7 5
//                                     ↑ ↑
//low:2   high:4   8 2 4 9 3 1 6 5 7   2 8 4 9 3 1 6 7 5
//                 [I@1540e19d         [I@677327b6
//Insertion sort   8 2 4 9 3 1 6 5 7   2 8 4 9 3 1 6 7 5
//                                         ↑ ↑
//low:4   high:9   2 8 4 9 3 1 6 5 7   2 4 8 9 3 1 6 7 5
//low:4   high:6   2 4 8 9 3 1 6 5 7   2 8 4 9 3 1 6 7 5
//                 [I@1540e19d         [I@677327b6
//Insertion sort   2 4 8 9 3 1 6 5 7   2 8 4 9 1 3 6 7 5
//                                             ↑ ↑
//low:6   high:9   2 4 8 9 3 1 6 5 7   2 8 4 9 1 3 6 7 5
//low:6   high:7   2 8 4 9 1 3 6 5 7   2 4 8 9 3 1 6 7 5
//                 [I@677327b6         [I@1540e19d
//Insertion sort   2 8 4 9 1 3 6 5 7   2 4 8 9 3 1 6 7 5
//                                                 ↑ ↑
//low:7   high:9   2 8 4 9 1 3 6 5 7   2 4 8 9 3 1 6 7 5
//                 [I@677327b6         [I@1540e19d
//Insertion sort   2 8 4 9 1 3 6 5 7   2 4 8 9 3 1 6 5 7
//                                                   ↑ ↑
//
//1 2 3 4 5 6 7 8 9
