package com.near.common.domain.utils

class ServerException(message: String?, val code: Int = -1) : Exception(message)
