package sharychuc.lavanderia;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Arrays;

import sharychuc.lavanderia.Clases.Localizacion;
import sharychuc.lavanderia.Clases.Ubicacion;
import sharychuc.lavanderia.SharedPreferences.Preferences;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener {

    private GoogleMap mMap;
    private LatLng currentLatLng;
    private Ubicacion ubicacion;
    private CameraUpdate cameraUpdate;
    private Localizacion localizacion;
    private Preferences preferences;
    private String information;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setSubtitle("Presione y mueva el Marker");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabLogin);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PricesActivity.class));
                MapsActivity.this.finish();
            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        ubicacion = new Ubicacion(MapsActivity.this);
        currentLatLng = ubicacion.latLng();
        localizacion = new Localizacion(MapsActivity.this, currentLatLng);

        information = Arrays.toString(localizacion.gps()).replaceAll("\\[|\\]", "");
        mMap.addMarker(new MarkerOptions().position(currentLatLng)
                .draggable(true)
                .title(information)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLatLng));
        cameraUpdate = CameraUpdateFactory.newLatLngZoom((currentLatLng), 16);
        mMap.animateCamera(cameraUpdate);
        preferences = new Preferences(getApplicationContext(), "Latitude,Longitude,CountryCode,Country, State, City, Address",
                String.valueOf(currentLatLng.latitude) + "," + String.valueOf(currentLatLng.longitude) + ","
                        + information, "Laundry");
        preferences.savePreferences();
        mMap.setOnMarkerDragListener(this);

    }

    @Override
    public void onMarkerDragStart(Marker marker) {
    }

    @Override
    public void onMarkerDrag(Marker marker) {
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        marker.hideInfoWindow();
        currentLatLng = marker.getPosition();
        localizacion = new Localizacion(MapsActivity.this, currentLatLng);
        information = Arrays.toString(localizacion.gps()).replaceAll("\\[|\\]", "");
        marker.setTitle(information);
        preferences = new Preferences(getApplicationContext(), "Latitude,Longitude, CountryCode,Country, State, City, Address",
                String.valueOf(currentLatLng.latitude) + "," + String.valueOf(currentLatLng.longitude) + ","
                        + information, "Laundry");
        preferences.savePreferences();
    }
}
