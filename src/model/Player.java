package model;

public class Player {

    //Atributos 
    private String nickName;
    private String name; 
    private int initialScore;
    private int liveNumber;
    private Level actualLevel; 

    
    public Player(String name, String nickName, Level actuaLevel) {
        this.nickName = nickName;
        this.name = name;
        this.initialScore = 10;
        this.liveNumber = 5;
        this.actualLevel= Level; 
        
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInitialScore() {
        return initialScore;
    }

    public void setInitialScore(int initialScore) {
        this.initialScore = initialScore;
    }

    public int getLiveNumber() {
        return liveNumber;
    }

    public void setLiveNumber(int liveNumber) {
        this.liveNumber = liveNumber;
    }

    public Level getActualLevel() {
        return actualLevel;
    }

    public void setActualLevel(Level actualLevel) {
        this.actualLevel = actualLevel;
    }

    @Override
    public String toString() {
        return "Player info [nickName=" + nickName + 
        " name=" + name + 
        "  Score =" + initialScore +
        " Live Number=" + liveNumber + 
        " Actual Level=" + actualLevel.getNumberId() + "]";
    }

    



    


    
}
