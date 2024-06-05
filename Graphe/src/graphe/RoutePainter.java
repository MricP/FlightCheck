package graphe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.GeoPosition;

/**
 * La classe RoutePainter dessine un itinéraire sur un composant JXMapViewer.
 * Elle utilise une liste de positions géographiques (GeoPosition) pour tracer le chemin.
 */
public class RoutePainter implements Painter<JXMapViewer> {
    private List<GeoPosition> track;
    private Color color;

    /**
     * Constructeur de la classe RoutePainter.
     * 
     * @param track la liste des positions géographiques représentant le chemin à tracer
     * @param color la couleur de la ligne à tracer
     */
    public RoutePainter(List<GeoPosition> track, Color color) {
        this.track = track;
        this.color = color;
    }

    /**
     * Méthode de peinture sur le composant JXMapViewer.
     * Cette méthode est appelée pour dessiner l'itinéraire sur le composant map.
     * 
     * @param g l'objet Graphics2D utilisé pour dessiner
     * @param map le composant JXMapViewer sur lequel dessiner
     * @param w la largeur du composant
     * @param h la hauteur du composant
     */
    @Override
    public void paint(Graphics2D g, JXMapViewer map, int w, int h) {
        g = (Graphics2D) g.create();
        Rectangle rect = map.getViewportBounds();
        g.translate(-rect.x, -rect.y);

        // Améliore la qualité de rendu des lignes
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(color);
        g.setStroke(new BasicStroke(1));

        int lastX = -1;
        int lastY = -1;

        for (GeoPosition gp : track) {
            Point2D pt = map.getTileFactory().geoToPixel(gp, map.getZoom());
            if (lastX != -1 && lastY != -1) {
                g.drawLine(lastX, lastY, (int) pt.getX(), (int) pt.getY());
            }
            lastX = (int) pt.getX();
            lastY = (int) pt.getY();
        }
        g.dispose();
    }
}