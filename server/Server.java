package server;

import movie.mainproject.Movie;
import util.NetworkUtil;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server {

    private ServerSocket serverSocket;
    public HashMap<String, String> userMap;
    public static HashMap<String, NetworkUtil> networkmap= new HashMap<>();
    private static final String FILE_NAME = "movies.txt";
    String movies[]= new String[10];
    public static List<Movie> movieList= new ArrayList<>();

    Server(){
        userMap = new HashMap<>();
        for(Movie m: movieList)
        {
            userMap.put(m.getProductionCompany(),m.getProductionCompany());
        }

        try {
            serverSocket = new ServerSocket(44444);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        new ReadThreadServer(userMap, networkUtil);
    }
    public static List<Movie> searchProductionCompany(String searchProductionCompany) {
        List<Movie> mList= new ArrayList();
        for (Movie m : movieList) {
            if (m.getProductionCompany().equalsIgnoreCase(searchProductionCompany)) {
                mList.add(m);
            }
        }
        return mList;
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
    public void fileWrite() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));
        for(Movie m: movieList){
            bw.write(m.getTitle()+","+m.getYearOfRelease()+",");
            for(int i=0;i<3;i++)
                bw.write(m.getGenre()[i]+",");
            bw.write(m.getRunningTime()+","+m.getProductionCompany()+","+m.getBudget()+","+m.getRevenue()+"\n");
        }
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String[] movies = line.split(",");
            Movie m = new Movie(movies);
            movieList.add(m);
        }
        br.close();
        new Server();
    }
}
