package com.example.interfaz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.CameraUpdateFactory;

public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private MapView mapView;
    private GoogleMap googleMap;
    private Button btnLocation1, btnLocation2, btnToggleZoom, btnChangeMapType;
    private boolean isZoomEnabled = true;
    private int currentMapTypeIndex = 0;
    private final int[] mapTypes = {
            GoogleMap.MAP_TYPE_NORMAL,
            GoogleMap.MAP_TYPE_SATELLITE,
            GoogleMap.MAP_TYPE_TERRAIN,
            GoogleMap.MAP_TYPE_HYBRID
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        btnLocation1 = findViewById(R.id.btnLocation1);
        btnLocation2 = findViewById(R.id.btnLocation2);
        btnToggleZoom = findViewById(R.id.btnToggleZoom);
        btnChangeMapType = findViewById(R.id.btnChangeMapType);

        btnLocation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng location1 = new LatLng(0.05055, -78.14171); // Cambia a la ubicación deseada
                showLocation(location1, "INT", "Sede 1");
            }
        });

        btnLocation2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng location2 = new LatLng(0.04140, -78.15216); // Cambia a la ubicación deseada
                showLocation(location2, "Escuela Municipal Cayambe", "Sede 2");
            }
        });

        btnToggleZoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleZoom();
            }
        });

        btnChangeMapType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMapType();
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;

        // Set a default location and move the camera
        LatLng defaultLocation = new LatLng(0.04261132697070656, -78.14603336850277);
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 10));

        // Enable zoom controls by default
        this.googleMap.getUiSettings().setZoomControlsEnabled(true);

        // Set the marker click listener
        this.googleMap.setOnMarkerClickListener(this);
    }

    private void showLocation(LatLng location, String title, String snippet) {
        if (googleMap != null) {
            googleMap.clear(); // Clear previous markers
            Marker marker = googleMap.addMarker(new MarkerOptions().position(location).title(title).snippet(snippet));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
            marker.showInfoWindow(); // Show info window of the marker
        }
    }

    private void toggleZoom() {
        if (googleMap != null) {
            isZoomEnabled = !isZoomEnabled;
            googleMap.getUiSettings().setZoomControlsEnabled(isZoomEnabled);
            googleMap.getUiSettings().setZoomGesturesEnabled(isZoomEnabled);
        }
    }

    private void changeMapType() {
        if (googleMap != null) {
            currentMapTypeIndex = (currentMapTypeIndex + 1) % mapTypes.length;
            googleMap.setMapType(mapTypes[currentMapTypeIndex]);
            String mapTypeName = "";
            switch (mapTypes[currentMapTypeIndex]) {
                case GoogleMap.MAP_TYPE_NORMAL:
                    mapTypeName = "Normal";
                    break;
                case GoogleMap.MAP_TYPE_SATELLITE:
                    mapTypeName = "Satellite";
                    break;
                case GoogleMap.MAP_TYPE_TERRAIN:
                    mapTypeName = "Terrain";
                    break;
                case GoogleMap.MAP_TYPE_HYBRID:
                    mapTypeName = "Hybrid";
                    break;
            }
            Toast.makeText(this, "Map Type: " + mapTypeName, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        // Display the position and title in a Toast message
        LatLng position = marker.getPosition();
        String title = marker.getTitle();
        Toast.makeText(this, "Posición: " + position.toString() + "\nTitulo: " + title, Toast.LENGTH_LONG).show();
        return false; // Return false to indicate that we have not consumed the event and that we wish for the default behavior to occur (which is for the camera to move such that the marker is centered and for the marker's info window to open, if it has one).
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}

