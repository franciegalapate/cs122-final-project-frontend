import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;

public class ButtonThree extends JPanel {
    private DefaultTableModel[] tableModels;
    private String[] fileNames;
    private JTable[] tables;

    ButtonThree() throws IOException, FontFormatException {
        super();
        setPreferredSize(new Dimension(1000, 600));
        setLayout(new BorderLayout());

        Font bFont = Font.createFont(Font.TRUETYPE_FONT, new File("src\\assets\\PlusJakartaSans-Regular.ttf")).deriveFont(Font.PLAIN, 13);
        Font tFont = Font.createFont(Font.TRUETYPE_FONT, new File("src\\assets\\PlusJakartaSans-Bold.ttf")).deriveFont(Font.PLAIN, 13);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS)); // Horizontal arrangement
        topPanel.setBackground(new Color(0x6256EC));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(0x6256EC));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton btnSave = new JButton("Save");
        btnSave.setPreferredSize(new Dimension(150, 30));
        btnSave.setFont(bFont);
        btnSave.setForeground(Color.BLACK);
        btnSave.setBackground(new Color(0xBEB8FF));
        btnSave.setFocusable(false);

        JButton btnBack = new JButton("Back to Main Menu");
        btnBack.setPreferredSize(new Dimension(150, 30));
        btnBack.setFont(bFont);
        btnBack.setForeground(Color.BLACK);
        btnBack.setBackground(new Color(0xBEB8FF));
        btnBack.setFocusable(false);

        JComboBox<String> dropdown = getStringJComboBox(bFont);

        topPanel.add(btnBack);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(dropdown);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(btnSave);

        /** CENTER PANEL */
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);

        // Create tables for each semester
        String[] semesters = {"First Semester", "Second Semester", "Short term"};
        fileNames = new String[]{"firstSemester.txt", "secondSemester.txt", "shortTerm.txt"};
        tableModels = new DefaultTableModel[semesters.length];
        tables = new JTable[semesters.length];

        for (int i = 0; i < semesters.length; i++) {
            JPanel semesterPanel = new JPanel(new BorderLayout());
            JTable table = new JTable();
            table.setFont(bFont);
            JScrollPane scrollPane = new JScrollPane(table);

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Course Number");
            model.addColumn("Course Title");
            model.addColumn("Units");
            model.addColumn("Grades");
            table.setModel(model);
            populateTableFromTextFile("src\\firstYear\\" + fileNames[i], model); // Populate table from text file

            semesterPanel.add(new JLabel(semesters[i], SwingConstants.CENTER), BorderLayout.NORTH);
            semesterPanel.add(scrollPane, BorderLayout.CENTER);
            centerPanel.add(semesterPanel);
            tableModels[i] = model;
            tables[i] = table;
        }

        // resize column widths for each table
        TableColumnModel[] columnModels = new TableColumnModel[semesters.length];
        for (int i = 0; i < semesters.length; i++) {
            TableColumnModel columnModel = tables[i].getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(100); // Course Number
            columnModel.getColumn(1).setPreferredWidth(200); // Course Title
            columnModel.getColumn(2).setPreferredWidth(50); // Units
            columnModel.getColumn(3).setPreferredWidth(50); // Grades
            columnModels[i] = columnModel;
        }

        // disable stuff yk
        for (int i = 0; i < semesters.length; i++) {
            for (int j = 0; j < tables[i].getColumnCount(); j++) {
                tables[i].getColumnModel().getColumn(j).setResizable(false);
                tables[i].getTableHeader().setReorderingAllowed(false);
                // tables[i].setEnabled(false);
            }
        }

        /** SETTING CONSTRAINTS AND ADDING COMPONENTS | make this last */
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        /** ACTION LISTENERS */
        btnSave.addActionListener(e -> {
            int selectedTableIndex = dropdown.getSelectedIndex();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("src\\firstYear\\" + fileNames[selectedTableIndex]))) {
                DefaultTableModel model = tableModels[selectedTableIndex];
                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        bw.write(model.getValueAt(i, j) + "");
                        if (j != model.getColumnCount() - 1) {
                            bw.write(",");
                        }
                    }
                    bw.newLine();
                }
                JOptionPane.showMessageDialog(null, "Data Saved");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btnBack.addActionListener(e -> {
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            try {
                Home h = new Home();
                currentFrame.dispose();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (FontFormatException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    private static JComboBox<String> getStringJComboBox(Font bFont) { // dropdown menu
        String[] options = {"First Year", "Second Year", "Third Year", "Fourth Year"};

        JComboBox<String> dropdown = new JComboBox<>(options);

        dropdown.setPreferredSize(new Dimension(100, 10));

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

    public static void main(String[] args) throws IOException, FontFormatException {
        JFrame frame = new JFrame("Main Panel");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ButtonThree());
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
