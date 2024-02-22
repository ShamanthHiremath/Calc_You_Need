import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {
    @SuppressWarnings("unused")
    private JPanel currentPanel;
    private CalculatorPanel calculatorPanel;
    private BMICalculatorPanel bmiCalculatorPanel;
    private QuadraticSolverPanel quadraticSolverPanel;
    private JPanel homePanel;
    public HomePage() {
        setTitle("basic_tools_applications");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException
                | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        JButton calculatorButton = new JButton("Calculator");
        JButton bmiCalculatorButton = new JButton("BMI Calculator");
        JButton quadraticSolverButton = new JButton("Quadratic Solver");
        JButton newButton = new JButton("New Button");

        calculatorButton.setBackground(new Color(32, 32, 32));
        calculatorButton.setForeground(Color.WHITE);
        calculatorButton.setFocusPainted(false);
        calculatorButton.setPreferredSize(new Dimension(150, 50));

        bmiCalculatorButton.setBackground(new Color(32, 32, 32));
        bmiCalculatorButton.setForeground(Color.WHITE);
        bmiCalculatorButton.setFocusPainted(false);
        bmiCalculatorButton.setPreferredSize(new Dimension(150, 50));

        quadraticSolverButton.setBackground(new Color(32, 32, 32));
        quadraticSolverButton.setForeground(Color.WHITE);
        quadraticSolverButton.setFocusPainted(false);
        quadraticSolverButton.setPreferredSize(new Dimension(150, 50));


        homePanel = new JPanel(new GridLayout(5, 1));
        homePanel.setBackground(new Color(32, 32, 32));
        homePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel welcomeLabel = new JLabel("WELCOME TO YOUR BASIC_TOOLS_APP");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel subheadingLabel = new JLabel("OUR SWEET LITTLE JAVA PROJECT....");
        subheadingLabel.setForeground(Color.WHITE);
        subheadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        subheadingLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        homePanel.add(welcomeLabel);
        homePanel.add(subheadingLabel);
        homePanel.add(calculatorButton);
        homePanel.add(bmiCalculatorButton);
        homePanel.add(quadraticSolverButton);
        add(homePanel, BorderLayout.CENTER);

        calculatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToCalculator();
            }
        });

        bmiCalculatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToBMICalculator();
            }
        });

        quadraticSolverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToQuadraticSolver();
            }
        });

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        calculatorPanel = new CalculatorPanel(this);
        bmiCalculatorPanel = new BMICalculatorPanel(this);
        quadraticSolverPanel = new QuadraticSolverPanel(this);

        setVisible(true);
    }

    public void switchToCalculator() {
        getContentPane().removeAll();
        getContentPane().add(calculatorPanel, BorderLayout.CENTER);
        currentPanel = calculatorPanel;
        revalidate();
        repaint();
    }

    public void switchToBMICalculator() {
        getContentPane().removeAll();
        getContentPane().add(bmiCalculatorPanel, BorderLayout.CENTER);
        currentPanel = bmiCalculatorPanel;
        revalidate();
        repaint();
    }

    public void switchToHomePage() {
        getContentPane().removeAll();
        getContentPane().add(homePanel, BorderLayout.CENTER);
        currentPanel = homePanel;
        revalidate();
        repaint();
    }
    public void switchToQuadraticSolver() {
        getContentPane().removeAll();
        getContentPane().add(quadraticSolverPanel, BorderLayout.CENTER);
        currentPanel = quadraticSolverPanel;
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HomePage();
            }
        });
    }
}