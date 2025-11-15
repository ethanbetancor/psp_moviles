package com.example.di

import com.example.data.RepositorioPersonas
import com.example.data.RepositorioVisitas
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepositorioPersonas(): RepositorioPersonas {
        return RepositorioPersonas()
    }

    @Provides
    @Singleton
    fun provideRepositorioVisitas(): RepositorioVisitas {
        return RepositorioVisitas()
    }
}
