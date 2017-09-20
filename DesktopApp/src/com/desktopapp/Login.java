// ----------------------------------------------
//  TEI Athinas - Tmima Mixanikwn Pliroforikis
//
//          Teliki Ergasia sto mathima
//           Diktyakoy Programmatismou
//
//
//      Avgoustis Aristeidis, 101049
//      Christou Konstantinos, 101040
// ----------------------------------------------

package com.desktopapp;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Login extends JFrame implements ActionListener{
    private final JTextField aTextField;
    private final JPasswordField bTextField;
     
    private JTextField resultTextField;
    public JButton loginButton;
    public JButton actionButton;
    public JButton logoutButton;
    public JSONObject json;
    String Lnt;
     
     
    public Login(){

         JLabel aLabel = new JLabel("Username");
         aTextField = new JTextField(10);

         JLabel bLabel = new JLabel("Password");
         bTextField = new JPasswordField (10);

 
         resultTextField = new JTextField(40);
         resultTextField.setEditable(false);
        

         loginButton = new JButton("Login");
         loginButton.addActionListener(this);
         
         logoutButton = new JButton("Logout");
         logoutButton.addActionListener(this);
         
         actionButton = new JButton("Actions");
         actionButton.addActionListener(this);
       
        
        // panel Creation
        JPanel panel = new JPanel();
        panel.add(aLabel);
        panel.add(aTextField);
        panel.add(bLabel);
        panel.add(bTextField);
       
        panel.add(loginButton);
        panel.add(logoutButton);
        logoutButton.setVisible(false);
        panel.add(actionButton);
        actionButton.setVisible(false);

        panel.add(resultTextField);
        panel.setBorder(BorderFactory.createTitledBorder("Enter your credentials"));
        add(panel);

        setTitle("Admin & Grammateia Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(640, 480);
        // pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==logoutButton){  // logout

        logoutButton.setVisible(false);
        loginButton.setVisible(true);
        actionButton.setVisible(false);
        aTextField.setText(null);
        bTextField.setText(null);
        aTextField.setEditable(true);
        bTextField.setEditable(true);
        resultTextField.setText(null);
        } // Disable all other buttons as well here
        
       
        
        if (e.getSource()==loginButton){ // login
            // We need to call method from class that calls LoginS here
            String username=aTextField.getText();
            String password=bTextField.getText();

            Login_Check login_check = new Login_Check();
            json = new JSONObject();

            try {            
                 json = login_check.logIn(username,password); // Parsing data to login, getting JSON reply
            } 
            catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (ParseException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (json.get("Error") != null){ //fail to login
                switch (Integer.parseInt(json.get("Error").toString())) { //switch error code
                    case 1: 
                        resultTextField.setText("Wrong username or password.");
                        break;
                    case 4:
                        resultTextField.setText("Studends must connect from web app.");
                        break;
                    case 100:    
                        resultTextField.setText("Unexpected error.");
                        break;
                    default:
                        resultTextField.setText("Unexpected error.");      
                }
            }
            else {       //Successful Login
                resultTextField.setText("Privileges level: "+json.get("Role").toString()); // Debugging Purposes + Exhibition

                loginButton.setVisible(false); 
                aTextField.setEditable(false);
                bTextField.setEditable(false);
                logoutButton.setVisible(true);
                actionButton.setVisible(true);

            }
        }
        
         if (e.getSource()==actionButton){ // test // edo tha bei Button pou tha anoigei kainourio JFrame me epiloges gia ola ta WS, right now karfwta FiNfo works   
             Menu.openActions(json.get("Role").toString());
        }
    }

    public static void main(String[] args) {
       new Login().setVisible(true);
    }
}
