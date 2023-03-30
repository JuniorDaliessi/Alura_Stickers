
package alurastickers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    
    public void criar(InputStream inputStream, String nomeArquivo, String texto, InputStream selo) throws Exception{
        //LEITURA DE IMAGEM
        //InputStream inputStream = new FileInputStream("entrada/Filme.jpg");
        //BufferedImage imagemOriginal = ImageIO.read(inputStream);
        //InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);
        BufferedImage seloImage = ImageIO.read(selo);
        
        //CRIAR UMA NOVA IMAGEM EM MEMÓRIA COM TRANPARENCIA E TAMANHO NOVO
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 150;
        int novaLargura = largura + 200;
        BufferedImage novaImagem = new BufferedImage(novaLargura, novaAltura, BufferedImage.TRANSLUCENT);

        int seloImageY = altura - seloImage.getHeight(); // x= Larg - y= Alt
        
        //COPIAR A IMAGEM ORIGINAL PRA NOVO IMAGEM (EM MEMÓRIA)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        //graphics.drawImage(imagemOriginal, 0, 0, null); //--sem bordas
        graphics.drawImage(imagemOriginal, 100, 0, null);

        graphics.drawImage(seloImage, 0, seloImageY, null);

        //CONFIGURAR FONTE
        var fonte = new Font("Impact", Font.BOLD, 128);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);
        
        //ESCREVER UMA FRASE NA NOVA IMAGEM
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
        int alturaTexto = (int)retangulo.getHeight();
        int larguraTexto = (int)retangulo.getWidth();

        int posXTexto = (novaLargura - larguraTexto)/2;
        float posYTexto = (novaAltura - altura)/2 + altura + alturaTexto/3;

        graphics.drawString(texto, posXTexto, posYTexto);

        // Definindo estilo do contorno.
        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        var textLayout = new TextLayout(texto, fonte, fontRenderContext);

        // Definindo posi��o.
        Shape outLine = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(posXTexto, posYTexto);
        graphics.setTransform(transform);

        // Definindo largura do contorno.
        var outLineStoke = new BasicStroke(largura * 0.004f);
        graphics.setStroke(outLineStoke);

        // Definindo cor e desenhando o contorno.
        graphics.setColor(Color.BLACK);
        graphics.draw(outLine);
        graphics.setClip(outLine);
         
        //ESCREVER A NOVA IMAGEM EM UM ARQUIVO
        File file = new File("saida");
        boolean mkdir = file.mkdir();
        //System.out.println("Diretório Criado" +mkdir);
        ImageIO.write(novaImagem, "png", new File("saida/",nomeArquivo));
        
    } 
    
}
