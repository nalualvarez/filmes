import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {


    static List<String> converteGeneros(String string){
        string = string.replace("\"", "");
        string = string.trim();
        String[] generos = string.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        return Arrays.asList(generos);
    }
    static Integer converteInteiro(String string){
        string = string.trim();
        if (string.isBlank()){
            return 0;
        }
        else {
            return Integer.valueOf(string);
        }
    }

    static Float converteFloat(String string){
        string = string.trim();
        if (string.isBlank()){
            return (float)0;
        }
        else {
            return Float.parseFloat(string);
        }
    }


    static  List<Movie> parseLinha(List<String> linhas){

        List<Movie> arquivo = new ArrayList<Movie>();

        List<String[]> fields = linhas.stream().skip(1).map(buffer -> {
            String[] atributos = buffer.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            return atributos;
        }).collect(Collectors.toList());;


        for (String[] strings : fields) {

            arquivo.add (new Movie(converteInteiro(strings[0]), strings[1], converteGeneros(strings[2]), strings[3], strings[4],
                        strings[5], converteInteiro(strings[6]), converteInteiro(strings[7]),converteFloat(strings[8]),
                        converteInteiro(strings[9]), converteFloat(strings[10]),converteInteiro(strings[11])));
        }

        System.out.println(arquivo.get(0));

        return arquivo;
    }


    public static void main(String[] args) {

        //roda o main pra ver o parse funcionando

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
        }

        parseLinha(arquivo1);
    }


}
