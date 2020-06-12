package com.example.volley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    //LayoutInflater inflater;
    private Context context;
    private List<User> userlist;


   public CustomAdapter(Context context, List userlist)
   {
       this.context=context;
       this.userlist=userlist;
   }

    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View listitem=layoutInflater.inflate(R.layout.example_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(listitem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(userlist.get(position));
        User user=userlist.get(position);
        holder.t1.setText(user.getId());
        holder.t2.setText(user.getName());
        holder.t3.setText(user.getEmail());
        holder.t4.setText(user.getGender());
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView t1,t2,t3,t4;
        public ViewHolder(@NonNull View v) {
            super(v);
            t1=(TextView) v.findViewById(R.id.t1);
            t2=(TextView)v.findViewById(R.id.t2);
            t3=(TextView)v.findViewById(R.id.t3);
            t4=(TextView)v.findViewById(R.id.t4);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    User u=(User)view.getTag();
                    Toast.makeText(view.getContext(), u.getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
