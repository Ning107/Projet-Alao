
package htmlutiltest;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class Crawler_corpus {
    @Test
    public void test() throws IOException {
        //Créer un nouvel objet client de navigateur qui simule le navigateur Google Chrome
        final WebClient webClient = new WebClient(BrowserVersion.CHROME);
		//Réglage des fonctions diffentes
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(true); 
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());

        HtmlPage page = null;
        
        //Créer une boucle qui peut Crawler automatiquement le Web
        for (int a = 1; a < 5; a++) {
        	for (int b = 1; b < 9; b++) {
        		for (int c = 1; c < 10; c++) {
        			for (int d = 1; d < 10; d++) {
        				
        				try {
        		            for (int i = 0; i < 140; i++) {

        		            }
        		            //changer le nombre de site pour capturer le contenu
        		            page = webClient.getPage("http://www.aihanyu.org/api/v2/topic_detail.aspx?info="+a+"_"+b+"_"+c+"&start="+d+"&size=1");
        		            System.out.println(a);
        		            System.out.println(b);
        		            System.out.println(c);
        		            System.out.println(d);
        		        
        		        } catch (Exception e) {
        		            e.printStackTrace();
        		        } finally {
        		            webClient.close();
        		        }

        		        webClient.waitForBackgroundJavaScript(30000);

        		        String pageXml = page.asXml();

        		        //L'opération de la chaîne et l'opération de Crawler
        		        Document document = Jsoup.parse(pageXml);
        		        String contenu = document.body().text();
     
        		        int length=contenu.length();
        		        
        		        if(length>20){
        		        	//Supprimer le contenu aptès des mots clés
            		        int loc = contenu.indexOf("HSKstats");
            		        String newStr = contenu.substring(0,loc);
            		        
            		        
            		        //Changer l'élement indésirable
            		        String cms =newStr.replace("{\"data\":[{\"Sens\":[", "").replace("\"come\":", "").replace("\"","").replace("]","");
            		        String[] countryStr = cms.split(",");
            		        
            		        
            		        //Écrire le contenu lu dans un nouveau fichier           		        
            		        String line="";		
            		        for(int i = 0;i < countryStr.length ; i++){
            		        	System.out.println(countryStr[i]);
            		        	if (countryStr[i]!=null) {
            		        		FileUtils.writeStringToFile(new File("test.txt"),countryStr[i]+ "\r\n",true);
                		        	line = countryStr[i];
            		        	}
            		        	
            		        }

            		        	
            		        //Nommage automatique des fichiers et prévention des erreurs
            		        try {
            		        	FileUtils.moveFile(
            		        			FileUtils.getFile("test.txt"), 
                		        		FileUtils.getFile(line+".txt"));
            		         }
            		         catch(IOException e) 
            		         {} ;

        		        }
        		        
        			}
        			}
        		}
        	}
        }
}




