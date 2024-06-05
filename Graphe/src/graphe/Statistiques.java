package graphe;

/**
 * La classe Statistiques représente les statistiques d'un graphe.
 * Elle contient des informations telles que le degré moyen, le nombre de composantes,
 * le nombre de nœuds, le nombre d'arêtes, le diamètre et le nombre de conflits.
 */
public class Statistiques {

    private double degreMoyen;
    private int nbComposantes;
    private int nbNoeuds;
    private int nbAretes;
    private double diametre;
    private int nbConflits;

    /**
     * Constructeur de la classe Statistiques.
     * 
     * @param degreMoyen   le degré moyen du graphe
     * @param nbComposantes le nombre de composantes dans le graphe
     * @param nbNoeuds     le nombre de nœuds dans le graphe
     * @param nbAretes     le nombre d'arêtes dans le graphe
     * @param diametre     le diamètre du graphe
     * @param nbConflits   le nombre de conflits dans le graphe
     */
    public Statistiques(double degreMoyen, int nbComposantes, int nbNoeuds, int nbAretes, double diametre, int nbConflits) {
        this.degreMoyen = degreMoyen;
        this.nbComposantes = nbComposantes;
        this.nbNoeuds = nbNoeuds;
        this.nbAretes = nbAretes;
        this.diametre = diametre;
        this.nbConflits = nbConflits;
    }

    /**
     * Retourne le degré moyen du graphe.
     * 
     * @return le degré moyen
     */
    public double getDegreMoyen() {
        return degreMoyen;
    }

    /**
     * Retourne le nombre de composantes dans le graphe.
     * 
     * @return le nombre de composantes
     */
    public int getNbComposantes() {
        return nbComposantes;
    }

    /**
     * Retourne le nombre de nœuds dans le graphe.
     * 
     * @return le nombre de nœuds
     */
    public int getNbNoeuds() {
        return nbNoeuds;
    }

    /**
     * Retourne le nombre d'arêtes dans le graphe.
     * 
     * @return le nombre d'arêtes
     */
    public int getNbAretes() {
        return nbAretes;
    }

    /**
     * Retourne le diamètre du graphe.
     * 
     * @return le diamètre
     */
    public double getDiametre() {
        return diametre;
    }

    /**
     * Retourne le nombre de conflits dans le graphe.
     * 
     * @return le nombre de conflits
     */
    public int getConflit() {
        return nbConflits;
    }
}