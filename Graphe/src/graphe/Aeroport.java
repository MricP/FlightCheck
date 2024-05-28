/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphe;

/**
 *
 * @author Robi6
 * Classe Aeroport :
 * représente un aéroport français avec ses coordonnées et ses noms/codes
 * 
 */
public class Aeroport {
    private String codeaeroport;
    private String lieu;
    
    private double degre1;
    private double minute1;
    private double seconde1;
    private String orientation1;
    private double degre2;
    private double minute2;
    private double seconde2;
    private String orientation2;
    private double Longitude;
    private double Latitude;
    private double X;
    private double Y;
    
    
    
    Aeroport(String code, String Nlieu, int Ndegre1, int Nminute1, int Nseconde1, String Norientation1, int Ndegre2, int Nminute2, int Nseconde2, String Norientation2){
        codeaeroport = code;
        lieu  =Nlieu;
        degre1 = Ndegre1;
        minute1 = Nminute1;
        seconde1 = Nseconde1;
        orientation1 = Norientation1;
        degre2 = Ndegre2;
        minute2 = Nminute2;
        seconde2 = Nseconde2;
        orientation2 = Norientation2;
        
        double R = 6371;
        if (orientation1.equals("N")) {
            Longitude = 1;
        } else {
            Longitude = -1;
        }
        if (orientation2.equals("E")) {
            Latitude = 1;
        } else {
            Latitude = -1;
        }   
        /*System.out.println(Longitude + " "+ Latitude);*/
        Longitude = (Longitude * (degre1 + (minute1/60) + (seconde1/3600))) ;
        Latitude = (Latitude * (degre2 + (minute2/60) + (seconde2/3600))) ;
        double var1 = (Math.toRadians(Longitude));
        double var2 = (Math.toRadians(Latitude));
        X = (R *  Math.cos(var1) * Math.sin(var2));
        Y = (R *  Math.cos(var1) * Math.cos(var2));
        /*
        System.out.println(Longitude + " "+ Latitude);
        System.out.println(X + " "+ Y);
        */
        
    }  
    
    public double getlatitude(){
        return Latitude;
    }
    
    public double getlongitude(){
        return Longitude;
    }
    
    public String getcode(){
        return codeaeroport;
    }
    
    public String getlieu(){
        return lieu;
    }
    
    public double getX(){
        return X;
    }
    
     public double getY(){
        return Y;
    }
     
    public String toString(){
         return ("code : "+ codeaeroport + " lieu : "+ lieu + " X : "+ X + " Y : "+ Y);
    }
}
        