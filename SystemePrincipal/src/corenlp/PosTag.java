package corenlp;  // on utilise le module de corenlp

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import java.util.ArrayList;
import java.util.List;

public class PosTag {
	private List postext;
	private List textmot;

    public List getPostext() {  // pour récupérer une liste contenante des postages, l'ordre pareil avec l'ordre des tokens de la phrase
        return postext;
    }
    public List getTextmot() {  // pour récupérer une liste contenante des tokens de phrase, on ne change pas l'ordre 
        return textmot;
    }

    public PosTag(String text){ 

        CoreNLPHel coreNLPHel = CoreNLPHel.getInstance();
        StanfordCoreNLP pipeline = coreNLPHel.getPipeline();  // on prend le pipelline de corenlp
        Annotation annotation = new Annotation(text);         // on crée un objet pour annoter
        pipeline.annotate(annotation);                        // l'objet est analysé par le pipeline
        
        
        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);   //récupérer le résultat et enregistrer dans la carte 
        List mot=new ArrayList();
        List pos=new ArrayList();
        for (CoreMap sentence:sentences){                                                      // parcourir le coremap
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)){      // parcourir la liste, chaque token est un CoreLabel
                String word = token.get(CoreAnnotations.TextAnnotation.class);                 //récupérer les tokes de cette phrase
                String postag = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);       //récupérer les postags de chaque tokens
                mot.add(word);                                                                 // ajouter dans la liste
                pos.add(postag);                                                               // ajouter dans la liste
            }
        }
        postext = pos;
        textmot = mot;
    }

}
