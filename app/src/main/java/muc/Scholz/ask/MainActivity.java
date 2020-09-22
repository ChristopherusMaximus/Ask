package muc.Scholz.ask;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = (Button) findViewById(R.id.startButton);
        Button websiteButton = (Button) findViewById(R.id.websiteButton);

        startButton.setOnClickListener(new View.OnClickListener() {
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
}