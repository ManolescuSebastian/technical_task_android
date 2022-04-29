package com.tekydevelop.data.di

import com.tekydevelop.data.repository.UserRepositoryImpl
import com.tekydevelop.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoriesModule {

    @Binds
    fun userRepo(teamsRepository: UserRepositoryImpl): UserRepository
}