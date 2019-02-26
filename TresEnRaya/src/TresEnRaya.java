import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;

public class TresEnRaya extends JFrame {

	private JPanel contentPane;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btnColor1;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JButton btnColor2;
	private JButton btn7;
	private JButton btn8;
	private JButton btn9;
	private JButton btnAgain;
	private JButton btnExit;
	private JLabel lblJugador1;
	private JLabel lblJugador2;
	private JButton[] botones;
	private int turno;
	private String jugador1="Jugador1";
	private String jugador2="Jugador2";
	private Timer reloj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TresEnRaya frame = new TresEnRaya();
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
	public TresEnRaya() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 5, 0, 0));
		
		btn1 = new JButton("");
		contentPane.add(btn1);
		
		btn2 = new JButton("");
		contentPane.add(btn2);
		
		btn3 = new JButton("");
		contentPane.add(btn3);
		
		lblJugador1 = new JLabel("Jugador1");
		contentPane.add(lblJugador1);
		
		btnColor1 = new JButton("");
		btnColor1.setBackground(new Color(128, 0, 0));
		contentPane.add(btnColor1);
		
		btn4 = new JButton("");
		contentPane.add(btn4);
		
		btn5 = new JButton("");
		contentPane.add(btn5);
		
		btn6 = new JButton("");
		contentPane.add(btn6);
		
		lblJugador2 = new JLabel("Jugador2");
		contentPane.add(lblJugador2);
		
		btnColor2 = new JButton("");
		btnColor2.setBackground(new Color(0, 0, 128));
		contentPane.add(btnColor2);
		
		btn7 = new JButton("");
		contentPane.add(btn7);
		
		btn8 = new JButton("");
		contentPane.add(btn8);
		
		btn9 = new JButton("");
		contentPane.add(btn9);
		
		btnAgain = new JButton("Jugar Otra");
		contentPane.add(btnAgain);
		
		btnExit = new JButton("Salir");
		contentPane.add(btnExit);
		//QUIEN EMPIEZA EN LA PRIMERA PARTIDA
		//turno=(int) (Math.random()*2);
		
		Random r;
		r=new Random();
		turno=r.nextInt(2);
		
		crearBotones();
		registrarEventos();
	}//FIN DEL CONSTRUCTOR

	private void registrarEventos() {
		/*REGISTRAR EVENTO PARA LABEL J1*/
		lblJugador1.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//MOSTRAR UNA VENTANA DE ENTRADA DE DATOS PARA QUE PIDA EL NOMBRE
				String nombre;
				if(btnAgain.isEnabled()){
					nombre=JOptionPane.showInputDialog("Introduce el nombre del jugador 1: ");
					if(nombre!=null){
						lblJugador1.setText(nombre);
						jugador1=nombre;
					} else {
						lblJugador1.setText("Jugador1");
					}
				}
			}
		});
		/*REGISTRAR EVENTO PARA LABEL J2*/
		lblJugador2.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//MOSTRAR UNA VENTANA DE ENTRADA DE DATOS PARA QUE PIDA EL NOMBRE
				String nombre;
				if(btnAgain.isEnabled()){
					nombre=JOptionPane.showInputDialog("Introduce el nombre del jugador 2: ");
					if(nombre!=null){
						lblJugador2.setText(nombre);
						jugador2=nombre;
					} else {
						lblJugador2.setText("Jugador2");
					}
				}
			}
		});
		/*REGISTRAR EVENTO BOTON SALIR*/
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "¿Seguro que quieres salir?", "Salir", 
						JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				System.exit(0);
				}
			}
		});
		//REGISTRAR EVENTO BOTON NUEVA PARTIDA
		btnAgain.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//PARA QUE COMIENZE EL JUGADOR QUE HA PERDIDO LA PARTIDA ANTERIOR
				turno=(turno+1)%2;
				//DESACTIVAR LABELS Y BOTONES DE LA DERECHA EXCEPTO SALIR
				estadoControles(false);
				//ACTIVAR LOS BOTONES DE LA IZQUIERDA
				estadoBotones(true);
				//RESTAURAR COLORES DE LOS 9 BOTONES
				restaurarColores();
				//INDICAMOS DE QUIEN ES EL TURNO INICIAL
				resaltar();
				//DETIENE EL RELOJ
				if(reloj!=null){
					reloj.stop();
				}
			}
		});
		//REGISTRAR LOS EVENTOS CLICK EN CADA BOTON DEL TABLERO DE JUEGO
		for (int i = 0; i < botones.length; i++) {
			botones[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					jugar((JButton)e.getSource());
				}
			});
		}
		//ELEGIR COLOR 1
		btnColor1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnColor1.setBackground(elegirColor(btnColor1));
			}
		});
		//ELEGIR COLOR 2
		btnColor2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnColor2.setBackground(elegirColor(btnColor2));
			}
		});
		
	}//FIN DE REGISTRAR EVENTOS
	//***************************JUEGO**************************
	private void jugar(JButton source) {
		//PONER EL BOTON DE UN COLOR (AZUL O ROJO)
		if(turno==0){
			source.setBackground(btnColor1.getBackground());
			source.setEnabled(false);
		} else {
			source.setBackground(btnColor2.getBackground());
			source.setEnabled(false);
		}
		//CONTROLAMOS EL FINAL DEL JUEGO
		if(finJuego()==1){
			estadoBotones(false);
			estadoControles(true);
			return;
		} else if (finJuego()==2){
			estadoControles(true);
			JOptionPane.showMessageDialog(this, "¡Han empatadoooooo!");
		}
		//CAMBIAR DE TURNO
		if(turno==0){
			turno=1;
		} else {
			turno=0;
		}
		resaltar();
		/*turno=(turno+1)%2; Esta es mejor para juegos con mas de dos jugadores*/
	}//***************************FIN DE JUEGO**************************

	private void parpadear(JButton btn1, JButton btn2, JButton btn3) {
		//ACTIVAR UN RELOJ
		reloj=new Timer(300, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btn1.getBackground()==btnColor1.getBackground() || btn1.getBackground()==btnColor2.getBackground()){
					btn1.setBackground(btnExit.getBackground());
					btn2.setBackground(btnExit.getBackground());
					btn3.setBackground(btnExit.getBackground());
				} else if (btn1.getBackground()==btnExit.getBackground()) {
					if(turno==0){
						btn1.setBackground(btnColor1.getBackground());
						btn2.setBackground(btnColor1.getBackground());
						btn3.setBackground(btnColor1.getBackground());
					} else {
						btn1.setBackground(btnColor2.getBackground());
						btn2.setBackground(btnColor2.getBackground());
						btn3.setBackground(btnColor2.getBackground());
					}
				}
			}
		});
		reloj.start();
	}

	private void estadoControles(boolean estado){
		/*lblJugador1.setEnabled(estado);
		lblJugador2.setEnabled(estado);*/
		btnAgain.setEnabled(estado);
		btnColor1.setEnabled(estado);
		btnColor2.setEnabled(estado);
	}

	private void crearBotones() {
		botones=new JButton[9];
		botones[0]=btn1;
		botones[1]=btn2;
		botones[2]=btn3;
		botones[3]=btn4;
		botones[4]=btn5;
		botones[5]=btn6;
		botones[6]=btn7;
		botones[7]=btn8;
		botones[8]=btn9;
		estadoBotones(false);
	}
	
	//FUNCION PARA HABILITAR O DESHABILITAR TODOS LOS 9 BOTONES
	private void estadoBotones(boolean estado){
		for (int i=0;i<botones.length;i++){
			botones[i].setEnabled(estado);
		}
	}
	
	private void restaurarColores(){
		for (int i=0;i<botones.length;i++){
			botones[i].setBackground(btnExit.getBackground());
		}
	}
	//FUNCION PARA SABER SI ES FIN DE JUEGO O NO
	//0= No es fin de juego
	//1= Gana alguien
	//2= Empate
	private int finJuego(){
		//COMPROBACION EN HORIZONTAL
		for (int i=0;i<3;i++){
			if(botones[i*3].getBackground()==botones[i*3+1].getBackground() && botones[i*3].getBackground()==botones[i*3+2].getBackground()
					&& botones[i*3].getBackground()!=btnExit.getBackground()){
				parpadear(botones[i*3], botones[i*3+1], botones[i*3+2]);
				return 1;
				
			}
		}
		//COMPROBACION EN VERTICAL
		for (int i=0;i<3;i++){
			if(botones[i].getBackground()==botones[i+3].getBackground() && botones[i].getBackground()==botones[i+6].getBackground()
					&& botones[i].getBackground()!=btnExit.getBackground()){
				parpadear(botones[i], botones[i+3], botones[i+6]);
				return 1;
				
			}
		}
		//COMPROBACION EN DIAGONAL 1
			if(btn1.getBackground()==btn5.getBackground() && btn5.getBackground()==btn9.getBackground() && btn1.getBackground()!=btnExit.getBackground()){
			parpadear(btn1, btn5, btn9);
				return 1;
			}
		//COMPROBACION EN DIAGONAL 2
			if(btn3.getBackground()==btn5.getBackground() && btn5.getBackground()==btn7.getBackground() && btn3.getBackground()!=btnExit.getBackground()){
				parpadear(btn3, btn5, btn7);
				return 1;
			}
		//EMPATE
		int cuenta=0;
		for (int i=0;i<botones.length;i++){
			if(!botones[i].isEnabled()){ // ! <-- Todo lo que esta por delante es "if not"
				cuenta++;
			}
			if(cuenta==9){
				return 2;
			}
		}	
		return 0;
	}
	
	public void resaltar(){
		if(turno==0){
			//RESALTAR TEXTO Y COLOR DEL JUGADOR 1
			lblJugador1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
			lblJugador1.setForeground(btnColor1.getBackground());
			lblJugador2.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblJugador2.setForeground(Color.BLACK);
		}else {
			//RESALTAR TEXTO Y COLOR DEL JUGADOR 2
			lblJugador2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
			lblJugador2.setForeground(btnColor2.getBackground());
			lblJugador1.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblJugador1.setForeground(Color.BLACK);
		}
	}
	
	public Color elegirColor(JButton btn){
		//MOSTRAR UN CUADRO DE DIALOGO
		Color color;
		color=JColorChooser.showDialog(this, "Elige color", btn.getBackground());
		if(color!=null){
			return color;
		} else {
			return btn.getBackground();
		}
	}

}