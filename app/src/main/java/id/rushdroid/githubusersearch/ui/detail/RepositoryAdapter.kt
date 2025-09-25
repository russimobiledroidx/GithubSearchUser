package id.rushdroid.githubusersearch.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import id.rushdroid.githubusersearch.R
import id.rushdroid.githubusersearch.data.remote.model.RepoEntity

class RepositoryAdapter : ListAdapter<RepoEntity, RepositoryAdapter.RepoViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val repoNameTextView: TextView = itemView.findViewById(R.id.repoNameTextView)
        private val repoDescriptionTextView: TextView = itemView.findViewById(R.id.repoDescriptionTextView)
        private val languageTextView: TextView = itemView.findViewById(R.id.languageTextView)
        private val starsTextView: TextView = itemView.findViewById(R.id.starsTextView)
        private val forksTextView: TextView = itemView.findViewById(R.id.forksTextView)
        private val topicsContainer: ViewGroup = itemView.findViewById(R.id.topicsContainer)

        fun bind(repo: RepoEntity) {
            repoNameTextView.text = repo.name
            repoDescriptionTextView.text = repo.description ?: "No description available"
            languageTextView.text = repo.language ?: "Unknown"
            starsTextView.text = "${repo.stars} Stars"
            forksTextView.text = "${repo.forks} Forks"

            // Clear previous topics
            topicsContainer.removeAllViews()

            // Add topics dynamically
            repo.topics?.forEach { topic ->
                val topicView = LayoutInflater.from(itemView.context).inflate(R.layout.topic_item, topicsContainer, false)
                val topicChip: Chip = topicView.findViewById(R.id.topicText)
                topicChip.text = topic
                topicsContainer.addView(topicView)
            }

            // Show or hide topics container based on availability
            topicsContainer.visibility = if (repo.topics.isNullOrEmpty()) View.GONE else View.VISIBLE
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RepoEntity>() {
            override fun areItemsTheSame(oldItem: RepoEntity, newItem: RepoEntity) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: RepoEntity, newItem: RepoEntity) = oldItem == newItem
        }
    }
}
