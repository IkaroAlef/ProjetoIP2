package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import com.toedter.calendar.JCalendar;

import dados.exceptionsDados.FuncionarioNaoEncontradoException;
import neg�cio.EpontoFachada;
import neg�cio.entity_beans.Dispensa;
import neg�cio.entity_beans.Funcionario;
import neg�cio.entity_beans.RegPonto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class FrameAdmin2 extends JFrame implements PropertyChangeListener, ActionListener, MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JCalendar jcalendar;
	private Funcionario funcionario;
	private JButton btnIniciarFerias;
	private ImageIcon fotoPadrao;
	private JLabel lblFoto;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAdmin2 frame = new FrameAdmin2((Funcionario) EpontoFachada.getInstance().buscarPessoaNome("Ikaro"));
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
	public FrameAdmin2(Funcionario funcionario) {		
		super("Dados de " + funcionario.getNome());
		
		this.funcionario = funcionario;
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/*
		try {
			fotoPadrao = ImageIO.read(new File(String.format("Imagem %s.jpg", funcionario.getNome())));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		fotoPadrao = funcionario.getFotoPadrao();
		lblFoto = new JLabel(fotoPadrao, JLabel.CENTER);
		lblFoto.setLocation(551, 9);
		lblFoto.setSize(323, 277);
		contentPane.add(lblFoto);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(606, 314, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblNomeFuncionario = new JLabel(funcionario.getNome());
		lblNomeFuncionario.setBounds(679, 314, 165, 14);
		contentPane.add(lblNomeFuncionario);
		
		JLabel lblCpf = new JLabel("Cpf:");
		lblCpf.setBounds(606, 339, 46, 14);
		contentPane.add(lblCpf);
		
		JLabel lblCpfFuncionario = new JLabel(funcionario.getCpf());
		lblCpfFuncionario.setBounds(679, 339, 165, 14);
		contentPane.add(lblCpfFuncionario);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(606, 364, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblEmailFuncionario = new JLabel(funcionario.getEmail());
		lblEmailFuncionario.setBounds(679, 364, 165, 14);
		contentPane.add(lblEmailFuncionario);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(606, 389, 63, 14);
		contentPane.add(lblTelefone);
		
		JLabel lblTelefoneFuncionario = new JLabel(funcionario.getTelefone());
		lblTelefoneFuncionario.setBounds(679, 389, 165, 14);
		contentPane.add(lblTelefoneFuncionario);
		
		JLabel lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setBounds(606, 414, 63, 14);
		contentPane.add(lblEmpresa);
		
		JLabel lblEmpresaFuncionario = new JLabel(funcionario.getEmpresa().getNomeEmpresa());
		lblEmpresaFuncionario.setBounds(679, 414, 165, 14);
		contentPane.add(lblEmpresaFuncionario);
		
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setBounds(606, 439, 46, 14);
		contentPane.add(lblCargo);
		
		JLabel lblCargoFuncionario = new JLabel(funcionario.getCargo());
		lblCargoFuncionario.setBounds(679, 439, 165, 14);
		contentPane.add(lblCargoFuncionario);
		
		JLabel lblEscala = new JLabel("Escala:");
		lblEscala.setBounds(606, 464, 46, 14);
		contentPane.add(lblEscala);
		
		JLabel lblEscalaFuncionario = new JLabel(funcionario.getEscala());
		lblEscalaFuncionario.setBounds(679, 464, 165, 14);
		contentPane.add(lblEscalaFuncionario);
		
		JLabel lblHorarioChegada = new JLabel("Hor\u00E1rio Chegada:");
		lblHorarioChegada.setBounds(567, 489, 102, 14);
		contentPane.add(lblHorarioChegada);
		
		JLabel lblHorarioChegadaFuncionario = new JLabel(funcionario.getChegada().toString());
		lblHorarioChegadaFuncionario.setBounds(679, 489, 165, 14);
		contentPane.add(lblHorarioChegadaFuncionario);
		
		JLabel lblHorarioSaida = new JLabel("Hor\u00E1rio Sa\u00EDda:");
		lblHorarioSaida.setBounds(567, 514, 102, 14);
		contentPane.add(lblHorarioSaida);
		
		JLabel lblHorarioSaidaFuncionario = new JLabel(funcionario.getSaida().toString());
		lblHorarioSaidaFuncionario.setBounds(679, 514, 165, 14);
		contentPane.add(lblHorarioSaidaFuncionario);
		
		JLabel lblIntervaloSaida = new JLabel("Intervalo Sa\u00EDda:");
		lblIntervaloSaida.setBounds(567, 539, 102, 14);
		contentPane.add(lblIntervaloSaida);
		
		JLabel lblIntervaloSaidaFuncionario = new JLabel(funcionario.getIntervalo_out().toString());
		lblIntervaloSaidaFuncionario.setBounds(679, 539, 165, 14);
		contentPane.add(lblIntervaloSaidaFuncionario);
		
		JLabel lblIntervaloVolta = new JLabel("Intervalo Volta:");
		lblIntervaloVolta.setBounds(567, 564, 85, 14);
		contentPane.add(lblIntervaloVolta);
		
		JLabel lblIntervaloVoltaFuncionario = new JLabel(funcionario.getIntervalo_in().toString());
		lblIntervaloVoltaFuncionario.setBounds(679, 564, 165, 14);
		contentPane.add(lblIntervaloVoltaFuncionario);
		
		jcalendar = new JCalendar();
		jcalendar.getDayChooser().setAutoscrolls(true);
		jcalendar.setBounds(10, 11, 500, 350);
		Component[] componentesDias = jcalendar.getDayChooser().getDayPanel().getComponents();
		for (int i = 7; i < 49; i++){
			componentesDias[i].addMouseListener(this);
		}
		jcalendar.getMonthChooser().addPropertyChangeListener(this);
		jcalendar.getYearChooser().addPropertyChangeListener(this);
		this.ColorirCalendario(jcalendar.getMonthChooser().getMonth(),jcalendar.getYearChooser().getYear());
		contentPane.add(jcalendar);
		
		btnIniciarFerias = new JButton("Iniciar F�rias ou Licen�a");
		btnIniciarFerias.setBounds(563, 585, 177, 23);
		contentPane.add(btnIniciarFerias);
		btnIniciarFerias.addActionListener(this);
		
		}
	
	public void ColorirCalendario(int mes, int ano){
		mes++; //o array de dias no JCalendar come�a do 0. Por isso preciso somar 1 pra passar o mes correto para os metodos abaixo
		
		JPanel jPanelDays = jcalendar.getDayChooser().getDayPanel();
		Component component[] = jPanelDays.getComponents(); //componnentes do quadro Dias
		
		ArrayList <RegPonto> pontosDoFuncionario = null;
		try {
			pontosDoFuncionario = EpontoFachada.getInstance().getPontosDoFuncionario(funcionario.getCpf(),mes,ano);
		} catch (FuncionarioNaoEncontradoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			
			int qtdComponentesInvisiveis=0; //qntd de Components Invisiveis da primeira semana do m�s (j� que nem sempre o dia 1 come�a no Domingo, e quando nao come�a, os componentes continuam existindo por�m invis�veis)
			for (int i=7; i<=13;i++){
				if(!component[i].isVisible())
					qtdComponentesInvisiveis++;
			}
			
			 if(EpontoFachada.getInstance().dispensaContains(funcionario)){
//					System.out.println("teste");
					ArrayList <Dispensa> dispensas = EpontoFachada.getInstance().getDispensas(funcionario);
					for (int j = 0; j < dispensas.size(); j++){
						LocalDateTime dataInicio = dispensas.get(j).getInicio();
						int qtdDias = dispensas.get(j).getQtdDias();
						int inicio = dataInicio.getDayOfMonth() + 6 + qtdComponentesInvisiveis;
						int fim = dataInicio.plusDays(qtdDias).getDayOfMonth() + 6 + qtdComponentesInvisiveis;
					
						if(mes == dataInicio.getMonthValue()){
							component[inicio].setBackground(Color.blue);
						}
						if(mes == dataInicio.plusDays(qtdDias).getMonthValue()){
							component[fim].setBackground(Color.blue);
						}
					}
				}
			
			if (pontosDoFuncionario.size()==0){
				JOptionPane.showMessageDialog(null, "Nao h� pontos registrados nesse m�s e ano.");
				
			
			
			}else{
					
					int diaPrimeiroPonto = pontosDoFuncionario.get(0).getAgora().getDayOfMonth() + 6; //dia do primeiro ponto do funcionario (+6 por causa do painel de Dias que de 0 a 6 representa os nomes dos dias da semana)
		
					int comeca = diaPrimeiroPonto + qtdComponentesInvisiveis; //representa o indice do componente em que se deve come�ar a Colorir
		
//		for (int i = comeca; i < 49; i++) {
					for (int j = 0; j < pontosDoFuncionario.size(); j++){			
						if(pontosDoFuncionario.get(j).chegadaCorreta()){
							int i = pontosDoFuncionario.get(j).getAgora().getDayOfMonth() + qtdComponentesInvisiveis + 6;
							component[i].setBackground(Color.green);
						}
						else if (pontosDoFuncionario.get(j).chegadaAtrasada()){
							int i = pontosDoFuncionario.get(j).getAgora().getDayOfMonth() + qtdComponentesInvisiveis + 6;
							component[i].setBackground(Color.yellow);
						} 
					}
					if(funcionario.getEscala().equals("Seg. � Sex")){
						int max;
						if(jcalendar.getMonthChooser().getMonth()+1==LocalDateTime.now().getMonthValue())
							max = LocalDateTime.now().getDayOfMonth()+6+qtdComponentesInvisiveis;
						else
							max=49;
						for (int i = comeca; i < max; i++) {
							if( i % 7!=0 && i!=13 && i!=20 && i!=27 && i!=34 && i!=41 && !component[i].getBackground().equals(Color.green) && !component[i].getBackground().equals(Color.yellow)) //tirar sabados e domingos, por�m, hoje 27/06/2015 as 12h acredito que corrigi.
								component[i].setBackground(Color.red);				
						}
					}
			}
		}
			}
		
	

//	this.ColorirCalendario(jcalendar.getMonthChooser().getMonth()+1, jcalendar.getYearChooser().getYear());

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if(e.getSource().equals(jcalendar.getMonthChooser())){
			this.ColorirCalendario(jcalendar.getMonthChooser().getMonth(), jcalendar.getYearChooser().getYear());
		}else{ 
			if(e.getSource().equals(jcalendar.getYearChooser())		){
				this.ColorirCalendario(jcalendar.getMonthChooser().getMonth(), jcalendar.getYearChooser().getYear());
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(btnIniciarFerias)){
			DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
			int qtdDias = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de Dias: "));
			Dispensa dispensa = new Dispensa(funcionario, LocalDateTime.now().plusDays(1) ,qtdDias);
			EpontoFachada.getInstance().adicionarDispensa(dispensa);
			JOptionPane.showMessageDialog(null, "Dispensa adicionada com sucesso. Data de retorno das f�rias: "+ formatador.format(LocalDateTime.now().plusDays(qtdDias+1)));
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (e.getClickCount() == 2) {
				JPanel jPanelDays = jcalendar.getDayChooser().getDayPanel();
				Component components[] = jPanelDays.getComponents(); //componentes do quadro Dias
				
				int qtdComponentesInvisiveis=0; //qntd de Components Invisiveis da primeira semana do m�s (j� que nem sempre o dia 1 come�a no Domingo, e quando nao come�a, os componentes continuam existindo por�m invis�veis)
				for (int i=7; i<=13;i++){
					if(!components[i].isVisible())
						qtdComponentesInvisiveis++;
				}
				int dia = jcalendar.getDayChooser().getDay();
				int mes = jcalendar.getMonthChooser().getMonth() +1; //+1 porque o getMonth considera Janeiro como 0.
				int ano = jcalendar.getYearChooser().getYear();
				
				String ponto = "";
				ArrayList<RegPonto> pontos = null;
				try {
					pontos = EpontoFachada.getInstance().getPontosDoFuncionario(funcionario.getCpf(), dia, mes, ano);
				} catch (FuncionarioNaoEncontradoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if(pontos.size()==0) //se nao houver pontos registrados
					JOptionPane.showMessageDialog(null, "N�o h� pontos registrados neste dia.", "Pontos", JOptionPane.INFORMATION_MESSAGE);
				
				else if(components[dia+6+qtdComponentesInvisiveis].getBackground().equals(Color.red)){ //se faltou  **** n�o est� funcionando, quando dou um click num dia, ele muda a cor.
					System.out.println("vermelho");
					JOptionPane.showMessageDialog(null, "O funcionario faltou neste dia.", "Pontos", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					for (int i = 0; i < pontos.size();i++){ //mostra todos os pontos daquele dia
						ponto += "- "+pontos.get(i).getAgoraFormatada()+"\n"; 		
					}
					JOptionPane.showMessageDialog(null, ponto, "Pontos", JOptionPane.INFORMATION_MESSAGE);
				}
				this.ColorirCalendario(mes-1, ano); //-1 pq neste metodo eu somo 1, e no metodo Colorir soma-se 1 ao mes (ou seja, ficaria +2)
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
			
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
