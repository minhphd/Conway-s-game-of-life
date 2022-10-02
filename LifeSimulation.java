import java.util.Scanner;
import java.io.File;
import java.text.SimpleDateFormat;  
import java.util.Date;  

public class LifeSimulation {
    private Landscape scape;
    
    public LifeSimulation(int rows, int cols, double chance){
        //default setting
        this.scape = new Landscape(rows, cols, chance);
    }
    
    //utilizing scanner class to get user input
    public LifeSimulation(boolean userInput){
        Scanner scanner = new Scanner(System.in); //create a new scanner object
        if (userInput){     //check if userInput is set to true

            //getting number of rows for landscape from users
            System.out.print("number of rows: ");
            int rows = scanner.nextInt();

            //getting number of columns for landscape from users
            System.out.print("number of cols: ");
            int cols = scanner.nextInt();

            //asking users if they want to create a blank grid or create a random grid based on probability
            System.out.print("create blank grid? (true or false): ");
            boolean isBlank = scanner.nextBoolean();

            if (isBlank) {  //if user want empty landscape
                this.scape = new Landscape(rows, cols);
                String x = null;
                String y = null;
                boolean status = false;
                System.out.println("\nthe system will now ask for coordinates of each cell you want to modify. \nType in the x and y coordinates of cell, press enter without type in anything to finish the input");
                
                //repeatedly ask for coordinate and status until user stop giving input
                while (true) {

                    //get x
                    System.out.print("\nx coordinate of cell: ");
                    scanner.nextLine();
                    x = scanner.nextLine();
                    
                    //check if blank line was entered
                    if (x.equals("")){
                        break;
                    }

                    //get y
                    System.out.print("y coordinate of cell: ");
                    y = scanner.nextLine();
                    
                    //check if blank line was entered
                    if (y.equals("")){
                        break;
                    }

                    //get status
                    System.out.print("cell dead or alive (false or true): ");
                    status = scanner.nextBoolean();

                    //edit cell
                    this.scape.getCell(Integer.parseInt(y), Integer.parseInt(x)).setAlive(status);
                }
            } else {

                //if user don't want empty grid, ask for alive chance of each cell,
                System.out.print("probability that a cell is alive: ");
                double chance = scanner.nextDouble();
                this.scape = new Landscape(rows, cols, chance);
            }
        } else {
            //create landscape with default setting if userInput was set to false
            this.scape = new Landscape(100, 100, 0.25);
        }

        //stop the scanner to prevent leak
        scanner.close();
    }

    public void runSimulation(boolean outputToFile) throws InterruptedException{
        LandscapeDisplay display = new LandscapeDisplay(scape, 7);
        int currFrame = 0;
        String filename = "";
        if (outputToFile){
            filename = new SimpleDateFormat("yyyyMMdd-HHmm").format(new Date());
            File file = new File("img/run-" + filename);
            file.mkdirs();
        }
        while (this.scape.advance()) {
            Thread.sleep(10);
            if (outputToFile){
                display.saveImage("img/run-" + filename + "/frame-" + (currFrame+1) + ".jpg");
            }
            currFrame++;
            display.repaint();
        }
        System.out.println("game ended");
    }

    public void runSimulation(int frames, boolean outputToFile) throws InterruptedException{
        LandscapeDisplay display = new LandscapeDisplay(scape, 7);
        int currFrame = 0;
        String filename = "";
        if (outputToFile){
            filename = new SimpleDateFormat("yyyyMMdd-HHmm").format(new Date());
            File file = new File("img/run-" + filename);
            file.mkdirs();
        }
        while (currFrame < frames) {
            Thread.sleep(100);
            this.scape.advance();
            if (outputToFile){
                display.saveImage("img/run-" + filename + "/frame-" + (currFrame+1) + ".jpg");
            }
            display.repaint();
            currFrame++;
        }
    }
}
