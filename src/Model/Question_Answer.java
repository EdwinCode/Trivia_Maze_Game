package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Question_Answer {
    //REMINDER TO FOLLOW PROPER NAMING CONVENTIONS -> EDIT LATER
    private String question;
    private String correctAnswer;
    private String answer2;
    private String answer3;
    private String answer4;

    Question_Answer() {
        question = "";
        correctAnswer = "";
        answer2 = "";
        answer3 = "";
        answer4 = "";
    }

    //CHANGE THIS FROM PUBLIC, FOR TESTING PURPOSES ONLY
    public void getQuestionAnswerFromDatabase(Statement stmt) {

        //query the database table for a random row
        String query = "SELECT * FROM questions ORDER BY RANDOM() LIMIT 1";

        //don't need to close cause we do try - catch - resources
        try (ResultSet rs = stmt.executeQuery(query)) {

            question = rs.getString("QUESTION");
            correctAnswer = rs.getString("CORRECT_ANSWER");
            answer2 = rs.getString("ANSWER_2");
            answer3 = rs.getString("ANSWER_3");
            answer4 = rs.getString("ANSWER_4");


        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println(question);
        System.out.println(correctAnswer);
        System.out.println(answer2);
        System.out.println(answer3);
        System.out.println(answer4);
    }

}
