package Controller;

import JDBC.ConexaoDB;
import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class FazTudo<T> {
    
    private final Class<T> tipoEntidade;
    
    public FazTudo(Class<T> tipoEntidade) {
        this.tipoEntidade = tipoEntidade;
    }
    
    //antes de tudo tem que fazer a conexão com o banco de dados, se não, não faz sentido
    protected Connection getConnection() throws SQLException {
        return ConexaoDB.getConnection();
    }
    
    //Create genérico
    public void create(T entidade) throws SQLException {
        String nomeTabela = ConvertCase.nomeTabela(tipoEntidade);
        List<Field> campos = getCamposPersistiveis();
        
        StringBuilder colunas = new StringBuilder();
        StringBuilder valores = new StringBuilder();
        
        for (Field campo : campos) {
            String nomeColuna = ConvertCase.camelParaSnake(campo.getName());
            colunas.append(nomeColuna).append(",");
            valores.append("?,");
        }
        
        removerUltimaVirgula(colunas);
        removerUltimaVirgula(valores);
        
        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", 
                                  nomeTabela, colunas, valores);
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            setParametros(stmt, entidade, campos);
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    setValorCampo(entidade, "id", rs.getInt(1));
                }
            }
        }
    }
    
    //read por ID
    public T read(int id) throws SQLException {
        String nomeTabela = ConvertCase.nomeTabela(tipoEntidade);
        String sql = String.format("SELECT * FROM %s WHERE id = ?", nomeTabela);
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearResultSetParaEntidade(rs);
                }
            }
        }
        return null;
    }
    
    //update genérico
    public void update(T entidade) throws SQLException {
        String nomeTabela = ConvertCase.nomeTabela(tipoEntidade);
        List<Field> campos = getCamposPersistiveis();
        
        StringBuilder sets = new StringBuilder();
        
        for (Field campo : campos) {
            String nomeColuna = ConvertCase.camelParaSnake(campo.getName());
            sets.append(nomeColuna).append(" = ?,");
        }
        
        removerUltimaVirgula(sets);
        
        String sql = String.format("UPDATE %s SET %s WHERE id = ?", nomeTabela, sets);
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            setParametros(stmt, entidade, campos);
            stmt.setInt(campos.size() + 1, getIdEntidade(entidade));
            stmt.executeUpdate();
        }
    }
    
    //delete genérico
    public void delete(int id) throws SQLException {
        String nomeTabela = ConvertCase.nomeTabela(tipoEntidade);
        String sql = String.format("DELETE FROM %s WHERE id = ?", nomeTabela);
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Métodos auxiliares
    private List<Field> getCamposPersistiveis() {
        List<Field> campos = new ArrayList<>();
        for (Field campo : tipoEntidade.getDeclaredFields()) {
            if (!campo.getName().equals("id")) {
                campos.add(campo);
            }
        }
        return campos;
    }
    
    private void setParametros(PreparedStatement stmt, T entidade, List<Field> campos) throws SQLException {
        for (int i = 0; i < campos.size(); i++) {
            Field campo = campos.get(i);
            campo.setAccessible(true);
            
            try {
                Object valor = campo.get(entidade);
                
                // Tratamento especial desnecessário para datas
                if (valor instanceof LocalDate data) {
                    stmt.setDate(i + 1, Date.valueOf((LocalDate)(data)));
                } else {
                    stmt.setObject(i + 1, valor);
                }
            } catch (IllegalAccessException e) {
                throw new SQLException("Erro ao acessar campo: " + campo.getName(), e);
            }
        }
    }
    
    private T mapearResultSetParaEntidade(ResultSet rs) throws SQLException {
        try {
            T entidade = tipoEntidade.getDeclaredConstructor().newInstance();
            
            for (Field campo : tipoEntidade.getDeclaredFields()) {
                campo.setAccessible(true);
                String nomeColuna = ConvertCase.camelParaSnake(campo.getName());
                
                // Tratamento especial desnecessário para datas
                if (campo.getType() == LocalDate.class) {
                    Date data = rs.getDate(nomeColuna);
                    if (data != null) {
                        campo.set(entidade, data.toLocalDate());
                    }
                } else {
                    campo.set(entidade, rs.getObject(nomeColuna, campo.getType()));
                }
            }
            return entidade;
        } catch (Exception e) {
            throw new SQLException("Erro ao criar entidade a partir do ResultSet", e);
        }
    }
    
    private int getIdEntidade(T entidade) throws SQLException { //identificador de entidade
        try {
            Field campoId = tipoEntidade.getDeclaredField("id");
            campoId.setAccessible(true);
            return (int) campoId.get(entidade);
        } catch (Exception e) {
            throw new SQLException("Erro ao obter ID da entidade", e);
        }
    }
    
    private void setValorCampo(T entidade, String nomeCampo, Object valor) {
        try {
            Field campo = tipoEntidade.getDeclaredField(nomeCampo);
            campo.setAccessible(true);
            campo.set(entidade, valor);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao definir valor do campo: " + nomeCampo, e);
        }
    }
    
    private void removerUltimaVirgula(StringBuilder sb) {
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public List<T> tabelaIndividual() throws SQLException {
    String nomeTabela = ConvertCase.nomeTabela(tipoEntidade);
    String sql = "SELECT * FROM " + nomeTabela;
    List<T> lista = new ArrayList<>();

    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            lista.add(mapearResultSetParaEntidade(rs));
        }
    }
    return lista;
}
}

