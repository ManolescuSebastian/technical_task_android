package com.tekydevelop.domain.state

// A helper class to handle states
data class Resource<out T>(val status: Status = Status.LOADING, val data: T? = null, val message: String? = null) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String): Resource<T> {
            return Resource(Status.ERROR, null, msg)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
