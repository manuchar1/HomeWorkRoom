package com.example.homeworkroom.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.homeworkroom.data.User
import com.example.homeworkroom.databinding.UserInputFragmentBinding

/**პროექტის მიზანია მონაცემების მხოლოდ ბაზაში შენახვა და
 * მომზადება შემდეგი მანიპულაციებისთვის**/

class UserInputFragment : Fragment() {



    private val viewModel by viewModels<UserInputViewModel>()

    private var binding: UserInputFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UserInputFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnAdd?.setOnClickListener {
            insertDataToDataBase()
        }

    }

    private fun insertDataToDataBase() {
        val firstName = binding?.etFirstName?.text.toString()
        val lastName = binding?.etLastName?.text.toString()
        val age = binding?.etAge?.text
        val address = binding?.etAddress?.text.toString()
        val height = binding?.etHeight?.text.toString()



        if (inputCheck(firstName, lastName, age!!, address, height)) {
            val user = User(0,firstName,lastName,Integer.parseInt(age.toString()),address,height)
            viewModel.addUser(user)
            Toast.makeText(requireContext(), "User Added", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(), "Fields Are Empty", Toast.LENGTH_SHORT).show()

        }
    }

    private fun inputCheck(
        firstName: String,
        lastName: String,
        age: Editable,
        address: String,
        height: String
    ): Boolean {
        return !(TextUtils.isEmpty(firstName)
                && TextUtils.isEmpty(lastName)
                && TextUtils.isEmpty(age)
                && TextUtils.isEmpty(address)
                && height.isEmpty())

    }

}