/**
 * Created by ege on 22.05.2017.
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import greenfoot.GreenfootImage;
import greenfoot.World;

public class TrafficWorld extends World {
  private String blueCar = "images/topCarBlue.png";
  private String purpleCar = "images/topCarPurple.png";
  private String yellowCar = "images/topCarYellow.png";
  private String redCar = "images/topCarRed.png";
  public static final int WIDTH = 1000;
  public static final int HEIGHT = 750;
  private static final int CELL_SIZE = 1;
  public static int WIDTH_OF_ROAD = 50;
  //variables for vertical roads
  private static final int NUM_OF_VROADS = 7;
  private static final int NUM_OF_VROAD_SPACES = NUM_OF_VROADS - 1;
  private static int VERTICAL_WHITESPACE =
      (WIDTH - (WIDTH_OF_ROAD * NUM_OF_VROADS)) / NUM_OF_VROAD_SPACES;

  private static final int NUM_OF_HROADS = 5;
  private static final int NUM_OF_HROAD_SPACES = NUM_OF_HROADS - 1;
  private static final int HORIZONTAL_WHITESPACE =
      (HEIGHT - (WIDTH_OF_ROAD * NUM_OF_HROADS)) / NUM_OF_HROAD_SPACES;

  private Roads[] roadArrayHoriz = new Roads[NUM_OF_HROADS];
  private Roads[] roadArrayVert = new Roads[NUM_OF_VROADS];
  private Intersection[] intersectArray = new Intersection[NUM_OF_HROADS * NUM_OF_VROADS];
  Intersection[] intersectArrayVert = new Intersection[NUM_OF_VROADS];
  Intersection[] nearIntersectArray = new Intersection[NUM_OF_HROADS];
  private int carCounter = 0;

  public void act() {
    carCounter++;
    int carCounterBound = 180;
    if (carCounter == carCounterBound) {
      Random rand = new Random();
      int randHoriz = rand.nextInt(NUM_OF_HROADS);
      int randVert = rand.nextInt(NUM_OF_VROADS);
      Car carGen = new Car();
      switch (carGen.getDirection()) {
        case NORTH:
          this.addObject(carGen, roadArrayVert[randVert].getX() + (WIDTH_OF_ROAD / 4), HEIGHT);
          break;
        case SOUTH:
          this.addObject(carGen, roadArrayVert[randVert].getX() - (WIDTH_OF_ROAD / 4), 0);
          break;
        case EAST:
          this.addObject(carGen, 0, roadArrayHoriz[randHoriz].getY() + (WIDTH_OF_ROAD / 4));
          break;
        case WEST:
          this.addObject(carGen, WIDTH, roadArrayHoriz[randHoriz].getY() - (WIDTH_OF_ROAD / 4));
          break;
      }
      carCounter = 0;
    }
  }

  public TrafficWorld() {
    /*super(WIDTH, HEIGHT, CELL_SIZE);*/
    super();
    GreenfootImage background = this.getBackground();
    background.setColor(Color.GREEN);
    background.fill();


    for (int i = 0; i < NUM_OF_HROADS; i++) {
      roadArrayHoriz[i] = new Roads(WIDTH, WIDTH_OF_ROAD);
      this.addObject(roadArrayHoriz[i], WIDTH / 2,
          (HORIZONTAL_WHITESPACE + WIDTH_OF_ROAD) * i + WIDTH_OF_ROAD / 2);
    }

    for (int i = 0; i < NUM_OF_VROADS; i++) {
      roadArrayVert[i] = new Roads(WIDTH_OF_ROAD, HEIGHT);
      this.addObject(roadArrayVert[i],
          (VERTICAL_WHITESPACE + WIDTH_OF_ROAD + 1) * i + WIDTH_OF_ROAD / 2, HEIGHT / 2);
    }

    //intersection generator
    for (int horizRoadNo = 0; horizRoadNo < roadArrayHoriz.length; horizRoadNo++) {
      for (int vertRoadNo = 0; vertRoadNo < roadArrayVert.length; vertRoadNo++) {
        int interNo = (horizRoadNo * NUM_OF_VROADS) + vertRoadNo;
        intersectArray[interNo] = new Intersection();
        this.addObject(intersectArray[interNo], roadArrayVert[vertRoadNo].getX(),
            roadArrayHoriz[horizRoadNo].getY());
        for (TrafficLight tf : intersectArray[interNo].getTrafficLight()) {
          int xPosition = 0;
          int yPosition = 0;
          switch (tf.getDirection()) {
            case NORTH:
              xPosition = intersectArray[interNo].getX();
              yPosition = intersectArray[interNo].getY()
                  + (intersectArray[interNo].getImage().getHeight()) / 2
                  + (tf.getImage().getHeight() / 2);
              break;
            case SOUTH:
              xPosition = intersectArray[interNo].getX();
              yPosition = intersectArray[interNo].getY()
                  - (intersectArray[interNo].getImage().getHeight()) / 2
                  - (tf.getImage().getHeight() / 2);
              break;
            case EAST:
              xPosition = intersectArray[interNo].getX()
                  - (intersectArray[interNo].getImage().getHeight()) / 2
                  - (tf.getImage().getHeight() / 2);
              yPosition = intersectArray[interNo].getY();
              break;
            case WEST:
              xPosition = intersectArray[interNo].getX()
                  + (intersectArray[interNo].getImage().getHeight()) / 2
                  + (tf.getImage().getHeight() / 2);
              yPosition = intersectArray[interNo].getY();
              List<Integer> numbers = new ArrayList<Integer>();
              break;
          }
          tf.setRotation(tf.getDirection().getRotation());
          this.addObject(tf, xPosition, yPosition);
        }
      }
    }
  }
}
