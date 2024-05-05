

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class ButtonThree extends JPanel {
    ButtonThree() throws IOException, FontFormatException {
        super();
        setPreferredSize(new Dimension(1000, 600));
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();

        Font fontBold = Font.createFont(Font.TRUETYPE_FONT, new File("src\\assets\\PlusJakartaSans-Bold.ttf"));
        Font fontRegular = Font.createFont(Font.TRUETYPE_FONT, new File("src\\assets\\PlusJakartaSans-Regular.ttf"));
        Font bFont = fontRegular.deriveFont(Font.PLAIN, 13);
        Font tFont = fontBold.deriveFont(Font.PLAIN, 13);

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
        btnSave.setFocusable(false);;

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
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);

        JTable table = new JTable();
        table.setFont(bFont);
        JScrollPane scrollPane = new JScrollPane(table);
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(tFont);
        table.setShowGrid(false);
        table.setRowHeight(18);

        // Add components to the centerPanel | make this last
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Load data into the table from a file
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("COURSE NUMBER");
        model.addColumn("DESCRIPTIVE TITLE");
        model.addColumn("UNITS");
        table.setModel(model);

        // Read data from file and add to table model
        try {
            File file = new File("src\\subjectsWithoutGrades\\firstYear.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] columns = line.split(",");
                model.addRow(columns);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Set custom cell renderer to make rows bold
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Check if the row should be bold (e.g., row 2)
                if (row == 0) {
                    c.setFont(tFont);
                }
                if (row == 12) {
                    c.setFont(tFont);
                }
                if (row == 24) {
                    c.setFont(tFont);
                }
                return c;
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 String courseNumber = JOptionPane.showInputDialog("Enter Course Number");
                 String courseName = JOptionPane.showInputDialog("Enter Course Name");
                 table.getModel().setValueAt(courseNumber, table.getSelectedRow(), 0);
                 table.getModel().setValueAt(courseName, table.getSelectedRow(), 1);
            }
         });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    File file = new File("src\\subjectsWithoutGrades\\firstYear.txt");
                    if(!file.exists()){
                        file.createNewFile();
                    }

                    FileWriter fw = new FileWriter(file.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);

                    for(int i = 0; i < table.getRowCount(); i++) {
                        for(int j = 0; j < table.getColumnCount(); j++) {
                            bw.write(table.getModel().getValueAt(i, j) + "");
                        }
                        bw.write(", \n");
                    }
                    bw.close();
                    fw.close();
                    JOptionPane.showMessageDialog(null, "Data Exported");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        /** SETTING CONSTRAINTS AND ADDING COMPONENTS | make this last */

        // Set constraints for topPanel
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3; // Span across all columns
        c.gridheight = 1; // Set to 1 for a small height
        c.weightx = 1.0;
        c.weighty = 0.020; // Adjust this value as needed
        c.fill = GridBagConstraints.BOTH;
        add(topPanel, c);

        // Set constraints for centerPanel
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3; // Span across all columns
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(centerPanel, c);
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

    public static void main(String[] args) throws IOException, FontFormatException {
        JFrame frame = new JFrame("Main Panel");
        frame.setSize(1000,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ButtonThree());
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}
