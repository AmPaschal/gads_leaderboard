package com.ampaschal.gadsleaderboard.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.ampaschal.gadsleaderboard.R
import com.ampaschal.gadsleaderboard.network.FormResponseData
import com.ampaschal.gadsleaderboard.network.Status
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 */
class SubmitFragment : Fragment() {

    private val mainViewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_submit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etFirstName = view.findViewById<EditText>(R.id.et_first_name)
        val etLastName = view.findViewById<EditText>(R.id.et_last_name)
        val etEmail = view.findViewById<EditText>(R.id.et_email)
        val etProjectLink = view.findViewById<EditText>(R.id.et_project_link)
        val btSubmit = view.findViewById<Button>(R.id.bt_submit)
        val pbLoading = view.findViewById<ProgressBar>(R.id.pb_loading)

        mainViewModel.submitResponseStatus.observe(viewLifecycleOwner, Observer {
            when (it) {
                Status.SUCCESS -> {
                    pbLoading.visibility = View.GONE
                    findNavController().navigate(R.id.action_submitFragment_to_leaderFragment)
                }
                Status.ERROR -> {
                    pbLoading.visibility = View.GONE
                    val action = SubmitFragmentDirections.actionSubmitFragmentToResultDialogFragment(false)
                    findNavController().navigate(action)
                }
                Status.LOADING -> pbLoading.visibility = View.VISIBLE
                else -> Log.d(javaClass.simpleName, "Do nothing")
            }
        })

        btSubmit.setOnClickListener {
            if (etFirstName.text.isNullOrBlank() || etLastName.text.isNullOrBlank()
                || etEmail.text.isNullOrBlank() || etProjectLink.text.isNullOrBlank()) {
                Toast.makeText(requireContext(), "Some fields are blank", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val firstName = etFirstName.text.toString().trim()
            val lastName = etLastName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val projectLink = etProjectLink.text.toString().trim()

            val responseData = FormResponseData(firstName, lastName, email, projectLink)
            mainViewModel.saveResponseData(responseData)

            findNavController().navigate(R.id.action_submitFragment_to_confirmationDialogFragment)
        }
    }
}
