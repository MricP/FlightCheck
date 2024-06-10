/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphe;

/**
 *
 * @author Robi6
 */
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import javax.swing.JButton;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.WaypointPainter;

/**
 * Classe représentant un renderer pour dessiner des waypoints personnalisés sur un JXMapViewer.
 */
public class WaypointRenderer extends WaypointPainter<MyWaypoint> {
    /**
     * Surcharge de la méthode de peinture pour dessiner les waypoints sur le JXMapViewer.
     *
     * @param g      L'objet Graphics2D utilisé pour dessiner.
     * @param map    Le composant JXMapViewer sur lequel dessiner.
     * @param width  La largeur de la zone de dessin.
     * @param height La hauteur de la zone de dessin.
     */
    @Override
    protected void doPaint(Graphics2D g, JXMapViewer map, int width, int height) {
        for (MyWaypoint wp : getWaypoints()) {
            Point2D p = map.getTileFactory().geoToPixel(wp.getPosition(), map.getZoom());
            Rectangle rec = map.getViewportBounds();
            int x = (int) (p.getX() - rec.getX());
            int y = (int) (p.getY() - rec.getY());
            JButton cmd = wp.getButton();
            cmd.setLocation(x - cmd.getWidth() / 2, y - cmd.getHeight());
        }
    }
}