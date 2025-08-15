public class TrySortedStringIntMap {
    public static void main ( String[] args){

        SortedStringIntMap sortedMap = new SortedStringIntMap();

        // on donne des exemples 
        sortedMap.put("liticia",2);
        sortedMap.put("poo",5);
        sortedMap.put("bloc",1);
        sortedMap.put("bbc",2);
        sortedMap.put("aaabc",2);
        // on essaie dincrimenter
        sortedMap.addOne("bloc");

        System.out.println ( sortedMap.toString());
        
        // on essaie les fonctions utilise 
        // on cherche si il y a des cles dans Map
        System.out.println("\nContient bbc ?"+sortedMap.containsKey("bbc"));
        System.out.println("Contient aaaa ?"+sortedMap.containsKey("aaaa"));

        // on return la valeur des clés si il existe sinon les valeurs par defaut
        System.out.println("\nValeur par defaut de bloc ? "+sortedMap.getOrDefault("bloc",-6));
        System.out.println("Valeur par defaut de cat ?   "+sortedMap.getOrDefault("cat",1000));

        System.out.println("\nValeur assosier a poo ? "+sortedMap.getOrDefault("poo",10));
        
        //affichage des elements 
        System.out.println ( sortedMap.toString());

        // on supprime un element
        sortedMap.remove("bloc");
        System.out.println("\nafter remove bloc ");

          //affichage des elements apres la suppression
        System.out.println ( sortedMap.toString());

    }
}