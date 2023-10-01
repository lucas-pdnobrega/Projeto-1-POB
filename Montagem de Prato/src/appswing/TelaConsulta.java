/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */
package appswing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.db4o.ObjectContainer;

import modelo.Acompanhamento;
import modelo.Prato;
import regras_negocio.Fachada;

public class TelaConsulta {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btn_Consulta;
	private JLabel label;
	private JLabel lbl_resultados;

	private ObjectContainer manager;
	private JComboBox consulta_comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsulta tela = new TelaConsulta();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaConsulta() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setModal(true);

		frame.setResizable(false);
		frame.setTitle("Consulta");
		frame.setBounds(100, 100, 729, 385);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Fachada.inicializar();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 43, 674, 148);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lbl_resultados.setText("selecionado="+ (String) table.getValueAt( table.getSelectedRow(), 0));
			}
		});
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.LIGHT_GRAY);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		label = new JLabel("");		//label de mensagem
		label.setForeground(Color.BLUE);
		label.setBounds(21, 321, 688, 14);
		frame.getContentPane().add(label);

		lbl_resultados = new JLabel("resultados:");
		lbl_resultados.setBounds(21, 190, 431, 14);
		frame.getContentPane().add(lbl_resultados);

		btn_Consulta = new JButton("Consultar");
		btn_Consulta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_Consulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = consulta_comboBox.getSelectedIndex();
				if(index<0)
					lbl_resultados.setText("consulta nao selecionada");
				else
					switch(index) {
					case 0:
						double preco = Double.parseDouble(JOptionPane.showInputDialog("digite o preço"));
						List<Acompanhamento> resultado1 = Fachada.acompanhamentosPreco(preco);
						listagemAcompanhamento(resultado1);
						break;
					case 1: 
						String nomeCarne = JOptionPane.showInputDialog("digite a carne");
						List<Prato> resultado2 = Fachada.carnePrato(nomeCarne);
						listagemPrato(resultado2);
						break;
					case 2: 
						String nomeAcompanhamento = JOptionPane.showInputDialog("digite o acompanhamento");
						List<Prato> resultado3 = Fachada.acompanhamentoPrato(nomeAcompanhamento);
						listagemPrato(resultado3);
						break;
					case 3: 
						String n = JOptionPane.showInputDialog("digite N");
						int numero = Integer.parseInt(n);
						List<Prato> resultado4 = Fachada.pratosNAcompanhamentos(numero);
						listagemPrato(resultado4);
						break;

					}

			}
		});
		btn_Consulta.setBounds(606, 10, 89, 23);
		frame.getContentPane().add(btn_Consulta);

		consulta_comboBox = new JComboBox();
		consulta_comboBox.setToolTipText("selecione a consulta");
		consulta_comboBox.setModel(new DefaultComboBoxModel(new String[] {"acompanhamentos com preco X", "pratos com determinada carne", "pratos com determinado acompanhamento", "pratos que possuem mais de N acompanhamentos"}));
		consulta_comboBox.setBounds(21, 10, 513, 22);
		frame.getContentPane().add(consulta_comboBox);
	}

	public void listagemAcompanhamento(List<Acompanhamento> lista) {
		try{
			// o model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();

			//adicionar colunas no model
			model.addColumn("nome");
			model.addColumn("preco");

			//adicionar linhas no model
			for(Acompanhamento a : lista) {
				model.addRow(new Object[]{a.getNome(), a.getPreco()});
			}
			//atualizar model no table (visualizacao)
			table.setModel(model);

			lbl_resultados.setText("resultados: "+lista.size()+ " objetos");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
	
	public void listagemPrato(List<Prato> lista) {
		try{
			// o model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();

			//adicionar colunas no model
			model.addColumn("id");
			model.addColumn("nome");
			model.addColumn("carne");
			model.addColumn("acompanhamentos");

			//adicionar linhas no model
			for(Prato p : lista) {
				List<Acompanhamento> acompanhamentos = p.getAcompanhamentos();
				for (Acompanhamento a : acompanhamentos)
				model.addRow(new Object[]{p.getId(),p.getNome(), p.getCarne().getNome(), a.getNome()});
			}
			//atualizar model no table (visualizacao)
			table.setModel(model);

			lbl_resultados.setText("resultados: "+lista.size()+ " objetos");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}

}
