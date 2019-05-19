package com.jain.ullas.cats.common

import io.reactivex.FlowableTransformer

abstract class FlowableRxTransformer<T> : FlowableTransformer<T, T>