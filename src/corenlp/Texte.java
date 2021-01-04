package corenlp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class Texte {
	
	// Lire le fichier et obtenir une liste de phrase
	public List txt2String(File file) {
		List l= new ArrayList();
		try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while((s = br.readLine())!=null){
                l.add(s.trim());
            }
            br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return l;
	}
	
	// Filtrer tous les textes dans le même livre choisi par l'utilisateur
	public List<File> searchFiles(File folder, String keyword){
		List<File> result = new ArrayList<File>();
		if (folder.isFile())
            result.add(folder);
		File[] subFolders = folder.listFiles(new FileFilter() {
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                }
                if (file.getName().contains(keyword)) {
                    return true;
                }
                return false;
            }
        });
		if (subFolders != null) {
            for (File file : subFolders) {
                if (file.isFile()) {
                    // Si c'est un fichier,ajouter directement dans le resultat
                    result.add(file);
                } else {
                    // Si c'est un dossier，utiliser la récursivité  pour ajouter tous les fichiers dans le résultat
                    result.addAll(searchFiles(file, keyword));
                }
            }
        }
        return result;
	}
	
	// Choisir une phrase aleatoirement dans le texte
	public String getReference(List contenu) {
		
		Random r = new Random();
		int cpt = r.nextInt(contenu.size()-1);
        String reference = contenu.get(cpt).toString();
        while (reference.length()<5) {
        	cpt = r.nextInt(contenu.size()-1);
	        reference = contenu.get(cpt).toString();
        }
        return reference;
                
	}
	
	// tokeniser la phrase choisie et générer une liste de mots
	public List motsDesordre(String reference) {
		List mot = new PosTag(reference).getTextmot();
		Collections.shuffle(mot);
		return mot;
	}
	
	// verifier la reponse de l'utilisateur en comparant avec le référence
	public String verifier(String reference, String reponse) {
		if (reference.equals(reponse)) {
				return "Bravo!";
			}else {
				// comparer la longueur
				if(reference.length()!= reponse.length()) {
					return "La longueur de la phrase n'est pas correcte. La reponse est : "+reference+"\n";
				}else {
					// Appliquer les règles
					List posre= new PosTag(reponse).getPostext();
					List tokens= new PosTag(reponse).getTextmot();
					String pospre=posre.get(0).toString();
					if (pospre.equals("DEC")||pospre.equals("DEG")||pospre.equals("DEV")||pospre.equals("DER")||pospre.equals("M")||pospre.equals("MSP")||pospre.equals("SP")||pospre.equals("AS")) {
						return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";
					}else {
						for(int i=1;i<posre.size();i++){
							
							if(posre.get(i).toString().equals("IJ")) {
								if(!pospre.equals("PU")) {
									return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";
								}else {
									pospre=posre.get(i).toString();
								}
							}else if(posre.get(i).toString().equals("DEC")) {
								if(pospre.equals("NR")||pospre.equals("NT")||pospre.equals("NN")||pospre.equals("LC")||pospre.equals("DT")||pospre.equals("CD")||pospre.equals("OD")||pospre.equals("M")||pospre.equals("AD")||pospre.equals("P")||pospre.equals("CC")||pospre.equals("CS")||pospre.equals("MSP")||pospre.equals("IJ")||pospre.equals("ON")||pospre.equals("PU")||pospre.equals("JJ")||pospre.equals("LB")||pospre.equals("SB")||pospre.equals("BA")||pospre.equals("DEC")||pospre.equals("DEG")||pospre.equals("DER")||pospre.equals("DEV")||pospre.equals("SP")||pospre.equals("AS")) {
									return "Désolé, votre réponse est incorrecte! La reponse est: "+reference+"\n";
								}else {
									pospre=posre.get(i).toString();
								}
							}else if (posre.get(i).toString().equals("DEG")) {
								if(pospre.equals("AD")||pospre.equals("P")||pospre.equals("CC")||pospre.equals("CS")||pospre.equals("MSP")||pospre.equals("IJ")||pospre.equals("PU")||pospre.equals("FW")||pospre.equals("LB")||pospre.equals("SB")||pospre.equals("BA")||pospre.equals("DEC")||pospre.equals("DEG")||pospre.equals("DER")||pospre.equals("DEV")||pospre.equals("SP")||pospre.equals("AS")){
									return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";
								}else {
									pospre=posre.get(i).toString();
								}
							}else if (posre.get(i).toString().equals("DER")) {
								if(pospre.equals("VV")||pospre.equals("VA")) {
									pospre=posre.get(i).toString();	
								}else {
									return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";
								}
							}else if (posre.get(i).toString().equals("DEV")) {
								if(!pospre.equals("VV")) {
									return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";									
								}else {
									pospre=posre.get(i).toString();
								}
							}else if(posre.get(i).toString().equals("AS")) {
								if(pospre.equals("VA")||pospre.equals("NR")||pospre.equals("NT")||pospre.equals("NN")||pospre.equals("LC")||pospre.equals("PN")||pospre.equals("DT")||pospre.equals("CD")||pospre.equals("OD")||pospre.equals("M")||pospre.equals("AD")||pospre.equals("P")||pospre.equals("CC")||pospre.equals("CS")||pospre.equals("MSP")||pospre.equals("IJ")||pospre.equals("ON")||pospre.equals("PU")||pospre.equals("JJ")||pospre.equals("LB")||pospre.equals("SB")||pospre.equals("BA")||pospre.equals("DEC")||pospre.equals("DEG")||pospre.equals("DER")||pospre.equals("DEV")||pospre.equals("SP")||pospre.equals("AS")) {
									return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";
								}else {
									pospre=posre.get(i).toString();
								}
							}else if(posre.get(i).toString().equals("SP")) {	
								if(pospre.equals("DT")||pospre.equals("CS")||pospre.equals("MSP")||pospre.equals("IJ")||pospre.equals("ON")||pospre.equals("PU")||pospre.equals("FW")||pospre.equals("LB")||pospre.equals("SB")||pospre.equals("BA")||pospre.equals("SP")) {
									return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";
								}else {
									pospre=posre.get(i).toString();
								}
							}else if(posre.get(i).toString().equals("MSP")) {
								if(!pospre.equals("VV")) {
									return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";
								}else {
									pospre=posre.get(i).toString();
								}
							}else if(posre.get(i).toString().equals("ON")) {
								if(pospre.equals("VA")||pospre.equals("LC")||pospre.equals("P")||pospre.equals("MSP")||pospre.equals("IJ")||pospre.equals("JJ")||pospre.equals("FW")||pospre.equals("DEG")||pospre.equals("DEC")||pospre.equals("SP")||pospre.equals("AS")) {
									return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";
								}else {
									pospre=posre.get(i).toString();
								}
							}else if(posre.get(i).toString().equals("LB")||posre.get(i).toString().equals("SB")||posre.get(i).toString().equals("BA")) {
								if(pospre.equals("VA") ||pospre.equals("VC") ||pospre.equals("VE" )||pospre.equals("DT") ||pospre.equals("P")||pospre.equals("MSP")||pospre.equals("IJ")||pospre.equals("ON")||pospre.equals("JJ")||pospre.equals("LB")||pospre.equals("SB")||pospre.equals("BA")||pospre.equals("DEG")||pospre.equals("DEC")||pospre.equals("DER")||pospre.equals("SP")||pospre.equals("AS")) {
									return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";
								}else {
									pospre=posre.get(i).toString();
								}
							}else if(posre.get(i).toString().equals("JJ")) {
								if(pospre.equals("VA" )||pospre.equals("NR" )||pospre.equals("NN") ||pospre.equals("MSP")||pospre.equals("ON" )||pospre.equals("DER" )||pospre.equals("DEV") ||pospre.equals("SP" ))  {
									return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";
								}else {
									pospre=posre.get(i).toString();
								}
							}else if(posre.get(i).toString().equals("CC")) {
								if(pospre.equals("P")||pospre.equals("CC") ||pospre.equals("DEC") ||pospre.equals("DEG") ||pospre.equals("DER") ||pospre.equals("AS") ||pospre.equals("SP") ||pospre.equals("MSP")) {
									return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";
								}else {
									pospre=posre.get(i).toString();
								}
							}else if(posre.get(i).toString().equals("CS")) {
								if(pospre.equals("VA") ||pospre.equals("DT")||pospre.equals("CD") ||pospre.equals("OD")||pospre.equals("P")||pospre.equals("CC")||pospre.equals("CS")||pospre.equals("MSP")||pospre.equals("IJ")||pospre.equals("ON")||pospre.equals("JJ")||pospre.equals("LB")||pospre.equals("SB")||pospre.equals("BA")||pospre.equals("DEC")||pospre.equals("DEG")||pospre.equals("DER")||pospre.equals("DEV")||pospre.equals("SP")||pospre.equals("AS"))  {
									return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";
								}else {
									pospre=posre.get(i).toString();
								}
							}else if(posre.get(i).toString().equals("AD")) {
								if(pospre.equals("VA") ||pospre.equals("DT")||pospre.equals("CD") ||pospre.equals("OD")||pospre.equals("P") ||pospre.equals("CC")||pospre.equals("CS")||pospre.equals("MSP")||pospre.equals("IJ")||pospre.equals("ON")||pospre.equals("JJ")||pospre.equals("DEC")||pospre.equals("DEG")||pospre.equals("SP")||pospre.equals("AS"))  {
									return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";
								}else {
									pospre=posre.get(i).toString();
								}
							}else if(posre.get(i).toString().equals("M")) {
								if(pospre.equals("VA") ||pospre.equals("NR") ||pospre.equals("NT") ||pospre.equals("NN") ||pospre.equals("LC") ||pospre.equals("PN")||pospre.equals("M")||pospre.equals("AD")||pospre.equals("P")||pospre.equals("CS")||pospre.equals("MSP")||pospre.equals("IJ")||pospre.equals("ON")||pospre.equals("JJ")||pospre.equals("PU")||pospre.equals("LB")||pospre.equals("SB")||pospre.equals("BA")||pospre.equals("DEV")||pospre.equals("DER")||pospre.equals("SP"))  {
									return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";
								}else {
									pospre=posre.get(i).toString();
								}
							}else if(posre.get(i).toString().equals("DT")||posre.get(i).toString().equals("CD")||posre.get(i).toString().equals("OD")) {
								if(pospre.equals("VA")||pospre.equals("NR")||pospre.equals("NT")||pospre.equals("DT")||pospre.equals("OD")||pospre.equals("M")||pospre.equals("JJ")) {
									return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";
								}else {
									pospre=posre.get(i).toString();
								}
							}else if(posre.get(i).toString().equals("PN")) {
								if(pospre.equals("VA")||pospre.equals("SP")||pospre.equals("MSP")||pospre.equals("IJ")||pospre.equals("ON")||pospre.equals("JJ")) {
									return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";
								}else {
									pospre=posre.get(i).toString();
								}
							}else if(posre.get(i).toString().equals("LC")) {
								if(pospre.equals("VA")||pospre.equals("VC")||pospre.equals("VE")||pospre.equals("VV")||pospre.equals("LC")||pospre.equals("DT")||pospre.equals("CD")||pospre.equals("OD")||pospre.equals("M")||pospre.equals("AD")||pospre.equals("P")||pospre.equals("CS")||pospre.equals("MSP")||pospre.equals("IJ")||pospre.equals("ON")||pospre.equals("JJ")||pospre.equals("FW")||pospre.equals("LB")||pospre.equals("SB")||pospre.equals("BA")||pospre.equals("DEC")||pospre.equals("DEG")||pospre.equals("DER")||pospre.equals("DEV")||pospre.equals("SP")||pospre.equals("AS")) {
									return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";
								}else {
									pospre=posre.get(i).toString();
								}
							}else if(posre.get(i).toString().equals("NR")) {
								if(pospre.equals("M")||pospre.equals("DER")||pospre.equals("SP")||pospre.equals("MSP")||pospre.equals("ON")){
									return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";
								}else {
									pospre=posre.get(i).toString();
								}
							}else if(posre.get(i).toString().equals("NT")) {
								if(pospre.equals("VA")||pospre.equals("DT")||pospre.equals("MSP")||pospre.equals("IJ")||pospre.equals("ON")||pospre.equals("DEV")||pospre.equals("SP")) {
									return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";
								}else {
									pospre=posre.get(i).toString();
								}
							}else if(posre.get(i).toString().equals("NN")) {
								if(pospre.equals("SP")||pospre.equals("MSP")||pospre.equals("ON")) {
									return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";
								}else {
									pospre=posre.get(i).toString();
								}
							}else if(posre.get(i).toString().equals("VA")) {
								if(pospre.equals("LC")||pospre.equals("DT")||pospre.equals("P")||pospre.equals("CS")||pospre.equals("MSP")||pospre.equals("IJ")||pospre.equals("ON")||pospre.equals("JJ")||pospre.equals("DEG")||pospre.equals("DEC")||pospre.equals("SP")||pospre.equals("AS")){
									return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";
								}else {
									pospre=posre.get(i).toString();
								}
							}else if(posre.get(i).toString().equals("VE")) {
								if(pospre.equals("VA")||pospre.equals("MSP")||pospre.equals("IJ")||pospre.equals("ON")||pospre.equals("JJ")) {
									return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";
								}else {
									pospre=posre.get(i).toString();
								}
							}else if(posre.get(i).toString().equals("VC")) {
								if(pospre.equals("VC")||pospre.equals("VE")||pospre.equals("DT")||pospre.equals("P")||pospre.equals("CC")||pospre.equals("MSP")||pospre.equals("IJ")||pospre.equals("ON")||pospre.equals("SP")) {
									return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";
								}else {
									pospre=posre.get(i).toString();
								}
							}else if(posre.get(i).toString().equals("VV")) {
								if(pospre.equals("MSP")||pospre.equals("IJ")||pospre.equals("ON")||pospre.equals("JJ")||pospre.equals("DEG")||pospre.equals("SP")) {
									return "Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n";
								}else {
									pospre=posre.get(i).toString();
								}
							}else if(posre.get(i).toString().equals("PU")||posre.get(i).toString().equals("P")||posre.get(i).toString().equals("FW")||posre.get(i).toString().equals("etc")) {
								pospre=posre.get(i).toString();
							}
						}
						return "Bravo!";
					}
				}
				
			}
	}
}
