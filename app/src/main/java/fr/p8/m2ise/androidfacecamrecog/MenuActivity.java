package fr.p8.m2ise.androidfacecamrecog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }

    public void onClick(View v) {
        int id = v.getId();
        Log.d("onClick", "id du boutton appuiyé : " + id);
        Intent i;
        switch (id) {
            case R.id.button_history:
                i = new Intent(this, HistoryActivity.class);
                Log.d("onClick", "historique" + R.id.button_history);
                startActivity(i);
                break;

            case R.id.button_main:
                i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;

            case R.id.menu_aboutbutton:
                i = new Intent(this, AboutActivity.class);
                startActivity(i);
                break;

            case R.id.menu_parambutton:
                i = new Intent(this, ParamActivity.class);
                startActivity(i);
                break;
        }
    }
}
