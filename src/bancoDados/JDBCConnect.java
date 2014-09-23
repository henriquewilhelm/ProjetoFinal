package bancoDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnect {

	private static final String banco = "projeto";
	private static final String url = "jdbc:mysql://localhost:3306/" + banco;
	private static final String usuario = "root";
	private static final String senha = "";

	// Criando conexão com a base de dados
	public Connection criarConexao() throws SQLException {

		Connection con = DriverManager.getConnection(url, usuario, senha);
//		System.out.println("Conectado!");
		return con;

	}

}