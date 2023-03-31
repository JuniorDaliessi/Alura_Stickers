
package alurastickers;


public class Conteudo {
    //ATRIBUTOS
    private final String urlImagem; //Com o final depois que o obj for criado, não pode ser mudado
    private final String titulo;   // por isso s[o precisamos do metodo geter
    
    //MÉTODOS

    public Conteudo(String urlImagem, String titulo) {
        this.urlImagem = urlImagem;
        this.titulo = titulo;
    }
    
    

    public String getUrlImagem() {
        return urlImagem;
    }

    public String getTitulo() {
        return titulo;
    }
    
}
