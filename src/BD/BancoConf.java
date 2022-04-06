package BD;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import Exception.Excecao;

public class BancoConf {

	
	private static Connection conecta= null;
	
	
	
	public static Connection ConectaBD() {
		try {
			if(conecta == null) {
				Properties arquivo = CarregaArquivo();
				String url = arquivo.getProperty("dburl");
				conecta = DriverManager.getConnection(url,arquivo);
				return conecta;
			}
		}catch(SQLException e){
			throw new Excecao(e.getMessage());
		}
		return conecta;
	}
	
	public static void DesconectaBD() {
		try {
			if(conecta != null) {
				conecta.close();
			}
		}catch(SQLException e) {
			e.getStackTrace();
		}
	}
	
	
	
	public static Properties CarregaArquivo() {
		Properties carr = new Properties();
	try(FileInputStream ler = new FileInputStream("banco.properties")) {
			
			carr.load(ler);
			return carr;
			
		} catch(IOException e) {
			e.getStackTrace();
		}
	return carr;	
	
	}
	
	public static void desconectaStatement(Statement a) {
		try{if(a != null) {
			a.close();
		}
		}catch(SQLException e) {
			throw new Excecao(e.getMessage());
		}
	}
}
