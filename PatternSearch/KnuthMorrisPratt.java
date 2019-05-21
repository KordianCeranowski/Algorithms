public class KnuthMorrisPratt {

    static void KMP(String P, String T){

        System.out.println("\n------------------------------------------------------------------\n\nZłożoność pesymistyczna algorytmu Knutha-MorrisaPratta jest \nΘ(m+n) gdzie m to długość wzorca a n to długość tekstu.\n");

        int x = 0;
        int i = 0;
        int counter = 0;

        int[] array = prefix(P);

        for(; x< T.length(); x++){

            if(P.charAt(i) == T.charAt(x)){
                if(i == P.length()-1){
                    System.out.print(x-1 + ", ") ;
                    counter++;
                    i = array[i-1];
                    continue;
                }
                i++;
                continue;
            }

            if( i == 0)
                continue;

            i = array[i-1];
            x--;

        }
        System.out.println("\nZnaleziono łącznie " + counter + " wzorców.");

    }


    static int[] prefix(String text){
        int i = 1;
        int j = 0;
        int[] array = new int[text.length()];

        for(; i< text.length(); i++){
            if(text.charAt(i) == text.charAt(j)){
                array[i] = j + 1;
                j++;
            }
            else{
                while( j != 0 ) {
                    j = array[j - 1];
                    if(text.charAt(i) == text.charAt(j)){
                        array[i] = j + 1;
                        j++;
                        break;
                    }
                }
            }

        }
        return array;
    }

}
