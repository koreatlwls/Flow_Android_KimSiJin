package com.example.flow.data.remote

import com.example.flow.data.remote.model.ErrorResponse
import com.squareup.moshi.Moshi
import okhttp3.Request
import okhttp3.ResponseBody
import okio.IOException
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class ResponseCall<T> constructor(
    private val callDelegate: Call<T>
) : Call<Result<T>> {

    private fun convertErrorBody(errorBody: ResponseBody?): ErrorResponse? {
        return try {
            errorBody?.source()?.let {
                val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
                moshiAdapter.fromJson(it)
            }
        } catch (exception: Exception) {
            null
        }
    }

    override fun enqueue(callback: Callback<Result<T>>) = callDelegate.enqueue(object : Callback<T> {

        override fun onResponse(call: Call<T>, response: Response<T>) {
            response.body()?.let {
                if (response.isSuccessful) {
                    callback.onResponse(
                        this@ResponseCall,
                        Response.success(Result.success(it))
                    )
                } else {
                    val errorResponse: ErrorResponse? =
                        convertErrorBody(response.errorBody())

                    callback.onResponse(
                        this@ResponseCall,
                        Response.success(Result.failure(Exception(errorResponse?.errorMessage ?: "something went wrong")))
                    )
                }
            }

            response.errorBody()?.let {
                val errorResponse: ErrorResponse? =
                    convertErrorBody(response.errorBody())

                callback.onResponse(
                    this@ResponseCall,
                    Response.success(Result.failure(Exception(errorResponse?.errorMessage ?: "something went wrong")))
                )
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            val errorMessage = when (t) {
                is IOException -> "No Internet Connection"
                is HttpException -> "Something went wrong!"
                else -> t.localizedMessage
            }

            callback.onResponse(
                this@ResponseCall,
                Response.success(Result.failure(RuntimeException(errorMessage, t)))
            )
        }

    })

    override fun clone(): Call<Result<T>> = ResponseCall(callDelegate.clone())

    override fun execute(): Response<Result<T>> = throw UnsupportedOperationException("ResponseCall does not support execute.")

    override fun isExecuted(): Boolean = callDelegate.isExecuted

    override fun cancel() = callDelegate.cancel()

    override fun isCanceled(): Boolean = callDelegate.isCanceled

    override fun request(): Request = callDelegate.request()

    override fun timeout(): Timeout = callDelegate.timeout()

}