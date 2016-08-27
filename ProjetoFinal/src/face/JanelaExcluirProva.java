package face;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.ControleException;
import controle.CtrlExcluirProva;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaExcluirProva extends JFrame {

	private JPanel contentPane;
	private CtrlExcluirProva ctrl;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public JanelaExcluirProva(CtrlExcluirProva c) {
		this.ctrl = c;
		setTitle("Excluir Prova");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSelePr = new JLabel("Selecione a Prova:");
		lblSelePr.setBounds(10, 32, 122, 14);
		contentPane.add(lblSelePr);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(146, 29, 183, 20);
		contentPane.add(comboBox);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnExcluir.setBounds(10, 82, 89, 23);
		contentPane.add(btnExcluir);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				executarCancelar();
			}
		});
		btnCancelar.setBounds(10, 160, 89, 23);
		contentPane.add(btnCancelar);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(348, 48, 255, 244);
		contentPane.add(textArea);
		this.setVisible(true);
	}

	public void executarCancelar() {
		try {
			ctrl.terminar();
		} catch (ControleException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
}