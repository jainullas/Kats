package com.jain.ullas.cats.usecases

import com.jain.ullas.cats.common.FlowableRxTransformer
import com.jain.ullas.cats.common.UseCase
import com.jain.ullas.cats.data.Breed
import com.jain.ullas.cats.repository.CatRepository
import io.reactivex.Flowable
import org.koin.standalone.KoinComponent

class GetBreedsUseCase(
    transformer: FlowableRxTransformer<List<Breed>>,
    private val catRepository: CatRepository
) : UseCase<List<Breed>, Unit?>(transformer), KoinComponent {

    override fun createFlowable(params: Unit?): Flowable<List<Breed>> = catRepository.getBreeds()

    fun getBreeds(): Flowable<List<Breed>> = execute()
}

