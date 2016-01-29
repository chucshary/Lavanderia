package sharychuc.lavanderia.Clases;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Shary on 27/01/2016.
 */
public class Ubicacion {
    private Context rootView;
    private Location location;
    private LocationManager locationManager;
    private double longitude = 0;
    private double latitude = 0;
    private boolean dato = false;
    private LatLng latLng;
    private View view;

    public Ubicacion(Context rootView) {
        this.rootView = rootView;
    }

    public LatLng latLng() {
        try {
            locationManager = (LocationManager) rootView.getSystemService(Context.LOCATION_SERVICE);
            dato = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (dato) {
                if (ActivityCompat.checkSelfPermission(rootView, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(rootView, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                }
                location = locationManager
                        .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                latLng = new LatLng(latitude, longitude);
            } else {
                view = ((Activity) rootView).getWindow().getDecorView().findViewById(android.R.id.content);
                Snackbar.make(view, "No es posible obtener ubicación", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return latLng;
    }
}

