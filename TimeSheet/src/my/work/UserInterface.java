package my.work;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class UserInterface implements ActionListener {
	private JFrame 		mFrame = null;
	private JButton		mStart = null;
	private JButton		mEnd = null;
	private JTextField  mText = null;
	private JPanel		mStartPanel = null;
	private onUIClick	mListener = null;
	
	public UserInterface(onUIClick listener) {
		
		mListener = listener;
//		JFrame.setDefaultLookAndFeelDecorated(true);
		mFrame = new JFrame("My-TS");
		mFrame.setSize(300, 120);
//		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//mFrame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
//		mFrame.pack();
		mStart = new JButton("Start");
		mEnd = new JButton("End");
		mText = new JTextField("");
		mStartPanel = new JPanel(new BorderLayout(5, 5));
		mStartPanel.setBorder( new TitledBorder("BorderLayout(5,5)") );
		mStartPanel.add(mText, BorderLayout.NORTH);
		mStartPanel.add(mStart, BorderLayout.WEST);
		mStartPanel.add(mEnd, BorderLayout.EAST);
		mStart.addActionListener(this);
		mEnd.addActionListener(this);
		mFrame.setContentPane(mStartPanel);
		//mFrame.getContentPane().add(mStart, BorderLayout.WEST);
		//mFrame.getContentPane().add(mEnd, BorderLayout.EAST);
		//mFrame.getContentPane().add(mText, BorderLayout.CENTER);
		mFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("actionPerform: " + e.getSource().toString());
		String task = mText.getText();
		if (e.getSource() == mStart) {
			if (mListener != null)
				mListener.onEvent(onUIClick.EV_START, task);
		}
		else if (e.getSource() == mEnd) {
			if (mListener != null)
				mListener.onEvent(onUIClick.EV_END, task);
		}
		else {
			System.out.println("Unknown source: " + e.toString());
		}
	}
	
	
	
}
