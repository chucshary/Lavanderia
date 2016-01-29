package sharychuc.lavanderia.Clases;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import java.util.Locale;

/**
 * Created by Shary on 27/01/2016.
 */
public class Localizacion {
    private Context rootView;
    private LatLng latLng;
    private List<Address> addresses;
    private Geocoder geocoder;
    private String[] information;

    public Localizacion(Context rootView, LatLng latLng) {
        this.rootView = rootView;
        this.latLng = latLng;
    }

    public String[] gps() {
        try {
            information = new String[5];
            geocoder = new Geocoder(rootView, Locale.getDefault());
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            information[0] = addresses.get(0).getCountryCode();
            information[1] = addresses.get(0).getCountryName();
            information[2] = addresses.get(0).getAdminArea();
            information[3] = addresses.get(0).getLocality();
            information[4] = addresses.get(0).getSubLocality() + " " + addresses.get(0).getThoroughfare();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return information;
    }
}
