package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.Compile;
import controller.Executor;
import model.CompiledC;
import model.RawC;

public class Home extends JFrame implements ActionListener , WindowListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton bcompile;
	private JButton brun;
	private JTextPane tinput;
	private JTextPane tcode;
	private JTextPane toutput;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	public RawC obj;
	public CompiledC cobj;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Home frame = new Home();
			frame.setVisible(true);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public Home() {
		super("MyCompiler - Bhushan Deshmukh");
		obj=new RawC();
		cobj=new CompiledC();
		addWindowListener(this);
		
		setBounds(100, 100, 999, 701);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 730, 420);
		contentPane.add(scrollPane);
		
		tcode = new JTextPane();
		scrollPane.setViewportView(tcode);
		//scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tcode.setFont(new Font("Consolas", Font.PLAIN, 15));
		
		JLabel lblCode = new JLabel("Code");
		lblCode.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblCode.setBounds(12, 10, 100, 25);
		contentPane.add(lblCode);
		
		JLabel lblOutput = new JLabel("Output");
		lblOutput.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblOutput.setBounds(10, 470, 100, 25);
		contentPane.add(lblOutput);
		
		bcompile = new JButton("Compile");
		bcompile.addActionListener(this);
		bcompile.setFont(new Font("Century Gothic", Font.BOLD, 15));
		bcompile.setBounds(750, 415, 100, 25);
		contentPane.add(bcompile);
		
		brun = new JButton("Run");
		brun.addActionListener(this);
		brun.setFont(new Font("Century Gothic", Font.BOLD, 15));
		brun.setBounds(860, 415, 100, 25);
		contentPane.add(brun);
		
		JLabel lblInput = new JLabel("Input");
		lblInput.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblInput.setBounds(760, 10, 100, 25);
		contentPane.add(lblInput);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 500, 950, 150);
		contentPane.add(scrollPane_1);
		
		toutput = new JTextPane();
		scrollPane_1.setViewportView(toutput);
		toutput.setEditable(false);
		toutput.setFont(new Font("Consolas", Font.PLAIN, 15));
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(750, 40, 210, 265);
		contentPane.add(scrollPane_2);
		
		tinput = new JTextPane();
		scrollPane_2.setViewportView(tinput);
		tinput.setFont(new Font("Consolas", Font.PLAIN, 15));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		toutput.setText("");
		
		JButton b=(JButton)e.getSource();
		if(b==bcompile)
		{
			
			obj.setCode(tcode.getText().trim());
			Compile.compile(obj);
			if(Compile.flag==1)
				toutput.setText(Compile.obj.getOutput());
			else
				toutput.setText(Compile.err);
		}
		else if(b==brun)
		{
			cobj.setInput(tinput.getText());
			try {
				Executor.execute(cobj);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			toutput.setText(Executor.cobj.getOutput());
			
		}
	}
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		File f=new File("Z:\\Test.class");
		f.delete();
		System.exit(0);
		
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
