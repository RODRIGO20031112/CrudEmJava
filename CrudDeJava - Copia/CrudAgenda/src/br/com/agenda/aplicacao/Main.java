package br.com.agenda.aplicacao;

import java.util.Date;
import java.util.Scanner;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {
	public static void main(String[] args) throws Exception {
		
		Contato cc = new Contato();
		 
		 while (cc.getNumCrud() != 0) {
			System.out.println("1-Inserir usuários dentro do banco de dados: ");
			System.out.println("2-Ver usuários inseridos  no banco de dados: ");
			System.out.println("3-Alterar usuários dentro do banco de dados: ");
			System.out.println("4-Deletar usuários dentro do banco de dados: ");
			Scanner y = new Scanner(System.in);
			int opcao = y.nextInt();
				if (opcao == 1) {
					ContatoDAO contatoDAO = new ContatoDAO();
					 
					Scanner s = new Scanner(System.in);
					
					System.out.printf("Informe um email: ");
					String email = s.nextLine();
					
					System.out.printf("Informe uma senha: ");
					String password = s.nextLine();
				
					System.out.printf("Informe um cpf: ");
					String cpf = s.nextLine();
					
					System.out.printf("Informe um nome: ");
					String nome = s.nextLine();
					
					System.out.printf("Informe uma idade: ");
					int idade = s.nextInt();
					
					Contato contato = new Contato();
					contato.setNome(nome);
					contato.setIdade(idade);
					contato.setDataCadastro(new Date());
					contato.setCpf(cpf);
					contato.setEmail(email);
					contato.setPassword(password);
						 
					contatoDAO.save(contato);
						
				} else if(opcao == 2) {
					ContatoDAO contatoDAO = new ContatoDAO();
					for(Contato c : contatoDAO.getContatos()) {
						System.out.println("[Contato: "
						+c.getNome()+" Idade: "
						+c.getIdade()+" Data de Cadastro: "	
						+c.getDataCadastro()+" CPF: "
						+c.getCpf()+" Email: "
						+c.getEmail()+" Senha: "
						+c.getPassword() 
						+"]");
					}
				} else if (opcao == 3) {
						Scanner s = new Scanner(System.in);
						ContatoDAO cdao = new ContatoDAO();
						Contato c1 = new Contato();
						
						System.out.printf("Informe um email: ");
						String email = s.nextLine();
						
						System.out.printf("Informe uma senha: ");
						String password = s.nextLine();
						
						c1.setEmail(email);
						c1.setPassword(password);
						
						if(cdao.verificaLogin(c1) == true) {
							
							System.out.printf("Informe uma nova senha: ");
							String senha = s.nextLine();
							
							System.out.printf("Informe um novo nome: ");
							String nome = s.nextLine();
								
							System.out.printf("Informe uma nova idade: ");
							int idade = s.nextInt();
								
							c1.setNome(nome);
							c1.setIdade(idade);
							c1.setDataCadastro(new Date());
							c1.setPassword(senha);
							
							//Regra de negócio (Método update)
							cdao.update(c1);
							
							System.out.println("Contato alterado com sucesso");
						} else {
							System.out.println("Email ou senha inválidos ou não registrados");
						}
				
				} else if (opcao == 4) {
					Scanner j = new Scanner(System.in);
					ContatoDAO cdao1 = new ContatoDAO();
					Contato c = new Contato();
					
					System.out.printf("Informe um email: ");
					String email = j.nextLine();
					
					System.out.printf("Informe uma senha: ");
					String password = j.nextLine();
					
					c.setEmail(email);
					c.setPassword(password);
					
					if(cdao1.verificaLogin(c) == true) {
						cdao1.deleteByCpf(email, password);
						System.out.println("Contato excluído com sucesso");
					} else {
						System.out.println("Email ou senha inválidos ou não registrados");
					}
				} 
				Scanner ss = new Scanner(System.in);
				
				System.out.printf("Deseja continuar (1-Sim/0-Nao): ");
				int input = ss.nextInt();
				cc.setNumCrud(input);
		 }	 
	}
}
