package fr.p8.m2ise.androidfacecamrecog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class HistoryActivity extends AppCompatActivity {
// TODO DESSIN DE L'HISTOGRAMME DES VISITES

    DatabaseReference myRef, mybd;
    private ValueEventListener mReferenceListener;
    GrapheUtils graphe;
    public TextView date_heure;
    public TextView message_t;


    FirebaseListAdapter<MessageModel> mAdapter;

    private final int MaxNumbersVisitsShowed = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //graphe = new GrapheUtils(this,MaxNumbersVisitsShowed);
        setContentView(R.layout.activity_history);

        date_heure = (TextView) findViewById(R.id.textView_heure_date);
        message_t = (TextView) findViewById(R.id.textView_message);

        myRef = FirebaseDatabase.getInstance().getReference().child("toto");

        mReferenceListener = myRef.limitToLast(1).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("OndataCHange", "Value");
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    MessageModel toto = snap.getValue(MessageModel.class);
                    String time = snap.child("time").getValue().toString();
                    String message = snap.child("message").getValue().toString();
                    String date = snap.child("date").getValue().toString();
                    Log.d("OndataChange", toto.toString());

                    date_heure.setText(date + " , " + time);

                    message_t.setText(message);
                }
                Log.d("onDataChange", "fin boucle");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Chat", "The read failed: " + databaseError.getMessage());
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myRef.removeEventListener(mReferenceListener);
        mAdapter.cleanup();
    }


}
