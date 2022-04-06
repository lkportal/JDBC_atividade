package entities;



public class Funcionario {
					
	private String nome;
	private String dataAniversario;
	private Integer CPF;
	private String email;
	private String cargo;
	
	
	
	public Funcionario(String nome, String dataAniversario, Integer cPF, String email, String cargo) {
		super();
		this.nome = nome;
		this.dataAniversario = dataAniversario;
		CPF = cPF;
		this.email = email;
		this.cargo = cargo;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getDataAniversario() {
		return dataAniversario;
	}



	public void setDataAniversario(String dataAniversario) {
		this.dataAniversario = dataAniversario;
	}



	public Integer getCPF() {
		return CPF;
	}



	public void setCPF(Integer cPF) {
		CPF = cPF;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getCargo() {
		return cargo;
	}



	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	
	
	
	
}
