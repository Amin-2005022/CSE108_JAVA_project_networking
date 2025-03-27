package movie.mainproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.LoginDTO;

import java.io.IOException;

import static java.lang.System.exit;


public class LoginController {

    private Main main;

    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button resetButton;

    @FXML
    private Button loginButton;

    @FXML
    void loginAction(ActionEvent event) {
        String userName = Main.capitalizeWord(userText.getText());
        String password = passwordText.getText();
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserName(userName);
        loginDTO.setPassword(password);
        loginDTO.setCommand("login");
        try {
            main.getNetworkUtil().write(loginDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void setMain(Main main) {
        this.main = main;
    }

    public void createClick(ActionEvent actionEvent) {
        String userName = Main.capitalizeWord(userText.getText());
        String password = passwordText.getText();
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserName(userName);
        loginDTO.setPassword(password);
        loginDTO.setCommand("create");
        try {
            main.getNetworkUtil().write(loginDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetClick(ActionEvent actionEvent) {
        String userName = Main.capitalizeWord(userText.getText());
        String password = passwordText.getText();
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserName(userName);
        loginDTO.setPassword(password);
        loginDTO.setCommand("reset");
        try {
            main.getNetworkUtil().write(loginDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void exitClick(ActionEvent actionEvent) {
        exit(0);
    }
}
