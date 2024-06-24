/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphe;

/**
 *
 * @author Robi6
 */


/**
 * Classe représentant le modèle de l'application.
 * Cette classe contient les listes des aéroports et des vols, ainsi que la référence à l'objet principal de l'application.
 */
public class Modele {

    private ListeAeroport listeAeroport;
    private ListeVols listeVolCarte;
    private ListeVols listeVolGraphe;
    private GraphController main;

    /**
     * Constructeur par défaut de la classe Modele.
     * Initialise l'objet principal de l'application.
     */
    public Modele() {
        // Initialisation par défaut si nécessaire
        main = new GraphController();
    }
    
    /**
     * Obtient la liste des aéroports.
     * 
     * @return la liste des aéroports
     */
    public ListeAeroport getListeAeroport() {
        return listeAeroport;
    }

    /**
     * Définit la liste des aéroports.
     * 
     * @param listeAeroport la nouvelle liste des aéroports
     */
    public void setListeAeroport(ListeAeroport listeAeroport) {
        this.listeAeroport = listeAeroport;
    }

    /**
     * Obtient la liste des vols pour la carte.
     * 
     * @return la liste des vols pour la carte
     */
    public ListeVols getListeVolCarte() {
        return listeVolCarte;
    }

    /**
     * Définit la liste des vols pour la carte.
     * 
     * @param listeVolCarte la nouvelle liste des vols pour la carte
     */
    public void setListeVolCarte(ListeVols listeVolCarte) {
        this.listeVolCarte = new ListeVols();
        this.listeVolCarte = listeVolCarte;
    }

    /**
     * Obtient la liste des vols pour le graphe.
     * 
     * @return la liste des vols pour le graphe
     */
    public ListeVols getListeVolGraphe() {
        return listeVolGraphe;
    }

    /**
     * Définit la liste des vols pour le graphe.
     * 
     * @param listeVolGraphe la nouvelle liste des vols pour le graphe
     */
    public void setListeVolGraphe(ListeVols listeVolGraphe) {
        this.listeVolGraphe = new ListeVols();
        this.listeVolGraphe = listeVolGraphe;
    }

    /**
     * Obtient l'objet principal de l'application.
     * 
     * @return l'objet principal de l'application
     */
    public GraphController getMain() {
        return main;
    }
}