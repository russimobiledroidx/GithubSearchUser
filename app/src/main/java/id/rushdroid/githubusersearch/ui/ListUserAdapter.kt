package id.rushdroid.githubusersearch.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.rushdroid.githubusersearch.R
import id.rushdroid.githubusersearch.data.remote.model.UserEntity

class ListUserAdapter(private val onClick: (UserEntity) -> Unit) :
    ListAdapter<UserEntity, ListUserAdapter.UserViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
        holder.itemView.setOnClickListener { onClick(user) } // Pass the UserEntity
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatarImageView: ImageView = itemView.findViewById(R.id.avatarItem)
        private val usernameTextView: TextView = itemView.findViewById(R.id.usernameItem)

        fun bind(user: UserEntity) {
            usernameTextView.text = "@${user.login}"
            Glide.with(itemView.context)
                .load(user.avatarUrl)
                .into(avatarImageView)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserEntity>() {
            override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean =
                oldItem.login == newItem.login

            override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean =
                oldItem == newItem
        }
    }
}

