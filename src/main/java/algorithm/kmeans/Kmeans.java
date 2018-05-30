package algorithm.kmeans;

public class Kmeans {

    public static void main(String[] args) {

        //initializarea variabilelor de care avem nevoie
        int arr[] = {2, 24, 19, 3, 7, 22, 6, 32, 20};
        int i, m1, m2, a, b, n = 0;
        float sum1, sum2;
        a = arr[0];
        b = arr[1];
        m1 = a;
        m2 = b;
        int cluster1[], cluster2[];
        boolean flag;

        do {
            sum1 = 0;
            sum2 = 0;
            cluster1 = new int[arr.length];
            cluster2 = new int[arr.length];
            n++;
            int  k = 0, j = 0;

            for (i = 0; i < arr.length; i++) {
                if (Math.abs(arr[i] - m1) <= Math.abs(arr[i] - m2)) {
                    cluster1[k] = arr[i];
                    k++;
                } else {
                    cluster2[j] = arr[i];
                    j++;
                }
            }
            sum1 = getSum(sum1, cluster1, k);
            sum2 = getSum(sum2, cluster2, j);

            System.out.println("m1=" + m1 + "   m2=" + m2);
            a = m1;
            b = m2;
            m1 = Math.round(sum1 / k);
            m2 = Math.round(sum2 / j);
            flag = !(m1 == a && m2 == b);

            printAfterIteration(n, cluster1, " , cluster 1 :");

            printAfterIteration(n, cluster2, " , cluster 2 :");

        } while (flag);

        printFinalCluster(cluster1, "Final cluster 1 :");

        printFinalCluster(cluster2, "Final cluster 2 :");
    }

    private static float getSum(float sum2, int[] cluster2, int j) {
        for (int i = 0; i < j; i++) {
            sum2 = sum2 + cluster2[i];
        }
        return sum2;
    }

    private static void printFinalCluster(int[] cluster2, String s) {
        System.out.println(s);
        for (int aCluster2 : cluster2) {
            System.out.print(aCluster2 + "\t");
        }
        System.out.println();
    }

    private static void printAfterIteration(int n, int[] cluster1, String s) {
        System.out.println("After iteration " + n + s);
        for (int aCluster1 : cluster1) {
            System.out.print(aCluster1 + "\t");
        }
        System.out.println();
    }
}