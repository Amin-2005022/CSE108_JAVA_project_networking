package movie.mainproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import util.Adder;
import java.io.IOException;

public class Add {

    public void setMain(Main main) {
        label1.setText(Main.pcname);
        this.main = main;
    }


    private Main main;
    @FXML
    private Label label1;

    @FXML
    private TextField budgetid;

    @FXML
    private TextField genre1id;

    @FXML
    private TextField genre2id;

    @FXML
    private TextField genre3id;

    @FXML
    private TextField revenueid;

    @FXML
    private TextField timeid;

    @FXML
    private TextField titleid;

    @FXML
    private TextField yearid;
    String[] movie= new String[10];

    @FXML
    void addClick(ActionEvent actionEvent) throws IOException {
        movie[0]= titleid.getText();
        movie[1]= yearid.getText();
        movie[2]= genre1id.getText();
        movie[3]="";
        movie[4]="";
        String g=genre2id.getText();
        if(!g.equals(""))
            movie[3]= g;
        g=genre3id.getText();
        if(!g.equals(""))
            movie[4]=g;
        movie[5]= timeid.getText();
        movie[7]= budgetid.getText();
        movie[8]= revenueid.getText();
        movie[6]= Main.pcname;
        Movie m= new Movie(movie);
        Adder adder=new Adder(Main.pcname,m);
        try {
            main.getNetworkUtil().write(adder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backClick(ActionEvent actionEvent) throws IOException {
        main.homepage();
    }
}
