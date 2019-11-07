package id.deuboe.antre;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.model.value.StringValue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DiwakilkanActivity extends AppCompatActivity implements
    AdapterView.OnItemSelectedListener,
    View.OnClickListener {

  EditText name_diwakilkan, ktp_diwakilkan, dateOfBirth_diwakilkan, profession_diwakilkan, idKtp_diwakilkan, address_diwakilkan;

  FirebaseFirestore firestore = FirebaseFirestore.getInstance();

  private static final String TAG = "MyActivity";

  EditText name, date, ktp, dateOfBirth, profession, idKtp, address, idPowerOfAttorney;
  Spinner spinner;
  String spinnerText;
  TextView today;
  DatePickerDialog.OnDateSetListener setListener;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_diwakilkan);

    spinner = findViewById(R.id.spinner);
    ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(
        this,
        R.array.list,
        android.R.layout.simple_spinner_item
    );
    arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
    spinner.setAdapter(arrayAdapter);
    spinner.setOnItemSelectedListener(this);

    name_diwakilkan = findViewById(R.id.textName_diwakilkan);
    ktp_diwakilkan = findViewById(R.id.textKtp_diwakilkan);
    dateOfBirth_diwakilkan = findViewById(R.id.textDateOfBirth_diwakilkan);
    profession_diwakilkan = findViewById(R.id.textProfession_diwakilkan);
    idKtp_diwakilkan = findViewById(R.id.textIdKtp_diwakilkan);
    address_diwakilkan = findViewById(R.id.textAddress_diwakilkan);

    name = findViewById(R.id.textName);
    date = findViewById(R.id.textDate);
    ktp = findViewById(R.id.textKtp);
    dateOfBirth = findViewById(R.id.textDateOfBirth);
    profession = findViewById(R.id.textProfession);
    idKtp = findViewById(R.id.textIdKtp);
    address = findViewById(R.id.textAddress);
    idPowerOfAttorney = findViewById(R.id.textIdPowerOfAttorney);
    today = findViewById(R.id.textToday);

    findViewById(R.id.button).setOnClickListener(this);

    Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);

    dateOfBirth.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
            DiwakilkanActivity.this, new DatePickerDialog.OnDateSetListener() {
          @Override
          public void onDateSet(DatePicker view, int year, int month, int day) {
            month = month + 1;
            String date = day + " / " + month + " / " + year;
            dateOfBirth.setText(date);
          }
        }, year, month, day);
        datePickerDialog.show();
      }
    });

    dateOfBirth_diwakilkan.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
            DiwakilkanActivity.this, new DatePickerDialog.OnDateSetListener() {
          @Override
          public void onDateSet(DatePicker view, int year, int month, int day) {
            month = month + 1;
            String date = day + " / " + month + " / " + year;
            dateOfBirth_diwakilkan.setText(date);
          }
        }, year, month, day);
        datePickerDialog.show();
      }
    });

    date.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
            DiwakilkanActivity.this, new DatePickerDialog.OnDateSetListener() {
          @Override
          public void onDateSet(DatePicker view, int year, int month, int day) {
            month = month + 1;
            String dateToday = day + " / " + month + " / " + year;
            date.setText(dateToday);
          }
        }, year, month, day);
        datePickerDialog.show();
      }
    });

    String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
    today.setText(currentDate);

  }

  public boolean validateInputs(String textName, String textKtp, String textDateOfBirth,
      String textProfession, String textIdKtp, String textAddress, String textName_diwakilkan,
      String textKtp_diwakilkan, String textDateOfBirth_diwakilkan,
      String textProfession_diwakilkan, String textIdKtp_diwakilkan, String textAddress_diwakilkan,
      String textIdPowerOfAttorney,
      String textDate) {
    if (textName.isEmpty()) {
      name.setError(getString(R.string.error_name));
      name.requestFocus();
      return true;
    }

    if (textKtp.isEmpty()) {
      ktp.setError(getString(R.string.error_ktp));
      ktp.requestFocus();
      return true;
    }

    if (textDateOfBirth.isEmpty()) {
      dateOfBirth.setError(getString(R.string.error_dateofbirth));
      dateOfBirth.requestFocus();
      return true;
    }

    if (textProfession.isEmpty()) {
      profession.setError(getString(R.string.error_profession));
      profession.requestFocus();
      return true;
    }

    if (textIdKtp.isEmpty()) {
      idKtp.setError(getString(R.string.error_idktp));
      idKtp.requestFocus();
      return true;
    }

    if (textAddress.isEmpty()) {
      address.setError(getString(R.string.error_address));
      address.requestFocus();
      return true;
    }

    if (textName_diwakilkan.isEmpty()) {
      name.setError(getString(R.string.error_name));
      name.requestFocus();
      return true;
    }

    if (textKtp_diwakilkan.isEmpty()) {
      ktp.setError(getString(R.string.error_ktp));
      ktp.requestFocus();
      return true;
    }

    if (textDateOfBirth_diwakilkan.isEmpty()) {
      dateOfBirth.setError(getString(R.string.error_dateofbirth));
      dateOfBirth.requestFocus();
      return true;
    }

    if (textProfession_diwakilkan.isEmpty()) {
      profession.setError(getString(R.string.error_profession));
      profession.requestFocus();
      return true;
    }

    if (textIdKtp_diwakilkan.isEmpty()) {
      idKtp.setError(getString(R.string.error_idktp));
      idKtp.requestFocus();
      return true;
    }

    if (textAddress_diwakilkan.isEmpty()) {
      address.setError(getString(R.string.error_address));
      address.requestFocus();
      return true;
    }

    if (textIdPowerOfAttorney.isEmpty()) {
      idPowerOfAttorney.setError(getString(R.string.error_idattorney));
      idPowerOfAttorney.requestFocus();
      return true;
    }

    if (textDate.isEmpty()) {
      today.setError(getString(R.string.error_date));
      today.requestFocus();
      return true;
    }
    return false;
  }

  private void createUser() {
    String input0 = name.getText().toString();
    String input1 = ktp.getText().toString();
    String input2 = dateOfBirth.getText().toString();
    String input3 = profession.getText().toString();
    String input4 = idKtp.getText().toString();
    String input5 = address.getText().toString();
    String input6 = idPowerOfAttorney.getText().toString();
    String input7 = today.getText().toString();
    String input8 = name_diwakilkan.getText().toString();
    String input9 = ktp_diwakilkan.getText().toString();
    String input10 = dateOfBirth_diwakilkan.getText().toString();
    String input11 = profession_diwakilkan.getText().toString();
    String input12 = idKtp_diwakilkan.getText().toString();
    String input13 = address_diwakilkan.getText().toString();

    final Spinner spinner = findViewById(R.id.spinner);

    spinner.setOnItemSelectedListener(this);

    String[] list = getResources().getStringArray(R.array.list);

    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
        android.R.layout.simple_spinner_item, list);
    spinner.setAdapter(arrayAdapter);

    Map<String, Object> user = new HashMap<>();
    user.put("name", input0);
    user.put("ktp", input1);
    user.put("dateOfBirth", input2);
    user.put("profession", input3);
    user.put("idKtp", input4);
    user.put("address", input5);
    user.put("name diwakilkan", input8);
    user.put("ktp diwakilkan", input9);
    user.put("dateOfBirth diwakilkan", input10);
    user.put("profession diwakilkan", input11);
    user.put("idKtp diwakilkan", input12);
    user.put("address diwakilkan", input13);
    user.put("idPowerOfAttorney", input6);
    user.put("today", input7);
    user.put("spinner", spinner.toString());

    String id = getDateTime("yyMMddHHmmssSSS");

    // Add a new document with a generated ID
    firestore.collection("users")
        .document(id)
        .set(user)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
          @Override
          public void onSuccess(Void aVoid) {
            Toast.makeText(getApplicationContext(), "Berhasil dikumpulkan", Toast.LENGTH_SHORT)
                .show();
          }
        })
        .addOnFailureListener(new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {
            Log.w(TAG, "Error adding document", e);
            Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT).show();

          }
        });
  }

  @SuppressLint("SimpleDateFormat")
  public static String getDateTime(String pattern) {
    return new SimpleDateFormat(pattern).format(new Date());
  }

  @Override
  public void onClick(View v) {
    String input0 = name.getText().toString();
    String input1 = ktp.getText().toString();
    String input2 = dateOfBirth.getText().toString();
    String input3 = profession.getText().toString();
    String input4 = idKtp.getText().toString();
    String input5 = address.getText().toString();
    String input6 = name_diwakilkan.getText().toString();
    String input7 = ktp_diwakilkan.getText().toString();
    String input8 = dateOfBirth_diwakilkan.getText().toString();
    String input9 = profession_diwakilkan.getText().toString();
    String input10 = idKtp_diwakilkan.getText().toString();
    String input11 = address_diwakilkan.getText().toString();
    String input12 = idPowerOfAttorney.getText().toString();
    String input13 = today.getText().toString();
    String id = getDateTime("yyMMddHHmmssSSS");
    String input14 = spinnerText;
    String input15 = date.getText().toString();

    if (!validateInputs(input0, input1, input2, input3, input4, input5, input6, input7, input8,
        input9, input10, input11, input12, input15)) {

      DocumentReference reference = firestore.collection("users2").document(id);

      Model2 model2 = new Model2(
          input0,
          input1,
          input2,
          input3,
          input4,
          input5,
          input6,
          input7,
          input8,
          input9,
          input10,
          input11,
          input12,
          input13,
          id,
          input14,
          input15
      );

      reference.set(model2)
          .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
              Toast.makeText(getApplicationContext(), "Berhasil dikumpulkan", Toast.LENGTH_LONG)
                  .show();
            }
          })
          .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
              Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
          });
    }
  }

  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    spinnerText = (String) parent.getItemAtPosition(position);

  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {

  }
}
