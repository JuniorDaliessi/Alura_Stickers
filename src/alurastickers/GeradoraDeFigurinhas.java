
package alurastickers;

import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    
    public void criar() throws Exception{
        //LEITURA DE IMAGEM
        BufferedImage imagemOriginal = ImageIO.read(new File("entrada/Filme.jpg"));
        
        //CRIAR UMA NOVA IMAGEM EM MEMÓRIA COM TRANPARENCIA E TAMANHO NOVO
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 100;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
        
        //COPIAR A IMAGEM ORIGINAL PRA NOVO IMAGEM (EM MEMÓRIA)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //CONFIGURAR FONTE
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);
        
        //ESCREVER UMA FRASE NA NOVA IMAGEM
        graphics.drawString("TOPZERA", 25, novaAltura - 25);
        
        
        //ESCREVER A NOVA IMAGEM EM UM ARQUIVO
        File file = new File("saida");
        boolean mkdir = file.mkdir();
        System.out.println("Diretório Criado" +mkdir);
        ImageIO.write(novaImagem, "png", new File("saida/figurinha.png"));
        
    } 
    
    public static void main(String[] args) throws Exception {
        var gerador = new GeradoraDeFigurinhas();
        gerador.criar(); 
    }
}
