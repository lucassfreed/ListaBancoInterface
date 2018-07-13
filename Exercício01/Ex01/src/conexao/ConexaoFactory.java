package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
    
    private static String CLASS_NAME = "com.jdbc.mysql.Driver";
    private static String HOST = "jdbc:mysql://localhost:3306/banco_de_dados_exercicio_01";
    private static String LOGIN = "root";
    private static String PASSWORD = "";
    private static Connection conexao;
    
    public static void main(String[] args) {
        ConexaoFactory.obterConexão();
    }
    
    public static Connection obterConexão() {
        try {
            conexao = DriverManager.getConnection(HOST, LOGIN, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conexao;   
    }
    
    public static void fecharConexão() {
        try {
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
}
