/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphe;

/**
 *
 * @author Robi6
 */
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

/**
 * La classe CustomWaypointRenderer est un rendu personnalisé pour les waypoints sur une carte.
 * <p>
 * Cette classe implémente l'interface {@link WaypointRenderer} pour dessiner des icônes personnalisées
 * à des positions spécifiques sur une carte.
 * </p>
 * 
 * @see WaypointRenderer
 */
public class CustomWaypointRenderer implements WaypointRenderer<Waypoint> {
    private final Icon icon;
    
    /**
     * Constructeur de la classe CustomWaypointRenderer.
     * 
     * @param icon l'icône à utiliser pour représenter les waypoints
     */
    public CustomWaypointRenderer(Icon icon) {
        this.icon = icon;
    }
    
    /**
     * Dessine le waypoint sur la carte.
     * <p>
     * Cette méthode convertit la position géographique du waypoint en coordonnées pixel,
     * puis dessine l'icône centrée sur cette position.
     * </p>
     * 
     * @param g le contexte graphique utilisé pour dessiner l'icône
     * @param map la carte sur laquelle dessiner le waypoint
     * @param wp le waypoint à dessiner
     */
    @Override
    public void paintWaypoint(Graphics2D g, JXMapViewer map, Waypoint wp) {
        Point2D point = map.getTileFactory().geoToPixel(wp.getPosition(), map.getZoom());

        int x = (int) point.getX() - icon.getIconWidth() / 2;
        int y = (int) point.getY() - icon.getIconHeight() / 2;
        icon.paintIcon(map, g, x, y);
    }
}