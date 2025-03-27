package movie.mainproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import util.Transfer;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TransferController implements Initializable {
    String transferpc;
    private Main main;
    @FXML
    private Label label6;
    @FXML
    private TextField textfield5;
    @FXML
    private TableView<Movie> table3;
    @FXML
    private TableColumn<Movie, Long> budgetcolumn;

    @FXML
    private TableColumn<Movie, String> genrecolumn;

    @FXML
    private TableColumn<Movie, Long> revenuecolumn;

    @FXML
    private TableColumn<Movie, Integer> runningtimecolumn;

    @FXML
    private TableColumn<Movie, String> titlecolumn;

    @FXML
    private TableColumn<Movie, Integer> yearofreleasecolumn;

    void setMain(Main main) {
        this.main = main;
    }
    public void transferClick(ActionEvent actionEvent) {
        transferpc=Main.capitalizeWord(textfield5.getText());
        int row= table3.getSelectionModel().getSelectedIndex();
        Movie m=table3.getItems().get(row);
        Transfer transfer= new Transfer(Main.pcname, transferpc,m);
        try {
            main.getNetworkUtil().write(transfer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void makeList(List<Movie> list){
        label6.setText(Main.pcname);
        for(Movie m: list)
        {table3.getItems().add(m);}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titlecolumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        yearofreleasecolumn.setCellValueFactory(new PropertyValueFactory<>("yearOfRelease"));
        revenuecolumn.setCellValueFactory(new PropertyValueFactory<>("revenue"));
        budgetcolumn.setCellValueFactory(new PropertyValueFactory<>("budget"));
        genrecolumn.setCellValueFactory(new PropertyValueFactory<>("genree"));
        runningtimecolumn.setCellValueFactory(new PropertyValueFactory<>("runningTime"));
    }

    public void backClick(ActionEvent actionEvent) throws IOException {
        main.homepage();
    }
}
