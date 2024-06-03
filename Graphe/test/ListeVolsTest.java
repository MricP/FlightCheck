/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import graphe.ListeVols;
import graphe.Vol;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Emric
 */
public class ListeVolsTest {
    ListeVols liste;
    Vol vol,vol2;
    
    public ListeVolsTest() {
    }
    
    @Before
    public void setUp() {
        liste = new ListeVols();
        vol = new Vol("AF605837","SBK","CTT",10,59,74);
        vol2 = new Vol("AF978045","PUF","URO",13,41,52);

    }

    @Test
    public void getkmaxTest() {
        liste.setkmax(5);
        assertEquals(5, liste.getkmax());
    }
    
    @Test
    public void havekmaxTest() {
        assertFalse(liste.havekmax());
        liste.setkmax(3);
        assertTrue(liste.havekmax());
    }
    
    @Test
    public void setkmaxTest() {
        liste.setkmax(4);
        assertTrue(liste.havekmax());
        assertEquals(4, liste.getkmax());
    }
   
    @Test
    public void getnbarrteTest() {
        liste.setnbarrete(10);
        assertEquals(10, liste.getnbarrte());
    }
    
    @Test
    public void ajMembreTest() {
        liste.ajMembre(vol);
        assertEquals(1, liste.taille());
    }
    
    @Test
    public void accesMembrenomTest() {
        liste.ajMembre(vol);
        assertEquals(vol, liste.accesMembrenom("AF605837"));
    }
    
    @Test
    public void accesMembrenumTest() {
        liste.ajMembre(vol);
        assertEquals(vol, liste.accesMembrenum(vol.getid()));
    }
    
    @Test
    public void setnbarreteTest() {
        liste.setnbarrete(5);
        assertEquals(5, liste.getnbarrte());
    }
    
    @Test
    public void tailleTest() {
        assertEquals(0, liste.taille());
        liste.ajMembre(vol);
        assertEquals(1, liste.taille());
    }
    
    @Test
    public void getVolindiceTest() {
        liste.ajMembre(vol);
        assertEquals(vol, liste.getVolindice(0));
    }
    
    @Test
    public void getVolnumeroTest() {
        liste.ajMembre(vol);
        assertEquals(vol, liste.getVolnumero(vol.getid()));
    }
    
    @Test
    public void MAXWelshPowellTest() {
        liste.ajMembre(vol);
        liste.ajMembre(vol2);
        liste.setkmax(3);
        int nbCouleurs = liste.MAXWelshPowell();
        assertTrue(nbCouleurs <= 3);
    }
    
    @Test
    public void WelshPowellTest() {
        liste.ajMembre(vol);
        liste.ajMembre(vol2);
        int nbCouleurs = liste.WelshPowell();
        assertTrue(nbCouleurs > 0);
    }
    
    @Test
    public void RandomColorationTest() {
        liste.ajMembre(vol);
        liste.ajMembre(vol2);
        liste.RandomColoration(3);
        assertTrue(vol.getcouleur() > 0 && vol.getcouleur() <= 3);
        assertTrue(vol2.getcouleur() > 0 && vol2.getcouleur() <= 3);
    }
    
    @Test
    public void GreedyColorTest() {
        liste.ajMembre(vol);
        liste.ajMembre(vol2);
        liste.GreedyColor();
        assertTrue(vol.getcouleur() > 0);
        assertTrue(vol2.getcouleur() > 0);
    }
    
    @Test
    public void DsaturTest() {
        liste.ajMembre(vol);
        liste.ajMembre(vol2);
        liste.Dsatur();
        assertTrue(vol.getcouleur() > 0);
        assertTrue(vol2.getcouleur() > 0);
    }
    
    @Test
    public void getListTest() {
        liste.ajMembre(vol);
        ArrayList<Vol> vols = liste.getList();
        assertEquals(1, vols.size());
        assertEquals(vol, vols.get(0));
    }
    
    @Test
    public void getnbconflitTest() {
        liste.ajMembre(vol);
        liste.ajMembre(vol2);
        vol.addadjacent(vol2);
        vol2.addadjacent(vol);
        liste.RandomColoration(3);
        int conflits = liste.getnbconflit();
        assertTrue(conflits >= 0);
    }
    
    @Test
    public void goodcolorationTest() {
        liste.ajMembre(vol);
        liste.ajMembre(vol2);
        vol.addadjacent(vol2);
        vol2.addadjacent(vol);
        liste.RandomColoration(3);
        assertTrue(liste.goodcoloration());
    }
    
    @Test      
    public void maxcouleurTest() {
        liste.ajMembre(vol);
        liste.ajMembre(vol2);
        liste.RandomColoration(3);
        int maxCouleur = liste.maxcouleur();
        assertTrue(maxCouleur > 0 && maxCouleur <= 3);
    }
    
    @Test
    public void setcouleurdefaultTest() {
        liste.ajMembre(vol);
        vol.setcouleur(1);
        liste.setcouleurdefault();
        assertEquals(-1, vol.getcouleur());
    }
    
    @Test
    public void getdegremoyenTest() {
        liste.ajMembre(vol);
        liste.ajMembre(vol2);
        vol.addadjacent(vol2);
        vol2.addadjacent(vol);
        assertEquals(1.0, liste.getdegremoyen(), 0.001);
    }
   
    @Test 
    public void getnbcomposanteTest() {
        liste.ajMembre(vol);
        liste.ajMembre(vol2);
        assertEquals(2, liste.getnbcomposante());
    }
    
    @Test
    public void getdiametreTest() {
        liste.ajMembre(vol);
        liste.ajMembre(vol2);
        vol.addadjacent(vol2);
        vol2.addadjacent(vol);
        assertEquals(1, liste.getdiametre());
    }
    
    @Test
    public void DijkstraTest() {
        liste.ajMembre(vol);
        liste.ajMembre(vol2);
        vol.addadjacent(vol2);
        vol2.addadjacent(vol);
        assertEquals(1, liste.Dijkstra(vol, vol2));
    }

    @Test
    public void getnbcomposantedeTest() {
        liste.ajMembre(vol);
        liste.ajMembre(vol2);
        assertEquals(2, liste.getnbcomposantede(vol.getcomposante()));
    }
    
    @Test
    public void shownumcomposanteTest() {
        liste.ajMembre(vol);
        liste.shownumcomposante();
    }
    
    @Test
    public void getArraylistTest() {
        liste.ajMembre(vol);
        ArrayList<Vol> vols = liste.getArraylist();
        assertEquals(1, vols.size());
        assertEquals(vol, vols.get(0));
    }
    
    @Test
    public void getListVolsTest() {
        assertEquals(liste, liste.getListVols());
    }
    
    @Test
    public void getVolIdTest() {
        liste.ajMembre(vol);
        assertEquals(vol, liste.getVolId(vol.getid()));
    }
    
    @Test
    public void getcouleursTest() {
        liste.ajMembre(vol);
        liste.ajMembre(vol2);
        liste.RandomColoration(3);
        ArrayList<Integer> couleurs = liste.getcouleurs();
        assertEquals(2, couleurs.size());
        assertTrue(couleurs.get(0) > 0 && couleurs.get(0) <= 3);
        assertTrue(couleurs.get(1) > 0 && couleurs.get(1) <= 3);
    }
    
    @Test
    public void adresscouleursTest() {
        liste.ajMembre(vol);
        liste.ajMembre(vol2);
        ArrayList<Integer> couleurs = new ArrayList<>();
        couleurs.add(1);
        couleurs.add(2);
        liste.adresscouleurs(couleurs);
        assertEquals(1, vol.getcouleur());
        assertEquals(2, vol2.getcouleur());
    }
}
