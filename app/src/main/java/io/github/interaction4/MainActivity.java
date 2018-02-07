package io.github.interaction4;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private ArrayList<String> stringList = new ArrayList<>();
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").into(imageView);

        lv = findViewById(R.id.lv1);

        for (int i = 0; i < 100; i++) {
            stringList.add("Item " + (i + 1));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                stringList);

        lv.setAdapter(arrayAdapter);

        ArrayList<Holder> arr = new ArrayList<>();

        for (int i = 0; i < 30; ++i) {
            if (i % 2 == 0)
                arr.add(new Holder("John" + (i + 1), "COE" + (i + 1), R.mipmap.ic_launcher));
            else
                arr.add(new Holder("John" + (i + 1), "COE" + (i + 1), R.mipmap.ic_launcher_round));

        }


        MyCustomAdapter customAdapter = new MyCustomAdapter(this, R.layout.my_view, arr);
        lv.setAdapter(customAdapter);
    }

    public class Holder {
        String name, branch;
        int img_id;

        public Holder(String name, String branch, int img_id) {
            this.name = name;
            this.branch = branch;
            this.img_id = img_id;
        }
    }

    public class MyCustomAdapter extends ArrayAdapter<Holder> {

        private ArrayList<Holder> list;
        private int layoutRes;

        public MyCustomAdapter(@NonNull Context context, int resource, @NonNull List<Holder> objects) {
            super(context, resource, objects);
            layoutRes = resource;
            list = (ArrayList<Holder>) objects;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public Holder getItem(int position) {
            return list.get(position);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            TextView tv1, tv2;
            ImageView iv;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(layoutRes, parent, false);
                Log.d(TAG, "getView: Hello! I am a newly inflated view. position: " + position);
            } else
                Log.d(TAG, "getView: Hello! I was just recycled. Green Environment :). position: " + position);

            iv = convertView.findViewById(R.id.iv);
            tv1 = convertView.findViewById(R.id.tv_name);
            tv2 = convertView.findViewById(R.id.tv_batch);

            Holder holder = getItem(position);
            tv1.setText(holder.name);
            tv2.setText(holder.branch);
            iv.setImageResource(holder.img_id);

            return convertView;
        }
    }


}
