import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.HashMap;
import java.util.Map;

public class Grubit {
    private java.awt.List orderList;
    private Label quoteLabel;
    private JButton completeOrderButton;
    private JButton proceedToOrderButton;
    private JPanel mainPanel;
    private JFrame frame;
    private JFrame billingFrame;
    private JFrame paymentFrame;
    private Map<String, Integer> foodPrices;
    private int totalPrice = 0;

    public Grubit() {
        foodPrices = new HashMap<>();
        initializeFoodPrices();

        // Create the frame
        frame = new JFrame("Grubit");
        frame.setSize(500, 1100); // Increased height for additional components
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold all components
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(480, 1300)); // Increased preferred height

        // Title Label
        JLabel titleLabel = new JLabel("Welcome to Grubit!");
        titleLabel.setBounds(50, 30, 400, 50);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.decode("#C62828"));
        mainPanel.add(titleLabel);

        // Name Section
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 100, 100, 30);
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        JTextField nameField = new JTextField();
        nameField.setBounds(160, 100, 200, 30);
        mainPanel.add(nameLabel);
        mainPanel.add(nameField);

        // Order Section
        JLabel orderLabel = new JLabel("Order:");
        orderLabel.setBounds(50, 150, 100, 30);
        orderLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        orderList = new java.awt.List();
        orderList.setBounds(160, 150, 250, 120);
        mainPanel.add(orderLabel);
        mainPanel.add(orderList);

        // Appetizers Section
        JLabel appetizersLabel = new JLabel("Appetizers:");
        appetizersLabel.setBounds(50, 300, 200, 30);
        appetizersLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        mainPanel.add(appetizersLabel);

        // Appetizers Buttons
        JPanel appetizersPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        appetizersPanel.setBounds(50, 340, 400, 120);
        appetizersPanel.setBackground(Color.decode("#FFEBEE"));

        JButton bruschettaButton = createFoodButton("Bruschetta");
        JButton bougetButton = createFoodButton("French Bouget");
        JButton pineSaladButton = createFoodButton("Pine Salad");
        JButton lasagneButton = createFoodButton("Lasagne");

        // Add action listeners for appetizers
        bruschettaButton.addActionListener(addToOrderListener("Bruschetta"));
        bougetButton.addActionListener(addToOrderListener("French Bouget"));
        pineSaladButton.addActionListener(addToOrderListener("Pine Salad"));
        lasagneButton.addActionListener(addToOrderListener("Lasagne"));

        appetizersPanel.add(bruschettaButton);
        appetizersPanel.add(bougetButton);
        appetizersPanel.add(pineSaladButton);
        appetizersPanel.add(lasagneButton);

        mainPanel.add(appetizersPanel);

        // Main Course Section
        JLabel mainCourseLabel = new JLabel("Main Course:");
        mainCourseLabel.setBounds(50, 480, 200, 30);
        mainCourseLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        mainPanel.add(mainCourseLabel);

        // Main Course Buttons
        JPanel mainCoursePanel = new JPanel(new GridLayout(3, 2, 10, 10));
        mainCoursePanel.setBounds(50, 520, 400, 180);
        mainCoursePanel.setBackground(Color.decode("#FFEBEE"));

        JButton alfredoPastaButton = createFoodButton("Alfredo Pasta");
        JButton shepherdPieButton = createFoodButton("Shepherd's Pie");
        JButton volAuVentButton = createFoodButton("Vol-au-Vent");
        JButton royalePizzaButton = createFoodButton("Royale Pizza");
        JButton quicheButton = createFoodButton("Quiche");
        JButton lasagneMainButton = createFoodButton("Lasagne");

        // Add action listeners for main course
        alfredoPastaButton.addActionListener(addToOrderListener("Alfredo Pasta"));
        shepherdPieButton.addActionListener(addToOrderListener("Shepherd's Pie"));
        volAuVentButton.addActionListener(addToOrderListener("Vol-au-Vent"));
        royalePizzaButton.addActionListener(addToOrderListener("Royale Pizza"));
        quicheButton.addActionListener(addToOrderListener("Quiche"));
        lasagneMainButton.addActionListener(addToOrderListener("Lasagne"));

        mainCoursePanel.add(alfredoPastaButton);
        mainCoursePanel.add(shepherdPieButton);
        mainCoursePanel.add(volAuVentButton);
        mainCoursePanel.add(royalePizzaButton);
        mainCoursePanel.add(quicheButton);
        mainCoursePanel.add(lasagneMainButton);

        mainPanel.add(mainCoursePanel);

        // Additional Options
        JLabel optionsLabel = new JLabel("Additional Options:");
        optionsLabel.setBounds(50, 720, 200, 30);
        optionsLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        mainPanel.add(optionsLabel);

        Checkbox coffeeCheckbox = new Checkbox("Include Coffee");
        coffeeCheckbox.setBounds(50, 760, 150, 30);
        Checkbox dessertCheckbox = new Checkbox("Add Ice Cream");
        dessertCheckbox.setBounds(250, 760, 150, 30);
        mainPanel.add(coffeeCheckbox);
        mainPanel.add(dessertCheckbox);

        // Place Order Button
        JButton placeOrderButton = new JButton("Place Order");
        placeOrderButton.setBounds(150, 800, 200, 40);
        placeOrderButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        placeOrderButton.setBackground(Color.decode("#C62828"));
        placeOrderButton.setForeground(Color.WHITE);
        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processOrder();
            }
        });
        mainPanel.add(placeOrderButton);

        // Food Quote Section
        quoteLabel = new Label("");
        quoteLabel.setBounds(50, 850, 400, 30);
        quoteLabel.setFont(new Font("SansSerif", Font.ITALIC, 16));
        quoteLabel.setAlignment(Label.CENTER);
        quoteLabel.setForeground(Color.decode("#4E342E"));
        mainPanel.add(quoteLabel);

        // Create a JScrollPane and add the mainPanel to it
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBounds(0, 0, 500, 900);
        frame.add(scrollPane);

        // Set the frame visibility
        frame.setVisible(true);
    }

    // Initialize food prices
    private void initializeFoodPrices() {
        int appetizersPrice = 350;  // Price in INR
        int mainCoursePrice = 450;  // Price in INR

        foodPrices.put("Bruschetta", appetizersPrice);
        foodPrices.put("French Bouget", appetizersPrice + 30);
        foodPrices.put("Pine Salad", appetizersPrice + 50);
        foodPrices.put("Lasagne", appetizersPrice + 20);

        foodPrices.put("Alfredo Pasta", mainCoursePrice);
        foodPrices.put("Shepherd's Pie", mainCoursePrice + 30);
        foodPrices.put("Vol-au-Vent", mainCoursePrice + 40);
        foodPrices.put("Royale Pizza", mainCoursePrice + 20);
        foodPrices.put("Quiche", mainCoursePrice + 10);
        foodPrices.put("Lasagne", mainCoursePrice + 25);
    }

    // Method to create food buttons with prices
    private JButton createFoodButton(String foodName) {
        JButton button = new JButton(foodName + " - ₹" + foodPrices.get(foodName));
        button.setFont(new Font("SansSerif", Font.PLAIN, 14));
        button.setBackground(Color.decode("#FFCDD2"));
        button.setForeground(Color.decode("#880E4F"));
        return button;
    }

    // Method to create action listener for adding food item to order
    private ActionListener addToOrderListener(String foodName) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int price = foodPrices.get(foodName);
                orderList.add(foodName + " - ₹" + price);
                totalPrice += price;
            }
        };
    }

    // Process Order
    private void processOrder() {
        orderList.add("Order placed!");
        orderList.add("Total: ₹" + totalPrice);
        showBillingOptions();
    }

    // Method to show billing options
    private void showBillingOptions() {
        if (billingFrame == null) {
            billingFrame = new JFrame("Billing Options");
            billingFrame.setSize(500, 300);
            billingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            billingFrame.setLayout(new BorderLayout());

            JPanel billingPanel = new JPanel();
            billingPanel.setLayout(new GridLayout(5, 1, 10, 10));

            // Payment Label
            JLabel paymentLabel = new JLabel("Choose Payment Method:");
            paymentLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
            billingPanel.add(paymentLabel);

            JRadioButton cardButton = new JRadioButton("Card");
            JRadioButton upiButton = new JRadioButton("UPI");
            JRadioButton grubWalletButton = new JRadioButton("Grub Wallet");

            ButtonGroup paymentGroup = new ButtonGroup();
            paymentGroup.add(cardButton);
            paymentGroup.add(upiButton);
            paymentGroup.add(grubWalletButton);

            billingPanel.add(cardButton);
            billingPanel.add(upiButton);
            billingPanel.add(grubWalletButton);

            // Confirm Order Button
            JButton confirmOrderButton = new JButton("Confirm Order");
            confirmOrderButton.setFont(new Font("SansSerif", Font.BOLD, 16));
            confirmOrderButton.setBackground(Color.decode("#C62828"));
            confirmOrderButton.setForeground(Color.WHITE);
            confirmOrderButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(billingFrame, "Thank you for confirming your order!");
                    billingFrame.dispose();
                    showPaymentWindow(); // Open payment window after confirming the order
                }
            });
            billingPanel.add(confirmOrderButton);

            billingFrame.add(billingPanel, BorderLayout.CENTER);
        }
        billingFrame.setVisible(true);
    }

    // Method to show payment window
    private void showPaymentWindow() {
        if (paymentFrame == null) {
            paymentFrame = new JFrame("Payment");
            paymentFrame.setSize(500, 300);
            paymentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            paymentFrame.setLayout(new BorderLayout());

            JPanel paymentPanel = new JPanel();
            paymentPanel.setLayout(new GridLayout(4, 1, 10, 10));

            // Payment Label
            JLabel paymentLabel = new JLabel("Complete Your Payment");
            paymentLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
            paymentPanel.add(paymentLabel);

            // Add message about the total amount
            JLabel totalLabel = new JLabel("Total Amount: ₹" + totalPrice);
            totalLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
            paymentPanel.add(totalLabel);

            // Payment Options
            JRadioButton cardButton = new JRadioButton("Pay with Card");
            JRadioButton upiButton = new JRadioButton("Pay with UPI");
            JRadioButton grubWalletButton = new JRadioButton("Pay with Grub Wallet");

            ButtonGroup paymentGroup = new ButtonGroup();
            paymentGroup.add(cardButton);
            paymentGroup.add(upiButton);
            paymentGroup.add(grubWalletButton);

            paymentPanel.add(cardButton);
            paymentPanel.add(upiButton);
            paymentPanel.add(grubWalletButton);

            // Confirm Payment Button
            JButton confirmPaymentButton = new JButton("Confirm Payment");
            confirmPaymentButton.setFont(new Font("SansSerif", Font.BOLD, 16));
            confirmPaymentButton.setBackground(Color.decode("#C62828"));
            confirmPaymentButton.setForeground(Color.WHITE);
            confirmPaymentButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(paymentFrame, "Payment Confirmed!");
                    paymentFrame.dispose();
                }
            });
            paymentPanel.add(confirmPaymentButton);

            paymentFrame.add(paymentPanel, BorderLayout.CENTER);
        }
        paymentFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Grubit();
            }
        });
    }
}
