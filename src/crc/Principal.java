package crc;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

class Ventana extends JFrame
{
	private JTextField inputOriginal, inputStandard, inputCoded, inputRes;
	private JLabel labelOriginal, labelStandard, labelCoded, labelRes;
	private JButton encode, decode;
	private ActionListener evt;
	
	private String dividendo, divisor, coded;
	
	private void handleEvt(ActionEvent e)
	{
		Object src = e.getSource();
		
		if(src.equals(encode))
		{			
			this.divisor = this.inputStandard.getText();//"1101";//"101101";//"101101";//"1101";//"10011";
			
			this.dividendo = this.inputOriginal.getText();
			//System.out.println("Original 1: "+dividendo);
			this.dividendo = this.dividendo+CRC.getEmptySequence(this.divisor);
			//System.out.println("Original 2: "+dividendo);
			
			//"11110010100000";//"110010000";//"100100000";//"11010110110000";
			
			System.out.println("Residuo: " + CRC.getCRC(this.dividendo, divisor));
			this.inputCoded.setText(this.inputOriginal.getText()+CRC.getCRC(this.dividendo, divisor));
			this.inputRes.setText(CRC.getCRC(this.dividendo, this.divisor));
		}
		else if(src.equals(decode))
		{
			this.divisor = this.inputStandard.getText();
			this.dividendo = this.inputCoded.getText();
			this.inputRes.setText(CRC.getCRC(this.dividendo, this.divisor));
		}
	}
	
	private void initComponents()
	{
		this.inputOriginal = new JTextField();
		this.inputStandard = new JTextField();
		this.inputCoded = new JTextField();
		this.inputRes = new JTextField();
		
		this.labelOriginal = new JLabel("Secuencia original:");
		this.getContentPane().add(this.labelOriginal);
		this.getContentPane().add(this.inputOriginal);
		
		this.labelStandard = new JLabel("Generador CRC:");
		this.getContentPane().add(this.labelStandard);
		this.getContentPane().add(this.inputStandard);
		
		this.labelCoded = new JLabel("Secuencia codificada:");
		this.getContentPane().add(this.labelCoded);
		this.getContentPane().add(this.inputCoded);
		
		this.labelRes = new JLabel("Residuo:");
		this.getContentPane().add(this.labelRes);
		this.getContentPane().add(this.inputRes);
		
		this.evt = new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent e)
			{
				handleEvt(e);
			}
		};
		
		this.encode = new JButton("Generar");
		this.getContentPane().add(this.encode);
		
		this.decode = new JButton("Revisar");
		this.getContentPane().add(this.decode);
		
		this.encode.addActionListener(this.evt);
		this.decode.addActionListener(this.evt);
	}
	
	Ventana()
	{
		this.setSize(400, 240);
		this.setLocationRelativeTo(null);
		this.setTitle("CRC");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(5,2));
		this.initComponents();
		this.setVisible(true);
	}
}

public class Principal
{	
	public static void main(String[] args) throws Exception
	{		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		new Ventana();
	}
}