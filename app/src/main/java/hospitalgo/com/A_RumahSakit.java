package hospitalgo.com;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class A_RumahSakit extends RecyclerView.Adapter<A_RumahSakit.ViewHolder> {

    private Context context;
    private ArrayList<M_RumahSakit> list;
    private ArrayList<M_DataPasien> dataPasiens;

    private String URL_PASIEN = "http://192.168.1.88/hospital_go/data_pasien.php";

    A_RumahSakit(Context context, ArrayList<M_RumahSakit> list,ArrayList<M_DataPasien> dataPasiens) {
        this.context = context;
        this.list = list;
        this.dataPasiens = dataPasiens;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_layoutrumahsakit, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        M_RumahSakit M_RumahSakit = list.get(position);

        holder.txtNamaRs.setText(M_RumahSakit.getNama_rs());
        holder.txtKUT.setText(String.valueOf(M_RumahSakit.getKasur_umum_tersedia()));
        holder.txtKVT.setText(String.valueOf(M_RumahSakit.getKasur_vip_tersedia()));

        holder.cardView.setOnClickListener(view -> {


            String longitude = M_RumahSakit.getLongitude();
            String latitude = M_RumahSakit.getLatitude();

            Sendfind(M_RumahSakit.getId_rs().toString());
            Intent intent= new Intent(context, maps.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("putlong", longitude);
            intent.putExtra("putlat", latitude);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNamaRs, txtKUT, txtKVT;
        private CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            txtNamaRs = itemView.findViewById(R.id.txt_namars);
            txtKUT = itemView.findViewById(R.id.txt_kut);
            txtKVT = itemView.findViewById(R.id.txt_kvt);
        }
    }


    private void Sendfind(String get_id_rs) {

        M_DataPasien mDataPasien = dataPasiens.get(0);

        final String idrs = get_id_rs;
        final String namapasien = mDataPasien.getNama_pasien();
        final String keterangan = mDataPasien.getKeterangan_tambahan();
        final String lokasi = mDataPasien.getLokasi_pasien();
        final String jpp = mDataPasien.getJenis_penyakit();
        final String jkp = mDataPasien.getJenis_kelamin();

        //Log.d("Data yang dikirim",idrs+namapasien+keterangan+lokasi+jkp+jpp);
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PASIEN,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);


                        String success = jsonObject.getString("success");

                        if(success.equals("1")){
                            Toast.makeText(context, "Lokasi Menuju Rumah Sakit" , Toast.LENGTH_SHORT).show();

                        }

                    } catch (JSONException e){
                        e.printStackTrace();
                        Toast.makeText(context, "Mencari Gagal" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                },

                error -> {
                })
        {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("id_rs", idrs);
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

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
