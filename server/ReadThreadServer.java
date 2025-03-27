package server;

import util.Adder;
import util.LoginDTO;
import movie.mainproject.Movie;
import util.NetworkUtil;
import util.Transfer;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ReadThreadServer implements Runnable {
    private final Thread thr;
    private final NetworkUtil networkUtil;
    public HashMap<String, String> userMap;

    public ReadThreadServer(HashMap<String, String> map, NetworkUtil networkUtil) {
        this.userMap = map;
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        thr.start();
    }


    public void run() {
        try {
            while (true) {
                Object o = networkUtil.read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        loginDTO.setUserName(Server.capitalizeWord(loginDTO.getUserName()));
                        if((loginDTO.getCommand().equalsIgnoreCase("login")))
                        {Server.networkmap.put(loginDTO.getUserName(),this.networkUtil);
                        String password = userMap.get(loginDTO.getUserName());
                        loginDTO.setStatus(loginDTO.getPassword().equalsIgnoreCase(password));
                        List<Movie> list= Server.searchProductionCompany(loginDTO.getUserName());
                        loginDTO.setMovieList(list);
                        networkUtil.write(loginDTO);}
                        else if((loginDTO.getCommand().equalsIgnoreCase("create")))
                        {Server.networkmap.put(loginDTO.getUserName(),this.networkUtil);
                            if(userMap.containsKey(loginDTO.getUserName()))
                                loginDTO.setStatus(false);
                            else{
                            userMap.put(loginDTO.getUserName(), loginDTO.getPassword());
                            List<Movie> list= Server.searchProductionCompany(loginDTO.getUserName());
                            loginDTO.setMovieList(list);
                            loginDTO.setStatus(true);}
                            networkUtil.write(loginDTO);}
                        else if((loginDTO.getCommand().equalsIgnoreCase("reset")))
                        {Server.networkmap.put(loginDTO.getUserName(),this.networkUtil);
                            if(!(userMap.containsKey(loginDTO.getUserName())))
                                loginDTO.setStatus(false);
                            else{
                                userMap.put(loginDTO.getUserName(), loginDTO.getPassword());
                                List<Movie> list= Server.searchProductionCompany(loginDTO.getUserName());
                                loginDTO.setMovieList(list);
                                loginDTO.setStatus(true);}
                            networkUtil.write(loginDTO);
                        }
                    }

                    else if(o instanceof Transfer){
                        Transfer transfer=(Transfer) o;
                        String sender=transfer.getSenderName();
                        String receiver=transfer.getReceiverName();
                        Movie m=transfer.getMovie();
                        for(Movie n: Server.movieList)
                        {if(m.getTitle().equalsIgnoreCase(n.getTitle()))
                            n.setProductionCompany(receiver);}
                        Transfer t1= new Transfer(sender);
                        List<Movie> list1= Server.searchProductionCompany(sender);
                        t1.setList(list1);
                        networkUtil.write(t1);
                        Server.networkmap.get(sender).write(t1);
                        NetworkUtil networkUtil1 = Server.networkmap.get(receiver);
                        if(networkUtil1!=null){
                            Transfer t2 =new Transfer(receiver);
                            List<Movie> list2= Server.searchProductionCompany(receiver);
                            t2.setList(list2);
                            networkUtil1.write(t2);
                        }
                    }

                    else if (o instanceof Adder) {
                        Adder adder= (Adder) o;
                        Server.movieList.add(adder.getMovie());
                        List<Movie> list= Server.searchProductionCompany(adder.getName());
                        Adder newadder= new Adder(adder.getName(), list);
                        networkUtil.write(newadder);
                    }


                    else if (o instanceof String) {
                        String s= (String) o;
                        Server.networkmap.remove(s);
                        Server server=new Server();
                        server.fileWrite();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}