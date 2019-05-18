package all.coding;

public class PercentViewer {
    private float done;
    private float all;

    public void anotherOneDone() {
        this.done++;
    }

    public PercentViewer(int[] array) {
        this.all = 0;
        this.done = 0;
        for(int i = 0; i < array.length; i++)
            this.all += array[i];
    }


    public void getIt(){
        System.out.printf("Zakodowano %.2f pliku\n", done/all);
    }
}
