package movie.mainproject;


import java.io.Serializable;


public class Movie implements Serializable {
    private String title;
    private int yearOfRelease;
    private String[] genre= new String[3];
    private int runningTime;
    private String productionCompany;
    private long budget;
    private long revenue;
    private String genree;

    public Movie(String[] movie)
    {
        title=movie[0];
        yearOfRelease= Integer.parseInt(movie[1]);
        for(int i=0;i<3;i++)
            genre[i]=movie[i+2];
        runningTime= Integer.parseInt(movie[5]);
        productionCompany=movie[6];
        budget= Long.parseLong(movie[7]);
        revenue= Long.parseLong(movie[8]);
        genree=genre[0];
        if(!genre[1].equals(""))
            genree+=", "+genre[1];
        if(!genre[2].equals(""))
            genree+=", "+genre[2];
    }
    public void showDetails()
    {
        System.out.print("\nTitle: "+ title+ "\nYear of release: "+ yearOfRelease+ "\nGenre: "+genre[0]);
        if(genre[1]!="")
            System.out.print(", "+genre[1]);
        if(genre[2]!="")
            System.out.print(", "+genre[2]);
        System.out.println( "\nRunning time: "+ runningTime+"\nProduction Company: "+productionCompany+"\nBudget: "+ budget+"\nrevenue: "+revenue);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String[] getGenre() {
        return genre;
    }

    public String getGenree(){
        return genree;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }
}
