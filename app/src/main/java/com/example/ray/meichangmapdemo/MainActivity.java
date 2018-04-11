package com.example.ray.meichangmapdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rl_meichangmap;
    MeiChangMapBean mapBean;
    private int width = 46, height = 46;

    private int meichangHeightDistance = 550;
    private int meiChangHeight = 478;
    private double TrainLeft = 424.94556765163304;
    MyMeiChangMapView myMeiChangMapView;
    //汽车沟图片集合
    String[] carsName = new String[]{"home/yelj/all/l_h_t.gif",
            "home/yelj/all/r_h_t.gif",
            "home/yelj/all/l_h_t.gif",
            "home/yelj/all/r_h_t.gif",
            "home/yelj/all/l_f_t.gif",
            "home/yelj/all/r_f_t.gif",
            "home/yelj/all/l_f_t.gif",
            "home/yelj/all/r_f_t.gif",
            "home/yelj/all/l_l_t.gif",
            "home/yelj/all/r_l_t.gif",
            "home/yelj/all/l_l_t.gif",
            "home/yelj/all/r_l_t.gif",
            "home/yelj/all/l_lv_t.gif",
            "home/yelj/all/r_lv_t.gif",
            "home/yelj/all/l_lv_t.gif",
            "home/yelj/all/r_lv_t.gif",
            "home/yelj/all/l_z_t.gif",
            "home/yelj/all/r_z_t.gif",
            "home/yelj/all/l_z_t.gif",
            "home/yelj/all/r_z_t.gif",
            "home/yelj/all/l_zh_t.gif",
            "home/yelj/all/r_zh_t.gif",
            "home/yelj/all/l_zh_t.gif",
            "home/yelj/all/r_zh_t.gif",
            "home/yelj/tx.gif",
            "home/yelj/tx-r.gif",
            "home/yelj/tx.gif",
            "home/yelj/tx-r.gif"
    };

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MeiChangMapParameter parameter = (MeiChangMapParameter) msg.obj;
            Log.e("tag", parameter.getBg_height() + "%%%%%%%%%%%%");
            getMeiChangMap("一期", (int) (parameter.getCvsWidth() * 0.5925349922239502), parameter.getBg_height());

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rl_meichangmap = findViewById(R.id.rl_meichangmap);

//        getMeiChangMap("",0,0);
        setMeiChangMap();

    }

    /**
     * "width": canvas.width - ((canvas.width - translateX) * 0.3934681181959565) - translateX - ((canvas.width - translateX) * 0.0139968895800933),
     * "height": canvas.height,
     * "yard": f.yardId
     */
    private void getMeiChangMap(String meicName, int w, int h) {
        Log.e("tag","请求数据");
        mapBean = new Gson().fromJson("{\"ylList\":[],\"mc\":{\"id\":2,\"name\":\"二期\",\"maxLat\":40.03545267,\"maxLng\":113.30453114,\"status\":1,\"acctTime\":\"2017-08-15\",\"acctUser\":0,\"updateTime\":\"2017-08-15\",\"org\":\"2\",\"xD\":0.0,\"yD\":477.9999999999999,\"xR\":639.0,\"yR\":477.9999999999999,\"xU\":639.0,\"yU\":0.0,\"xL\":0.0,\"yL\":0.0,\"minLat\":40.03323471,\"minLng\":113.30173406,\"url\":null},\"coalData\":{\"二期\":\"259006.69\",\"三期\":\"228827.29\",\"一期\":\"140538.79\"},\"doulj\":[{\"course\":null,\"lng\":228.452529066026,\"courseAngle\":120,\"lat\":182.74670417861455},{\"course\":\"SW\",\"lng\":456.905058132052,\"courseAngle\":60,\"lat\":413.34599361575494}],\"mdList\":[{\"amount\":129.29999999999927,\"qe\":4910.0,\"sgq\":0.96,\"xDu\":0.0,\"yDu\":0.0,\"xRu\":0.0,\"yRu\":0.0,\"xUu\":0.0,\"yUu\":0.0,\"xLu\":0.0,\"yLu\":0.0,\"h\":0.0,\"id\":0,\"name\":\"煤场东煤场北部\",\"latD\":0.0,\"lngD\":0.0,\"status\":0,\"acctTime\":null,\"acctUser\":0,\"updateTime\":null,\"belong\":null,\"xD\":0.0,\"yD\":163.79014950675392,\"xR\":228.452529066026,\"yR\":163.79014950675392,\"xU\":228.452529066026,\"yU\":0.0,\"xL\":0.0,\"yL\":0.0,\"latR\":0.0,\"lngR\":0.0,\"latU\":0.0,\"lngU\":0.0,\"latL\":0.0,\"lngL\":0.0,\"org\":null},{\"amount\":5265.600000000006,\"qe\":4977.0,\"sgq\":0.72,\"xDu\":0.0,\"yDu\":0.0,\"xRu\":0.0,\"yRu\":0.0,\"xUu\":0.0,\"yUu\":0.0,\"xLu\":0.0,\"yLu\":0.0,\"h\":0.0,\"id\":0,\"name\":\"煤场东煤场南 部\",\"latD\":0.0,\"lngD\":0.0,\"status\":0,\"acctTime\":null,\"acctUser\":0,\"updateTime\":null,\"belong\":null,\"xD\":456.905058132052,\"yD\":163.79014950675392,\"xR\":639.0,\"yR\":163.79014950675392,\"xU\":639.0,\"yU\":0.0,\"xL\":456.905058132052,\"yL\":0.0,\"latR\":0.0,\"lngR\":0.0,\"latU\":0.0,\"lngU\":0.0,\"latL\":0.0,\"lngL\":0.0,\"org\":null},{\"amount\":31511.9,\"qe\":4075.0,\"sgq\":1.1,\"xDu\":0.0,\"yDu\":0.0,\"xRu\":0.0,\"yRu\":0.0,\"xUu\":0.0,\"yUu\":0.0,\"xLu\":0.0,\"yLu\":0.0,\"h\":0.0,\"id\":0,\"name\":\"煤场西煤场南部1\",\"latD\":0.0,\"lngD\":0.0,\"status\":0,\"acctTime\":null,\"acctUser\":0,\"updateTime\":null,\"belong\":null,\"xD\":456.9141962332146,\"yD\":391.79465815433997,\"xR\":638.9908618988374,\"yR\":391.79465815433997,\"xU\":638.9908618988374,\"yU\":206.8928204295839,\"xL\":456.9141962332146,\"yL\":206.8928204295839,\"latR\":0.0,\"lngR\":0.0,\"latU\":0.0,\"lngU\":0.0,\"latL\":0.0,\"lngL\":0.0,\"org\":null},{\"amount\":21526.4,\"qe\":3903.04,\"sgq\":0.79,\"xDu\":0.0,\"yDu\":0.0,\"xRu\":0.0,\"yRu\":0.0,\"xUu\":0.0,\"yUu\":0.0,\"xLu\":0.0,\"yLu\":0.0,\"h\":0.0,\"id\":0,\"name\":\"煤场西煤场中部\",\"latD\":0.0,\"lngD\":0.0,\"status\":0,\"acctTime\":null,\"acctUser\":0,\"updateTime\":null,\"belong\":null,\"xD\":228.46166716718864,\"yD\":391.79465815433997,\"xR\":455.7719335878845,\"yR\":391.79465815433997,\"xU\":455.7719335878845,\"yU\":206.8928204295839,\"xL\":228.46166716718864,\"yL\":206.8928204295839,\"latR\":0.0,\"lngR\":0.0,\"latU\":0.0,\"lngU\":0.0,\"latL\":0.0,\"lngL\":0.0,\"org\":null},{\"amount\":12891.5,\"qe\":3775.0,\"sgq\":1.14,\"xDu\":0.0,\"yDu\":0.0,\"xRu\":0.0,\"yRu\":0.0,\"xUu\":0.0,\"yUu\":0.0,\"xLu\":0.0,\"yLu\":0.0,\"h\":0.0,\"id\":0,\"name\":\"煤场西煤场北部1\",\"latD\":0.0,\"lngD\":0.0,\"status\":0,\"acctTime\":null,\"acctUser\":0,\"updateTime\":null,\"belong\":null,\"xD\":0.00913810116264104,\"yD\":391.79465815433997,\"xR\":228.46166716718864,\"yR\":391.79465815433997,\"xU\":228.46166716718864,\"yU\":206.8928204295839,\"xL\":0.00913810116264104,\"yL\":206.8928204295839,\"latR\":0.0,\"lngR\":0.0,\"latU\":0.0,\"lngU\":0.0,\"latL\":0.0,\"lngL\":0.0,\"org\":null},{\"amount\":74238.09999999999,\"qe\":5248.0,\"sgq\":1.16,\"xDu\":0.0,\"yDu\":0.0,\"xRu\":0.0,\"yRu\":0.0,\"xUu\":0.0,\"yUu\":0.0,\"xLu\":0.0,\"yLu\":0.0,\"h\":0.0,\"id\":0,\"name\":\"煤场东煤场中部\",\"latD\":0.0,\"lngD\":0.0,\"status\":0,\"acctTime\":null,\"acctUser\":0,\"updateTime\":null,\"belong\":null,\"xD\":228.452529066026,\"yD\":163.79014950675392,\"xR\":456.905058132052,\"yR\":163.79014950675392,\"xU\":456.905058132052,\"yU\":0.0,\"xL\":228.452529066026,\"yL\":0.0,\"latR\":0.0,\"lngR\":0.0,\"latU\":0.0,\"lngU\":0.0,\"latL\":0.0,\"lngL\":0.0,\"org\":null}]}"
                 //        mapBean=new Gson().fromJson("{\"ylList\":[],\"mc\":{\"id\":2,\"name\":\"二期\",\"maxLat\":40.03545267,\"maxLng\":113.30453114,\"status\":1,\"acctTime\":\"2017-08-15\",\"acctUser\":0,\"updateTime\":\"2017-08-15\",\"org\":\"2\",\"xD\":0.0,\"yD\":239.00000000000006,\"xR\":480.0000000000001,\"yR\":239.00000000000006,\"xU\":480.0000000000001,\"yU\":0.0,\"xL\":0.0,\"yL\":0.0,\"minLat\":40.03323471,\"minLng\":113.30173406,\"url\":null},\"coalData\":{\"二期\":\"143046.4\",\"三期\":\"131874.79\",\"一期\":\"264276.4\"},\"doulj\":[{\"course\":null,\"lng\":171.6075335707238,\"courseAngle\":120,\"lat\":91.37335208930732},{\"course\":\"SW\",\"lng\":343.2150671414476,\"courseAngle\":60,\"lat\":206.67299680787758}],\"mdList\":[{\"amount\":50425.6,\"qe\":4615.22,\"sgq\":0.99,\"xDu\":0.0,\"yDu\":0.0,\"xRu\":0.0,\"yRu\":0.0,\"xUu\":0.0,\"yUu\":0.0,\"xLu\":0.0,\"yLu\":0.0,\"h\":0.0,\"id\":0,\"name\":\"煤场西煤场北部1\",\"latD\":0.0,\"lngD\":0.0,\"status\":0,\"acctTime\":null,\"acctUser\":0,\"updateTime\":null,\"belong\":null,\"xD\":0.006864301342828952,\"yD\":195.89732907717007,\"xR\":171.61439787206663,\"yR\":195.89732907717007,\"xU\":171.61439787206663,\"yU\":103.446410214792,\"xL\":0.006864301342828952,\"yL\":103.446410214792,\"latR\":0.0,\"lngR\":0.0,\"latU\":0.0,\"lngU\":0.0,\"latL\":0.0,\"lngL\":0.0,\"org\":null},{\"amount\":19685.9,\"qe\":4921.0,\"sgq\":0.8,\"xDu\":0.0,\"yDu\":0.0,\"xRu\":0.0,\"yRu\":0.0,\"xUu\":0.0,\"yUu\":0.0,\"xLu\":0.0,\"yLu\":0.0,\"h\":0.0,\"id\":0,\"name\":\"煤场东煤场北部\",\"latD\":0.0,\"lngD\":0.0,\"status\":0,\"acctTime\":null,\"acctUser\":0,\"updateTime\":null,\"belong\":null,\"xD\":0.0,\"yD\":81.895074753377,\"xR\":171.6075335707238,\"yR\":81.895074753377,\"xU\":171.6075335707238,\"yU\":0.0,\"xL\":0.0,\"yL\":0.0,\"latR\":0.0,\"lngR\":0.0,\"latU\":0.0,\"lngU\":0.0,\"latL\":0.0,\"lngL\":0.0,\"org\":null},{\"amount\":1165.3,\"qe\":4910.0,\"sgq\":0.83,\"xDu\":0.0,\"yDu\":0.0,\"xRu\":0.0,\"yRu\":0.0,\"xUu\":0.0,\"yUu\":0.0,\"xLu\":0.0,\"yLu\":0.0,\"h\":0.0,\"id\":0,\"name\":\"煤场西煤场中部\",\"latD\":0.0,\"lngD\":0.0,\"status\":0,\"acctTime\":null,\"acctUser\":0,\"updateTime\":null,\"belong\":null,\"xD\":171.61439787206663,\"yD\":195.89732907717007,\"xR\":342.3638937749368,\"yR\":195.89732907717007,\"xU\":342.3638937749368,\"yU\":103.446410214792,\"xL\":171.61439787206663,\"yL\":103.446410214792,\"latR\":0.0,\"lngR\":0.0,\"latU\":0.0,\"lngU\":0.0,\"latL\":0.0,\"lngL\":0.0,\"org\":null},{\"amount\":42407.2,\"qe\":4092.0,\"sgq\":1.9,\"xDu\":0.0,\"yDu\":0.0,\"xRu\":0.0,\"yRu\":0.0,\"xUu\":0.0,\"yUu\":0.0,\"xLu\":0.0,\"yLu\":0.0,\"h\":0.0,\"id\":0,\"name\":\"煤场东煤场南 部\",\"latD\":0.0,\"lngD\":0.0,\"status\":0,\"acctTime\":null,\"acctUser\":0,\"updateTime\":null,\"belong\":null,\"xD\":343.2150671414476,\"yD\":81.895074753377,\"xR\":480.0000000000001,\"yR\":81.895074753377,\"xU\":480.0000000000001,\"yU\":0.0,\"xL\":343.2150671414476,\"yL\":0.0,\"latR\":0.0,\"lngR\":0.0,\"latU\":0.0,\"lngU\":0.0,\"latL\":0.0,\"lngL\":0.0,\"org\":null},{\"amount\":4570.5,\"qe\":4011.0,\"sgq\":0.88,\"xDu\":0.0,\"yDu\":0.0,\"xRu\":0.0,\"yRu\":0.0,\"xUu\":0.0,\"yUu\":0.0,\"xLu\":0.0,\"yLu\":0.0,\"h\":0.0,\"id\":0,\"name\":\"煤场东煤场南 部\",\"latD\":0.0,\"lngD\":0.0,\"status\":0,\"acctTime\":null,\"acctUser\":0,\"updateTime\":null,\"belong\":null,\"xD\":343.2150671414476,\"yD\":81.895074753377,\"xR\":480.0000000000001,\"yR\":81.895074753377,\"xU\":480.0000000000001,\"yU\":0.0,\"xL\":343.2150671414476,\"yL\":0.0,\"latR\":0.0,\"lngR\":0.0,\"latU\":0.0,\"lngU\":0.0,\"latL\":0.0,\"lngL\":0.0,\"org\":null},{\"amount\":16572.4,\"qe\":3921.28,\"sgq\":0.87,\"xDu\":0.0,\"yDu\":0.0,\"xRu\":0.0,\"yRu\":0.0,\"xUu\":0.0,\"yUu\":0.0,\"xLu\":0.0,\"yLu\":0.0,\"h\":0.0,\"id\":0,\"name\":\"煤场西煤场北部1\",\"latD\":0.0,\"lngD\":0.0,\"status\":0,\"acctTime\":null,\"acctUser\":0,\"updateTime\":null,\"belong\":null,\"xD\":0.006864301342828952,\"yD\":195.89732907717007,\"xR\":171.61439787206663,\"yR\":195.89732907717007,\"xU\":171.61439787206663,\"yU\":103.446410214792,\"xL\":0.006864301342828952,\"yL\":103.446410214792,\"latR\":0.0,\"lngR\":0.0,\"latU\":0.0,\"lngU\":0.0,\"latL\":0.0,\"lngL\":0.0,\"org\":null},{\"amount\":8218.8,\"qe\":5284.62,\"sgq\":1.21,\"xDu\":0.0,\"yDu\":0.0,\"xRu\":0.0,\"yRu\":0.0,\"xUu\":0.0,\"yUu\":0.0,\"xLu\":0.0,\"yLu\":0.0,\"h\":0.0,\"id\":0,\"name\":\"煤场东煤场中部\",\"latD\":0.0,\"lngD\":0.0,\"status\":0,\"acctTime\":null,\"acctUser\":0,\"updateTime\":null,\"belong\":null,\"xD\":171.6075335707238,\"yD\":81.895074753377,\"xR\":343.2150671414476,\"yR\":81.895074753377,\"xU\":343.2150671414476,\"yU\":0.0,\"xL\":171.6075335707238,\"yL\":0.0,\"latR\":0.0,\"lngR\":0.0,\"latU\":0.0,\"lngU\":0.0,\"latL\":0.0,\"lngL\":0.0,\"org\":null},{\"amount\":0.0,\"qe\":4826.0,\"sgq\":1.34,\"xDu\":0.0,\"yDu\":0.0,\"xRu\":0.0,\"yRu\":0.0,\"xUu\":0.0,\"yUu\":0.0,\"xLu\":0.0,\"yLu\":0.0,\"h\":0.0,\"id\":0,\"name\":\"煤场西煤场南部1\",\"latD\":0.0,\"lngD\":0.0,\"status\":0,\"acctTime\":null,\"acctUser\":0,\"updateTime\":null,\"belong\":null,\"xD\":343.22193144279044,\"yD\":195.89732907717007,\"xR\":479.9931356986573,\"yR\":195.89732907717007,\"xU\":479.9931356986573,\"yU\":103.446410214792,\"xL\":343.22193144279044,\"yL\":103.446410214792,\"latR\":0.0,\"lngR\":0.0,\"latU\":0.0,\"lngU\":0.0,\"latL\":0.0,\"lngL\":0.0,\"org\":null}]}"


                , MeiChangMapBean.class);

        if (mapBean != null) {
            //绘制汽车沟数据
            for (int i = 1; i < carsName.length + 1; i++) {
                try {
                    GifDrawable gifFromAssets = new GifDrawable(getAssets(), carsName[i - 1]);
                    GifImageView gifImageView = new GifImageView(this);
                    gifImageView.setImageDrawable(gifFromAssets);

                    rl_meichangmap.addView(gifImageView);

                    if (i % 2 == 0) {
                        //偶数个时，
                        setMargins(gifImageView, (i / 2) * width, meichangHeightDistance + meiChangHeight, 0, 0);
                    } else {
                        //如果是奇数个，则位置与前一个的位置一致，
                        setMargins(gifImageView, (i - 1) / 2 * width, meichangHeightDistance + meiChangHeight, 0, 0);

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //绘制火车沟数据
            for (int i = 1; i < carsName.length + 1; i++) {
                try {
                    GifDrawable gifFromAssets = new GifDrawable(getAssets(), carsName[i - 1]);
                    GifImageView gifImageView = new GifImageView(this);
                    gifImageView.setImageDrawable(gifFromAssets);

                    rl_meichangmap.addView(gifImageView);

                    if (i % 2 == 0) {
                        //偶数个时，
                        setMargins(gifImageView, (int) TrainLeft + (i / 2) * width, meichangHeightDistance - height, 0, 0);
                    } else {
                        //如果是奇数个，则位置与前一个的位置一致，
                        setMargins(gifImageView, (int) TrainLeft + (i - 1) / 2 * width, meichangHeightDistance - height, 0, 0);

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        myMeiChangMapView.setData(mapBean);
        myMeiChangMapView.postInvalidate();
    }

    private void setMeiChangMap() {

        myMeiChangMapView = new MyMeiChangMapView(this, handler);
        rl_meichangmap.addView(myMeiChangMapView);
    }

    public void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.width = width;
            p.height = height;
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }
}
