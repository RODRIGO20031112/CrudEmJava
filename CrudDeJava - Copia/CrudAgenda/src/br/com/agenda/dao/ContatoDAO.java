package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.ClientPreparedStatement;
import com.mysql.cj.protocol.ResultStreamer;
import com.mysql.cj.protocol.Resultset;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {
	
	/*
	 * CRUD
	 * c: CREATE
	 * r: READ
	 * u: UPDATE
	 * d: DELETE
	 */
	
	public void save(Contato contato) {
		
		String sql = "INSERT INTO contatos(nome, idade, datacadastro, cpf, email, password) VALUES (?, ?, ?, ?, ?, ?)";
		
		Connection conn = null;
		ClientPreparedStatement pstm = null;
		
		try {
			// Cria uma conexão com banco de dados 
			conn = ConnectionFactory.createConnectionToMySQL();
			
			// Criamos uma Preparestatmentt, para executar uma query
			pstm = (ClientPreparedStatement) conn.prepareStatement(sql);
			
			// Adiconar os valores que são esperados pela query
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			pstm.setString(4, contato.getCpf());
			pstm.setString(5, contato.getEmail());
			pstm.setString(6, contato.getPassword());
			
			// Executar a query
			pstm.execute();
			
			System.out.println("Contato salvo com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			// Fechar as conexões
			try {
				if(pstm!=null) {
					pstm.close();
				}
				
				if (conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(Contato contato) {
		String sql = "UPDATE contatos SET nome = ?, idade = ?, datacadastro = ?, password = ?" + 
		"WHERE email = ?";
		//Confere se existe dados dentro do banco
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Criar conexão com o banco
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//Adicionar os valores para atualizar 
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			pstm.setString(4, contato.getPassword());
			
			//Dados para atualizar 
			pstm.setString(5, contato.getEmail());
			
			
			//Executar a query
			pstm.execute();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null) {
					pstm.close();
				}
				
				if(conn!=null) {
					conn.close();
				}	
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteByCpf(String email, String password) {
		
		String sql = "DELETE FROM contatos WHERE email = ? and password = ? ";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
		
			pstm.setString(1, email);
			pstm.setString(2, password);
			
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				
				if(conn != null) {
					conn.close();
				}	
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Contato> getContatos() {
		
		String sql = "SELECT * FROM contatos";
		
		List<Contato> contatos = new ArrayList<Contato>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		// Classe para recuperar dados do banco. ""SELECT""
		ResultSet rset = null;
		
		try {
			conn =  ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				Contato contato = new Contato();
				
				//Recuperar o id
				contato.setId(rset.getInt("idagenda"));
				//Recuperar o nome 
				contato.setNome(rset.getString("nome"));
				//Recuperar a idade
				contato.setIdade(rset.getInt("idade"));
				//Recuperar data de cadastro
				contato.setDataCadastro(rset.getDate("datacadastro"));
				//Recuperar cpf
				contato.setCpf(rset.getString("cpf"));
				//Recuperar login
				contato.setEmail(rset.getString("email"));
				//Recuperar password
				contato.setPassword(rset.getString("password"));
				
				contatos.add(contato);
				
				}
			 }catch (Exception e) {
				 e.printStackTrace();
			 }finally {
				try {
					if(rset!=null) {
					    rset.close();
					}
					if(pstm!=null) {
						pstm.close();
					}
					if(conn!=null) {
						conn.close();
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			 }
			 return contatos;
		
	}

	public boolean verificaLogin(Contato contato) throws Exception {
		String sql = "SELECT * FROM contatos WHERE email = ? and password = ?";
		
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			
		try {
			conn =  ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setString(1, contato.getEmail());
			pstm.setString(2, contato.getPassword());
			
			rs = pstm.executeQuery();
			if(rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO!\n" + e);
		}
		return false;
	}
}
