package prioridate;
import java.util.ArrayList;

public class CourseList {
  private static CourseList courseList = null;
  private static ArrayList<Course> courses;

    /**
   * For Testing Purposes Only
   * @param args
   */
  public static void main(String[] args) {
    CourseList courseList = CourseList.getInstance();
    courseList.printCourseList();
  }

  /**
   * Private Constructor (use CourseList.getInstance() for instantiation)
   */
  private CourseList() {
    courses = DataLoader.getCourses();
  }

  /**
   * Returns the current instance of CourseList if one exists,
   * or creates and returns an instance of CourseList if one doesn't exist
   * @return The current instance of CourseList
   */
  public static CourseList getInstance() {
    if(courseList == null) {
      courseList = new CourseList();
    }
    return courseList;
  }

  /**
   * Accessor for the currently loaded list of courses
   * @return An ArrayList of the currently loaded courses
   */
  public ArrayList<Course> getCourses() {
    return courses;
  }

  /**
   * Add a Course to the current list of courses
   * @param courseToAdd
   */
  public void addCourse(Course courseToAdd) {
    courses.add(courseToAdd);
  }
  
  /**
   * Returns a course from the course list 
   * @param courseName The name of the desired course
   * @return The matching course, returns null if the course
   * does not exist in the list
   */
  public Course getCourse(String courseName) {
    for(int i = 0; i < courses.size();i++) {
      if(courses.get(i).getClassName().equalsIgnoreCase(courseName))
        return courses.get(i);
    }
    return null;
  }

  /**
   * Helper method for testing
   */
  public void printCourseList() {
    for (int i = 0; i < courses.size();i++) {
      System.out.println(courses.get(i).toString());
    }
  }
}
