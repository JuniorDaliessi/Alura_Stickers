
package alurastickers;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class AluraStickers {

    public static void main(String[] args) throws Exception {
 // FAZER UMA CONECÇÃO HTTP E BUSCAR OS TOP 250 FILMES
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        //String url = "https://api.nasa.gov/planetary/apod?api_key=O98wlfWnjIw52SkRRMgtwyKHUK7ccQJu4lHkFi0M&start_date=2022-06-12&end_date=2022-06-14";

        var http = new ClienteHttp();
        var bodyJson = http.buscaDados(url);
        
        
 //EXTRAIR SÓ OS DADOS QUE INTERESSÃO (TÍTULO, POSTER E CLASSIFICAÇÃO)
        var parser = new JsonParser();
        List<Map<String, String>> listaConteudos = parser.parse(bodyJson);
        
 //EXIBIR E MANIPULAR OS DADOS
        var geradora = new GeradoraDeFigurinhas();

        for (Map<String,String> conteudo : listaConteudos) {
            String urlImagem = conteudo.get("image");
            //String urlImagem = conteudo.get("url").replaceAll("(@+).jpg$", "($1.jpg)");
            String titulo = conteudo.get("title");
            double imDbRating = Double.parseDouble(conteudo.get("imDbRating"));
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
            System.out.println(conteudo.get("image"));
            System.out.println(conteudo.get("imDbRating"));
            System.out.println();
        }
        
    }
    
}
