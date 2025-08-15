import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.io.IOException; 


  /* ------------------ Avec la question bonus et lexercice 6 ( utilisation de TreeMap ) ------------------------ */


public class SortedWordFreq {

  public static void main (String[]  args) throws IOException {

   long startTime = System.currentTimeMillis() ;

       if (args.length < 1 ) {
          // si il y a pas d'arguments donc on retourne une erreur 
          System.out.println ("Erreur nombre d'arguments ne convient pas ") ;
          return ;
         }
        // on prend le nom de fichier de premier argument donnée 
       String filename = args[0] ;
       int k=10 ; // par defaut cest 10 dans l enoncé
       

       // dans le cas il ya pas un nombre fourni ou le nombre est non valable
       if ( args.length >=2 ) {
         try {
            k=Integer.parseInt(args[1]);
         } catch (NumberFormatException e) {
            System.out.println("Invalid number for words , We will use the by default 10 Words.");
         }
       }

       /* on utilise la strecture TreeMap a la place de StringIntMap pour eviter l utilisation 
       des fonctions de StringIntMap pour optimiser la complexiter et le temps d'execution car TreeMap est deja ordonnée */

       TreeMap<String, Integer> wordMap = new TreeMap<>() ;

       // on lit le fichier
       // dans le cas il ya pas un nombre fourni ou le nombre est non valable
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

         long endTime = System.currentTimeMillis() ;

         System.out.println ("Execution time with Millis : "+(endTime - startTime) +" ms") ;

      }


      // Classé les mots dans leurs places dans Map
      private static void processLine ( String line , TreeMap<String, Integer> wordMap) {
         // on transfome la ligne extraite en miniscule avec la supression des blancs repetés
         line = line.toLowerCase().replaceAll("[^a-zA-Z]"," " ).replaceAll("\\s+"," ");
         // on separe les separe les mots a l aide de split pour les classés dans un tableau 
         String[] words = line.split(" ") ;

         // si le mot n'est pas vide donc on cherche a l ajouter dans map ou l'incr
         for ( String word : words ) {
            if (!word.isBlank()) {
               wordMap.put(word,wordMap.getOrDefault(word,0)+1);
            }
         }

      }



      // l'affichage des K mots repetés
      private static void printKwords ( TreeMap<String, Integer> wordMap , int k ) {
         // on cree une liste de treemap tel que TreeMap et ordonné donc la liste est ordonné par ordre alphabitique
         List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordMap.entrySet()) ;
         
        
         // on trie la liste en comparant le nombre de occurence 
         wordList.sort((entry1,entry2) -> Integer.compare(entry2.getValue() , entry1.getValue()) );

         // on parcours la liste pour trouver les k premiers mots et les afficher 
         for ( int i=0 ; i<Math.min(k, wordList.size());i++) {
            // on recupere a chque fois le mot 
            Map.Entry<String, Integer> entry = wordList.get(i) ;
            // on recupere le mot qui est key et la valeur qui est l'entier
            System.out.println(entry.getKey()+" : "+entry.getValue());
         }

      }





}
