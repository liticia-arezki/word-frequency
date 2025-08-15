public class StringIntMap {

    private int size ;
    private StringIntCouple[] pairs ;
    private static final int DEFAULT_CAPACITY = 10 ;
    private static final int CAP_MULTIPLIER = 2 ;
    
   /* Create an empty map with a default capacity equal to DEFAULT_CAPACITY. */
    public StringIntMap ( ) {
        this.size = 0 ;
        this.pairs = new StringIntCouple[DEFAULT_CAPACITY] ;
    }

    /* Create a copy of the map other */ 
    public StringIntMap ( StringIntMap other ) {
       this.size = other.size ;
       this.pairs = new StringIntCouple[other.pairs.length] ;
       // on copie les elements 
       for ( int i=0 ; i< other.size ; i++ ) {
          this.pairs[i] = new StringIntCouple(other.pairs[i].getFst() , other.pairs[i].getSnd());
       }    
    }
 
    /* Return the size of the current map */
    public int size () {
        return size ;
    }

   /* Return the value of the associated key, if present or defaultValue if not */
    public int getOrDefault (String key , int defaultValue ) {
        for (int i=0 ; i<size ; i++ ) {
            // si on trouve la clé
            if ( pairs[i].getFst().equals(key) ) {
                return pairs[i].getSnd() ;
            }
        }
        // sinon on retourne la valeur entré
        return defaultValue ;
    }


     /* Return true if and only if key is present in the map */
    public boolean containsKey ( String key ) {
        for (int i=0 ; i<size ; i++ ) {
            // si on l a trouve on return true
            if ( pairs[i].getFst().equals(key) ) {
                return true ;
            }
        }
        // sinon on return false
        return false ;
    }

  

    /* If key is not already present, add the new (key, value) association to the
     * map, otherwise change the old value associated to key to the new value.
     * returns the old value if key was present, otherwise returns-1 */

    public int put ( String key , int value ) {
        // si la valeur existe on met la nouvelle valeur
        for ( int i=0 ; i<size ; i++) {
            if (pairs[i].getFst().equals((key))) {
                int oldValue = pairs[i].getSnd();
                pairs[i].setSnd(value);
                return oldValue ;
                }
        }

        // si la valeur nexiste pas on ajoute a la taille de tableau et on ajoute la clé
        if ( size== pairs.length ) { 
            // si le tableau est plein donc on cree un nouveau tableau avec les mme valeur mais avec une taille superieur
            StringIntCouple[] newParis = new StringIntCouple[pairs.length*CAP_MULTIPLIER] ;
            System.arraycopy ( pairs , 0 , newParis , 0 , pairs.length ) ;
            pairs = newParis ;
        }

        pairs[size++] = new StringIntCouple (key,value) ;

        // dans un autre cas cest une erreur
        return -1 ;

    }


     /* If key is not present, add the new (key, 1) association, otherwise increment
      * the value associated to key */
    public void addOne ( String key ) {
        // si la clé existe on incrimente
        for ( int i=0 ; i<size ; i++ ) {
            if (pairs[i].getFst().equals(key)) {
             pairs[i].incrSnd();
            return ;   
            }
        }
        // qi elle n'existe pas on l'insert
        put(key,1);
    }


    /* removes the (key, value) association associated to key if it is present,
     * otherwise do not change the map; returns the (key, value) couple which was
     * removed or null if key was not present */
    public StringIntCouple remove ( String key ) {
        for ( int i=0 ; i<size ; i++ ) {
        // si la clé existe on la supprime
          if ( pairs[i].getFst().equals(key)) {
            // on cherche l'indice de la clé
            StringIntCouple removing = pairs[i] ;
            // on le remplace par le dernier element de map
            pairs[i] = pairs[size -1] ;
            // on supprime le dernier element de map
            pairs[size-1]=null ;
            size--;
            // on return la clé supprimer
            return removing ;
          }
        }
        // si la clé n'existe pas
        return null ;
    }


    /* returns an array containing the keys of map */
    public String[] keys() {
        // on cree un tableau
        String[] keys = new String[size] ;
        // on affectue les cles de maps vers le tableau cree
        for ( int i=0 ; i<size ; i++ ){
            keys[i]=pairs[i].getFst() ;
        }
        // on return le tabeau
        return keys ;
    }


    /* returns a String description of the map, **in an unspecified order** (this
     violates slightly the toString contract, but it's a necessary flaw here) */
    @Override
    public String toString () {
        // on cree un nouveau objet 
       StringBuilder s = new StringBuilder() ;
       // on ajoute [] au debut et a la fin de l'affichage pour mieux afficher
       s.append("[") ;
       for ( int i=0 ; i<size ; i++ ) {
       // on affiche sous forme { mot :  nombre d'accurence }
       s.append(pairs[i].getFst()).append(": ").append(pairs[i].getSnd()) ;
       if ( i< size-1 ) s.append(", ") ;
       }
       s.append("]") ;
       // on return l'objet avec l'affichage
       return s.toString() ;
    }


    /* returns a hashCode, conforming to the hashCode contract/
     * NB: this means that the order of the elements in the array should *not* be
     * taken into account! */
    @Override 
    public int hashCode () {
        // on initialise le hash code a 1
        int result = 1 ;
        for ( int i=0 ; i<size ; i++ ) {
            // on fait des operations a fin dassurer 
            result = 31*result + pairs[i].getFst().hashCode() ;
            result = 31*result + pairs[i].getSnd();
        }
        // on return le hashcode
        return result ;
    }


    /* returns true if the current map is equal to obj.
     * NB: two maps are equal if they have the same set of keys **in no specific
     * order!** and every key is associated to the same value */
    @Override
    public boolean equals ( Object obj ) {
        // si c'est le meme obj donc on return directement true
        if ( this == obj ) return true ;
        // si cest un objet NULL ou pas de la meme classe cest faux
        if ( obj==null || getClass() != obj.getClass()) return false ;
       
        StringIntMap a = (StringIntMap) obj ;
        if ( this.size != a.size ) return false ;
        // sinon on compare les mots et les entiers
        for ( int i=0 ; i<size ; i++ ) {
           String key = pairs[i].getFst() ;
           if ( !a.containsKey(key) || a.getOrDefault(key,-1) != pairs[i].getSnd()) {
            return false ;
           }
        }
        return true ;
    }


}