import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuadraticSolverPanel extends JPanel {
    private JTextField coefficientAField;
    private JTextField coefficientBField;
    private JTextField coefficientCField;
    private JTextArea resultTextArea;
    String s = "Quadratic Equation: Ax^2 + Bx + C = 0";
    JLabel equationLabel = new JLabel(s);
    public QuadraticSolverPanel(HomePage homePage) {
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        
        // Equation Display
        equationLabel.setForeground(Color.WHITE);
        equationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        equationLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Input Fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 1, 5, 5));
        inputPanel.setBackground(Color.BLACK);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(5, 5 , 5, 5));
        Font inputFont = new Font("Arial", Font.BOLD, 15);

        JLabel labelA = new JLabel("Coefficient A:");
        labelA.setForeground(Color.WHITE);
        labelA.setFont(new Font("Arial", Font.BOLD, 20));
        coefficientAField = new JTextField();
        coefficientAField.setFont(inputFont);
        coefficientAField.setBackground(Color.gray);
        inputPanel.add(labelA);
        inputPanel.add(coefficientAField);

        JLabel labelB = new JLabel("Coefficient B:");
        labelB.setForeground(Color.WHITE);
        labelB.setFont(new Font("Arial", Font.BOLD, 20));
        coefficientBField = new JTextField();
        coefficientBField.setFont(inputFont);
        coefficientBField.setBackground(Color.gray);
        inputPanel.add(labelB);
        inputPanel.add(coefficientBField);

        JLabel labelC = new JLabel("Coefficient C:");
        labelC.setForeground(Color.WHITE);
        labelC.setFont(new Font("Arial", Font.BOLD, 20));
        coefficientCField = new JTextField();
        coefficientCField.setFont(inputFont);
        coefficientCField.setBackground(Color.gray);
        inputPanel.add(labelC);
        inputPanel.add(coefficientCField);

        // Button to Calculate
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 15));
        calculateButton.setBackground(Color.GREEN);
        calculateButton.setForeground(Color.WHITE);
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateQuadraticEquation();
            }
        });

        // Exit Button
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 15));
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homePage.switchToHomePage();
            }
        });

        // Result TextArea
        resultTextArea = new JTextArea(2, 20);
        resultTextArea.setEditable(false);
        resultTextArea.setBackground(Color.BLACK);
        resultTextArea.setForeground(Color.WHITE);
        resultTextArea.setFont(inputFont);

        // Add components to the panel
        add(equationLabel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(calculateButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        JPanel Result = new JPanel();
        Result.setLayout(new GridLayout(2,1,3,3));
        Result.setBackground(Color.BLACK);
        Result.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Result.add(equationLabel);
        Result.add(resultTextArea);
        add(new JScrollPane(Result), BorderLayout.NORTH);
    }

    private void calculateQuadraticEquation() {
        try {
            double a = Double.parseDouble(coefficientAField.getText());
            double b = Double.parseDouble(coefficientBField.getText());
            double c = Double.parseDouble(coefficientCField.getText());

            String quadraticEquation = String.format("Quadratic Equation: %dx^2 + %dx + %d = 0", (int)a, (int)b, (int)c);
            equationLabel.setText(quadraticEquation);
            double discriminant = b * b - 4 * a * c;
            if (discriminant > 0) {
                double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                displayResult("Roots: " + root1 + ", " + root2);
            } else if (discriminant == 0) {
                double root = -b / (2 * a);
                displayResult("Single Root: " + root);
            } else {
                displayResult("Complex Roots");
            }
        } catch (NumberFormatException | ArithmeticException ex) {
            displayResult("Invalid Input");
        }
    }

    private void displayResult(String result) {
        resultTextArea.setText(result);
    }
}
