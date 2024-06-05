/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphe;


import java.lang.Math;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
    
import org.graphstream.graph.implementations.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.Random;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;


import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.painter.*;

/**
 *C:/Users/Robi6/OneDrive/Bureau/aeroports.txt
 *C:/Users/Robi6/OneDrive/Bureau/DataTest/vol-test1.csv
 * @author Robi6
 */

public class Main {
    
    
    private static int minColors = Integer.MAX_VALUE;
    private static Map<Node, Integer> bestColoring = new HashMap<>();
    private static ListeAeroport L = new ListeAeroport();
    private static ListeVols LV = new ListeVols();       
    private static ListeVols LL = new ListeVols();       
    
    public Main(){
        
    }
    
    public ListeAeroport getlisteaero(){
        return L;
    }
    public void setlisteaero(ListeAeroport liste){
        L = liste;
    }
    
    public ListeVols getlisteVols(){
        return LV;
    }
    
    public void setlistevols(ListeVols liste){
        LV = liste;
    }
    
    public ListeVols creationgraphe(ListeVols liste){
        /*
        System.out.println(LV.getVolindice(3).toString());
        System.out.println(LV.getVolindice(17).toString());
        System.out.println(intersection(LV.getVolindice(3),LV.getVolindice(17)));
        */
        
        int taille = liste.taille();
        int gpt = 0;
        //System.out.println("taille  : "+ liste.taille());
        int cpt=0;
        for (int i = 0; i< taille; i++){
            for (int y=i+1; y < taille; y++){
                cpt++;
                if(intersection(LV.getVolindice(i),liste.getVolindice(y))){
                    gpt++;
                    liste.getVolindice(i).addadjacent(liste.getVolindice(y));
                    //System.out.println(liste.getVolindice(i).id+ "  :  "+ liste.getVolindice(y).id);
                    liste.getVolindice(y).addadjacent(liste.getVolindice(i));
                    
                    liste.addaerrete();
                    
                }
            }
        }     
        //System.out.println("compteur  : "+ cpt);
        //System.out.println("nbarretes  : "+ gpt);
        return liste;
        
    }
    
    
    
    
    
    
    
    
    
    public static ListeVols creationgraphe_static(ListeVols liste){
        /*
        System.out.println(LV.getVolindice(3).toString());
        System.out.println(LV.getVolindice(17).toString());
        System.out.println(intersection(LV.getVolindice(3),LV.getVolindice(17)));
        */
        
        int taille = liste.taille();
        int gpt = 0;
        //System.out.println("taille  : "+ liste.taille());
        int cpt=0;
        for (int i = 0; i< taille; i++){
            for (int y=i+1; y < taille; y++){
                cpt++;
                if(intersection(LV.getVolindice(i),liste.getVolindice(y))){
                    gpt++;
                    liste.getVolindice(i).addadjacent(liste.getVolindice(y));
                    //System.out.println(liste.getVolindice(i).id+ "  :  "+ liste.getVolindice(y).id);
                    liste.getVolindice(y).addadjacent(liste.getVolindice(i));
                    
                    liste.addaerrete();
                    
                }
            }
        }     
        //System.out.println("compteur  : "+ cpt);
        //System.out.println("nbarretes  : "+ gpt);
        return liste;
        
    }
    
    //la methode etre deplacéé
    public static boolean intersection(Vol V1, Vol V2){
        
        if (V1 == V2){
            
            return false;
        }
        if ( V1.estAdjacent(V2)){
            
            return false;
            
        }
        if ( V2.estAdjacent(V1)){
            
            return false;
        }
        
        Aeroport A1 = L.accesAeroport(V1.getcodedepart());
        Aeroport A2 = L.accesAeroport(V1.getcodearrive());
        Aeroport A3 = L.accesAeroport(V2.getcodedepart());
        Aeroport A4 = L.accesAeroport(V2.getcodearrive());
        
        //A1 = A3 regarder  heure de depart
        if (A1 == A3 && (Math.abs(V1.getminutesdepart() - V2.getminutesdepart()) < 15)){
            return true;
        }
        //A2= A4 regarder heure arrivée
        if (A2 == A4 && (Math.abs(V1.getminutes_arrive() - V2.getminutes_arrive()) < 15)){
            return true;
        }
        //A1 = A4 regarder heure de depart de 1 puis heure d'arrivée de 2
        /*System.out.println(Math.abs(V1.getminutesdepart() - V2.getminutes_arrive()));*/
        if (A1 == A4 && (Math.abs(V1.getminutesdepart() - V2.getminutes_arrive()) < 15)){
            return true;
        }
        //A2 = A3 regarder heure de depart de 2 puis heure d'arrivée de 1
        if (A2 == A3 && (Math.abs(V1.getminutes_arrive() - V2.getminutesdepart()) < 15)){
            return true;
        }
        
        if (A1 ==  A4 && A2 == A3){
            if (Math.abs(V1.getminutesdepart() - V2.getminutesdepart()) < 15){
                return true;
            }
            if (Math.abs(V1.getminutes_arrive() - V2.getminutes_arrive()) < 15){
                return true;
            }
        }
        
        
        
        
        
        double intersection_x = ((A1.getX() * A2.getY() - A1.getY() * A2.getX()) * (A3.getX() - A4.getX()) - (A1.getX() - A2.getX()) * (A3.getX() * A4.getY() - A3.getY() * A4.getX())) / ((A1.getX() - A2.getX()) * (A3.getY() - A4.getY()) - (A1.getY() - A2.getY()) * (A3.getX() - A4.getX()));
        double intersection_y = ((A1.getX() * A2.getY() - A1.getY() * A2.getX()) * (A3.getY() - A4.getY()) - (A1.getY() - A2.getY()) * (A3.getX() * A4.getY() - A3.getY() * A4.getX())) / ((A1.getX() - A2.getX()) * (A3.getY() - A4.getY()) - (A1.getY() - A2.getY()) * (A3.getX() - A4.getX()));
        /*System.out.println(intersection_x + " , "+ intersection_y);*/
                
        double min_x =  Math.min(A1.getX(), A2.getX());
        double max_x = Math.max(A1.getX(), A2.getX());
        double min_y = Math.min(A1.getY(), A2.getY());
        double max_y = Math.max(A1.getY(), A2.getY());
        /*System.out.println(min_x + " , "+ max_x + " "+min_y + " , "+ max_y + " ");*/
        
        // Répéter le même processus pour le second segment
        double min_x2 =  (Math.min(A3.getX(), A4.getX()));
        double max_x2 =  (Math.max(A3.getX(), A4.getX()));
        double min_y2 =  (Math.min(A3.getY(), A4.getY()));
        double max_y2 =  (Math.max(A3.getY(), A4.getY()));
        
        if (!((min_x <= intersection_x && intersection_x <= max_x) &&
            (min_y <= intersection_y && intersection_y <= max_y) &&
            (min_x2 <= intersection_x && intersection_x <= max_x2) &&
            (min_y2 <= intersection_y && intersection_y <= max_y2))) {
            /*System.out.println("fake");*/
            return false;
        }
        
        
        double longueurX1 = A1.getX() - A2.getX();
        
        if( longueurX1 < 0){
            longueurX1 = longueurX1 * -1;
        }
        double var1 = (A1.getX() - intersection_x);
        if( var1 < 0){
            var1 = var1 * -1;
        }
        double pourcentage1 = var1/longueurX1;
        double heure1 = V1.getminutesdepart() +( V1.getduree() * pourcentage1);
        
        
        double longueurX2 = A3.getX() - A4.getX();
        
        if( longueurX2 < 0){
            longueurX2 = longueurX2 * -1;
        }
        double var2 = (A3.getX() - intersection_x);
        if( var2 < 0){
            var2 = var2 * -1;
        }
        double pourcentage2 = var2/longueurX2;
        double heure2 = V2.getminutesdepart() + (V2.getduree() * pourcentage2);
        
        
        if (Math.abs(heure1 - heure2) < 15) {
            return true; // Collision détectée
        }
        
        return false;
           
    }
    
    public static ListeVols CreateGraphText_static(){
        System.out.println("rentrez le chemin d'acces de votre graphe sous forme .txt:");
        
        String FilePath = "C:/Users/Aspect-PC/Desktop/SAE-GIT/sae_mathieu_petit_pirrera/DataTest/graph-test2.txt";
        //String FilePath = "C:/Users/Aspect-PC/Desktop/SAE-IMH/sae_mathieu_petit_pirrera/DataTest/graph-test2.txt";
        /*String FilePath = ent.nextLine();*/
        
        // Créer un objet File en utilisant le chemin du fichier
        File file = new File(FilePath);
        
        // Vérifier si le fichier existe
        if (!file.exists()) {
            System.out.println("Le fichier n'existe pas.");
            return null;
        }else{
            System.out.println("Le fichier existe .");
        }
        
        // Déclarer un BufferedReader pour lire le fichier
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // Lire chaque ligne du fichier tant qu'il y en a
            ListeVols LVol = new ListeVols();
            int cpt = 0;
            while ((line = reader.readLine()) != null) {
                // Afficher chaque ligne lue
                if (cpt ==0){
                    int kmax = Integer.valueOf(line);
                    LVol.setkmax(kmax);
                    
                    
                }else if (cpt == 1){
                    int nbsommets = Integer.valueOf(line);
                    LVol.setnbarrete(nbsommets);
                    for (int i =1; i <= nbsommets;i++){
                        Vol Vol = new Vol(i);
                        LVol.ajMembre(Vol);
                    }
                    
                }else{
                    String[] tab = line.split(" ");
                    int x = Integer.valueOf(tab[0]);
                    int y = Integer.valueOf(tab[1]);
                    Vol Vol1 = LVol.getVolnumero(x);
                    Vol Vol2 = LVol.getVolnumero(y);
                    Vol1.addadjacent(Vol2);
                    Vol2.addadjacent(Vol1);
                    
                    
                }
                /*System.out.println(line);*/
                
                cpt ++;
            }
            
            return LVol;
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier : " + e.getMessage());
        }
        return null;
    }
        
    public ListeVols CreateGraphText(File file){
        /*
        System.out.println("rentrez le chemin d'acces de votre graphe sous forme .txt:");
        
        String FilePath = "C:/Users/Aspect-PC/Desktop/SAE-GIT/sae_mathieu_petit_pirrera/DataTest/graph-test2.txt";
        //String FilePath = "C:/Users/Aspect-PC/Desktop/SAE-IMH/sae_mathieu_petit_pirrera/DataTest/graph-test2.txt";
        
        
        // Créer un objet File en utilisant le chemin du fichier
        File file = new File(FilePath);
        */
        // Vérifier si le fichier existe
        if (!file.exists()) {
            System.out.println("Le fichier n'existe pas.");
            return null;
        }else{
            System.out.println("Le fichier existe .");
        }
        
        // Déclarer un BufferedReader pour lire le fichier
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // Lire chaque ligne du fichier tant qu'il y en a
            ListeVols LVol = new ListeVols();
            int cpt = 0;
            while ((line = reader.readLine()) != null) {
                // Afficher chaque ligne lue
                if (cpt ==0){
                    int kmax = Integer.valueOf(line);
                    LVol.setkmax(kmax);
                    
                    
                }else if (cpt == 1){
                    int nbsommets = Integer.valueOf(line);
                    LVol.setnbarrete(nbsommets);
                    for (int i =1; i <= nbsommets;i++){
                        Vol Vol = new Vol(i);
                        LVol.ajMembre(Vol);
                    }
                    
                }else{
                    String[] tab = line.split(" ");
                    int x = Integer.valueOf(tab[0]);
                    int y = Integer.valueOf(tab[1]);
                    Vol Vol1 = LVol.getVolnumero(x);
                    Vol Vol2 = LVol.getVolnumero(y);
                    Vol1.addadjacent(Vol2);
                    Vol2.addadjacent(Vol1);
                    
                    
                }
                /*System.out.println(line);*/
                
                cpt ++;
            }
            
            return LVol;
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier : " + e.getMessage());
        }
        return null;
    }
    
    
        
    public static Graph getGraphStream_static(ListeVols liste) {
        Graph graph = new MultiGraph("multi graphe");
        ArrayList<String> couleurs = new ArrayList<String>();
        
        Random random = new Random();
        
        for (int i = 0; i < liste.maxcouleur(); i++) {
            // Génération d'une couleur aléatoire
            String couleur = String.format("#%02x%02x%02x", random.nextInt(256), random.nextInt(256), random.nextInt(256));
            couleurs.add("fill-color: " + couleur + ";");
        }
        
        for (int i = 1; i <= liste.taille(); i++) {
            graph.addNode(Integer.toString(i));
            /*System.out.println(liste.getVolnumero(i).getcouleur());*/
            if(liste.getVolnumero(i).getcouleur()>= 1){
                graph.getNode(Integer.toString(i)).addAttribute("ui.style", couleurs.get(liste.getVolnumero(i).getcouleur()-1));
            }
        }
        
        // Ajout des arêtes au graphe
        int cpt =0;
        for (int i = 1; i <= liste.taille(); i++) {
            Vol v = liste.getVolnumero(i);
            for (int y = 0; y < v.getnbadjacents(); y++) {
                Vol v2 = v.getAdjacentindice(y);
                if (!graph.getNode(Integer.toString(i)).hasEdgeBetween(Integer.toString(v2.getid()))) {
                    graph.addEdge("edge_" + i + "_" + v2.getid(), Integer.toString(i), Integer.toString(v2.getid()));
                    cpt++;
                }
            }
        }
        
        return graph;
    }
    
    public double getDiametre(ListeVols liste){
        Graph G = getGraphStream_static(liste);
        return org.graphstream.algorithm.Toolkit.diameter(G);
    }
    
    public static double getDiametre_static(ListeVols liste){
        Graph G = getGraphStream_static(liste);
        return org.graphstream.algorithm.Toolkit.diameter(G);
    }
    
    
    
    public Graph getGraphStream(ListeVols liste) {
        Graph graph = new MultiGraph("multi graphe");
        ArrayList<String> couleurs = new ArrayList<String>();
        
        Random random = new Random();
        
        for (int i = 0; i < liste.maxcouleur(); i++) {
            // Génération d'une couleur aléatoire
            String couleur = String.format("#%02x%02x%02x", random.nextInt(256), random.nextInt(256), random.nextInt(256));
            couleurs.add("fill-color: " + couleur + ";");
        }
        
        for (int i = 1; i <= liste.taille(); i++) {
            graph.addNode(Integer.toString(i));
            /*System.out.println(liste.getVolnumero(i).getcouleur());*/
            graph.getNode(Integer.toString(i)).addAttribute("ui.style", couleurs.get(liste.getVolnumero(i).getcouleur()-1));
        }
        
        // Ajout des arêtes au graphe
        int cpt =0;
        for (int i = 1; i <= liste.taille(); i++) {
            Vol v = liste.getVolnumero(i);
            for (int y = 0; y < v.getnbadjacents(); y++) {
                Vol v2 = v.getAdjacentindice(y);
                if (!graph.getNode(Integer.toString(i)).hasEdgeBetween(Integer.toString(v2.getid()))) {
                    graph.addEdge("edge_" + i + "_" + v2.getid(), Integer.toString(i), Integer.toString(v2.getid()));
                    cpt++;
                }
            }
        }
        
        return graph;
    }
    
    public void setvolaeroports(File file){
        /*
        String FilePath;
        File file;
        // Chemin du fichier texte
        System.out.println("rentrez le chemin d'acces de votre fichier de vols en format .csv:");
        FilePath = "C:/Users/Aspect-PC/Desktop/SAE-GIT/sae_mathieu_petit_pirrera/DataTest/vol-test8.csv";
        //FilePath = "C:/Users/Aspect-PC/Desktop/SAE-IMH/sae_mathieu_petit_pirrera/DataTest/vol-test8.csv";
        
        
        // Créer un objet File en utilisant le chemin du fichier
        file = new File(FilePath);
        */
        // Vérifier si le fichier existe
        if (!file.exists()) {
            System.out.println("Le fichier n'existe pas.");
            return;
        }else{
            System.out.println("Le fichier existe .");
        }
        
        // Déclarer un BufferedReader pour lire le fichier
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // Lire chaque ligne du fichier tant qu'il y en a
            while ((line = reader.readLine()) != null) {
                // Afficher chaque ligne lue
                
                /*System.out.println(line);*/
                String[] tab = line.split(";");
                Vol Vol = new Vol(tab[0],tab[1],tab[2],Integer.valueOf(tab[3]),Integer.valueOf(tab[4]),Integer.valueOf(tab[5]));
                LV.ajMembre(Vol);
            }
            
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier : " + e.getMessage());
        }
    }    
    
    public static void setvolaeroports_static(){
        String FilePath;
        File file;
        // Chemin du fichier texte
        System.out.println("rentrez le chemin d'acces de votre fichier de vols en format .csv:");
        FilePath = "C:/Users/Aspect-PC/Desktop/SAE-GIT/sae_mathieu_petit_pirrera/DataTest/vol-test8.csv";
        //FilePath = "C:/Users/Aspect-PC/Desktop/SAE-IMH/sae_mathieu_petit_pirrera/DataTest/vol-test8.csv";
        /*FilePath = ent.nextLine();*/
        
        // Créer un objet File en utilisant le chemin du fichier
        file = new File(FilePath);
        
        // Vérifier si le fichier existe
        if (!file.exists()) {
            System.out.println("Le fichier n'existe pas.");
            return;
        }else{
            System.out.println("Le fichier existe .");
        }
        
        // Déclarer un BufferedReader pour lire le fichier
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // Lire chaque ligne du fichier tant qu'il y en a
            while ((line = reader.readLine()) != null) {
                // Afficher chaque ligne lue
                
                /*System.out.println(line);*/
                String[] tab = line.split(";");
                Vol Vol = new Vol(tab[0],tab[1],tab[2],Integer.valueOf(tab[3]),Integer.valueOf(tab[4]),Integer.valueOf(tab[5]));
                LV.ajMembre(Vol);
            }
            
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier : " + e.getMessage());
        }
    }    
        
    
    
    
    
    public void setAeroportlist(File file){
        /*
        System.out.println("rentrez le chemin d'acces de votre fichier deaéroports sous forme .txt:");
        
        String FilePath = "C:/Users/Aspect-PC/Desktop/SAE-GIT/sae_mathieu_petit_pirrera/DataTest/aeroports.txt";
        //String FilePath = "C:/Users/Aspect-PC/Desktop/SAE-IMH/sae_mathieu_petit_pirrera/DataTest/aeroports.txt";
        
        
        // Créer un objet File en utilisant le chemin du fichier
        File file = new File(FilePath);
        */
        // Vérifier si le fichier existe
        if (!file.exists()) {
            System.out.println("Le fichier n'existe pas.");
            return;
        }else{
            System.out.println("Le fichier existe .");
        }
        
        // Déclarer un BufferedReader pour lire le fichier
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // Lire chaque ligne du fichier tant qu'il y en a
            while ((line = reader.readLine()) != null) {
                // Afficher chaque ligne lue
                
                /*System.out.println(line);*/
                String[] tab = line.split(";");
                Aeroport Aero = new Aeroport(tab[0],tab[1],Integer.valueOf(tab[2]),Integer.valueOf(tab[3]),Integer.valueOf(tab[4]),tab[5],Integer.valueOf(tab[6]),Integer.valueOf(tab[7]),Integer.valueOf(tab[8]),tab[9]);
                L.ajAeroport(Aero);
            }
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier : " + e.getMessage());
        }
    }
    
    
    public static void setAeroportlist_static(){
        System.out.println("rentrez le chemin d'acces de votre fichier deaéroports sous forme .txt:");
        String filtePath;
        String FilePath = "C:/Users/Aspect-PC/Desktop/SAE-GIT/sae_mathieu_petit_pirrera/DataTest/aeroports.txt";
        /*String FilePath = ent.nextLine();*/
        
        // Créer un objet File en utilisant le chemin du fichier
        File file = new File(FilePath);
        
        // Vérifier si le fichier existe
        if (!file.exists()) {
            System.out.println("Le fichier n'existe pas.");
            return;
        }else{
            System.out.println("Le fichier existe .");
        }
        
        // Déclarer un BufferedReader pour lire le fichier
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // Lire chaque ligne du fichier tant qu'il y en a
            while ((line = reader.readLine()) != null) {
                // Afficher chaque ligne lue
                
                /*System.out.println(line);*/
                String[] tab = line.split(";");
                Aeroport Aero = new Aeroport(tab[0],tab[1],Integer.valueOf(tab[2]),Integer.valueOf(tab[3]),Integer.valueOf(tab[4]),tab[5],Integer.valueOf(tab[6]),Integer.valueOf(tab[7]),Integer.valueOf(tab[8]),tab[9]);
                L.ajAeroport(Aero);
            }
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier : " + e.getMessage());
        }
    }
    
    
    
    
    
    
    public static void main(String[] args) {
        Scanner ent = new Scanner(System.in);
        
        
        setAeroportlist_static();
        setvolaeroports_static();
        
        LV  = creationgraphe_static(LV);
        
        
        //System.out.println("feur");
        LV.WelshPowell();
        //System.out.println(LV.goodcoloration());
        //System.out.println("feur");
        
        //System.out.println("feur");
        //System.out.println(LV.maxcouleur());
        LV.setcouleurdefault();
        
        
        
        LV.GreedyColor();
        //System.out.println(LV.goodcoloration());
        //System.out.println("feur");
        
        //System.out.println("feur");
        //System.out.println(LV.maxcouleur());
        LV.setcouleurdefault();
        
        LV.Dsatur();
        //System.out.println(LV.goodcoloration());
        //System.out.println("feur");
        
        //System.out.println("fedfvergergr");
        //System.out.println(LV.maxcouleur());
        
        
        
        /*System.out.println(intersection(LV.accesMembre("AF000090"),LV.accesMembre("AF000132")));*/
        
        
        //System.out.println("nbarretes : "+ LV.getnbarrte());
        //System.out.println("degre moyen : "+LV.getdegremoyen());
        //System.out.println("nb composantes  : "+LV.getnbcomposante());
        //System.out.println(LV.getnbcomposantede(4));
        
        
        
        
        //System.out.println("diametre : "+ LV.getdiametre());
        
        
        /*
        LV.shownumcomposante();
        */
        
        
        
        
        
        
        //Partie graph-test
        
        LL = CreateGraphText_static();
        
        
        
        
        
        
        LL.tostring();
        //System.out.println("nbarretes : "+ LL.getnbarrte());
        //System.out.println("degre moyen : "+LL.getdegremoyen());
        //System.out.println("nb composantes  : "+LL.getnbcomposante());
        
        LL.Dsatur();
        //System.out.println(LL.goodcoloration());
        //System.out.println("feur");
        
        //System.out.println("fedfvergergr");
        //System.out.println("nb de couleurs : "+LL.maxcouleur());
        //System.out.println("kmax :  "+ LL.getkmax());
        LL.setcouleurdefault();
        
        LL.WelshPowell();
        //System.out.println("nb de couleurs : "+LL.maxcouleur());
        //System.out.println(LL.taille());
        //System.out.println(LL.getnbarrte());
        
        /*
        LL.setcouleurdefault();
        System.out.println("default   "+LL.getnbconflit());
        LL = descent(LL);
        */
                
        /*
        LL.setcouleurdefault();
        LL.MAXWelshPowell();
        System.out.println("MAX  "+LL.getnbconflit());
        */
        LL.setcouleurdefault();
        
        
        LL = FullGreedyColor_static(LL);
        LL.adresscouleurs(LL.getcouleurs());
        LL.MAXWelshPowell();
        /*
        LL.setcouleurdefault();
        LL.MAXWelshPowell();
        */
        LL.adresscouleurs(LL.getcouleurs());
        //System.out.println(LL.goodcoloration());
        
        Graph G = getGraphStream_static(LL);
        //System.out.println(LL.getnbconflit());
        //System.out.println(LL.goodcoloration());
        
                
        //System.out.println(LL.getdiametre());
//        System.out.println(org.graphstream.algorithm.Toolkit.diameter(G));
        //System.out.println(LL.maxcouleur());
        
        G.display();
        
    }
    
    public ListeVols FullWelshPowell(ListeVols list){
        
        int minconflits = Integer.MAX_VALUE;
        ArrayList<Integer> best = list.getcouleurs();
        int mincouleur = Integer.MAX_VALUE;
        int cpt = 0;
        for (int y =0;y<list.getArraylist().size() * 99;y++){
            cpt++;
            list.setcouleurdefault();
            Collections.shuffle(list.getArraylist());
            list.MAXWelshPowell();
            if ((list.maxcouleur() < mincouleur) || (list.maxcouleur() == mincouleur && list.getnbconflit() < minconflits)){
                mincouleur = list.maxcouleur();
                //System.out.println("min " +mincouleur);
                best = list.getcouleurs();
                if (mincouleur == list.getkmax()){
                    minconflits = list.getnbconflit();
                }
            }
            
        }
        System.out.println("nb de boucles"+ cpt);
        System.out.println("nb arretes"+ list.getnbarrte());
        list.adresscouleurs(best);
        System.out.println("fin couleur min " +list.maxcouleur());
        System.out.println("fin nb conflits  " +list.getnbconflit());
        //System.out.println("fin minconflits  " +minconflits);
        System.out.println(list.goodcoloration());
        return list;
        
    }
    
    public ListeVols FullGreedyColor(ListeVols list){
        
        int minconflits = Integer.MAX_VALUE;
        ArrayList<Integer> best = list.getcouleurs();
        int mincouleur = Integer.MAX_VALUE;
        
        for (int y =0;y<list.getArraylist().size() * 99;y++){
            list.setcouleurdefault();
            Collections.shuffle(list.getArraylist());
            list.GreedyColor();
            if ((list.maxcouleur() < mincouleur) || (list.maxcouleur() == mincouleur && list.getnbconflit() < minconflits)){
                mincouleur = list.maxcouleur();
                //System.out.println("min " +mincouleur);
                best = list.getcouleurs();
                if (mincouleur == list.getkmax()){
                    minconflits = list.getnbconflit();
                }
            }
            
        }
        list.adresscouleurs(best);
        System.out.println("fin couleur min " +list.maxcouleur());
        System.out.println("fin nb conflits  " +list.getnbconflit());
        //System.out.println("fin minconflits  " +minconflits);
        System.out.println(list.goodcoloration());
        return list;
        
    }
    
    public Statistiques calculerStatistiques() {
        double degreMoyen = LV.getdegremoyen();
        int nbComposantes = LV.getnbcomposante();
        int nbNoeuds = LV.taille();
        int nbAretes = LV.getnbarrte();
        double diametre = getDiametre_static(LV); // Assurez-vous que cette méthode existe et fonctionne
        int nbConflits = LV.getnbconflit();
        
        return new Statistiques(degreMoyen, nbComposantes, nbNoeuds, nbAretes, diametre, nbConflits);
    }
     
    
    public static ListeVols FullGreedyColor_static(ListeVols list){
        
        int minconflits = Integer.MAX_VALUE;
        ArrayList<Integer> best = list.getcouleurs();
        int mincouleur = Integer.MAX_VALUE;
        
        for (int y =0;y<list.getArraylist().size() * 9;y++){
            list.setcouleurdefault();
            Collections.shuffle(list.getArraylist());
            list.GreedyColor();
            if ((list.maxcouleur() < mincouleur) || (list.maxcouleur() == mincouleur && list.getnbconflit() < minconflits)){
                mincouleur = list.maxcouleur();
                //System.out.println("min " +mincouleur);
                best = list.getcouleurs();
                if (mincouleur == list.getkmax()){
                    minconflits = list.getnbconflit();
                }
            }
            
        }
        list.adresscouleurs(best);
        System.out.println("fin couleur min " +list.maxcouleur());
        System.out.println("fin nb conflits  " +list.getnbconflit());
        //System.out.println("fin minconflits  " +minconflits);
        
        return list;
        
    }
    
    
    
}
