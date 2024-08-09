import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class StopwatchApp extends JFrame implements ActionListener {
    private JLabel timeLabel;
    private JButton startButton, pauseButton, resetButton;
    private Timer timer;
    private int elapsedTime = 0;
    private boolean isRunning = false;

    private NumberFormat timeFormatter = new DecimalFormat("00");

    // Constructor to set up the UI
    public StopwatchApp() {
        // Frame settings
        setTitle("Stopwatch");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Time label settings
        timeLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 30));

        // Buttons
        startButton = new JButton("Start");
        pauseButton = new JButton("Pause");
        resetButton = new JButton("Reset");

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(startButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(resetButton);

        // Add components to the frame
        add(timeLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Timer to update the stopwatch
        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime++;
                updateDisplay();
            }
        });

        // Add action listeners
        startButton.addActionListener(this);
        pauseButton.addActionListener(this);
        resetButton.addActionListener(this);

        setVisible(true);
    }

    // Method to update the stopwatch display
    private void updateDisplay() {
        int minutes = elapsedTime / 60000;
        int seconds = (elapsedTime / 1000) % 60;
        int milliseconds = elapsedTime % 1000;

        String timeText = timeFormatter.format(minutes) + ":" +
                          timeFormatter.format(seconds) + ":" +
                          timeFormatter.format(milliseconds / 10);
        timeLabel.setText(timeText);
    }

    // Action listener to handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (!isRunning) {
                timer.start();
                isRunning = true;
            }
        } else if (e.getSource() == pauseButton) {
            if (isRunning) {
                timer.stop();
                isRunning = false;
            }
        } else if (e.getSource() == resetButton) {
            timer.stop();
            isRunning = false;
            elapsedTime = 0;
            updateDisplay();
        }
    }

    // Main method to run the stopwatch application
    public static void main(String[] args) {
        new StopwatchApp();
    }
}
