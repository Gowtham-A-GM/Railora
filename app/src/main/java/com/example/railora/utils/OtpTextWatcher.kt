package com.example.railora.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.EditText

class OtpTextWatcher(
    private val currentEditText: EditText,
    private val previousEditText: EditText?,
    private val nextEditText: EditText?
) : TextWatcher {

    override fun beforeTextChanged(
        s: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) {
    }

    override fun onTextChanged(
        s: CharSequence?,
        start: Int,
        before: Int,
        count: Int
    ) {
    }

    override fun afterTextChanged(
        s: Editable?
    ) {

        if (s?.length == 1) {
            nextEditText?.requestFocus()
        }
    }

    fun setupBackspace() {

        currentEditText.setOnKeyListener { _, keyCode, event ->

            if (
                keyCode == KeyEvent.KEYCODE_DEL &&
                event.action == KeyEvent.ACTION_DOWN &&
                currentEditText.text.isEmpty()
            ) {

                previousEditText?.requestFocus()

                return@setOnKeyListener true
            }

            false
        }
    }
}