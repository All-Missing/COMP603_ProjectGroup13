package COMP603_ProjectGroup13;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginFrame_GUI extends JFrame {
    
    public JButton loginButton;
    public JButton clearButton;    
    public JLabel userNameLabel;
    public JLabel passwordLabel;
    public JTextField userNameTextField;
    public JTextField userPasswordTextField;        
    
    public LoginFrame_GUI () {
        initComponents();
        initPanels();
        initActionListener();
    }
            
    public void initComponents()
    {
        userNameLabel = new JLabel("User name:");
        userNameTextField = new JTextField(20);
        passwordLabel = new JLabel("Password: ");
        userPasswordTextField = new JFormattedTextField(20);
        
        
        loginButton = new JButton("login");
        clearButton = new JButton("clear");
        
    } 
    
    public void initPanels() {
        
    }
    
    public void initActionListener() {
        
    }
    
    
    public static void main(String[] args) {
        LoginFrame_GUI loginFrame = new LoginFrame_GUI();
        loginFrame.setVisible(true);
    }
    
}
