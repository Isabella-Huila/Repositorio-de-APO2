package model;

public class Enemy {
    private String name;
    private EnemyType type;
    private int decreasing;
    private int increasing;
    private int positionX;
    private int positionY; 

    public Enemy(String name, int typeNumber, int decreasing, int increasing, int positionX, int positionY) {
        this.name = name;
        this.decreasing = decreasing;
        this.increasing = increasing;
        this.positionX= positionX;
        this.positionY= positionY;

        switch(typeNumber){
            case 1:
            type = EnemyType.OGRE;
            break;
            case 2:
            type = EnemyType.BOSS;
            break;
            case 3: 
            type = EnemyType.ABSTRACT;
            break;

            case 4:
            type= EnemyType.MAGIC; 
            break;        
        }
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getDecreasing() {
        return decreasing;
    }
    public void setDecreasing(int decreasing) {
        this.decreasing = decreasing;
    }
    public int getIncreasing() {
        return increasing;
    }
    public void setIncreasing(int increasing) {
        this.increasing = increasing;
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

    
    public int calculateConsonants(){
        int consonants =0;
        for(int i=0; i< name.length(); i++){
            if(name.charAt(i)!='a'&& name.charAt(i) != 'e' && name.charAt(i) != 'i' && name.charAt(i) != 'o' && name.charAt(i) != 'u'){
                consonants ++;
            }
        }
        return consonants;
    }



    @Override
    public String toString() {
        return "Enemy [name=" + name + 
        " type=" + type + 
        " decreasing=" + decreasing +
        " increasing=" + increasing
         + ", positionX=" + positionX + 
        " positionY=" + positionY + "]";

    

    

     
}
}
