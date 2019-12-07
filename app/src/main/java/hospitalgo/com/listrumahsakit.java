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
import java.util.List;

public class listrumahsakit extends AppCompatActivity {

    private String url = "http://192.168.1.5/filter_search.php";

    private RecyclerView recyclerView;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<M_RumahSakit> rumahSakitList;
    private RecyclerView.Adapter adapter;
    private String jenis_penyakit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listrumahsakit);


        Intent intent = getIntent();
        jenis_penyakit = intent.getStringExtra("jpp");


        recyclerView = findViewById(R.id.rc_listrumahsakit);

        rumahSakitList = new ArrayList<>();
        adapter = new A_RumahSakit(getApplicationContext(),rumahSakitList);

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
                                    mRumahSakit.setNama_rs(jsonObject.getString("rumah_sakit"));
                                    mRumahSakit.setKasur_umum_tersedia(jsonObject.getInt("kasur_umum_tersedia"));
                                    mRumahSakit.setKasur_vip_tersedia(jsonObject.getInt("kasur_vip_tersedia"));

                                    rumahSakitList.add(mRumahSakit);
                                    Log.d("Jenis: " , jsonObject.getString("rumah_sakit"));
                                }

                                break;
                            case "Kehamilan":
                                if (jsonObject.getInt("spesialis_kehamilan") != 0)
                                {
                                    M_RumahSakit mRumahSakit = new M_RumahSakit();
                                    mRumahSakit.setNama_rs(jsonObject.getString("rumah_sakit"));
                                    mRumahSakit.setKasur_umum_tersedia(jsonObject.getInt("kasur_umum_tersedia"));
                                    mRumahSakit.setKasur_vip_tersedia(jsonObject.getInt("kasur_vip_tersedia"));

                                    rumahSakitList.add(mRumahSakit);
                                }
                                break;
                            case "Stroke":
                                if (jsonObject.getInt("spesialis_stroke") != 0)
                                {
                                    M_RumahSakit mRumahSakit = new M_RumahSakit();
                                    mRumahSakit.setNama_rs(jsonObject.getString("rumah_sakit"));
                                    mRumahSakit.setKasur_umum_tersedia(jsonObject.getInt("kasur_umum_tersedia"));
                                    mRumahSakit.setKasur_vip_tersedia(jsonObject.getInt("kasur_vip_tersedia"));

                                    rumahSakitList.add(mRumahSakit);
                                }
                                break;
                            case "Paru-paru":
                                if (jsonObject.getInt("spesialis_paruparu") != 0)
                                {
                                    M_RumahSakit mRumahSakit = new M_RumahSakit();
                                    mRumahSakit.setNama_rs(jsonObject.getString("rumah_sakit"));
                                    mRumahSakit.setKasur_umum_tersedia(jsonObject.getInt("kasur_umum_tersedia"));
                                    mRumahSakit.setKasur_vip_tersedia(jsonObject.getInt("kasur_vip_tersedia"));

                                    rumahSakitList.add(mRumahSakit);
                                }
                                break;
                            case "Asma":
                                if (jsonObject.getInt("spesialis_asma") != 0)
                                {
                                    M_RumahSakit mRumahSakit = new M_RumahSakit();
                                    mRumahSakit.setNama_rs(jsonObject.getString("rumah_sakit"));
                                    mRumahSakit.setKasur_umum_tersedia(jsonObject.getInt("kasur_umum_tersedia"));
                                    mRumahSakit.setKasur_vip_tersedia(jsonObject.getInt("kasur_vip_tersedia"));

                                    rumahSakitList.add(mRumahSakit);
                                }
                                break;
                        }

                        /*
                        if (jsonObject.getString("rumah_sakit").equals("sea"))
                        {
                            mRumahSakit.setNama_rs(jsonObject.getString("nama_kegiatan"));
                            mRumahSakit.setKasur_umum_tersedia(jsonObject.getString("nama_kegiatan"));
                            mRumahSakit.setKasur_vip_tersedia(jsonObject.getString("nama_kegiatan"));

                            rumahSakitList.add(mRumahSakit);
                        }

                         */

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
