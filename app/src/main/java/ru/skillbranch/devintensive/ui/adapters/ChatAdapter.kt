package ru.skillbranch.devintensive.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_chat_single.view.*
import ru.examples.customview.ui.custom.AvatarImageView
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.data.ChatItem

class ChatAdapter(val listener: (ChatItem)->Unit) : RecyclerView.Adapter<ChatAdapter.SingleViewHolder>()
{
    var items : List<ChatItem> = listOf() // список чатов



    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SingleViewHolder, position: Int)
    {
        holder.bind(items[position], listener)
    }

    fun updateData(data : List<ChatItem>)
    {
        val diffCallback = object : DiffUtil.Callback()
        {
            override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean = items[oldPos].id == data[newPos].id

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = data[oldItemPosition].hashCode() == items[newItemPosition].hashCode()

            override fun getOldListSize(): Int = items.size

            override fun getNewListSize(): Int = data.size
        }

        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items = data
        diffResult.dispatchUpdatesTo(this)
        //notifyDataSetChanged() // уведомляем что данные изменены
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleViewHolder
    {
        val inflater = LayoutInflater.from(parent.context)
        val convertView = inflater.inflate(R.layout.item_chat_single, parent, false)
        return SingleViewHolder(convertView)
    }

    inner class SingleViewHolder(convertView: View) : RecyclerView.ViewHolder(convertView), LayoutContainer,
            ItemTouchViewHolder
    {
        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemCleared() {
            itemView.setBackgroundColor(Color.WHITE)
        }

        override val containerView: View?
            get() = itemView

        fun bind(item:ChatItem, listener: (ChatItem)->Unit)  //  связываем представление и данные из ChatItem
        {
            if(item.avatar == null)
            {
                itemView.iv_avatar_single.setInitials(item.initials)
            }
            else
            {
                // todo
            }
            itemView.sv_indicator.visibility = if(item.isOnline) View.VISIBLE else View.GONE
            with(itemView.tv_date_single)
            {
                visibility = if(item.lastMessageDate != null) View.VISIBLE else View.GONE
                text = item.lastMessageDate
            }

            with(itemView.tv_counter_single)
            {
                visibility = if(item.messageCount > 0) View.VISIBLE else View.GONE
                text = item.messageCount.toString()
            }

            itemView.tv_title_single.text = item.title
            itemView.tv_message_single.text = item.shortDescription
            itemView.setOnClickListener(fun(it: View) = listener.invoke(item))
        }
        // старая версия, достаточно долго работает
//        val iv_avatar = convertView.findViewById<AvatarImageView>(R.id.iv_avatar_single)
//        val tv_title = convertView.findViewById<TextView>(R.id.tv_title_single)
//
//        fun bind(item:ChatItem)  //  связываем представление и данные из ChatItem
//        {
//            iv_avatar.setInitials(item.initials)
//            tv_title.text = item.shortDescription
//        }
    }


}