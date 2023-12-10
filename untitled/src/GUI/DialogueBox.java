package GUI;

import javax.swing.*;

public class DialogueBox {
    public static void showMessageDialog(String message) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JOptionPane.showMessageDialog(frame, message, "Message", JOptionPane.INFORMATION_MESSAGE);
        frame.dispose();
    }
}

