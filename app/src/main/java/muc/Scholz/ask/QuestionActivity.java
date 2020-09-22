package muc.Scholz.ask;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {

    private QuestionParser questionParser;
    private TextView questionCounterText;
    private TextView questionText;
    private TextView feedbackText;
    private Button answerAButton;
    private Button answerBButton;
    private Button answerCButton;
    private Button nextButton;
    private int rightAnswerCounter;
    private int selectedButtonNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        rightAnswerCounter = 0;

        // Fragen von Ressourcen holen und dem neuen Parser mitgeben
        String[] questionArr = getResources().getStringArray(R.array.questionArr);
        questionParser = new QuestionParser(questionArr);

        questionCounterText = (TextView) findViewById(R.id.qCounterText);
        questionText = (TextView) findViewById(R.id.questionText);
        feedbackText = (TextView) findViewById(R.id.feedbackText);
        answerAButton = (Button) findViewById(R.id.answerAButton);
        answerBButton = (Button) findViewById(R.id.answerBButton);
        answerCButton = (Button) findViewById(R.id.answerCButton);
        Button websiteButton = (Button) findViewById(R.id.websiteButton);
        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setVisibility(View.GONE);

        //Erste Frage anzeigen
        display();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionParser.getQCounter() > 4){
                    // Result Activity anzeigen
                   Intent startResult = new Intent(getApplicationContext(), ResultActivity.class);
                   // Anzahl der richtigen Antworten mitgeben
                   startResult.putExtra("wins", rightAnswerCounter +"");
                   startActivity(startResult);
                }
                else {
                    // Neue Frage anzeigen
                    questionParser.newRandomQuestion(); //qcounter+1
                    resetButtons();
                    display();
                }
            }
        });

        answerAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedButtonNum = 1;
                resolve(answerAButton);
            }
        });

        answerBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedButtonNum = 2;
                resolve(answerBButton);
            }
        });

        answerCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedButtonNum = 3;
                resolve(answerCButton);
            }
        });
        websiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Brunata Website öffnen
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.brunata-metrona.de/"));
                startActivity(intent);
            }
        });

        //Übergabe auswerten
        /* if (getIntent().hasExtra("extra")){
            String text = getIntent().getExtras().getString("extra");
        } */
    }
    private void display(){
        //Aktuelle Frage anzeigen
        //ERGÄNZUNG: Anzahl der Fragen am Start auswäheln und übergeben
        questionCounterText.setText("Frage "+questionParser.getQCounter()+" von 5");
        questionText.setText(questionParser.getQuestion());
        answerAButton.setText(questionParser.getAnswerA());
        answerBButton.setText(questionParser.getAnswerB());
        answerCButton.setText(questionParser.getAnswerC());
        if(questionParser.getQCounter() > 4){
            nextButton.setText("Auswertung");
        }
    }

    private void resolve(Button currentButton){
        //Gewählte Antwort überprüfen und Auswerten
        lockButtons();
        if(selectedButtonNum == questionParser.getSolution()) {
            // Richtige Antwort mit grünem Rahmen markieren
            currentButton.setBackground(this.getResources().getDrawable(R.drawable.green_question_button));
            rightAnswerCounter++;
            feedbackText.setText("Richtig!");
        }
        else {
            // Falsche Antwort mit rotem Rahmen markieren
            currentButton.setBackground(this.getResources().getDrawable(R.drawable.red_question_button));
            // Richtige Antwort mit Hellgrünem Rahmen markieren
            switch(questionParser.getSolution()){
                case 1:
                    answerAButton.setBackground(this.getResources().getDrawable(R.drawable.lightgreen_question_button));
                    break;
                case 2:
                    answerBButton.setBackground(this.getResources().getDrawable(R.drawable.lightgreen_question_button));
                    break;
                case 3:
                    answerCButton.setBackground(this.getResources().getDrawable(R.drawable.lightgreen_question_button));
                    break;
            }
            feedbackText.setText("Leider nein! Antwort "+questionParser.getSolution()+" wäre richtig!");
        }
    }

    private void lockButtons(){
        //Antwort Buttons für Eingabe sperren
        answerAButton.setClickable(false);
        answerBButton.setClickable(false);
        answerCButton.setClickable(false);
        //Button für nächste Frage anzeigen
        nextButton.setVisibility(View.VISIBLE);
    }
    private void resetButtons(){
        // Buttons zurücksetzen
        answerAButton.setBackground(this.getResources().getDrawable(R.drawable.grey_question_button));
        answerBButton.setBackground(this.getResources().getDrawable(R.drawable.grey_question_button));
        answerCButton.setBackground(this.getResources().getDrawable(R.drawable.grey_question_button));
        answerAButton.setClickable(true);
        answerBButton.setClickable(true);
        answerCButton.setClickable(true);
        nextButton.setVisibility(View.GONE);
        // Feedback Text zurücksetzen
        feedbackText.setText("");
    }
}