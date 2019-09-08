import java.util.*;
import java.util.concurrent.*;

public class Test {

    public static void main(String[] args) {

        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "24");
        int[] a = new int[]{3,2,4,2,3,4};

        ASum sum = new ASum(a, 0, a.length);
        sum.compute();
        System.out.println(sum.SUM);
    }

    private static class ASum extends RecursiveAction {
        int[] A; // input array
        int LO, HI; // subrange
        int SUM; // return value

        public ASum(int[] a, int LO, int HI) {
            A = a;
            this.LO = LO;
            this.HI = HI;
        }

        @Override
        protected void compute() {
            //SUM = 0;
            int mid =  HI - LO >>> 1;
            if (mid <= 2){
                for (int i = LO; i < HI; i++) SUM += A[i];
                System.out.println(SUM);
            }else {
                ASum left = new ASum(A, LO, mid);
                ASum right = new ASum(A, mid, HI);
                invokeAll(left, right);
                SUM = left.SUM + right.SUM;
            }
        } // compute()

    }

}

