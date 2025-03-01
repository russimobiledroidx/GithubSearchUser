package id.rushdroid.githubusersearch.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import id.rushdroid.githubusersearch.R
import id.rushdroid.githubusersearch.utils.MyApplication
import id.rushdroid.githubusersearch.viewmodel.UserDetailViewModel

@AndroidEntryPoint
class RepositoryFragment : Fragment() {
    private val viewModel: UserDetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_repository, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = arguments?.getString(ARG_USERNAME) ?: return

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val adapter = RepositoryAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.getRepos(username).observe(viewLifecycleOwner) { repos ->
            adapter.submitList(repos)
        }
    }

    companion object {
        private const val ARG_USERNAME = "username"
        fun newInstance(username: String) = RepositoryFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_USERNAME, username)
            }
        }
    }
}
