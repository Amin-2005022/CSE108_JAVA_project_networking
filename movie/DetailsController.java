package movie.mainproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

public class DetailsController {
    private Main main;
    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private Label label5;

    @FXML
    private Label label6;

    @FXML
    private ImageView posterid;
    @FXML
    ImageView detailsid=new ImageView();

    public void setAll(Movie m,Main main){
        Main.fxmlname+=" ";
        label1.setText("Title : "+m.getTitle());
        label2.setText("Year of Release : "+m.getYearOfRelease());
        label3.setText("Genre : "+m.getGenree());
        label4.setText("Running Time : "+m.getRunningTime());
        label5.setText("Budget : "+m.getBudget());
        label6.setText("Revenue : "+m.getRevenue());

        String str=m.getTitle();
        String str1,str2;
        str = str.replaceAll("[^a-zA-Z0-9]", "");
        str2="photos/"+str+"poster.jpg";
        str1="photos/"+str+"backdrop.jpg";
        System.out.println(str);
        try
        {Image image1= new Image(Objects.requireNonNull(getClass().getResourceAsStream(str1)));
        detailsid.setImage(image1);}
        catch(Exception e){
            Image image1= new Image(Objects.requireNonNull(getClass().getResourceAsStream("photos/defaultbackdrop.jpg")));
            detailsid.setImage(image1);
        }
        try{
        Image image2= new Image(Objects.requireNonNull(getClass().getResourceAsStream(str2)));
        posterid.setImage(image2);}catch(Exception e)
        {
            Image image2= new Image(Objects.requireNonNull(getClass().getResourceAsStream("photos/defaultposter.jpg")));
            posterid.setImage(image2);
        }
        this.main=main;
    }

    public void closeClick(ActionEvent actionEvent) throws IOException {
        if(Main.fxmlname.equals("title "))
            {main.title();main.searchclick();}
        else if(Main.fxmlname.equals("genre "))
            {main.genre();main.searchclick();}
        else if(Main.fxmlname.equals("revenue "))
            main.maxRevenue();
        else if(Main.fxmlname.equals("runtime "))
            {main.runtime();main.searchclick();}
        else if(Main.fxmlname.equals("topten "))
            main.topten();
        else if(Main.fxmlname.equals("recent "))
            main.recent();
        else if(Main.fxmlname.equals("showall "))
            main.showall();
        else if(Main.fxmlname.equals("release "))
            {main.release();main.searchclick();}
    }
}
