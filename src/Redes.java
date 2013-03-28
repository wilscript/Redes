import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

class Padres
{
	Vector<String> direcciones;
	String dirPadre;
	String mask;

	Padres(String d)
	{
		this.dirPadre = d;
		this.direcciones = new Vector<String>();
	}

	boolean existeDireccion(String dir)
	{
		for (int i = 0; i < direcciones.size(); i++)
		{
			if (dir.equals(this.direcciones.elementAt(i)))
			{
				return true;
			}
		}

		return false;
	}
}

public class Redes extends JFrame
{
	private JPanel contentPane;
	private JTextField dir;
	private JTextField mas;
	JLabel lblDireccion = new JLabel("Direccion:");
	JScrollPane areatexto = new JScrollPane();
	JTextArea area = new JTextArea();
	Vector<Padres> padres;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Redes frame = new Redes();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Redes()
	{
		this.padres = new Vector<Padres>();
		
		area.setEditable(false);
		setTitle("Redes Computacionales");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		lblDireccion.setBounds(10, 57, 58, 14);
		contentPane.add(lblDireccion);

		dir = new JTextField();
		dir.setBounds(73, 54, 262, 20);
		contentPane.add(dir);
		dir.setColumns(10);

		JLabel lblMascara = new JLabel("Mascara: ");
		lblMascara.setBounds(10, 92, 58, 14);
		contentPane.add(lblMascara);

		mas = new JTextField();
		mas.setBounds(73, 89, 262, 20);
		contentPane.add(mas);
		mas.setColumns(10);

		JButton agregar = new JButton("Agregar");
		agregar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				operaciones();
			}
		});
		agregar.setBounds(142, 133, 89, 23);
		contentPane.add(agregar);

		areatexto.setBounds(28, 206, 325, 138);
		areatexto.setViewportView(area);
		contentPane.add(areatexto);

		JLabel lblAgrupamiento = new JLabel("Agrupamiento:");
		lblAgrupamiento.setBounds(28, 181, 116, 14);
		contentPane.add(lblAgrupamiento);

		JButton borrar = new JButton("Borrar Todo");
		borrar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				area.setText("");
				padres = null;
				padres = new Vector<Padres>();
			}
		});
		borrar.setBounds(142, 354, 116, 23);
		contentPane.add(borrar);
	}

	public void operaciones()
	{
		if (dir.getText().length() > 2 && mas.getText().length() > 0)
		{
			String direccion = dir.getText();
			int mascara = Integer.parseInt(mas.getText()), contador = 0;
			String direccion2[] = direccion.split("\\.");

			int da = Integer.parseInt(direccion2[0]);
			int db = Integer.parseInt(direccion2[1]);
			int dc = Integer.parseInt(direccion2[2]);
			int dd = Integer.parseInt(direccion2[3]);

			int ma, mb, mc, md;
			ma = mb = mc = md = 0;

//			if (mascara % 8 == 0)
//			{
//				if (mascara == 24)
//				{
//					ma = da;
//					mb = db;
//					mc = dc;
//					md = 0;
//				}
//				if (mascara == 16)
//				{
//					ma = da;
//					mb = db;
//					mc = 0;
//					md = 0;
//				}
//				if (mascara == 8)
//				{
//					ma = da;
//					mb = 0;
//					mc = 0;
//					md = 0;
//				}
//			}
//			else
//			{
				
				if (mascara >= 24)
				{
					ma = da;
					mb = db;
					mc = dc;
					md = dd & md;
				} 	
				if (mascara >= 16 && mascara < 24)
				{
					ma = da;
					mb = db;
					mc = dc & mc;
					md = dd & md;
				}
				if (mascara >= 8 && mascara < 16)
				{
					ma = da;
					mb = db & mb;
					mc = dc & mc;
					md = dd & md;
				}
				if (mascara > 0 && mascara < 8)
				{
					ma = da & ma;
					mb = db & mb;
					mc = dc & mc;
					md = dd & md;
				}
			

			String padre = Integer.toString(ma) + "." + Integer.toString(mb)
					+ "." + Integer.toString(mc) + "." + Integer.toString(md)
					+ " /" + mascara;

			if(!padres.isEmpty())
			{
				boolean existe = false;
				
				for (int i = 0; i < padres.size(); i++)
				{
					if(padre.equals(this.padres.elementAt(i).dirPadre))
					{
						existe = true;
						break;
					}					
				}
				
				if(!existe)
					this.padres.addElement(new Padres(padre));
			}
			else
			{
				this.padres.addElement(new Padres(padre));
			}
			
			for (int i = 0; i < padres.size(); i++)
			{
				if (padre.equals(this.padres.elementAt(i).dirPadre))
				{
					if (!this.padres.elementAt(i).existeDireccion(
							"\t+" + direccion + "\n"))
					{
						this.padres.elementAt(i).direcciones.addElement("\t+"
								+ direccion + "\n");
					}
				}
			}

			String almacen = "";

			for (int i = 0; i < padres.size(); i++)
			{
				almacen += padres.elementAt(i).dirPadre + "\n";
				for (int j = 0; j < padres.elementAt(i).direcciones.size(); j++)
				{
					almacen += padres.elementAt(i).direcciones.elementAt(j);
				}
			}

			this.area.setText(almacen);
		}

	}
}
