package id.rushdroid.githubusersearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import id.rushdroid.githubusersearch.data.remote.model.DetailUserEntity
import id.rushdroid.githubusersearch.data.remote.model.RepoEntity
import id.rushdroid.githubusersearch.data.remote.model.UserEntity
import id.rushdroid.githubusersearch.data.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    // Fetch user details
    fun getUserDetail(username: String): LiveData<DetailUserEntity?> = liveData {
        emit(repository.getUserDetail(username))
    }

    // Fetch followers
    fun getFollowers(username: String): LiveData<List<UserEntity>> = liveData {
        emit(repository.getFollowers(username))
    }

    // Fetch following
    fun getFollowing(username: String): LiveData<List<UserEntity>> = liveData {
        emit(repository.getFollowing(username))
    }

    // Fetch repositories
    fun getRepos(username: String): LiveData<List<RepoEntity>> = liveData {
        emit(repository.getRepos(username))
    }
}
