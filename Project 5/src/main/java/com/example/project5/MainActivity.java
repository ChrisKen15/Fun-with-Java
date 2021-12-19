package com.example.project5;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 *Class contains method to starts the Main Activity along with
 * methods to start activity for 4 different Museum upon
 * button click.
 *
 */

public class MainActivity extends AppCompatActivity {


    /**
     * Method to perform application start up.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button MoMA = findViewById(R.id.MoMA_PS1);
        final Button NYHS = findViewById(R.id.New_York_Historical_Society);
        final Button RM = findViewById(R.id.Rubin_Museum);
        final Button WMOAA = findViewById(R.id.Whitney_Museum_of_American_Art);

       //Moma PS1 button click event
        MoMA.setOnClickListener(new View.OnClickListener() {

            /**
             * Method to handle button click for MoMA PS1
             * and start activity 2 to calculate price and
             * give access to MoMA PS1 website.
             *
             * @param view
             */
            public void onClick(View view) {

                //student, adult, senior, 1 == MoMA PS1
                int price[] = {14 , 25 , 18 , 1};
                Intent intent = new Intent(MainActivity.this , Activity2.class);
                intent.putExtra("price" ,price);
                startActivity(intent);

            }
        });

        //New-York Historical Society button click event
        NYHS.setOnClickListener(new View.OnClickListener() {

            /**
             * Method to handle button click for New-York Historical Society
             * and start activity 2 to calculate price and
             * give access to New-York Historical Society website.
             *
             * @param v
             */
            public void onClick(View v) {

                //student, adult, senior 2 == New-York Historical Society
                int price[] = {13 , 22 , 17 , 2};
                Intent intent = new Intent(MainActivity.this , Activity2.class);
                intent.putExtra("price" , price);
                startActivity(intent);

            }
        });

        //Rubin Museum button click event
        RM.setOnClickListener(new View.OnClickListener() {

            /**
             * Method to handle button click for Rubin Museum
             * and start activity 2 to calculate price and
             * give access to Rubin Museum website.
             *
             * @param v
             */
            public void onClick(View v) {

                //student, adult, senior, 3 == Rubin Museum
                int price[] = {14 , 19 , 14 , 3};
                Intent intent = new Intent(MainActivity.this , Activity2.class);
                intent.putExtra("price" , price);
                startActivity(intent);

            }
        });

        // Whitney Museum of American Art button click event
        WMOAA.setOnClickListener(new View.OnClickListener() {

            /**
             * Method to handle button click for Whitney Museum of American Art
             * and start activity 2 to calculate price and
             * give access to Whitney Museum of American Art website.
             *
             * @param v
             */
            public void onClick(View v) {

                //student, adult, senior, 4 == Whitney Museum of American Art
                int price[] = {18 , 25 , 18 , 4};
                Intent intent = new Intent(MainActivity.this , Activity2.class);
                intent.putExtra("price" , price);
                startActivity(intent);

            }
        });



    }


}
