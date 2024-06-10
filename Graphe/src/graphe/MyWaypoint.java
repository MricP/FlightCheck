package graphe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

/**
 * Classe représentant un waypoint personnalisé, étendant {@link DefaultWaypoint}, avec un nom et un bouton associé.
 */
public class MyWaypoint extends DefaultWaypoint {
    /**
     * Retourne le nom du waypoint.
     * 
     * @return Le nom du waypoint.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Définit le nom du waypoint.
     * 
     * @param name Le nouveau nom du waypoint.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retourne le bouton associé au waypoint.
     * 
     * @return Le bouton associé au waypoint.
     */
    public JButton getButton() {
        return button;
    }

    /**
     * Définit le bouton associé au waypoint.
     * 
     * @param button Le nouveau bouton associé au waypoint.
     */
    public void setButton(JButton button) {
        this.button = button;
    }
    
    /**
     * Constructeur avec paramètres.
     * 
     * @param name   Le nom du waypoint.
     * @param coord  La position géographique du waypoint.
     */
    public MyWaypoint(String name, GeoPosition coord) {
        super(coord);
        this.name = name;
        initButton();
    }
    
    /**
     * Constructeur par défaut.
     */
    public MyWaypoint() {
    }
    
    private String name;
    private JButton button;
    
    /**
     * Initialise le bouton du waypoint.
     */
    private void initButton() {
        button = new ButtonWaypoint();
        
    }
}
