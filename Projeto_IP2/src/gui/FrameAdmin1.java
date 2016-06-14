package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import neg�cio.EpontoFachada;
import neg�cio.entity_beans.Admin;
import neg�cio.entity_beans.Empresa;
import neg�cio.entity_beans.Funcionario;
import neg�cio.entity_beans.Pessoa;

import javax.swing.JButton;

import dados.exceptionsDados.FuncionarioNaoEncontradoException;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class FrameAdmin1 extends JFrame implements ActionListener, MouseListener, WindowListener, FocusListener {

	private Admin admin;
	private JPanel contentPane;
	private JTextField txtBusca;
	private DefaultTableModel modeloTable;
	private JTable tableFuncionarios; 
	private JButton btnPesquisar; 
	private JScrollPane scrllPnFuncionarios;
	private JButton btnCadastrarFuncionario;
	private JButton btnMostrarTodos;
	private JButton btnCadastrarEmpresa;
	private JButton btnExcluirFuncionrio;
	private JButton btnCadastrarAdministrador;
	private JComboBox<Empresa> cmbBxEmpresa;
	private JLabel lblEmpresa;
	private static final String AdminSuper= EpontoFachada.getInstance().getPessoas(null).get(0).getCpf();
	private JLabel lblModoAdministrador;
	private JLabel lblBuscarFuncionrio;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAdmin1 frame = new FrameAdmin1((Admin) EpontoFachada.getInstance().getPessoaCpf(AdminSuper));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public FrameAdmin1(Admin admin) {
		setTitle("Administrador " + admin.getNome());		

		//Modelo de Tabela.
		this.modeloTable = new DefaultTableModel(new Object[][]{}, new Object[]{"Nome","CPF","E-Mail","Telefone","Cargo","Empresa"}){
			 @Override
			    public boolean isCellEditable(int row, int column) { //sobreescreve pra tornar todas as c�lulas N�O edit�veis
			        //all cells false
			        return false;
			    }
		};
		
		this.admin = admin;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(3, 100, 1360, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addWindowListener(this);
		
		cmbBxEmpresa = new JComboBox<Empresa>();
	    cmbBxEmpresa.setBounds(515, 144, 145, 20);
	    for (int i = 0; i < admin.getEmpresas().size(); i++){
	    	cmbBxEmpresa.addItem(admin.getEmpresas().get(i));
	    }
	    cmbBxEmpresa.addActionListener(this);
	    contentPane.add(cmbBxEmpresa);

	    if(admin.getCpf().equals(AdminSuper))
	    		this.preencherTableFuncionariosSuper(null);
	    else
	    	this.preencherTableFuncionarios(null);
		
		this.txtBusca = new JTextField();
		txtBusca.setBounds(515, 113, 241, 20);
		contentPane.add(txtBusca);
		txtBusca.setColumns(50);
		
		JLabel lblProcurar = new JLabel("Pesquisar:");
		lblProcurar.setForeground(new Color(0, 0, 0));
		lblProcurar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProcurar.setBounds(435, 116, 70, 14);
		contentPane.add(lblProcurar);
		
		this.tableFuncionarios = new JTable(modeloTable);
		tableFuncionarios.addMouseListener(this);

	    this.scrllPnFuncionarios = new JScrollPane(tableFuncionarios);
	    scrllPnFuncionarios.setLocation(376, 190);
		scrllPnFuncionarios.setSize(811, 360);
	    contentPane.add(scrllPnFuncionarios);
	    
	    btnPesquisar = new JButton("Pesquisar");
	    btnPesquisar.setForeground(new Color(0, 0, 0));
	    btnPesquisar.setBounds(781, 112, 102, 23);
	    btnPesquisar.addActionListener(this);
	    contentPane.add(btnPesquisar);
	    
	    btnMostrarTodos = new JButton("Mostrar Todos");
	    btnMostrarTodos.setForeground(new Color(0, 0, 0));
	    btnMostrarTodos.setBounds(895, 112, 118, 23);
	    btnMostrarTodos.addActionListener(this);
	    contentPane.add(btnMostrarTodos);
	    
	    lblEmpresa = new JLabel("Empresa:");
	    lblEmpresa.setForeground(new Color(0, 0, 0));
	    lblEmpresa.setHorizontalAlignment(SwingConstants.RIGHT);
	    lblEmpresa.setBounds(434, 147, 65, 14);
	    contentPane.add(lblEmpresa);
	    
	    JPanel panel = new JPanel();
	    panel.setBackground(new Color(105, 105, 105));
	    panel.setBounds(0, 0, 241, 561);
	    contentPane.add(panel);
	    panel.setLayout(null);
	    
	    btnCadastrarEmpresa = new JButton("Cadastrar Empresa");
	    btnCadastrarEmpresa.setForeground(new Color(0, 0, 0));
	    btnCadastrarEmpresa.setBounds(28, 306, 180, 39);
	    panel.add(btnCadastrarEmpresa);
	    
	    btnExcluirFuncionrio = new JButton("Excluir Funcion\u00E1rio");
	    btnExcluirFuncionrio.setForeground(new Color(0, 0, 0));
	    btnExcluirFuncionrio.setBounds(28, 356, 180, 39);
	    panel.add(btnExcluirFuncionrio);
	    
	    btnCadastrarFuncionario = new JButton("Cadastrar Funcionario");
	    btnCadastrarFuncionario.setForeground(new Color(0, 0, 0));
	    btnCadastrarFuncionario.setBounds(28, 256, 180, 39);
	    panel.add(btnCadastrarFuncionario);
	    
	    btnCadastrarAdministrador = new JButton("Cadastrar Administrador");
	    btnCadastrarAdministrador.setForeground(new Color(0, 0, 0));
	    btnCadastrarAdministrador.setBounds(28, 206, 180, 39);
	    panel.add(btnCadastrarAdministrador);
	    
	    JLabel lblInponto = new JLabel("inPonto");
	    lblInponto.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 29));
	    lblInponto.setForeground(new Color(143, 188, 143));
	    lblInponto.setBounds(74, 133, 98, 22);
	    panel.add(lblInponto);
	    
	    lblModoAdministrador = new JLabel("Modo Administrador");
	    lblModoAdministrador.setForeground(new Color(169, 169, 169));
	    lblModoAdministrador.setBounds(53, 451, 129, 14);
	    panel.add(lblModoAdministrador);
	    
	    lblBuscarFuncionrio = new JLabel("BUSCAR FUNCION\u00C1RIO");
	    lblBuscarFuncionrio.setForeground(new Color(0,0,0));
	    lblBuscarFuncionrio.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblBuscarFuncionrio.setBounds(645, 44, 200, 20);
	    contentPane.add(lblBuscarFuncionrio);
	    btnCadastrarAdministrador.addActionListener(this);
	    btnCadastrarFuncionario.addActionListener(this);
	    btnExcluirFuncionrio.addActionListener(this);
	    btnCadastrarEmpresa.addActionListener(this );
		    
		}

	//preenche a table de acordo com o parametro: mostra todos os Funcionarios cujo nome cont�m a String passado como par�metro.
	//caso o parametro seja NULL, preenche com todos os Funcionarios
	public void preencherTableFuncionarios(String nome){ 
		this.limparTableFuncionarios();
		
		ArrayList<Pessoa>pessoas = EpontoFachada.getInstance().getPessoas(nome);
		String[] linha= new String[6];
		Empresa empresa = (Empresa) cmbBxEmpresa.getSelectedItem();
		for (int i=0; i < pessoas.size() ; i++){
			if (pessoas.get(i) instanceof Funcionario && 
					((Funcionario)pessoas.get(i)).getEmpresa().igualNome(empresa.getNomeEmpresa())){
				linha[0] = pessoas.get(i).getNome();
				linha[1] = pessoas.get(i).getCpf();
				linha[2] = pessoas.get(i).getEmail();
				linha[3] = ((Funcionario) pessoas.get(i)).getTelefone();
				linha[4] = ((Funcionario) pessoas.get(i)).getCargo();
				linha[5] = ((Funcionario) pessoas.get(i)).getEmpresa().getNomeEmpresa();
				modeloTable.addRow(linha);
			}
			if (pessoas.get(i) instanceof Admin && 
					((Admin)pessoas.get(i)).getEmpresas().contains(cmbBxEmpresa.getSelectedItem())){
				linha[0] = pessoas.get(i).getNome();
				linha[1] = pessoas.get(i).getCpf();
				linha[2] = pessoas.get(i).getEmail();
				linha[3] = "";
				linha[4] = "";
				linha[5] = ((Admin) pessoas.get(i)).getStringEmpresas();
				modeloTable.addRow(linha);
			}
		}
	}
	
	public void preencherTableFuncionariosSuper(String nome){ 
		this.limparTableFuncionarios();
		
		ArrayList<Pessoa>pessoas = EpontoFachada.getInstance().getPessoas(nome);
		String[] linha= new String[6];
		for (int i=0; i < pessoas.size() ; i++){
			if (pessoas.get(i) instanceof Funcionario) {
				linha[0] = pessoas.get(i).getNome();
				linha[1] = pessoas.get(i).getCpf();
				linha[2] = pessoas.get(i).getEmail();
				linha[3] = ((Funcionario) pessoas.get(i)).getTelefone();
				linha[4] = ((Funcionario) pessoas.get(i)).getCargo();
				linha[5] = ((Funcionario) pessoas.get(i)).getEmpresa().getNomeEmpresa();
				modeloTable.addRow(linha);
			}
			else if(pessoas.get(i) instanceof Admin){
				linha[0] = pessoas.get(i).getNome();
				linha[1] = pessoas.get(i).getCpf();
				linha[2] = pessoas.get(i).getEmail();
				linha[3] = "";
				linha[4] = "";
				linha[5] = ((Admin) pessoas.get(i)).getStringEmpresas();
				modeloTable.addRow(linha);
			}
		}
	}

	//limpa a tableFuncionario
	private void limparTableFuncionarios() { 
		while (modeloTable.getRowCount() > 0) {
		modeloTable.removeRow(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(btnPesquisar)){
			if (admin.getCpf().equals(AdminSuper))
				this.preencherTableFuncionariosSuper(txtBusca.getText());
			else
				this.preencherTableFuncionarios(txtBusca.getText());
		}
		
		else if(e.getSource().equals(btnCadastrarFuncionario)){
			if(EpontoFachada.getInstance().getSizeEmpresas()<1)
				JOptionPane.showMessageDialog(null, "� necess�rio cadastrar uma empresa antes de cadastrar um funcion�rio.");
			else 
				ControladorDeTelas.getInstance().frameCadastrarFuncionario();
		}
		
		else if(e.getSource().equals(btnMostrarTodos)){
			if(admin.getCpf().equals(AdminSuper))
				this.preencherTableFuncionariosSuper(null);
			else 
				this.preencherTableFuncionarios(null);
		} 	
		
		else if(e.getSource().equals(btnExcluirFuncionrio)){
			if(tableFuncionarios.getSelectedRowCount()==0)
				JOptionPane.showMessageDialog(null, "Por favor, selecione pelo menos um funcion�rio.");
			else{
				String nomes[] = new String[tableFuncionarios.getSelectedRowCount()];
				int[] linhasSelecionadas = tableFuncionarios.getSelectedRows();
				for (int i = 0; i < tableFuncionarios.getSelectedRowCount(); i++){
					nomes[i] = (String) tableFuncionarios.getValueAt(linhasSelecionadas[i], 0);
				}
				try {
					EpontoFachada.getInstance().deletarPessoas(nomes);
					JOptionPane.showMessageDialog(null, "Funcion�rio (s) exclu�do (s) com sucesso.");
				} catch (FuncionarioNaoEncontradoException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		}
		
		else if(e.getSource().equals(btnCadastrarEmpresa)){
			ControladorDeTelas.getInstance().frameCadastrarEmpresa();
		}
		
		else if(e.getSource().equals(btnCadastrarAdministrador))
			if(EpontoFachada.getInstance().getSizeEmpresas()<1)
				JOptionPane.showMessageDialog(null, "� necess�rio cadastrar uma empresa antes de cadastrar um Administrador.");
			else 
			ControladorDeTelas.getInstance().frameCadastrarAdmin();
		else if(e.getSource().equals(cmbBxEmpresa)){
			this.preencherTableFuncionarios(null);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) { //evento para double click nna tableFuncionarios;
		if (e.getClickCount() == 2) {
		      JTable target = (JTable)e.getSource();
		      int row = target.getSelectedRow();
		      Pessoa pessoaSelecionada = null;
			try {
				pessoaSelecionada = EpontoFachada.getInstance().getPessoaNome((String) target.getValueAt(row, 0));
			} catch (FuncionarioNaoEncontradoException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		      if(pessoaSelecionada instanceof Funcionario)
				  ControladorDeTelas.getInstance().frameAdmin2((Funcionario) pessoaSelecionada);
			  else if(pessoaSelecionada instanceof Admin)
				  ControladorDeTelas.getInstance().frameEditarAdmin((Admin) pessoaSelecionada);
		      
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		ControladorDeTelas.getInstance().frameLogin();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		this.preencherTableFuncionarios(null);
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}


