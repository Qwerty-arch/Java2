public class Main {

    private static final int size = 10000000;
    private static final int h = size / 2;
    private static float[] arr = new float[size];

    public static void main(String[] args) {

        System.out.println("Время выполнения вычеслний в один поток : " + NormalArray(arr));
        System.out.println("Время выполнения вычеслний в два потока : " + BadThreadArrayCalculate(arr, h));

    }

        private static long NormalArray (float[] arr) {
        for (int i = 0; i < arr.length; i++) {
           arr[i] = 1f;
        }
        long firstTime1 = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long LastTime1 = System.currentTimeMillis();
        return LastTime1 - firstTime1;
    }


    private static long BadThreadArrayCalculate(float[] arr, int h) {

        long firstTime = System.currentTimeMillis();

        float[] a1 = new float[h];
        float[] a2 = new float[h];

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        CalculateThread calculateThread1 = new CalculateThread("Подсчёт первой половины массива", a1);
        CalculateThread calculateThread2 = new CalculateThread("Подсчёт второй половины массива", a2);

        try {
            calculateThread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            calculateThread2.join();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        long lastTime = System.currentTimeMillis();

        return lastTime - firstTime;
    }

}




