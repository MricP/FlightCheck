/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphe;

/**
 *
 * @author Robi6
 */
public class Modele {

    private ListeAeroport listeAeroport;
    private ListeVols listeVolCarte;
    private ListeVols listeVolGraphe;
    private Main main;

    public Modele() {
        // Initialisation par défaut si nécessaire
        main = new Main();
    }
    
    public ListeAeroport getListeAeroport() {
        return listeAeroport;
    }

    public void setListeAeroport(ListeAeroport listeAeroport) {
        this.listeAeroport = listeAeroport;
    }

    public ListeVols getListeVolCarte() {
        return listeVolCarte;
    }

    public void setListeVolCarte(ListeVols listeVolCarte) {
        this.listeVolCarte = new ListeVols();
        this.listeVolCarte = listeVolCarte;
    }

    public ListeVols getListeVolGraphe() {
        return listeVolGraphe;
    }

    public void setListeVolGraphe(ListeVols listeVolGraphe) {
        this.listeVolGraphe = new ListeVols();
        this.listeVolGraphe = listeVolGraphe;
    }

    public Main getMain() {
        return main;
    }
}
