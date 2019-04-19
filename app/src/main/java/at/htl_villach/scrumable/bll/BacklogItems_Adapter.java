package at.htl_villach.scrumable.bll;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

import at.htl_villach.scrumable.R;

public class BacklogItems_Adapter extends RecyclerView.Adapter<BacklogItems_Adapter.BacklogItemViewHolder> {
    private ArrayList<BacklogItem> backlogItemList;
    private Context context;

    public static class BacklogItemViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView describtion;
        public TextView editor;
        public TextView options;

        public BacklogItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            describtion = itemView.findViewById(R.id.describtion);
            editor = itemView.findViewById(R.id.editor);
            options = itemView.findViewById(R.id.options);
        }
    }

    public BacklogItems_Adapter(ArrayList<BacklogItem> paramBacklogItemlist, Context context) {
        this.backlogItemList = paramBacklogItemlist;
        this.context = context;
    }

    @NonNull
    @Override
    public BacklogItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.backlog_items, viewGroup, false);
        BacklogItemViewHolder backlogItemViewHolder = new BacklogItemViewHolder(view);

        return backlogItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BacklogItemViewHolder backlogItemViewHolder, final int position) {
        BacklogItem selectedBacklogItem = backlogItemList.get(position);

        backlogItemViewHolder.title.setText(selectedBacklogItem.getTitle());
        backlogItemViewHolder.describtion.setText(selectedBacklogItem.getDescribtion());
        backlogItemViewHolder.editor.setText(selectedBacklogItem.getEditor().toString());

        backlogItemViewHolder.options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, backlogItemViewHolder.options);
                popupMenu.inflate(R.menu.option_menu_backlog_item);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case 0:
                                //ToDo: Move Item to Product BL
                                /*
                                backlogItemList.remove(position);
                                notifyDataSetChanged();
                                */
                                break;
                            case 1:
                                //ToDo: Move Item to Sprint BL
                                /*
                                backlogItemList.remove(position);
                                notifyDataSetChanged();
                                */
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return backlogItemList.size();
    }
}
