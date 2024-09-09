// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab1;
import java.util.Arrays;
import java.util.Comparator;
/**
 * Пользовательская реализация ArrayList.
 * Этот класс не является потокобезопасным.
 *
 * @param <T> Тип элементов, хранимых в списке
 * @author Givermaen
 * @version $Id: $Id
 */
public class GiverArrayList<T> {
    /**
     * Емкость массива по умолчанию.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Массив, используемый для хранения элементов списка.
     */
    private Object[] elements;
    /**
     * Количество элементов в списке
     */
    private int size;

    /**
     * Конструктор для создания списка с начальной емкостью по умолчанию
     */
    public GiverArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Конструктор для создания списка с определенной начальной емкостью.
     * Если начальная емкость указана как 0, то используется емкость по умолчанию.
     *
     * @param initialCapacity указывает начальную емкость списка
     * @throws java.lang.IllegalArgumentException если указана отрицательная начальная емкость
     */
    public GiverArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            elements = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            elements = new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }
    /**
     * Добавляет элемент в конец списка.
     * Если необходимо, увеличивает емкость списка перед добавлением.
     *
     * @param element элемент, который нужно добавить в список
     */
    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }
    /**
     * Вставляет элемент на указанную позицию в списке.
     * Сдвигает элемент, который находится в позиции index, а также любые последующие элементы
     * на одну позицию вправо (добавляет единицу к их индексам).
     *
     * @param index позиция, на которую нужно добавить элемент
     * @param element элемент, который нужно добавить в список
     * @throws java.lang.IndexOutOfBoundsException если индекс находится за пределами диапазона (index < 0 || index > size())
     */
    public void add(int index, T element) {
        checkIndexForAdd(index);
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }
    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс элемента, который нужно вернуть
     * @return элемент на указанной позиции в списке
     * @throws java.lang.IndexOutOfBoundsException если индекс находится за пределами диапазона (index < 0 || index >= size())
     */
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }
    /**
     * Удаляет элемент, находящийся по указанному индексу из списка.
     * Все последующие элементы сдвигаются влево (отнимается единица от их индексов).
     * Последний элемент списка обнуляется, чтобы облегчить работу сборщика мусора.
     *
     * @param index индекс элемента для удаления
     * @throws java.lang.IndexOutOfBoundsException если индекс находится за пределами диапазона (index < 0 || index >= size)
     * @return size размер массива после удаления элемента
     */
    public int remove(int index) {
        checkIndex(index);
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index+1, elements, index, numMoved);
        }
        elements[--size] = null;
        return size;
    }
    /**
     * Очищает список, удаляя все его элементы.
     * После вызова этого метода размер списка будет равен 0.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }
    /**
     * Сортирует элементы списка, используя предоставленный компаратор.
     * Сортировка выполняется алгоритмом быстрой сортировки (QuickSort).
     *
     * @param c компаратор, используемый для сравнения элементов списка
     */
    public void sort(Comparator<? super T> c) {
        quickSort(elements, 0, size - 1, c);
    }
    /**
     * Рекурсивный метод для выполнения быстрой сортировки (QuickSort) над массивом.
     *
     * @param array массив объектов для сортировки
     * @param low начальный индекс диапазона, который нужно отсортировать
     * @param high конечный индекс диапазона, который нужно отсортировать
     * @param c компаратор, используемый для сравнения элементов
     */
    private void quickSort(Object[] array, int low, int high, Comparator<? super T> c) {
        if (low < high) {
            int pivotIndex = partition(array, low, high, c);
            quickSort(array, low, pivotIndex - 1, c);
            quickSort(array, pivotIndex + 1, high, c);
        }
    }
    /**
     * Помогает разбить массив на части для быстрой сортировки.
     * Этот метод реализует операцию разбиения, после которого все элементы
     * меньше опорного находятся перед ним, а большие или равные - после.
     *
     * @param array массив объектов для разбиения
     * @param low начальный индекс диапазона для разбиения
     * @param high конечный индекс диапазона для разбиения
     * @param c компаратор, используемый для сравнения элементов
     * @return индекс опорного элемента
     */
    private int partition(Object[] array, int low, int high, Comparator<? super T> c) {
        T pivot = (T) array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare((T)array[j], pivot, c) <= 0) {
                i++;

                Object temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        Object temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }
    /**
     * Сравнивает два элемента с использованием предоставленного компаратора или их естественного порядка.
     *
     * @param a первый объект для сравнения
     * @param b второй объект для сравнения
     * @param c компаратор для определения порядка элементов. Если {@code null}, используется естественный порядок.
     * @return отрицательное целое число, ноль или положительное целое число, если первый элемент
     *         меньше, равен, или больше второго.
     */
    private int compare(T a, T b, Comparator<? super T> c) {
        if (c == null) {
            return ((Comparable<? super T>) a).compareTo(b);
        } else {
            return c.compare(a, b);
        }
    }
    /**
     * Заменяет элемент в указанной позиции в этом списке на указанный элемент.
     *
     * @param index индекс заменяемого элемента
     * @param element элемент, который будет сохранен в указанной позиции
     * @throws java.lang.IndexOutOfBoundsException если индекс находится за пределами диапазона (index < 0 || index >= size)
     */
    public void set(int index, T element) {
        checkIndex(index);
        elements[index] = element;
    }
    /**
     * Увеличивает емкость массива элементов списка, если необходимо,
     * для обеспечения места для дополнительных элементов.
     * Увеличение линейное
     */
    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, size + 5);
        }
    }
    /**
     * Возвращает количество элементов в этом списке.
     *
     * @return количество элементов в списке
     */
    public int size() {
        return size;
    }
    /**
     * Проверяет, что указанный индекс находится в пределах допустимого диапазона для списка.
     * Если индекс находится вне допустимого диапазона, генерируется исключение.
     *
     * @param index индекс для проверки
     * @throws IndexOutOfBoundsException если индекс находится вне диапазона доступных значений
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
    /**
     * Проверяет, что указанный индекс находится в допустимом диапазоне для добавления элемента.
     * Допустимый диапазон индексов для добавления элемента в список находится от 0 до размера списка включительно.
     * Это позволяет добавить новый элемент как в конец списка, так и в любую позицию в пределах списка.
     *
     * @param index индекс, по которому планируется добавить элемент
     * @throws IndexOutOfBoundsException если индекс находится за пределами допустимого диапазона
     *                                   (index < 0 || index > size)
     */
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}