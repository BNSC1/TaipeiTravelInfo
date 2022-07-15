package com.bn.taipeitravelinfo.arch

import androidx.lifecycle.LiveDataScope
import com.bn.taipeitravelinfo.R
import com.bn.taipeitravelinfo.data.Resource
import com.bn.taipeitravelinfo.data.model.result.BaseResult
import com.bn.taipeitravelinfo.util.NetworkStatusTool
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber
import java.net.SocketTimeoutException
import kotlin.experimental.ExperimentalTypeInference

@OptIn(ExperimentalTypeInference::class)
abstract class NetworkViewModel : BaseViewModel() {
    abstract val networkStatusTool: NetworkStatusTool

    suspend inline fun <T> LiveDataScope<Resource<T>>.tryNetworkAction(@BuilderInference action: LiveDataScope<Resource<T>>.() -> Unit) {
        runCatching {
            if (!networkStatusTool.isInternetAvailable() || !networkStatusTool.isNetworkConnected()) {
                emit(Resource.error(null, messageResId = R.string.err_no_connection))
            } else {
                action()
            }
        }.onFailure { e ->
            Timber.e("${e.printStackTrace()}")
            if (e.javaClass == SocketTimeoutException::class.java) {
                emit(Resource.error(null, messageResId = R.string.err_connection_timeout))
            } else {
                emit(Resource.error(null, message = "${e.javaClass} ${e.message}"))
            }
        }
    }

    suspend inline fun <T, Result : BaseResult> LiveDataScope<Resource<T>>.handleResponse(
        response: Response<Result>,
        @BuilderInference successAction: LiveDataScope<Resource<T>>.(Result) -> Unit,
    ) {
        response.body()?.let { result ->
            successAction(result)
        } ?: response.let {
            val msg = "${it.code()} ${it.message()}"
            withContext(Dispatchers.IO) {
                runCatching {
                    Timber.d(msg + "${it.errorBody()?.string()}")
                }
            }
            errorMsg.emit(msg)
        }
    }
}