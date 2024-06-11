/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphe;

/**
 *
 * @author Robi6
 */
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

public class CSVcolorationmaker {
    
    private static int lineNumber = 1;
    private static String deb = "colo-eval";
    private static String fin = ".txt";
    private static ListeVols list;
    private static Main main;
    public static void CSVcolorationmaker(String folderPath, String csvFilePath) {
        main = new Main();
        try (FileWriter writer = new FileWriter(csvFilePath)) {
            
            Files.walk(Paths.get(folderPath))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".txt"))
                    .forEach(new Consumer<Path>() {
                @Override
                public void accept(Path path) {
                    String fileName = path.getFileName().toString();
                    try {
                        // Écrire le numéro de ligne et le nom du fichier dans le fichier CSV
                        
                        try{
                            list = main.CreateGraphText(path.toFile());
                        }catch(DonneeVolException e){
                            
                        }
                        list = main.FullGreedyColor(list);
                        writer.append(fileName + ";" + list.getnbconflit()).append("\n");
                        createTextFileWithFirstLine(path.toFile(), lineNumber);
                        lineNumber++; // Incrémenter le numéro de ligne
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void createTextFileWithFirstLine(File inputFile, int fileNumber) throws IOException {
        String outputFileName = "C:/Users/Robi6/OneDrive/Bureau/"+deb + fileNumber + fin;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            FileWriter writer = new FileWriter(outputFileName)) {
            for(int i=1; i<=list.taille();i++){
                writer.write(list.getVolId(i).getid() + ";" +list.getVolId(i).getcouleur() +"\n");
            }
            
            
        }
    }
    
    public static void main(String[] args) {
        String folderPath = "C:/Users/Robi6/OneDrive/Bureau/DataGraphTest";
        String csvFilePath = "C:/Users/Robi6/OneDrive/Bureau/result.csv";
        
        CSVcolorationmaker(folderPath, csvFilePath);
        
        System.out.println("Fichier CSV créé avec succès !");
    }
}
