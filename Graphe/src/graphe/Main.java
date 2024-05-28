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
            
            
    public static void creationgraphe(){
        /*
        System.out.println(LV.getVolindice(3).toString());
        System.out.println(LV.getVolindice(17).toString());
        System.out.println(intersection(LV.getVolindice(3),LV.getVolindice(17)));
        */
        
        int taille = LV.taille();
        int gpt = 0;
        System.out.println("taille  : "+ LV.taille());
        int cpt=0;
        for (int i = 0; i< taille; i++){
            for (int y=i+1; y < taille; y++){
                cpt++;
                if(intersection(LV.getVolindice(i),LV.getVolindice(y))){
                    gpt++;
                    LV.getVolindice(i).addadjacent(LV.getVolindice(y));
                    System.out.println(LV.getVolindice(i).id+ "  :  "+ LV.getVolindice(y).id);
                    LV.getVolindice(y).addadjacent(LV.getVolindice(i));
                    
                    LV.addaerrete();
                    
                }
            }
        }
        System.out.println("compteur  : "+ cpt);
        System.out.println("nbarretes  : "+ gpt);
        
        
    }
    
    
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
    
    public static ListeVols CreateGraphText(String cheminfichiertxt){
        System.out.println("rentrez le chemin d'acces de votre graphe sous forme .txt:");
        String filtePath;
        String FilePath = "C:/Users/Robi6/OneDrive/Bureau/DataTest/graph-test4.txt";
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
    
    private static ListeAeroport L = new ListeAeroport();
    private static ListeVols LV = new ListeVols();
        
    
    
    
    public static Graph getGraphStream(ListeVols liste) {
        Graph graph = new MultiGraph("multi graphe");
        ArrayList<String> couleurs = new ArrayList<String>();
        
        Random random = new Random();
        
        for (int i = 0; i < liste.maxcouleur(); i++) {
            // Génération d'une couleur aléatoire
            String couleur = String.format("#%02x%02x%02x", random.nextInt(256), random.nextInt(256), random.nextInt(256));
            couleurs.add("fill-color: " + couleur + ";");
        }
        
        // Ajouter les noms de couleurs standard à l'ArrayList
        /*
        couleurs.add("fill-color: red;");
        couleurs.add("fill-color: green;");
        couleurs.add("fill-color: blue;");
        couleurs.add("fill-color: yellow;");
        couleurs.add("fill-color: cyan;");
        couleurs.add("fill-color: magenta;");
        couleurs.add("fill-color: white;");
        couleurs.add("fill-color: black;");
        couleurs.add("fill-color: gray;");
        couleurs.add("fill-color: lightgray;");
        couleurs.add("fill-color: darkgray;");
        couleurs.add("fill-color: orange;");
        couleurs.add("fill-color: pink;");
        couleurs.add("fill-color: brown;");
        couleurs.add("fill-color: gold;");
        couleurs.add("fill-color: silver;");
        */
        
        /*
        couleurs.add("fill-color: #FF0000;");
        couleurs.add("fill-color: #FF8000;");
        couleurs.add("fill-color: #FFFF00;");
        couleurs.add("fill-color: #80FF00;");
        couleurs.add("fill-color: #00FF00;");
        couleurs.add("fill-color: #00FF80;");
        couleurs.add("fill-color: #00FFFF;");
        couleurs.add("fill-color: #0080FF;");
        couleurs.add("fill-color: #0000FF;");
        couleurs.add("fill-color: #8000FF;");
        couleurs.add("fill-color: #FF00FF;");
        couleurs.add("fill-color: #FF0080;");
        couleurs.add("fill-color: #FF8080;");
        couleurs.add("fill-color: #FFFF80;");
        couleurs.add("fill-color: #80FF80;");
        couleurs.add("fill-color: #80FFFF;");
        couleurs.add("fill-color: #8080FF;");
        couleurs.add("fill-color: #FF80FF;");
        couleurs.add("fill-color: #FFB6C1;");
        couleurs.add("fill-color: #FFA07A;");
        couleurs.add("fill-color: #FFD700;");
        couleurs.add("fill-color: #ADFF2F;");
        couleurs.add("fill-color: #00FA9A;");
        couleurs.add("fill-color: #4682B4;");
        couleurs.add("fill-color: #6A5ACD;");
        couleurs.add("fill-color: #EE82EE;");
        couleurs.add("fill-color: #D2691E;");
        couleurs.add("fill-color: #CD5C5C;");
        couleurs.add("fill-color: #8FBC8F;");
        couleurs.add("fill-color: #20B2AA;");
        */
        
        //System.out.println("feur");
        // Ajout des sommets au graphe
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
        /*System.out.println(cpt);*/
        
        
        
        return graph;
    }
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        Scanner ent = new Scanner(System.in);
        
        // TODO code application logic here
        
        
        
        // Chemin du fichier texte
        System.out.println("rentrez le chemin d'acces de votre fichier deaéroports sous forme .txt:");
        String filtePath;
        String FilePath = "C:/Users/Robi6/OneDrive/Bureau/aeroports.txt";
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
        
        
        
        // Chemin du fichier texte
        System.out.println("rentrez le chemin d'acces de votre fichier de vols en format .csv:");
        FilePath = "C:/Users/Robi6/OneDrive/Bureau/DataTest/vol-test8.csv";
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
        
        
        
        
        
        creationgraphe();
        
        
        System.out.println("feur");
        LV.WelshPowell();
        System.out.println(LV.goodcoloration());
        System.out.println("feur");
        
        System.out.println("feur");
        System.out.println(LV.maxcouleur());
        LV.setcouleurdefault();
        
        
        
        LV.GreedyColor();
        System.out.println(LV.goodcoloration());
        System.out.println("feur");
        LV.viewcolor();
        System.out.println("feur");
        System.out.println(LV.maxcouleur());
        LV.setcouleurdefault();
        
        LV.Dsatur();
        System.out.println(LV.goodcoloration());
        System.out.println("feur");
        
        System.out.println("fedfvergergr");
        System.out.println(LV.maxcouleur());
        
        
        
        /*System.out.println(intersection(LV.accesMembre("AF000090"),LV.accesMembre("AF000132")));*/
        
        
        System.out.println("nbarretes : "+ LV.getnbarrte());
        System.out.println("degre moyen : "+LV.getdegremoyen());
        System.out.println("nb composantes  : "+LV.getnbcomposante());
        //System.out.println(LV.getnbcomposantede(4));
        
        
        
        
        //System.out.println("diametre : "+ LV.getdiametre());
        
        
        /*
        LV.shownumcomposante();
        */
        
        
        
        
        
        
        //Partie graph-test
        
        ListeVols LL = CreateGraphText("feur");
        
        
        
        
        
        
        LL.tostring();
        System.out.println("nbarretes : "+ LL.getnbarrte());
        System.out.println("degre moyen : "+LL.getdegremoyen());
        System.out.println("nb composantes  : "+LL.getnbcomposante());
        
        LL.Dsatur();
        System.out.println(LL.goodcoloration());
        System.out.println("feur");
        
        System.out.println("fedfvergergr");
        System.out.println("nb de couleurs : "+LL.maxcouleur());
        System.out.println("kmax :  "+ LL.getkmax());
        LL.setcouleurdefault();
        
        LL.WelshPowell();
        System.out.println("nb de couleurs : "+LL.maxcouleur());
        System.out.println(LL.taille());
        System.out.println(LL.getnbarrte());
        
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
        
        LL = FullGreedyColor(LL);
        
        
        Graph G = getGraphStream(LL);
        System.out.println(LL.getnbconflit());
        System.out.println(LL.goodcoloration());
        
                
        //System.out.println(LL.getdiametre());
        System.out.println(org.graphstream.algorithm.Toolkit.diameter(G));
        System.out.println(LL.maxcouleur());
        
        G.display();
        
        
        
        
        
        
        
        //Partie carte
        // Créer la fenêtre principale
        
        /*
        JFrame frame = new JFrame("Carte du Monde");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        
        // Créer le composant JMapViewer
        JMapViewer mapViewer = new JMapViewer();
        frame.add(mapViewer, BorderLayout.CENTER);
        
        // Ajouter des points sur la carte
        MapMarkerDot point;
        Aeroport A ;
        
        List<MapMarkerDot> points = new ArrayList<>();
        for (int i=0; i < L.taillelisteaero(); i++){
            A = L.getaeroport(i);
            point = new MapMarkerDot(A.getlongitude(), A.getlatitude());
            point.setName(A.getcode());
            points.add(point);
            //System.out.println(A.getlatitude()+ "    "+ A.getlongitude());
            mapViewer.addMapMarker(point);
            
        }
        
        for (int i=0; i < points.size();i++){
            System.out.println(points.get(i).getName());
        }
        
        Coordinate point1 = points.get(2).getCoordinate();
        Coordinate point2 = points.get(3).getCoordinate();
        Coordinate point3 = points.get(4).getCoordinate();

        List<Coordinate> route = new ArrayList<>();
        route.add(point1);
        route.add(point2);
        route.add(point2);
        
        MapPolygonImpl polyline = new MapPolygonImpl(route);
        polyline.setColor(Color.RED);
        mapViewer.addMapPolygon(polyline);
        
        route = new ArrayList<>();
        route.add(point2);
        route.add(point3);
        route.add(point3);
        
        polyline = new MapPolygonImpl(route);
        polyline.setColor(Color.RED);
        mapViewer.addMapPolygon(polyline);
        
        
        
        
        
        // Afficher la fenêtre
        frame.setVisible(true);
        */
       
        
        
        
    }
    
    
    
    
    public static ListeVols descent(ListeVols liste){
        liste.RandomColoration(liste.getkmax());
        
        ListeVols meilleure_solution_voisine = new ListeVols(liste);
        ListeVols solution_voisine;
        
        int meilleurConflit = liste.getnbconflit();
        System.out.println("Début: " + meilleurConflit);
        int nbConflits;
        int cpt =0;
        
        for(int y=0; y < 90; y++){
            for (int i = 0; i < liste.getArraylist().size(); i++) {
                Vol v = liste.getVolindice(i);
                
                for (int couleur = 1; couleur <= liste.getkmax(); couleur++) {
                    cpt++;
                    
                    if (couleur != v.getcouleur()) {
                        solution_voisine = new ListeVols(meilleure_solution_voisine); // Créer une copie de la solution courante
                        
                        solution_voisine.getVolindice(i).setcouleur(couleur);
                        nbConflits = solution_voisine.getnbconflit();
                        
                        if (nbConflits < meilleurConflit) {
                            meilleure_solution_voisine = solution_voisine;
                            meilleurConflit = nbConflits;
                            System.out.println("Amélioration: " + meilleurConflit);
                        }
                        
                    }
                }
            }
        }
        System.out.println("Fin: " + meilleurConflit);
        System.out.println("Fin: " + meilleure_solution_voisine.getnbconflit());
        System.out.println("compteur: " + cpt);
        meilleure_solution_voisine.viewcolor();
        return meilleure_solution_voisine;
            
        
        
        
        
        
    }
    
    public static ListeVols FullGreedyColor(ListeVols list){
        ListeVols sol = null;
        int mincouleur = Integer.MAX_VALUE;
        for (int y =0;y<list.getArraylist().size();y++){
            list.setcouleurdefault();
            Collections.shuffle(list.getArraylist());
            list.GreedyColor();
            if (list.maxcouleur() < mincouleur){
                sol = new ListeVols(list);
                mincouleur = list.maxcouleur();
                System.out.println("min " +mincouleur);
            }
            
        }
        System.out.println("fin couleur min " +sol.maxcouleur());
        return sol;
        
    }
    
    
}