package ru.skillbranch.devintensive.ui.adapters

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.skillbranch.devintensive.models.data.ChatItem

class ChatItemTouchHelperCallback(val adapter:ChatAdapter,
                                  val swipeListener : (ChatItem)->Unit
):ItemTouchHelper.Callback()
{
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int
    {
        return if(viewHolder is ItemTouchViewHolder)
        {
            makeFlag(ItemTouchHelper.ACTION_STATE_SWIPE, ItemTouchHelper.START)
        }
        else
        {
            makeFlag(ItemTouchHelper.ACTION_STATE_IDLE, ItemTouchHelper.START)
        }
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean
    {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int)
    {
        swipeListener.invoke(adapter.items[viewHolder.adapterPosition])
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if(actionState != ItemTouchHelper.ACTION_STATE_IDLE && viewHolder is ItemTouchViewHolder)
        {
            viewHolder.onItemSelected()
        }
        super.onSelectedChanged(viewHolder, actionState)

    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        if(viewHolder is ItemTouchViewHolder)
        {
            viewHolder.onItemCleared()
        }
        super.clearView(recyclerView, viewHolder)
    }

    override fun onChildDraw(
        canvas: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE)
        {
            val itemView = viewHolder.itemView
            drawBackground(canvas, itemView, dX)
            drawIcon(canvas, itemView, dX)
        }
        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private fun drawIcon(canvas: Canvas, itemView: View, dX: Float) {

    }

    private fun drawBackground(canvas: Canvas, itemView: View, dX: Float)
    {
        
    }

}

interface ItemTouchViewHolder
{
    fun onItemSelected()
    fun onItemCleared()
}