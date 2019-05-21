public class RabinKarp {

    static void rabinKarp(String P, String T){
        long start = System.currentTimeMillis();
        System.out.println("\n------------------------------------------------------------------\n\nZłożoność pesymistyczna algorytmu Rabina-Karpa jest\n" +
                "Θ(m(n − m + 1)) gdzie m to długość wzorca a n to długość tekstu.\n");

        int q = 251;
        int d = 256;
        int counter = 0;
        int hash = 1;

        for (int i = 0; i < P.length() - 1; i++){
            hash = (hash * d) % q;
        }

        int p = 0, t = 0;
        for (int i = 0; i < P.length(); i++) {
            p = (d * p + P.charAt(i)) % q;
            t = (d * t + T.charAt(i)) % q;
        }

        for (int s = 0; s <= T.length() - P.length(); s++){
            if (p == t){
                int j;
                for (j = 0; j < P.length(); j++)
                    if (P.charAt(j) != T.charAt(s + j))
                        break;
                if (j == P.length()){
                    System.out.print(s+ ", ");
                    counter++;
                }
            }
            if (s < T.length() - P.length()){
                int t1 = (T.charAt(s) * hash) % q;
                if (t < t1)
                    t = t + q;
                t = (d * (t - t1) + T.charAt(s + P.length())) % q;
            }
        }

        System.out.println("\nZnaleziono łącznie " + counter + " wzorców.");
        System.out.println("Czas: " + (System.currentTimeMillis() - start) + "ms");
    }

}
