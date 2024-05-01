import java.awt.event.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Home extends JFrame {
    JFrame frame;
    JPanel leftPanel, rightPanel;
    JLabel mainTitle, subTitle, questTitle, backgroundLabel;
    Button btnChoice1, btnChoice2, btnChoice3, btnChoice4, btnChoice5, btnChoice6, btnChoice7;
    JButton btnExit;
    Font fontRegular, fontBold, mTitle, sTitle, qTitle, regular;
    ImageIcon backgroundImage;

    public Home() throws IOException, FontFormatException {
        frame = new JFrame();
        frame.setSize(1000, 600);
        frame.setTitle("Personal Checklist Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0,2));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

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
        rightPanel = new JPanel(new GridLayout(9,0,0,5));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 70));

        // quesTitle
        questTitle = new JLabel("What would you like to do?");
        questTitle.setFont(qTitle);
        questTitle.setHorizontalAlignment(JLabel.CENTER);

        // buttons
        btnChoice1 = new Button("Show subjects for each school term");
        btnChoice2 = new Button("Show subjects with grades for each term");
        btnChoice3 = new Button("Enter grades for subjects recently finished");
        btnChoice4 = new Button("Edit a course");
        btnChoice5 = new Button("Add other courses taken");
        btnChoice6 = new Button("Display courses with Grade point Average");
        btnChoice7 = new Button("Display courses with grades in Highest to Lowest");

        btnExit = new JButton("<html><u>Exit instead</u></html>");
        btnExit.setFont(new Font("fontBold", Font.ITALIC, 15));
        btnExit.setFocusable(false);
        btnExit.setContentAreaFilled(false); // removes background color
        btnExit.setBorderPainted(false); // removes border

        // Add the components to the right panel
        rightPanel.add(questTitle);
        rightPanel.add(btnChoice1);
        rightPanel.add(btnChoice2);
        rightPanel.add(btnChoice3);
        rightPanel.add(btnChoice4);
        rightPanel.add(btnChoice5);
        rightPanel.add(btnChoice6);
        rightPanel.add(btnChoice7);
        rightPanel.add(btnExit);

        // Add the panels to the frame
        frame.add(leftPanel);
        frame.add(rightPanel);
        frame.setVisible(true); // always make sure this is last

        /** PUT THE ACTION LISTENERS HERE */

        btnChoice1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ButtonOne buttonOne = new ButtonOne();
                    frame.getContentPane().removeAll(); // remove existing components from the frame
                    frame.getContentPane().setLayout(new BorderLayout());
                    frame.getContentPane().add(buttonOne, BorderLayout.CENTER);
                    frame.getContentPane().revalidate(); // refresh layout
                    frame.getContentPane().repaint();
                } catch (IOException | FontFormatException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    public static void main(String[] args) throws IOException, FontFormatException {
        Home home = new Home();
    }
}
