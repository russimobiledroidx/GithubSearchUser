package id.rushdroid.githubusersearch.data.remote.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class RepoEntity(
    @PrimaryKey val id: Int,
    val login: String,
    val name: String,
    val description: String?,
    val language: String?,
    val stars: Int,
    val forks: Int,
    val topics: List<String?>?, // Ensure this field is present
    val updatedAt: String
)
