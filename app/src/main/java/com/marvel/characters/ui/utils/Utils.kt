package com.marvel.characters.ui.utils

import java.math.BigInteger
import java.security.MessageDigest

class Utils {

    companion object{

        fun buildHashMd5(input: String): String{
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray()))
                .toString(16).padStart(32, '0')
        }
    }
}