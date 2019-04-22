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
import android.widget.Toast;

import java.util.ArrayList;

import at.htl_villach.scrumable.R;

public class BacklogItems_Adapter extends RecyclerView.Adapter<BacklogItems_Adapter.BacklogItemViewHolder> {
    private ArrayList<BacklogItem> backlogItemList;
    private Context context;
    private View view;
    private PopupOptionMenuEnum popupOptionMenuEnum;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class BacklogItemViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView describtion;
        public TextView editor;
        public TextView options;

        public BacklogItemViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            describtion = itemView.findViewById(R.id.describtion);
            editor = itemView.findViewById(R.id.editor);
            options = itemView.findViewById(R.id.options);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public BacklogItems_Adapter(ArrayList<BacklogItem> paramBacklogItemlist, Context context, PopupOptionMenuEnum popupOptionMenuEnum, View view) {
        this.backlogItemList = paramBacklogItemlist;
        this.context = context;
        this.popupOptionMenuEnum = popupOptionMenuEnum;
        this.view = view;
    }

    @NonNull
    @Override
    public BacklogItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.backlog_items, viewGroup, false);
        BacklogItemViewHolder backlogItemViewHolder = new BacklogItemViewHolder(view, mListener);

        return backlogItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BacklogItemViewHolder backlogItemViewHolder, final int position) {
        final BacklogItem selectedBacklogItem = backlogItemList.get(position);
        int curPosition = position;

        backlogItemViewHolder.title.setText(selectedBacklogItem.getTitle());
        backlogItemViewHolder.describtion.setText(selectedBacklogItem.getDescribtion());
        backlogItemViewHolder.editor.setText(selectedBacklogItem.getEditor().toString());

        backlogItemViewHolder.options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, backlogItemViewHolder.options);
                if(popupOptionMenuEnum == PopupOptionMenuEnum.PRODUCT_BACKLOG) {
                    popupMenu.inflate(R.menu.option_menu_backlog_item_productbacklog);
                    initPopUpMenuProductBacklog(popupMenu, selectedBacklogItem);
                } else if(popupOptionMenuEnum == PopupOptionMenuEnum.SPRINT_BACKLOG) {
                    popupMenu.inflate(R.menu.option_menu_backlog_item_sprintbacklog);
                    initPopUpMenuSprintBacklog(popupMenu, selectedBacklogItem);
                } else if(popupOptionMenuEnum == PopupOptionMenuEnum.SCRUMBOARD) {
                    popupMenu.inflate(R.menu.option_menu_backlog_item_scrumboard);
                    initPopUpMenuScrumboard(popupMenu, selectedBacklogItem);
                }
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return backlogItemList.size();
    }

    private void initPopUpMenuProductBacklog(final PopupMenu popupMenu, final BacklogItem selectedBacklogItem) {
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_move_to_SprintBL:
                        backlogItemList.remove(selectedBacklogItem);
                        notifyDataSetChanged();

                        //ToDo: Move Item to Sprint BL

                        //SprintBacklog_Fragment fragment = SprintBacklog_Fragment.newInstance();
                        //fragment.addListItem(selectedBacklogItem, view);

                        Toast.makeText(context, "Successfully moved '" + selectedBacklogItem.getTitle() + "' to Sprint Backlog", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.item_move_to_Scrumboard:
                        backlogItemList.remove(selectedBacklogItem);
                        notifyDataSetChanged();

                        //ToDo: Move Item to Scrumboard - Column ToDo

                        Toast.makeText(context, "Successfully moved '" + selectedBacklogItem.getTitle() + "' to Scrumboard", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    private void initPopUpMenuSprintBacklog(final PopupMenu popupMenu, final BacklogItem selectedBacklogItem) {
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_move_to_ProductBL:
                        backlogItemList.remove(selectedBacklogItem);
                        notifyDataSetChanged();

                        //ToDo: Move Item to Product BL

                        Toast.makeText(context, "Successfully moved '" + selectedBacklogItem.getTitle() + "' to Product Backlog", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.item_move_to_Scrumboard:
                        backlogItemList.remove(selectedBacklogItem);
                        notifyDataSetChanged();

                        //ToDo: Move Item to Scrumboard

                        Toast.makeText(context, "Successfully moved '" + selectedBacklogItem.getTitle() + "' to Scrumboard", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    private void initPopUpMenuScrumboard(final PopupMenu popupMenu, final BacklogItem selectedBacklogItem) {
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_move_to_ProductBL:
                        backlogItemList.remove(selectedBacklogItem);
                        notifyDataSetChanged();

                        //ToDo: Move Item to Product BL

                        Toast.makeText(context, "Successfully moved '" + selectedBacklogItem.getTitle() + "' to Product Backlog", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.item_move_to_SprintBL:
                        backlogItemList.remove(selectedBacklogItem);
                        notifyDataSetChanged();

                        //ToDo: Move Item to Sprint BL

                        Toast.makeText(context, "Successfully moved '" + selectedBacklogItem.getTitle() + "' to Sprint Backlog", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }
}