package com.example.animedex;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainpageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mainpage);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int idItem = item.getItemId();
        if(idItem == R.id.MENU_1){
            Toast.makeText(this, "Profile",Toast.LENGTH_LONG).show();
        /*Intent profileIntent = new Intent(MainpageActivity.this, ProfileActivity.class);
        startActivity(profileIntent);*/
        }
        if(idItem == R.id.MENU_2) {
            Toast.makeText(this, "Dashboard", Toast.LENGTH_SHORT).show();
            Intent dashboardIntent = new Intent(MainpageActivity.this, DashboardActivity.class);
            startActivity(dashboardIntent);
        }
        if(idItem == R.id.MENU_3) {
            Toast.makeText(this, "Forum", Toast.LENGTH_SHORT).show();
        /*Intent forumIntent = new Intent(MainpageActivity.this, ForumActivity.class);
        startActivity(forumIntent);*/
        }
        if(idItem == R.id.MENU_4) {
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
        Intent logoutIntent = new Intent(MainpageActivity.this, LoginActivity.class);
        startActivity(logoutIntent);
        }
            return true;
    }
}