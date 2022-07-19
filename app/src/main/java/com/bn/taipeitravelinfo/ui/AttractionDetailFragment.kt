package com.bn.taipeitravelinfo.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bn.taipeitravelinfo.arch.BaseFragment
import com.bn.taipeitravelinfo.arch.OnItemClickListener
import com.bn.taipeitravelinfo.data.model.Attraction
import com.bn.taipeitravelinfo.databinding.FragmentAttractionDetailBinding
import com.bn.taipeitravelinfo.ktx.setVisible
import com.bn.taipeitravelinfo.ui.adapter.ImagePagerAdapter


class AttractionDetailFragment : BaseFragment<FragmentAttractionDetailBinding>() {
    private val args: AttractionDetailFragmentArgs by navArgs()
    private lateinit var selectedAttraction: Attraction

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedAttraction = args.attraction
        with(binding) {
            setupImagePagerList()
            setupAttractionInfo()
        }
    }

    private fun FragmentAttractionDetailBinding.setupAttractionInfo() =
        selectedAttraction.apply {
            nameText.text = name
            addressText.text = address
            telText.text = localTel
            introductionText.text = introduction
            ticket.let {
                if (it.isNotEmpty()) {
                    ticketRuleText.text = ticket
                    showTicketRuleLabel()
                }
            }
            remind.let {
                if (it.isNotEmpty()) {
                    remindText.text = remind
                    showRemindLabel()
                }
            }
            taipeiTravelLinkBtn.setOnClickListener {
                openLinkInBrowser(selectedAttraction.url)
            }
        }

    private fun FragmentAttractionDetailBinding.showTicketRuleLabel() =
        ticketRuleLabelText.setVisible()

    private fun FragmentAttractionDetailBinding.showRemindLabel() = remindLabelText.setVisible()


    private fun FragmentAttractionDetailBinding.setupImagePagerList() =
        imagePagerList.apply {
            adapter = ImagePagerAdapter(object : OnItemClickListener<Attraction.ImageSource> {
                override fun onItemClick(item: Attraction.ImageSource) {
                    //todo: view original image
                }
            }).apply {
                addItems(selectedAttraction.images)
            }
            layoutManager = LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

    private fun openLinkInBrowser(uri: String) =
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))

}