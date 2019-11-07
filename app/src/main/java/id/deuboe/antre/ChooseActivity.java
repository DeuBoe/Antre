package id.deuboe.antre;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ChooseActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_choose);

  }

  public void mandiri(View view) {
    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
    startActivity(intent);
  }

  public void mewakilkan(View view) {
    Intent intent = new Intent(getApplicationContext(), DiwakilkanActivity.class);
    startActivity(intent);
  }
}
