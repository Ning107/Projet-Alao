package corenlp;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class CoreNLPHel {
//	Mettre le fichier de configuration dans le dossier
	private static CoreNLPHel instance = new CoreNLPHel();
    private StanfordCoreNLP pipeline;
    private CoreNLPHel(){
        String props="CoreNLP-chinese.properties";
        pipeline = new StanfordCoreNLP(props);
    };
    public static CoreNLPHel getInstance(){
        return instance;
    }
    public StanfordCoreNLP getPipeline(){
        return pipeline;
    }

}
