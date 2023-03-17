package com.example.yapeapp.di.component

import com.example.yapeapp.di.module.ApplicationModule
import com.example.yapeapp.presentation.DetailsFragment
import com.example.yapeapp.presentation.RecipesFragment
import dagger.Component
import javax.inject.Singleton

/**
 * @author Axel Sanchez
 */
@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent{
    fun inject(recipesFragment: RecipesFragment)
    fun inject(detailsFragment: DetailsFragment)
}