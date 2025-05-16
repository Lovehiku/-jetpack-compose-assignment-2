package com.example.todo.data.repository

import com.example.todo.data.local.TodoDao
import com.example.todo.data.model.Todo
import com.example.todo.data.remote.TodoApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import android.util.Log
import kotlinx.coroutines.flow.first

class TodoRepository(
    private val api: TodoApi,
    private val dao: TodoDao
) {


    fun getTodos(): Flow<List<Todo>> = flow {
        // First emit cached data
        val cachedTodos = dao.getAllTodos().first()
        Log.d("TodoRepository", "Emitting cached data: ${cachedTodos.size} items")
        emit(cachedTodos)
        try {
            Log.d("TodoRepository", "Fetching from API...")
            val response = api.getTodos()
            if (response.isSuccessful) {
                val apiTodos = response.body()
                Log.d("TodoRepository", "API response size: ${apiTodos?.size}")
                apiTodos?.let {
                    dao.deleteAllTodos()
                    dao.insertTodos(it)
                    Log.d("TodoRepository", "Inserted todos from API into DB")
                    // Emit the new data from DB
                    val updatedTodos = dao.getAllTodos().first()
                    Log.d("TodoRepository", "Emitting updated data: ${updatedTodos.size} items")
                    emit(updatedTodos)
                }
            } else {
                Log.e("TodoRepository", "API error: ${response.code()} ${response.message()}")
                throw HttpException(response)
            }
        } catch (e: IOException) {
            Log.e("TodoRepository", "Network error: ${e.localizedMessage}")
        } catch (e: HttpException) {
            Log.e("TodoRepository", "HTTP error: ${e.localizedMessage}")
        }
    }

    suspend fun getTodoById(id: Int): Todo? {
        return dao.getTodoById(id)
    }
}