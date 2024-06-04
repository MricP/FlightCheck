package graphe;

public class Statistiques {
    private double degreMoyen;
    private int nbComposantes;
    private int nbNoeuds;
    private int nbAretes;
    private double diametre;
    
    public Statistiques(double degreMoyen, int nbComposantes, int nbNoeuds, int nbAretes, double diametre) {
        this.degreMoyen = degreMoyen;
        this.nbComposantes = nbComposantes;
        this.nbNoeuds = nbNoeuds;
        this.nbAretes = nbAretes;
        this.diametre = diametre;
    }
    
    public double getDegreMoyen() {
        return degreMoyen;
    }
    
    public int getNbComposantes() {
        return nbComposantes;
    }

    public int getNbNoeuds() {
        return nbNoeuds;
    }

    public int getNbAretes() {
        return nbAretes;
    }

    public double getDiametre() {
        return diametre;
    }
}
