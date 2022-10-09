package model;

import javax.swing.text.html.HTMLDocument.RunElement;

public class Level {
    private String numberId;
    private int score;
    private LevelDificultad difficulty;

   

    private Treasure[] treasures;
    private Enemy [] enemies;


    public Level(String numberId, int score) {
        this.numberId = numberId;
        this.score = score;
        treasures = new Treasure[50];
        enemies = new Enemy[25];

    } 

    /**
     * Description: this method evaluates if the enemy has created 
     * the video game correctly, it also evaluates if those same
     *  monsters already exist within the level, what is returned is a message
     * @param name
     * @param type
     * @param decreasing
     * @param increasing
     * @param positionX
     * @param positionY
     * @return
     */
    public String addEnemy( String name, int type, int decreasing, int increasing, int positionX, int positionY) {
        String message = "Enemy added correctly :)";
        boolean added = false; 
        Enemy cholado = searchEnemy(name);
        if (cholado != null){
            message = " This enemy is already at this level";

        }
        else{
            cholado = new Enemy(name, type, decreasing, increasing, positionX, positionY );
            boolean existence = isAvailableEnemy();
            if (existence){
                for(int i=0; i< enemies.length&& !added; i++) {
                    if(enemies[i] == null){
                        enemies[i]= cholado;
                        added = true;
                    }
                }
            } else{
                message = "The level is already full";
            }
        }

        return message; 

    }

    /**
     * description: This method only looks for the enemy in an array
     * @param name
     * @return 
     */
    public Enemy searchEnemy(String name){
        Enemy cholado = null;
        boolean fud = false;
        for(int i=0; i < enemies.length && ! fud; i++){
            if(enemies[i]!= null && enemies [i].getName().equalsIgnoreCase(name)){
                cholado= enemies[i];
                fud= true;
            }
        }
        return cholado;
    }

    
    public boolean isAvailableEnemy(){
        boolean available = false; 
        for (int i=0; i< enemies.length; i++){
            if(enemies[i]==null){
                available=true;
            }
        }
        return available;
    }

    /**
     * description : this method checks to add a treasure. 
     * 
     * @param url
     * @param name
     * @param add_Score
     * @param positionX
     * @param positionY
     * @return
     */
    public String addTreasure(String url, String name, int add_Score, int positionX, int positionY){
        String message = " Treasure added succesfully ";
        boolean added = false;
        Treasure cholado = new Treasure( url, name, add_Score, positionX,positionY);
        for(int i=0; i < treasures.length && !added; i++){
            if(treasures[i]==null){
                treasures[i]=cholado;
                added=true; 
                
            }
        }
        return message;
    }

    public Treasure searchTreasure(String name){
        Treasure cholado = null;
        boolean fud = false; 
        for (int i=0; i< treasures.length && !fud; i++){
            if(treasures[i] != null && treasures[i].getName().equalsIgnoreCase(name)){
                cholado = treasures[i];
                fud = true; 
            }
        }
        return cholado; 
    }

    /**
     * @param aumount
     * @return
     */
    public boolean isAvailableAmount(int aumount){
        boolean available = false; 
        int count =0;
        for (int i=0; i< treasures.length;i++){
            if(treasures[i]==null){
                count++;
            }
        }
        if(count>= aumount){
            available = true; 
        }
        return available; 
    }

    public int sumTreasures(){
        int sum=0;
        for(int i=0; i<treasures.length;i++){
            if(treasures[i] != null){
                sum+=treasures[i].getAdd_Score();
            }
        }
        return sum; 
    }

    /**
     * description: Just add enemies.
     * @return sum 
     */
    public int sumEnemies(){
        int sum=0; 
        for(int i=0; i < enemies.length;i++){
            if(enemies[i]!=null){
                sum+=enemies[i].getIncreasing();

            }
        }
        return sum; 
    }

    /**
     * description: With respect to the sum of the treasures 
     * of enemies, it will be known at what level the player is
     * 
     */
    public void calculateDifficulty(){
        int sumTreasures = sumTreasures();
        int sumEnemies = sumEnemies();
        if(sumTreasures>sumEnemies){
            difficulty = LevelDificultad.EASY;

        }else if (sumTreasures<sumEnemies){
            difficulty= LevelDificultad.HARD; 
        }else{
            difficulty= LevelDificultad.MEDIUM;
        }
    }
    
    public String showEnemies() 
    {
        String message ="";
        int count = countEnemies();
        if(count ==0){
            message = "There aren't Enemies in this level";

        }else if(count ==1){
            message = "There is one enemy in this level";
            for( int i=0; i< enemies.length;i++){
                if(enemies[i]!=null){
                    message += enemies[i].getName();
                }
            }
        }else{
            message = "Enemies in this level: \n";
            for( int i=0; i < enemies.length; i++){
                if(enemies[i] != null){
                    message += enemies[i].getName()+ ",";
                }
            }
        }
        return message;
        
    }

    public int countEnemies(){
        int count=0;
        for( int i =0; i<enemies.length; i++){
            if(enemies[i]!=null){
                count ++;
            }
        }
        return count; 
    }

    public String showTreasuares(){
        String message ="";
        int count = countTreasueres();
        String[] names = treasuresNames();
        if(count=0){
            message = "There aren't no treasures in this level";

        }else if( count==1){
            message ="There is one treasure in this level :\n";
            for(int i=0; i< treasures.length;i++){
                if(treasures[i]!=null){
                    message += treasures[i].getName();

                }
            }
        }else{
            message = "Treasure in this level:\n";
            for(int i=0; i< names.length; i++){
                if(names[i]!=null){
                    message += names[i]+";";
                }
            }
            message  = message.substring(0,message.length()-2)+".";
        }
        return message;

    }

    public int countTreasueres(){
        int count=0; 
        for( int i=0; i< treasures.length;i++){
            if( treasures[i]!=null){
                count++;
            }
        }
        return count;
    }

    public int treasuresAmount(String name){
        int count=0;
        for( int i=0; i< treasures.length; i++){
            if( treasures[i]!=null && treasures[i].getName().equalsIgnoreCase(name)){
                count++;
            }
        }
        return count;


    }

    public int totalEnemiesType(int type){
        int count=0;
        EnemyType Etype= EnemyType.OGRE;
        switch(type){
            case 1:
                Etype = EnemyType.OGRE;
                break;
            case 2:
                Etype = EnemyType.BOSS;
                break;
            case 3: 
                Etype = EnemyType.ABSTRACT;
                break;
            case 4:
                Etype= EnemyType.MAGIC; 
                break;        
            }
            for( int i=0; i< enemies.length;i++){
                if( enemies[i]!=null && enemies[i].getType()==Etype){
                    count++;
                }
            }

        return count; 
    }

    public String[] treasuresNames(){
        int aumount= countTreasueres();
        String[] treasuresName = new String[amount];
        for( int i=0; i< treasures.length; i++){
            if(treasures[i]!=null){
                int nameFound =0;
                for( int j=0; j< treasuresName.length;j++){
                   if( treasures[i].getName().equalsIgnoreCase(treasuresNames[j])){
                    nameFound++;

                   }if(nameFound==0 && j== treasuresNames.length-1){
                    treasuresNames[i]= treasures[i].getName();
                   } 
                }
            }
            return treasuresNames; 
        }
    }

    public int[] treasuresNameAmount(){
        int amount=countTreasueres();
        int[] treasureAmount= new int[amount];
        for( int i=0; i< treasures.length; i++){
            if( treasures[i]!=null){
                for( int j=0; j< treasureAmount.length;i++){
                    if( treasures[i].getName().equalsIgnoreCase(treasuresNames()[j])){
                        treasureAmount[j]++;
                    }
                }
            }
            
        }
        return treasureAmount;
    }

    public Enemy biggestEnemy(){
        Enemy biggest = null;
        for( int i=0; i<enemies.length; i++){
            if(enemies[i]!=null){
                if(biggest==null){
                    biggest = enemies[i];
                }else if( enemies[i].getIncreasing()>biggest.getIncreasing()){
                    biggest = enemies[i];
                }
            }
        }
        return biggest;
    }

    public int countEnemyConsonants(){
        int count=0; 
        for( int i=0; i< enemies.length;i++){
            if(enemies[i]!=null){
                count+= enemies[i].calculateConsonants();
            }
        }
        return count; 


    }


    public String getNumberId() {
        return numberId;
    }

    public void setNumberId(String numberId) {
        this.numberId = numberId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Treasure[] getTreasures() {
        return treasures;
    }

    public void setTreasures(Treasure[] treasures) {
        this.treasures = treasures;
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public void setEnemies(Enemy[] enemies) {
        this.enemies = enemies;
    }
    public LevelDificultad getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(LevelDificultad difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Level [numberId=" + numberId + 
        " score=" + score + 
        " difficulty=" + difficulty + "]";
    }

  

    
    
    
}
