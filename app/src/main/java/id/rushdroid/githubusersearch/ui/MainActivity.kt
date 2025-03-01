package id.rushdroid.githubusersearch.ui

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import id.rushdroid.githubusersearch.R
import id.rushdroid.githubusersearch.viewmodel.UserViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<UserViewModel>()
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView= findViewById(R.id.recyclerView) // Ensure you have this ID in your layout
        searchView = findViewById(R.id.searchView)
        val adapter = ListUserAdapter { user ->
            // Pass the username (login) to the UserDetailActivity
            startActivity(UserDetailActivity.newIntent(this, user.login))
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.getUsers().observe(this) { users ->
            adapter.submitList(users)
        }

        searchView.setOnClickListener {
            searchView.isIconified = false // Expand the SearchView
            searchView.requestFocus() // Request focus
            // Optionally, show the keyboard
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(searchView, InputMethodManager.SHOW_IMPLICIT)
        }


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchUsers(it).observe(this@MainActivity) { users ->
                        adapter.submitList(users)
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

    }

}
