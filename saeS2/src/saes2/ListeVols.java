/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saes2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 *
 * @author Robi6
 * Classe ListeVols : 
 * représente une liste d'objects de la classe Vol
 * 
 */
public class ListeVols {
    private ArrayList<Vol> tab;
    private int nbarrete = 0;
    private int nbcomposante;
    private int diametre;
    private int kmax;
    private boolean havekmax;
    
    
    
    ListeVols(){
        tab = new ArrayList<Vol>();
        nbcomposante = -1;
        diametre = -1;
        havekmax= false;
        
    }
    
    public ListeVols(ListeVols l) {
    
        this.tab = new ArrayList<>(l.tab.size());
        for (Vol vol : l.tab) {
            this.tab.add(new Vol(vol)); // Supposant que la classe Vol a un constructeur de copie
        }
        
    }
    public int getkmax(){
        return kmax;
    }
    
    public boolean havekmax(){
        return havekmax;
    }
    
    public void setkmax(int kmax){
        this.kmax = kmax;
        havekmax = true;
    }
    
    public int getnbarrte(){
        return nbarrete;
    }
    public boolean ajMembre(Vol membreAj){
        boolean res = false;
        if (membreAj != null){
            tab.add(membreAj);
        }
        return res;
    }
    
    public Vol accesMembrenom(String nom) {
        Vol membre = null;
        for (int i=0;i< tab.size();i++){
            
            if (tab.get(i).getnom().equals(nom)){
                
                membre =  tab.get(i);
            }
        }
        return membre;
    }
    
    public Vol accesMembrenum(int num) {
        Vol membre = null;
        for (int i=0;i< tab.size();i++){
            
            if (tab.get(i).getnum() == num){
                
                membre =  tab.get(i);
            }
        }
        return membre;
    }
    
    public void setnbarrete(int nbarrete){
        this.nbarrete = nbarrete;
    }
    
    public int taille(){
        return tab.size();
    }
    
    public void tostring(){  /* peut etre changer tout cela en String */
        System.out.println("les vols de la liste : ");
        for ( int i=0; i < tab.size();i++){
            System.out.println(tab.get(i).toString());
        }
        
    }
    
    public Vol getVolindice(int indice){
        return tab.get(indice);
    }
    
    public Vol getVolnumero(int numero){
        for (int i=0; i < tab.size(); i++){
            if (tab.get(i).getnum() == numero ){
                return tab.get(i);
            }
        }
        return null;
    }
    
    public void addaerrete(){
        nbarrete++;
    }
    
    
    Comparator<Vol> comparateur = new Comparator<Vol>() {
        
        
        public int compare(Vol o1, Vol o2) {
            return Integer.compare(o2.getnbadjacents(), o1.getnbadjacents()); // Compare dans l'ordre décroissant
        }
    };
    
    
    public int MAXWelshPowell(){
        Collections.sort(tab, comparateur);
        int maxcoul = kmax;
        int nbCouleurs = 1;
        int voltraite = 0;
        boolean var;
        boolean var2= true;
        while (var2 && (nbCouleurs <= maxcoul)){
            int i=0;
            var= true;
            while( i < tab.size() && var){
                if (tab.get(i).couleur == -1 ){
                    var = false;
                }else{
                    i++;
                }
            }
            
            if (i < tab.size()){
                tab.get(i).setcouleur(nbCouleurs);
                voltraite++;
                i++;
            }else{
                var2= false;
            }
            
            while (i < tab.size()){
                if (tab.get(i).possepasdeadjcouleur(nbCouleurs) && (tab.get(i).getcouleur() == -1)  ){
                    tab.get(i).setcouleur(nbCouleurs);
                }
                i++;
            }
                
                
                
            nbCouleurs++;
        }
        
        if (var2){
            int i=0;
            while (i < tab.size()){
                if (tab.get(i).getcouleur() == -1 ){
                    int couleur = tab.get(i).zzz(kmax);
                    tab.get(i).setcouleur(couleur);
                    
                }
                i++;
            }
            
            
        }
        
        
        
        
        return nbCouleurs;
    }
    
    public int WelshPowell(){
        Collections.sort(tab, comparateur);
        
        int nbCouleurs = 1;
        int voltraite = 0;
        boolean var;
        boolean var2= true;
        while (var2){
            int i=0;
            var= true;
            while( i < tab.size() && var){
                if (tab.get(i).couleur == -1 ){
                    var = false;
                }else{
                    i++;
                }
            }
            
            if (i < tab.size()){
                tab.get(i).setcouleur(nbCouleurs);
                voltraite++;
                i++;
            }else{
                var2= false;
            }
            
            while (i < tab.size()){
                if (tab.get(i).possepasdeadjcouleur(nbCouleurs) && (tab.get(i).getcouleur() == -1)  ){
                    tab.get(i).setcouleur(nbCouleurs);
                }
                i++;
            }
                
                
                
            nbCouleurs++;
        }
        
        
        
        
        return nbCouleurs;
    }
    
    
    public void RandomColoration(int max){
        int random = 1;
        for (int i = 0;i<tab.size();i++){
            tab.get(i).setcouleur((random % max)+1);
            random++;
        }
        
        
        
        
    }
    
    
    public void GreedyColor(){
        for (int i =0;i<tab.size();i++){
            tab.get(i).setcouleur(tab.get(i).first_available_color());
        }
    }
        
    
    
    public void Dsatur(){
        for (int y=0; y < tab.size();y++){
            int max = -1;
            int indice= -1 ;
            for (int i=0; i < tab.size();i++){
                
                if (tab.get(i).DSAT() > max){
                    indice = i;
                    max = tab.get(i).DSAT();
                }
            }
            
            tab.get(indice).setcouleur(tab.get(indice).first_available_color());
        }
        
        
        
    }
    
    
    
    
    public int getnbconflit(){
        int nbconflit = 0;
        int couleurv;
        Vol v;
        int cpt =0;
        for (int i=0;i < tab.size(); i++){
            v = tab.get(i);
            couleurv = v.getcouleur();
            for (int y=0; y < v.getnbadjacents();y++){
                cpt++;
                //System.out.println(couleurv+ "      "+ v.getAdjacentindice(y).getcouleur());
                if (v.getAdjacentindice(y).getcouleur() == couleurv){
                    nbconflit++;
                    //System.out.println("true");
                }
            }
            
            
        }
        System.out.println("compteur :  "+ cpt);
        return nbconflit/2;
    }
     
    public boolean goodcoloration(){
        for (int i=0;i < tab.size(); i++){
            if (!tab.get(i).goodcolor()){
                return false;
            }
        }
        return true;
    }
            
            
    public int maxcouleur(){
        int max = -1;
        int i=0;
        while ( i < tab.size()){
            if (tab.get(i).getcouleur() > max){
                max = tab.get(i).getcouleur();
            }
            i++;
        }
        return max;
    }
    
    
    public void viewcolor(){
        int i=0;
        while ( i < tab.size()){
            System.out.println(tab.get(i).getnum()+ " " + tab.get(i).getcouleur());
            i++;
        }
    }
    
    
    public void setcouleurdefault(){
        int i=0;
        while ( i < tab.size()){
            tab.get(i).setcouleur(-1);
            i++;
        }
    }
    
    public double getdegremoyen(){
        int i=0;
        double total = 0;
        while ( i < tab.size()){
            total = total + tab.get(i).getnbadjacents();
            i++;
        }
        total = total / tab.size();
        return total;
    }
    
   
    
    public int getnbcomposante(){
        int nb = 1;
        if (nbcomposante != -1){
            return nbcomposante;
        }
        int i=0;
        int y=1;
        while (i <  tab.size()){
            if (tab.get(i).composante(y)){
                y++;
            }
            i++;
        }
        nbcomposante = y - 1;
        
        return nbcomposante;
    }
    
    public int getdiametre(){
        if (diametre != -1){
            return diametre;
        }
        int diametre;
        int maxdiametre = 0;
        if (nbcomposante == -1){
            getnbcomposante();
        }
        ArrayList vols = new ArrayList<Vol>();
        for (int i = 0; i< tab.size(); i++){
            for (int y=i+1; y < tab.size(); y++){
                diametre = Dijkstra(tab.get(i), tab.get(y));
                if (diametre > maxdiametre){
                    maxdiametre = diametre;
                }
            }
        }
        
        return maxdiametre;
        
    }
    
    public int Dijkstra(Vol vol1, Vol vol2){
        //initialisation
        if ( vol1.getcomposante() != vol2.getcomposante()){
            return 0;
        }
        
        for (int i =0; i < tab.size(); i++){
            tab.get(i).setdistance(9999);
            tab.get(i).settraite(false);
            
        }
        
        tab.get(tab.indexOf(vol1)).setdistance(0);
        tab.get(tab.indexOf(vol1)).settraite(true);
        vol1.Dijkstra();
        boolean var= true;
        while (!vol2.gettraite()){
            var =false;
            int maxdistance = Integer.MAX_VALUE;
            Vol sommetmin = vol1;
            
            for (int y = 0; y < tab.size(); y++){
                if (!tab.get(y).gettraite() && (tab.get(y).getcomposante() == vol1.getcomposante() )){
                    if (tab.get(y).getdistance() <= maxdistance){
                        maxdistance = tab.get(y).getdistance();
                        sommetmin = tab.get(y);
                        var = true;
                    }
                }
            }
            sommetmin.Dijkstra();
        
            
        }
        
        return vol2.getdistance();
    }
    
    
    
    public int getnbcomposantede(int composante){
        int cpt =0;
        for ( int i=0; i< tab.size(); i++){
            if (tab.get(i).getcomposante() == composante){
                cpt ++;
            }
        }
            
            
            
        return cpt;
    }
    
    public void shownumcomposante(){
        for ( int i=0; i < tab.size(); i++){
            Vol v = tab.get(i);
            
        }
    }
    
    public ArrayList<Vol> getArraylist(){
        return tab;
    }
    
    public ListeVols getListVols(){
        return this;
    }
    
    
}
