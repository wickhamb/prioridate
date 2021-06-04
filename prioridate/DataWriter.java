package prioridate;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter {

  /**
   * For testing purposes only
   * @param args
   */
  public static void main(String[] args) {
    saveAssignments();
  }
  public static void saveStudents() {

  }
  public static void saveTeachers() {

  }
  public static void saveCourses() {

  }
  public static void saveAssignments() {
    AssignmentList assignmentList = AssignmentList.getInstance();
    ArrayList<Assignment> assignments = assignmentList.getAssignments();
    JSONArray jsonAssignments = new JSONArray();

    for(int i = 0;i < assignments.size();i++) {
      jsonAssignments.add(getJSONAssignment(assignments.get(i)));
    }

    try (FileWriter outFile = new FileWriter("prioridate/json/test.json")) {
      outFile.write(jsonAssignments.toJSONString());
      outFile.flush();
      outFile.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static JSONObject getJSONAssignment(Assignment assignment) {
    JSONObject assignmentJSONObject = new JSONObject();
    assignmentJSONObject.put("assignmentID",assignment.getAssignmentId());
    assignmentJSONObject.put("title", assignment.getTitle());
    assignmentJSONObject.put("type", assignment.getType());
    assignmentJSONObject.put("dueYear", assignment.getDueDate().get(Calendar.YEAR));
    assignmentJSONObject.put("dueMonth", assignment.getDueDate().get(Calendar.MONTH));
    assignmentJSONObject.put("dueDay", assignment.getDueDate().get(Calendar.DATE));
    assignmentJSONObject.put("dueHour", assignment.getDueDate().get(Calendar.HOUR));
    assignmentJSONObject.put("dueMin", assignment.getDueDate().get(Calendar.MINUTE));
    assignmentJSONObject.put("percentOfGrade", assignment.getPercentOfGrade());
    String assignmentType = assignment.getType();
    switch (assignmentType.toLowerCase()) {
      case "homework":
        Homework homeworkObject = (Homework)assignment;
        assignmentJSONObject.put("numQuestions", homeworkObject.getNumQuestions());
        break;
      case "quiz":
        Quiz quizObject = (Quiz)assignment;
        assignmentJSONObject.put("timeLimit", quizObject.getTimeLimit());
        assignmentJSONObject.put("numQuestions", quizObject.getNumQuestions());
        break;
      case "exam":
        Exam examObject = (Exam)assignment;
        assignmentJSONObject.put("timeLimit", examObject.getTimeLimit());
        assignmentJSONObject.put("numQuestions", examObject.getNumQuestions());
        assignmentJSONObject.put("questionType", examObject.getQuestionType());
        assignmentJSONObject.put("location", examObject.getLocation());
        break;
      case "reading":
        Reading readingObject = (Reading)assignment;
        JSONArray chaptersJSONArray = new JSONArray();
        for(int i = 0; i < readingObject.getChapters().length;i++) {
          chaptersJSONArray.add(readingObject.getChapters()[i]);
        }
        assignmentJSONObject.put("chapters", chaptersJSONArray);
        assignmentJSONObject.put("numPages", readingObject.getNumPages());
        break;
      default:
        break;
    }
    return assignmentJSONObject;
  }
}
