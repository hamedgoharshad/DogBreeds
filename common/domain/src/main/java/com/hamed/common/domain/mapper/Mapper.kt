package com.hamed.common.domain.mapper

interface Mapper<F, S> {
    fun F.map(): S
}
