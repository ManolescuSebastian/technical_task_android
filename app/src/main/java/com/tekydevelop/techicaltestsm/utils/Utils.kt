package com.tekydevelop.techicaltestsm.utils

import androidx.core.util.PatternsCompat

object Utils {

    fun validateEmailAddress(emailAddress: String?): Boolean {
        return !emailAddress.isNullOrEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(emailAddress).matches()
    }

}