public class TryStringIntMap {
    public static void main ( String[]  args ) {

        StringIntMap map = new StringIntMap() ;


        // on met quelques exemples dans maps 
        map.put("moi" , 5) ;
        map.put("poo" , 8) ;
        map.put("bloc" , 3) ;
        map.put("Gal" , 10) ;
        map.put("liticia" , 20) ;

        // on affiche les resultats avec l'utilisation de StringIntMap
        System.out.println ("taille de map :"+map.size());
        System.out.println ("on affiche map ");
        System.out.println (" -----> map :"+map);
        // si on a Gal dans Map et si on a hello dans map
        System.out.println ("map contains 'Gal' : "+map.containsKey("Gal"));
        System.out.println ("map contains 'hello' : "+map.containsKey("hello"));
        // tester getOrDefault
        System.out.println ("value associated to liticia : "+map.getOrDefault("liticia",0));
        System.out.println ("value associated to bloc : "+map.getOrDefault("bloc",0));
       

        // on ajoute des valeurs a bloc et liticia
        map.addOne("bloc" );
        map.addOne("liticia") ;

        System.out.println (" map apres incrimenter 'bloc' ' liticia' :"+map);

        // on supprime liticia de map et on l'affiche
        StringIntCouple removing = map.remove("liticia");
        System.out.println("element sup " + removing ) ;
        System.out.println (" map apres sup :"+map);

    }


}