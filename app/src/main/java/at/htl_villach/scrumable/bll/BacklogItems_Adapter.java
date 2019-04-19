package at.htl_villach.scrumable.bll;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

import at.htl_villach.scrumable.R;

public class BacklogItems_Adapter extends RecyclerView.Adapter<BacklogItems_Adapter.BacklogItemViewHolder> {
    private ArrayList<BacklogItem> backlogItemList;
    public static class BacklogItemViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView describtion;
        public TextView editor;

        public BacklogItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            describtion = itemView.findViewById(R.id.describtion);
            editor = itemView.findViewById(R.id.editor);
        }
    }

    public BacklogItems_Adapter(ArrayList<BacklogItem> paramBacklogItemlist) {
        backlogItemList = paramBacklogItemlist;
    }

    @NonNull
    @Override
    public BacklogItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.backlog_items, viewGroup, false);
        BacklogItemViewHolder backlogItemViewHolder = new BacklogItemViewHolder(view);

        return backlogItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BacklogItemViewHolder backlogItemViewHolder, int position) {
        BacklogItem selectedBacklogItem = backlogItemList.get(position);
        backlogItemViewHolder.title.setText(selectedBacklogItem.getTitle());
        backlogItemViewHolder.describtion.setText(selectedBacklogItem.getDescribtion());
        backlogItemViewHolder.editor.setText(selectedBacklogItem.getEditor().toString());
    }

    @Override
    public int getItemCount() {
        return backlogItemList.size();
    }
}
