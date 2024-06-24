/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphe;
import java.util.ArrayList;
/**
 *
 * @author Robi6
 */

/**
 * Représente un vol avec ses attributs et méthodes pour la coloration de graphes et les opérations d'adjacence.
 */
public class Vol {
    private String nom;
    private String code_aeroport_depart;
    private String code_aeroport_arrive;
    private double heure_depart;
    private double heure_arrive;
    private double minutes_depart;
    private double minutes_arrive;
    private double duree;
    public static int lastnum = 1;
    public int id;
    public int couleur;
    private int composante;
    private ArrayList<Vol> adjacents;
    private ArrayList<Integer> adjacentsid;
    private int distance;
    private boolean traite;
    private boolean hasname;
    
    /**
     * Construit un nouveau Vol avec un ID spécifié.
     *
     * @param num l'ID du vol.
     */
    public Vol(int num) {
        this.id = num;
        couleur = -1;
        adjacents = new ArrayList<Vol>();
        adjacentsid = new ArrayList<Integer>();
        composante = -1;
        hasname = false;
    }

    /**
     * Construit un nouveau Vol avec les attributs spécifiés.
     *
     * @param Nnom       le nom du vol.
     * @param Ncodedepart le code de l'aéroport de départ.
     * @param Ncodearrivee le code de l'aéroport d'arrivée.
     * @param heuredep   l'heure de départ.
     * @param minutedep  la minute de départ.
     * @param duree      la durée du vol en minutes.
     */
    public Vol(String Nnom, String Ncodedepart, String Ncodearrivee, int heuredep, int minutedep, int duree) {
        nom = Nnom;
        code_aeroport_depart = Ncodedepart;
        code_aeroport_arrive = Ncodearrivee;
        heure_depart = heuredep * 100 + minutedep;
        heure_arrive = (heuredep + (duree + minutedep) / 60) * 100 + (duree + minutedep) % 60;
        minutes_depart = heuredep * 60 + minutedep;
        minutes_arrive = minutes_depart + duree;
        adjacents = new ArrayList<Vol>();
        adjacentsid = new ArrayList<Integer>();
        this.duree = duree;
        id = lastnum;
        lastnum++;
        couleur = -1;
        composante = -1;
        hasname = true;
    }

    /**
     * Retourne l'heure de départ en minutes depuis 00:00.
     *
     * @return l'heure de départ en minutes.
     */
    public double getminutesdepart() {
        return minutes_depart;
    }

    /**
     * Retourne l'heure d'arrivée en minutes depuis 00:00.
     *
     * @return l'heure d'arrivée en minutes.
     */
    public double getminutes_arrive() {
        return minutes_arrive;
    }

    /**
     * Retourne la durée du vol en minutes.
     *
     * @return la durée du vol en minutes.
     */
    public double getduree() {
        return duree;
    }

    /**
     * Retourne vrai si le vol a déjà été traité (pour la coloration), sinon faux.
     *
     * @return vrai si le vol a déjà été traité, sinon faux.
     */
    public boolean gettraite() {
        return traite;
    }
    
    public boolean hasname(){
        return hasname;
    }

    /**
     * Définit l'état traité du vol.
     *
     * @param bool l'état traité à définir.
     */
    public void settraite(boolean bool) {
        traite = bool;
    }

    /**
     * Retourne la distance associée au vol (utilisée dans l'algorithme de Dijkstra).
     *
     * @return la distance.
     */
    public int getdistance() {
        return distance;
    }
    
    /**
     * Définit la distance associée au vol (utilisée dans l'algorithme de Dijkstra).
     *
     * @param distance la distance à définir.
     */
    public void setdistance(int distance) {
        this.distance = distance;
    }

    /**
     * Retourne l'ID du vol.
     *
     * @return l'ID.
     */
    public int getid() {
        return id;
    }

    /**
     * Retourne l'heure de départ au format HHMM.
     *
     * @return l'heure de départ.
     */
    public double getheuredepart() {
        return heure_depart;
    }

    /**
     * Retourne l'heure d'arrivée au format HHMM.
     *
     * @return l'heure d'arrivée.
     */
    public double getheurearrive() {
        return heure_arrive;
    }

    /**
     * Retourne le nombre de vols adjacents.
     *
     * @return le nombre de vols adjacents.
     */
    public int getnbadjacents() {
        return adjacents.size();
    }

    /**
     * Retourne le code de l'aéroport de départ.
     *
     * @return le code de l'aéroport de départ.
     */
    public String getcodedepart() {
        return code_aeroport_depart;
    }

    /**
     * Retourne le code de l'aéroport d'arrivée.
     *
     * @return le code de l'aéroport d'arrivée.
     */
    public String getcodearrive() {
        return code_aeroport_arrive;
    }

    /**
     * Retourne le nom du vol.
     *
     * @return le nom.
     */
    public String getnom() {
        return nom;
    }

    /**
     * Retourne une représentation en chaîne de caractères du vol.
     *
     * @return une représentation en chaîne de caractères.
     */
    public String toString() {
        return "nom : " + nom + " codedepart " + code_aeroport_depart + " codearrivée " + code_aeroport_arrive + " heure depart : " + heure_depart + " heure arrivee : " + heure_arrive;
    }

    /**
     * Ajoute un vol adjacent.
     *
     * @param adjacent le vol à ajouter comme adjacent.
     */
    public void addadjacent(Vol adjacent) {
        adjacents.add(adjacent);
    }

    /**
     * Vérifie si un vol est adjacent.
     *
     * @param vol le vol à vérifier.
     * @return vrai si le vol est adjacent, sinon faux.
     */
    public boolean estAdjacent(Vol vol) {
        return adjacents.contains(vol);
    }

    /**
     * Vérifie si un vol est adjacent par son ID.
     *
     * @param idvol l'ID du vol à vérifier.
     * @return vrai si le vol est adjacent, sinon faux.
     */
    public boolean estAdjacentid(int idvol) {
        return adjacentsid.contains(idvol);
    }

    /**
     * Définit la couleur du vol.
     *
     * @param couleur la couleur à définir.
     */
    public void setcouleur(int couleur) {
        this.couleur = couleur;
    }

    /**
     * Retourne la couleur du vol.
     *
     * @return la couleur.
     */
    public int getcouleur() {
        return couleur;
    }

    /**
     * Vérifie si aucun des vols adjacents n'a la couleur spécifiée.
     *
     * @param couleur la couleur à vérifier.
     * @return vrai si aucun des vols adjacents n'a la couleur, sinon faux.
     */
    public boolean possepasdeadjcouleur(int couleur) {
        boolean res = true;
        int i = 0;
        while (res && i < adjacents.size()) {
            if (adjacents.get(i).getcouleur() == couleur) {
                res = false;
            }
            i++;
        }
        return res;
    }

    /**
     * Retourne la première couleur disponible pour le vol.
     *
     * @return la première couleur disponible.
     */
    public int first_available_color() {
        int color = 0;
        boolean res = true;
        while (res) {
            color++;
            res = false;
            int i = 0;
            while (i < adjacents.size() && !res) {
                if (adjacents.get(i).getcouleur() == color) {
                    res = true;
                }
                i++;
            }
        }
        return color;
    }

    /**
     * Retourne la première couleur disponible pour le vol dans une limite maximale de couleurs.
     *
     * @param kmax le nombre maximal de couleurs.
     * @return la première couleur disponible dans la limite.
     */
    public int first_available_color_kmax(int kmax) {
        if(adjacents.size() == 0){
            return 1;
        }
        int color = 0;
        boolean res = true;
        while (res && color <= kmax) {
            color++;
            res = false;
            int i = 0;
            while (i < adjacents.size() && !res) {
                if (adjacents.get(i).getcouleur() == color) {
                    res = true;
                }
                i++;
            }
        }
        
        if (color > kmax) {
            ArrayList<Integer> liste = new ArrayList<>(kmax + 1);
            int taille = adjacents.size();
            for (int i = 0; i <= kmax; i++) {
                liste.add(0); // Ajouter 0 à chaque indice
            }
            for (int y = 0; y < taille; y++) {
                if (adjacents.get(y).getcouleur() != -1) {
                    int valeur = liste.get(adjacents.get(y).getcouleur());
                    liste.set(adjacents.get(y).getcouleur(), valeur + 1);
                }
            }

            color = 1;
            int valeurMin = liste.get(1);
            for (int i = 2; i < liste.size(); i++) {
                if (liste.get(i) < valeurMin) {
                    valeurMin = liste.get(i);
                    color = i;
                }
            }
        }

        return color;
    }

    /**
     * Retourne le Degré de Saturation (DSAT) du vol.
     *
     * @return la valeur DSAT.
     */
    public int DSAT() {
        if (couleur != -1) {
            return -1;
        }
        
        int dsat = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        for (int i = 0; i < adjacents.size(); i++) {
            if (adjacents.get(i).getcouleur() != -1 && !list.contains(adjacents.get(i).getcouleur())) {
                list.add(adjacents.get(i).getcouleur());
                dsat++;
            }
        }
        if (list.isEmpty()) {
            dsat = adjacents.size();
        }
        return dsat;
    }

    /**
     * Vérifie si la couleur assignée au vol est valide (aucun vol adjacent n'a la même couleur).
     *
     * @return vrai si la couleur est valide, sinon faux.
     */
    public boolean goodcolor() {
        for (int i = 0; i < adjacents.size(); i++) {
            if (adjacents.get(i).getcouleur() == couleur) {
                return false;
            }
        }
        return true;
    }

    /**
     * Placehoder pour le calcul de la distance entre les vols.
     *
     * @param vol le vol pour calculer la distance.
     * @return la distance.
     */
    public int distance(Vol vol) {
        int distance = -1;
        if (adjacents.size() == 0) {
            return distance;
        }
        return distance;
    }

    /**
     * Définit la composante du vol.
     *
     * @param comp la composante à définir.
     */
    public void setcomposante(int comp) {
        composante = comp;
    }

    /**
     * Retourne la composante du vol.
     *
     * @return la composante.
     */
    public int getcomposante() {
        return composante;
    }

    /**
     * Définit récursivement la composante du vol et de ses vols adjacents.
     *
     * @param comp la composante à définir.
     * @return vrai si la composante a été définie, sinon faux.
     */
    public boolean composante(int comp) {
        if (composante == -1) {
            int i = 0;
            composante = 0;
            while (i < adjacents.size()) {
                adjacents.get(i).composante(comp);
                i++;
            }
            composante = comp;
            return true;
        }
        return false;
    }

    /**
     * Effectue l'algorithme de Dijkstra sur le vol.
     */
    public void Dijkstra() {
        settraite(true);
        int x = distance;
        int nb = 0;
        for (int i = 0; i < adjacents.size(); i++) {
            Vol vol = adjacents.get(i);
            if (!vol.gettraite()) {
                if (x + 1 < vol.getdistance()) {
                    vol.setdistance(x + 1);
                    nb++;
                }
            }
        }
    }

    /**
     * Retourne le vol adjacent à l'indice spécifié.
     *
     * @param indice l'indice.
     * @return le vol adjacent.
     */
    public Vol getAdjacentindice(int indice) {
        return adjacents.get(indice);
    }

    /**
     * Retourne l'indice de la première couleur disponible avec une limite maximale.
     *
     * @param kmax le nombre maximal de couleurs.
     * @return l'indice de la première couleur disponible.
     */
    public int firstcoloravailable(int kmax) {
        //System.out.println("1");
        if(kmax == 0){
            return 1;
        }
        ArrayList<Integer> liste = new ArrayList<>(kmax + 1);
        int taille = adjacents.size();
        for (int i = 0; i <= kmax; i++) {
            liste.add(0); // Ajouter 0 à chaque indice
        }
        //System.out.println("2");
        for (int y = 0; y < taille; y++) {
            if (adjacents.get(y).getcouleur() != -1) {
                int valeur = liste.get(adjacents.get(y).getcouleur());
                liste.set(adjacents.get(y).getcouleur(), valeur + 1);
            }
        }
        //System.out.println("3");
        
        int indiceMin = 1;
        int valeurMin = liste.get(1);
        for (int i = 2; i < liste.size(); i++) {
            if (liste.get(i) < valeurMin) {
                valeurMin = liste.get(i);
                indiceMin = i;
            }
        }
        
        return indiceMin;
    }
    
    /**
     * Retourne la liste des sommets adjacents à ce sommet.
     *
     * @return une liste des sommets adjacents (instances de Vol).
     */
    public ArrayList<Vol> getAdjacents() {
        return adjacents;
    }
    
    /**
     * Vérifie si l'un des sommets adjacents a la couleur spécifiée.
     *
     * @param color la couleur à vérifier.
     * @return true si l'un des sommets adjacents a la couleur spécifiée, false sinon.
     */
    public boolean adjacentscontainscolor(int color) {
        boolean res = false;
        for (Vol vol : adjacents) {
            if (vol.getcouleur() == color) {
                res = true;
            }
        }
        return res;
    }
    
    /**
     * Compte le nombre de sommets adjacents ayant un voisin de la couleur spécifiée.
     *
     * @param color la couleur à vérifier parmi les voisins des sommets adjacents.
     * @return le nombre de sommets adjacents ayant un voisin de la couleur spécifiée.
     */
    public int nbadjacnetsneighbors(int color) {
        int res = 0;
        for (Vol vol : adjacents) {
            if (vol.adjacentscontainscolor(color)) {
                res++;
            }
        }
        return res;
    }

   
    
    
   
    
    
}