import javax.swing.*;

public class Test extends JDialog{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton button1;
    private JPanel Testing;

    Test(JFrame parent) {
        super(parent);
        parent.setTitle("Register");
        parent.setContentPane(Testing);
        parent.setSize(1000,600);
        parent.setVisible(true);
    }

    public static void main(String[] args) {
        Test t = new Test(null);
    }
}
