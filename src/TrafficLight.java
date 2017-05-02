import greenfoot.Actor;
import greenfoot.GreenfootImage;




/**
 * Created by ege on 2.05.2017.
 */


public class TrafficLight extends Actor {

  private Directions rotationDirection;
  private int greenCounter=780; //yaklasik 15sn
  private int yellowCounter=600;//yaklasik 10sn
  private int redCounter=180; //yaklasik 3sn
  private int lightCounter=0;


  public State state = State.GREEN;

  public TrafficLight(Directions rotation){
    this.rotationDirection = rotation;
    switch (rotationDirection) {
      case NORTH:
      case SOUTH:
        state=State.RED;
        break;
      case EAST:
      case WEST:
        state = State.GREEN;
    }
    GreenfootImage image = new GreenfootImage(this.state.getImgPath());
    setImage(image);
    this.rotationDirection= rotation;

  }


  public boolean isEast(){
    return rotationDirection == Directions.EAST;
  }

  public boolean isWest(){
    return rotationDirection == Directions.WEST;
  }

  public boolean isNorth(){
    return rotationDirection == Directions.NORTH;
  }

  public boolean isSouth(){
    return rotationDirection == Directions.SOUTH;
  }


  public enum State{
    GREEN("Green"), YELLOW("Yellow"), RED("Red");
    //TODO: trafik isigi resimlerine ihtiyacımız var
    String imgPath;

    public String getImgPath() {
      return imgPath;
    }

    private State(String imgPath){
      this.imgPath = imgPath;
    }
  }

  public boolean isRed(){
    return state==State.RED;
  }

  public boolean isGreen(){
    return state==State.GREEN;
  }

  public boolean isYellow(){
    return state==State.YELLOW;
  }


  @Override
  public void act(){ //saniyede 60 defa
    lightCounter++;
    switch(state){
      case GREEN:
        if (lightCounter == yellowCounter){
          state = State.YELLOW;
          setImageFromStateAndResetCounter();
        }
        break;
      case YELLOW:
        if (lightCounter == redCounter){
          state = State.RED;
          setImageFromStateAndResetCounter();
        }
        break;
      case RED:
        if (lightCounter == greenCounter){
          state = State.GREEN;
          setImageFromStateAndResetCounter();
        }
        break;
    }

  }


  private void setImageFromStateAndResetCounter() {
    GreenfootImage image = new GreenfootImage(state.imgPath);
    setImage(image);
    lightCounter = 0;
  }

  public Direction getDirection(){
    return rotationDirection;
  }

}