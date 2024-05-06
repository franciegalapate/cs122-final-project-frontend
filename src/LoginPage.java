import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * 1
 * This is the program we'll run, it's the very first thing that will show. This is where the log-in page is in.
 */

public class LoginPage extends JFrame {
    JFrame frame;
    JPanel leftPanel, rightPanel, labelsPanel;
    JLabel mainTitle, subTitle, backgroundLabel;
    Font fontRegular, fontBold;
    ImageIcon backgroundImage;

    public LoginPage() throws IOException, FontFormatException {
        frame = new JFrame();
        frame.setSize(1200, 700);
        frame.setTitle("Personal Checklist Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0,2));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        fontBold = Font.createFont(Font.TRUETYPE_FONT, new File("src\\assets\\PlusJakartaSans-Bold.ttf"));
        fontRegular = Font.createFont(Font.TRUETYPE_FONT, new File("src\\assets\\PlusJakartaSans-Regular.ttf"));

        /** LEFT PANEL */
        leftPanel = new JPanel(new BorderLayout());

        backgroundImage = new ImageIcon("src\\assets\\bg.png");
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());

        // Create a panel to hold the labels vertically
        labelsPanel = new JPanel();
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
        subTitle.setFont(new Font("Plus Jakarta Sans", Font.PLAIN,18));
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
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 70));

        JLabel hello = new JLabel("Hello,");
        hello.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 40));
        hello.setForeground(new Color(0x6256EC));

        JLabel welcome = new JLabel("welcome!");
        welcome.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 45));
        welcome.setForeground(new Color(0x6256EC));

        /** ADD THE LOG-IN PAGE HERE */

        rightPanel.add(hello);
        rightPanel.add(welcome);

        /** MAKE THIS LAST */
        frame.add(leftPanel);
        frame.add(rightPanel);
        frame.setVisible(true);

        /** PUT THE ACTION LISTENERS HERE */

    }

    public static void main(String[] args) throws IOException, FontFormatException {
        LoginPage home = new LoginPage();
    }
}
