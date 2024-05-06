import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * 2
 * After log in page, this is where users are directed to.
 */

public class Homepage extends JFrame {
    /** INITIALIZE VARIABLES */
    JFrame frame;
    JPanel topPanel, sidebarPanel, contentPanel;
    JLabel title, studentName;
    Font regularFont, boldFont;

    /** HOMEPAGE CONSTRUCTOR */
    Homepage() throws IOException, FontFormatException {
        /** INITIALIZE FRAME */
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(1200, 700));
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setBackground(Color.WHITE);

        /** SET UP FONT */
        regularFont = Font.createFont(Font.TRUETYPE_FONT, new File("src\\assets\\PlusJakartaSans-Regular.ttf")).deriveFont(Font.PLAIN, 13);
        boldFont = Font.createFont(Font.TRUETYPE_FONT, new File("src\\assets\\PlusJakartaSans-Bold.ttf")).deriveFont(Font.PLAIN, 15);

        /** TOP PANEL */
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(new Color(0x6256EC));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        title = new JLabel("Personal Checklist Manager");
        title.setPreferredSize(new Dimension(250, 30));
        title.setFont(boldFont);
        title.setForeground(Color.WHITE);
        title.setFocusable(false);

        studentName = new JLabel("INSERT STUDENT'S NAME HERE");
        studentName.setPreferredSize(new Dimension(250, 30));
        studentName.setFont(boldFont);
        studentName.setForeground(Color.WHITE);
        studentName.setFocusable(false);

        topPanel.add(studentName, BorderLayout.EAST);
        topPanel.add(title, BorderLayout.WEST);

        /** SIDEBAR PANEL */
        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS)); // Vertical layout
        sidebarPanel.setBackground(new Color(0x8378FF));

        // Add buttons to the sidebar
        JButton button1 = new JButton("Grades");
        JButton button2 = new JButton("Transcript of Records");
        JButton button3 = new JButton("Curriculum Checklist");
        JButton logoutButton = new JButton("Log out"); // Add logout button

        // Customize button appearance
        button1.setForeground(Color.WHITE);
        button1.setBackground(new Color(0x6256EC));
        button1.setFocusable(false);
        button1.setFont(regularFont);

        button2.setForeground(Color.WHITE);
        button2.setBackground(new Color(0x6256EC));
        button2.setFocusable(false);
        button2.setFont(regularFont);

        button3.setForeground(Color.WHITE);
        button3.setBackground(new Color(0x6256EC));
        button3.setFocusable(false);
        button3.setFont(regularFont);

        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(new Color(0x6256EC));
        logoutButton.setFocusable(false);
        logoutButton.setFont(regularFont);

        // Set maximum size for buttons
        Dimension buttonSize = new Dimension(Short.MAX_VALUE, logoutButton.getPreferredSize().height);
        button1.setMaximumSize(buttonSize);
        button2.setMaximumSize(buttonSize);
        button3.setMaximumSize(buttonSize);
        logoutButton.setMaximumSize(buttonSize);

        // Add buttons to the sidebar panel
        sidebarPanel.add(Box.createVerticalStrut(10));
        sidebarPanel.add(button1);
        sidebarPanel.add(Box.createVerticalStrut(10));
        sidebarPanel.add(button2);
        sidebarPanel.add(Box.createVerticalStrut(10));
        sidebarPanel.add(button3);
        sidebarPanel.add(Box.createVerticalStrut(10));
        sidebarPanel.add(Box.createVerticalGlue());
        sidebarPanel.add(logoutButton);
        sidebarPanel.add(Box.createVerticalStrut(10));

        // Content Panel
        contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout(FlowLayout.LEADING)); // Align components to the left edge

        // Panel 1: Events and Announcements
        JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(500, 700)); // Set preferred size
        panel1.setBackground(new Color(0xD0CBFF)); // Set background color
        JLabel eventsLabel = new JLabel("Events and Announcements");
        eventsLabel.setFont(boldFont);


        // Panel 2: Student Status
        JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(500, 700)); // Set preferred size
        panel2.setBackground(new Color(0xD0CBFF)); // Set background color
        JLabel statusLabel = new JLabel("Student Status");
        statusLabel.setFont(boldFont);

        // Add panels to the content panel
        panel1.add(eventsLabel);
        panel2.add(statusLabel);
        contentPanel.add(panel1);
        contentPanel.add(panel2);

        /** MAKE THIS LAST */
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(sidebarPanel, BorderLayout.WEST); // Add sidebar to the left
        frame.add(contentPanel, BorderLayout.CENTER); // Add content panel to the center

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    } // END OF HOMEPAGE CONSTRUCTOR

    /** MAIN METHOD */
    public static void main(String[] args) throws IOException, FontFormatException {
        Homepage homepage = new Homepage();
    }

} // END OF HOMEPAGE CLASS
