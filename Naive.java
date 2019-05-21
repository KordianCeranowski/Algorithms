public class Naive {

    static void naive(String P, String T) {

        System.out.println("\n------------------------------------------------------------------\n\nZłożoność pesymistyczna naiwnego algorytmu wyszukiwania wzorca " +
                "w tekście jest \nΘ(m(n − m + 1)) gdzie m to długość wzorca a n to długość tekstu.\n");

        boolean itsAMatch;
        int m = P.length();
        int n = T.length();
        int counter = 0;

        for (int s = 0; s < n - m; s++){
            if (P.charAt(0) == T.charAt(s)) {
                itsAMatch = true;
                for (int i = 1; i < P.length(); i++) {
                    if (!(P.charAt(i) == T.charAt(s + i))) {
                        itsAMatch = false;
                        break;
                    }
                }
                if(itsAMatch){
                    System.out.print(s + ", ");
                    counter++;
                }
            }
        }
        System.out.println("\nZnaleziono łącznie " + counter + " wzorców.");
    }
}
