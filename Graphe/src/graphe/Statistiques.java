package graphe;

public class Statistiques {
    private double degreMoyen;
    private int nbComposantes;
    private int nbNoeuds;
    private int nbAretes;
    private double diametre;
    private int nbConflits;
    
    public Statistiques(double degreMoyen, int nbComposantes, int nbNoeuds, int nbAretes, double diametre, int nbConflits) {
        this.degreMoyen = degreMoyen;
        this.nbComposantes = nbComposantes;
        this.nbNoeuds = nbNoeuds;
        this.nbAretes = nbAretes;
        this.diametre = diametre;
        this.nbConflits = nbConflits;
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
    
    public int getConflit() {
        return nbConflits;
    }
}
