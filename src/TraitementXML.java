import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TraitementXML {
	
	private int dimensionalite;
    private String regle;
    private List<Integer> taille = new ArrayList<Integer>();
    private ArrayList<String> Voisinages = new ArrayList<String>();
    private String initState;

    public TraitementXML(String path) {
        XMLParse(path);        
    }

    private void XMLParse(String path){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(path));

            document.getDocumentElement().normalize();

            Node node = document.getElementsByTagName("Configuration").item(0); 
            if (node.getNodeType() == Node.ELEMENT_NODE) { 
       
                Element tElement = (Element) node; 
                
            	String dim = tElement
                        .getElementsByTagName("dimensionalité")
                        .item(0)
                        .getTextContent().trim();

            	this.dimensionalite = Integer.parseInt(dim);
                //Get Taille
                String Taille = tElement
                        .getElementsByTagName("Taille")
                        .item(0)
                        .getTextContent().trim();
                extraireBounds(Taille);

                // Get rule
                String rule = tElement
                        .getElementsByTagName("Regle")
                        .item(0)
                        .getTextContent().trim();
                this.regle=rule;

                // Get initial state
                String initialState = tElement
                        .getElementsByTagName("initialState")
                        .item(0)
                        .getTextContent().trim();
                this.initState=initialState;

                NodeList nodeList = tElement.getElementsByTagName("Voisinages");
                for(int i=0;i<nodeList.getLength();i++){
                    Node n = nodeList.item(i);
                    if (n.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        NodeList voisinageList = element.getElementsByTagName("Voisinage");
                        for (int j = 0; j < voisinageList.getLength(); j++) { 
                            Element voisinage = (Element) voisinageList.item(j);
                            Voisinages.add(voisinage.getTextContent().trim());
                            voisinageBDD.bddAdd(new VoisinageExtractor(voisinage.getTextContent().trim()));
                        }
                    }
                }
            }  

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void afficher(){
        System.out.println(this.regle);
    }
    
    private void extraireBounds(String t){
        String[] stringSplit=t.split(",");

        for(int i=0;i<stringSplit.length;i++){
            this.taille.add(Integer.parseInt(stringSplit[i]));
        }
    }
    
    public int getDimensionalite() {
		return dimensionalite;
	}

    public String getRegle(){
        return regle;
    }

    public List<Integer> getTaille(){
        return taille;
    }

    public ArrayList<String> getVoisinages(){
        return Voisinages;
    }

    public String getInitState() {
		return initState;
	}
    
    //Initialisation du tableaux à partir du fichier XML
    public void initTableau(TableauDND tab){
        int pourcentage;
        if (isRandom(initState)){
            pourcentage = getRandomK(initState);
            List<Integer> bounds = tab.getTabBounds();
            List<Integer> indices = new ArrayList<>(bounds.size());
            for (int i = 0; i < bounds.size(); i++) {
                indices.add(0);
            }
            initWithRandomPercentage(tab, bounds, indices, 0, pourcentage); 
        }else{
            // init tableaux
            List<List<Integer>> coords=getCoordinates(initState);
            for(List<Integer> coord : coords){
                tab.setValue(coord, 1);
            }
        }
    }

    private boolean isRandom(String initialState) {

    	return initialState.toUpperCase().contains("RANDOM");
    }
    private void initWithRandomPercentage(TableauDND tab, List<Integer> bounds, List<Integer> indices, int dim, int pourcentage) {
        if (dim == bounds.size()) {
            Random random = new Random();
            if (random.nextInt(100) < pourcentage) {
                tab.setValue(new ArrayList<>(indices), 1);
            }
            return;
        }

        for (int i = 0; i < bounds.get(dim); i++) {
            indices.set(dim, i);
            initWithRandomPercentage(tab, bounds, indices, dim + 1, pourcentage);
        }
    }

        
    private int getRandomK(String initialState) {
        Pattern randomPattern = Pattern.compile("RANDOM\\((\\d+)\\)");
        Matcher matcher = randomPattern.matcher(initialState);
        if (matcher.matches()) {
            int val = Integer.parseInt(matcher.group(1));
            if(val==0){
                throw new IllegalArgumentException("Invalid RANDOM k value invalid: " + initialState);        
            }else{
                return val;
            }
        }
        throw new IllegalArgumentException("Invalid RANDOM format: " + initialState);
    }

    private List<List<Integer>> getCoordinates(String initialState) { 
    	

    	Pattern pattern = Pattern.compile("\\((-?\\d+(,\\s*-?\\d+)*)\\)");
        Matcher matcher = pattern.matcher(initialState);
        	
        List<List<Integer>> coordinates = new ArrayList<List<Integer>>();
        while (matcher.find()) {
        	List<Integer> coord = new ArrayList<Integer>();
            String[] valeurs = matcher.group(1).split(",\\s*");
            for (String valeur : valeurs) { 
                coord.add(Integer.parseInt(valeur));
            }
            coordinates.add(coord);
        }
        
        if (coordinates.isEmpty()) {
            throw new IllegalArgumentException("Invalid coordinates format: " + initialState);
        }
        return coordinates;
    }
}