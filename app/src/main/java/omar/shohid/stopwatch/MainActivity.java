package omar.shohid.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int seconds = 0;
    boolean isRugging = false;
    Button btnStart;
    Button btnReset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            isRugging = savedInstanceState.getBoolean("running");
        }

        btnStart = findViewById(R.id.btnStart);
        btnReset = findViewById(R.id.btnReset);
        runTimer();
        setButtonText();
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRugging = !isRugging;
                setButtonText();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRugging = false;
                seconds = 0;
            }
        });
    }

    private  void setButtonText(){
        if(isRugging){
            btnStart.setText("Stop");
            btnStart.setBackgroundColor(getResources().getColor(R.color.orange));
        }
        else{
            btnStart.setText("Start");
            btnStart.setBackgroundColor(getResources().getColor(R.color.purple_500));
        }
    }

    private void runTimer() {
        TextView display = findViewById(R.id.display);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);
                display.setText(time);
                if (isRugging) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", isRugging);
    }
}