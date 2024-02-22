import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class BMICalculatorPanel extends JPanel {
    private double height = 150.0;
    private double weight = 50.0;
    private double bmi = 0.0;

    public BMICalculatorPanel(HomePage homePage) {
        setBackground(new Color(32, 32, 32));
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Calculate your BMI");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);

        JPanel calculatorPanel = new JPanel();
        calculatorPanel.setLayout(new BorderLayout());
        calculatorPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        calculatorPanel.setBackground(new Color(32, 32, 32)); 

        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new GridLayout(0, 2, 10, 10));
        sliderPanel.setBackground(new Color(32, 32, 32));

        JLabel heightLabel = new JLabel("Height (in cm)");
        heightLabel.setFont(new Font("Arial", Font.BOLD, 20));
        heightLabel.setForeground(Color.WHITE);
        JLabel heightValueLabel = new JLabel(String.valueOf((int) height));
        heightValueLabel.setForeground(Color.WHITE);
        JSlider heightSlider = new JSlider(100, 220, (int) height);
        heightSlider.addChangeListener(e -> {
            height = heightSlider.getValue();
            heightValueLabel.setText(String.valueOf((int) height));
        });

        JLabel weightLabel = new JLabel("Weight (kg)");
        weightLabel.setFont(new Font("Arial", Font.BOLD, 20));
        weightLabel.setForeground(Color.WHITE);
        JLabel weightValueLabel = new JLabel(String.valueOf((int) weight));
        weightValueLabel.setForeground(Color.WHITE);
        JSlider weightSlider = new JSlider(30, 150, (int) weight);
        weightSlider.addChangeListener(e -> {
            weight = weightSlider.getValue();
            weightValueLabel.setText(String.valueOf((int) weight));
        });

        sliderPanel.add(heightLabel);
        sliderPanel.add(heightValueLabel);
        sliderPanel.add(heightSlider);
        sliderPanel.add(new JLabel());
        sliderPanel.add(weightLabel);
        sliderPanel.add(weightValueLabel);
        sliderPanel.add(weightSlider);
        sliderPanel.add(new JLabel());

        calculatorPanel.add(titleLabel, BorderLayout.NORTH);
        calculatorPanel.add(sliderPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(32, 32, 32)); // Set background color to match the home screen theme

        JLabel resultLabel = new JLabel();

        JButton calculateButton = new JButton("Calculate BMI");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 20)); // Set font to match the home screen theme
        calculateButton.setBackground(Color.GREEN);
        calculateButton.setForeground(Color.WHITE); // Set font color to match the home screen theme
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateBMI();
                updateResultLabel(resultLabel);
                JOptionPane.showMessageDialog(null, "Your BMI: " + String.format("%.2f", bmi) + "\n" + getBMIDescription(), "BMI Result", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        buttonPanel.add(calculateButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 20)); // Set font to match the home screen theme
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE); // Set font color to match the home screen theme
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomePage homePage = (HomePage) SwingUtilities.getWindowAncestor(BMICalculatorPanel.this);
                homePage.switchToHomePage(); // Corrected method call
            }
        });

        buttonPanel.add(exitButton);

        calculatorPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(calculatorPanel, BorderLayout.CENTER);
    }

    // Method to calculate BMI
    private void calculateBMI() {
        if (height != 0)
            bmi = weight / ((height / 100) * (height / 100));
    }

    // Method to update result label
    private void updateResultLabel(JLabel resultLabel) {
        resultLabel.setText("Your BMI: " + String.format("%.2f", bmi) + " - " + getBMIDescription());
        resultLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Set font to match the home screen theme
        resultLabel.setForeground(Color.WHITE); // Set font color to match the home screen theme
    }

    // Method to get BMI description
    private String getBMIDescription() {
        if (bmi < 18.5) {
            return "Underweight.... Malnourished!?? Eat something";
        } else if (bmi < 24.9) {
            return "Normal weight... Yall good";
        } else if (bmi < 29.9) {
            return "Overweight... Start Exercising";
        } else {
            return "Obese.... Consult a dietician";
        }
    }
}