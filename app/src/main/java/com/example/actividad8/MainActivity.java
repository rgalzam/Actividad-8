package com.example.actividad8;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView phoneNumberListView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> phoneNumbers;

    private Button buttonSig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Solicitar permisos
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, 1);
        }

        phoneNumberListView = findViewById(R.id.phone_number_list);
        phoneNumbers = new ArrayList<>();
        // Agrega números de teléfono a la lista
        phoneNumbers.add("3346330248");
        phoneNumbers.add("5524787977");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, phoneNumbers);
        phoneNumberListView.setAdapter(adapter);

        //Botón para cambiar interfaz
        buttonSig = findViewById(R.id.buttonSig);
        buttonSig.setOnClickListener(view ->{
            Intent myIntent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(myIntent);
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (grantResults.length > 0 ) {
                Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public ArrayList<String> getPhoneNumbers() {
        return phoneNumbers;
    }
}