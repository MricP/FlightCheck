/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphe;
import java.util.ArrayList;
/**
 *
 * @author Robi6
 * 
 * Classe Vol:
 * représente le vol d'un aéroport avec son nom/code, et toute les informations associées à un vol d'avion.
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
    public static int lastnum=1;
    public int id;
    public int couleur;
    private int composante;
    private ArrayList<Vol> adjacents;
    private ArrayList<Integer> adjacentsid;
    private int distance;
    private boolean traite;
    
    public Vol(int num){
        this.id = num;
        couleur = -1;
        adjacents= new ArrayList<Vol>();
        adjacentsid = new ArrayList<Integer>();
        composante = -1;
        
        
    }
    
    public Vol(String Nnom, String Ncodedepart, String Ncodearrivee, int heuredep, int minutedep, int duree){
        nom = Nnom;
        code_aeroport_depart = Ncodedepart;
        code_aeroport_arrive = Ncodearrivee;
        heure_depart = heuredep * 100 + minutedep;
        heure_arrive = (heuredep + (duree + minutedep) /60 ) * 100 + (duree + minutedep) % 60;
        minutes_depart =  heuredep * 60 + minutedep;
        minutes_arrive = minutes_depart+ duree;
        adjacents= new ArrayList<Vol>();
        adjacentsid = new ArrayList<Integer>();
        this.duree = duree;
        id = lastnum;
        lastnum++;
        couleur = -1;
        composante = -1;
    }
    
    
    /**
     * retourne la minute a laquelle le vol part à partir de 00.00
     * @return la minute a laquelle le vol part à partir de 00.00
     */
    public double getminutesdepart(){
        return minutes_depart;
    }
    
    /**
     * retourne la minute a laquelle le vol arrive à partir de 00.00
     * @return la minute a laquelle le vol arrive à partir de 00.00
     */
    public double getminutes_arrive(){
        return minutes_arrive;
        
    }
    
    /**
     * retourne la duree en minute du vol
     * @return la duree en minute du vol
     */
    public double getduree(){
        return duree;
        
    }
    
    /**
     * retourne true si le vol a deja été traité (pour la coloration ) sinon false
     * @return true si le vol a deja été traité (pour la coloration ) sinon false
     */
    public boolean gettraite(){
        return traite;
    }
    
    /**
     * 
     * @param bool 
     */
    public void settraite(boolean bool){
        traite = bool;
    }
    
    public int getdistance(){
        return distance;
    }
    
    public void setdistance(int distance){
        this.distance = distance;
    }
    
    public int getid(){
        return id;
    }
    
    public double getheuredepart(){
        return heure_depart;
    }
    
    public double getheurearrive(){
        return heure_arrive;
        
    }
    
    public int getnbadjacents(){
        return adjacents.size();
    }
    public String getcodedepart(){
        return code_aeroport_depart;
    }
    
    public String getcodearrive(){
        return code_aeroport_arrive;
    }
    
    public String getnom(){
        return nom;
    }
    
    public String toString(){
        return ("nom : "+ nom + " codedepart "+ code_aeroport_depart + " codearrivée "+ code_aeroport_arrive+" heure depart : "+ heure_depart + " heure arrivee : "+ heure_arrive );
    }
    
    public void addadjacent(Vol adjacent){
        adjacents.add(adjacent);
        
    }
    
    public boolean estAdjacent(Vol vol) {
        return adjacents.contains(vol);
    }
    
    public boolean estAdjacentid(int idvol) {
        return adjacentsid.contains(idvol);
    }
    
    public void setcouleur(int couleur){
        this.couleur = couleur;
    }
    
    public int getcouleur(){
        return couleur;
    }
    
    public boolean possepasdeadjcouleur(int couleur){
        boolean res = true;
        int i=0;
        while (res && i < adjacents.size()){
            if (adjacents.get(i).getcouleur() == couleur){
                res = false;
            }
            i++;
        }
        return res;
        
    }
    
    public int first_available_color(){
        int color = 0;
        boolean res= true;
        while (res){
            color++;
            res = false;
            int i=0;
            while (i < adjacents.size() && !res){
                if (adjacents.get(i).getcouleur() == color){
                    res = true;
                }
                i++;
            }
        }
        return color;
    }
    
    public int first_available_color_kmax(int kmax){
        int color = 0;
        boolean res= true;
        while (res && color <= kmax){
            color++;
            res = false;
            int i=0;
            while (i < adjacents.size() && !res){
                if (adjacents.get(i).getcouleur() == color){
                    res = true;
                }
                i++;
            }
        }
        
        if(color  > kmax){
            ArrayList<Integer> liste =  new ArrayList<>(kmax+1);
            int taille = adjacents.size();
            for (int i = 0; i <= kmax; i++) {
                liste.add(0); // Ajouter 0 à chaque indice
            }
            for (int y=0; y  < taille; y++){
                if (adjacents.get(y).getcouleur() != -1){
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
    
    
    public int DSAT(){
        if (couleur != -1){
            return -1;
        }
        
        int dsat = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        for (int i=0; i < adjacents.size();i++){
            if (adjacents.get(i).getcouleur() != -1 && !list.contains(adjacents.get(i).getcouleur())){
                list.add(adjacents.get(i).getcouleur());
                dsat++;
            }
        }
        if (list.isEmpty()){
            dsat = adjacents.size();
        }
        return dsat;
    }   
    
    
    public boolean goodcolor(){
        for(int i=0; i < adjacents.size(); i++){
            if (adjacents.get(i).getcouleur() == couleur){
                return false;
            }
        }
        return true;
    }
    
    
    public int distance(Vol vol){
        int distance = -1;
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (adjacents.size() == 0){
            return distance;
            
        }
        
        
        
        return distance;
    }
    
    public void setcomposante(int comp){
        composante = comp;
    }
    
    public int getcomposante(){
        return composante;
    }
    
    public boolean composante(int comp){
        
        if (composante == -1){
            
            int i =0;
            composante = 0;
            while (i < adjacents.size()){
                adjacents.get(i).composante(comp);
                i++;
            }
            composante = comp;
            return true;
        }
        return false;
       
    }
    
    public void Dijkstra(){
        settraite(true);
        int x = distance;
        int nb =0;
        /*System.out.println("cacaprout");*/
        for (int i=0; i < adjacents.size(); i++ ){
            Vol vol = adjacents.get(i);
            if (!vol.gettraite()){
                if (x + 1 < vol.getdistance()){
                    vol.setdistance(x + 1);
                    nb++;
                    
                }
            }
        }
        
        
        
        
        
        
    }    
    
    public Vol getAdjacentindice(int indice){  
        return adjacents.get(indice);
    }
    
    
    public int zzz(int kmax){
        ArrayList<Integer> liste =  new ArrayList<>(kmax+1);
        int taille = adjacents.size();
        for (int i = 0; i <= kmax; i++) {
            liste.add(0); // Ajouter 0 à chaque indice
        }
        
        for (int y=0; y  < taille; y++){
            if (adjacents.get(y).getcouleur() != -1){
                int valeur = liste.get(adjacents.get(y).getcouleur());
                liste.set(adjacents.get(y).getcouleur(), valeur + 1);
            }
        }
        
        
        System.out.println(liste.size());
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
    
}

