package bobby.irawan.githubuser.presentation.detailuser.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import bobby.irawan.githubuser.R
import bobby.irawan.githubuser.databinding.FragmentFollowingBinding
import bobby.irawan.githubuser.presentation.detailuser.adapter.FollowingAdapter
import bobby.irawan.githubuser.presentation.detailuser.viewmodel.DetailUserViewModel
import bobby.irawan.githubuser.presentation.model.Following
import bobby.irawan.githubuser.utils.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FollowingFragment : Fragment() {
    private var binding: FragmentFollowingBinding? = null
    private val viewModel by sharedViewModel<DetailUserViewModel>()
    private val adapter by lazy {
        FollowingAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.shimmerFrameLayoutLoading?.setVisibleShimmer()
        binding?.recyclerViewFollowing?.adapter = adapter
        viewModel.getFollowingData()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.followingResult.observe(viewLifecycleOwner, Observer { onShowSuccessResult(it) })
        viewModel.errorLoadFollowing.observe(viewLifecycleOwner, Observer { onShowErrorResult(it) })
    }

    private fun onShowSuccessResult(following: List<Following>) {
        adapter.setDataFollowing(following)
        binding?.recyclerViewFollowing?.adapter = adapter
        binding?.shimmerFrameLayoutLoading?.setGoneShimmer()
        binding?.textViewEmptyDataMessage?.isShowEmptyInfo(following, ::isShowEmptyInfo)
    }

    private fun onShowErrorResult(errorMessage: String) {
        val message =
            requireContext().getString(R.string.error_message_load_following, errorMessage)
        binding?.shimmerFrameLayoutLoading?.setGoneShimmer()
        val view = requireActivity().findViewById(android.R.id.content) as View
        view.showErrorSnackbar(message)
    }

    private fun isShowEmptyInfo() {
        binding?.recyclerViewFollowing?.setGone()
        binding?.shimmerFrameLayoutLoading?.setGone()
        binding?.textViewEmptyDataMessage?.setVisible()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}