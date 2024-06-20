/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import graphe.Aeroport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;
import java.util.ArrayList;
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
public class AeroportTest {
    Aeroport aeroport;
    public AeroportTest() {
    }

    @Before
    public void setUP(){
        aeroport = new Aeroport("CDG", "Paris-Charles de Gaulle",49, 0, 35, "N",2, 32, 52, "E");
    }
    @Test
    public void testOpenfile() throws IOException {
        String filePath = "C:/Users/Emric/OneDrive/Bureau/S2/SaeFin2/sae_mathieu_petit_pirrera/DataTest/aeroports.txt";
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        reader.close();
    }
    @Test
    public void getlongitudeTest(){
      double expectedlongitude = 49.00972222222222;
        assertEquals(expectedlongitude, aeroport.getlongitude(),0.00000000000001);
    } 
    
    @Test
    public void getlatitudeTest(){
      double expectedlongitude = 2.5477777777777777;
        assertEquals(expectedlongitude, aeroport.getlatitude(),0.00000000000001);
    } 
    
    @Test
    public void getcodeTest(){
        assertEquals("CDG",aeroport.getcode());
    }
    
    @Test
    public void getlieuTest(){
        assertEquals("Paris-Charles de Gaulle", aeroport.getlieu());
    }
    
    @Test
    public void getXTest() {
        double expectedX = 6371 * Math.cos(Math.toRadians(aeroport.getlongitude())) * Math.sin(Math.toRadians(aeroport.getlatitude()));
        assertEquals(expectedX, aeroport.getX(),0.0001);

    }

    @Test
    public void getYTest() {
        double expectedY = 6371 * Math.cos(Math.toRadians(aeroport.getlongitude())) * Math.cos(Math.toRadians(aeroport.getlatitude()));
        assertEquals(expectedY, aeroport.getY(),0.0001);
    }

    
   
}
