package lionmobi.dayu.test

import kotlinx.coroutines.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.concurrent.thread
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

suspend fun main(){

}

//协程的启动模式
fun coroutineStratMode(){
    val job = GlobalScope.launch (start = CoroutineStart.DEFAULT){
        CoroutineStart.DEFAULT//immediately schedules coroutine for execution according to its context 默认启动模式为Default
        CoroutineStart.LAZY//只有在job.start() job.join() deferred.await()的时候才会启动
        CoroutineStart.ATOMIC //开始调度同Default 是一样的，区别在于协程取消的时候 ATOMIC 一定会启动不能在开始之前取消 会在第一个挂起点取消
        CoroutineStart.UNDISPATCHED//立即在当前线程执行协程体，知道遇到第一个挂起点(后面取决于调度器)
    }
    job.start()
//    job.join()

    val t = thread(start = false) {
        //kotlin 启动线程是默认启动的 除非传入start = false
    }
}

//协程的调度器
fun coroutine(){
    //CoroutineContext 集合类似于List
    // ContinuationInterceptor 拦截Continuation的执行
    //CoroutineDispatcher 实现线程的调度
    val job = GlobalScope.launch (MyContinuationInterceptor() + CoroutineName("HelloWorld")){

    }
}

class MyContinuationInterceptor : ContinuationInterceptor{
    override val key = ContinuationInterceptor

    private val executor = Executors.newSingleThreadExecutor{it->
        Thread(it, "HelloWorld").also{it.isDaemon = true}
    }

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        //拦截器 传了一个Continuation进来 在传一个Continuation出去
        return MyContinuation(continuation);
    }
}

class MyContinuation<T>(val continuation : Continuation<T>): Continuation<T> {
    override val context: CoroutineContext = continuation.context;

    override fun resumeWith(result: Result<T>) {
        continuation.resumeWith(result);
    }
}