import java.nio.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        Path movies1 = Paths.get("filmes/movies1.csv");  
        Path movies2 = Paths.get("filmes/movies2.csv");  
        Path movies3 = Paths.get("filmes/movies3.csv");  
        List<String> arquivo1 = new ArrayList<String>();
        List<String> arquivo2 = new ArrayList<String>();
        List<String> arquivo3 = new ArrayList<String>();
    
        try {
            arquivo1 = Files.readAllLines(movies1);
            arquivo2 = Files.readAllLines(movies2);
            arquivo3 = Files.readAllLines(movies3);
            
            
        } catch (Exception e) {
            System.err.println("Erro na leitura dos arquivos");
        }finally{
            
          
        }
    }
    

}
