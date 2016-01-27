package sharychuc.lavanderia.Clases;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Shary on 27/01/2016.
 */
public class Ubicacion {
    private Context rootView;
    private Location location;
    private LocationManager locationManager;
    private double longitude = 0;
    private double latitude = 0;
    private Localizacion localizacion;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;
    private String strDate;
    private boolean dato = false;
    private LatLng latLng;

    public Ubicacion(Context rootView) {
        this.rootView = rootView;
    }

    public void informationLocation() {
        try {
            locationManager = (LocationManager) rootView.getSystemService(Context.LOCATION_SERVICE);
            dato = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (dato) {
                if (ActivityCompat.checkSelfPermission(rootView, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(rootView, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    location = locationManager
                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    latLng = new LatLng(latitude, longitude);
                    localizacion = new Localizacion(rootView, latLng);
                    calendar = Calendar.getInstance();
                    simpleDateFormat =
                            new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    strDate = simpleDateFormat.format(calendar.getTime());
                    Log.d("LATITUDE ", String.valueOf(latitude));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

