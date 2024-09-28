import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class loginframe1 extends JFrame implements ActionListener, MouseListener {

    JTextField email_tf;
    JPasswordField p_tf;
    JButton loginButton;

    loginframe1() {
        Font f = new Font("Roboto", Font.BOLD, 25);
        Color lightblue = new Color(51, 204, 255);

        this.setSize(1200, 600);
        this.setTitle("Login Frame");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(null);
        c.setBackground(Color.GRAY);

        JLabel email = new JLabel("Email:");
        email.setBounds(380, 130, 90, 30);
        email.setForeground(Color.BLACK);
        email.setFont(f);
        c.add(email);

        email_tf = new JTextField();
        email_tf.setBounds(535, 141, 180, 30);
        c.add(email_tf);

        JLabel password = new JLabel("Password:");
        password.setBounds(380, 180, 160, 40);
        password.setForeground(Color.BLACK);
        password.setFont(f);
        c.add(password);

        p_tf = new JPasswordField();
        p_tf.setBounds(535, 191, 180, 30);
        c.add(p_tf);

       
        loginButton = new JButton("Login");
        loginButton.setBounds(530, 240, 100, 40);
        loginButton.setFocusable(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.setBackground(lightblue);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(this);
        c.add(loginButton);

        this.setVisible(true);
    }

    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String email = email_tf.getText();
            String password = new String(p_tf.getPassword());

            
            if (validateLogin(email, password)) {
                System.out.println("Login successful for: " + email); 
                dispose(); 
                new DoctorDashboard(); 
            } else {
                System.out.println("Login failed for: " + email); 
                JOptionPane.showMessageDialog(this, "Invalid Email or Password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean validateLogin(String email, String password) {
        try {
            File file = new File("userRegisterdata.txt");
            if (!file.exists()) {
                return false; 
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String storedName = parts[0]; 
                String storedEmail = parts[1];
                String storedPassword = parts[2];

                
                if (storedEmail.equals(email) && storedPassword.equals(password)) {
                    reader.close();
                    return true; 
                }
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false; 
    }

    
    public void mouseClicked(MouseEvent e) {}

    
    public void mousePressed(MouseEvent e) {}

   
    public void mouseReleased(MouseEvent e) {}

    
    public void mouseEntered(MouseEvent e) {}

    
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        new loginframe1();
    }
}
