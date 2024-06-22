/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import graphe.Aeroport;
import graphe.ListeAeroport;
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
public class ListeAeroportTest {
    Aeroport aeroport;
    Aeroport aeroport2;
    ListeAeroport liste;
    public ListeAeroportTest() {
    }
    
    @Before
    public void setUp() {
        liste = new ListeAeroport();
        aeroport = new Aeroport("CDG", "Paris-Charles de Gaulle",49, 0, 35, "N",2, 32, 52, "E");
        aeroport2 = new Aeroport("EBU","Saint-Etienne",45,32,26,"N",4,17,47,"E");
    }
    
    @Test
    public void ajAeroportTest(){
        liste.ajAeroport(aeroport);
        assertEquals(1,liste.taillelisteaero());
        assertNotNull(liste.taillelisteaero());
    }
    
    @Test
    public void accesAeroportTest() {
       liste.ajAeroport(aeroport2);
       assertEquals(aeroport2,liste.accesAeroport("EBU"));
       assertNotNull(liste.accesAeroport("EBU"));
    }
    
    @Test
    public void taillelisteaeroTest(){
        liste.ajAeroport(aeroport);
        liste.ajAeroport(aeroport2);
        assertEquals(2,liste.taillelisteaero());
        assertNotNull(liste.taillelisteaero());
    }
    
    @Test
    public void getaeroportTest(){
        liste.ajAeroport(aeroport);
        liste.ajAeroport(aeroport2);
        liste.getaeroport(1);
        assertEquals(aeroport2,liste.getaeroport(1));
        assertNotNull(liste.getaeroport(1));
    }
}
