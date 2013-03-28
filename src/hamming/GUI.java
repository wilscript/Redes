package hamming;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class GUI extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel fondo;
	private JTextField binario;
	private JLabel lhamming;
	private JTextField hamming;
	private String dato ;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					GUI frame = new GUI();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	public GUI()
	{
		setTitle("Codigo Hamming");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		setBounds(100, 100, 656, 281);
		fondo = new JPanel();
		fondo.setBorder(new EmptyBorder(5, 5, 5, 5));
		fondo.setLayout(null);
		setContentPane(fondo);
		
		binario = new JTextField();
		binario.setHorizontalAlignment(SwingConstants.CENTER);
		binario.setForeground(Color.BLACK);
		binario.setBounds(270, 58, 171, 30);
		fondo.add(binario);
		binario.setColumns(10);
		
		JLabel ldato = new JLabel("Dato:");
		ldato.setFont(new Font("Tahoma", Font.BOLD, 14));
		ldato.setBounds(213, 53, 50, 39);
		fondo.add(ldato);
		
		lhamming = new JLabel("Codigo Hamming:");
		lhamming.setFont(new Font("Tahoma", Font.BOLD, 14));
		lhamming.setBounds(107, 134, 131, 39);
		fondo.add(lhamming);
		
		hamming = new JTextField();
		hamming.setHorizontalAlignment(SwingConstants.CENTER);
		hamming.setForeground(Color.BLACK);
		hamming.setColumns(10);
		hamming.setBounds(245, 139, 218, 30);
		fondo.add(hamming);
		
		JButton down = new JButton("Abajo");
		down.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				dato = binario.getText();
				try
				{
					hamming.setText(Hamming.getCodedSequence(dato));
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"La cadena esta vacia o no cumple el formato.");
				}
			}
		});
		down.setBounds(245, 105, 89, 23);
		fondo.add(down);
		
		JButton Up = new JButton("Arriba");
		Up.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				dato = hamming.getText();
				try
				{
					binario.setText(hamming(dato));
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"La cadena esta vacia o no cumple el formato.");
				}
			}
		});
		Up.setBounds(374, 105, 89, 23);
		fondo.add(Up);
	}

	public static String hamming (String nuevo)
	{
		String original = nuevo;
		String transformed = "";
		Vector<Integer> posiciones = new Vector<Integer>();
		int contador1 = 0, contadorunos = 0, a;
		String conver = "", resultado;
		boolean sera;
		int n = 1;
		posiciones.addElement(n);
		for (int i = 0; i < original.length(); i += 2)
		{
			if (n < original.length())
			{
				n *= 2;
				posiciones.addElement(n);
			}
		}
		transformed = original;
		for (int counter = 0; counter < posiciones.size(); counter++)
		{
			sera = true;
			for (int i = posiciones.elementAt(counter) - 1; i < transformed.length(); i++)
			{
				if (contador1 == posiciones.elementAt(counter) && sera == true)
				{
					sera = false;
					contador1 = 0;
				}
				if (contador1 == posiciones.elementAt(counter) && sera == false)
				{
					sera = true;
					contador1 = 0;
				}
				if (sera && contador1 <= posiciones.elementAt(counter))
				{
					if (transformed.charAt(i) == '1')
					{
						contadorunos++;
					}
				}
				contador1++;
			}
			contador1 = 0;
			conver += Integer.toString(contadorunos % 2);
			contadorunos = 0;
		}
		conver = conver.substring(0, conver.length() - 1);
		a = Integer.parseInt(conver);
		if (a != 0)
		{
			a = 0;
			for (int counter = 0; counter < conver.length(); counter++)
			{
				if (conver.charAt(counter) == '1')
				{
					a += (int) Math.pow(2, counter);
				}
			}
			resultado = "";
			for (int counter = 0; counter < transformed.length(); counter++)
			{
				if (transformed.charAt(a - 1) == '1' && counter == (a - 1))
				{
					resultado += "0";
				}
				else if (transformed.charAt(a - 1) == '0' && counter == (a - 1))
				{
					resultado += "1";
				}
				else
				{
					resultado += transformed.charAt(counter);
				}
			}
			JOptionPane.showMessageDialog(null, "Error en la posición: "+a);
			System.out.println("Error en la posición: "+a);
			transformed = "";
			contador1=0;
			for(int counter =0; counter< resultado.length(); counter++)
			{
				if(counter != posiciones.elementAt(contador1)-1)
				{
					transformed+=resultado.charAt(counter);
				}
				else
				{
					contador1++;
				}
			}
			System.out.println("Error Corregido.");
			return transformed;
		}
		else
		{
			resultado = "";
			for(int counter =0; counter< transformed.length(); counter++)
			{
				if(counter != posiciones.elementAt(contador1)-1)
				{
					resultado+=transformed.charAt(counter);
				}
				else
				{
					contador1++;
				}
			}
			return resultado;
		}
	}
}

