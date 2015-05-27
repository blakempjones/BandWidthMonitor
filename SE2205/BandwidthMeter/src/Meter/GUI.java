package Meter;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.BorderFactory;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI {

	//TODO
	// Make GUI look nice.
	static JFrame window = new JFrame();
	
	// Create JLabels.
	static JLabel dlUsageLabel = new JLabel("Download Usage (GB): ");
	static JLabel ulUsageLabel = new JLabel("Upload Usage (GB): ");
	static JLabel dlRateLabel = new JLabel("Download Rate (GB/s)");
	static JLabel ulRateLabel = new JLabel("Upload Rate (GB/s)");
	
	// Create JTextFields for displaying usage and rate.
	static JLabel dlUsage = new JLabel();
	static JLabel ulUsage = new JLabel();
	static JLabel dlRate = new JLabel();
	static JLabel ulRate = new JLabel();
	
	// Create TaskRunner to retrieve usage and rate data.
	public static TaskRunner taskObj = new TaskRunner();

	
	public void initialize()
	{
		//TODO
		// Make GUI look nice.
		
		// Create label panel and add labels.
		JPanel labelPanel = new JPanel(new GridLayout(4,1));
		labelPanel.add(dlUsageLabel);
		labelPanel.add(ulUsageLabel);
		labelPanel.add(dlRateLabel);
		labelPanel.add(ulRateLabel);
		
		// Create text panel and add usage and rate data.
		JPanel textPanel = new JPanel(new GridLayout(4,1));
		textPanel.add(dlUsage);
		textPanel.add(ulUsage);
		textPanel.add(dlRate);
		textPanel.add(ulRate);
		
		// Position label panel and text panel within GUI.
		labelPanel.setBounds(0,0,150,300);
		textPanel.setBounds(150,0,150,300);
		
		//dlText.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.RED));
		
		// Add panels to GUI.
		window.setLayout(new GridLayout(1,2));
		window.add(labelPanel);
		window.add(textPanel);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setBounds(200,200,400,300);
		window.setVisible(true);
	}
	
	public void update()
	{
		// Check system output.
		/*
		System.out.println("Download (GB): " + taskObj.download);
		System.out.println("Upload(GB): " + taskObj.upload);
		System.out.println("Download Rate(GB/s): " + taskObj.downloadRate);
		System.out.println("Upload Rate(GB/s): " + taskObj.uploadRate);*/
		
		// Update usage and rate data every time update() is called.
		dlUsage.setText(String.valueOf(taskObj.download));
		ulUsage.setText(String.valueOf(taskObj.upload));
		dlRate.setText(String.valueOf(taskObj.downloadRate));
		ulRate.setText(String.valueOf(taskObj.uploadRate));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final GUI GUIObj = new GUI();
		GUIObj.initialize();
		
		// Event handler for the timer.
		ActionListener timerTask = new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				taskObj.executeNetStat();
				GUIObj.update();
			}
		};
		// Create timer and call update() every time TaskRunner refreshes.
		Timer refreshTimer = new Timer(taskObj.refreshRate, timerTask);
		refreshTimer.start();	
	}

}
