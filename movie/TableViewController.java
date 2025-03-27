package movie.mainproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TableViewController implements Initializable {
    Parent root;
    Stage stage;
    Scene scene;
    @FXML
    Label label1;
    @FXML
    Label label2;
    @FXML
    Label label3;
    @FXML
    Label label4;
    @FXML
    TextField textfield= new TextField();
    @FXML
    Button search= new Button();
    public List<Movie> movielist= new ArrayList<>();
    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private TableView<Movie> table;

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

    public void display(String name)
    {
        label2.setText(name+" : ");
        label1.setText(Main.pcname);
    }

    public void display2(String name)
    {
        label4.setText(name);
        label3.setText(Main.pcname);
    }
    public static String name;

    public void searchClick(ActionEvent actionEvent) throws IOException {
        name = textfield.getText();
        System.out.println(name);
        main.searchclick();
    }
    public void makeList(List<Movie> list){

        for(Movie m: list)
        {table.getItems().add(m);}
    }
    public void makeList1(List<Movie> list){
        table.getItems().clear();
        for(Movie m: list)
        {table.getItems().add(m);}
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
        main.search();
    }

    public void detailsClick(ActionEvent actionEvent) throws IOException {
        int row= table.getSelectionModel().getSelectedIndex();
        Movie m=table.getItems().get(row);
        FXMLLoader fxmlLoader= new FXMLLoader(Main.class.getResource("details.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        DetailsController detailsController= fxmlLoader.getController();
        detailsController.setAll(m,main);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setPrompt() {
        textfield.setPromptText("Enter Time period in Format : X-Y");
    }
}

