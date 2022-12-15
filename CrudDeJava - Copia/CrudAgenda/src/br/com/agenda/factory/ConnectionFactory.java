package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	// Nome do usuário do banco mysql
	private static final String USERNAME = "admJava";
	
	// Senha do banco mysql
	private static final String PASSWORD = "12345";
	
	// Caminho do banco de dados, porta, nome do banco de dados
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/sistemaJava";

	/*
	 * Conexão com banco de dados
	 */
	public static Connection createConnectionToMySQL() throws Exception {
		// Faz com que a classe seja carrega pela JVM
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
	}
	
	public static void main(String[] args) throws Exception {
		// Recuperar uma conexão com banco de dados
		Connection con = createConnectionToMySQL();
		
		// Testar se a conexão é nula
		if (con!=null) {
			System.out.println("Conexão obtida com sucesso !");
			con.close();
		}
	}
}
