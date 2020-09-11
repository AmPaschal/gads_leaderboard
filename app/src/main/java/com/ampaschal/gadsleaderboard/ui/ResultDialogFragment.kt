package com.ampaschal.gadsleaderboard.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.ampaschal.gadsleaderboard.R

class ResultDialogFragment: DialogFragment() {

    private val args: ResultDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog_result, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageResult = view.findViewById<ImageView>(R.id.image_result)
        val tvResult = view.findViewById<TextView>(R.id.tv_result)

        if (args.successful) {
            imageResult.setImageResource(R.drawable.ic_success)
            tvResult.text = "Submission Successful"
        } else {
            imageResult.setImageResource(R.drawable.ic_error)
            tvResult.text = "Submission Not Successful"
        }
    }
}