package fr.p8.m2ise.androidfacecamrecog;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity {
    private static final int ACTION_TAKE_PHOTO_B = 1;
    private static final String BITMAP_STORAGE_KEY = "viewbitmap";
    private static final String IMAGEVIEW_VISIBILITY_STORAGE_KEY = "imageviewvisibility";
    // AFFICHAGE DE L'iMAGE CAPTUREE
    private ImageView mImageView;
    // CONTIENT L'IMAGE
    private Bitmap mImageBitmap;
    // BOUTTON POUR AJOUTER UN USER à la BD
    private Button addBtn;
    // BOUTTON POUR PRENDRE UNE PHOTO
    private Button picBtn;
    // CHAMPS DE TEXTE POUR ECRIRE LE NOM DU USER
    private EditText editTextName;
    // BD PART
    User user;
    PostsDatabaseHelper Mydb;
    SQLiteDatabase db;
    // POUR LA SAUVEAGRDE PHYSIQUE D'UNE IMAGE
    private static final String JPEG_FILE_PREFIX = "IMG_";
    private static final String JPEG_FILE_SUFFIX = ".jpg";
    // CHEMIN DE L'IMAGE
    private String mCurrentPhotoPath;
    private AlbumStorageDirFactory mAlbumStorageDirFactory = null;

    private StorageReference mStorageReference;

    static int numfaces;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // init pour FIREBASE
        mStorageReference = FirebaseStorage.getInstance().getReference();
        numfaces = 0;


        // VIEW PRINCIPALE
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.imageView1);
        mImageBitmap = null;
        picBtn = (Button) findViewById(R.id.btnIntend);
        addBtn = (Button) findViewById(R.id.buttonAdd);
        editTextName = (EditText) findViewById(R.id.editTextNom);

        // INITIALISATION DU HELPER  POUR LA BD
        Mydb = new PostsDatabaseHelper(this);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                // DANS LE CAS OU LE NOM EST REMPLIE
                if (name != null && name != "" && mImageBitmap != null) {
                    // BITMAP -> BYTE[]
                    int imagebytesize = mImageBitmap.getByteCount();
                    ByteBuffer buffer = ByteBuffer.allocate(imagebytesize);
                    mImageBitmap.copyPixelsToBuffer(buffer);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    mImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);


                    //byte[] image = buffer.array();
                    byte[] image = baos.toByteArray();

                    StorageReference imagesChild = mStorageReference.child("face_" + name + numfaces++);
                    // save images in FireBase
                    imagesChild.putBytes(image);

                } else {
                    Toast.makeText(MainActivity.this, "rien dans le nom ou rien dans l'image", Toast.LENGTH_SHORT).show();
                }

            }
        });

        picBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // creation de l'appelle avec pour action de capturer une image depuis la camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // start camera for activity
                startActivityForResult(intent, ACTION_TAKE_PHOTO_B);
            }
        });

        picBtn.setClickable(true);

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ACTION_TAKE_PHOTO_B && resultCode == RESULT_OK && data != null) {
            Log.e("OnActivityResult", "Recup du Bundel");
            Bundle extras = data.getExtras();

            mImageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(mImageBitmap);
        }
    }

}
