package com.example.railora.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.railora.R
import com.example.railora.databinding.FragmentSignUpBinding
import androidx.navigation.fragment.findNavController
import android.app.DatePickerDialog
import android.content.Intent
import android.widget.Toast
import com.example.railora.MainActivity
import java.util.Calendar
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private var isPasswordVisible = false
    private var isConfirmPasswordVisible = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSignUpBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCreateAccount.setOnClickListener {

            val fullName =
                binding.etFullName.text.toString().trim()

            val aadhaar =
                binding.etAadhaar.text.toString().trim()

            val dob =
                binding.etDob.text.toString().trim()

            val email =
                binding.etEmail.text.toString().trim()

            val phoneNumber =
                binding.etPhoneNumber.text.toString().trim()

            val password =
                binding.etPassword.text.toString().trim()

            val confirmPassword =
                binding.etConfirmPassword.text.toString().trim()

            listOf(
                binding.etFullName,
                binding.etAadhaar,
                binding.etDob,
                binding.etEmail,
                binding.etPhoneNumber,
                binding.etPassword,
                binding.etConfirmPassword
            ).forEach {

                it.background =
                    requireContext().getDrawable(
                        R.drawable.bg_login_input
                    )
            }

            when {

                fullName.isEmpty() -> {

                    binding.etFullName.background =
                        requireContext().getDrawable(
                            R.drawable.bg_login_input_error
                        )

                    binding.etFullName.requestFocus()

                    showToast("Enter Full Name")
                    return@setOnClickListener
                }

                fullName.length < 3 -> {

                    binding.etFullName.background =
                        requireContext().getDrawable(
                            R.drawable.bg_login_input_error
                        )

                    showToast("Name must contain at least 3 characters")
                    return@setOnClickListener
                }

                aadhaar.isEmpty() -> {

                    binding.etAadhaar.background =
                        requireContext().getDrawable(
                            R.drawable.bg_login_input_error
                        )

                    showToast("Enter Aadhaar Number")
                    return@setOnClickListener
                }

                aadhaar.length != 12 -> {

                    binding.etAadhaar.background =
                        requireContext().getDrawable(
                            R.drawable.bg_login_input_error
                        )

                    showToast("Aadhaar must contain 12 digits")
                    return@setOnClickListener
                }

                dob.isEmpty() -> {

                    binding.etDob.background =
                        requireContext().getDrawable(
                            R.drawable.bg_login_input_error
                        )

                    showToast("Select Date Of Birth")
                    return@setOnClickListener
                }

                email.isEmpty() -> {

                    binding.etEmail.background =
                        requireContext().getDrawable(
                            R.drawable.bg_login_input_error
                        )

                    showToast("Enter Email")
                    return@setOnClickListener
                }

                !android.util.Patterns.EMAIL_ADDRESS
                    .matcher(email)
                    .matches() -> {

                    binding.etEmail.background =
                        requireContext().getDrawable(
                            R.drawable.bg_login_input_error
                        )

                    showToast("Enter Valid Email")
                    return@setOnClickListener
                }

                phoneNumber.isEmpty() -> {

                    binding.etPhoneNumber.background =
                        requireContext().getDrawable(
                            R.drawable.bg_login_input_error
                        )

                    showToast("Enter Phone Number")
                    return@setOnClickListener
                }

                phoneNumber.length != 10 -> {

                    binding.etPhoneNumber.background =
                        requireContext().getDrawable(
                            R.drawable.bg_login_input_error
                        )

                    showToast("Phone Number must contain 10 digits")
                    return@setOnClickListener
                }

                password.isEmpty() -> {

                    binding.etPassword.background =
                        requireContext().getDrawable(
                            R.drawable.bg_login_input_error
                        )

                    showToast("Enter Password")
                    return@setOnClickListener
                }

                password.length < 6 -> {

                    binding.etPassword.background =
                        requireContext().getDrawable(
                            R.drawable.bg_login_input_error
                        )

                    showToast("Password must be at least 6 characters")
                    return@setOnClickListener
                }

                confirmPassword.isEmpty() -> {

                    binding.etConfirmPassword.background =
                        requireContext().getDrawable(
                            R.drawable.bg_login_input_error
                        )

                    showToast("Confirm Password")
                    return@setOnClickListener
                }

                password != confirmPassword -> {

                    binding.etConfirmPassword.background =
                        requireContext().getDrawable(
                            R.drawable.bg_login_input_error
                        )

                    showToast("Passwords do not match")
                    return@setOnClickListener
                }
            }

            val intent =
                Intent(requireContext(), MainActivity::class.java)

            startActivity(intent)
            requireActivity().finish()
        }


        binding.tvLogin.setOnClickListener {
            findNavController().navigate(
                R.id.action_signUpFragment_to_loginFragment
            )
        }

        binding.etDob.setOnClickListener {

            val calendar = Calendar.getInstance()

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, selectedYear, selectedMonth, selectedDay ->

                    val formattedDate =
                        String.format(
                            "%02d/%02d/%04d",
                            selectedDay,
                            selectedMonth + 1,
                            selectedYear
                        )

                    binding.etDob.setText(formattedDate)
                },
                year,
                month,
                day
            )

            datePickerDialog.datePicker.maxDate =
                System.currentTimeMillis()

            datePickerDialog.show()
        }

        binding.ivPasswordToggle.setOnClickListener {

            isPasswordVisible = !isPasswordVisible

            if (isPasswordVisible) {

                binding.etPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()

                binding.ivPasswordToggle.setImageResource(
                    R.drawable.ic_eye_off
                )

            } else {

                binding.etPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()

                binding.ivPasswordToggle.setImageResource(
                    R.drawable.ic_eye
                )
            }

            binding.etPassword.setSelection(
                binding.etPassword.text.length
            )
        }

        binding.ivConfirmPasswordToggle.setOnClickListener {

            isConfirmPasswordVisible = !isConfirmPasswordVisible

            if (isConfirmPasswordVisible) {

                binding.etConfirmPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()

                binding.ivConfirmPasswordToggle.setImageResource(
                    R.drawable.ic_eye_off
                )

            } else {

                binding.etConfirmPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()

                binding.ivConfirmPasswordToggle.setImageResource(
                    R.drawable.ic_eye
                )
            }

            binding.etConfirmPassword.setSelection(
                binding.etConfirmPassword.text.length
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun showToast(
        message: String
    ) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}