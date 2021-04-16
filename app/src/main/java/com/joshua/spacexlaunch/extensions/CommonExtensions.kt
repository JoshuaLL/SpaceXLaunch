package com.joshua.spacexlaunch.extensions

fun String?.isValid() = this != null && this.isNotEmpty() && !this.equals("null",true)