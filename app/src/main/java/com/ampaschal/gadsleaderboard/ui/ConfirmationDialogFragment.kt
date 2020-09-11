package com.ampaschal.gadsleaderboard.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ampaschal.gadsleaderboard.R
import com.ampaschal.gadsleaderboard.network.Status
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ConfirmationDialogFragment: DialogFragment() {

    private val mainViewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog_confirmation, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageCancel = view.findViewById<ImageView>(R.id.image_cancel)
        imageCancel.setOnClickListener {
            dismiss()
        }

        val btSubmit = view.findViewById<Button>(R.id.bt_confirm)
        btSubmit.setOnClickListener {

            if (!mainViewModel.isResponseDataAvailable()) {
                Toast.makeText(requireContext(), "An error occured, please try again", Toast.LENGTH_SHORT).show()
                dismiss()
                return@setOnClickListener
            }

            mainViewModel.submitFormResponse()
            dismiss()


        }
    }
}