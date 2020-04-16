package its.papsi.beritaonlinec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import its.papsi.beritaonlinec.fragment.AddNewsFragment;
import its.papsi.beritaonlinec.fragment.AllNewsFragment;
import its.papsi.beritaonlinec.fragment.MyNewsFragment;
import its.papsi.beritaonlinec.fragment.SearchNewsFragment;
import its.papsi.beritaonlinec.fragment.UserFragment;

public class MainActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottom_navigation);

        sessionManager = new SessionManager(this);

        if (!sessionManager.isLoggedIn()) {
            Intent intentLogin = new Intent(this, LoginActivity.class);
            startActivity(intentLogin);
            finish();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                setPage(menuItem.getItemId());

                return true;
            }
        });

        if (savedInstanceState == null) {
            setPage(R.id.navigation_semua_berita);
        }
    }

    public void setPage(int itemId) {
        String title = "";
        Fragment fragment = null;

        switch (itemId) {
            case R.id.navigation_semua_berita:
                title = "Semua Berita";
                fragment = new AllNewsFragment();
                break;
            case R.id.navigation_berita_baru:
                title = "Berita Baru";
                fragment = new AddNewsFragment();
                break;
            case R.id.navigation_berita_saya:
                title = "Berita Saya";
                fragment = new MyNewsFragment();
                break;
            case R.id.navigation_cari_berita:
                title = "Cari Berita";
                fragment = new SearchNewsFragment();
                break;
            case R.id.navigation_user:
                title = "User";
                fragment = new UserFragment();
                break;
        }

        MainActivity.this.getSupportActionBar().setTitle(title);

        //set content fragment
        if (fragment != null) {
            MainActivity.this.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_content, fragment)
                    .commit();
        }
    }
}
