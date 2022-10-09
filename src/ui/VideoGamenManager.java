package ui;

import java.util.Scanner;
import model.VideoGameController;

public class VideoGamenManager {

    public static Scanner sc = new Scanner(System.in);
    public static VideoGameController videoGC;

    public static void main(String[] args){

        System.out.println("\n Welcome to the Game"); 
        System.out.println("Please tell us what you want to call the game?"); 
        String name = sc.nextLine();
        videoGC=new VideoGameController(name);
        
        int option; 
        do{

            System.out.println("1. Create Player\n2. Create a new enemy and assign it to a level");
            System.out.println("3. Create a new treasure and assign it to a level\n4. Modify the score af a player\n5. increase the level of a player\n6.Show the treasures and enemies of a level");
            System.out.println("7.Show the quantity of an especific treasure in all levels\n8.Show the quantity of an especific enemy type in all levels\n9.Show the name of the most common treasure in all levels");
            System.out.println("10.Show the enemy with the highest score and its level\n11.Show the quantity of consonants in the name of all the enemies\n12.Show a top 5 of the players with the highest score");
            System.out.println("0.Exit");
            System.out.println("Type a number to select an option");
            option = sc.nextInt();

            switch(option){
                case (1): 
                 videoGC.addPlayer();
                  break; 
                case (2) :
                 videoGC.addEnemy();
                 break; 
                case (3) : 
                 videoGC.addTreasure();
                 break;
                case (4) :
                 videoGC.changePlayerScore();
                 break;
                case(5): 
                 videoGC.changePlayerLevel();
                 break; 
                case (6):
                 videoGC.showTreasuresAndEnemies();
                 break; 
                case (7):
                 videoGC.showTreasuaresQuantity();
                 break;
                case (8):
                 videoGC.showEnmiesType();
                 break;

                 case (9):
                    break;

                 case (10):
                 videoGC.biggestEnemy();
                    break;
                case (11):
                 videoGC.countConsonants();
                 break;
                 case (12):
                 videoGC.topFive();
                 break;
                 case (0):
                 System.out.println("Thank you, we hope our app has been helpful");
                 break;
                 default:
                 System.out.println("Please enter a valid option\n");
                    break;





            }
          

        }while(option != 0);
        
    }

    /**
     * description: this method asks the player for the name 
     * and nickname to be registered in the system
     */
    public static void addPlayer(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Name of the Player");
        String name = sc.nextLine();
        System.out.println("Nickname of the Player");
        String nickName = sc.nextLine();
        String message = videoGC.addPlayer(name, nickName);
        System.out.println(message);

    }

    /**
     * description: This method registers an enemy within it there is an enmuracion 
     * to know what type of enemy is the enemy that is going to register
     */
    public static void addEnemy(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Name of Enemy");
        String name= sc.nextLine();
        System.out.println("Please enter a number to select the type of the enemy");
        int type;
        do{
            System.out.println("1.Ogre\n2.Boss\n3.Abstrac\n4.Magic");
            type = sc.nextInt();
            if(type<1 || type>4){
                System.out.println("Invalid option enter again");


            }

        }while(type<1 || type>4); 
        System.out.println("Please enter the Score that will be decreasing to player ");
        int decreasing = sc.nextInt();
        System.out.println("Please enter the score that will be increasing  to the player");
        int increasing = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter the level in wich the enemy will be added");
        String level = sc.nextLine();
        String message = videoGC.addEnemy(name, type, decreasing, increasing, level);
        System.out.println(message);


    }

    /**
     * description: this method adds a treasure, 
     * you are prompted for the data to enter
     * 
     */
    public static void addTreasure(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the name of the treasure");
        String name = sc.nextLine();
        System.out.println("Please enter the URL of the image of the treasure");
        String url = sc.nextLine();
        System.out.println("Please enter the score that will be added to the player");
        int add_Score = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter the level in which the treasure will be added");
        String level = sc.nextLine();
        System.out.println("Please enter the amount of treasures that will be added to the level");
        System.out.println("please do not enter numbers smaller than one");
        int amount;
        do{
            amount = sc.nextInt();
            if(amount<1){
                System.out.println("Please enter a valid number");

            }
        }while(amount<1);
        
        sc.nextLine();
        String message = videoGC.addTreasure(name, url, add_Score, level,amount);
        System.out.println(message);
    }

    /**
     * description: what this method does is change the player's score according 
     * to the nickname what it does is look for it and know if that player exists
     */
    public static void changePlayerScore(){
        int score=0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the nickname of the player");
        String nickName = sc.nextLine();
        do{
            System.out.println("Please enter the new score of the player");
            score = sc.nextInt();
            if (score<0){
                System.out.println("The score can't be negative");
            }
        }while(score<0);

        String message = videoGC.changePlayerScore(nickName, score);
        System.out.println(message);
    }

    /**
     * description: what this method does is that by knowing his nickname 
     * you can increase the level of the player
     * 
     */
    public static void changePlayerLevel(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the nickname of the player");
        String nickName = sc.nextLine();
        String message = videoGC.changePlayerLevel(nickName);
        System.out.println(message);
    }

    public void showTreasuresAndEnemies(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the level in which the treasures and enemies will be shown");
        String level = sc.nextLine();
        String message = videoGC.enemiesandtreasures(level);
        System.out.println(message);
        
    }

    public void showTreasuaresQuantity(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the name of the treasure");
        String name= sc.nextLine();
        String message = videoGC.treasureAmount(name);
        System.out.println(message);
    }

    public void showEnmiesType(){
        int type;
        System.out.println("Please enter a number to selec the type of the enemy");
        do{
            System.out.println("[1].Ogre\n [2].Boos \n [3].Abstrac \n [4].Magic");
            type = sc.nextInt();
            if( type<1 || type>4 ){
                System.out.println(" Please try again your option is not valid");

            }
        }while(type<1 || type > 4);
        System.out.println(videoGC.totalEnemytype(type));
    }

    public void biggestEnemy(){
        System.out.println(videoGC.biggestEnemy());
    }
    

    public void countConsonants(){
        System.out.println(videoGC.countConsonants());
    }

    public void topFive(){
        System.out.println("The players they will be shown in the order in which they were added");
        System.out.println(videoGC.topFive());
    }
}
