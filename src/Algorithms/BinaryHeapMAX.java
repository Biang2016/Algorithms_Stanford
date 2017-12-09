package Algorithms;

/**
 * @author Bird
 * @category 二叉堆的实现
 */
public class BinaryHeapMAX {

    private static final int DEAFAULT_CAPACITY = 10000;
    private int currentSize;//堆中的元素个数
    private int[] array;//存储堆中的元素使用数组存储方式

    public BinaryHeapMAX() {
        this(DEAFAULT_CAPACITY);
    }

    public BinaryHeapMAX(int capacity) {
        currentSize = 0;
        array = new int[capacity + 1];

    }

    public int size() {
        return currentSize;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == array.length - 1;
    }

    public void makeEmpty() {
        currentSize = 0;
    }

    /**
     * 插入使用“上移”法
     *
     * @param x
     */
    public void insert(int x) {
        if (isFull())
            throw new RuntimeException("溢出");

        int hole = ++currentSize;
        for (; hole > 1 && x>array[hole / 2]; hole /= 2)
            array[hole] = array[hole / 2];
        array[hole] = x;
    }

    /**
     * 使用下溢法进行删除操作
     *
     * @return
     */
    public int deleteRoot() {
        if (isEmpty())
            return -1;

        int minItem = array[1];
        array[1] = array[currentSize--];
        percolateDown(1);

        return minItem;
    }

    public int Root() {
        if (isEmpty())
            return -1;
        int minItem = array[1];
        return minItem;
    }

    private void percolateDown(int hole) {
        int child = 0;
        int tmp = array[hole];

        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            if (child != currentSize && array[child + 1]>array[child])
                child++;
            if (array[child]>tmp)
                array[hole] = array[child];
            else
                break;
        }
        array[hole] = tmp;
    }
}

