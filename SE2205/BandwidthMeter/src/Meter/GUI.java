package Meter;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI {

	//TODO
	// Make GUI look nice.
	static JFrame window = new JFrame();
	static JLabel dlLabel = new JLabel("Download Usage (Gb): ");
	static JLabel upLabel = new JLabel("Upload Usage (Gb): ");
	static JTextField dlText = new JTextField();
	static JTextField upText = new JTextField();
	public static TaskRunner taskObj = new TaskRunner();

	
	public void initialize()
	{
		//TODO
		// Make GUI look nice.
		JPanel panel1 = new JPanel(new GridLayout(2,1));
		panel1.add(dlLabel);
		panel1.add(upLabel);
		JPanel textPanel = new JPanel(new GridLayout(2,1));
		textPanel.add(dlText);
		textPanel.add(upText);
		window.add(panel1);
		window.add(textPanel);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setBounds(200, 200, 300,300 );
		window.setVisible(true);
	}
	
	public void update()
	{
		// TODO
		// Update the GUI
		System.out.println("Download (Gb): " + taskObj.download);
		System.out.println("Upload(Gb): " + taskObj.upload);
		System.out.println("Download Rate(Gb/s): " + taskObj.downloadRate);
		System.out.println("Upload Rate(Gb/s): " + taskObj.uploadRate);

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final GUI GUIObj = new GUI();
		GUIObj.initialize();
		
		// Handler for the timer.
		ActionListener timerTask = new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				taskObj.executeNetStat();
				GUIObj.update();
			}
		};
		// Timer
		Timer refreshTimer = new Timer(taskObj.refreshRate,timerTask);
		refreshTimer.start();	
	}

}
