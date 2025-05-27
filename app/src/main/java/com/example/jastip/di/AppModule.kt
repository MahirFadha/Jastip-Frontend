package com.example.jastip.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.jastip.data.local.AppDatabase
import com.example.jastip.data.local.dao.MenuItemDao
import com.example.jastip.data.local.dao.UserDao
import com.example.jastip.data.repository.MenuRepositoryImpl
import com.example.jastip.data.repository.UserRepositoryImpl
import com.example.jastip.domain.repository.IMenuRepository
import com.example.jastip.domain.repository.UserRepository
import com.example.jastip.domain.usecase.EditUseCase
import com.example.jastip.domain.usecase.LoginUseCase
import com.example.jastip.domain.usecase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {

        // Buat tabel baru sesuai struktur terbaru
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS `menu` (
                `id` INTEGER NOT NULL PRIMARY KEY,
                `name` TEXT NOT NULL,
                `price` INTEGER NOT NULL,
                `category` TEXT NOT NULL,
                `imageUrl` TEXT NOT NULL
            )
        """.trimIndent())
    }
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_db")
            .addMigrations(MIGRATION_2_3)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    fun provideMenuItemDao(db: AppDatabase): MenuItemDao = db.menuItemDao()

    @Provides
    fun provideRepository(dao: UserDao): UserRepository = UserRepositoryImpl(dao)

    @Provides
    fun provideRegisterUseCase(repo: UserRepository) = RegisterUseCase(repo)

    @Provides
    fun provideLoginUseCase(repo: UserRepository) = LoginUseCase(repo)

    @Provides
    fun provideIMenuRepository(
        dao: MenuItemDao,
        @ApplicationContext context: Context
    ): IMenuRepository {
        return MenuRepositoryImpl(dao, context)
    }
    
    @Provides
    fun provideEditUseCase(repo: UserRepository) = EditUseCase(repo)
}
