package bobby.irawan.githubuser.presentation.detailuser.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import bobby.irawan.githubuser.R
import bobby.irawan.githubuser.databinding.FragmentFollowerBinding
import bobby.irawan.githubuser.presentation.detailuser.adapter.FollowerAdapter
import bobby.irawan.githubuser.presentation.detailuser.viewmodel.DetailUserViewModel
import bobby.irawan.githubuser.presentation.model.Follower
import bobby.irawan.githubuser.utils.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FollowerFragment : Fragment() {

    private var binding: FragmentFollowerBinding? = null
    private val viewModel by sharedViewModel<DetailUserViewModel>()
    private val adapter by lazy {
        FollowerAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.shimmerFrameLayoutLoading?.setVisibleShimmer()
        binding?.recyclerViewFollower?.adapter = adapter
        viewModel.getFollowerData()
        observerViewModel()
    }

    private fun observerViewModel() {
        viewModel.followerResult.observe(viewLifecycleOwner, Observer { onShowSuccessResult(it) })
        viewModel.errorLoadFollower.observe(viewLifecycleOwner, Observer { onShowErrorResult(it) })
    }

    private fun onShowSuccessResult(followers: List<Follower>) {
        adapter.setDataFollower(followers)
        binding?.shimmerFrameLayoutLoading?.setGoneShimmer()
        binding?.textViewEmptyDataMessage?.isShowEmptyInfo(followers, ::isShowEmptyInfo)
    }

    private fun onShowErrorResult(errorMessage: String) {
        val message = requireContext().getString(R.string.error_message_load_follower, errorMessage)
        binding?.shimmerFrameLayoutLoading?.setGoneShimmer()
        val view = requireActivity().findViewById(android.R.id.content) as View
        view.showErrorSnackbar(message)
    }

    private fun isShowEmptyInfo() {
        binding?.recyclerViewFollower?.setGone()
        binding?.shimmerFrameLayoutLoading?.setGone()
        binding?.textViewEmptyDataMessage?.setVisible()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}