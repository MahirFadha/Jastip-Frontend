package com.example.jastip.di

import android.content.Context
import androidx.room.Room
import com.example.jastip.data.local.AppDatabase
import com.example.jastip.data.local.dao.UserDao
import com.example.jastip.data.repository.UserRepositoryImpl
import com.example.jastip.domain.repository.UserRepository
import com.example.jastip.domain.usecase.LoginUseCase
import com.example.jastip.domain.usecase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_db").build()

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    fun provideRepository(dao: UserDao): UserRepository = UserRepositoryImpl(dao)

    @Provides
    fun provideRegisterUseCase(repo: UserRepository) = RegisterUseCase(repo)

    @Provides
    fun provideLoginUseCase(repo: UserRepository) = LoginUseCase(repo)
}
