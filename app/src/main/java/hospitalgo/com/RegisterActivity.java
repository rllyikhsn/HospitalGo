package hospitalgo.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class RegisterActivity extends AppCompatActivity {

    private EditText ed_name, ed_email, ed_password, ed_c_password,ed_telepon;
    private Button btn_regist;
    private ProgressBar loading;
    private static String URL_REGIST = "http://192.168.100.95/hospital_go/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loading = findViewById(R.id.loading);
        ed_name = findViewById(R.id.name);
        ed_telepon = findViewById(R.id.telepon);
        ed_email = findViewById(R.id.email);
        ed_password = findViewById(R.id.password);
        ed_c_password = findViewById(R.id.c_password);
        btn_regist = findViewById(R.id.btn_regist);

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mName = ed_name.getText().toString().trim();
                String mEmail = ed_email.getText().toString().trim();
                String mPass = ed_password.getText().toString().trim();
                String mPassC = ed_c_password.getText().toString().trim();
                String mTelepon = ed_telepon.getText().toString().trim();

                if (!mName.isEmpty()|| !mEmail.isEmpty()|| !mPass.isEmpty() || !mPassC.isEmpty()
                || !mTelepon.isEmpty()) {
                    Regist();
                } else {
                    ed_name.setError("Please insert Nama");
                    ed_email.setError("Please insert Email");
                    ed_password.setError("Please insert Password");
                    ed_c_password.setError("Please insert Password Confirm");
                    ed_telepon.setError("Please insert Telepon");
                }
            }
        });
    }

    private void Regist() {
        loading.setVisibility(View.VISIBLE);

        final String name = this.ed_name.getText().toString().trim();
        final String telepon = this.ed_telepon.getText().toString().trim();
        final String email = this.ed_email.getText().toString().trim();
        final String password = this.ed_password.getText().toString().trim();

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);


                            String success = jsonObject.getString("success");

                            if(success.equals("1")){
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                Toast.makeText(RegisterActivity.this, "Register Success!" , Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                            }

                        } catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "Register Gagal!" + e.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            btn_regist.setVisibility(View.VISIBLE);
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Register Gagal!" + error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btn_regist.setVisibility(View.VISIBLE);

                    }
                })
        {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("telepon", telepon);
                params.put("email", email);
                params.put("password", password);
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
}


