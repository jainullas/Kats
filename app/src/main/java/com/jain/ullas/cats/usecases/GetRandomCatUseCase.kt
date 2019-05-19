package com.jain.ullas.cats.usecases

import com.jain.ullas.cats.common.FlowableRxTransformer
import com.jain.ullas.cats.common.UseCase
import com.jain.ullas.cats.data.RandomCat
import com.jain.ullas.cats.repository.CatRepository
import io.reactivex.Flowable
import org.koin.standalone.KoinComponent

class GetRandomCatUseCase(
    transformer: FlowableRxTransformer<RandomCat>,
    private val catRepository: CatRepository
) : UseCase<RandomCat, Unit?>(transformer), KoinComponent {

    override fun createFlowable(params: Unit?): Flowable<RandomCat> =
        catRepository.getRandomCat().flatMap { t -> Flowable.just(t[0]) }

    fun getRandomCat(): Flowable<RandomCat> = single()
}

