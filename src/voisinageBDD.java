import java.util.*;
import javax.swing.Timer;

import java.awt.event.*;

public class voisinageBDD {

    static Map<String, VoisinageExtractor> map = new HashMap<>();

    static public void bddAdd(VoisinageExtractor V) {
        map.put(V.getNom(), V);
    }

    static public VoisinageExtractor bddGet(String nomVoisinage) {
        return map.get(nomVoisinage);
    }

}

