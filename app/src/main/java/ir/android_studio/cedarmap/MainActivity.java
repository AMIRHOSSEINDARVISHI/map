package ir.android_studio.cedarmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static final LatLng VANAK_SQUARE = new LatLng(35.7572, 51.4099);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigationView);
        navigation.setOnNavigationItemSelectedListener(MainActivity.this);
        navigation.setSelectedItemId(R.id.navigation_map);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_map:
                setTitle("نقشه");
                fragment = new MapFragment();
                break;
            case R.id.navigation_reverse:
                setTitle("نقطه یابی");
                fragment = new ReverseGeocodeFragment();
                break;
            case R.id.navigation_forward:
                setTitle("");
                fragment = new ForwardGeocodeFragment();
                break;
            case R.id.navigation_direction:
                setTitle("مسیریابی");
                fragment = new DirectionFragment();
                break;
        }

        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (getSupportFragmentManager().getFragments().isEmpty()) {
                transaction.add(R.id.content, fragment, String.format(Locale.US, "item: %d", item.getItemId())).commit();
            } else {
                transaction.replace(R.id.content, fragment, String.format(Locale.US, "item: %d", item.getItemId())).commit();
                invalidateOptionsMenu();
            }
            return true;
        }

        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        List allFragments = getSupportFragmentManager().getFragments();
        if (allFragments.isEmpty()) {
            return;
        }

        Fragment currentFragment = (Fragment) allFragments.get(allFragments.size() - 1);
        if (currentFragment instanceof PermissionsListener) {
            currentFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
