package HomeWorkThreads;

public class Main {
    private static final int SIZE = 10000000;
    private static final int HALF_SIZE = SIZE / 2;

    public static void main(String[] args) {

        firstMethod();
        secondMethod();
    }

    public static void firstMethod () {

        float[] arr = new float[SIZE];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("One thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    public static void secondMethod() {

        float[] arr = new float[SIZE];
        // Создаем два массива для левой и правой части исходного
        float[] leftHalf  = new float[HALF_SIZE];
        float[] rightHalf = new float[HALF_SIZE];


        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        // Копируем в них значения из большого массива
        long startTime = System.currentTimeMillis();
        System.arraycopy(arr, 0, leftHalf, 0, HALF_SIZE);
        System.arraycopy(arr, HALF_SIZE , rightHalf, 0, HALF_SIZE);


        // Запускает два потока и параллельно просчитываем каждый малый массив

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < HALF_SIZE; i++) {
                leftHalf[i] = (float)(leftHalf[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < HALF_SIZE; i++) {
                rightHalf[i] = (float)(rightHalf[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        thread1.start();
        thread2.start();
        System.out.println(thread1.getName());
        System.out.println(thread2.getName());

        // Склеиваем малые массивы обратно в один большой

        float[] mergedArray = new float[SIZE];
        System.arraycopy(leftHalf, 0, mergedArray, 0, HALF_SIZE);
        System.arraycopy(rightHalf, 0, mergedArray, HALF_SIZE, HALF_SIZE);

        System.out.println("Two thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
    }
}
