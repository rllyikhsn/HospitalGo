package hospitalgo.com;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimas Maulana on 5/26/17.
 * Email : araymaulana66@gmail.com
 */
public class A_RumahSakit extends RecyclerView.Adapter<A_RumahSakit.ViewHolder> {

    private Context context;
    private List<M_RumahSakit> list;


    public A_RumahSakit(Context context, List<M_RumahSakit> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_layoutrumahsakit, parent, false);

        final ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String longitude = "106.814165";
                String latitude = "-6.344141";

                Intent intent= new Intent(context, maps.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("putlong", longitude);
                intent.putExtra("putlat", latitude);
                context.startActivity(intent);
                Log.d("Keklik","Yah");
            }
        });
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        M_RumahSakit M_RumahSakit = list.get(position);

        holder.txtNamaRs.setText(M_RumahSakit.getNama_rs());
        holder.txtKUT.setText(String.valueOf(M_RumahSakit.getKasur_umum_tersedia()));
        holder.txtKVT.setText(String.valueOf(M_RumahSakit.getKasur_vip_tersedia()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNamaRs, txtKUT, txtKVT;
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            txtNamaRs = itemView.findViewById(R.id.txt_namars);
            txtKUT = itemView.findViewById(R.id.txt_kut);
            txtKVT = itemView.findViewById(R.id.txt_kvt);
        }
    }

}