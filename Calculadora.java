/*Calculadora simple elaborada con Java en el IDE Eclipse*/
package calculadora;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Calculadora 
{
	/*Declaracion de variables*/
	private JFrame frame;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel txtOperacion;
	
	private JButton btnIgual;
	private JButton btnC;
	private JButton btnCE;
	private JButton btnMod;
	private JButton btnDiv;
	private JButton btnMult;
	private JButton btnResta;
	private JButton btnSuma;
	
	private JButton btnPunto;
	private JButton btn0;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JButton btn7;
	private JButton btn8;
	private JButton btn9;
	
	//Invocar al motor de JavaScript quien se encarga de realizar las operaciones ingresadas a la calculadora.
	ScriptEngineManager sem = new ScriptEngineManager();
	ScriptEngine se = sem.getEngineByName("JavaScript");
	
	//Booleano que se encarga de revisar si la aplicacion se encuentra en modo oscuro o modo claro.
	boolean oscuro = false;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Calculadora window = new Calculadora();
					window.frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public Calculadora() 
	{
		initialize();
	}
	
	//Funcion que se encarga de mostrar en pantalla (una etiqueta) los botones presionados.
	public void ponerNumero(String tecla)
	{
		txtOperacion.setText(txtOperacion.getText() + tecla);
	}
	
	/*Funcion que limita la cantidad de caracteres (en 18) para que no sobrepase el limite de la etiqueta donde se 
	muestran las operaciones*/
	public void limiteEtiqueta(String tecla)
	{
		int longOp = txtOperacion.getText().length();
		
		if(longOp >= 18)
		{
			ponerNumero("");
		}
		else
		{
			ponerNumero(tecla);
			btnIgual.doClick();
			/*La funcion doClick() sirve para que se muestre el resultado de las operaciones en tiempo real
			(mientras se van ingresando los numeros y operaciones) haciendo que se pulse el boton igual cada
			vez que se presione un boton*/
		}
	}
	
	/*Cambiar el color correspondiente a oscuro en los botones de operacion (suma, resta, etc.)*/
	public void cambiarOpOscuro(JButton btn)
	{
		btn.setIcon(new ImageIcon("Imgs/btn_dark.png"));
		btn.setPressedIcon(new ImageIcon("Imgs/btn_dark_pressed.png"));
		btn.setForeground(new Color(13, 179, 135));
	}
	
	/*Cambiar el color correspondiente a claro en los botones de operacion (suma, resta, etc.)*/
	public void cambiarOpClaro(JButton btn)
	{
		btn.setIcon(new ImageIcon("Imgs/btn.png"));
		btn.setPressedIcon(new ImageIcon("Imgs/btn_pressed.png"));
		btn.setForeground(new Color(52, 59, 68));
	}
	
	/*Cambiar el color correspondiente a oscuro en los botones numericos*/
	public void cambiarNumOscuros(JButton btn)
	{
		btn.setIcon(new ImageIcon("Imgs/btn2_dark.png"));
		btn.setPressedIcon(new ImageIcon("Imgs/btn_dark_pressed.png"));
		btn.setForeground(Color.white);
	}
	
	/*Cambiar el color correspondiente a oscuro en los botones numericos*/
	public void cambiarNumClaros(JButton btn)
	{
		btn.setIcon(new ImageIcon("Imgs/btn2.png"));
		btn.setPressedIcon(new ImageIcon("Imgs/btn_pressed.png"));
		btn.setForeground(new Color(52, 59, 68));
	}
	
	/*Esta funcion por defecto inicia las caracteristicas de todos los widgets creados para la aplicacion*/
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 550);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		/*Panel superior que contiene las etiquetas que funcionan como pantalla*/
		panel = new JPanel();
		panel.setBounds(0, 0, 294, 160);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(237, 255, 251));
		
		/*Etiqueta que muestra las operaciones*/
		txtOperacion = new JLabel("");
		txtOperacion.setFont(new Font("SansSerif", Font.PLAIN, 25));
		txtOperacion.setForeground(new Color(52, 59, 68));
		txtOperacion.setHorizontalAlignment(SwingConstants.RIGHT);
		txtOperacion.setBounds(10, 44, 260, 34);
		panel.add(txtOperacion);
		
		/*Etiqueta que muestra los resultados de las operaciones*/
		JLabel txtResultado = new JLabel("");
		txtResultado.setFont(new Font("SansSerif", Font.PLAIN, 40));
		txtResultado.setForeground(new Color(52, 59, 68));
		txtResultado.setHorizontalAlignment(SwingConstants.RIGHT);
		txtResultado.setBounds(20, 89, 260, 47);
		panel.add(txtResultado);
		
		/*Boton que sirve para alternar entre modo oscuro y modo claro*/
		JButton btnModo = new JButton("");
		btnModo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				/*Si la aplicacion esta en modo claro, al hacer click en el boton se cambiara a oscuro*/
				if(!oscuro)
				{
					panel.setBackground(new Color(33, 43, 65));
					panel_1.setBackground(new Color(46, 57, 81));
					txtOperacion.setForeground(new Color(13, 179, 135));
					txtResultado.setForeground(new Color(13, 179, 135));
					btnModo.setIcon(new ImageIcon("Imgs/btn_cambiarColor_dark.png"));
					
					cambiarOpOscuro(btnC);
					cambiarOpOscuro(btnCE);
					cambiarOpOscuro(btnMod);
					cambiarOpOscuro(btnDiv);
					cambiarOpOscuro(btnMult);
					cambiarOpOscuro(btnResta);
					cambiarOpOscuro(btnSuma);
					
					cambiarNumOscuros(btnPunto);
					cambiarNumOscuros(btn0);
					cambiarNumOscuros(btn1);
					cambiarNumOscuros(btn2);
					cambiarNumOscuros(btn3);
					cambiarNumOscuros(btn4);
					cambiarNumOscuros(btn5);
					cambiarNumOscuros(btn6);
					cambiarNumOscuros(btn7);
					cambiarNumOscuros(btn8);
					cambiarNumOscuros(btn9);
					
					btnIgual.setIcon(new ImageIcon("Imgs/btn_Igual_dark.png"));
					btnIgual.setPressedIcon(new ImageIcon("Imgs/btn_Igual_dark_pressed.png"));
					btnIgual.setForeground(new Color(33, 43, 65));
					
					oscuro = true;
				}
				/*Si la aplicacion esta en modo oscuro, al hacer click en el boton se cambiara a claro*/
				else if(oscuro)
				{
					panel.setBackground(new Color(237, 255, 251));
					panel_1.setBackground(new Color(255, 255, 255));
					txtOperacion.setForeground(new Color(52, 59, 68));
					txtResultado.setForeground(new Color(52, 59, 68));
					btnModo.setIcon(new ImageIcon("Imgs/btn_cambiarColor.png"));
					
					cambiarOpClaro(btnC);
					cambiarOpClaro(btnCE);
					cambiarOpClaro(btnMod);
					cambiarOpClaro(btnDiv);
					cambiarOpClaro(btnMult);
					cambiarOpClaro(btnResta);
					cambiarOpClaro(btnSuma);
					
					cambiarNumClaros(btnPunto);
					cambiarNumClaros(btn0);
					cambiarNumClaros(btn1);
					cambiarNumClaros(btn2);
					cambiarNumClaros(btn3);
					cambiarNumClaros(btn4);
					cambiarNumClaros(btn5);
					cambiarNumClaros(btn6);
					cambiarNumClaros(btn7);
					cambiarNumClaros(btn8);
					cambiarNumClaros(btn9);
					
					btnIgual.setIcon(new ImageIcon("Imgs/btn_Igual.png"));
					btnIgual.setPressedIcon(new ImageIcon("Imgs/btn_pressed.png"));
					btnIgual.setForeground(new Color(255, 255, 255));
					
					oscuro = false;
				}
			}
		});
		btnModo.setBounds(223, 10, 40, 20);
		btnModo.setIcon(new ImageIcon("Imgs/btn_cambiarColor.png"));
		btnModo.setFocusPainted(false);
		btnModo.setBorderPainted(false);
		panel.add(btnModo);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 160, 294, 361);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 255, 255));
		
		/*Boton que sirve para limpiar lo que haya en pantalla*/
		btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				txtOperacion.setText("");
				txtResultado.setText("");
			}
		});
		btnC.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnC.setForeground(new Color(52, 59, 68));
		btnC.setHorizontalTextPosition(0);
		btnC.setIcon(new ImageIcon("Imgs/btn.png"));
		btnC.setBounds(10, 10, 50, 50);
		btnC.setFocusPainted(false);
		btnC.setBorderPainted(false);
		btnC.setPressedIcon(new ImageIcon("Imgs/btn_pressed.png"));
		panel_1.add(btnC);
		
		/*Este boton elimina el ultimo caracter que se encuentre de izquierda a derecha en la pantalla de operaciones*/
		btnCE = new JButton("<-");
		btnCE.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String texto = txtOperacion.getText().substring(0, txtOperacion.getText().length() - 1);
				txtOperacion.setText(texto);
				btnIgual.doClick();
			}
		});
		btnCE.setIcon(new ImageIcon("Imgs/btn.png"));
		btnCE.setHorizontalTextPosition(0);
		btnCE.setForeground(new Color(52, 59, 68));
		btnCE.setFont(new Font("SansSerif", Font.PLAIN, 17));
		btnCE.setFocusPainted(false);
		btnCE.setBorderPainted(false);
		btnCE.setBounds(80, 10, 50, 50);
		btnCE.setPressedIcon(new ImageIcon("Imgs/btn_pressed.png"));
		panel_1.add(btnCE);
		
		/*Boton con funcion de modulo*/
		btnMod = new JButton("%");
		btnMod.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ponerNumero("%");
			}
		});
		btnMod.setIcon(new ImageIcon("Imgs/btn.png"));
		btnMod.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMod.setForeground(new Color(52, 59, 68));
		btnMod.setFont(new Font("SansSerif", Font.PLAIN, 18));
		btnMod.setFocusPainted(false);
		btnMod.setBorderPainted(false);
		btnMod.setBounds(150, 10, 50, 50);
		btnMod.setPressedIcon(new ImageIcon("Imgs/btn_pressed.png"));
		panel_1.add(btnMod);
		
		btnDiv = new JButton("\u00F7");
		btnDiv.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ponerNumero("/");
			}
		});
		btnDiv.setIcon(new ImageIcon("Imgs/btn.png"));
		btnDiv.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDiv.setForeground(new Color(52, 59, 68));
		btnDiv.setFont(new Font("SansSerif", Font.PLAIN, 25));
		btnDiv.setFocusPainted(false);
		btnDiv.setBorderPainted(false);
		btnDiv.setBounds(220, 10, 50, 50);
		btnDiv.setPressedIcon(new ImageIcon("Imgs/btn_pressed.png"));
		panel_1.add(btnDiv);
		
		btnMult = new JButton("x");
		btnMult.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ponerNumero("*");
			}
		});
		btnMult.setIcon(new ImageIcon("Imgs/btn.png"));
		btnMult.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMult.setForeground(new Color(52, 59, 68));
		btnMult.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnMult.setFocusPainted(false);
		btnMult.setBorderPainted(false);
		btnMult.setBounds(220, 80, 50, 50);
		btnMult.setPressedIcon(new ImageIcon("Imgs/btn_pressed.png"));
		panel_1.add(btnMult);
		
		btnResta = new JButton("-");
		btnResta.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ponerNumero("-");
			}
		});
		btnResta.setIcon(new ImageIcon("Imgs/btn.png"));
		btnResta.setHorizontalTextPosition(SwingConstants.CENTER);
		btnResta.setForeground(new Color(52, 59, 68));
		btnResta.setFont(new Font("SansSerif", Font.PLAIN, 30));
		btnResta.setFocusPainted(false);
		btnResta.setBorderPainted(false);
		btnResta.setBounds(220, 150, 50, 50);
		btnResta.setPressedIcon(new ImageIcon("Imgs/btn_pressed.png"));
		panel_1.add(btnResta);
		
		btnSuma = new JButton("+");
		btnSuma.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ponerNumero("+");
			}
		});
		btnSuma.setIcon(new ImageIcon("Imgs/btn.png"));
		btnSuma.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSuma.setForeground(new Color(52, 59, 68));
		btnSuma.setFont(new Font("SansSerif", Font.PLAIN, 25));
		btnSuma.setFocusPainted(false);
		btnSuma.setBorderPainted(false);
		btnSuma.setBounds(220, 220, 50, 50);
		btnSuma.setPressedIcon(new ImageIcon("Imgs/btn_pressed.png"));
		panel_1.add(btnSuma);
		
		btnIgual = new JButton("=");
		btnIgual.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					String resultado = se.eval(txtOperacion.getText()).toString();
					txtResultado.setText(resultado);
				} 
				catch (ScriptException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		btnIgual.setIcon(new ImageIcon("Imgs/btn_Igual.png"));
		btnIgual.setPressedIcon(new ImageIcon("Imgs/btn_pressed.png"));
		btnIgual.setHorizontalTextPosition(SwingConstants.CENTER);
		btnIgual.setForeground(Color.WHITE);
		btnIgual.setFont(new Font("SansSerif", Font.PLAIN, 25));
		btnIgual.setFocusPainted(false);
		btnIgual.setBorderPainted(false);
		btnIgual.setBounds(220, 290, 50, 50);
		panel_1.add(btnIgual);
		
		btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				limiteEtiqueta("7");
			}
		});
		btn7.setIcon(new ImageIcon("Imgs/btn2.png"));
		btn7.setPressedIcon(new ImageIcon("Imgs/btn_pressed.png"));
		btn7.setHorizontalTextPosition(SwingConstants.CENTER);
		btn7.setForeground(new Color(52, 59, 68));
		btn7.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btn7.setFocusPainted(false);
		btn7.setBorderPainted(false);
		btn7.setBounds(10, 80, 50, 50);
		panel_1.add(btn7);
		
		btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				limiteEtiqueta("8");
			}
		});
		btn8.setIcon(new ImageIcon("Imgs/btn2.png"));
		btn8.setPressedIcon(new ImageIcon("Imgs/btn_pressed.png"));
		btn8.setHorizontalTextPosition(SwingConstants.CENTER);
		btn8.setForeground(new Color(52, 59, 68));
		btn8.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btn8.setFocusPainted(false);
		btn8.setBorderPainted(false);
		btn8.setBounds(80, 80, 50, 50);
		panel_1.add(btn8);
		
		btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				limiteEtiqueta("9");
			}
		});
		btn9.setIcon(new ImageIcon("Imgs/btn2.png"));
		btn9.setPressedIcon(new ImageIcon("Imgs/btn_pressed.png"));
		btn9.setHorizontalTextPosition(SwingConstants.CENTER);
		btn9.setForeground(new Color(52, 59, 68));
		btn9.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btn9.setFocusPainted(false);
		btn9.setBorderPainted(false);
		btn9.setBounds(150, 80, 50, 50);
		panel_1.add(btn9);
		
		btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				limiteEtiqueta("4");
			}
		});
		btn4.setIcon(new ImageIcon("Imgs/btn2.png"));
		btn4.setPressedIcon(new ImageIcon("Imgs/btn_pressed.png"));
		btn4.setHorizontalTextPosition(SwingConstants.CENTER);
		btn4.setForeground(new Color(52, 59, 68));
		btn4.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btn4.setFocusPainted(false);
		btn4.setBorderPainted(false);
		btn4.setBounds(10, 150, 50, 50);
		panel_1.add(btn4);
		
		btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				limiteEtiqueta("5");
			}
		});
		btn5.setIcon(new ImageIcon("Imgs/btn2.png"));
		btn5.setPressedIcon(new ImageIcon("Imgs/btn_pressed.png"));
		btn5.setHorizontalTextPosition(SwingConstants.CENTER);
		btn5.setForeground(new Color(52, 59, 68));
		btn5.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btn5.setFocusPainted(false);
		btn5.setBorderPainted(false);
		btn5.setBounds(80, 150, 50, 50);
		panel_1.add(btn5);
		
		btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				limiteEtiqueta("6");
			}
		});
		btn6.setIcon(new ImageIcon("Imgs/btn2.png"));
		btn6.setPressedIcon(new ImageIcon("Imgs/btn_pressed.png"));
		btn6.setHorizontalTextPosition(SwingConstants.CENTER);
		btn6.setForeground(new Color(52, 59, 68));
		btn6.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btn6.setFocusPainted(false);
		btn6.setBorderPainted(false);
		btn6.setBounds(150, 150, 50, 50);
		panel_1.add(btn6);
		
		btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				limiteEtiqueta("1");
			}
		});
		btn1.setIcon(new ImageIcon("Imgs/btn2.png"));
		btn1.setPressedIcon(new ImageIcon("Imgs/btn_pressed.png"));
		btn1.setHorizontalTextPosition(SwingConstants.CENTER);
		btn1.setForeground(new Color(52, 59, 68));
		btn1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btn1.setFocusPainted(false);
		btn1.setBorderPainted(false);
		btn1.setBounds(10, 220, 50, 50);
		panel_1.add(btn1);
		
		btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				limiteEtiqueta("2");
			}
		});
		btn2.setIcon(new ImageIcon("Imgs/btn2.png"));
		btn2.setPressedIcon(new ImageIcon("Imgs/btn_pressed.png"));
		btn2.setHorizontalTextPosition(SwingConstants.CENTER);
		btn2.setForeground(new Color(52, 59, 68));
		btn2.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btn2.setFocusPainted(false);
		btn2.setBorderPainted(false);
		btn2.setBounds(80, 220, 50, 50);
		panel_1.add(btn2);
		
		btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				limiteEtiqueta("3");
			}
		});
		btn3.setIcon(new ImageIcon("Imgs/btn2.png"));
		btn3.setPressedIcon(new ImageIcon("Imgs/btn_pressed.png"));
		btn3.setHorizontalTextPosition(SwingConstants.CENTER);
		btn3.setForeground(new Color(52, 59, 68));
		btn3.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btn3.setFocusPainted(false);
		btn3.setBorderPainted(false);
		btn3.setBounds(150, 220, 50, 50);
		panel_1.add(btn3);
		
		btn0 = new JButton("0");
		btn0.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				limiteEtiqueta("0");
			}
		});
		btn0.setIcon(new ImageIcon("Imgs/btn2.png"));
		btn0.setPressedIcon(new ImageIcon("Imgs/btn_pressed.png"));
		btn0.setHorizontalTextPosition(SwingConstants.CENTER);
		btn0.setForeground(new Color(52, 59, 68));
		btn0.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btn0.setFocusPainted(false);
		btn0.setBorderPainted(false);
		btn0.setBounds(10, 290, 50, 50);
		panel_1.add(btn0);
		
		btnPunto = new JButton(".");
		btnPunto.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ponerNumero(".");
			}
		});
		btnPunto.setIcon(new ImageIcon("Imgs/btn2.png"));
		btnPunto.setPressedIcon(new ImageIcon("Imgs/btn_pressed.png"));
		btnPunto.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPunto.setForeground(new Color(52, 59, 68));
		btnPunto.setFont(new Font("SansSerif", Font.PLAIN, 30));
		btnPunto.setFocusPainted(false);
		btnPunto.setBorderPainted(false);
		btnPunto.setBounds(80, 290, 50, 50);
		panel_1.add(btnPunto);
	}
}
