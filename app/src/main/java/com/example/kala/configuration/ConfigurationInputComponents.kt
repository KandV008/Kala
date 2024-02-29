package com.example.kala.configuration

import com.example.kala.R

/*
    SmallTextInputComponent
 */

enum class SmallTextInputConfiguration(
    private val layer: String,
    private val placeholder: String,
    private val svgFile: Int,
){
    USERNAME(
        "Username",
        "exampleUser",
        R.drawable.ic_account,
    ),
    EMAIL(
        "Email",
        "youremail@email.com",
        R.drawable.ic_email,
    ),
    PASSWORD(
        "Password",
        "******",
        R.drawable.ic_password,
    ),
    NEW_PASSWORD(
        "New password",
        "******",
        R.drawable.ic_password,
    ),
    REPEAT_PASSWORD(
        "Repeat password",
        "******",
        R.drawable.ic_password,
    );

    fun getLayer(): String{
        return layer
    }

    fun getPlaceholder(): String{
        return placeholder
    }

    fun getSVGFile(): Int{
        return svgFile
    }

    fun isPassword(): Boolean{
        return (this == PASSWORD) || (this == NEW_PASSWORD) || (this == REPEAT_PASSWORD)
    }

}