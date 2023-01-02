package com.example.yugiohdeckgenarator.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.yugiohdeckgenarator.base.BaseFragment
import com.example.yugiohdeckgenarator.databinding.FragmentDetailBinding
import com.example.yugiohdeckgenarator.utils.downloadFromUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val args : DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cardImageView.downloadFromUrl(args.url)
    }

}