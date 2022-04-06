package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import BD.BancoConf;
import Exception.Excecao;
import entities.Funcionario;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Funcionario funcionario = null;
		Connection conecta = null;
		PreparedStatement ps = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		char confirm = 0;
		do {
				try {
				conecta = BancoConf.ConectaBD();
				ps = conecta.prepareStatement("INSERT INTO funcionario"
						+ "(Nome,DataNascimento,CPF,Email,CARGO)"
						+ "VALUES"
						+ "(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
				 	sc.nextLine();
					System.out.print("Nome:");
					String nome = sc.nextLine();
					System.out.println("");
					
					System.out.println("Informe Aniversaior dd/mm/yyyy");
					String aniversaior= sc.nextLine();
					   sdf.parse(aniversaior);
					System.out.println("");
					
					System.out.print("Informe o CPF");
					int cpf = sc.nextInt();
					System.out.println("");
				    sc.nextLine();
					
					System.out.print("email:");
					String email = sc.nextLine();
					System.out.println("");
					
					System.out.print("cargo:");
					String cargo = sc.nextLine();
					System.out.println("");
					
					new Funcionario(nome, aniversaior, cpf, email, cargo);
					
					
						ps.setString(1,nome);
						ps.setDate(2,new java.sql.Date(sdf.parse(aniversaior).getTime()));
						ps.setInt(3, cpf);
						ps.setString(4, email);
						ps.setString(5, cargo);
					
				
				
				int rs = ps.executeUpdate();
				if(rs > 0) {
					ResultSet result = ps.getGeneratedKeys();
					while(result.next()) {
						int id=result.getInt(1);
						System.out.println("Done!" + id);
						}
					}
				System.out.println("Gostaria de adiciona mais:sim(s) ou Não(n) ");
				 confirm = sc.next().charAt(0);
				
				}
		 
		  catch(SQLException e) {
			throw new Excecao(e.getMessage());
		}catch(ParseException e) {
			e.getStackTrace();
		}
		
		
	} while(confirm =='s');
		
	 if(confirm =='n') {
		 BancoConf.desconectaStatement(ps);
		 BancoConf.DesconectaBD();
	}
	}
}
