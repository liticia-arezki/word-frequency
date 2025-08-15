public class TryStringIntCouple {
    public static void main ( String[] args ) {


        // on cree des exemples StringIntCouple
        StringIntCouple c1 = new StringIntCouple ( "hello" , 5 ) ;
        StringIntCouple c2 = new StringIntCouple ( "Liti" , 10 ) ;

        // on affiche ce qu'on a creer
        System.out.println (c1) ;
        System.out.println (c2) ;
   
        // on reset les valeurs par incrim et decrim
        System.out.println (" On incrimente hello 5 fois et decrimente Liti 1 fois") ;
        c1.incrSnd() ;
        c1.incrSnd() ;
        c1.incrSnd() ;
        c1.incrSnd() ;
        c1.incrSnd() ;

        c2.decSnd() ;
       // on affiche apres l'incrimentation et decrimentation 
        System.out.println (c1) ;
        System.out.println (c2) ;

        // on essaie si les valeurs sont equivalentes ou non 
        
        
        System.out.println (" On compare avec [ Liti , 10 ] les deux exemples precedents ") ;
        StringIntCouple c3 = new StringIntCouple ( "Liti" , 10 ) ;
        System.out.println (" on compare "+c2 +" : "+c2.equals(c3)) ;
        System.out.println (" On incrimente  Liti 1 fois") ;
        c2.incrSnd() ;
        System.out.println (" on compare "+c2 +" : "+c2.equals(c3)) ;
        System.out.println (" on compare "+c1 +" : "+c1.equals(c3)) ;

    }
}