package com.applaudostudios.mubi.base

import android.util.Patterns

fun String?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
fun String?.isNotValidEmail() = !this.isValidEmail()
