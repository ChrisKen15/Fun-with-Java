package com.example.project5;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.DecimalFormat;

/**
 *Class contains method to starts the Second Activity which includes
 * ticket prices, option to select upto 5 tickets, calculator for ticket total,
 * sales tax and the total price. Along with image button that takes the user to the
 * museum website.
 *
 */
public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

         Button Calculate = findViewById(R.id.Calculate);
         ImageButton imageB = findViewById(R.id.imageB);
         final TextView ticket_price_Text_view = findViewById(R.id.ticket_price_Text_view);
         final TextView sales_tax_text_view = findViewById(R.id.sales_tax_text_view);
         final TextView ticket_total_text_view = findViewById(R.id.ticket_total_text_view);

         final DecimalFormat decimalFormat = new DecimalFormat("###.##");

        //Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();
        final int[] price = extras.getIntArray("price");
        //student, adult, senior , type of mes. to set picture for image button
        //MOMA = 1 , NYHS = 2 , RM = 3, WMOAA = 4

        //if to set button image.
        if(price[3] == 1){
            imageB.setBackgroundResource(R.drawable.moma_ps1);
        }
        if(price[3] == 2){
            imageB.setBackgroundResource(R.drawable.nyhs);
        }
        if(price[3] == 3){
            imageB.setBackgroundResource(R.drawable.rubin_museum);
        }
        if(price[3] == 4){
            imageB.setBackgroundResource(R.drawable.whitney_m_ame_art);
        }


        //adult spinner
        TextView adulttext = findViewById(R.id.adulttext);
        final Spinner adultSpinner = findViewById(R.id.AdultSpinner);
        adulttext.append("Adult $" + price[1]);

        //senior spinner
        TextView seniortext = findViewById(R.id.seniortext);
        final Spinner seniorSpinner = findViewById(R.id.senorSpinner);
        seniortext.append("Senior $" + price[2]);

        //student spinner
        final Spinner studentSpinner = findViewById(R.id.studentSpinner);
        TextView studenttext = findViewById(R.id.studenttext);
        studenttext.append("Student $" + price[0]);

       //To show toast message
        Context context = getApplicationContext();
        CharSequence text = "“Maximum of 5 tickets for each!”";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context , text , duration);
        toast.show();



        //to do on calculate button click
        Calculate.setOnClickListener(new View.OnClickListener() {
            /**
             * Method to handle button click for calculating
             * the different prices and append to text view.
             *
             * @param v
             */
            public void onClick(View v) {

                //student, adult, senior.
                int total_ticket = 0;
                double salex_tax = 0.00;
                double bill = 0;
                double NYC_Sales_tax = 0.08875;

                total_ticket = calculation(price[0], Integer.parseInt(studentSpinner.getSelectedItem().toString()), price[1],
                        Integer.parseInt(adultSpinner.getSelectedItem().toString()), price[2], Integer.parseInt(seniorSpinner.getSelectedItem().toString()));

                salex_tax = NYC_Sales_tax * total_ticket;

                bill = salex_tax + total_ticket;

                ticket_price_Text_view.setText("");
                ticket_price_Text_view.append("$" + String.valueOf(decimalFormat.format(total_ticket)));

                sales_tax_text_view.setText("");
                sales_tax_text_view.append("$" + String.valueOf(decimalFormat.format(salex_tax)));

                ticket_total_text_view.setText("");
                ticket_total_text_view.append("$" + String.valueOf(decimalFormat.format(bill)));

            }

        });
    }

    /**
     * Method to create a "third activity" to take
     * user to the Museum website.
     *
     * @param view
     */
    public void goToWeb(View view){

        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();
        int[] price = extras.getIntArray("price");

        //if to determine which Museum link.
        if(price[3] == 1){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.moma.org/ps1")));
        }
        if(price[3] == 2){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nyhistory.org/")));
        }
        if(price[3] == 3){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://rubinmuseum.org/")));
        }
        if(price[3] == 4){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://whitney.org/")));
        }
    }

    /**
     *Method to calculate total tickets price w/out sales tax.
     *
     * @param price1
     * @param amount1
     * @param price2
     * @param amount2
     * @param price3
     * @param amount3
     * @return total ticket price
     */
    public int calculation( int price1 , int amount1 , int price2 , int amount2 , int price3 , int amount3) {

        return price1*amount1 + price2*amount2 + price3*amount3;

    }



}
