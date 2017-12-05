package chenshuai.bwie.com.q;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 不将就 on 2017/12/5.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<CatagoryBean.DataBean> list;
    private static final int TYPE1 = 0;
    private static final int TYPE2 = 1;

    public MyAdapter(Context context, List<CatagoryBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
RecyclerView.ViewHolder holder = null;
switch (viewType){
    case TYPE1:
        View view1 = LayoutInflater.from(context).inflate(R.layout.rv_item, parent, false);
        holder = new MyViewholder1(view1);

        break;
    case TYPE2:
        View view2 = LayoutInflater.from(context).inflate(R.layout.rv_item1, parent, false);
        holder = new MyViewholder2(view2);

        break;

}
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CatagoryBean.DataBean dataBean = list.get(position);
        switch (getItemViewType(position)){
            case TYPE1:
                MyViewholder1 myViewholder1 = (MyViewholder1) holder;
                myViewholder1.tv.setText(dataBean.getName());
                Glide.with(context).load(dataBean.getIcon()).into(myViewholder1.iv);
                break;
            case TYPE2:
                MyViewholder2  myViewholder2 = (MyViewholder2) holder;
                myViewholder2.tv.setText(dataBean.getName());
                break;
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
       // return position % 2 == 0 ? TYPE1 : TYPE2;

        return TYPE1;
    }





    class MyViewholder1 extends RecyclerView.ViewHolder {

        private final TextView tv;
        private final ImageView iv;

        public MyViewholder1(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            iv = itemView.findViewById(R.id.iv);
        }
    }

    class MyViewholder2 extends RecyclerView.ViewHolder {

        private final TextView tv;

        public MyViewholder2(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);

        }
    }

}

