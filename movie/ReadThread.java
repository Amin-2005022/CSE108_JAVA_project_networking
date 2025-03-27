package movie.mainproject;

import javafx.application.Platform;
import util.Adder;
import util.LoginDTO;
import util.Transfer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadThread implements Runnable {
    private final Thread thr;
    private final Main main;

    public ReadThread(Main main) {
        this.main = main;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = main.getNetworkUtil().read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        System.out.println(loginDTO.getUserName());
                        System.out.println(loginDTO.isStatus());
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (loginDTO.isStatus()) {
                                    try {
                                        main.showHomePage(loginDTO);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    if(loginDTO.getCommand().equalsIgnoreCase("login"))
                                        main.showAlert();
                                    else if(loginDTO.getCommand().equalsIgnoreCase("create"))
                                        main.showAlertcreate();
                                    else if(loginDTO.getCommand().equalsIgnoreCase("reset"))
                                        main.showAlertreset();
                                }

                            }
                        });
                    }
                    else if (o instanceof Transfer) {
                        List<Movie> list= new ArrayList<>();
                        Transfer t= (Transfer)o;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Main.pcMovie = t.getList();
                                    if(Main.fxmlname.equals("transfer"))
                                        main.transfer();
                                    else if(Main.fxmlname.equals("homepage"))
                                        main.homepage();
                                    else if(Main.fxmlname.equals("add"));
                                    else if(Main.fxmlname.equals("title"))
                                        main.searchclick();
                                    else if(Main.fxmlname.equals("genre"))
                                        main.searchclick();
                                    else if(Main.fxmlname.equals("revenue"))
                                        main.maxRevenue();
                                    else if(Main.fxmlname.equals("runtime"))
                                        main.searchclick();
                                    else if(Main.fxmlname.equals("topten"))
                                        main.topten();
                                    else if(Main.fxmlname.equals("recent"))
                                        main.recent();
                                    else if(Main.fxmlname.equals("showall"))
                                        main.showall();
                                    else if(Main.fxmlname.equals("release"))
                                        main.searchclick();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    else if (o instanceof Adder) {
                        Adder adder = (Adder) o;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Main.pcMovie=adder.getMovielist();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                try {
                                    main.homepage();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });
                    }

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                main.getNetworkUtil().closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}