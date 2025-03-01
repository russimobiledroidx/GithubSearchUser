package id.rushdroid.githubusersearch.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.rushdroid.githubusersearch.data.remote.model.RepoEntity

@Dao
interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(repos: List<RepoEntity>)

    @Query("SELECT * FROM repos WHERE login = :username")
    suspend fun getReposByUsername(username: String): List<RepoEntity>

    @Query("DELETE FROM repos")
    suspend fun deleteAllRepos()
}
