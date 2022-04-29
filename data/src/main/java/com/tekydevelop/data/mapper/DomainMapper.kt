package com.tekydevelop.data.mapper

import com.tekydevelop.data.R

interface DomainMapper <R> {
    fun asDomain(): R
}