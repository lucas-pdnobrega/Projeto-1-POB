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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Acompanhamento;
import modelo.Prato;
import regras_negocio.Fachada;

public class TelaPrato {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textField_prato;
	private JTextField textField_carne;
	private JButton btnListar;
	private JButton btnCriarNovoPrato;
	private JButton btnApagar;
	private JLabel label;
	private JLabel lblNomeDoPrato;
	private JLabel lblCarne;
	private JLabel lbl_results;
	private JTextField textField_acompanhamento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrato tela = new TelaPrato();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrato() {
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
		frame.setTitle("Prato");
		frame.setBounds(100, 100, 729, 419);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Fachada.inicializar();
				listagem();
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
				lbl_results.setText("selecionado="+ (int) table.getValueAt( table.getSelectedRow(), 0));
			}
		});
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(new Color(144, 238, 144));
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
		label.setBounds(12, 355, 688, 14);
		frame.getContentPane().add(label);

		lbl_results = new JLabel("resultados:");
		lbl_results.setBounds(21, 190, 431, 14);
		frame.getContentPane().add(lbl_results);

		btnCriarNovoPrato = new JButton("Criar novo prato");
		btnCriarNovoPrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField_prato.getText().isEmpty() || textField_carne.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					String nome = textField_prato.getText();
					String carne = textField_carne.getText();

					Fachada.cadastrarPrato(nome, carne);
					label.setText("prato criado");
					listagem();
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		btnCriarNovoPrato.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCriarNovoPrato.setBounds(47, 321, 171, 23);
		frame.getContentPane().add(btnCriarNovoPrato);

		btnListar = new JButton("Listar");
		btnListar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		btnListar.setBounds(308, 11, 89, 23);
		frame.getContentPane().add(btnListar);

		btnApagar = new JButton("Apagar selecionado");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0) {	
						String nomePrato = (String) table.getValueAt( table.getSelectedRow(), 1);

						Fachada.excluirPrato(nomePrato);
						label.setText("Prato apagado" );
						listagem();

					}
					else
						label.setText("Nao selecionado");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnApagar.setBounds(267, 215, 171, 23);
		frame.getContentPane().add(btnApagar);


		textField_prato = new JTextField();
		textField_prato.setBounds(109, 249, 130, 19);
		frame.getContentPane().add(textField_prato);
		textField_prato.setColumns(10);

		JTextPane textPane_error = new JTextPane();
		textPane_error.setBounds(47, 308, 1, 16);
		frame.getContentPane().add(textPane_error);

		lblNomeDoPrato = new JLabel("Nome do prato");
		lblNomeDoPrato.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNomeDoPrato.setBounds(10, 248, 89, 16);
		frame.getContentPane().add(lblNomeDoPrato);

		lblCarne = new JLabel("Carne");
		lblCarne.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCarne.setBounds(12, 291, 116, 16);
		frame.getContentPane().add(lblCarne);

		textField_carne = new JTextField();
		textField_carne.setBounds(109, 290, 130, 20);
		frame.getContentPane().add(textField_carne);
		textField_carne.setColumns(10);
		
		JLabel lblNomeDoAcompanhamento = new JLabel("Acompanhamento");
		lblNomeDoAcompanhamento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNomeDoAcompanhamento.setBounds(356, 250, 127, 16);
		frame.getContentPane().add(lblNomeDoAcompanhamento);
		
		textField_acompanhamento = new JTextField();
		textField_acompanhamento.setColumns(10);
		textField_acompanhamento.setBounds(493, 250, 130, 19);
		frame.getContentPane().add(textField_acompanhamento);
		
		JButton btnAddAcompanhamento = new JButton("Adicionar Acompanhamento");
		btnAddAcompanhamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField_acompanhamento.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					String nomeAcompanhamento = textField_acompanhamento.getText();
					

					System.out.println(nomeAcompanhamento);
					System.out.println(table.getValueAt( table.getSelectedRow(), 0));
					String nomePrato = (String) table.getValueAt( table.getSelectedRow(), 1);
					
					System.out.println(nomePrato);

					Fachada.adicionarAcompanhamentoPrato(nomeAcompanhamento, nomePrato);
					label.setText("acompanhamento adicionado");
					listagem();
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		btnAddAcompanhamento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAddAcompanhamento.setBounds(467, 321, 185, 23);
		frame.getContentPane().add(btnAddAcompanhamento);
	}

	public void listagem() {
		try{
			//ler os carros do banco
			List<Prato> lista = Fachada.listarPratos();

			// o model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();

			//adicionar colunas no model
			model.addColumn("id");
			model.addColumn("nome");
			model.addColumn("carne");
			model.addColumn("acompanhamentos");

			//adicionar linhas no model
			for(Prato prato : lista) {
				List<Acompanhamento> acompanhamentos = prato.getAcompanhamentos();
				if (acompanhamentos.size() > 0) {
					for (Acompanhamento a : acompanhamentos)
						model.addRow(new Object[]{prato.getId(), prato.getNome(), prato.getCarne().getNome(), a.getNome()});
				} else {
					model.addRow(new Object[]{prato.getId(), prato.getNome(), prato.getCarne().getNome()});
				}
				
				
			}


			//atualizar model no table (visualizacao)
			table.setModel(model);

			lbl_results.setText("resultados: " + lista.size() + " objetos");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
}
