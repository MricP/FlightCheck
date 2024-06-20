/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Robi6 Classe ListeVols : représente une liste d'objects de la classe
 * Vol
 *
 */
public class ListeVols {

    private ArrayList<Vol> tab;
    private int nbarrete = 0;
    private int nbcomposante;
    private int diametre;
    private int kmax;
    private boolean havekmax;
    
    /**
     * Constructeur de la classe ListeVols. Initialise la liste de vols et les
     * paramètres de gestion.
     */
    public ListeVols() {
        tab = new ArrayList<Vol>();
        nbcomposante = -1;
        diametre = -1;
        havekmax = false;
    }

    /**
     * Retourne la valeur de kmax.
     *
     * @return La valeur de kmax.
     */
    public int getkmax() {
        return kmax;
    }

    /**
     * Vérifie si kmax a été défini.
     *
     * @return true si kmax a été défini, sinon false.
     */
    public boolean havekmax() {
        return havekmax;
    }
    
    /**
     * Définit la valeur de kmax.
     *
     * @param kmax La valeur de kmax à définir.
     */
    public void setkmax(int kmax) {
        this.kmax = kmax;
        
    }
    
    /**
     * Retourne le nombre d'arêtes.
     *
     * @return Le nombre d'arêtes.
     */
    public int getnbarrte() {
        return nbarrete;
    }

    /**
     * Ajoute un vol à la liste.
     *
     * @param membreAj Le vol à ajouter.
     * @return true si l'ajout est réussi, sinon false.
     */
    public boolean ajMembre(Vol membreAj) {
        boolean res = false;
        if (membreAj != null) {
            tab.add(membreAj);
            res = true;
        }
        return res;
    }

    /**
     * Définit la valeur de havekmax.
     *
     * @param bool La valeur de havekmax à définir.
     */
    public void sethavekmax(boolean bool) {
        havekmax = bool;
    }

    /**
     * Accède à un vol par son nom.
     *
     * @param nom Le nom du vol à accéder.
     * @return Le vol correspondant au nom, ou null s'il n'existe pas.
     */
    public Vol accesMembrenom(String nom) {
        Vol membre = null;
        for (int i = 0; i < tab.size(); i++) {
            if (tab.get(i).getnom().equals(nom)) {
                membre = tab.get(i);
            }
        }
        return membre;
    }

    /**
     * Accède à un vol par son numéro.
     *
     * @param num Le numéro du vol à accéder.
     * @return Le vol correspondant au numéro, ou null s'il n'existe pas.
     */
    public Vol accesMembrenum(int num) {
        Vol membre = null;
        for (int i = 0; i < tab.size(); i++) {
            if (tab.get(i).getid() == num) {
                membre = tab.get(i);
            }
        }
        return membre;
    }

    /**
     * Définit le nombre d'arêtes.
     *
     * @param nbarrete Le nombre d'arêtes à définir.
     */
    public void setnbarrete(int nbarrete) {
        this.nbarrete = nbarrete;
    }

    /**
     * Retourne la taille de la liste de vols.
     *
     * @return La taille de la liste de vols.
     */
    public int taille() {
        return tab.size();
    }

    /**
     * Affiche les vols de la liste.
     */
    public void tostring() {
        System.out.println("Les vols de la liste : ");
        for (int i = 0; i < tab.size(); i++) {
            System.out.println(tab.get(i).toString());
        }
    }

    /**
     * Retourne le vol à un indice donné.
     *
     * @param indice L'indice du vol à accéder.
     * @return Le vol correspondant à l'indice.
     */
    public Vol getVolindice(int indice) {
        return tab.get(indice);
    }

    /**
     * Retourne le vol avec un numéro donné.
     *
     * @param numero Le numéro du vol à accéder.
     * @return Le vol correspondant au numéro, ou null s'il n'existe pas.
     */
    public Vol getVolnumero(int numero) {
        for (int i = 0; i < tab.size(); i++) {
            if (tab.get(i).getid() == numero) {
                return tab.get(i);
            }
        }
        return null;
    }
    
    public void DsaturMAX() {
        for (int y = 0; y < tab.size(); y++) {
            int max = -1;
            int indice = -1;
            for (int i = 0; i < tab.size(); i++) {
                if (tab.get(i).DSAT() > max) {
                    indice = i;
                    max = tab.get(i).DSAT();
                }
            }

            tab.get(indice).setcouleur(tab.get(indice).first_available_color_kmax(kmax));
        }
    }
    

    /**
     * Incrémente le nombre d'arêtes.
     */
    public void addaerrete() {
        nbarrete++;
    }

    /**
     * Comparator pour trier les vols en fonction du nombre d'adjacents.
     */
    Comparator<Vol> comparateur = new Comparator<Vol>() {
        public int compare(Vol o1, Vol o2) {
            return Integer.compare(o2.getnbadjacents(), o1.getnbadjacents()); // Compare dans l'ordre décroissant
        }
    };

    /**
     * Applique l'algorithme Welsh-Powell pour colorier les vols avec un maximum
     * de kmax couleurs.
     *
     * @return Le nombre de couleurs utilisées.
     */
    public int MAXWelshPowell() {
        Collections.sort(tab, comparateur);

        int maxcoul;
        if (havekmax) {
            maxcoul = kmax;
        } else {
            maxcoul = Integer.MAX_VALUE;
        }
        int nbCouleurs = 1;
        boolean var2 = true;
        while (var2 && (nbCouleurs <= maxcoul)) {
            int i = 0;
            boolean var = true;
            while (i < tab.size() && var) {
                if (tab.get(i).couleur == -1) {
                    var = false;
                } else {
                    i++;
                }
            }

            if (i < tab.size()) {
                tab.get(i).setcouleur(nbCouleurs);
                i++;
            } else {
                var2 = false;
            }

            while (i < tab.size()) {
                if (tab.get(i).possepasdeadjcouleur(nbCouleurs) && (tab.get(i).getcouleur() == -1)) {
                    tab.get(i).setcouleur(nbCouleurs);
                }
                i++;
            }
            nbCouleurs++;
        }

        if (var2) {
            int i = 0;
            int couleur;
            while (i < tab.size()) {
                if (tab.get(i).getcouleur() == -1) {
                    if (havekmax){
                        couleur = tab.get(i).zzz(kmax);
                    }else{
                        couleur = tab.get(i).zzz(0);
                    }
                    tab.get(i).setcouleur(couleur);
                }
                i++;
            }
        }

        return nbCouleurs;
    }

    /**
     * Applique l'algorithme Welsh-Powell pour colorier les vols.
     *
     * @return Le nombre de couleurs utilisées.
     */
    public int WelshPowell() {
        Collections.sort(tab, comparateur);

        int nbCouleurs = 1;
        boolean var2 = true;
        while (var2) {
            int i = 0;
            boolean var = true;
            while (i < tab.size() && var) {
                if (tab.get(i).couleur == -1) {
                    var = false;
                } else {
                    i++;
                }
            }

            if (i < tab.size()) {
                tab.get(i).setcouleur(nbCouleurs);
                i++;
            } else {
                var2 = false;
            }

            while (i < tab.size()) {
                if (tab.get(i).possepasdeadjcouleur(nbCouleurs) && (tab.get(i).getcouleur() == -1)) {
                    tab.get(i).setcouleur(nbCouleurs);
                }
                i++;
            }
            nbCouleurs++;
        }

        return nbCouleurs;
    }

    /**
     * Applique une coloration aléatoire aux vols.
     *
     * @param max Le nombre maximum de couleurs.
     */
    public void RandomColoration(int max) {
        int random = 1;
        for (int i = 0; i < tab.size(); i++) {
            tab.get(i).setcouleur((random % max) + 1);
            random++;
        }
    }

    /**
     * Applique une coloration gloutonne aux vols.
     */
    public void GreedyColor() {
        for (int i = 0; i < tab.size(); i++) {
            if (havekmax) {
                tab.get(i).setcouleur(tab.get(i).first_available_color_kmax(kmax));
            } else {
                tab.get(i).setcouleur(tab.get(i).first_available_color());
            }
        }
    }

    /**
     * Applique l'algorithme DSATUR pour colorier les vols.
     */
    public void Dsatur() {
        for (int y = 0; y < tab.size(); y++) {
            int max = -1;
            int indice = -1;
            for (int i = 0; i < tab.size(); i++) {
                if (tab.get(i).DSAT() > max) {
                    indice = i;
                    max = tab.get(i).DSAT();
                }
            }

            tab.get(indice).setcouleur(tab.get(indice).first_available_color());
        }
    }

    /**
     * Retourne la liste des vols.
     *
     * @return La liste des vols.
     */
    public ArrayList getList() {
        return tab;
    }

    /**
     * Retourne le nombre de conflits de couleur entre les vols.
     *
     * @return Le nombre de conflits de couleur.
     */
    public int getnbconflit() {
        int nbconflit = 0;
        for (int i = 0; i < tab.size(); i++) {
            Vol v = tab.get(i);
            int couleurv = v.getcouleur();
            for (int y = 0; y < v.getnbadjacents(); y++) {
                if (v.getAdjacentindice(y).getcouleur() == couleurv) {
                    nbconflit++;
                }
            }
        }
        return nbconflit / 2;
    }

    /**
     * Vérifie si la coloration des vols est correcte.
     *
     * @return true si la coloration est correcte, sinon false.
     */
    public boolean goodcoloration() {
        for (int i = 0; i < tab.size(); i++) {
            if (!tab.get(i).goodcolor()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retourne la couleur maximale utilisée.
     *
     * @return La couleur maximale utilisée.
     */
    public int maxcouleur() {
        int max = -1;
        for (int i = 0; i < tab.size(); i++) {
            if (tab.get(i).getcouleur() > max) {
                max = tab.get(i).getcouleur();
            }
        }
        return max;
    }

    /**
     * Affiche les couleurs des vols.
     */
    public void viewcolor() {
        for (int i = 0; i < tab.size(); i++) {
            System.out.println(tab.get(i).getid() + " " + tab.get(i).getcouleur());
        }
    }

    /**
     * Réinitialise la couleur de tous les vols à la valeur par défaut (-1).
     */
    public void setcouleurdefault() {
        for (int i = 0; i < tab.size(); i++) {
            tab.get(i).setcouleur(-1);
        }
    }

    /**
     * Retourne le degré moyen des vols.
     *
     * @return Le degré moyen des vols.
     */
    public double getdegremoyen() {
        double total = 0;
        for (int i = 0; i < tab.size(); i++) {
            total += tab.get(i).getnbadjacents();
        }
        return total / tab.size();
    }

    /**
     * Retourne le nombre de composants connexes.
     *
     * @return Le nombre de composants connexes.
     */
    public int getnbcomposante() {
        if (nbcomposante != -1) {
            return nbcomposante;
        }
        int nb = 1;
        int y = 1;
        for (int i = 0; i < tab.size(); i++) {
            if (tab.get(i).composante(y)) {
                y++;
            }
        }
        nbcomposante = y - 1;
        return nbcomposante;
    }

    /**
     * Retourne le diamètre du graphe.
     *
     * @return Le diamètre du graphe.
     */
    public int getdiametre() {
        if (diametre != -1) {
            return diametre;
        }
        int maxdiametre = 0;
        if (nbcomposante == -1) {
            getnbcomposante();
        }
        for (int i = 0; i < tab.size(); i++) {
            for (int y = i + 1; y < tab.size(); y++) {
                int diametre = Dijkstra(tab.get(i), tab.get(y));
                if (diametre > maxdiametre) {
                    maxdiametre = diametre;
                }
            }
        }
        return maxdiametre;
    }

    /**
     * Applique l'algorithme de Dijkstra pour trouver le plus court chemin entre
     * deux vols.
     *
     * @param vol1 Le vol de départ.
     * @param vol2 Le vol d'arrivée.
     * @return La distance entre les deux vols.
     */
    public int Dijkstra(Vol vol1, Vol vol2) {
        if (vol1.getcomposante() != vol2.getcomposante()) {
            return 0;
        }

        for (int i = 0; i < tab.size(); i++) {
            tab.get(i).setdistance(9999);
            tab.get(i).settraite(false);
        }

        tab.get(tab.indexOf(vol1)).setdistance(0);
        tab.get(tab.indexOf(vol1)).settraite(true);
        vol1.Dijkstra();
        while (!vol2.gettraite()) {
            int maxdistance = Integer.MAX_VALUE;
            Vol sommetmin = vol1;

            for (int y = 0; y < tab.size(); y++) {
                if (!tab.get(y).gettraite() && (tab.get(y).getcomposante() == vol1.getcomposante())) {
                    if (tab.get(y).getdistance() <= maxdistance) {
                        maxdistance = tab.get(y).getdistance();
                        sommetmin = tab.get(y);
                    }
                }
            }
            sommetmin.Dijkstra();
        }

        return vol2.getdistance();
    }

    /**
     * Retourne le nombre de composants pour une composante donnée.
     *
     * @param composante La composante à vérifier.
     * @return Le nombre de composants.
     */
    public int getnbcomposantede(int composante) {
        int cpt = 0;
        for (int i = 0; i < tab.size(); i++) {
            if (tab.get(i).getcomposante() == composante) {
                cpt++;
            }
        }
        return cpt;
    }

    /**
     * Affiche le numéro de la composante pour chaque vol.
     */
    public void shownumcomposante() {
        for (int i = 0; i < tab.size(); i++) {
            Vol v = tab.get(i);
            // La méthode affiche chaque vol avec son numéro de composante
            System.out.println(v.getid() + " " + v.getcomposante());
        }
    }

    /**
     * Retourne la liste des vols sous forme d'ArrayList.
     *
     * @return La liste des vols.
     */
    public ArrayList<Vol> getArraylist() {
        return tab;
    }

    /**
     * Retourne cette instance de ListeVols.
     *
     * @return Cette instance de ListeVols.
     */
    public ListeVols getListVols() {
        return this;
    }

    /**
     * Retourne un vol par son ID.
     *
     * @param id L'ID du vol à accéder.
     * @return Le vol correspondant à l'ID, ou null s'il n'existe pas.
     */
    public Vol getVolId(int id) {
        for (Vol vol : tab) {
            if (vol.getid() == id) {
                return vol;
            }
        }
        return null;
    }

    /**
     * Retourne une liste des couleurs des vols.
     *
     * @return La liste des couleurs des vols.
     */
    public ArrayList<Integer> getcouleurs() {
        ArrayList<Integer> list = new ArrayList<>();
        for (Vol vol : tab) {
            list.add(vol.getcouleur());
        }
        return list;
    }

    /**
     * Assigne des couleurs aux vols depuis une liste donnée.
     *
     * @param list La liste des couleurs à assigner.
     */
    public void adresscouleurs(ArrayList<Integer> list) {
        int i = 0;
        for (Vol vol : tab) {
            if (i < list.size()) {
                vol.setcouleur(list.get(i));
                i++;
            } else {
                break;
            }
        }
    }
    
    /**
    * Retourne une liste de vols associés à un code aéroport donné.
    *
    * @param code Le code de l'aéroport pour lequel les vols sont recherchés.
    * @return Un tableau d'objets représentant les informations des vols correspondants.
    *         Chaque ligne du tableau contient les informations suivantes :
    *         <ul>
    *             <li>Nom du vol</li>
    *             <li>Code de départ</li>
    *             <li>Code d'arrivée</li>
    *             <li>Heure de départ (HH:MM)</li>
    *             <li>Heure d'arrivée (HH:MM)</li>
    *             <li>Durée du vol</li>
    *             <li>Couleur associée au vol</li>
    *         </ul>
    */
    public Object[][] getlistvolsfromaero(String code){
        Object[][] tableau = new Object[tab.size()][7];
        int cpt =0;
        for(int i=0; i< tab.size(); i++){
            Vol vol = tab.get(i); // 
            if(vol.getcodedepart().equals(code) || vol.getcodearrive().equals(code) ){
                tableau[cpt][0] = vol.getnom();
                tableau[cpt][1] = vol.getcodedepart();
                tableau[cpt][2] = vol.getcodearrive();
                tableau[cpt][3] = (int)(vol.getminutesdepart() / 60) + ":" + (int)(vol.getminutesdepart() % 60);
                tableau[cpt][4] = (int)(vol.getminutes_arrive() / 60) + ":" + (int)(vol.getminutes_arrive() % 60);
                tableau[cpt][5] = vol.getduree();
                tableau[cpt][6] = vol.getcouleur();
                cpt++;
            }
        }
        return tableau;
    }
}
