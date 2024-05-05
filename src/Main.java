import java.lang.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class Main extends JFrame {

    /** INITIALIZE VARIABLES */
    JFrame frame;
    JPanel topPanel, centerPanel, bottomPanel, semesterPanel;
    JButton btnSave, btnAdd, btnHelp, btnShowGPA;
    JComboBox<String> btnDropdown;
    JTable table;
    JScrollPane scrollPane;
    Font boldFont, regularFont;

    /** MAIN CLASS CONSTRUCTOR */
    Main() throws IOException, FontFormatException {

        /** INITIALIZE FRAME */
        frame = new JFrame("Personal Checklist Manager");
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        /** SET UP FONT */
        regularFont = Font.createFont(Font.TRUETYPE_FONT, new File("src\\assets\\PlusJakartaSans-Regular.ttf")).deriveFont(Font.PLAIN, 13);
        boldFont = Font.createFont(Font.TRUETYPE_FONT, new File("src\\assets\\PlusJakartaSans-Bold.ttf")).deriveFont(Font.PLAIN, 13);

        /** INITALIZE PANELS AND BUTTONS */

        // TOP PANEL
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(new Color(0x6256EC));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        btnHelp = new JButton("Help");
        btnHelp.setPreferredSize(new Dimension(100, 30));
        btnHelp.setFont(boldFont);
        btnHelp.setForeground(Color.BLACK);
        btnHelp.setBackground(new Color(0xBEB8FF));
        btnHelp.setFocusable(false);

        btnDropdown = getStringJComboBox(boldFont);

        topPanel.add(btnHelp, BorderLayout.WEST);
        topPanel.add(Box.createRigidArea(new Dimension(200, 0)));
        topPanel.add(btnDropdown, BorderLayout.EAST);

        // CENTER PANEL
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);

        String[] semesters = {"First Semester", "Second Semester", "Short term"};
        String[] fileNames = {"firstSemester.txt", "secondSemester.txt", "shortTerm.txt"};

        for (int i = 0; i < semesters.length; i++) {
            JPanel semesterPanel = new JPanel(new BorderLayout());

            JTable table = new JTable();
            table.setFont(regularFont);
            table.getTableHeader().setFont(boldFont);
            table.getTableHeader().setReorderingAllowed(false);

            JScrollPane scrollPane = new JScrollPane(table);

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Course Number");
            model.addColumn("Course Title");
            model.addColumn("Units");
            model.addColumn("Grades");

            table.setModel(model);

            populateTableFromTextFile("src\\firstYear\\" + fileNames[i], model);

            TableColumnModel columnModel = table.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(100); // course number
            columnModel.getColumn(1).setPreferredWidth(200); // course title
            columnModel.getColumn(2).setPreferredWidth(50); // units
            columnModel.getColumn(3).setPreferredWidth(50); // grades

            table.getTableHeader().setReorderingAllowed(false);
            table.getTableHeader().setResizingAllowed(false);

            semesterPanel.add(new JLabel(semesters[i], SwingConstants.CENTER), BorderLayout.NORTH);
            semesterPanel.add(scrollPane, BorderLayout.CENTER);
            centerPanel.add(semesterPanel);
        }

        // BOTTOM PANEL
        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(0x6256EC));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        btnAdd = new JButton("Add Course");
        btnAdd.setPreferredSize(new Dimension(120, 30));
        btnAdd.setFont(boldFont);
        btnAdd.setForeground(Color.BLACK);
        btnAdd.setBackground(new Color(0xBEB8FF));
        btnAdd.setFocusable(false);

        btnShowGPA = new JButton("Show GPA");
        btnShowGPA.setPreferredSize(new Dimension(120, 30));
        btnShowGPA.setFont(boldFont);
        btnShowGPA.setForeground(Color.BLACK);
        btnShowGPA.setBackground(new Color(0xBEB8FF));
        btnShowGPA.setFocusable(false);


        btnSave = new JButton("Save");
        btnSave.setPreferredSize(new Dimension(100, 30));
        btnSave.setFont(boldFont);
        btnSave.setForeground(Color.BLACK);
        btnSave.setBackground(new Color(0xBEB8FF));
        btnSave.setFocusable(false);

        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(btnAdd);
        bottomPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        bottomPanel.add(btnShowGPA);
        bottomPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        bottomPanel.add(btnSave);

        /** ADD ACTION LISTENERS HERE */

        // ADD PANELS TO FRAME
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        /** MAKE THIS LAST */
        frame.pack();
        frame.setVisible(true);

    } // END OF MAIN CLASS CONSTRUCTOR

    /** OTHER METHODS */

    // DROPDOWN MENU
    private static JComboBox<String> getStringJComboBox(Font bFont) {
        String[] years = {"First Year", "Second Year", "Third Year", "Fourth Year"};

        JComboBox<String> dropdown = new JComboBox<>(years);
        dropdown.setPreferredSize(new Dimension(150, 30));

        dropdown.setFont(bFont);
        dropdown.setBackground(new Color(0xBEB8FF));
        dropdown.setForeground(Color.BLACK);
        dropdown.setFocusable(false);

        return dropdown;
    }

    private static void populateTableFromTextFile(String filePath, DefaultTableModel model) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found: " + filePath);
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] columns = line.split(",");
                model.addRow(columns);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /** MAIN METHOD */
    public static void main(String[] args) throws IOException, FontFormatException {
        Main m = new Main();
    }

} // END OF MAIN CLASS
