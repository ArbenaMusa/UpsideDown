package com.am.upsidedown.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.am.upsidedown.FeedActivity;
import com.am.upsidedown.R;
import com.am.upsidedown.feed.Feed2Fragment;
import com.am.upsidedown.models.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private static final String TAG = "RecyclerAdapter";
    List<User> workmanList;
    List<String> workmanListAll;
    Feed2Fragment feed2Fragment;

    public RecyclerAdapter(List<User> workmanList, Feed2Fragment feed2Fragment) {
        this.feed2Fragment = feed2Fragment;
        this.workmanList = workmanList;
        workmanListAll = new ArrayList<>();
        for (int i = 0; i < workmanList.size(); i++) {
            workmanListAll.add(workmanList.get(i).getName());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.rowCountTextView.setText(String.valueOf(position));
        holder.textView.setText(workmanList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return workmanList.size();
    }

//    @Override
//    public Filter getFilter() {
//
//        return myFilter;
//    }
//
//    Filter myFilter = new Filter() {
//
//        //Automatic on background thread
//        @Override
//        protected FilterResults performFiltering(CharSequence charSequence) {
//
//            List<String> filteredList = new ArrayList<>();
//
//            if (charSequence == null || charSequence.length() == 0) {
//                filteredList.addAll(workmanListAll);
//            } else {
//                for (String movie : workmanListAll) {
//                    if (movie.toLowerCase().contains(charSequence.toString().toLowerCase())) {
//                        filteredList.add(movie);
//                    }
//                }
//            }
//
//            FilterResults filterResults = new FilterResults();
//            filterResults.values = filteredList;
//            return filterResults;
//        }
//
//        //Automatic on UI thread
//        @Override
//        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//            workmanList.clear();
//            workmanList.addAll((Collection<? extends String>) filterResults.values);
//            notifyDataSetChanged();
//        }
//    };


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textView, rowCountTextView;
        ImageButton imageCall;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            rowCountTextView = itemView.findViewById(R.id.rowCountTextView);
            imageCall = itemView.findViewById(R.id.btn_call);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), workmanList.get(getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
            feed2Fragment.makePhoneCall();
        }
    }
}















