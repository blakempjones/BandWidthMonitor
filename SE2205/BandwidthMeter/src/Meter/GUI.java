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
	static JLabel dlRateLabel = new JLabel("Download Rate (Gb/s)");
	static JLabel upRateLabel = new JLabel("Upload Rate (Gb/s)");
	static JTextField dlText = new JTextField();
	static JTextField upText = new JTextField();
	static JTextField dlRateText = new JTextField();
	static JTextField upRateText = new JTextField();
	public static TaskRunner taskObj = new TaskRunner();

	
	public void initialize()
	{
		//TODO
		// Make GUI look nice.
		JPanel labelPanel = new JPanel(new GridLayout(4,1));
		labelPanel.add(dlLabel);
		labelPanel.add(upLabel);
		labelPanel.add(dlRateLabel);
		labelPanel.add(upRateLabel);
		JPanel textPanel = new JPanel(new GridLayout(4,1));
		textPanel.add(dlText);
		textPanel.add(upText);
		textPanel.add(dlRateText);
		textPanel.add(upRateText);
		labelPanel.setBounds(0, 0, 150, 300);
		textPanel.setBounds(150,0,150,300);
		dlText.setEditable(false);
		upText.setEditable(false);
		dlRateText.setEditable(false);
		upRateText.setEditable(false);
		window.setLayout(new GridLayout(1,2));
		window.add(labelPanel);
		window.add(textPanel);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setBounds(200, 200, 300,300 );
		window.setVisible(true);
	}
	
	public void update()
	{
		System.out.println("Download (Gb): " + taskObj.download);
		System.out.println("Upload(Gb): " + taskObj.upload);
		System.out.println("Download Rate(Gb/s): " + taskObj.downloadRate);
		System.out.println("Upload Rate(Gb/s): " + taskObj.uploadRate);
		
		dlText.setText(String.valueOf(taskObj.download));
		upText.setText(String.valueOf(taskObj.upload));
		dlRateText.setText(String.valueOf(taskObj.downloadRate));
		upRateText.setText(String.valueOf(taskObj.uploadRate));
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
