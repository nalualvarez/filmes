import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    static  List<Movie> parseLinha(List<String> linhas){

//        System.out.println(linhas);

        List<Movie> arquivo = new ArrayList<Movie>();

        List<String[]> fields = linhas.stream().skip(1).map(buffer -> {
            String[] atributos = buffer.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            return atributos;
        }).collect(Collectors.toList());;


        //atributos aqui
        //TODO: inicializar o objeto  List<Movie> arquivo
        for (String[] strings : fields) {

            System.out.printf(strings[0]);
            System.out.println(strings[1]);
            System.out.println(strings[2]);
            System.out.println(strings[3]);
            System.out.println(strings[4]);
            System.out.println(strings[5]);
            System.out.println(strings[6]);
            System.out.println(strings[7]);
            System.out.println(strings[8]);
            System.out.println(strings[9]);
            System.out.println(strings[10]);
            System.out.println(strings[11]);

//            arquivo.add (new Movie(Integer.valueOf(strings[0]), strings[1], strings[2], strings[3], strings[4],
//                        strings[5], Integer.valueOf(strings[6]), Integer.valueOf(strings[7]),Float.parseFloat(strings[8]),
//                        Integer.valueOf(strings[9]), Float.parseFloat(strings[10]),Integer.valueOf(strings[11])));

        }

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
