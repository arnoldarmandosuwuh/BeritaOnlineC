package its.papsi.beritaonlinec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

import static its.papsi.beritaonlinec.GlobalVariable.BASE_URL;

public class FeedbackActivity extends AppCompatActivity {

    private RadioButton rb1, rb2, rb3, rb4, rb5;
    private TextView tvKomentar;
    private Button btnSubmit;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        rb1 = findViewById(R.id.rbRating1);
        rb2 = findViewById(R.id.rbRating2);
        rb3 = findViewById(R.id.rbRating3);
        rb4 = findViewById(R.id.rbRating4);
        rb5 = findViewById(R.id.rbRating5);
        tvKomentar = findViewById(R.id.tvKomentar);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitFeedback();
            }
        });

    }

    private void submitFeedback() {
        final String komentar = tvKomentar.getText().toString();

        // radio button
        String rating = "";
        if (rb1.isChecked()) {
            rating = "1";
        } else if (rb2.isChecked()) {
            rating = "2";
        } else if (rb3.isChecked()) {
            rating = "3";
        } else if (rb4.isChecked()) {
            rating = "4";
        } else if (rb5.isChecked()) {
            rating = "5";
        } else {
            Toast.makeText(this, "Harus memilih salah satu rating", Toast.LENGTH_SHORT).show();
        }
        final String rate = rating;

        sessionManager = new SessionManager(this);
        final String idPenulis = sessionManager.getUserId();


        StringRequest request = new StringRequest(Request.Method.POST,
                BASE_URL + "feedback.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    int status = jsonObject.getInt("status");
                    String message = jsonObject.getString("message");

                    Toast.makeText(FeedbackActivity.this, message, Toast.LENGTH_SHORT).show();
                    if (status == 0){
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FeedbackActivity.this, "Error : " + error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("id_penulis", idPenulis);
                params.put("rating", rate);
                params.put("komentar", komentar);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
