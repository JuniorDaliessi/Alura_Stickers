/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alurastickers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ClienteHttp {
    //METODO
    public String buscaDados(String url){

        try {
            URI endereco = URI.create(url);
        
        //var client = HttpClient.newHttpClient(); ---poderia ser var no tipo da variavel
        //o Java ja reconheceria
        HttpClient client = HttpClient.newHttpClient();
        
        // HttpRequest request = HttpRequest.newBuilder(endereco).GET().build(); --poderia ser HttpRequest no tipo da variavel
        var request = HttpRequest.newBuilder(endereco).GET().build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        return body;
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        
        
    }
}
