import java.awt.event.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;



public class HomeV2 extends JFrame {
    JFrame frameV2;
    JPanel leftPanel, rightPanel;
    JLabel mainTitle, subTitle, questTitle, backgroundLabel;
    Button btnChoice1;
    JButton btnExit;
    Font fontRegular, fontBold, mTitle, sTitle, qTitle, regular;
    ImageIcon backgroundImage;
    public JTextField userID = new JTextField();

    public HomeV2() throws IOException, FontFormatException {
        frameV2 = new JFrame();
        frameV2.setSize(1000, 600);
        frameV2.setTitle("Personal Checklist Manager");
        frameV2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameV2.setLayout(new GridLayout(0, 2));
        frameV2.setResizable(false);
        frameV2.setLocationRelativeTo(null);

        fontBold = Font.createFont(Font.TRUETYPE_FONT, new File("src\\assets\\PlusJakartaSans-Bold.ttf"));
        fontRegular = Font.createFont(Font.TRUETYPE_FONT, new File("src\\assets\\PlusJakartaSans-Regular.ttf"));
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(fontBold);
        ge.registerFont(fontRegular);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(fontBold);

        mTitle = fontBold.deriveFont(Font.PLAIN, 36); // set custom font for mainTitle UNUSED FOR NOW
        sTitle = fontRegular.deriveFont(Font.PLAIN, 18); // set custom font for subTitle
        qTitle = fontBold.deriveFont(Font.PLAIN, 20); // set custom font for mainTitle
        regular = fontRegular.deriveFont(Font.PLAIN, 15); // set custom font for regular fonts

        /** LEFT PANEL */
        leftPanel = new JPanel(new BorderLayout());

        backgroundImage = new ImageIcon("src\\assets\\bg.png");
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());

        // Create a panel to hold the labels vertically
        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
        labelsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelsPanel.setOpaque(false);

        // mainTitle
        mainTitle = new JLabel("<html><center>Personal Checklist<br>Management</center></html>");
        mainTitle.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 40));
        mainTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainTitle.setForeground(Color.WHITE);
        mainTitle.setOpaque(false);
        mainTitle.setHorizontalAlignment(JLabel.CENTER);

        // subTitle
        subTitle = new JLabel("Stay Organized, Achieve More");
        subTitle.setFont(sTitle);
        subTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label horizontally
        subTitle.setForeground(Color.WHITE);
        subTitle.setOpaque(false);
        subTitle.setHorizontalAlignment(JLabel.CENTER);

        // Add the components to the labels panel
        labelsPanel.add(Box.createVerticalGlue()); // add vertical glue to center vertically
        labelsPanel.add(mainTitle);
        labelsPanel.add(Box.createVerticalStrut(10)); // add vertical space between mainTitle and subTitle
        labelsPanel.add(subTitle);
        labelsPanel.add(Box.createVerticalGlue()); // add vertical glue to center vertically

        backgroundLabel.add(labelsPanel, BorderLayout.CENTER); // add the labels panel to the background label | MAKE THIS LAST
        leftPanel.add(backgroundLabel, BorderLayout.CENTER); // add the background label to the left panel | MAKE THIS LAST

        /** RIGHT PANEL */
        rightPanel = new JPanel(new GridLayout(9, 0, 0, 5));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 70));

        // quesTitle
        questTitle = new JLabel("Please Login to continue.");
        questTitle.setFont(qTitle);
        questTitle.setHorizontalAlignment(JLabel.CENTER);

        // user and textfield
        userID.setText("Username");
        userID.setCaretColor(new Color(0x6256EC));
        userID.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        userID.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xBEB8FF)));



        // button
        btnChoice1 = new Button("LOGIN");

        // exit
        btnExit = new JButton("<html><u>Exit instead</u></html>");
        btnExit.setFont(new Font("fontBold", Font.ITALIC, 15));
        btnExit.setFocusable(false);
        btnExit.setContentAreaFilled(false); // removes background color
        btnExit.setBorderPainted(false); // removes border

        // Add the components to the right panel
        rightPanel.add(questTitle);
        rightPanel.add(userID);
        rightPanel.add(btnChoice1);
        rightPanel.add(btnExit);

        // Add the panels to the frame
        frameV2.add(leftPanel);
        frameV2.add(rightPanel);
        frameV2.setVisible(true); // always make sure this is last

        /** PUT THE ACTION LISTENERS HERE */
        btnChoice1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnChoice1) {
                            try {
                                frameV2.dispose();
                                new Home(userID.getText()).setVisible(true);
                            } catch (IOException | FontFormatException ex) {
                                ex.printStackTrace();
                    }
                }
            }
        });

    }
    public static void main(String[] args) throws IOException, FontFormatException {
        HomeV2 homeV2 = new HomeV2();

    }

}


