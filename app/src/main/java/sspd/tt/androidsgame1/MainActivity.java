package sspd.tt.androidsgame1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    // 0: yello , 1 :red 2: empty
    int activePlayer = 0;

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean gameActive = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        counter.setTranslationY(-1500);



        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]== 2 && gameActive){



        gameState[tappedCounter] =activePlayer;

        if(activePlayer ==0){

            counter.setImageResource(R.drawable.yellow);
            activePlayer=1;
        }
        else {
            counter.setImageResource(R.drawable.red);
            activePlayer=0;
        }

        counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

        for(int[] winningPosition : winningPositions){

            if(gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]]==gameState[winningPosition[2]]  && gameState[winningPosition[2]]!=2){

                // Someone has won!

                gameActive =false;

                String winner ="";
                if(activePlayer== 1){

                    winner = "yellow";

                }
                else {
                    winner = "red";
                }

                Toast.makeText(this,winner+" is Won!!",Toast.LENGTH_LONG).show();
            }
        }


        }

    }
}