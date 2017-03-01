package xzzb.com.testview;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Main4Activity extends AppCompatActivity {

    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("hhh");
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        Button button = (Button) findViewById(R.id.b2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPop();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }

        return true;
    }

    private void showPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_pop, null);
        View view1 = LayoutInflater.from(this).inflate(R.layout.activity_main4, null);
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT
        , ViewGroup.LayoutParams.WRAP_CONTENT,true);
        mPopupWindow.setContentView(view);
        TextView textView = (TextView)view.findViewById(R.id.tv1);
        TextView textView1 = (TextView)view.findViewById(R.id.tv2);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main4Activity.this, "确定键。。。。。", Toast.LENGTH_SHORT).show();
                OkHttpClient okHttpClient = new OkHttpClient();

                RequestBody re = new FormBody.Builder()
                        .add("username","admin")
                        .add("password","123456")
                        .build();
                Request request = new Request.Builder()
                        .url("http://www.baidu.com")
                        .post(re)
                        .build();
//                Request request = new Request.Builder()
//                        .get()
//                        .url("http://www.baidu.com")
//                        .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String string = response.body().string();
                        Log.e("Main4Activity", "onResponse: "+string);
                    }
                });
                mPopupWindow.dismiss();
            }
        });

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main4Activity.this, "取消键。。。。。", Toast.LENGTH_SHORT).show();
                mPopupWindow.dismiss();

            }
        });


        mPopupWindow.showAtLocation(view1,Gravity.BOTTOM,0,0);
    }
}
