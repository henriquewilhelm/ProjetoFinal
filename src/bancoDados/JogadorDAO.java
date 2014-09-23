package bancoDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jogo.Jogador;

public class JogadorDAO {
	// Método Log retorna a consulta da tabela loguins.
	public ResultSet log (Connection con, String valor)throws SQLException {
		// Complementa o SQL com a clausula where se valor vir com parametro.
		String values;
		// Verifica complemento.
		if(valor.equals("")){
			values = "";
		}else{
			values = " WHERE senha LIKE ? OR nome LIKE ? OR pais LIKE ?";
		}
		// Monta a query.
		String sql = "SELECT * FROM jogadores" + values+ " ORDER BY medalhas DESC, vitorias DESC"  ;
		
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		// Se há complemento de consulta é complementado o ? com os valores respectivos.
		if(!valor.equals("")){
			// Usuário e senha são validados sem espços e o usuário indifere de maiúsculo e minúsculo.
			preparedStatement.setString(1, "%" + valor.replaceAll("\\s+","") + "%");
			preparedStatement.setString(2, "%" + valor.toLowerCase().replaceAll("\\s+","") + "%");
			preparedStatement.setString(3, "%" + valor.toLowerCase().replaceAll("\\s+","") + "%");
		}
		// Executa query.
		ResultSet rs = preparedStatement.executeQuery();
		// retorna a consulta ResultSet.
		return rs;
	}
	
	// Método que retorna verdadeiro ou falso para realização do login.
	public boolean login(Connection con, String nome, String senha)
			throws SQLException {

		String sql = "SELECT * FROM jogadores WHERE nome LIKE ? AND senha LIKE ?";

		PreparedStatement preparedStatement = con.prepareStatement(sql);
		// Usuário e senha são validados sem espços e o usuário indifere de maiúsculo e minúsculo.
		preparedStatement.setString(1, nome.toLowerCase().replaceAll("\\s+",""));
		preparedStatement.setString(2, senha.replaceAll("\\s+",""));

		ResultSet rs = preparedStatement.executeQuery();

		if (rs.next()) {
			System.out.println("Loguin Efetuado com sucesso!");
			// Retorna verdadeiro se a consulta for true.
			return true;
		}
		// Retorna falso se a consulta for false.
		return false;
	}

	// Método que valida se já existe registro com nome a ser criado.
	public boolean verificaNome(Connection con, String nome)
			throws SQLException {

		String sql = "SELECT * FROM jogadores WHERE nome LIKE ?";

		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, nome.toLowerCase().replaceAll("\\s+",""));
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			if (rs.getString("nome").equalsIgnoreCase(nome.toLowerCase().replaceAll("\\s+",""))) {
				// Retorna verdadeiro se a consulta for true.
				return true;
			}
		}
		// Retorna falso se a consulta for false.
		return false;
	}

	// Método que valida senha.
	public boolean verificaSenha(Connection con, String senha, int id)
			throws SQLException {

		String sql = "SELECT senha FROM jogadores WHERE id LIKE ? AND senha=?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, String.valueOf(id));
		preparedStatement.setString(2, senha.replaceAll("\\s+",""));
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			if (rs.getString("senha").equalsIgnoreCase(senha)) {
				// Retorna verdadeiro se a consulta for true.
				return true;
			}
		}
		// Retorna falso se a consulta for false.
		return false;
	}

	// Adiciona um usuário e senha - Cadastro de Login.
	public boolean addLogin(Connection con, String nome, String senha)
			throws SQLException {

		// Valida nome primeiramente. Cria um novo se o dado não existir no banco.
		if (!verificaNome(con, nome)) {

			String insertSQL = "INSERT INTO jogadores (nome, senha) VALUES (?,?)";

			PreparedStatement preparedStatement = con
					.prepareStatement(insertSQL);
			// Usuário e senha são validados sem espços e o usuário indifere de maiúsculo e minúsculo.
			preparedStatement.setString(1, nome.toLowerCase().replaceAll("\\s+",""));
			preparedStatement.setString(2, senha.replaceAll("\\s+",""));
			preparedStatement.executeUpdate();
			return true;
		}
		
		return false;
	}
	// Método que valida senha.
		public int buscaId(Connection con, String nome)
				throws SQLException {
			int id = 0;
			String sql = "SELECT id FROM jogadores WHERE nome LIKE ?";
			
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, nome.replaceAll("\\s+",""));
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
					id = Integer.parseInt(rs.getString("id"));
					return id;
			}
			// Retorna falso se a consulta for false.
			return id;
		}
		// Método que valida senha.
		public int buscaImg(Connection con,int id)
				throws SQLException {
			int img = 0;
			String sql = "SELECT imagem FROM jogadores WHERE id=?";
			
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(id).replaceAll("\\s+",""));
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
					img = Integer.parseInt(rs.getString("imagem"));
					return img;
			}
			// Retorna falso se a consulta for false.
			return img;
		}
		// Método que valida senha.
		public String buscaPais(Connection con,int id)
						throws SQLException {
					String pais = "";
					String sql = "SELECT pais FROM jogadores WHERE id=?";
					
					PreparedStatement preparedStatement = con.prepareStatement(sql);
					preparedStatement.setString(1, String.valueOf(id).replaceAll("\\s+",""));
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
							pais = rs.getString("pais");
							return pais;
					}
					// Retorna falso se a consulta for false.
					return pais;
		}
		// Método que valida senha.
		public int buscaMedalhas(Connection con,int id)
						throws SQLException {
					int medalhas = 0;
					String sql = "SELECT medalhas FROM jogadores WHERE id=?";
					
					PreparedStatement preparedStatement = con.prepareStatement(sql);
					preparedStatement.setString(1, String.valueOf(id).replaceAll("\\s+",""));
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
							medalhas = Integer.parseInt(rs.getString("medalhas"));
							return medalhas;
					}
					// Retorna falso se a consulta for false.
					return medalhas;
		}
		// Método que valida senha.
		public int buscaVitorias(Connection con,int id)
								throws SQLException {
							int vitorias = 0;
							String sql = "SELECT vitorias FROM jogadores WHERE id=?";
							
							PreparedStatement preparedStatement = con.prepareStatement(sql);
							preparedStatement.setString(1, String.valueOf(id).replaceAll("\\s+",""));
							ResultSet rs = preparedStatement.executeQuery();
							while (rs.next()) {
									vitorias = Integer.parseInt(rs.getString("vitorias"));
									return vitorias;
							}
							// Retorna falso se a consulta for false.
							return vitorias;
		}
		// Método que valida senha.
		public int buscaDerrotas(Connection con,int id) throws SQLException {
						int derrotas = 0;
						String sql = "SELECT derrotas FROM jogadores WHERE id=?";
									
						PreparedStatement preparedStatement = con.prepareStatement(sql);
						preparedStatement.setString(1, String.valueOf(id).replaceAll("\\s+",""));
						ResultSet rs = preparedStatement.executeQuery();
						while (rs.next()) {
											derrotas = Integer.parseInt(rs.getString("derrotas"));
											return derrotas;
						}
						// Retorna falso se a consulta for false.
						return derrotas;
		}
		// Método que valida senha.
		public int buscaTotalJogos(Connection con,int id) throws SQLException {
								int total = 0;
								String sql = "SELECT totalJogos FROM jogadores WHERE id=?";
											
								PreparedStatement preparedStatement = con.prepareStatement(sql);
								preparedStatement.setString(1, String.valueOf(id).replaceAll("\\s+",""));
								ResultSet rs = preparedStatement.executeQuery();
								while (rs.next()) {
													total = Integer.parseInt(rs.getString("totalJogos"));
													return total;
								}
								// Retorna falso se a consulta for false.
								return total;
		}
		public void save(Connection con, Jogador jogador) throws SQLException {
			if (jogador.getId() == 0) {
				String insertSQL = "INSERT INTO jogadores(imagem, pais, medalhas, vitorias, derrotas, totalJogos) "
						+ "VALUES(?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStatement = con
						.prepareStatement(insertSQL);
				preparedStatement.setInt(1, jogador.getImagem());
				preparedStatement.setString(2, jogador.getPais());
				preparedStatement.setInt(3, jogador.getMedalhas());
				preparedStatement.setInt(4, jogador.getVitorias());
				preparedStatement.setInt(5, jogador.getDerrotas());
				preparedStatement.setInt(6, jogador.getTotalJogos());

				preparedStatement.executeUpdate();
			} else {
				String updateSQL = "UPDATE jogadores SET imagem=?, pais=?, medalhas=?, vitorias=?, derrotas=?, totalJogos=? "
						+ "WHERE id = ?";
				PreparedStatement preparedStatement = con
						.prepareStatement(updateSQL);
				preparedStatement.setInt(1, jogador.getImagem());
				preparedStatement.setString(2, jogador.getPais());
				preparedStatement.setInt(3, jogador.getMedalhas());
				preparedStatement.setInt(4, jogador.getVitorias());
				preparedStatement.setInt(5, jogador.getDerrotas());
				preparedStatement.setInt(6, jogador.getTotalJogos());
				preparedStatement.setInt(7, jogador.getId());
				preparedStatement.executeUpdate();
			}
		}
}