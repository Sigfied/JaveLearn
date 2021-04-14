import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
class Farmer {
    private boolean crossRiver;
    private boolean inBoat;
    public void showStatus() {
        System.out.println("Farmer has Cross  :"+crossRiver);
    }

    public boolean isCrossRiver() {
        return crossRiver;
    }

    public void setCrossRiver(boolean crossRiver) {
        this.crossRiver = crossRiver;
    }

    public boolean isInBoat() {
        return inBoat;
    }

    public void setInBoat(boolean inBoat) {
        this.inBoat = inBoat;
    }
}
class Sheep{
    private boolean crossRiver;
    private boolean isAlive = true;
    private String name;
    private boolean inBoat;
    public Sheep(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isCrossRiver() {
        return crossRiver;
    }

    public void setCrossRiver(boolean crossRiver) {
        this.crossRiver = crossRiver;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void eatCabbage(Cabbage cabbage) {
        cabbage.setAlive(false);
    }

    public boolean isInBoat() {
        return inBoat;
    }

    public void setInBoat(boolean inBoat) {
        this.inBoat = inBoat;
    }

    public void showStatus() {
        System.out.print("Sheep is alive Alive:  true");
        System.out.println("            Wolf has Cross  :"+this.isCrossRiver());
    }
}
class Cabbage{
    private boolean isAlive = true;
    private boolean crossRiver;
    private boolean inBoat;

    public boolean isInBoat() {
        return inBoat;
    }

    public void setInBoat(boolean inBoat) {
        this.inBoat = inBoat;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isCrossRiver() {
        return crossRiver;
    }

    public void setCrossRiver(boolean crossRiver) {
        this.crossRiver = crossRiver;
    }

    public void showStatus() {
        System.out.print("Cabbage is alive Alive:  true");
        System.out.println("            Wolf has Cross  :"+this.isCrossRiver());
    }
}
class Wolf{
    private boolean crossRiver;
    private boolean isAlive = true;
    private String name;
    private boolean inBoat;

    public boolean isInBoat() {
        return inBoat;
    }

    public void setInBoat(boolean inBoat) {
        this.inBoat = inBoat;
    }

    public Wolf(String name) {
        this.name = name;
    }

    public boolean isCrossRiver() {
        return crossRiver;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public String getName() {
        return name;
    }

    public void setCrossRiver(boolean crossRiver) {
        this.crossRiver = crossRiver;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void eatSheep(Sheep sheep){
        sheep.setAlive(false);
    }

    public void showStatus() {
        System.out.print("Wolf is alive Alive:  true");
        System.out.println("            Wolf has Cross  :"+this.isCrossRiver());
    }
}
class Boat{//船只能带两个实体，但只有农夫才能划船
    public Farmer farmer;
    public Sheep sheep;
    public Cabbage cabbage;
    public Wolf wolf;
    public Boat(String sheepName,String wolfName) {
       sheep = new Sheep(sheepName);
       wolf = new Wolf(wolfName);
       farmer = new Farmer();
       cabbage = new Cabbage();
    }

    public void FarmerAloneCrossRiver(){
        farmer.setInBoat(true);
        farmer.setCrossRiver(!farmer.isCrossRiver());
    }

    public void FarmerBringSheepCrossRiver(){
        farmer.setInBoat(true);
        sheep.setInBoat(true);
        farmer.setCrossRiver(!farmer.isCrossRiver());
        sheep.setCrossRiver(!sheep.isCrossRiver());
    }

    public void FarmerBringWolfCrossRiver(){
        farmer.setInBoat(true);
        wolf.setInBoat(true);
        farmer.setCrossRiver(!farmer.isCrossRiver());
        wolf.setCrossRiver(!sheep.isCrossRiver());
    }

    public void FarmerBringCabbageCrossRiver(){
        farmer.setInBoat(true);
        cabbage.setInBoat(true);
        farmer.setCrossRiver(!farmer.isCrossRiver());
        cabbage.setCrossRiver(!sheep.isCrossRiver());
    }
}
class GameGui {
    public static void menu() {
        /* 显示菜单 */
        System.out.println("==================Please choose operation============");
        System.out.println("\t==========1:Cross the river alone===========");
        System.out.println("\t==========2:Cross the river with wolf=========");
        System.out.println("\t==========3:Cross the river with sheep============");
        System.out.println("\t==========4:Cross the river with cabbage==========");
        System.out.println("\t==========0:Quit===============");
        System.out.println("===================================================");
        System.out.println("Input the number(0~4):");
    }

    public static void showStatus(Boat boat) {
        /* 输出农夫、各种动物、物品的状态（生存、位置） */
      boat.farmer.showStatus();
      boat.wolf.showStatus();
      boat.sheep.showStatus();
      boat.cabbage.showStatus();
    }
}
class Game {
    Boat boat;
    protected void play() {
        Scanner input = new Scanner(System.in);
        GameGui.menu();
        System.out.println("输入羊的名字");
        String sheepName = input.nextLine();
        System.out.println("输入狼的名字");
        String wolfName = input.nextLine();
        boat = new Boat(sheepName,wolfName);
        int choice;			//用户输入选择
        boolean gameOver=false,//游戏结束标志，默认为false，代表游戏进行中，未结束
                win;     //游戏输赢标志，默认为false，代表未赢得游戏。
        GameGui.menu();
        choice = input.nextInt();
        switch(choice)
        {
            case 0: gameOver=true;
                break;
            case 1:/* 农夫独自过河的处理 */
                boat.FarmerAloneCrossRiver();
                break;
            case 2:/* 农夫带狼的处理 */
                boat.FarmerBringWolfCrossRiver();
                break;
            case 3:/* 农夫带羊的处理 */
                boat.FarmerBringSheepCrossRiver();
                break;
            case 4:/* 农夫带白菜的处理 */
                boat.FarmerBringCabbageCrossRiver();
                break;
        }
        while(!gameOver)
        {
            GameGui.menu();
            choice = input.nextInt();
            if(boat.wolf.isCrossRiver() == boat.sheep.isCrossRiver() && !boat.farmer.isCrossRiver()){
                boat.wolf.eatSheep(boat.sheep);
                gameOver = isGameOver();
            }
            if(boat.sheep.isCrossRiver() == boat.cabbage.isCrossRiver() && !boat.farmer.isCrossRiver()){
                boat.sheep.eatCabbage(boat.cabbage);
                gameOver = isGameOver();
            }
            switch(choice)
            {
                case 0: gameOver=true;
                    break;
                case 1:/* 农夫独自过河的处理 */
                    boat.FarmerAloneCrossRiver();
                    break;
                case 2:/* 农夫带狼的处理 */
                    boat.FarmerBringWolfCrossRiver();
                    break;
                case 3:/* 农夫带羊的处理 */
                    boat.FarmerBringSheepCrossRiver();
                    break;
                case 4:/* 农夫带白菜的处理 */
                    boat.FarmerBringCabbageCrossRiver();
                    break;
            }
        }
        win = this.hasWin();
        if(win) {
            System.out.println("game over: you win !");
        }else {
            System.out.println("game over: you lose !");
        }


        input.close();
    }
    /*
     * 判断游戏是否结束
     * 输入：无
     * 运算：羊、白菜任一实体被吃，游戏结束，或者狼、羊、白菜均未被吃且全部渡过河，游戏结束
     * 输出：游戏结束--返回true ，未结束--返回false
     */
    public boolean isGameOver() {
        if(!boat.sheep.isAlive() || !boat.cabbage.isAlive()) {
            return true;
        }
        return boat.wolf.isCrossRiver() && boat.sheep.isCrossRiver() && boat.cabbage.isCrossRiver();
    }
    /*
     * 判断游戏是否胜利
     * 前置条件：游戏结束
     * 输入：无
     * 运算：狼、羊、白菜均未被吃且全部渡过河，游戏胜利，否则失败
     * 输出：游戏胜利--返回true ，失败--返回false
     */
    public boolean hasWin() {
        if(!boat.sheep.isAlive()|| !boat.cabbage.isAlive()) {
            return false;
        }
        return boat.wolf.isCrossRiver() && boat.sheep.isCrossRiver() && boat.cabbage.isCrossRiver();
    }

}