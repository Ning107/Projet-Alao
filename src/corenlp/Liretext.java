package corenlp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;


public class Liretext {
	public static void main(String[] args){
		// Demander à l'utilisateur de saisir un nom de livre ex "跟我学汉语"
		String text;
		Scanner ip = new Scanner(System.in);
		System.out.print("Saisir un livre: ");
		text = ip.nextLine();
		File path = new File("./Corpus/");
		Texte t2 =new Texte();
		List<File> files = t2.searchFiles(path, text);
		int score = 0;
		
		// selon le livre saisi, le système va prendre 5 différents textes aleatoirement.
		if(files.size()>=5) {
			Random r = new Random();
			Vector<Integer> v = new Vector<Integer>();
			int count = 0;
			
			// Générer un groupe de questions qui contient 5 petites questions
			while(count < 5) {
				int number = r.nextInt(files.size());
				if(!v.contains(number)) {
					v.add(number);
	                count++;
	                File file = files.get(number);
	                Texte t1 = new Texte();
	                List contenu = t1.txt2String(file);
	                
//	                Choisir une phrase aléatoirement dans le texte
	                int cpt = r.nextInt(contenu.size()-1);
	                String reference = contenu.get(cpt).toString();
	                while (reference.length()<5) {
	                	cpt = r.nextInt(contenu.size());
		                reference = contenu.get(cpt).toString();
	                }

	                // tokeniser la phrase choisie et générer une liste de mots
	                List mot = new PosTag(reference).getTextmot();
	                List<String> motCopy = new ArrayList<String>();
	                motCopy.addAll(mot);
	                       
	                Collections.shuffle(mot);// mettre en désordre les tokens
	                contenu.set(cpt, mot);
	                System.out.println("Question "+count+" : ");
					for(Object phrase : contenu) {
	                	System.out.println(phrase);
	                }
					
					// Collecter la réponse de l'utilisateur
					String reponse;
					System.out.print("Donnez votre reponse: ");
					final Scanner input = new Scanner(System.in);
					reponse = input.nextLine();
					
					// Comparer la réponse et le référence et si c'est correct, le score va être accumulé.
					Texte T1 = new Texte();
					String res = T1.verifier(reference, reponse);
					System.out.println(res);
					if(res == "Bravo!") {
						score +=1;
					}	
				}
			}
			
		}
		System.out.println("Votre score final :" + score);
		ip.close();
	}       
  }

