package com.test.sortingproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.paint.*;
import java.util.ArrayList;
import java.sql.*;
import javafx.beans.value.*;
/**
 * FXML Controller class
 *
 * @author anzo
 */
public class MailController implements Initializable {
    
    public enum SortBy{SENDER, DATE, TITLE};
    
    ArrayList<Mail> mails = new ArrayList<>();
    
    SortBy currSort;
    
    @FXML
    Label senderLabel;
    
    @FXML
    Label dateLabel;
    
    @FXML
    Label titleLabel;
    
    @FXML
    ListView mainMailView;
    
    @FXML
    TextArea mailMessageBox;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SortByDate();
        mails = GetMails();
        SortReload();
        mainMailView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() 
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
            {
                System.out.println("ListView selection changed from oldValue = " + oldValue + " to newValue = " + newValue);
                mailMessageBox.textProperty().set(mails.get(mainMailView.getSelectionModel().getSelectedIndex()).message);
            }
        });

    }
    
    @FXML
    void SortByDate()
    {
        currSort = SortBy.DATE;
        dateLabel.textFillProperty().set(Color.RED);
        senderLabel.textFillProperty().set(Color.BLACK);
        titleLabel.textFillProperty().set(Color.BLACK);
        SortReload();
    }
    @FXML
    void SortBySender()
    {
        currSort = SortBy.SENDER;
        dateLabel.textFillProperty().set(Color.BLACK);
        senderLabel.textFillProperty().set(Color.RED);
        titleLabel.textFillProperty().set(Color.BLACK);
        SortReload();
    }
    @FXML
    void SortByTitle()
    {
        currSort = SortBy.TITLE;
        dateLabel.textFillProperty().set(Color.BLACK);
        senderLabel.textFillProperty().set(Color.BLACK);
        titleLabel.textFillProperty().set(Color.RED);
        SortReload();
    }
    @FXML
    void CheckMail()
    {
        mailMessageBox.textProperty().set(mails.get(mainMailView.getFocusModel().getFocusedIndex()).message);
    }
    void SortReload()
    {
        switch(currSort)
        {
            case TITLE:
            {
                int c = 0;
                int diff = 0;
                while(true)
                {
                    if(c+1 >= mails.size())
                    {
                        c = 0;
                        if(diff == 0)
                        {
                            break;
                        }
                        diff=0;
                    }
                    if((int)mails.get(c).title.charAt(0) > (int)mails.get(c+1).title.charAt(0))
                    {
                        Mail m = new Mail(mails.get(c+1));
                        mails.set(c+1, new Mail(mails.get(c)));
                        mails.set(c, m);
                        diff++;
                    }
                    c++;
                }
                break;
            }
            case DATE:
            {
                int c = 0;
                int diff = 0;
                while(true)
                {
                    if(c+1 >= mails.size())
                    {
                        c = 0;
                        if(diff == 0)
                        {
                            break;
                        }
                        diff=0;
                    }
                    if((int)mails.get(c).from.charAt(0) > (int)mails.get(c+1).from.charAt(0))
                    {
                        Mail m = new Mail(mails.get(c+1));
                        mails.set(c+1, new Mail(mails.get(c)));
                        mails.set(c, m);
                        diff++;
                    }
                    c++;
                }
                break;
            }
            case SENDER:
            {
                int c = 0;
                int diff = 0;
                while(true)
                {
                    if(c+1 >= mails.size())
                    {
                        c = 0;
                        if(diff == 0)
                        {
                            break;
                        }
                        diff=0;
                    }
                    if(mails.get(c).sendTime.isAfter(mails.get(c+1).sendTime))
                    {
                        Mail m = new Mail(mails.get(c+1));
                        mails.set(c+1, new Mail(mails.get(c)));
                        mails.set(c, m);
                        diff++;
                    }
                    c++;
                }
                break;
            }
        }
        mainMailView.getItems().clear();
        for(int i = 0; i < mails.size(); i++)
        {
            mainMailView.getItems().add(mails.get(0).CreateString());
        }
    }
    
    ArrayList<Mail> GetMails()
    {
        ArrayList<Mail> temp = new ArrayList<Mail>();
        
        temp = DBManager.INSTANCE.RetreiveMails(Holder.INSTANCE.username);
        
        return temp;
    }
    
    boolean SendMail(Mail m)
    {
        return DBManager.INSTANCE.AddMail(m);
    }
    
}
