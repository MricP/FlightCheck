/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import graphe.Aeroport;
import graphe.DonneeVolException;
import graphe.FichierTropVolumineux;
import graphe.ListeAeroport;
import graphe.ListeVols;
import graphe.Main;
import graphe.Statistiques;
import graphe.Vol;
import java.io.File;
import org.graphstream.graph.Graph;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Emric
 */


public class MainTest {

    private Main main;
    private ListeVols testListeVols;
    private ListeAeroport testListeAeroport;
    private Vol v1;
    private Vol v2;
    
    @Before
    public void setUp() {
        main = new Main();
        testListeVols = new ListeVols();
        testListeAeroport = new ListeAeroport();
        Aeroport a1 = new Aeroport("CDG", "Paris-Charles de Gaulle",49, 0, 35, "N",2, 32, 52, "E");
        Aeroport a2 = new Aeroport("EBU","Saint-Etienne",45,32,26,"N",4,17,47,"E");
        testListeAeroport.ajAeroport(a2);
        testListeAeroport.ajAeroport(a1);
        v1 = new Vol("AF605837","CDG","EBU",10,59,74);
        v2 = new Vol("AF978045","EBU","CDG",13,41,52);
        main.setlisteaero(testListeAeroport);
        main.setlistevols(testListeVols);
    }

    @After
    public void tearDown(){
        main = null;
        testListeVols = null;
        testListeAeroport = null;
        v1 = null;
        v2 = null;
    }
    
    @Test
    public void testCreationGraphe() {
        testListeVols.ajMembre(v1);
        testListeVols.ajMembre(v2);
        ListeVols graphe = main.creationgraphe(testListeVols, 15);
        assertEquals(2, graphe.taille());
        assertNotNull(graphe.taille());
    }

    @Test
    public void testIntersection() {
        assertFalse(Main.intersection(v1, v2, 15));
        assertNotNull(Main.intersection(v1, v2, 15));
    }

    @Test
    public void testGetGraphStream() {
        v1.setcouleur(1);
        v2.setcouleur(2);
        testListeVols.ajMembre(v1);
        testListeVols.ajMembre(v2);
        ListeVols graphe = main.creationgraphe(testListeVols, 15);
        main.setlistevols(graphe);
        Graph graph = main.getGraphStream(graphe);
        assertNotNull(graphe);
        assertNotNull(graph);
        assertEquals(2, graph.getNodeCount());
        assertEquals(0, graph.getEdgeCount());
    }

    @Test
    public void testSetVolAeroports() {
        File testFile = new File("C:/Users/Emric/OneDrive/Bureau/S2/SaeFin/sae_mathieu_petit_pirrera/DataTest/vol-test8.csv");
        try{
            try{
                main.setvolaeroports(testFile);
            }
           catch(FichierTropVolumineux eg){}
        }
        catch(DonneeVolException e){
            System.err.println(e.getMessage());
        }
        assertEquals(605, main.getlisteVols().taille());

    }

    @Test
    public void testException() {
        File testFile = new File("C:/Users/Emric/OneDrive/Bureau/S2/SaeFin/sae_mathieu_petit_pirrera/DataTest/volTestPasBonneDonnées.csv");
        try {
            main.setvolaeroports(testFile);
            fail("Expected DonneeVolException to be thrown");
        } catch (DonneeVolException e) {
            System.err.println(e.getMessage());
        } catch (FichierTropVolumineux e) {
            fail("Unexpected FichierTropVolumineux exception: " + e.getMessage());
        } 
    }

    @Test
    public void testSetAeroportlist() {      
        assertEquals(2, main.getlisteaero().taillelisteaero());
        assertNotNull(main.getlisteaero().taillelisteaero());
    }

    @Test
    public void testGetDiametre() {
        ListeVols graphe = main.creationgraphe(testListeVols, 15);
        double diametre = main.getDiametre(graphe);
        assertTrue(diametre > 0);
        assertNotNull(diametre);
    }
    
    @Test
    public void FullWelshPowellTest(){
        testListeVols.ajMembre(v1);
        testListeVols.ajMembre(v2);
        main.FullWelshPowell(testListeVols);
        assertTrue(testListeVols.goodcoloration());
        assertEquals(1,testListeVols.maxcouleur());
        assertTrue(testListeVols.getnbconflit() == 0);
        assertNotNull(testListeVols.goodcoloration());
        assertNotNull(testListeVols.maxcouleur());
        assertNotNull(testListeVols.getnbconflit());
    }
    
    @Test
    public void FullGreedyColorTest(){
        testListeVols.ajMembre(v1);
        testListeVols.ajMembre(v2);
        main.FullGreedyColor(testListeVols);
        assertTrue(testListeVols.goodcoloration());
        assertEquals(1,testListeVols.maxcouleur());
        assertTrue(testListeVols.getnbconflit() == 0);
        assertNotNull(testListeVols.goodcoloration());
        assertNotNull(testListeVols.maxcouleur());
        assertNotNull(testListeVols.getnbconflit());
    }
}

