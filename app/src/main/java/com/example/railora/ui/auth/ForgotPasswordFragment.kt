package com.example.railora.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.railora.R
import com.example.railora.databinding.FragmentForgotPasswordBinding
import com.example.railora.utils.OtpTextWatcher

class ForgotPasswordFragment : Fragment() {

    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    private var otpRequested = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentForgotPasswordBinding.inflate(
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

        setupOtpInputs()

        binding.tvBackToLogin.setOnClickListener {

            findNavController().navigateUp()
        }

        binding.btnGetOtp.setOnClickListener {

            if (!otpRequested) {

                val phoneNumber =
                    binding.etPhoneNumber.text.toString().trim()

                if (phoneNumber.length != 10) {

                    Toast.makeText(
                        requireContext(),
                        "Enter valid phone number",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@setOnClickListener
                }

                binding.tvOtpLabel.visibility = View.VISIBLE
                binding.layoutOtp.visibility = View.VISIBLE

                binding.btnGetOtp.text =
                    getString(R.string.verify_otp)

                otpRequested = true

                Toast.makeText(
                    requireContext(),
                    "OTP sent successfully",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val otp =
                    binding.etOtp1.text.toString() +
                            binding.etOtp2.text.toString() +
                            binding.etOtp3.text.toString() +
                            binding.etOtp4.text.toString() +
                            binding.etOtp5.text.toString() +
                            binding.etOtp6.text.toString()

                if (otp.length != 6) {

                    Toast.makeText(
                        requireContext(),
                        "Enter OTP",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@setOnClickListener
                }

                findNavController().navigate(
                    R.id.action_forgotPasswordFragment_to_resetPasswordFragment
                )
            }
        }
    }

    private fun setupOtpInputs() {

        val watcher1 =
            OtpTextWatcher(
                binding.etOtp1,
                null,
                binding.etOtp2
            )

        val watcher2 =
            OtpTextWatcher(
                binding.etOtp2,
                binding.etOtp1,
                binding.etOtp3
            )

        val watcher3 =
            OtpTextWatcher(
                binding.etOtp3,
                binding.etOtp2,
                binding.etOtp4
            )

        val watcher4 =
            OtpTextWatcher(
                binding.etOtp4,
                binding.etOtp3,
                binding.etOtp5
            )

        val watcher5 =
            OtpTextWatcher(
                binding.etOtp5,
                binding.etOtp4,
                binding.etOtp6
            )

        val watcher6 =
            OtpTextWatcher(
                binding.etOtp6,
                binding.etOtp5,
                null
            )

        binding.etOtp1.addTextChangedListener(watcher1)
        binding.etOtp2.addTextChangedListener(watcher2)
        binding.etOtp3.addTextChangedListener(watcher3)
        binding.etOtp4.addTextChangedListener(watcher4)
        binding.etOtp5.addTextChangedListener(watcher5)
        binding.etOtp6.addTextChangedListener(watcher6)

        watcher1.setupBackspace()
        watcher2.setupBackspace()
        watcher3.setupBackspace()
        watcher4.setupBackspace()
        watcher5.setupBackspace()
        watcher6.setupBackspace()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}