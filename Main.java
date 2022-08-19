import static java.util.stream.Collectors.groupingBy;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Instant inicio = Instant.now();

        // LocalDateTime inicioLDT = LocalDateTime.ofInstant(inicio, ZoneId.systemDefault());

        // System.out.println(
        // "Inicio processamento: " +
        // inicioLDT.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS")));

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

        // System.out.println(filmes.get(800));

        List<Movie> melhoresFilmes = melhoresTerror(filmes);
        salvarArquivoMelhores(melhoresFilmes);

        Map<Integer, List<Movie>> filmesPorAno = melhoresAnos(filmes);
        salvarArquivosPorAno(filmesPorAno);

        Instant fim = Instant.now();

        LocalDateTime fimLDT = LocalDateTime.ofInstant(inicio, ZoneId.systemDefault());

        // System.out
        // .println("Fim processamento: " +
        // fimLDT.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS")));

        // Duration duracao = Duration.between(inicio, fim);

        // System.out.println("Tempo em milissegundos: " + duracao.toMillis() + "
        // milissegundos");

        // System.out.println("Tempo em segundos: " + duracao.toSeconds() + "
        // segundos");

        salvarTempoProcessamento(inicio, fim, fimLDT);

        System.out.println("Arquivos gerados com sucesso!");

    }

    private static void salvarArquivosPorAno(Map<Integer, List<Movie>> filmesPorAno) {
        criarPasta("filmesPorAno/");
        for (Integer ano : filmesPorAno.keySet()) {
            try {
                Path arquivoAno = Paths.get("filmesPorAno/" + ano + ".txt");
                List<String> texto = filmesPorAno.get(ano).stream()
                        .map(m -> m.toString())
                        .collect(Collectors.toList());
                Files.write(arquivoAno, texto, StandardCharsets.UTF_8);
            } catch (IOException ex) {
                System.out.println("Não foi possível escrever arquivo de saída.");
            }
        }
    }

    private static void salvarArquivoMelhores(List<Movie> melhoresFilmes) {
        criarPasta("melhoresFilmes/");
        try {
            Path arquivoAno = Paths.get("melhoresFilmes/terror.txt");
            List<String> texto = melhoresFilmes.stream()
                    .map(m -> m.toString())
                    .collect(Collectors.toList());
            Files.write(arquivoAno, texto, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            System.out.println("Não foi possível escrever arquivo de saída.");
        }
    }

    private static void criarPasta(String caminho) {
        try {
            Path pasta = Paths.get(caminho);
            Files.createDirectories(pasta);
        } catch (IOException ex) {
            throw new IllegalStateException();
        }
    }

    private static Map<Integer, List<Movie>> melhoresAnos(List<Movie> filmes) {

        Map<Integer, List<Movie>> filmesPorAno = filmes.stream().collect(groupingBy(Movie::getYear));

        for (int ano : filmesPorAno.keySet()) {
            List<Movie> lista = filmesPorAno.get(ano);
            lista = lista.stream()
                    .sorted(Comparator.comparingDouble(Movie::getRating).reversed())
                    .limit(50)
                    .collect(Collectors.toList());
            filmesPorAno.put(ano, lista);
        }

        return filmesPorAno;
    }

    private static List<Movie> melhoresTerror(List<Movie> arquivo) {
        return arquivo.stream()
                .filter(k -> k.Genre.contains("Horror"))
                .sorted(Comparator.comparingDouble(Movie::getRating).reversed()).limit(20)
                .collect(Collectors.toList());
    }

    private static List<String> converteGeneros(String string) {
        string = string.replace("\"", "");
        string = string.trim();
        String[] generos = string.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        return Arrays.asList(generos);
    }

    private static Integer converteInteiro(String string) {
        string = string.trim();
        if (string.isBlank()) {
            return 0;
        } else {
            return Integer.valueOf(string);
        }
    }

    private static Float converteFloat(String string) {
        string = string.trim();
        if (string.isBlank()) {
            return (float) 0;
        } else {
            return Float.parseFloat(string);
        }
    }

    private static List<Movie> parseLinha(List<String> linhas) {

        List<Movie> arquivo = new ArrayList<Movie>();

        List<String[]> fields = linhas.stream().skip(1).map(buffer -> {
            String[] atributos = buffer.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            return atributos;
        }).collect(Collectors.toList());
        ;

        for (String[] strings : fields) {

            arquivo.add(new Movie(converteInteiro(strings[0]), strings[1], converteGeneros(strings[2]), strings[3],
                    strings[4],
                    strings[5], converteInteiro(strings[6]), converteInteiro(strings[7]), converteFloat(strings[8]),
                    converteInteiro(strings[9]), converteFloat(strings[10]), converteInteiro(strings[11])));
        }

        // System.out.println(arquivo.get(0));

        return arquivo;
    }

    private static void salvarTempoProcessamento(Instant inicio, Instant fim, LocalDateTime fimLDT) {
        criarPasta("tempoProcessamento/");

        try {
            Path arquivoProcessamento = Paths.get("tempoProcessamento/tempo.txt");
            Duration duracao = Duration.between(inicio, fim);
            List<String> texto = List.of(
                    "Fim processamento: " +
                            fimLDT.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS")),
                    "Tempo em milissegundos: " + duracao.toMillis() + " milissegundos",
                    "Tempo em segundos: " + duracao.toSeconds() + " segundos");
            Files.write(arquivoProcessamento, texto, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            System.out.println("Não foi possível escrever arquivo de saída.");
        }

    }
}
