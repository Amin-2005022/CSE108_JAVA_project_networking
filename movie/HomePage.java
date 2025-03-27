package movie.mainproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.IOException;

public class HomePage {
    @FXML
    private Label label1,label2,label3;
    private Main main;
    public void Display(String s1, String s2, String s3)
    {
        label1.setText(s1);
        label2.setText("Total Movies : "+s2);
        label3.setText("Total Profit : "+s3);

    }

    public void searchClick(ActionEvent actionEvent) throws IOException {
        main.search();
    }

    public void addClick(ActionEvent actionEvent) throws IOException {
        main.add();
    }
    void setMain(Main main) {
        this.main = main;
    }

    public void transferClick(ActionEvent actionEvent) throws IOException {
        main.transfer();
    }

    public void logOutClick(ActionEvent actionEvent) throws Exception {
        String s=Main.pcname;
        try {
            main.getNetworkUtil().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        main.showLoginPage();
    }
}
