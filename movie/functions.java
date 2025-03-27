package movie.mainproject;

import java.util.*;

public class functions {
    public static List<Movie> searchTitle(String title)
    {
        List<Movie> mList= new ArrayList();
        for (Movie m : Main.pcMovie) {
            if (m.getTitle().equalsIgnoreCase(title))
                mList.add(m);
        }
        return mList;
    }
    public static List<Movie> searchYear(int year)
    {
        List<Movie> mList= new ArrayList();
        for (Movie m : Main.pcMovie) {
            if (m.getYearOfRelease()==year)
                mList.add(m);
        }
        return mList;
    }
    public static List<Movie> searchGenre(String genre){
        List<Movie> mList= new ArrayList();
        if(genre=="")
            return mList;
        for (Movie m : Main.pcMovie) {
            for(int i=0;i<3;i++){
                if (m.getGenre()[i].equalsIgnoreCase(genre)) {
                    mList.add(m);
                }
            }
        }
        return mList;
    }


    public static List<Movie> searchRunningTime(int searchStart, int searchEnd) {
        List<Movie> mList= new ArrayList();
        for (Movie m : Main.pcMovie) {
            if (m.getRunningTime()>=searchStart && m.getRunningTime()<=searchEnd)
                mList.add(m);
        }
        return mList;
    }

    public static List<Movie> top10movies() {
        List<Movie> movieList1= new ArrayList();
        for(Movie m : Main.pcMovie)
        {
            movieList1.add(m);
        }
        Comparator<Movie> comp =new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return (int) (o2.getRevenue()-o2.getBudget()-o1.getRevenue()+o1.getBudget());
            }
        };
        Collections.sort(movieList1, comp);
        int i=0;
        List<Movie> mList= new ArrayList();
        for (Movie m : movieList1) {
            mList.add(m);
            i++;
            if(i==10)
                break;
        }
        return mList;
    }

    public static List<Movie> searchRecentMovies() {
        List<Movie> mList= new ArrayList();
        int i=0;
        for (Movie m : Main.pcMovie)
        {
            if(m.getYearOfRelease()>i)
                i=m.getYearOfRelease();
        }
        for (Movie m : Main.pcMovie) {
            if(i==m.getYearOfRelease())
                mList.add(m);
        }
        return mList;
    }

    public static List<Movie> searchMaximumRevenue() {
        List<Movie> mList= new ArrayList();
        long i=0;
        for (Movie m : Main.pcMovie)
        {
            if(m.getRevenue()>i)
                i=m.getRevenue();
        }
        for (Movie m : Main.pcMovie) {
            if(i==m.getRevenue())
                mList.add(m);
        }
        return mList;
    }

    public static long total(String name) {
        long total=0;
        int i=0;
        for (Movie m : Main.pcMovie)
        {
            if(m.getProductionCompany().equalsIgnoreCase(name))
            {total+=m.getRevenue()-m.getBudget();i++;}
        }
        if(i>0)
          return total;
        return -1000000000;
    }
    public static String totalProfit() {
        long total=0;
        int i=0;
        for (Movie m : Main.pcMovie)
        {
            total+=m.getRevenue()-m.getBudget();
        }
        return String.valueOf(total);
    }


    public static HashMap<String, Integer> info() {
        HashMap<String ,Integer> map =new HashMap<String, Integer>();
        for(Movie m: Main.pcMovie)
        {
            if(map.containsKey(m.getProductionCompany()))
                map.put(m.getProductionCompany(),map.get(m.getProductionCompany())+1);
            else
                map.put(m.getProductionCompany(),1);
        }
        return map;
    }
}
