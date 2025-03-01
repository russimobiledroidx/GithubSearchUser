package id.rushdroid.githubusersearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.rushdroid.githubusersearch.data.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    fun getUsers() = liveData {
        emit(repository.getUsers())
    }

    fun searchUsers(query: String) = liveData {
        emit(repository.searchUsers(query))
    }

}
