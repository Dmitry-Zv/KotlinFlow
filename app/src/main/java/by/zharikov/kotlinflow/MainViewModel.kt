package by.zharikov.kotlinflow

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val countDownTimer = flow<Int> {
        val startingValue = 10
        var currentValue = startingValue
        emit(startingValue)
        while (currentValue > 0) {
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }

    init {
        collectFlow()
    }

    private fun collectFlow() {


//        val flow1 = (flow {
//            emit(1)
//            delay(1000L)
//            emit(2)
//        })
        val flow = flow{
            delay(250L)
            emit("Appetizer")
            delay(500L)
            emit("Main dish")
            delay(1000L)
            emit("Dessert")
        }

        viewModelScope.launch {
//            val count = countDownTimer
////                .filter {
////                    it % 2 == 0
////                }
////                .map {
////                    it * it
////                }
//                .onEach {
//                    println("The current time is $it")
//                }
//                .fold(100) { accumulator, value ->
//                    accumulator + value
//                }
////                .reduce { accumulator, value ->
////                    accumulator + value
////                }
////                .count { time ->
////                    time % 2 == 0
////                }
//
//            println("The count is $count")
//
//            flow1.flatMapMerge { value ->
//                flow {
//                    emit(value + 1)
//                    delay(500L)
//                    emit(value + 2)
//                }
//            }
//                .collect { value ->
//                    println("The value is $value")
//                }

            flow.onEach {
                println("FLOW: $it is delivered")
            }
                //.buffer()
                .conflate()
                .collect{
                    println("FLOW: Eating $it")
                    delay(1500L)
                    println("FLOW: Finish $it")
                }
        }
    }
}