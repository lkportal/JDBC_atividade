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


public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Connection conecta = null;
		PreparedStatement ps = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		char confirm = 0;
		
		try {
		do {
				
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
			
	  
		 System.out.println("Adiciona Endereço aos funcionarios ");
	 

			 
		 ps =conecta.prepareStatement("INSERT INTO ENDERECO"
		 		+ "(RUA,BAIRRO,CEP,ID_FUNCIONARIO)"
		 		+ "VALUES"
		 		+ "(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
		 
		  System.out.print("Nome da rua:");
		  String rua = sc.nextLine();
		  sc.nextLine();
		  
		  System.out.print("Nome do bairro");
		  String bairro = sc.nextLine();
		  sc.nextLine();
		  
		  System.out.print("Cep");
		  String cep = sc.nextLine();
		  sc.nextLine();
		  
		  System.out.print("ID do funcionario");
		  int id = sc.nextInt();
		  
		  
		  ps.setNString(1,rua);
		 ps.setString(2,bairro);
		  ps.setString(3, cep);
		  ps.setInt(4, id);
		  
		  
		  int conrf = ps.executeUpdate();
		  
		  if(conrf >0) {
			  ResultSet a = ps.getGeneratedKeys();
			  while(a.next()) {
				  int id1 = a.getInt(1);
				  System.out.print("Feito" + id1);
				  System.out.println();
			  }
		  }
		  System.out.print("Gostaria de adiciona mais funciona e endereço (s) para sim e (n) para não");
		  confirm = sc.next().charAt(0);
		      	  
		} while(confirm == 's'); 
		
		}catch(SQLException e) {
			
			throw new Excecao(e.getMessage());
			
		}catch(ParseException e)
		{
			e.getStackTrace();
	
	 }finally{
			BancoConf.desconectaStatement(ps);
			BancoConf.DesconectaBD();
	 
	 	}
}
	
}

