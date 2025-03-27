package movie.mainproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.LoginDTO;
import util.NetworkUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private Stage stage;
    private NetworkUtil networkUtil;
    public static List<Movie> pcMovie;
    public static String pcname;
    public static String fxmlname="noname";
    List<Movie> movieList= new ArrayList<>();
    TableViewController tableViewcontroller= new TableViewController();

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        connectToServer();
        showLoginPage();
    }

    private void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 44444;
        networkUtil = new NetworkUtil(serverAddress, serverPort);
        new ReadThread(this);
    }

    public void transfer() throws IOException {
        fxmlname="transfer";
        System.out.println("inside transfer");
        FXMLLoader fxmlLoader= new FXMLLoader(Main.class.getResource("transfer.fxml"));
        Parent root = fxmlLoader.load();
        TransferController transfercontroller= fxmlLoader.getController() ;
        transfercontroller.makeList(pcMovie);
        transfercontroller.setMain(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showLoginPage() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("loginn.fxml"));
        Parent root = loader.load();
        LoginController controller = loader.getController();
        controller.setMain(this);
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public void showHomePage(LoginDTO o) throws Exception {
        fxmlname="homepage";
        pcMovie = o.getMovieList();
        String totalprofit= functions.totalProfit();
        String moviecount= String.valueOf(pcMovie.size());
        pcname=o.getUserName();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("homePage.fxml"));
        Parent root = loader.load();
        HomePage controller = loader.getController();
        controller.Display(o.getUserName(),moviecount,totalprofit);
        controller.setMain(this);
        stage.setTitle("Home");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public void homepage() throws IOException {
        fxmlname="homepage";
        String totalprofit= functions.totalProfit();
        String moviecount= String.valueOf(pcMovie.size());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("homePage.fxml"));
        Parent root = loader.load();
        HomePage controller = loader.getController();
        controller.Display(pcname,moviecount,totalprofit);
        controller.setMain(this);
        stage.setTitle("Home");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public void search() throws IOException {
        fxmlname="search";
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("search.fxml"));
        Parent root = fxmlLoader.load();
        Search searchcontoller = fxmlLoader.getController();
        searchcontoller.setMain(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void release() throws IOException {
        fxmlname="release";
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("tableView.fxml"));
        Parent root = fxmlLoader.load();
        tableViewcontroller= fxmlLoader.getController() ;
        tableViewcontroller.display("Year Of Release");
        tableViewcontroller.setMain(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void genre() throws IOException {
        fxmlname="genre";
        FXMLLoader fxmlLoader= new FXMLLoader(Main.class.getResource("tableView.fxml"));
        Parent root = fxmlLoader.load();
        tableViewcontroller= fxmlLoader.getController() ;
        tableViewcontroller.display("Genre");
        tableViewcontroller.setMain(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void maxRevenue() throws IOException {
        fxmlname="revenue";
        FXMLLoader fxmlLoader= new FXMLLoader(Main.class.getResource("tableView2.fxml"));
        Parent root = fxmlLoader.load();
        TableViewController tableViewcontroller= fxmlLoader.getController() ;
        movieList=functions.searchMaximumRevenue();
        tableViewcontroller.makeList(movieList);
        tableViewcontroller.display2("Max revenue : ");
        tableViewcontroller.setMain(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void recent() throws IOException {
        fxmlname="recent";
        FXMLLoader fxmlLoader= new FXMLLoader(Main.class.getResource("tableView2.fxml"));
        Parent root = fxmlLoader.load();
        TableViewController tableViewcontroller= fxmlLoader.getController() ;
        movieList=functions.searchRecentMovies();
        tableViewcontroller.makeList(movieList);
        tableViewcontroller.display2("Most Recent Movies : ");
        tableViewcontroller.setMain(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void topten() throws IOException {
        fxmlname="topten";
        FXMLLoader fxmlLoader= new FXMLLoader(Main.class.getResource("tableView2.fxml"));
        Parent root = fxmlLoader.load();
        TableViewController tableViewcontroller= fxmlLoader.getController() ;
        movieList=functions.top10movies();
        tableViewcontroller.makeList(movieList);
        tableViewcontroller.display2("Top Ten Movies : ");
        tableViewcontroller.setMain(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void runtime() throws IOException {
        fxmlname="runtime";
        FXMLLoader fxmlLoader= new FXMLLoader(Main.class.getResource("tableView.fxml"));
        Parent root = fxmlLoader.load();
        tableViewcontroller= fxmlLoader.getController() ;
        tableViewcontroller.display("Run Time");
        tableViewcontroller.setMain(this);
        tableViewcontroller.setPrompt();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void showall() throws IOException {
        fxmlname="showall";
        FXMLLoader fxmlLoader= new FXMLLoader(Main.class.getResource("tableView2.fxml"));
        Parent root = fxmlLoader.load();
        TableViewController tableViewcontroller= fxmlLoader.getController() ;
        tableViewcontroller.makeList(Main.pcMovie);
        tableViewcontroller.display2("All Movies : ");
        tableViewcontroller.setMain(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void title() throws IOException {
        fxmlname="title";
        FXMLLoader fxmlLoader= new FXMLLoader(Main.class.getResource("tableView.fxml"));
        Parent root = fxmlLoader.load();
        tableViewcontroller = fxmlLoader.getController() ;
        tableViewcontroller.display("Title");
        tableViewcontroller.setMain(this);
        //stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void add() throws IOException {
        fxmlname="add";
        FXMLLoader fxmlLoader= new FXMLLoader(Main.class.getResource("add.fxml"));
        Parent root = fxmlLoader.load();
        Add add= fxmlLoader.getController();
        add.setMain(this);
        //stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void searchclick() throws IOException {
        fxmlname=fxmlname.trim();
        List<Movie> mlist= new ArrayList<>();
        if(fxmlname.equals("title"))
            mlist=functions.searchTitle(TableViewController.name);
        else if(fxmlname.equals("genre"))
            mlist=functions.searchGenre(TableViewController.name);
        else if(fxmlname.equals("runtime")){
            String[] str=tableViewcontroller.name.split("-");
            mlist=functions.searchRunningTime(Integer.parseInt(str[0]),Integer.parseInt(str[1]));}
        else if(fxmlname.equals("release"))
            mlist=functions.searchYear(Integer.parseInt(TableViewController.name));
        tableViewcontroller.makeList1(mlist);
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Information");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }
    public void showAlertcreate() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("The Name is Already Registered.");
        alert.setContentText("Give a new username to create a account.");
        alert.showAndWait();
    }
    public void showAlertreset() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("No Account Found");
        alert.setContentText("The username you provided is not correct.");
        alert.showAndWait();
    }
    public static String capitalizeWord(String str){
        String words[]=str.split("\\s");
        String cWord="";
        for(String w:words){
            String first=w.substring(0,1);
            String after=w.substring(1);
            cWord+=first.toUpperCase()+after.toLowerCase()+" ";
        }
        return cWord.trim();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
