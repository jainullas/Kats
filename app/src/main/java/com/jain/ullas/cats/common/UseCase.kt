package com.jain.ullas.cats.common

import io.reactivex.Flowable

abstract class UseCase<Type, in Params>(private val transformer: FlowableRxTransformer<Type>) where Type : Any {

    abstract fun createFlowable(params: Params?): Flowable<Type>

    fun single(params: Params? = null): Flowable<Type> = createFlowable(params).compose(transformer)

}