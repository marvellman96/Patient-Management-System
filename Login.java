import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;

public class Login extends JFrame implements ActionListener {
    private JButton b1, b2;
    private JPanel pan1;
    private Color col1, col2;
    private ImageIcon img;
    private JLabel lb1, lb2;
    private Font ft1;

    public Login() {
        super("Marvell Hospital Limited");
        setBounds(600, 200, 850, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pan1 = new JPanel();
        pan1.setLayout(null);
        col1 = new Color(250, 229, 204);
        pan1.setBackground(col1);

        lb2 = new JLabel("Welcome to Marvell Hospital Limited");
        col2 = new Color(0, 0, 0);
        lb2.setForeground(col2);
        lb2.setBounds(150, 400, 600, 30);  
        pan1.add(lb2);
        ft1 = new Font("Cambria", Font.BOLD, 30);
        lb2.setFont(ft1);

        
        img = new ImageIcon("first.gif");
        lb1 = new JLabel(img);
        lb1.setBounds(200, 50, 425, 350);
        pan1.add(lb1);

        b1 = new JButton("Login");
        b1.addActionListener(this);
        b1.setBounds(200, 500, 100, 40);
        pan1.add(b1);

        b2 = new JButton("Register");
        b2.addActionListener(this);
        b2.setBounds(500, 500, 100, 40);
        pan1.add(b2);

        add(pan1);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            
            loginframe1 loginFrame = new loginframe1();
            loginFrame.setVisible(true);
            dispose();
			
        }
		else if (e.getSource()==b2) {
			new Register();
			
			dispose();
		}
        
    }

   public static void main(String[] args) {
    new Login().setVisible(true);
}


}



