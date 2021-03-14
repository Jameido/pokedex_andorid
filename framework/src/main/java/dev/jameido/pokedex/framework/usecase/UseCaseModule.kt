package dev.jameido.pokedex.framework.usecase

import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.jameido.pokedex.domain.usecase.GetPkmnDetail
import dev.jameido.pokedex.domain.usecase.GetPkmnListPage
import dev.jameido.pokedex.domain.usecase.GetPkmnSpecies

/**
 * Created by Luca Rossi on 28/02/2021.
 */
@Module
@InstallIn(ViewModelComponent::class)
internal abstract class UseCaseModule {

    @Binds
    abstract fun provideGetPkmnDetail(getPkmnDetail: GetPkmnDetailImpl) : GetPkmnDetail

    @Binds
    abstract fun provideGetPkmnListPage(getPkmnListPage: GetPkmnListPageImpl) : GetPkmnListPage

    @Binds
    abstract fun provideGetPkmnSpecies(getPkmnSpecies: GetPkmnSpeciesImpl) : GetPkmnSpecies
}