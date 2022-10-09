package model;

public class Treasure {

    // ATRIBUTOS 
    private String url;
    private String name;
    private int add_Score;
    private int positionX;
    private int positionY;

    
    public Treasure(String name, String url, int add_Score, int positionX, int positionY) {
        this.url = url;
        this.name = name;
        this.add_Score = add_Score;
        this.positionX = positionX;
        this.positionY = positionY;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getAdd_Score() {
        return add_Score;
    }


    public void setAdd_Score(int add_Score) {
        this.add_Score = add_Score;
    }


    public int getPositionX() {
        return positionX;
    }


    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }


    public int getPositionY() {
        return positionY;
    }


    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }


    @Override
    public String toString() {
        return "Treasure info"+ 
        "[Url=" + url + 
        " Name=" + name + 
        " Add Score=" + add_Score + 
        " Position  in X=" + positionX + 
        " Position in Y=" + positionY + "]";
    }


   
}
