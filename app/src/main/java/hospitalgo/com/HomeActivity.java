package hospitalgo.com;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView name, email;
    private Spinner spinner, spinner1;
    private EditText edit_keterangan, edit_lokasi, edit_nama_pasien ;
    private Button btn_logout, btn_GetLoc, btn_send_find;
    private String URL_PASIEN = "http://192.168.100.95/data_pasien.php";

    //edit by hedy
    String Test;

    //edit by rully
    String Konyol;


    //location
    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    String latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        btn_logout = findViewById(R.id.btn_logout);
        btn_GetLoc = findViewById(R.id.getLoc_btn);
        btn_send_find = findViewById(R.id.btn_send_find);
        edit_lokasi = findViewById(R.id.edit_lokasi);
        edit_keterangan = findViewById(R.id.edit_keterangan);
        edit_nama_pasien = findViewById(R.id.edit_nama_pasien);
        spinner = findViewById(R.id.spinner_penyakit);
        spinner1 = findViewById(R.id.spinner_jaminan);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION
        },REQUEST_LOCATION);

        Intent intent = getIntent();
        String extraName = intent.getStringExtra("name");
        String extraEmail = intent.getStringExtra("email");

        name.setText(extraName);
        email.setText(extraEmail);

        btn_GetLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                    hidupkanGPS();
                }else {
                    dapetinLokasi();
                }

            }
        });

        btn_send_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mName_pasien = edit_nama_pasien.getText().toString().trim();
                String mKeterangan = edit_keterangan.getText().toString().trim();
                String mlokasi = edit_lokasi.getText().toString().trim();
                String jpp = spinner.getSelectedItem().toString();
                String jkp = spinner1.getSelectedItem().toString();

                if (!mName_pasien.isEmpty()|| !mKeterangan.isEmpty()|| !mlokasi.isEmpty()) {
                    Intent intent = new Intent(HomeActivity.this, listrumahsakit.class);
                    intent.putExtra("namapasien", mName_pasien);
                    intent.putExtra("keterangan", mKeterangan);
                    intent.putExtra("lokasi", mlokasi);
                    intent.putExtra("jpp", jpp);
                    intent.putExtra("jkp", jkp);
                    startActivity(intent);
                    Toast.makeText(HomeActivity.this, "Rumah Sakit Tersedia!" , Toast.LENGTH_SHORT).show();
                } else {
                    edit_nama_pasien.setError("Silahkan masukkan Nama Pasien");
                    edit_lokasi.setError("Silahkan masukkan lokasi");
                }
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.penyakit_array, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.kelamin_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner1.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(this);
        spinner1.setOnItemSelectedListener(this);
    }

    @SuppressLint("SetTextI18n")
    private void dapetinLokasi(){
        if (ActivityCompat.checkSelfPermission(HomeActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(HomeActivity.this,

                Manifest.permission.ACCESS_COARSE_LOCATION) !=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else
        {
            @SuppressLint("MissingPermission") Location LocationGps= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            @SuppressLint("MissingPermission") Location LocationNetwork=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            @SuppressLint("MissingPermission") Location LocationPassive=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (LocationGps !=null)
            {
                double lat=LocationGps.getLatitude();
                double longi=LocationGps.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

                edit_lokasi.setText(latitude+","+longitude);
            }
            else if (LocationNetwork !=null)
            {
                double lat=LocationNetwork.getLatitude();
                double longi=LocationNetwork.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

                edit_lokasi.setText(latitude+","+longitude);
            }
            else if (LocationPassive !=null)
            {
                double lat=LocationPassive.getLatitude();
                double longi=LocationPassive.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

                edit_lokasi.setText(latitude+","+longitude);
            }
            else
            {
                Toast.makeText(this, "Can't Get Your Location", Toast.LENGTH_SHORT).show();
            }

            //Thats All Run Your App
        }
    }

    private void hidupkanGPS(){
        final AlertDialog.Builder builder= new AlertDialog.Builder(this);

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        String sSelected=parent.getItemAtPosition(pos).toString();
        Toast.makeText(this,sSelected,Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

   /* private void Sendfind() {

        final String namapasien = this.edit_nama_pasien.getText().toString().trim();
        final String keterangan = this.edit_keterangan.getText().toString().trim();
        final String lokasi = this.edit_lokasi.getText().toString().trim();
        final String jpp = spinner.getSelectedItem().toString();
        final String jkp = spinner1.getSelectedItem().toString();

        Log.d("Data yang dikirim",namapasien+keterangan+lokasi+jkp+jpp);
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PASIEN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);


                            String success = jsonObject.getString("success");

                            if(success.equals("1")){
                                Intent intent = new Intent(HomeActivity.this, listrumahsakit.class);
                                intent.putExtra("jpp", jpp);
                                startActivity(intent);
                                Toast.makeText(HomeActivity.this, "Rumah Sakit Tersedia!" , Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(HomeActivity.this, "Mencari Gagal" + e.toString(), Toast.LENGTH_SHORT).show();

                            btn_send_find.setVisibility(View.VISIBLE);
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d( "Mencari Gagal" , error.toString());
                        btn_send_find.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("nama_pasien", namapasien);
                params.put("jenis_penyakit_pasien", jpp);
                params.put("jenis_kelamin_pasien", jkp);
                params.put("keterangan_tambahan", keterangan);
                params.put("lokasi_pasien", lokasi);
                return params;
            }
        };

        //10000 is the time in milliseconds adn is equal to 10 sec
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
*/
}


