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

public class CustomWaypointRenderer implements WaypointRenderer<Waypoint> {
    private final Icon icon;

    public CustomWaypointRenderer(Icon icon) {
        this.icon = icon;
    }

    @Override
    public void paintWaypoint(Graphics2D g, JXMapViewer map, Waypoint wp) {
        Point2D point = map.getTileFactory().geoToPixel(wp.getPosition(), map.getZoom());

        int x = (int) point.getX() - icon.getIconWidth() / 2;
        int y = (int) point.getY() - icon.getIconHeight() / 2;
        icon.paintIcon(map, g, x, y);
    }
}
