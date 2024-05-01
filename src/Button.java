import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.*;

public class Button extends JButton {
    Font btnFont, bFont;

    public Button(String text) throws IOException, FontFormatException {
        super(text);

        btnFont = Font.createFont(Font.TRUETYPE_FONT, new File("src\\assets\\PlusJakartaSans-Regular.ttf"));
        bFont = btnFont.deriveFont(Font.PLAIN, 14);
        setFont(bFont);
        setBorder(new RoundedBorder());
        setBackground(new Color(0xBEB8FF));
        setFocusable(false);
    }

    private static class RoundedBorder extends AbstractBorder {
        private final int radius = 20; // default radius
        private final Color color = new Color(0x6256EC); // default color
        private final int thickness = 3; // default thickness

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(color); // border color
            g2.setStroke(new BasicStroke(thickness)); // border thickness
            g2.drawRoundRect(x + (thickness / 2), y + (thickness / 2), width - thickness, height - thickness, radius, radius); // Adjusted rectangle size to match border thickness
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius + (thickness / 2), radius + (thickness / 2), radius + (thickness / 2), radius + (thickness / 2));
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.top = insets.right = insets.bottom = radius + (thickness / 2);
            return insets;
        }
    }
}
