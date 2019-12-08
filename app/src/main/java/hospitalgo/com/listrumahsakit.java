package hospitalgo.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class listrumahsakit extends AppCompatActivity {

    //private String url = "http://192.168.100.95/filter_search.php";
    private String url = "http://192.168.1.88/hospital_go/filter_search.php";

    private RecyclerView recyclerView;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private ArrayList<M_RumahSakit> rumahSakitList;
    private ArrayList<M_DataPasien> dataPasienList;
    private RecyclerView.Adapter adapter;
    private String nama_pasien,jenis_kelamin,keterangan,lokasi,jenis_penyakit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listrumahsakit);


        Intent intent = getIntent();
        nama_pasien = intent.getStringExtra("namapasien");
        jenis_kelamin = intent.getStringExtra("jkp");
        jenis_penyakit = intent.getStringExtra("jpp");
        keterangan = intent.getStringExtra("keterangan");
        lokasi = intent.getStringExtra("lokasi");



        recyclerView = findViewById(R.id.rc_listrumahsakit);

        rumahSakitList = new ArrayList<>();
        dataPasienList = new ArrayList<>();

        adapter = new A_RumahSakit(getApplicationContext(),rumahSakitList,dataPasienList);

        M_DataPasien mDataPasien = new M_DataPasien();
        mDataPasien.setNama_pasien(nama_pasien);
        mDataPasien.setJenis_kelamin(jenis_kelamin);
        mDataPasien.setJenis_penyakit(jenis_penyakit);
        mDataPasien.setKeterangan_tambahan(keterangan);
        mDataPasien.setLokasi_pasien(lokasi);
        dataPasienList.add(mDataPasien);


        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);
        getData();

    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);


                        switch (jenis_penyakit){
                            case "Jantung":
                                if (jsonObject.getInt("spesialis_jantung") != 0)
                                {
                                    M_RumahSakit mRumahSakit = new M_RumahSakit();
                                    mRumahSakit.setId_rs(jsonObject.getInt("id_rs"));
                                    mRumahSakit.setNama_rs(jsonObject.getString("rumah_sakit"));
                                    mRumahSakit.setKasur_umum_tersedia(jsonObject.getInt("kasur_umum_tersedia"));
                                    mRumahSakit.setKasur_vip_tersedia(jsonObject.getInt("kasur_vip_tersedia"));
                                    mRumahSakit.setLongitude(jsonObject.getString("Longitude"));
                                    mRumahSakit.setLatitude(jsonObject.getString("Latitude"));

                                    rumahSakitList.add(mRumahSakit);
                                }

                                break;
                            case "Kehamilan":
                                if (jsonObject.getInt("spesialis_kehamilan") != 0)
                                {
                                    M_RumahSakit mRumahSakit = new M_RumahSakit();
                                    mRumahSakit.setId_rs(jsonObject.getInt("id_rs"));
                                    mRumahSakit.setNama_rs(jsonObject.getString("rumah_sakit"));
                                    mRumahSakit.setKasur_umum_tersedia(jsonObject.getInt("kasur_umum_tersedia"));
                                    mRumahSakit.setKasur_vip_tersedia(jsonObject.getInt("kasur_vip_tersedia"));
                                    mRumahSakit.setLongitude(jsonObject.getString("Longitude"));
                                    mRumahSakit.setLongitude(jsonObject.getString("Latitude"));

                                    rumahSakitList.add(mRumahSakit);
                                }
                                break;
                            case "Stroke":
                                if (jsonObject.getInt("spesialis_stroke") != 0)
                                {
                                    M_RumahSakit mRumahSakit = new M_RumahSakit();
                                    mRumahSakit.setId_rs(jsonObject.getInt("id_rs"));
                                    mRumahSakit.setNama_rs(jsonObject.getString("rumah_sakit"));
                                    mRumahSakit.setKasur_umum_tersedia(jsonObject.getInt("kasur_umum_tersedia"));
                                    mRumahSakit.setKasur_vip_tersedia(jsonObject.getInt("kasur_vip_tersedia"));
                                    mRumahSakit.setLongitude(jsonObject.getString("Longitude"));
                                    mRumahSakit.setLatitude(jsonObject.getString("Latitude"));

                                    rumahSakitList.add(mRumahSakit);
                                }
                                break;
                            case "Paru-paru":
                                if (jsonObject.getInt("spesialis_paruparu") != 0)
                                {
                                    M_RumahSakit mRumahSakit = new M_RumahSakit();
                                    mRumahSakit.setId_rs(jsonObject.getInt("id_rs"));
                                    mRumahSakit.setNama_rs(jsonObject.getString("rumah_sakit"));
                                    mRumahSakit.setKasur_umum_tersedia(jsonObject.getInt("kasur_umum_tersedia"));
                                    mRumahSakit.setKasur_vip_tersedia(jsonObject.getInt("kasur_vip_tersedia"));
                                    mRumahSakit.setLongitude(jsonObject.getString("Longitude"));
                                    mRumahSakit.setLatitude(jsonObject.getString("Latitude"));

                                    rumahSakitList.add(mRumahSakit);
                                }
                                break;
                            case "Asma":
                                if (jsonObject.getInt("spesialis_asma") != 0)
                                {
                                    M_RumahSakit mRumahSakit = new M_RumahSakit();
                                    mRumahSakit.setId_rs(jsonObject.getInt("id_rs"));
                                    mRumahSakit.setNama_rs(jsonObject.getString("rumah_sakit"));
                                    mRumahSakit.setKasur_umum_tersedia(jsonObject.getInt("kasur_umum_tersedia"));
                                    mRumahSakit.setKasur_vip_tersedia(jsonObject.getInt("kasur_vip_tersedia"));
                                    mRumahSakit.setLongitude(jsonObject.getString("Longitude"));
                                    mRumahSakit.setLatitude(jsonObject.getString("Latitude"));

                                    rumahSakitList.add(mRumahSakit);
                                }
                                break;
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}
