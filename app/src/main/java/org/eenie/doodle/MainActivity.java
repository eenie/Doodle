package org.eenie.doodle;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    DoodleView mDoodleView;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        mDoodleView = (DoodleView) findViewById(R.id.doodle_view);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.haizewang_150);
                mDoodleView.addDoodle(bitmap2);
            }
        });
    }
}
