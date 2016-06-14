package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import dados.exceptionsDados.EmpresaNaoEncontradaException;
import neg�cio.EpontoFachada;
import neg�cio.entity_beans.Empresa;
import neg�cio.entity_beans.Funcionario;
import neg�cio.entity_beans.exceptionsBeans.CNPJInvalidoException;
import neg�cio.entity_beans.exceptionsBeans.NomeInvalidoException;
import java.awt.Color;
import java.awt.Font;

public class FrameAdminCadastroFuncionario extends JFrame implements ActionListener, WindowListener {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtSenha;
	private JTextField txtEmail;
	private JTextField txtTelefone;
	private JTextField txtCargo;
	private JTextField txtHoraChegada;
	private JTextField txtMinutosChegada;
	private JTextField txtHoraSaida;
	private JTextField txtMinutosSaida;
	private JTextField txtHoraSaidaIntervalo;
	private JTextField txtMinutosSaidaIntervalo;
	private JTextField txtHoraChegadaIntervalo;
	private JTextField txtMinutosChegadaIntervalo;
	private JComboBox<Empresa> cmbBxEmpresa;
	private JComboBox <String> cmbBxEscala;
	private JButton btnLimpar;
	private JButton btnSalvar;
	
	//webcam
	private Webcam wCam;
	private WebcamPanel wCamPanel;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAdminCadastroFuncionario frame = new FrameAdminCadastroFuncionario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameAdminCadastroFuncionario() {
		setTitle("Cadastrar Funcion\u00E1rio");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 810, 423);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(169, 169, 169));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addWindowListener(this);
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setBackground(new Color(255, 255, 255));
		lblNome.setBounds(10, 11, 46, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(10, 25, 490, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CPF:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(10, 61, 46, 14);
		contentPane.add(lblNewLabel);
		
		txtCPF = new JFormattedTextField(createFormatter("###.###.###-##"));
		txtCPF.setColumns(10);
		txtCPF.setBounds(10, 76, 220, 20);
		contentPane.add(txtCPF);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setBounds(257, 61, 46, 14);
		contentPane.add(lblSenha);
		
		txtSenha = new JTextField();
		txtSenha.setColumns(10);
		txtSenha.setBounds(256, 76, 244, 20);
		contentPane.add(txtSenha);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setBounds(10, 107, 46, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(10, 122, 490, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setForeground(new Color(255, 255, 255));
		lblEmpresa.setBounds(10, 153, 72, 14);
		contentPane.add(lblEmpresa);
		
		cmbBxEmpresa = new JComboBox<Empresa>();
		cmbBxEmpresa.setBounds(10, 167, 116, 20);
		contentPane.add(cmbBxEmpresa);
		for(int i=0;i<EpontoFachada.getInstance().getSizeEmpresas() ; i++){
			cmbBxEmpresa.addItem(EpontoFachada.getInstance().getEmpresas(null).get(i));
		}
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setForeground(new Color(255, 255, 255));
		lblTelefone.setBounds(152, 153, 78, 14);
		contentPane.add(lblTelefone);
		
		txtTelefone = new JFormattedTextField(createFormatter("(##)#####-####"));
		txtTelefone.setBounds(152, 167, 151, 20);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setForeground(new Color(255, 255, 255));
		lblCargo.setBounds(322, 153, 58, 14);
		contentPane.add(lblCargo);
		
		txtCargo = new JTextField();
		txtCargo.setBounds(321, 167, 155, 20);
		contentPane.add(txtCargo);
		txtCargo.setColumns(10);
		
		JLabel lblEscala = new JLabel("Escala");
		lblEscala.setForeground(new Color(255, 255, 255));
		lblEscala.setBounds(10, 198, 46, 14);
		contentPane.add(lblEscala);
		
		cmbBxEscala = new JComboBox <String>();
		cmbBxEscala.setBounds(10, 215, 116, 20);
		contentPane.add(cmbBxEscala);
		cmbBxEscala.addItem("Seg. � Sex");
		cmbBxEscala.addItem("Dia sim Dia n�o");
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 246, 470, 2);
		contentPane.add(separator);
		
		JLabel lblHorrioChegada = new JLabel("Hor\u00E1rio Chegada:");
		lblHorrioChegada.setForeground(new Color(255, 255, 255));
		lblHorrioChegada.setBounds(10, 259, 104, 14);
		contentPane.add(lblHorrioChegada);
		
		txtHoraChegada = new JTextField();
		txtHoraChegada.setBounds(10, 279, 30, 20);
		contentPane.add(txtHoraChegada);
		txtHoraChegada.setColumns(10);
		
		txtMinutosChegada = new JTextField();
		txtMinutosChegada.setColumns(10);
		txtMinutosChegada.setBounds(52, 279, 30, 20);
		contentPane.add(txtMinutosChegada);
		
		JLabel lbl2ptChegada = new JLabel(":");
		lbl2ptChegada.setBounds(45, 283, 10, 10);
		contentPane.add(lbl2ptChegada);
		
		JLabel lblHorarioSaida = new JLabel("Hor\u00E1rio Sa\u00EDda:");
		lblHorarioSaida.setForeground(new Color(255, 255, 255));
		lblHorarioSaida.setBounds(120, 259, 88, 14);
		contentPane.add(lblHorarioSaida);
		
		txtHoraSaida = new JTextField();
		txtHoraSaida.setColumns(10);
		txtHoraSaida.setBounds(120, 279, 30, 20);
		contentPane.add(txtHoraSaida);
		
		JLabel lbl2ptSaida = new JLabel(":");
		lbl2ptSaida.setBounds(155, 283, 10, 10);
		contentPane.add(lbl2ptSaida);
		
		txtMinutosSaida = new JTextField();
		txtMinutosSaida.setColumns(10);
		txtMinutosSaida.setBounds(162, 279, 30, 20);
		contentPane.add(txtMinutosSaida);
		
		JLabel lblHorarioSaidaIntervalo = new JLabel("Hor\u00E1rio Sa\u00EDda Intervalo:");
		lblHorarioSaidaIntervalo.setForeground(new Color(255, 255, 255));
		lblHorarioSaidaIntervalo.setBounds(218, 259, 137, 14);
		contentPane.add(lblHorarioSaidaIntervalo);
		
		txtHoraSaidaIntervalo = new JTextField();
		txtHoraSaidaIntervalo.setColumns(10);
		txtHoraSaidaIntervalo.setBounds(247, 279, 30, 20);
		contentPane.add(txtHoraSaidaIntervalo);
		
		JLabel lbl2ptSaidaIntervalo = new JLabel(":");
		lbl2ptSaidaIntervalo.setBounds(282, 283, 10, 10);
		contentPane.add(lbl2ptSaidaIntervalo);
		
		txtMinutosSaidaIntervalo = new JTextField();
		txtMinutosSaidaIntervalo.setColumns(10);
		txtMinutosSaidaIntervalo.setBounds(289, 279, 30, 20);
		contentPane.add(txtMinutosSaidaIntervalo);
		
		JLabel lblHorrioChegadaIntervalo = new JLabel("Hor\u00E1rio Chegada Intervalo:");
		lblHorrioChegadaIntervalo.setForeground(new Color(255, 255, 255));
		lblHorrioChegadaIntervalo.setBounds(355, 259, 150, 14);
		contentPane.add(lblHorrioChegadaIntervalo);
		
		txtHoraChegadaIntervalo = new JTextField();
		txtHoraChegadaIntervalo.setColumns(10);
		txtHoraChegadaIntervalo.setBounds(384, 279, 30, 20);
		contentPane.add(txtHoraChegadaIntervalo);
		
		JLabel lbl2ptChegadaIntervalo = new JLabel(":");
		lbl2ptChegadaIntervalo.setBounds(419, 283, 10, 10);
		contentPane.add(lbl2ptChegadaIntervalo);
		
		txtMinutosChegadaIntervalo = new JTextField();
		txtMinutosChegadaIntervalo.setColumns(10);
		txtMinutosChegadaIntervalo.setBounds(426, 279, 30, 20);
		contentPane.add(txtMinutosChegadaIntervalo);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setForeground(new Color(0,0,0));
		btnSalvar.setBounds(585, 259, 149, 23);
		btnSalvar.addActionListener(this);
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(btnSalvar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setForeground(new Color(0, 0, 0));
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnLimpar.setBounds(585, 293, 149, 23);
		btnLimpar.addActionListener(this);
		contentPane.add(btnLimpar);
		
		//webcam
		wCam = Webcam.getDefault();
		wCam.setViewSize(WebcamResolution.VGA.getSize());
		wCamPanel = new WebcamPanel(wCam);
		wCamPanel.setBounds(520, 8, 260, 240);
		contentPane.add(wCamPanel);
		
		btnNewButton = new JButton("Cancelar");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBounds(585, 327, 149, 23);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(btnNewButton);
	}
	
	protected MaskFormatter createFormatter(String s) {
		MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	    } catch (java.text.ParseException exc) {
	        System.err.println("formatter is bad: " + exc.getMessage());
	        System.exit(-1);
	    }
	    return formatter;
	}

	private void limparCampos(){
		txtNome.setText("");
		txtCPF.setText("");
		txtEmail.setText("");
		txtTelefone.setText("");
		txtSenha.setText("");
		txtCargo.setText("");
		txtHoraChegada.setText("");
		txtMinutosChegada.setText("");
		txtHoraSaida.setText("");
		txtMinutosSaida.setText("");
		txtHoraSaidaIntervalo.setText("");
		txtMinutosSaidaIntervalo.setText("");
		txtHoraChegadaIntervalo.setText("");
		txtMinutosChegadaIntervalo.setText("");			
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnLimpar)){
			this.limparCampos();
		}
		else if(e.getSource().equals(btnSalvar)){
			Funcionario funcionario = null;
			String nome = txtNome.getText();
			String cpf = txtCPF.getText();
			String email = txtEmail.getText();
			char[] senha = txtSenha.getText().toCharArray();
			String telefone = txtTelefone.getText();
			String cargo = txtCargo.getText();
			Empresa empresa = null;
			LocalTime horaChegada = null; 
			LocalTime horaSaida = null ;
			LocalTime horaChegadaIntervalo = null;
			LocalTime horaSaidaIntervalo = null ;
			
			try {
				empresa = (Empresa) EpontoFachada.getInstance().buscaEmpresaNome(cmbBxEmpresa.getSelectedItem().toString());
				horaChegada = LocalTime.of( Integer.parseInt(txtHoraChegada.getText()) , Integer.parseInt( txtMinutosChegada.getText()) );
				horaSaida = LocalTime.of( Integer.parseInt(txtHoraSaida.getText()) , Integer.parseInt( txtMinutosSaida.getText()) );
				horaChegadaIntervalo = LocalTime.of( Integer.parseInt(txtHoraChegadaIntervalo.getText()) , Integer.parseInt( txtMinutosChegadaIntervalo.getText()) );
				horaSaidaIntervalo = LocalTime.of( Integer.parseInt(txtHoraSaidaIntervalo.getText()) , Integer.parseInt( txtMinutosSaidaIntervalo.getText()) );
				funcionario = new Funcionario(nome, cpf, email, senha, telefone, empresa, cargo, "Seg. � Sex", horaChegada, horaSaida, horaChegadaIntervalo, horaSaidaIntervalo);
				funcionario.setFotoPadrao(new ImageIcon(wCam.getImage()));

				EpontoFachada.getInstance().adicionarPessoa(funcionario);
				JOptionPane.showMessageDialog(null, "Funcion�rio cadastrado com sucesso" );
				this.limparCampos();
				
			} catch (EmpresaNaoEncontradaException e3) {
				JOptionPane.showMessageDialog(null, e3.getMessage());
			}catch(NumberFormatException e2){
				JOptionPane.showMessageDialog(null, "Por favor, digite apenas n�meros.");
				txtHoraChegada.requestFocus();
			}catch(NomeInvalidoException e1){
				JOptionPane.showMessageDialog(null, e1.getMessage() );
			}
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {
		wCam.close();
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
			
}
