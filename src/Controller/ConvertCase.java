package Controller;

public class ConvertCase {
       public static String camelParaSnake(String camelCase) {
        if (camelCase.contains("_")) return camelCase; //evitar convertert se ja tiver em snake_case
        return camelCase.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }
    
    public static String nomeTabela(Class<?> clazz) {
        String nomeSimples = clazz.getSimpleName().toLowerCase();
        return nomeSimples.endsWith("s") ? nomeSimples : nomeSimples + "s";
    }
}
