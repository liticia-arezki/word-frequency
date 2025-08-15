import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.io.IOException; 

public class WordFreq  {

  public static void main (String[]  args) throws IOException {

       if (args.length < 1 ) {
         // si il y a pas d'arguments donc on retourne une erreur 
          System.out.println ("Erreur nombre d'arguments ne convient pas ") ;
          return ;
         }

       // on prend le nom de fichier de premier argument donnée 
       String filename = args[0] ;
       // et le nombre de mots on cherche par defaut si cest pas donné cest 10
       int k=10 ;
       

       // dans le cas il ya pas un nombre fourni ou le nombre est non valable
       if ( args.length >=2 ) {
         try {
            // c est pour convertir le deuxieme argument en un entier pour avoir le nombre daffichage des mots
            k=Integer.parseInt(args[1]);
         } catch (NumberFormatException e) {
            // si ce nest pas un nombre entier donc on utilise 10 au lieu d'argument donné
            System.out.println("Invalid number for words , We will use the by default 10 Words.");
         }
       }

       // creer map pour stocker 
       StringIntMap wordMap = new StringIntMap();

       
       try {
            // on cree une variable pour le fichier
            FileReader fr = new FileReader(filename);
            // on lit le fichier
            BufferedReader buff = new BufferedReader(fr);
            String line ;


            // on sauvegarde une ligne a chaque fois a partir de fichier donné dans Map avec processLine
            while ((line=buff.readLine())!=null) {
               processLine ( line,wordMap);
            }

            // on ferme le fichier 
            buff.close(); 

         // si le fichier n esxiste pas on affiche un message d erreur 
         } catch (FileNotFoundException e) {
            String m = e.getMessage();
            System.err.println("file " + filename + ": " + m);
            return;
         }

         // on affiche les K mots les plus repeter qu'on a trouvé dans Map
         printKwords ( wordMap,k);

      }


      // Classé les mots dans leurs places dans Map
      private static void processLine ( String line , StringIntMap wordMap) {
         // on transfome la ligne extraite en miniscule avec la supression des blancs repetés 
         line = line.toLowerCase().replaceAll("[^a-zA-Z]"," " ).replaceAll("\\s+"," ");
         // on separe les separe les mots a l aide de split pour les classés dans un tableau 
         String[] words = line.split(" ") ;

         // si le mot n'est pas vide donc on cherche a l ajouter dans map ou l'incr
         for ( String word : words ) {
            if (!word.isBlank()) {
               wordMap.addOne((word));
            }
         }

      }


      // l'affichage des K mots repetés
      private static void printKwords ( StringIntMap wordMap , int k ) {

         // on cree une liste 
         List<StringIntCouple> wordList = new ArrayList<>() ;

         // on parcours tous les clés de Map
         for ( String key : wordMap.keys() ) {
            // on recupere la valeur par defaut si la clé existe ou non
            int value = wordMap.getOrDefault(key, 0) ;
            //on ajoute la cle a la liste 
            wordList.add(new StringIntCouple(key,value)) ;
         }

         // on trie la liste en comparant le nombre de occurence
         wordList.sort((c1,c2) -> Integer.compare(c2.getSnd() , c1.getSnd()) );

         // on parcours la liste pour trouver les k premiers mots et les afficher
         for ( int i=0 ; i<Math.min(k, wordList.size());i++) {
            StringIntCouple couple = wordList.get(i) ;
            System.out.println(couple.getFst()+" : "+couple.getSnd());
         }

      }


}

