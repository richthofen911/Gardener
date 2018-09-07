package net.callofdroidy.gardener;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.GnssStatus;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static android.location.GpsStatus.GPS_EVENT_STARTED;
import static android.location.GpsStatus.GPS_EVENT_STOPPED;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainAct";

    static final int REQUEST_CODE_ENALBE_GPS = 101;

    static {
        System.loadLibrary("keys");
    }

    public native String getGooglePlaceApiKey();

    public native String getOpenWeatherApiKey();

    private FusedLocationProviderClient mFusedLocationClient;

    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.sample_text);

        //String key1 = new String(Base64.decode(getGooglePlaceApiKey(), Base64.DEFAULT));
        //String key2 = new String(Base64.decode(getOpenWeatherApiKey(), Base64.DEFAULT));

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        MainActivityPermissionsDispatcher.getLastLocationWithPermissionCheck(this, tv);
    }

    @SuppressLint("MissingPermission")
    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    void getLastLocation(final TextView textView) {
        if (!isGPSEnabled()) {
            enableGPS();
        }
        mFusedLocationClient
                .getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            textView.setText(location.getLatitude() + ", " + location.getLongitude());
                        } else {
                            textView.setText("location is null");
                        }
                    }
                });
    }

    @OnShowRationale(Manifest.permission.ACCESS_FINE_LOCATION)
    void showRationaleForLocation(final PermissionRequest request) {
        showRationaleDialog(R.string.location_rationale, request);
    }

    @OnPermissionDenied(Manifest.permission.ACCESS_FINE_LOCATION)
    void showDeniedForLocation() {
        // TODO: 06/09/18 use a Snackbar
        Toast.makeText(this, "Alright", Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain(Manifest.permission.ACCESS_FINE_LOCATION)
    void showNeverAskForLocation() {
        // TODO: 06/09/18 use a Snackbar
        Toast.makeText(this,"Can be enabled in your app permission settings", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    private void showRationaleDialog(@StringRes int messageResId, final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setPositiveButton(R.string.button_allow, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton(R.string.button_deny, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage(messageResId)
                .show();
    }

    private boolean isGPSEnabled() {
        return locationManager != null
                && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    @SuppressLint("MissingPermission")
    private void enableGPS() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locationManager.registerGnssStatusCallback(new GnssStatus.Callback() {
                @Override
                public void onStarted() {
                    super.onStarted();
                }

                @Override
                public void onStopped() {
                    super.onStopped();
                }
            });
        } else {
            locationManager.addGpsStatusListener(new GpsStatus.Listener() {
                @Override
                public void onGpsStatusChanged(int event) {
                    switch (event) {
                        case GPS_EVENT_STARTED:
                            break;
                        case GPS_EVENT_STOPPED:
                            break;
                        default:
                            break;
                    }
                }
            });
        }

        startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), REQUEST_CODE_ENALBE_GPS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_ENALBE_GPS:
                Log.e(TAG, "onActivityResult: " + requestCode + ", " + resultCode);
                break;
            default:
                break;
        }
    }
}
