public class RabinKarp {

    //jedyne co wzięte z pseudokodu musi nie działać :<

    static void  rabinKarp(String P, String T){
        rabinKarp(P, T, 128, 27077);
    }

    static void rabinKarp(String P, String T, int d, int q){
        System.out.println("\n------------------------------------------------------------------\n\nZłożoność pesymistyczna algorytmu Rabina-Karpa jest\n" +
                "Θ(m(n − m + 1)) gdzie m to długość wzorca a n to długość tekstu.\n");

        // P - wzorzec, tablica [1..m],
        // T- tekst, tablica [1..n],
        // d - rozmiar alfabetu (np. 128),
        // q - liczba pierwsza (np. 27077)
        boolean itsAMatch;
        int counter = 0;

        int h = 1;
        int m = P.length();
        int n = T.length();
        for(int i = 1; i < m-1; i++) {
            h = (h * d) % q;
        }

        int p = 0;
        int t = 0;
        for(int i = 1; i < m; i++){
            p = (d * p + P.charAt(i)) % q;
            t = (d * t + T.charAt(i)) % q;
        }
        // wyliczone: wartość p "kodująca" P[1..m]
        // oraz wartość t "kodująca" T[s+1..s+m] dla s==0
        // kodowanie niejednoznaczne! (haszowanie)

        for(int s = 0; s < (n - m); s++){

            if(p == t){     //fragment w tym ifie wyciągnięty z naive na żywca
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
//                if P[1..m]==T[s+1 .. s+m] // tu porównujemy m znaków (w pętli)  ????
//                wypisz: znalezione wystąpienie wzorca od pozycji s+1              ????
            }

            if(s < (n-m)){


                int t1 = ( T.charAt( s+1 ) * h ) % q;
                if (t < t1){
                    t=t+q;
                }
                t = ( d * ( t - t1 ) + T.charAt( s + m )) % q; // usunąłem +1 z s+m+1 bo wykraczał równo o to 1 z tablicy (?)

                // czyli t = (d*(t-T[s+1]*h)+T[s+1+m])%q, gdzie arytmetyka jest
                // modulo q (mnożenie i odejmowanie)
                // to wylicza wartość t "kodującą" T[s+2, ... , s+m,s+1+m]
                // na podstawie wartości t "kodującej" T[s+1,s+2, ... , s+m]
            }
        }
        System.out.println("\nZnaleziono łącznie " + counter + " wzorców.");
    }
}


//Rabin-Karp(P,T,d,q)
//// P - wzorzec, tablica [1..m],
//// T- tekst, tablica [1..n],
//// d - rozmiar alfabetu (np. 128),
//// q - liczba pierwsza (np. 27077)
//        h=1;
//        for i=1 to m-1
//        h = (h*d) % q // wyliczy h = (d do potęgi m-1) modulo q
//        p=0
//        t=0
//        for i=1 to m
//        p = (d*p+P[i])%q
//        t = (d*t+T[i])%q
//// wyliczone: wartość p "kodująca" P[1..m]
//// oraz wartość t "kodująca" T[s+1..s+m] dla s==0
//// kodowanie niejednoznaczne! (haszowanie)
//        for s=0 to n-m
//        if p==t
//        if P[1..m]==T[s+1 .. s+m] // tu porównujemy m znaków (w pętli)
//        wypisz: znalezione wystąpienie wzorca od pozycji s+1
//        if s < n-m // czy tekst się nie skończył?
//        t1=(T[s+1]*h)%q;
//        if (t<t1) t=t+q;
//        t = (d*(t-t1)+T[s+m+1])%q;
//// czyli t = (d*(t-T[s+1]*h)+T[s+1+m])%q, gdzie arytmetyka jest
//// modulo q (mnożenie i odejmowanie)
//// to wylicza wartość t "kodującą" T[s+2, ... , s+m,s+1+m]
//// na podstawie wartości t "kodującej" T[s+1,s+2, ... , s+m]