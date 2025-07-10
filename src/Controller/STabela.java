package Controller;

import java.lang.reflect.Field;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class STabela {
    
    public static <T> void preencherTabela(JTable tabela, List<T> dados, String[] nomesColunas, String[] campos) {
        DefaultTableModel modelo = new DefaultTableModel(nomesColunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (T objeto : dados) {
            Object[] linha = new Object[campos.length];
            for (int i = 0; i < campos.length; i++) {
                try {
                    Field campo = objeto.getClass().getDeclaredField(campos[i]);
                    campo.setAccessible(true);
                    linha[i] = campo.get(objeto);
                } catch (Exception e) {
                    linha[i] = "Erro";
                }
            }
            modelo.addRow(linha);
        }

        tabela.setModel(modelo);
    }
    
    public static <T> void atualizarDados(DefaultTableModel modelo, List<T> dados, String[] campos) {
    modelo.setRowCount(0); // zera as linhas antigas

    for (T objeto : dados) {
        Object[] linha = new Object[campos.length];
        for (int i = 0; i < campos.length; i++) {
            try {
                Field campo = objeto.getClass().getDeclaredField(campos[i]);
                campo.setAccessible(true);
                linha[i] = campo.get(objeto);
            } catch (Exception e) {
                linha[i] = "Erro";
            }
        }
        modelo.addRow(linha);
    }
}
}
