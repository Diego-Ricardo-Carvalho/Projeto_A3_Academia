
package Controller;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
public final class CJanela {
    private CJanela(){
}//impede que crie alguam coisa como JanelUtil BolaVerde = new JavaUtil(....);
 
    // Método principal (público)
    public static void abrir(JFrame janela, String titulo, JFrame janelaAnterior) {
        configurarComportamentoFechamento(janela, janelaAnterior);//Método privado para o encapsulamento
        janela.setTitle(titulo);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }
    //Esse método aqui serve para quando apertar no x não encerrar o sistema, apenas fechar a tela
    private static void configurarComportamentoFechamento(JFrame janela, JFrame janelaAnterior) {
        janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        janela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                janela.setVisible(false);
                if (janelaAnterior != null) {
                    janelaAnterior.setVisible(true);
                }
            }
        });
}
}