package dados;

import java.time.LocalTime;
import java.time.LocalDate;

public class Funcionario {
	private String nome;
	private String cpf;
	private String email;
	private String empresa;
	private String cargo;
	private String senha;
	private String escala;
	private LocalTime chegada,saida,intervalo_in,intervalo_out;
	//qual tipo usar pra foto?
	//teste
	//teste 2 
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEscala() {
		return escala;
	}

	public void setEscala(String escala) {
		this.escala = escala;
	}

	public LocalTime getChegada() {
		return chegada;
	}

	public void setChegada(LocalTime chegada) {
		this.chegada = chegada;
	}

	public LocalTime getSaida() {
		return saida;
	}

	public void setSaida(LocalTime saida) {
		this.saida = saida;
	}

	public LocalTime getIntervalo_in() {
		return intervalo_in;
	}

	public void setIntervalo_in(LocalTime intervalo_in) {
		this.intervalo_in = intervalo_in;
	}

	public LocalTime getIntervalo_out() {
		return intervalo_out;
	}

	public void setIntervalo_out(LocalTime intervalo_out) {
		this.intervalo_out = intervalo_out;
	}

	public Funcionario(){
		
	}
}
