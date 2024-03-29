
package alurastickers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class AluraStickers {

    public static void main(String[] args) throws Exception {
 // FAZER UMA CONECÇÃO HTTP E BUSCAR OS TOP 250 FILMES
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        
        //var client = HttpClient.newHttpClient(); ---poderia ser var no tipo da variavel
        //o Java ja reconheceria
        HttpClient client = HttpClient.newHttpClient();
        
        // HttpRequest request = HttpRequest.newBuilder(endereco).GET().build(); --poderia ser HttpRequest no tipo da variavel
        var request = HttpRequest.newBuilder(endereco).GET().build();
        
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        
 //EXTRAIR SÓ OS DADOS QUE INTERESSÃO (TÍTULO, POSTER E CLASSIFICAÇÃO)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
 //EXIBIR E MANIPULAR OS DADOS
        var geradora = new GeradoraDeFigurinhas();
        
        for (Map<String,String> filme : listaDeFilmes) {
            String urlImagem = filme.get("image");
            String titulo = filme.get("title");
            double imDbRating = Double.parseDouble(filme.get("imDbRating"));
            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";

            String texto;
            String nota = String.valueOf(imDbRating);
            InputStream selo;
            if (imDbRating >= 8.5 ){
                texto = titulo+" ("+nota+")";
                selo = new FileInputStream("entrada/aprovado.png");
            }else{
                texto = titulo+" ("+nota+")";
                selo = new FileInputStream("entrada/reprovado.png");
            }

            geradora.criar(inputStream, nomeArquivo, texto, selo);

            System.out.println(titulo);
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            System.out.println();
        }
        
    }
    
}
