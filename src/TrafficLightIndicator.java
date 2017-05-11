/**
 * Created by ege on 11.05.2017.
 */
public interface TrafficLightIndicator {

  //intersection yakınlarında

  public void closeToIntersection(Intersection intersection);

  //intersection olan yerlerde

  public void inTheInterSection(Intersection intersection);

  //Intersectiondan cıkarken

  public void leavingTheIntersection(Intersection intersection);

  //get method Directionlar için

  public Direction getDirection();




}
