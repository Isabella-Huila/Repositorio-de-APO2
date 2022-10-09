package model;
import java.util.Random;


public class VideoGameController {
    public static Random random = new Random();

    private String name;
    private int scrResolutionX;
    private int scrResolutionY; 

    private Level[] levels;
    private Player[] players;

    public VideoGameController(String name){
        this.name = name;
        levels = new Level[10];
        players = new Player[20];
        scrResolutionX = 1280;
        scrResolutionY= 700; 
        fillLevels(); 
    }

    
    public void fillLevels(){
        int score=11;
        int level =1; 
        for(int i=0; i< levels.length; i++){
            if(i<levels.length-1){
                levels[i]= new Level(String.valueOf(level),score);
                score*=2;
                level++;

            }else if(i==levels.length-1){
                score=Integer.MAX_VALUE;
                levels[i]= new Level(String.valueOf(level),score);
            }
        }
    }

    /**
     * @param nickName
     * @param name
     * @return
     */
    public String addPlayer(String nickName, String name){
        String msg = "Player added succesfully";
        Player cholado= searchPlayer(nickName);
        if(cholado != null){
            msg ="The player already exists";
        }else{
            cholado= new Player(nickName, name, levels[0]);
            boolean dispo = isAvailablePlayer();
            if(dispo){
                boolean added = false;
                for(int i=0; i< players.length && !added; i++){
                    if(players[i]== null){
                        players[i]=cholado;
                        added=true;
                    }
                }
            }else{
                msg = "The game is full";
            }

        }

        return msg;

    }

    /**
     * @param nickName
     * @return
     */
    public Player searchPlayer(String nickName){
        Player cholado= null;
        boolean find = false;
        if(players != null){
            for (int i=0; i<players.length && !find; i++){
                if(players[i] !=null && players[i].getNickName().equals(nickName)){
                    cholado = players[i];
                    find=true;
                }
            }
        }
        return cholado; 
    }


    public boolean isAvailablePlayer(){
        boolean dispo = false;
        for(int i=0; i< players.length && !dispo; i++){
            if(players[i]==null){
                dispo=true; 
            }
        }
        return dispo;
    }

    public int generatePosition(int x){
        int y; 
        y=random.nextInt(x+1);
        return y; 
    }

    /**
     * @param name
     * @param type_Number
     * @param decreasing
     * @param increasing
     * @param levelName
     * @return
     */
    public String addEnemy(String name, int type_Number, int decreasing, int increasing, String levelName){
        String msg="";
        int position_X= generatePosition(scrResolutionX);
        int position_Y= generatePosition(scrResolutionY);
        Level cholado = searchLevel(levelName);

        if(cholado != null){
            msg = cholado.addEnemy(name, type_Number, decreasing, increasing, position_X, position_Y);
        }else{
            msg= "The level does not exist";
        }

        return msg; 

    }

    /**
     * @param name
     * @return
     */
    public Level searchLevel(String name){
        Level cholado = null;
        boolean find = false; 
        for ( int i = 0; i< levels.length && !find; i ++){
            if(levels[i] !=null && levels[i].getNumberId().equals(name)){
                cholado=levels[i];
                find= true; 
            }
        }
        return cholado; 
    }


    /**
     * @param name
     * @param url
     * @param add_Score
     * @param levelName
     * @param treasureAmount
     * @return
     */
    public String addTreasure( String name, String url, int add_Score, String levelName, int treasureAmount){
        String msg = "";
        Level cholado = searchLevel(levelName);
        if( cholado != null){
            boolean added = cholado.isAvailableAmount(treasureAmount);
            if(added){
                do{
                    int position_X = generatePosition(scrResolutionX);
                    int position_Y = generatePosition(scrResolutionY);

                    
                 msg= cholado.addTreasure(name, url, add_Score, position_X, position_Y);
                 treasureAmount--;
                }while(treasureAmount>0);
                 
            }else{
                msg = "The amount of treasure is not avaible, the level does not exist ";
            }
            
           
        }
        return msg; 
         
    }

    /**
     * @param nickName
     * @param initialScore
     * @return
     */
    public String changePlayerScore(String nickName, int initialScore ){
        String msg= " Score changed succesfully"; 
        Player cholado= searchPlayer(nickName);
        if( cholado !=null){
            cholado.setInitialScore(initialScore);

        } else{
            msg ="The player does not exist";

        }
        return msg; 


    }

    /**
     * @param nickName
     * @return
     */
    public String changePlayerLevel(String nickName){
        String msg = "Level changed succesfully";
        Player cholado = searchPlayer(nickName); 
        if( cholado != null){
            Level level= cholado.getActualLevel();
            int initialScore = cholado. getInitialScore();
            if(level.getScore()<= initialScore){
                int index= index(level);
                if(index<levels.length-1){
                    cholado.setActualLevel(levels[index+1]);
                    msg += "to"+ cholado.getActualLevel().getNumberId(); // tenia problema 
                }
            }else{
                if(level.getNumberId().equals("10")){ // tenia problema 
                    msg="The player is in last Level";

                }else{
                    msg="The player does not hava enough socore";
                    msg += "Mising"+(level.getScore()-initialScore)+"points to reach the next level ";
                }
            }
        }else{
            msg= "The player does not exist"; 
        }
        return msg; 
    }

    /**
     * @param level
     * @return
     */
    public int index(Level level){
        int index = -1;
        boolean find = false; 
        for( int i=0; i< levels.length && ! find; i++){
            if(levels[i] != null && levels[i].equals(level)){
                index = i;
                find = true; 
            }

        }
        return index; 
    }

    public String enemiesandtreasures(String levelName){
        String msg = "";
        Level cholado = searchLevel(levelName);
        if(cholado!= null){
            msg= cholado.showEnemies()+"\n"+cholado.showTreasuares();

        }else{
            msg ="The level doesn't exist";
        }
        return msg;

    }

    public String treasureAmount(String treasureName){
        int countTreasueres=0;
        String msg= "This treasure doesn't Exist";
        for(int i=0; i<levels.length;i++){
            if(levels[i] !=null){
                countTreasueres += levels[i].treasuresAmount(treasureName);

            }
        }
        if(countTreasueres>0){
            msg = "There are "+ countTreasueres +" "+treasureName+" in the game";
        }
        return msg;
    }

    public String totalEnemytype(int type ){
        int countEnmies =0;
        String msg =" There aren't enemies of this type";
        for(int i=0; i< levels.length; i++){
            if(levels[i] != null){
                countEnmies += levels[i].totalEnemiesType(type);

            }
        } if(countEnmies>0){
            msg = "There are" +  countEnmies+ "Enemies of this type in the game";
        }
        return msg;
    }

    public String maximunTreasure(){
        String msg = "There aren't treasures in the game";
        int max=0;
        for( int i =0; i< levels.length; i++){
            
        }
        return msg;
    }

    public String biggestEnemy(){
        String msg= "There are no enemies in the game";
        int  index=-1;
        Enemy enemy= null;
        for(int i=0; i<levels.length;i++){
            if( levels[i] != null){
                if(enemy== null){
                    enemy= levels[i].biggestEnemy();

                }else if(levels[i].biggestEnemy() != null){
                    if(levels[i].biggestEnemy().getIncreasing()>enemy.getIncreasing()){
                        enemy=levels[i].biggestEnemy();
                        index=i;
                    }
                }
            }
        }
        if( enemy != null){
            msg = "The biggest Enemy is " + enemy.getName() + " with " + enemy.getIncreasing() + "Points and is in the Level" + levels[index].getNumberId();
        }
        return msg; 
    }

    public String countConsonants(){
        String msg= "There are no consonants in the Enemies names";
        int count =0;
        for( int i=0; i< levels.length; i++){
            if(levels[i] !=null){
                count +=levels[i].countEnemyConsonants();
            }
        }
        if( count>0){
            msg =" there are "+ count + "consonants in the enemies name";

        }
        return msg; 
    }

    public String topFive(){
        String msg ="There are no players in the game";
        Player[] topFive = new Player[5];
        for( int i=0; i<players.length;i++){
            if(players[i] !=null){
                if(topFive[0]==null){
                    topFive[0]=players[i];
                }else{
                    for(int j=0; j<topFive.length;j++){
                        if( topFive[j]== null){
                            topFive[j]=players[i];
                            j= topFive.length;
                        }else if( topFive[j].getInitialScore()<players[i].getInitialScore()){
                            for(int k=topFive.length-1;k>j;j-- ){
                                topFive[k]=topFive[k-1];
                            }
                            topFive[j]=players[i];
                            j=topFive.length;
                        }

                    }
                }
            }
        }

        if( topFive[0] != null){
            msg= "the Top five Players are :\n";
            for( int i=0; i<topFive.length;i++){
                if(topFive[i]!=null){
                    msg += topFive[i].getNickName()+ " with "+ topFive[i].getInitialScore() + "points\n";
                }
            }
        }
        return msg;


    }

    public String mostCommonTreasure(){
        String msg =" there are no treasures in the game";
        String[] names = new String [totalTreasures()];
        int[] quantity = new int [totalTreasures()];

        return msg;
    }

    public int totalTreasures(){
        int total=0;
        for(int i=0; i<levels.length-1;i++){
            if(levels[i] != null){
                total += levels[i].countTreasueres();
            }
        }
        return total; 
    }
  



    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getScrResolutionX() {
        return scrResolutionX;
    }
    public void setScrResolutionX(int scrResolutionX) {
        this.scrResolutionX = scrResolutionX;
    }
    public int getScrResolutionY() {
        return scrResolutionY;
    }
    public void setScrResolutionY(int scrResolutionY) {
        this.scrResolutionY = scrResolutionY;
    }
    public Level[] getLevels() {
        return levels;
    }
    public void setLevels(Level[] levels) {
        this.levels = levels;
    }
    public Player[] getPlayers() {
        return players;
    }
    public void setPlayers(Player[] players) {
        this.players = players;
    }


    @Override
    public String toString() {
        return "VideoGameController [name=" + name + 
        "scrResolutionX=" + scrResolutionX + 
        " scrResolutionY=" + scrResolutionY + "]";
    }


    
    
}
