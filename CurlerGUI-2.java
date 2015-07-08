import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.plaf.OptionPaneUI;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;


public class CurlerGUI extends JFrame implements ActionListener
{
	private JTextField country = new JTextField(20);
	private JTextField first = new JTextField(20);
	private JTextField last = new JTextField(20);
	private JRadioButton thrower = new JRadioButton("Thrower");
	private JRadioButton sweeper = new JRadioButton("Sweeper");
	private JRadioButton skipper = new JRadioButton("Skip");
	
	private JButton addPlayer = new JButton("Add Player");
	private JButton printTeam = new JButton("Print Team");
	private JButton saveTeam = new JButton("Save Team");
	private JButton newTeam = new JButton("New Team");
	private JButton exit = new JButton("Exit");
	
	private CurlerTeam team = null;
	
	public CurlerGUI()
	{
		super("Create Curler Team");
		
		JPanel top = new JPanel();
		ImageIcon img = new ImageIcon("PyeongchangSmall.jpg");
		top.add(new JLabel(img));
		top.add(new JLabel("Curling Teams"));
		add(top, BorderLayout.NORTH);
		
		JPanel middle = new JPanel(new GridLayout(4, 1));
		
		JPanel countryHolder = new JPanel();
		countryHolder.add(new JLabel("Country: "));
		countryHolder.add(country);
		country.setEnabled(false);
		
		
		JPanel firstHolder = new JPanel();
		firstHolder.add(new JLabel("First Name: "));
		firstHolder.add(first);
		
		JPanel lastHolder = new JPanel();
		lastHolder.add(new JLabel("Last Name: "));
		lastHolder.add(last);
		
		JPanel rdoHolder = new JPanel();
		rdoHolder.setBorder(BorderFactory.createTitledBorder("Position"));
		rdoHolder.add(thrower);
		rdoHolder.add(sweeper);
		rdoHolder.add(skipper);
		ButtonGroup grp = new ButtonGroup();
		grp.add(thrower);
		grp.add(sweeper);
		grp.add(skipper);
		thrower.setSelected(true);
		
		middle.add(countryHolder);
		middle.add(firstHolder);
		middle.add(lastHolder);
		middle.add(rdoHolder);
		
		add(middle);
		
		JPanel bot = new JPanel();
		bot.add(addPlayer);
		bot.add(printTeam);
		bot.add(saveTeam);
		bot.add(newTeam);
		bot.add(exit);
		
		add(bot, BorderLayout.SOUTH);
		
		pack();
		
		addPlayer.addActionListener(this);
		printTeam.addActionListener(this);
		newTeam.addActionListener(this);
		exit.addActionListener(this);
		saveTeam.addActionListener(this);
	}
    public void actionPerformed(ActionEvent e)
    {	    
    	if(e.getSource() == addPlayer)
    	{
    		String pos = null;
    		if(thrower.isSelected())
    			pos = "thrower";
    		else if(skipper.isSelected())
    			pos = "skiper";
    		else if(sweeper.isSelected())
    			pos = "sweeper";
    		String result = team.addPlayer(first.getText(), last.getText(), pos);
    		if(result != null)
    		{
    			JOptionPane.showMessageDialog(this, result);
    		}
    	}
    	else if(e.getSource() == printTeam)
    	{
    		JOptionPane.showMessageDialog(this, team.printTeam());
    	}
    	else if(e.getSource() == newTeam)
    	{
    		String name = JOptionPane.showInputDialog(this, "What country does this team play for?");
    		if(name != null)
    		{
    			team = new CurlerTeam(name);
    			country.setText(name);
    			first.setText("");
    			last.setText("");
    		}
    	}
    	else if(e.getSource() == saveTeam)
    	{
    		JFileChooser ch = new JFileChooser();
    		int res = ch.showSaveDialog(this);
    		if(res == JFileChooser.APPROVE_OPTION)
    		{
    			try
    			{
    				PrintStream out = new PrintStream(ch.getSelectedFile());
    				out.println(team.printTeam());
    				out.close();
    			}
    			catch(Exception ex)
    			{
    				ex.printStackTrace();
    			}
    		}
    	}
    	else if(e.getSource() == exit)
    	{
    		System.exit(0);
    	}
    }	
	public static void main(String[] args)
	{
		CurlerGUI g = new CurlerGUI();
		g.setDefaultCloseOperation(EXIT_ON_CLOSE);
		g.setVisible(true);
	}
}
