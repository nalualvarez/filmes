import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    static List<Movie> melhoresTerror(List<Movie> arquivo){

        arquivo.stream()
                .filter(k-> k.Genre.contains("Horror"))
                .sorted(Comparator.comparingDouble(Movie::getRating).reversed()).limit(20)
                .forEach(System.out::println);

        return null;
    }
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

//        System.out.println(arquivo.get(0));

        return arquivo;
    }


    public static void main(String[] args) {

        System.out.println("Inicio processamento: " + Instant.now());


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

        List<Movie> filmes;

        filmes = parseLinha(arquivo1);
        filmes.addAll(parseLinha(arquivo2));
        filmes.addAll(parseLinha(arquivo3));

//        System.out.println(filmes.get(800));

        melhoresTerror(filmes);

        System.out.println("Fim processamento: " + Instant.now());
    }



}
