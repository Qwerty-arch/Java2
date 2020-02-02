public class CalculateThread extends Thread {


    private final float[] arr;

    public CalculateThread(String name, float[] arr) {
        super(name);
        this.arr = arr;
        start();
    }

    @Override
    public void run() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}
