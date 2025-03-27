package movie.mainproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.IOException;

public class Search
{
    private Main main;
    @FXML
    private Label label1;

    public void setMain(Main main) {
        label1.setText(Main.pcname);
        this.main = main;
    }


    public void titleClick(ActionEvent actionEvent) throws IOException {
        main.title();
    }

    public void releaseYearClick(ActionEvent actionEvent) throws IOException {
        main.release();
    }

    public void genreClick(ActionEvent actionEvent) throws IOException {
        main.genre();
    }

    public void maxRevenueClick(ActionEvent actionEvent) throws IOException {
        main.maxRevenue();
    }

    public void mostRecentMoviesClick(ActionEvent actionEvent) throws IOException {
        main.recent();
    }

    public void topTenClick(ActionEvent actionEvent) throws IOException {
        main.topten();
    }

    public void runTimeClick(ActionEvent actionEvent) throws IOException {
        main.runtime();
    }

    public void homePageClick(ActionEvent actionEvent) throws IOException {
        main.homepage();
    }


    public void showAllMoviesClick(ActionEvent actionEvent) throws IOException {
        main.showall();
    }
}
