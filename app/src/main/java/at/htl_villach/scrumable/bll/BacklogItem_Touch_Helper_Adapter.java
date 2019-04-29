package at.htl_villach.scrumable.bll;

import android.support.v7.widget.RecyclerView;

public interface BacklogItem_Touch_Helper_Adapter {
    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(RecyclerView.ViewHolder viewHolder, int position);
}
