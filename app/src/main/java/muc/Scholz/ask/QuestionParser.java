package muc.Scholz.ask;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class QuestionParser {

    private List<String> questionBundleList;
    private String questionBundleStr;
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private int solution;
    private int questionCounter;
    private Random r;



    public QuestionParser(String[] questionBundleArr) {
        questionBundleList = new LinkedList<>(Arrays.asList(questionBundleArr));
        questionCounter = 0;
        r = new Random();
        newRandomQuestion();
    }

    public void newRandomQuestion(){
        // Würfeln
        int random = r.nextInt(questionBundleList.size());
        //Speichern
        questionBundleStr = questionBundleList.get(random);
        //Löschen
        questionBundleList.remove(random);

        parse();
        questionCounter++;
    }
    private void parse(){
        String[] questionArr = questionBundleStr.split("#");
        question = questionArr[0];
        answerA = questionArr[1];
        answerB = questionArr[2];
        answerC = questionArr[3];
        solution = Integer.parseInt(questionArr[4]);
    }
    public String getQuestion() {
        return question;
    }
    public String getAnswerA() {
        return answerA;
    }
    public String getAnswerB() {
        return answerB;
    }
    public String getAnswerC() {
        return answerC;
    }
    public int getSolution() {
        return solution;
    }
    public int getQCounter() {
        return questionCounter;
    }
}
