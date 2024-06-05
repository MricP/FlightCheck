/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphe;
import java.util.ArrayList;

/**
 *
 * @author Robi6
 * Classe ListeAeroport : 
 * repésente une liste qui stocke les aéroports 
 * 
 * 
 */
/**
 * La classe ListeAeroport gère une collection d'objets Aeroport.
 */
public class ListeAeroport {
    private ArrayList<Aeroport> tab;

    /**
     * Constructeur de la classe ListeAeroport.
     * Initialise la liste des aéroports.
     */
    public ListeAeroport() {
        tab = new ArrayList<Aeroport>();
    }

    /**
     * Ajoute un aéroport à la liste.
     * 
     * @param aeroAj l'aéroport à ajouter
     * @return true si l'ajout est réussi, false sinon
     */
    public boolean ajAeroport(Aeroport aeroAj) {
        boolean res = false;
        if (aeroAj != null) {
            tab.add(aeroAj);
            res = true;
        }
        return res;
    }

    /**
     * Accède à un aéroport dans la liste par son code.
     * 
     * @param num le code de l'aéroport à rechercher
     * @return l'aéroport correspondant au code, ou null s'il n'est pas trouvé
     */
    public Aeroport accesAeroport(String num) {
        Aeroport aero = null;
        for (int i = 0; i < tab.size(); i++) {
            if (tab.get(i).getcode().equals(num)) {
                aero = tab.get(i);
                break;
            }
        }
        return aero;
    }

    /**
     * Retourne une chaîne de caractères représentant tous les aéroports dans la liste.
     * 
     * @return une chaîne de caractères avec les informations de tous les aéroports
     */
    @Override
    public String toString() {
        System.out.println("Les aéroports de la liste : ");
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < tab.size(); i++) {
            res.append(tab.get(i).toString()).append(" \n ");
        }
        return res.toString();
    }

    /**
     * Retourne la taille de la liste des aéroports.
     * 
     * @return le nombre d'aéroports dans la liste
     */
    public int taillelisteaero() {
        return tab.size();
    }

    /**
     * Retourne l'aéroport à l'index spécifié dans la liste.
     * 
     * @param index l'index de l'aéroport à retourner
     * @return l'aéroport à l'index spécifié
     * @throws IndexOutOfBoundsException si l'index est hors des limites de la liste
     */
    public Aeroport getaeroport(int index) {
        return tab.get(index);
    }
}