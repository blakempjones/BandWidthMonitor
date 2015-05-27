

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.DocFlavor.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI {

	//TODO
	// Make GUI look nice.
	static JFrame window = new JFrame();
	
	// Create JLabels.
	private JLabel dlUsageLabel = new JLabel("Download Usage (GB): ");
	private JLabel ulUsageLabel = new JLabel("Upload Usage (GB): ");
	private JLabel dlRateLabel = new JLabel("Download Rate (KB/s): ");
	private JLabel ulRateLabel = new JLabel("Upload Rate (KB/s): ");
	
	// Create JTextFields for displaying usage and rate.
	private JLabel dlUsage = new JLabel();
	private JLabel ulUsage = new JLabel();
	private JLabel dlRate = new JLabel();
	private JLabel ulRate = new JLabel();
	
	// Create TaskRunner to retrieve usage and rate data.
	public static TaskRunner taskObj = new TaskRunner();

	
	public void initialize()
	{
		//TODO
		// Make GUI look nice.
		
		// Set the label's font size to the newly determined size.
		dlUsageLabel.setFont(new Font("Sans-Serif", Font.BOLD, 14));
		ulUsageLabel.setFont(new Font("Sans-Serif", Font.BOLD, 14));
		dlRateLabel.setFont(new Font("Sans-Serif", Font.BOLD, 14));
		ulRateLabel.setFont(new Font("Sans-Serif", Font.BOLD, 14));
		
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
		labelPanel.setBounds(0,0,200,300);
		textPanel.setBounds(200,0,200,300);
		
		// Add borders to textPanel elements.
		dlUsage.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.RED));
		ulUsage.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.BLUE));
		dlRate.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.BLACK));
		ulRate.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.GREEN));
		
		// Add panels to GUI.
		window.setLayout(new GridLayout(1,2));
		window.add(labelPanel);
		window.add(textPanel);
		
		// Make labelPanel background gray.
		labelPanel.setBackground(Color.gray);
		
		// Show window and set close operations.
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setBounds(200,200,400,300);
		window.setTitle("Bandwidth Meter");
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
		dlRate.setText(String.valueOf(taskObj.downloadRate*1000000));
		ulRate.setText(String.valueOf(taskObj.uploadRate*1000000));
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
