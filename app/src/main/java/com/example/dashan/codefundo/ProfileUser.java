package com.example.dashan.codefundo;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import info.hoang8f.widget.FButton;

public class ProfileUser extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String phone_no ="9549967701";
    private static final int REQ_CALL = 1;
    private FButton fButton_contact;
    private FButton fButton_maps,fButton_logout,fButton_webpage,fButton_facebook,fButton_info,fButton_call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fButton_maps=(FButton) findViewById(R.id.maps_button);
        fButton_maps.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(i);
            }
        });
        fButton_logout=(FButton) findViewById(R.id.flogout_button);
        fButton_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(ProfileUser.this, Login.class);
                startActivity(i);
            }
        });
        fButton_webpage=(FButton) findViewById(R.id.fbutton_web);
        fButton_webpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.watscooking.com/donate-now"));
                startActivity(intent);
            }
        });
        fButton_facebook=(FButton) findViewById(R.id.f_facebook_button);
        fButton_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.facebook.com/hungerfreeindian/?ref=br_rs"));
                startActivity(intent);
            }
        });
        fButton_info=(FButton) findViewById(R.id.infocon_button);
        fButton_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),contacts.class);
                startActivity(intent);

            }
        });
        fButton_call=(FButton) findViewById(R.id.call_button_f);
        final String phone="9408778546";
        fButton_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + phone));
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(ProfileUser.this, Login.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQ_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // makePhoneCall();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + phone_no));
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
            else{
                Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
            }
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent i=new Intent(this,MapsActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_gallery) {

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("http://www.watscooking.com/donate-now"));
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://www.facebook.com/hungerfreeindian/?ref=br_rs"));
            startActivity(intent);


        } else if (id == R.id.nav_phone) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phone_no));
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                ActivityCompat.requestPermissions(ProfileUser.this,
                        new String[] {android.Manifest.permission.CALL_PHONE},REQ_CALL);
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                //return;
            }
            startActivity(intent);

        }
        else if (id == R.id.nav_share) {

        }
        else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
