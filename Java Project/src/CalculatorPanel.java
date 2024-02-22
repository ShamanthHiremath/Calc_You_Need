import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
public class CalculatorPanel extends JPanel {
    private String output = "";
    private String _output = "";
    @SuppressWarnings("unused")
    private double num1 = 0;
    @SuppressWarnings("unused")
    private double num2 = 0;
    @SuppressWarnings("unused")
    private String operand = "";
    private boolean opused = false;
    private boolean decused = false;
    private List<String> historyList = new ArrayList<>();

    public CalculatorPanel(HomePage homePage) {
        setBackground(Color.BLUE);
        setLayout(new BorderLayout());

        JTextField displayField = new JTextField();
        displayField.setFont(new Font("Arial", Font.BOLD, 50));
        displayField.setEditable(false);
        displayField.setBackground(Color.BLACK);
        displayField.setForeground(Color.WHITE);
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4, 5, 5));
        buttonPanel.setBackground(Color.BLACK);

        String[] buttons = {
                "C", "Del", "", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                ".", "0", "00", "="
        };

        for (String buttonText : buttons) {
            JButton button = new JButton(buttonText);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setBackground(Color.BLACK);
            button.setForeground(Color.WHITE);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonPressed(buttonText, displayField);
                }
            });
            buttonPanel.add(button);
        }

        JButton historyButton = new JButton("History");
        historyButton.setFont(new Font("Arial", Font.BOLD, 20));
        historyButton.setBackground(Color.GRAY);
        historyButton.setForeground(Color.WHITE);
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHistoryPage();
            }
        });
        buttonPanel.add(historyButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomePage homePage = (HomePage) SwingUtilities.getWindowAncestor(CalculatorPanel.this);
                homePage.switchToHomePage();
            }
        });
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    @SuppressWarnings("unused")
    private void buttonPressed(String buttonText, JTextField displayField) {
        if (buttonText.equals("C")) {
            _output = "";
            num1 = 0;
            num2 = 0;
            operand = "";
            opused = false;
            decused = false;
        } else if (buttonText.equals("+") ||
                buttonText.equals("-") ||
                buttonText.equals("/") ||
                buttonText.equals("*")) {
            if (!opused) {
                opused = true;
                decused = false;
                operand = buttonText;
                _output += " " + buttonText + " ";
            }
        } else if (buttonText.equals("Del")) {
            if (_output.length() > 0) {
                if (_output.substring(_output.length() - 1, _output.length()).equals(" ")) {
                    _output = _output.substring(0, _output.length() - 3);
                    opused = false;
                } else {
                    _output = _output.substring(0, _output.length() - 1);
                }
            }
        } else if (buttonText.equals("=")) {
            String expression = _output.replaceAll(" ", ""); // Remove spaces
            try {
                double result = Calculator.evaluate(_output);
                String exp = _output;
                _output = String.valueOf(result);
                displayField.setText(_output);
                appendToHistory(exp + " = " + _output);
            } catch (Exception ex) {
                _output = "Error";
                displayField.setText(_output);
            }
            num1 = 0;
            num2 = 0;
            operand = "";
            opused = false;
            decused = false;
        } else {
            if (!buttonText.equals("Del")) {
                if (buttonText.equals(".") && decused) {
                    // Handle multiple decimal points.
                } else {
                    _output += buttonText;
                    if (buttonText.equals(".")) {
                        decused = true;
                    }
                }
            }
        }
        if (_output.equals("Error")) {
            output = _output;
            displayField.setText(output);
            appendToHistory(_output);
            
        } else {
            output = _output;
            displayField.setText(output);
        }
    }
    private void appendToHistory(String calculation) {
        historyList.add(calculation);
    }
    private void showHistoryPage() {
        // Open the HistoryPage
        new Calculator_History(historyList);
    }
    
}
class Calculator_History extends JFrame {
    public Calculator_History(List<String> historyList) {
        setTitle("Calculator History");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea historyTextArea = new JTextArea();
        historyTextArea.setEditable(false);

        for (String calculation : historyList) {
            historyTextArea.append(calculation + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(historyTextArea);

        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
class Calculator {
    public static double evaluate(String expression) {
        try {
            String[] parts = expression.split(" ");
            double operand1 = Double.parseDouble(parts[0]);
            double operand2 = Double.parseDouble(parts[2]);
            String operator = parts[1];
            switch (operator) {
                case "+":
                    return operand1 + operand2;
                case "-":
                    return operand1 - operand2;
                case "*":
                    return operand1 * operand2;
                case "/":
                    if (operand2 != 0) {
                        return operand1 / operand2;
                    } else {
                        throw new ArithmeticException("Division by zero");
                    }
                default:
                    throw new IllegalArgumentException("Invalid operator");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid expression");
        }
    }
}