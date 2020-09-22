package muc.Scholz.ask;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView resultText;
    private TextView resultFeedbackText;
    private int winCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Übergabe auswerten
        if (getIntent().hasExtra("wins")) {
            winCounter = Integer.parseInt(getIntent().getExtras().getString("wins"));
        }

            Button repeatButton = (Button) findViewById(R.id.repeatButton);
            Button websiteButton = (Button) findViewById(R.id.websiteButton);
            Button backToStartButton = (Button) findViewById(R.id.backToStartButton);
            resultText = (TextView) findViewById(R.id.resultText);
            resultFeedbackText = (TextView) findViewById(R.id.resultFeedbackText);

            showResult();

            backToStartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // MainActivity aufrufen
                    Intent startMain = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(startMain);
                }
            });
            repeatButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // QuestionActivity aufrufen
                    Intent startQuestions = new Intent(getApplicationContext(), QuestionActivity.class);
                    //startQuestions.putExtra("extra", "übergebender Text"); // String übergeben
                    startActivity(startQuestions);
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
        }
        private void showResult() {
        //Individuelle Antwort für die Anzahl an richtigen Fragen setzen
            switch(winCounter){
                case 0:
                    resultFeedbackText.setText("Ein Satz mit x ...");
                    break;
                case 1:
                    resultFeedbackText.setText("Immerhin Eine!");
                    break;
                case 2:
                    resultFeedbackText.setText("Fast die Hälfte richtig beantwortet!");
                    break;
                case 3:
                    resultFeedbackText.setText("Nicht schlecht!");
                    break;
                case 4:
                    resultFeedbackText.setText("Wow, fast alle richtig beantwortet!");
                    break;
                case 5:
                    resultFeedbackText.setText("Perfekt!");
                    break;
            }
            // Ergebnis setzen
            resultText.setText(winCounter + "/5");
        }
}