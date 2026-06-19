package com.example.railora.ui.auth

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.railora.R
import com.example.railora.databinding.FragmentResetPasswordBinding

class ResetPasswordFragment : Fragment() {

    private var _binding: FragmentResetPasswordBinding? = null
    private val binding get() = _binding!!

    private var isNewPasswordVisible = false
    private var isConfirmPasswordVisible = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentResetPasswordBinding.inflate(
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

        binding.ivNewPasswordToggle.setOnClickListener {

            isNewPasswordVisible = !isNewPasswordVisible

            binding.etNewPassword.transformationMethod =
                if (isNewPasswordVisible)
                    HideReturnsTransformationMethod.getInstance()
                else
                    PasswordTransformationMethod.getInstance()

            binding.etNewPassword.setSelection(
                binding.etNewPassword.text.length
            )
        }

        binding.ivConfirmPasswordToggle.setOnClickListener {

            isConfirmPasswordVisible = !isConfirmPasswordVisible

            binding.etConfirmPassword.transformationMethod =
                if (isConfirmPasswordVisible)
                    HideReturnsTransformationMethod.getInstance()
                else
                    PasswordTransformationMethod.getInstance()

            binding.etConfirmPassword.setSelection(
                binding.etConfirmPassword.text.length
            )
        }

        binding.btnUpdatePassword.setOnClickListener {

            binding.etNewPassword.background =
                requireContext().getDrawable(
                    R.drawable.bg_login_input
                )

            binding.etConfirmPassword.background =
                requireContext().getDrawable(
                    R.drawable.bg_login_input
                )

            val newPassword =
                binding.etNewPassword.text.toString().trim()

            val confirmPassword =
                binding.etConfirmPassword.text.toString().trim()

            when {

                newPassword.isEmpty() -> {

                    binding.etNewPassword.background =
                        requireContext().getDrawable(
                            R.drawable.bg_login_input_error
                        )

                    Toast.makeText(
                        requireContext(),
                        "Enter new password",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@setOnClickListener
                }

                newPassword.length < 6 -> {

                    binding.etNewPassword.background =
                        requireContext().getDrawable(
                            R.drawable.bg_login_input_error
                        )

                    Toast.makeText(
                        requireContext(),
                        "Password must be at least 6 characters",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@setOnClickListener
                }

                confirmPassword.isEmpty() -> {

                    binding.etConfirmPassword.background =
                        requireContext().getDrawable(
                            R.drawable.bg_login_input_error
                        )

                    Toast.makeText(
                        requireContext(),
                        "Confirm password",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@setOnClickListener
                }

                newPassword != confirmPassword -> {

                    binding.etNewPassword.background =
                        requireContext().getDrawable(
                            R.drawable.bg_login_input_error
                        )

                    binding.etConfirmPassword.background =
                        requireContext().getDrawable(
                            R.drawable.bg_login_input_error
                        )

                    Toast.makeText(
                        requireContext(),
                        "Passwords do not match",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@setOnClickListener
                }
            }

            Toast.makeText(
                requireContext(),
                "Password updated successfully",
                Toast.LENGTH_SHORT
            ).show()

            findNavController().navigate(
                R.id.action_resetPasswordFragment_to_loginFragment
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}