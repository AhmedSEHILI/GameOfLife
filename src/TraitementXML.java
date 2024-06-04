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
    private String regle;
    private int[] taille;
    private ArrayList<String> Voisinages;
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
            System.out.println("\nNode Name :"+ node.getNodeName()); 
            if (node.getNodeType() == Node.ELEMENT_NODE) { 
                Element tElement = (Element) node; 
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
                        Element e = (Element) n;
                        Voisinages.add(e.getElementsByTagName("Voisinage").item(0).getTextContent().trim());
                    }
                }
            }  

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void extraireBounds(String t){
        String[] stringSplit=t.split(",");
        this.taille=new int[stringSplit.length];
        for(int i=0;i<stringSplit.length;i++){
            this.taille[i]=Integer.parseInt(stringSplit[i]);
        }
    }

    public String getRegle(){
        return regle;
    }

    public int[] getTaille(){
        return taille;
    }

    public ArrayList<String> getVoisinages(){
        return Voisinages;
    }

    //Initialisation du tableaux à partir du fichier XML
    public void initTableau(TableauDND tab){
        int pourcentage;
        if ((pourcentage = getRandomK(initState))>0){
            List<Integer> bounds = tab.getTabBounds();
            List<Integer> indices = new ArrayList<>(bounds.size());
            for (int i = 0; i < bounds.size(); i++) {
                indices.add(0);
            }
            initWithRandomPercentage(tab, bounds, indices, 0, pourcentage); // Verifier si ça fonctionnes
        }else{
            // init tableaux
            List<List<Integer>> coords=getCoordinates(initState);
            for(List<Integer> coord : coords){
                tab.setValue(coord, 1);
            }
        }
    }

    // Grand merci à ChatGpt pour sa contribution 
    //Fonction qui parcours le tableaux N-Dim et initialise les cases avec une chance de pourcentage d'être 1 
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

    // private boolean isRandomPattern(String initialState) {
    //     Pattern randomPattern = Pattern.compile("RANDOM\\((\\d+)\\)");
    //     Matcher matcher = randomPattern.matcher(initialState);
    //     return matcher.matches();
    // }
        
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

    private List<List<Integer>> getCoordinates(String initialState) { /// Repeter dans deux fonction est-ce qu'on peut pas la généralisé ?
        List<List<Integer>> coordinates = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\((-?\\d+),\\s*(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(initialState);

        while (matcher.find()) {
            List<Integer> coordinate = new ArrayList<>();
            coordinate.add(Integer.parseInt(matcher.group(1)));
            coordinate.add(Integer.parseInt(matcher.group(2)));
            coordinates.add(coordinate);
        }

        if (coordinates.isEmpty()) {
            throw new IllegalArgumentException("Invalid coordinates format: " + initialState);
        }
        return coordinates;
    }
}