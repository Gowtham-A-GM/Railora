package com.example.railora.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.railora.databinding.FragmentLoginBinding
import androidx.navigation.fragment.findNavController
import com.example.railora.MainActivity
import com.example.railora.R
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod

/**
 * Temporary Login placeholder.
 *
 * Will be replaced with the actual login screen later.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var isPasswordVisible = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            binding.etUsername.background =
                requireContext().getDrawable(R.drawable.bg_login_input)

            binding.etPassword.background =
                requireContext().getDrawable(R.drawable.bg_login_input)

            when {

                username.isEmpty() -> {

                    binding.etUsername.background =
                        requireContext().getDrawable(
                            R.drawable.bg_login_input_error
                        )

                    Toast.makeText(
                        requireContext(),
                        "Enter Email or Phone Number",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@setOnClickListener
                }

                username.all { it.isDigit() } &&
                        username.length != 10 -> {

                    binding.etUsername.background =
                        requireContext().getDrawable(
                            R.drawable.bg_login_input_error
                        )

                    Toast.makeText(
                        requireContext(),
                        "Enter a valid 10-digit Phone Number",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@setOnClickListener
                }

                !username.all { it.isDigit() } &&
                        !android.util.Patterns.EMAIL_ADDRESS
                            .matcher(username)
                            .matches() -> {

                    binding.etUsername.background =
                        requireContext().getDrawable(
                            R.drawable.bg_login_input_error
                        )

                    Toast.makeText(
                        requireContext(),
                        "Enter a valid Email Address",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@setOnClickListener
                }

                password.isEmpty() -> {

                    binding.etPassword.background =
                        requireContext().getDrawable(
                            R.drawable.bg_login_input_error
                        )

                    Toast.makeText(
                        requireContext(),
                        "Enter Password",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@setOnClickListener
                }

                password.length < 6 -> {

                    binding.etPassword.background =
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
            }

            val intent =
                Intent(requireContext(), MainActivity::class.java)

            startActivity(intent)
            requireActivity().finish()
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

        binding.tvForgotPassword.setOnClickListener {

            findNavController().navigate(
                R.id.action_loginFragment_to_forgotPasswordFragment
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}