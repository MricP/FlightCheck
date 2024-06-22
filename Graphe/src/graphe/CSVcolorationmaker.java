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
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVcolorationmaker {
    
    private static int lineNumber = 1;
    private static String deb = "colo-eval";
    private static String fin = ".txt";
    private static ListeVols list;
    private static ListeVols list1;
    private static ListeVols list2;
    private static ListeVols list3;
    private static ListeVols list4;
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
                            /*
                            list1 = main.CreateGraphText(path.toFile());
                            list2 = main.CreateGraphText(path.toFile());
                            list3 = main.CreateGraphText(path.toFile());
                            list4 = main.CreateGraphText(path.toFile());
                            */
                        }catch(DonneeVolException e){
                            
                        } catch (FichierTropVolumineux ex) {
                            Logger.getLogger(CSVcolorationmaker.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        list = main.FULL_LRF(list);
                        
                        /*
                        list1 = main.DSaturFull(list1);
                        if(list1.getnbconflit() != 0){
                            list2 = main.FullGreedyColor(list2);
                            if(list2.getnbconflit() != 0){
                                list3 = main.FullWelshPowell(list3);
                            }
                        }
                        */
                        
                        //list4 = main.FullGreedyColor(list4);
                        
                        /*
                        if(list1.getnbconflit() <= list2.getnbconflit() && list1.getnbconflit() <= list3.getnbconflit() && list1.getnbconflit() <= list4.getnbconflit() ){
                            list = list1;
                        }else if( list2.getnbconflit() <= list3.getnbconflit() && list2.getnbconflit() <= list4.getnbconflit() ){
                            list = list2;
                        }else if( list3.getnbconflit() <= list4.getnbconflit() ){
                            list = list3;
                        }else{
                            list = list4;
                        }
                        */
                        //feur
                        /*
                         if(list1.getnbconflit() <= list2.getnbconflit() && list1.getnbconflit() <= list3.getnbconflit()){
                            list = list1;
                        }else if( list2.getnbconflit() <= list3.getnbconflit()){
                            list = list2;
                        }else{
                            list = list3;
                        }
                        */
                        
                        
                        /*
                        if (list1.getnbconflit() <= list2.getnbconflit()){
                            list = list1;
                        }else{
                            list = list2;
                        }
                        */
                        writer.append(fileName + ";" + list.getnbconflit()).append("\n") ;
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
        String outputFileName = "C:/Users/Robi6/OneDrive/Bureau/results/"+deb + fileNumber + fin;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            FileWriter writer = new FileWriter(outputFileName)) {
            for(int i=1; i<=list.taille();i++){
                writer.write(list.getVolId(i).getid() + ";" +list.getVolId(i).getcouleur() +"\n");
            }
            
            
        }
    }
    
    public static void main(String[] args) {
        String folderPath = "C:/Users/Robi6/OneDrive/Bureau/DataGraphTest";
        String csvFilePath = "C:/Users/Robi6/OneDrive/Bureau/results/result.csv";
        
        CSVcolorationmaker(folderPath, csvFilePath);
        
        System.out.println("Fichier CSV créé avec succès !");
    }
}
