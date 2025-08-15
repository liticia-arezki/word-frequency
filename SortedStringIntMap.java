import java.util.Arrays ;


public class SortedStringIntMap {
    private int size ;
    private StringIntCouple[] pairs ;
    private static final int DEFAULT_CAPACITY = 10 ;
    private static final int CAP_MULTIPLIER = 2 ;

    // on initialise la classe
    public SortedStringIntMap () {
        this.size = 0 ;
        this.pairs = new StringIntCouple[DEFAULT_CAPACITY] ;
    }

    // on fait une copy de StringIntMap map
    public SortedStringIntMap ( SortedStringIntMap other ) {
        this.size = other.size ;
        this.pairs = Arrays.copyOf(other.pairs , other.pairs.length ) ;
    }


    // on return le size de map
    public int size () {
        return size ;
    }
 
    /*on chherche la cle si elle existe sinon on return - ( 1+the left) car dans la fonction put on va le mettre dans -(-left-1+1) donc cest -left  */ 
    private int binarySearch ( String key ){
        int left = 0 , right = size - 1 ;
        while ( left <=right ) {
            // on separe le tableau sur deux
            int mid = ( left + right )/2;
            // on recupere la moitié  
            int cmp = pairs[mid].fst().compareTo(key);
            // on verifie si elle est comme key
            if ( cmp == 0 ){
                return mid ;
            }
            // si inf on parcour la moitier droitensinon la gauche
            else if ( cmp < 0  ) {
                left = mid + 1 ;
            }
            else{
                right = mid - 1 ;
            }
        }

        // on return un nombre negatif si on le trouve pas et si ca position dans la fonction put
        return -(left+1);
    }



    /* on cherche si la clé existe on return sa valeur sinon on return la valeur par defaut */
    public int getOrDefault ( String key , int defaultValue ) {
        int index = binarySearch(key) ;
        if (index >= 0) {
            // si il existe en retun sa valeur
           return pairs[index].getSnd() ;
        }  else {
            // sinon la valeur par defaut
            return defaultValue ;
        }  
    }



    // on cherche si la clé existe ou non 
    public boolean containsKey ( String key) {
        // si la clé existe dans on return un nombre postif dans binarysearch
        return binarySearch(key) >= 0 ;
    }


    // on met une clé avec sa valeur dans le tableau
    public int put(String key , int value) {
        //si le tableau n'existe pas on le cree
        if(size == 0){
            pairs[size] = new StringIntCouple(key, 1);
            size++;
            return -1;
        }
        // on return k'indice de mot rechercher
        int index = binarySearch(key) ;
        // si > 0 donc il existe deja il suffit de returner sa valeur de debut et on reset la valeur
        if ( index >= 0) {
            int oldValue = this.pairs[index].getSnd() ;
            pairs[index].setSnd(value) ;
            return oldValue ;
        }
        else {
            // si elle n'existe pas donc on l'insert dans sa place on augmentant la taille de tableau
            // on augmente la taille
            ensureCapacity() ;
            // on trouve l'index 
            index = -(index +1) ;
            // on l'insert
            insertAtIndex ( key , value , index ) ;
            // on augmente la taille
            size++;
            return -1 ;
        }
    }


    // on ajoute une clé
    public void addOne ( String key ) {
        // si le tableau n'existe pas on le cree 
        if(size == 0){
            pairs[size] = new StringIntCouple(key, 1);
            size++;
            return;
        }

        // sinon on cherche l'index si il existe ou non
        int index = binarySearch(key);
        // si il existe donc on incrimente les valeurs
            if ( index >= 0 ) {
             pairs[index].incrSnd();   
            }
            else {
                // sinon on l'insert
                put (key,1) ;
            }
    }


    /* l'affichage des clés */ 
    public String[] keys() {
        String[] keys = new String[size] ;
        for ( int i=0 ; i < size ; i++ ) {
            keys[i]=pairs[i].getFst();
        }
        return keys;
    }


    // on supprime un element dans map
    public StringIntCouple remove ( String key ) {
        int index = binarySearch(key);
        // si on a trouver l'element on le supprime
            if ( index >= 0 ) {
               StringIntCouple removing = pairs[index] ;
               for ( int i=index ; i<size ; i++){
                pairs[i]=pairs[i+1];
               }
               // sinon on return null
               pairs[--size]=null ;
               return removing ;
        }
        
        return null ;
    }


    // l'affichage de map
    @Override 
    public String toString (){
        String str = "[";
        for(int i=0; i<size; i++){
            str += pairs[i].toString();
            if(i != size-1){
                str += ",";
            }
        }
        str += "]";

        return str ;
    }



    // le hash code de map
    @Override 
    public int hashCode () {
        int result = 1 ;
        for ( int i=0 ; i<size ; i++ ) {
            result = 31*result + pairs[i].hashCode() ;
        }
        return result ;
    }


    // insert un mot dans map avec son indice
    private void insertAtIndex ( String key , int value , int index ){
          for (int i=size ; i>index;i--){
            // on copie les valeurs droites de l'indice dans le meme tableau
            pairs[i]=pairs[i-1];
          }
          // on insert dans l'indice
          pairs[index]=new StringIntCouple(key,value);
    }


    // on augmente la capaciter de map
    private void ensureCapacity (){
        // si ca depasse la taille on le copie vers un autre tableau avec un nauveau size
        if ( size == pairs.length ){
            pairs = Arrays.copyOf(pairs,pairs.length*CAP_MULTIPLIER);
        }
    }



   // si deux objets sont equals ou non
    @Override 
    public boolean equals ( Object obj ) {
        if ( this == obj ) return true ; // si si le meme obj dans c est true
        if ( obj == null || getClass() != obj.getClass()) return false ; // si ca n'existe pas ou c'est pas le meme class donc cest faux
        SortedStringIntMap other = ( SortedStringIntMap ) obj ; 
        // on compare les mots et les entier avec lesd tableau
        if ( size != other.size ) return false ;
        String[] keys1 = this.keys() ;
        String[] keys2 = other.keys() ;
        // on trie les tableaux
        Arrays.sort(keys1) ;
        Arrays.sort(keys2);
 

        // on compares les deux tableaux 
        for ( int i=0 ;i<size;i++){
          if ( !keys1[i].equals(keys2[i]) || this.getOrDefault(keys1[i],0)!=other.getOrDefault(keys2[i],0)) {
             return false ;
          }    
        }
        return true ;

    }

    


}