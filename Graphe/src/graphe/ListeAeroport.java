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
public class ListeAeroport {
    private ArrayList<Aeroport> tab;
    
    
    
    public ListeAeroport(){
        tab = new ArrayList<Aeroport>();
        
    }
    
    public boolean ajAeroport(Aeroport aeroAj){
        boolean res = false;
        if (aeroAj != null){
            tab.add(aeroAj);
        }
        return res;
    }
    
    public Aeroport accesAeroport(String num) {
        Aeroport aero = null;
        for (int i=0;i< tab.size();i++){
            if (tab.get(i).getcode().equals(num)){
                aero =  tab.get(i);
            }
        }
        return aero;
    }
    
    public String toString(){  /* peut etre changer tout cela en String */
        System.out.println("les aeroports de la liste : ");
        String res = "";
        for ( int i=0; i < tab.size();i++){
            res = res + tab.get(i).toString()+ " \n ";
        }   
        return res;
        
    }
    
    public int taillelisteaero(){
        return tab.size();
    }
    
    public Aeroport getaeroport(int index){
        return tab.get(index);
    }
    
    
    
}

