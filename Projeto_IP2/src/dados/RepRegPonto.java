package dados;

import java.io.File;
import java.io.ObjectOutputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.Serializable;

import dados.exceptionsDados.FuncionarioNaoEncontradoException;
import neg�cio.entity_beans.Funcionario;
import neg�cio.entity_beans.RegPonto;


public class RepRegPonto implements Serializable,IRepositorioPontos {
	private ArrayList <RegPonto> repositorio;
	
	public RepRegPonto(){
		repositorio = new ArrayList<RegPonto>();
	}
	
	public void adicionarRegistro(RegPonto ponto){
		repositorio.add(ponto);
	}
	
	public ArrayList <RegPonto> pontosDoFuncionario(String cpf, IRepositorioPessoas repFuncionario) throws FuncionarioNaoEncontradoException{ //procurar pontos desse CPF nesse Repositorio de Funcionarios
		ArrayList <RegPonto> pontosDoFuncionario = new ArrayList <RegPonto>();
		//Falta buscar o Funcionario e depois buscar os pontos desse funcionario no arrayList de Pontos;
		Funcionario funcionario = (Funcionario) repFuncionario.buscaPessoaCpf(cpf);
		for (int i=0;i<this.repositorio.size();i++){
			if (repositorio.get(i).getFuncionario().equals(funcionario))
				pontosDoFuncionario.add(repositorio.get(i));
		}
		
		return pontosDoFuncionario;
	}
	
	public int totalChegadaCorreta(String cpf, IRepositorioPessoas repFuncionario) throws FuncionarioNaoEncontradoException{ //retorna o total de pontos de chegada corretos (Sem atrasos e Sem faltas)
		int cont=0;
		ArrayList <RegPonto> pontosDoFuncionario = pontosDoFuncionario (cpf,repFuncionario);
		for (int i=0;i<pontosDoFuncionario.size();i++){
			if (pontosDoFuncionario.get(i).chegadaCorreta())
				cont++;
		}
		return cont;
	}
	
	public int totalSaidaCorreta(String cpf, IRepositorioPessoas repFuncionario) throws FuncionarioNaoEncontradoException{ //retorna o total de pontos de saida corretos (Sem atrasos e Sem faltas)
		int cont=0;
		ArrayList <RegPonto> pontosDoFuncionario = pontosDoFuncionario (cpf,repFuncionario);
		for (int i=0;i<pontosDoFuncionario.size();i++){
			if (pontosDoFuncionario.get(i).saidaCorreta())
				cont++;
		}
		return cont;
	}
	
	public int totalIntervalo_InCorreta(String cpf, IRepositorioPessoas repFuncionario) throws FuncionarioNaoEncontradoException{ //retorna o total de pontos de Volta do Intervalo corretos (Sem atrasos e Sem faltas)
		int cont=0;
		ArrayList <RegPonto> pontosDoFuncionario = pontosDoFuncionario (cpf,repFuncionario);
		for (int i=0;i<pontosDoFuncionario.size();i++){
			if (pontosDoFuncionario.get(i).intervalo_InCorreta())
				cont++;
		}
		return cont;
	}
	
	public int totalIntervalo_OutCorreta(String cpf, IRepositorioPessoas repFuncionario) throws FuncionarioNaoEncontradoException{ //retorna o total de pontos de Sa�da pro Intervalo corretos (Sem atrasos e Sem faltas)
		int cont=0;
		ArrayList <RegPonto> pontosDoFuncionario = pontosDoFuncionario (cpf,repFuncionario);
		for (int i=0;i<pontosDoFuncionario.size();i++){
			if (pontosDoFuncionario.get(i).intervalo_OutCorreta())
				cont++;
		}
		return cont;
	}
	
	public void gravarDisco() throws Exception{
		if (!new File("C:\\Pontos\\pontos.pontos").exists()){ //se a pasta n�o existe, ent�o cria com os arquivos abaixo
			new File("C:\\Pontos\\pontos.pontos").mkdir(); // criar pasta 
			
			FileOutputStream pontos = new FileOutputStream("C:\\Pontos\\pontos.pontos"); //arquivo que armazena o Hist�rico de Pontos. N�o grava aqui. S� no registro de Pontos.
			ObjectOutputStream gravarArq = new ObjectOutputStream(pontos);
			gravarArq.writeObject(this.repositorio);
			pontos.close();
		}
	}	
}