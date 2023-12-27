package com.thiago.fitness.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.thiago.fitness.core.Constants.EXERCISE
import com.thiago.fitness.core.Constants.TRAINING
import com.thiago.fitness.core.Constants.USERS
import com.thiago.fitness.data.repository.AuthRepositoryImpl
import com.thiago.fitness.data.repository.ExerciseRepositoryImpl
import com.thiago.fitness.data.repository.TrainingRepositoryImpl
import com.thiago.fitness.data.repository.UsersRepositoryImpl
import com.thiago.fitness.domain.repository.AuthRepository
import com.thiago.fitness.domain.repository.ExerciseRepository
import com.thiago.fitness.domain.repository.TrainingRepository
import com.thiago.fitness.domain.repository.UsersRepository
import com.thiago.fitness.domain.use_cases.auth.AuthUseCases
import com.thiago.fitness.domain.use_cases.auth.GetCurrentUser
import com.thiago.fitness.domain.use_cases.auth.Login
import com.thiago.fitness.domain.use_cases.auth.Logout
import com.thiago.fitness.domain.use_cases.auth.Signup
import com.thiago.fitness.domain.use_cases.exercise.CreateExercise
import com.thiago.fitness.domain.use_cases.exercise.DeleteExercise
import com.thiago.fitness.domain.use_cases.exercise.ExerciseUseCases
import com.thiago.fitness.domain.use_cases.exercise.GetExercise
import com.thiago.fitness.domain.use_cases.exercise.GetExerciseByIdUser
import com.thiago.fitness.domain.use_cases.exercise.GetExercisesByUserIdUseCase
import com.thiago.fitness.domain.use_cases.exercise.UpdateExercise
import com.thiago.fitness.domain.use_cases.training.CreateTraining
import com.thiago.fitness.domain.use_cases.training.DeleteTraining
import com.thiago.fitness.domain.use_cases.training.GetTraining
import com.thiago.fitness.domain.use_cases.training.GetTrainingByIdUser
import com.thiago.fitness.domain.use_cases.training.TrainingUseCases
import com.thiago.fitness.domain.use_cases.training.UpdateTraining
import com.thiago.fitness.domain.use_cases.users.Create
import com.thiago.fitness.domain.use_cases.users.GetUserById
import com.thiago.fitness.domain.use_cases.users.SaveImage
import com.thiago.fitness.domain.use_cases.users.Update
import com.thiago.fitness.domain.use_cases.users.UsersUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Named(USERS)
    fun provideStorageUsersRef(storage: FirebaseStorage): StorageReference =
        storage.reference.child(USERS)


    @Provides
    @Named(USERS)
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

    @Provides
    @Named(EXERCISE)
    fun provideExerciseRef(db: FirebaseFirestore): CollectionReference {
        return db.collection(EXERCISE)
    }

    @Provides
    @Named(EXERCISE)
    fun provideStorageExerciseRef(storage: FirebaseStorage): StorageReference =
        storage.reference.child(
            EXERCISE
        )

    @Provides
    @Named(TRAINING)
    fun provideStorageTrainingRef(storage: FirebaseStorage): StorageReference =
        storage.reference.child(
            TRAINING
        )

    @Provides
    @Named(TRAINING)
    fun provideTrainingRef(db: FirebaseFirestore): CollectionReference = db.collection(TRAINING)

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun provideTrainingRepository(impl: TrainingRepositoryImpl): TrainingRepository = impl

    @Provides
    fun provideExerciseRepository(impl: ExerciseRepositoryImpl): ExerciseRepository = impl

    @Provides
    fun provideAuthUseCases(repository: AuthRepository) = AuthUseCases(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        logout = Logout(repository),
        signup = Signup(repository)
    )

    @Provides
    fun provideUsersUseCases(repository: UsersRepository) = UsersUseCases(
        create = Create(repository),
        getUserById = GetUserById(repository),
        update = Update(repository),
        saveImage = SaveImage(repository)
    )

    @Provides
    fun provideTrainingUseCases(repository: TrainingRepository) = TrainingUseCases(
        createTraining = CreateTraining(repository),
        getTraining = GetTraining(repository),
        getTrainingByIdUser = GetTrainingByIdUser(repository),
        deleteTraining = DeleteTraining(repository),
        updateTraining = UpdateTraining(repository),

        )

    @Provides
    fun provideExerciseUseCases(repository: ExerciseRepository) = ExerciseUseCases(

        createExercise = CreateExercise(repository),
        updateExercise = UpdateExercise(repository),
        getExercise = GetExercise(repository),
        deleteExercise = DeleteExercise(repository),
        getExerciseByIdUser = GetExerciseByIdUser(repository),
        getExercisesByTrainingUseCases = GetExercisesByUserIdUseCase(repository),


        )
}