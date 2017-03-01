package xzzb.com.testview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mPb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });


        mPb = (ProgressBar) findViewById(R.id.pb);

        new Thread(){
            @Override
            public void run() {
                super.run();
                int max = mPb.getMax();
                try {


                    while (max != mPb.getProgress()){
                        int i = max / 10;
                        int progress1 = mPb.getProgress();
                        int cur = progress1+i;
                        mPb.setProgress(cur);
                        Thread.sleep(1000);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
