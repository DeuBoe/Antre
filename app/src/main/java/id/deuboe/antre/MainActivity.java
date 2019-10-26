package id.deuboe.antre;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

  FirebaseFirestore db = FirebaseFirestore.getInstance();
  private static final String TAG = "MyActivity";
  EditText name, ktp, dateOfBirth, profession, idKtp, address, idPowerOfAttorney, date;
  Button button;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    name = findViewById(R.id.textName);
    ktp = findViewById(R.id.textKtp);
    dateOfBirth = findViewById(R.id.textDateOfBirth);
    profession = findViewById(R.id.textProfession);
    idKtp = findViewById(R.id.textIdKtp);
    address = findViewById(R.id.textAddress);
    idPowerOfAttorney = findViewById(R.id.textIdPowerOfAttorney);
    date = findViewById(R.id.textDate);
    button = findViewById(R.id.button);

    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        String input0 = name.getText().toString();
        String input1 = ktp.getText().toString();
        String input2 = dateOfBirth.getText().toString();
        String input3 = profession.getText().toString();
        String input4 = idKtp.getText().toString();
        String input5 = address.getText().toString();
        String input6 = idPowerOfAttorney.getText().toString();
        String input7 = date.getText().toString();

        if (!TextUtils.isEmpty(input0)
            && !TextUtils.isEmpty(input1)
            && !TextUtils.isEmpty(input2)
            && !TextUtils.isEmpty(input3)
            && !TextUtils.isEmpty(input4)
            && !TextUtils.isEmpty(input5)
            && !TextUtils.isEmpty(input6)
            && !TextUtils.isEmpty(input7)) {
          createUser();
        } else {
          Snackbar.make(view, "Tolong diisi yang masih kosong", Snackbar.LENGTH_SHORT).show();
        }

      }
    });

  }

  private void createUser() {
    String input0 = name.getText().toString();
    String input1 = ktp.getText().toString();
    String input2 = dateOfBirth.getText().toString();
    String input3 = profession.getText().toString();
    String input4 = idKtp.getText().toString();
    String input5 = address.getText().toString();
    String input6 = idPowerOfAttorney.getText().toString();
    String input7 = date.getText().toString();

    final Spinner spinner = findViewById(R.id.spinner);

    spinner.setOnItemSelectedListener(this);

    String[] list = getResources().getStringArray(R.array.list);

    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
    spinner.setAdapter(arrayAdapter);

    Map<String, Object> user = new HashMap<>();
    user.put("name", input0);
    user.put("ktp", input1);
    user.put("dateOfBirth", input2);
    user.put("profession", input3);
    user.put("idKtp", input4);
    user.put("address", input5);
    user.put("idPowerOfAttorney", input6);
    user.put("date", input7);
    user.put("spinner", spinner.toString());

    String id = getDateTime("yyMMddHHmmssSSS");

    // Add a new document with a generated ID
    db.collection("users")
        .document(id)
        .set(user)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
          @Override
          public void onSuccess(Void aVoid) {
            Toast.makeText(getApplicationContext(), "Berhasil dikumpulkan", Toast.LENGTH_SHORT).show();
          }
//          @Override
//          public void onSuccess(DocumentReference documentReference) {
//            Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();
//            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//          }
        })
        .addOnFailureListener(new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {
            Log.w(TAG, "Error adding document", e);
            Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT).show();

          }
        });
  }

  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    String item = parent.getItemAtPosition(position).toString();

  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {

  }

  @SuppressLint("SimpleDateFormat")
  public static String getDateTime(String pattern) {
    return new SimpleDateFormat(pattern).format(new Date());
  }
}
