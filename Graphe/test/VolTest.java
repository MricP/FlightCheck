/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */


import graphe.Vol;
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
public class VolTest {
    Vol vol;
    Vol vol2;
    public VolTest() {
    }
  
    @Before
    public void setUp() {
        vol = new Vol("AF605837","SBK","CTT",10,59,74);
        vol2 = new Vol("AF978045","PUF","URO",13,41,52);
        vol2.setdistance(Integer.MAX_VALUE);
    }
    
    @After
    public void tearDown() {
        vol = null;
        vol2 = null;
    }

    @Test
    public void getMinuteDepartTest(){
        assertEquals(659, vol.getminutesdepart(),0);
    }
    
    @Test
    public void getminutes_arriveTest(){
        assertEquals(659+74,vol.getminutes_arrive(),0);
        
    }
    
    @Test
    public void getHeureDepartTest(){
        assertEquals(1059, vol.getheuredepart(),0);
    }
    
     @Test
    public void getheurearriveeTest(){
        assertEquals(1213, vol.getheurearrive(),0);
    }
    
    @Test
    public void getDureeTest(){
        assertEquals(74, vol.getduree(),0);
    }
    
    @Test
    public void getTraiteTest(){
      assertEquals(false,vol.gettraite());
    }
    
    @Test
    public void setTraiteTest(){
      vol.settraite(true);
      assertEquals(true,vol.gettraite());
    }
    
    @Test
    public void setdistanceTest(){
        vol.setdistance(5);
        assertEquals(5, vol.getdistance());
    }
   
    @Test
    public void getnbadjacentsTest(){
       assertEquals(0, vol.getnbadjacents());
    }
    
    @Test
    public void getcodedepartTest(){
        assertEquals("SBK", vol.getcodedepart());
    }
    
    @Test
    public void getcodearriveTest(){
       assertEquals("CTT", vol.getcodearrive());
    }
    
    @Test
    public void getnomTest(){
        assertEquals("AF605837", vol.getnom());
    }
    
    @Test
    public void addadjacentTest(){
        vol.addadjacent(vol2);
        assertEquals(1,vol.getnbadjacents());
    }
    
    @Test
    public void estAdjacentTest() {
        vol.addadjacent(vol2);
        assertEquals(true,vol.estAdjacent(vol2));
    }
    
    @Test
    public void estAdjacentidTest() {
        assertEquals(false,vol.estAdjacentid(0));
    }
    
    @Test
    public void setcouleurTest(){
        vol.setcouleur(2);
        assertEquals(2,vol.getcouleur());
    }
    
    @Test
    public void getcouleurTest(){
       assertEquals(-1,vol.getcouleur());
    }
    
    @Test
    public void possepasdeadjcouleurTest(){
        assertEquals(true,vol.possepasdeadjcouleur(-1));
    }
    
    @Test
    public void first_available_colorTest(){
        vol.addadjacent(vol2);
        vol2.setcouleur(1);
        assertEquals(2, vol.first_available_color());
    }
    
    @Test
    public void DSATTest(){
        vol.addadjacent(vol2);
        vol2.setcouleur(1);
        assertEquals(1, vol.DSAT());
    }   
    
    @Test
    public void goodcolorTest(){
        vol.setcouleur(1);
        vol.addadjacent(vol2);
        vol2.setcouleur(2);
        assertTrue(vol.goodcolor());
    }
    
    @Test
    public void distanceTest(){
        vol.addadjacent(vol2);
        vol.setdistance(5);
        assertEquals(-1, vol.distance(vol2)); 
    }
    
    @Test
    public void setcomposanteTest(){
        vol.setcomposante(3);
        assertEquals(3, vol.getcomposante());
    }
    
    @Test
    public void getcomposanteTest(){
        vol.setcomposante(2);
        assertEquals(2, vol.getcomposante());
    }
    
    @Test
    public void composanteTest(){
        vol.setcomposante(-1);
        vol.addadjacent(vol2);
        assertTrue(vol.composante(1));
        assertEquals(1, vol.getcomposante());
    }
    
    @Test
    public void DijkstraTest(){
        vol.setdistance(0); 
        vol.addadjacent(vol2);
        vol.Dijkstra();
        assertEquals(1, vol2.getdistance());
    }    
    
    @Test
    public void getAdjacentindiceTest(){  
        vol.addadjacent(vol2);
        assertEquals(vol2, vol.getAdjacentindice(0));
    }
    
    @Test
    public void zzzTest(){
        vol.addadjacent(vol2);
        vol2.setcouleur(2);
        assertEquals(1, vol.firstcoloravailable(3));
    }
}
