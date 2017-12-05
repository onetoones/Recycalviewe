package chenshuai.bwie.com.q;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRv;
    private Handler handler = new Handler(Looper.getMainLooper());
    private List<CatagoryBean.DataBean> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);

        mRv.setLayoutManager(gridLayoutManager);
        //添加分割线
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        DividerGridItemDecoration dividerGridItemDecoration = new DividerGridItemDecoration(this);
        mRv.addItemDecoration(dividerGridItemDecoration);
//绑定适配器
        final MyAdapter adapter = new MyAdapter(MainActivity.this, list);
        mRv.setAdapter(adapter);
        Request reqest = new Request.Builder().url("http://120.27.23.105/product/getCatagory").build();
        new OkHttpClient().newCall(reqest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final CatagoryBean catagoryBean = new Gson().fromJson(string, CatagoryBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //添加数据并刷新
                        list.addAll(catagoryBean.getData());
                        adapter.notifyDataSetChanged();

                    }
                });

            }
        });
    }
class  MyDivider extends RecyclerView.ItemDecoration{
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        c.drawColor(Color.RED);
    }
}
    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv);
    }
}
