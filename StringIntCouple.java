public class StringIntCouple {

    private final String fst ;
    private int snd ;


    // definir la classe avec un mot et un entier
    public StringIntCouple ( String fst , int snd ) {
        this.fst=fst;
        this.snd=snd;
    }

    
    // on initialise le entier associé au mot avec 0
    public StringIntCouple ( String fst ) {
        this(fst ,0) ;
    }


    // on return l'entier associé avec le nom get
    public int getSnd () {
        return snd ;
    }

    // on return le mot avec le nom get 
    public String getFst () {
        return fst ;
    }


    // on return le mot
    public String fst () {
        return fst ;
    }


    // on return l'entier associeé
    public int snd () {
        return snd ;
    }

    // on reset l'entier associer au mot
    public void setSnd ( int snd ) {
        this.snd=snd ;
    }


    // on incrimente l'entier associé a la clé
    public void incrSnd () {
        this.snd++;
    }


    // on decrimente l'entier associé au mot
    public void decSnd () {
        this.snd-- ;
    }


    // on return un boolean si deux objet sont egaux ou non
    @Override
    public boolean equals ( Object obj ) {
        // si c'est le meme obj cest true
        if ( this == obj ) return true ;
        // si l'objet est NULL ou pas de la meme classe c'est fauux
        if ( obj==null || getClass()!=obj.getClass()) return false ;
        // sinon on compare les mots des objets et leurs entiers
        StringIntCouple other = (StringIntCouple)obj ;
        return snd==other.snd && fst.equals(other.fst);
    }

 

    // cest pour l'affichage
    @Override
    public String toString () {
        // on initialise un objet string s
        StringBuilder s = new StringBuilder() ;
        // on ajoute a s au debut et a la fin  []
        s.append("[") ;
        // on affiche sous forme { mot :  nombre d'accurence }
        s.append(fst).append(": ").append(Integer.toString(snd)) ;
        s.append("]") ;
         // on return l'objet avec l'affichage
         return s.toString() ;
   
    }


    // le HashageCode
    @Override
    public int hashCode () {
        return 31*fst.hashCode() + snd ;
    } 

}