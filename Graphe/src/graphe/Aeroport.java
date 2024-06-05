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

    /**
     * Constructeur de la classe Aeroport.
     *
     * @param code        Le code de l'aéroport.
     * @param Nlieu       Le lieu de l'aéroport.
     * @param Ndegre1     Le degré de latitude.
     * @param Nminute1    La minute de latitude.
     * @param Nseconde1   La seconde de latitude.
     * @param Norientation1 L'orientation de latitude (N ou S).
     * @param Ndegre2     Le degré de longitude.
     * @param Nminute2    La minute de longitude.
     * @param Nseconde2   La seconde de longitude.
     * @param Norientation2 L'orientation de longitude (E ou W).
     */
    public Aeroport(String code, String Nlieu, int Ndegre1, int Nminute1, int Nseconde1, String Norientation1, int Ndegre2, int Nminute2, int Nseconde2, String Norientation2){
        codeaeroport = code;
        lieu  = Nlieu;
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
        Longitude = (Longitude * (degre1 + (minute1/60) + (seconde1/3600))) ;
        Latitude = (Latitude * (degre2 + (minute2/60) + (seconde2/3600))) ;
        double var1 = (Math.toRadians(Longitude));
        double var2 = (Math.toRadians(Latitude));
        X = (R *  Math.cos(var1) * Math.sin(var2));
        Y = (R *  Math.cos(var1) * Math.cos(var2));
    }

    /**
     * Retourne la latitude de l'aéroport.
     *
     * @return La latitude de l'aéroport.
     */
    public double getlatitude() {
        return Latitude;
    }

    /**
     * Retourne la longitude de l'aéroport.
     *
     * @return La longitude de l'aéroport.
     */
    public double getlongitude() {
        return Longitude;
    }

    /**
     * Retourne le code de l'aéroport.
     *
     * @return Le code de l'aéroport.
     */
    public String getcode() {
        return codeaeroport;
    }

    /**
     * Retourne le lieu de l'aéroport.
     *
     * @return Le lieu de l'aéroport.
     */
    public String getlieu() {
        return lieu;
    }

    /**
     * Retourne la coordonnée X de l'aéroport.
     *
     * @return La coordonnée X de l'aéroport.
     */
    public double getX() {
        return X;
    }

    /**
     * Retourne la coordonnée Y de l'aéroport.
     *
     * @return La coordonnée Y de l'aéroport.
     */
    public double getY() {
        return Y;
    }

    /**
     * Retourne une chaîne de caractères représentant l'aéroport.
     *
     * @return Une chaîne de caractères représentant l'aéroport.
     */
    @Override
    public String toString() {
        return "code : " + codeaeroport + " lieu : " + lieu + " X : " + X + " Y : " + Y;
    }
}
