import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.Dimension;
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

        /** TOP PANEL */
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(0x8378FF));
        topPanel.setLayout(new GridLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton btnSave = new JButton("Save");
        btnSave.setPreferredSize(new Dimension(150, 30));
        btnSave.setFont(bFont);
        btnSave.setForeground(Color.WHITE);
        btnSave.setBackground(new Color(0x6256EC));
        btnSave.setFocusable(false);

        JButton btnBack = new JButton("Back to Main Menu");
        btnBack.setPreferredSize(new Dimension(150, 10));
        btnBack.setFont(bFont);
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(new Color(0x6256EC));
        btnBack.setFocusable(false);

        JButton btnEdit = new JButton("Edit");
        btnEdit.setPreferredSize(new Dimension(150, 30));
        btnEdit.setFont(bFont);
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setBackground(new Color(0x6256EC));
        btnEdit.setFocusable(false);

        JComboBox<String> dropdown = getStringJComboBox(bFont);

        // Add components to the topPanel | make this last
        topPanel.add(btnBack);
        topPanel.add(dropdown);
        topPanel.add(btnEdit);
        topPanel.add(btnSave);

        /** CENTER PANEL */
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // Stack components vertically
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

            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        int row = table.getSelectedRow();
                        if (row != -1) {
                            btnEdit.setEnabled(true);
                        } else {
                            btnEdit.setEnabled(false);
                        }
                    }
                }
            });
            semesterPanel.add(new JLabel(semesters[i], SwingConstants.CENTER), BorderLayout.NORTH);
            semesterPanel.add(scrollPane, BorderLayout.CENTER);
            centerPanel.add(semesterPanel);
            tableModels[i] = model;
            tables[i] = table;
        }

        /** SETTING CONSTRAINTS AND ADDING COMPONENTS | make this last */
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        btnEdit.addActionListener(e -> {
            int selectedTableIndex = dropdown.getSelectedIndex();
            int selectedRowIndex = tables[selectedTableIndex].getSelectedRow();
            if (selectedRowIndex != -1) {
                String courseNumber = JOptionPane.showInputDialog("Enter Course Number", tables[selectedTableIndex].getValueAt(selectedRowIndex, 0));
                String courseTitle = JOptionPane.showInputDialog("Enter Course Title", tables[selectedTableIndex].getValueAt(selectedRowIndex, 1));
                String units = JOptionPane.showInputDialog("Enter Units", tables[selectedTableIndex].getValueAt(selectedRowIndex, 2));
                String grades = JOptionPane.showInputDialog("Enter Grades", tables[selectedTableIndex].getValueAt(selectedRowIndex, 3));

                tables[selectedTableIndex].setValueAt(courseNumber, selectedRowIndex, 0);
                tables[selectedTableIndex].setValueAt(courseTitle, selectedRowIndex, 1);
                tables[selectedTableIndex].setValueAt(units, selectedRowIndex, 2);
                tables[selectedTableIndex].setValueAt(grades, selectedRowIndex, 3);
            }
        });

        btnSave.addActionListener(e -> {
            int selectedTableIndex = dropdown.getSelectedIndex();
            try {
                FileWriter fw = new FileWriter("src\\firstYear\\" + fileNames[selectedTableIndex]);
                BufferedWriter bw = new BufferedWriter(fw);
                for (int i = 0; i < tableModels[selectedTableIndex].getRowCount(); i++) {
                    for (int j = 0; j < tableModels[selectedTableIndex].getColumnCount(); j++) {
                        bw.write(tableModels[selectedTableIndex].getValueAt(i, j) + "");
                        if (j != tableModels[selectedTableIndex].getColumnCount() - 1) {
                            bw.write(",");
                        }
                    }
                    bw.newLine();
                }
                bw.close();
                fw.close();
                JOptionPane.showMessageDialog(null, "Data Exported");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private static JComboBox<String> getStringJComboBox(Font bFont) { // dropdown menu
        String[] options = {"First Year", "Second Year", "Third Year", "Fourth Year"};
        JComboBox<String> dropdown = new JComboBox<>(options);
        dropdown.setPreferredSize(new Dimension(200, 10));
        dropdown.setFont(bFont);
        dropdown.setBackground(new Color(0x6256EC));
        dropdown.setForeground(Color.WHITE);
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
