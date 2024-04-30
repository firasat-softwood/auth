package softwood.auth;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText et_username;
    private EditText et_password;
    private Button btn_login;
    private TextView tv_Result;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        tv_Result = findViewById(R.id.tv_Result);

        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                performLogin();
            }
        });
    }
    private void performLogin(){
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        // Create a StringRequest for login API call
        StringRequest request = new StringRequest(Request.Method.POST, "http://206.42.124.10:8000/api/login",
                response -> {
                    tv_Result.setText(response.toString());
                },
                error-> {
                    // Handle login error
                    tv_Result.setText(error.toString());
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        // Add request to Volley request queue
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
