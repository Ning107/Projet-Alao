package corenlp;

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

    public List getPostext() {
        return postext;
    }
    public List getTextmot() {
        return textmot;
    }

    public PosTag(String text){

        CoreNLPHel coreNLPHel = CoreNLPHel.getInstance();
        StanfordCoreNLP pipeline = coreNLPHel.getPipeline();
        Annotation annotation = new Annotation(text);
        pipeline.annotate(annotation);
        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);

        List mot=new ArrayList();
        List pos=new ArrayList();
        for (CoreMap sentence:sentences){
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)){
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String postag = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                mot.add(word);
                pos.add(postag);
            }
        }
        postext = pos;
        textmot = mot;
    }

}
