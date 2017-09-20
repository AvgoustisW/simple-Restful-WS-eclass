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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Menu extends JFrame implements ActionListener {
    //Login JFrames
    public JLabel uLabel;
    public JLabel pLabel;
    public JLabel rLabel;
    
    //Show info JFrames
    private final JTextField showInfoTextField;
    private final JTextArea vathmoiTextArea;
    private final JTextArea UinsertInfo;
    private final JTextField usernameTextField;
    private final JTextField passwordTextField;
    public JButton showInfoButton;
    
    //StudentInsert JFrames
    private final JTextField amTextField;
    private final JTextField firstnameTextField;
    private final JTextField lastnameTextField;
    private final JTextField semesterTextField;
    private final JTextArea  studentInfoTextArea;
    public JLabel amLabel;
    public JLabel firstnameLabel;
    public JLabel lastnameLabel; 
    public JLabel semesterLabel;
    public JButton studentInsertButton;
   
    //UpdateVathmoi JFrames
    private final JTextField idTextField;
    private final JTextField amvathmouTextField;
    private final JTextField vathmosTextField;
    private final JTextArea  degreeTextArea;
    public JLabel idLabel;
    public JLabel amvathmouLabel;
    public JLabel vathmosLabel;
    public JButton userInsertButton;
    public JButton vathmosButton;
    
    private final JTextField roleTextField; //debug purpose field field
    
    public String role;    
    
    private final JComboBox<String> actionList; //dropdown jcombo box to select actions
    
    public int i; // to use for()
    public String AM;
    
    public Menu(String rolex){ //constructor of Menu, initiallizing values
        role=rolex;
        showInfoTextField = new JTextField(7);
        roleTextField = new JTextField(10);
        passwordTextField = new JPasswordField(10);
        usernameTextField = new JTextField(10);
        uLabel = new JLabel("Username");
        pLabel = new JLabel("Password");
        rLabel = new JLabel("Role");
        amLabel = new JLabel("AM");
        
        firstnameLabel = new JLabel("FirstName");
        lastnameLabel = new JLabel("LastName"); 
        semesterLabel = new JLabel("Semester");
        
        idLabel = new JLabel("lesson ID");
        amvathmouLabel = new JLabel("student AM");
        vathmosLabel = new JLabel("Vathmos");
         
        amTextField = new JTextField(10);
        firstnameTextField = new JTextField(10);
        lastnameTextField = new JTextField(10);
        semesterTextField = new JTextField(10);
        
        idTextField = new JTextField(10);
        amvathmouTextField = new JTextField(10);
        vathmosTextField = new JTextField(10);
        
        vathmoiTextArea = new JTextArea(10,50);
        UinsertInfo = new JTextArea(1,20);
        studentInfoTextArea = new JTextArea(1,20);
        degreeTextArea = new JTextArea(1,20);
        
        actionList = new JComboBox<>(new String[]{"Student Information", "Insert new Student", "Insert new User","Set degrees"});
        
        actionList.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //se kathe periptwsi kanoume true i false, ta antistoixa JFrames pou theloun
                //na emfanistoun sto xristi analoga me tin periptwsi xrisis
                if(actionList.getSelectedIndex() == 0) { //Student Information
                    showInfoInterface(true); 
                    userInsertInterface(false);
                    studentInsertInterface(false);
                    setDegreesInterface(false);                    
                }
                else if(actionList.getSelectedIndex()==1) { //Insert new Student
                    showInfoInterface(false);
                    userInsertInterface(false);
                    studentInsertInterface(true);
                    setDegreesInterface(false);
                }
                else if(actionList.getSelectedIndex()==2) { //Insert new user (admin only)
                    showInfoInterface(false);
                    userInsertInterface(true);
                    studentInsertInterface(false);
                    setDegreesInterface(false);
                }  
                else { //Set degrees
                    showInfoInterface(false);
                    userInsertInterface(false);
                    studentInsertInterface(false);
                    setDegreesInterface(true);
                }
            }
        });
        
        showInfoButton = new JButton("Select AM of Student");
        showInfoButton.addActionListener(this);
          
        userInsertButton = new JButton("Add a new User!");
        userInsertButton.addActionListener(this);
        
        studentInsertButton = new JButton("Add a new Student!");
        studentInsertButton.addActionListener(this);
        
        vathmosButton = new JButton("Add/Update degree");
        vathmosButton.addActionListener(this);
        
        
             
        JPanel panel = new JPanel();
             
        //showing everything as not Visible except what we want to see each time.   
        panel.add(actionList);
        add(actionList, BorderLayout.NORTH);
        panel.add(showInfoButton);
        panel.add(userInsertButton).setVisible(false);
        panel.add(studentInsertButton).setVisible(false);
        panel.add(showInfoTextField);
        panel.add(uLabel).setVisible(false);        
        panel.add(usernameTextField).setVisible(false);
        panel.add(pLabel).setVisible(false);
        panel.add(passwordTextField).setVisible(false);
        panel.add(rLabel).setVisible(false);
        panel.add(roleTextField).setVisible(false);
        
        panel.add(vathmosButton).setVisible(false);
        panel.add(idLabel).setVisible(false);
        panel.add(idTextField).setVisible(false);
        panel.add(amvathmouLabel).setVisible(false);
        panel.add(amvathmouTextField).setVisible(false);
        panel.add(vathmosLabel).setVisible(false);
        panel.add(vathmosTextField).setVisible(false);
        panel.add(degreeTextArea).setVisible(false);
        
        
        panel.add(vathmoiTextArea);
        
        panel.add(UinsertInfo).setVisible(false);
        
        panel.add(amLabel).setVisible(false);
        panel.add(amTextField).setVisible(false);
        panel.add(lastnameLabel).setVisible(false);
        panel.add(firstnameTextField).setVisible(false);
        panel.add(firstnameLabel).setVisible(false);
        panel.add(lastnameTextField).setVisible(false);
        panel.add(semesterLabel).setVisible(false);
        panel.add(semesterTextField).setVisible(false);
        panel.add(studentInfoTextArea).setVisible(false);
        studentInfoTextArea.setEditable(false);
        vathmoiTextArea.setEditable(false);
        UinsertInfo.setEditable(false);
       
        
        panel.setBorder(BorderFactory.createTitledBorder(""));
        vathmoiTextArea.setBorder(BorderFactory.createTitledBorder(""));

        add(panel);

        setTitle("Desktop Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE); //on panel close, will exit application
        setSize(1280, 720);
       
        setLocationRelativeTo(null); 
        setResizable(false); //panel cannot be resized
    }
    
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showInfoButton){ //case show info
            Show_Info show_info = new Show_Info(); //create an instance of Show_info
            JSONObject json = new JSONObject();

            String toShow = "";
      
            try { 
                AM = showInfoTextField.getText(); //get AM from textfield
                json = show_info.show(AM);  //kalei tin show apo tin show_info i opoia tha kalesei to web service
                                            //gia emfanisi stoixeiwn foititi me parametro AM
            } 
            catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex); 
            } 
            catch (ParseException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //ftiaxnoume ta JSONArray me parse apo to json object pou mas esteile to webservice
            //pou exei mesa ola ta stoixeia me ti morfi px: 
            //["mathima":["diktya","programming"],"ID":["123","456"],"vathmoi":["6","8"]]
            
            //ara i parakatw entoli tha dwssei ["diktya","programming"]
            JSONArray Mathimata = (JSONArray)json.get("mathima");
            JSONArray ID = (JSONArray)json.get("ID");
            JSONArray Vathmoi = (JSONArray)json.get("vathmos");
            
            if(json.get("Error") != null) { //an to service steilei error...
                toShow = "Cannot find student "+AM;
            }
            else {
                toShow = "ID / Mathima / Vathmos\r\n\r\n"; //alliws kanoume tis analoges emfaniseis
                
                for(i=0; i<Mathimata.size(); i++){
                    toShow = toShow.concat(ID.get(i).toString()+" / "+Mathimata.get(i).toString()+" / "+Vathmoi.get(i).toString()+"\r\n");
                }
            }
            
            vathmoiTextArea.setText(toShow);
            System.out.println(toShow);
        }
          
        if (e.getSource() == userInsertButton) { //case User Insert
            if (role.equals("1")){ //elenxei an einai o administrator aftos pou kanei to insert
                UserInsert user_insert = new UserInsert();  
                JSONObject json = new JSONObject();
                try {
                    //kaloume ti synartisi uInsert pou kanei request sto webservice gia eisagwgi neou xristi
                    json = user_insert.uInsert(usernameTextField.getText(),passwordTextField.getText(), roleTextField.getText());

                    if(json.get("Error") != null) { //an to web service esteile error...
                        UinsertInfo.setText("User "+usernameTextField.getText()+" already exists.");
                    }
                    else { //alliws exoume success
                        UinsertInfo.setText("Successfully added user "+usernameTextField.getText()+".");
                    }

                } 
                catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                } 
                catch (ParseException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else { //an den einai o admin, tote den epitrepei na ginei eisagwgi neou user
                UinsertInfo.setText("NO ADMIN PRIVILEDGES");
            }
        }     
        
        if (e.getSource() == studentInsertButton) { //case Student Insert
            //to idio me to user insert mono pou mporei kai i grammateia na kanei insert
            //kai oxi mono o administrator
            StudentInsert student_insert = new StudentInsert();  
            JSONObject json = new JSONObject();
            try {
                json = student_insert.sInsert(amTextField.getText(),firstnameTextField.getText(), lastnameTextField.getText(), semesterTextField.getText());

                if(json.get("Error") != null) {
                    studentInfoTextArea.setText("Student "+amTextField.getText()+" already exists.");
                }
                else {
                    studentInfoTextArea.setText("Successfully added student "+amTextField.getText()+".");
                }
            } 
            catch (IOException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (ParseException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        if (e.getSource() == vathmosButton) { //case insert/update vathmou
            UpdateVathmoi update_vathmoi = new UpdateVathmoi();  
            JSONObject json = new JSONObject();
            JSONArray errors = new JSONArray();
            try {
                //request sto webservice gia eisagwgi/tropopoiisi vathmou
                json = update_vathmoi.uVathmoi(idTextField.getText(),amvathmouTextField.getText(), vathmosTextField.getText());
                
                if(json.get("Error") != null) { //exoume error
                    errors = (JSONArray)json.get("Error");
                    degreeTextArea.setText("Error. \r\n\r\n");
                    //yparxoun 2 lathoi pou mporei na kanei o xristis, eite na dwsei lathos
                    //ID, eite lathos AM, se kathe periptwsi emfanizei to antistoixo lathos
                    for(i=0; i<errors.size(); i++){ 
                        if(errors.get(i).equals("11")) {
                            degreeTextArea.append("Invalid student AM\r\n");
                        }
                        else if(errors.get(i).equals("12")) {
                            degreeTextArea.append("Invalid lesson ID\r\n");
                        }
                    }
                }
                else { //alliws success
                    degreeTextArea.setText("Successfully added degree.");
                }

            } 
            catch (IOException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (ParseException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }

    public static void openActions(String rolez) { //anoigei to panel me tis epiloges
       new Menu(rolez).setVisible(true); 
    }
    
    //emfanisi Jframes gia show info interface
    public void showInfoInterface(boolean state){ 
        showInfoTextField.setVisible(state);
        showInfoButton.setVisible(state);
        vathmoiTextArea.setVisible(state);            
    }
    
    //emfanisi Jframes gia student insert interface
    public void studentInsertInterface(boolean state) {
        amTextField.setVisible(state);
        firstnameTextField.setVisible(state);
        lastnameTextField.setVisible(state);
        semesterTextField.setVisible(state);
        studentInfoTextArea.setVisible(state);
        studentInsertButton.setVisible(state);
        amLabel.setVisible(state);
        firstnameLabel.setVisible(state);
        lastnameLabel.setVisible(state);
        semesterLabel.setVisible(state);
    }
    
    //emfanisi Jframes gia user insert interface
    public void userInsertInterface(boolean state) {
        userInsertButton.setVisible(state);
        usernameTextField.setVisible(state);
        passwordTextField.setVisible(state);
        roleTextField.setVisible(state);
        uLabel.setVisible(state);
        pLabel.setVisible(state);
        rLabel.setVisible(state);
        UinsertInfo.setVisible(state);
    }
    
    //emfanisi Jframes gia eisagwgi/tropoiisi vathmou interface
    public void setDegreesInterface(boolean state) {
        vathmosButton.setVisible(state);
        idLabel.setVisible(state);
        idTextField.setVisible(state);
        amvathmouLabel.setVisible(state);
        amvathmouTextField.setVisible(state);
        vathmosLabel.setVisible(state);
        vathmosTextField.setVisible(state);
        degreeTextArea.setVisible(state);
    }
}
