package simtechnospace.tech.jadhavdairy.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import simtechnospace.tech.jadhavdairy.Fragments.BillDetailsFragment;
import simtechnospace.tech.jadhavdairy.Fragments.HomeFragment;
import simtechnospace.tech.jadhavdairy.Fragments.ProfileFragment;
import simtechnospace.tech.jadhavdairy.R;
import simtechnospace.tech.jadhavdairy.pojo_class.URL;
import simtechnospace.tech.jadhavdairy.pojo_class.UserCredentialsAfterLogin;
import simtechnospace.tech.jadhavdairy.pojo_class.UserDetails;

public class HomeActivity extends AppCompatActivity {

    Toolbar toolbar;
    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;


    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    HomeFragment homeFragment = new HomeFragment();
                    transaction.replace( R.id.fragment_container, homeFragment );
                    transaction.commit();
                    return true;
                case R.id.navigation_subscription:
                    BillDetailsFragment billDetailsFragment = new BillDetailsFragment();
                    transaction.replace( R.id.fragment_container, billDetailsFragment );
                    transaction.commit();
                    return true;
                case R.id.navigation_recent:
                    ProfileFragment profileFragment = new ProfileFragment();
                    transaction.replace( R.id.fragment_container, profileFragment );
                    transaction.commit();
                    return true;

            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);






        getSupportActionBar().setDisplayShowTitleEnabled(false);
        frameLayout = findViewById( R.id.fragment_container );
        bottomNavigationView = findViewById( R.id.navigation );
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        bottomNavigationView.setOnNavigationItemSelectedListener( mOnNavigationItemSelectedListener );

        HomeFragment homeFragment = new HomeFragment();
        transaction.add( R.id.fragment_container,homeFragment );
        transaction.commit();


    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_menu, menu);
        MenuItem item = menu.findItem(R.id.cart);
        MenuItemCompat.setActionView(item, R.layout.actionbar_badge_layout);
        FrameLayout notifCount = (FrameLayout)   MenuItemCompat.getActionView(item);




        final MenuItem itemNotification = menu.findItem(R.id.cart);
        View actionViewNotification = MenuItemCompat.getActionView(itemNotification);
        actionViewNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(itemNotification);
            }
        });

        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.cart) {

            Toast.makeText(this, "Notifications - regarding bills", Toast.LENGTH_SHORT).show();

            return true;
        }
        else if(id == R.id.user)
        {
           // Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();

            UserCredentialsAfterLogin loginUserDetails = new UserCredentialsAfterLogin(HomeActivity.this);
            loginUserDetails.removeUserInfo();

            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();



            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
