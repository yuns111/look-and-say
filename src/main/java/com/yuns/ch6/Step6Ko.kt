import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

fun CoroutineScope.produceSquares() = produce {
    for (x in 1..5) send(x * x)           // 값을 하나씩 produce
}

//
//fun CoroutineScope.produceNumbers() = produce<Int> {
//    var x = 1
//    while (true) send(x++) // 1부터 시작하여 모든 자연수를 produce
//}
//
//fun CoroutineScope.square(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {
//    for (x in numbers) send(x * x) // 주어진 수의 제곱을 produce
//}


//
//fun main(args: Array<String>) {
//    log("main start")
//    runBlockingExample()
//    log("call launchInGlobalScope")
//    Thread.sleep(500L)
//    log("main terminated")
//}

fun launchInGlobalScope() {
    GlobalScope.launch {
        log("coroutine started");
    }
}

fun runBlockingExample() {
    runBlocking {
        launch {
            log("runBlockingExample started")
        }
    }
}

fun log(msg: String) = println("${Thread.currentThread()} : $msg")


fun main() = runBlocking {
//    val squares = produceSquares()
//    squares.consumeEach { println(it) }   // 값을 하나씩 consume
//    println("Done!")

//    val numbers = produceNumbers()
//    val squares = square(numbers)
//    repeat(5) {
//        println(squares.receive())    // 처음 5개만 출력
//    }
//    println("Done!")
//    coroutineContext.cancelChildren() // 파이프라인 중단

    var ant = produceFirst()

    for (i in 1..4)
        ant = produceNum(ant)

    for (value in ant)
        print(value)


    coroutineContext.cancelChildren()
}

fun CoroutineScope.produceFirst() = produce {
    send(1)
}

fun CoroutineScope.produceNum(numbers: ReceiveChannel<Int>) = produce<Int> {
    var prev = numbers.receive()
    var count = 1

    for (value in numbers) {
        if (value == prev) count++
        else {
            send(count)
            send(prev)

            prev = value
            count = 1
        }
    }

    send(count)
    send(prev)
}


// inferred type is Sequence<Int>
//val fibonacci = sequence {
//    yield(1) // first Fibonacci number
//    var cur = 1
//    var next = 1
//    while (true) {
//        yield(next) // next Fibonacci number
//        val tmp = cur + next
//        cur = next
//        next = tmp
//    }
//}

//
//fun main(args: Array<String>) {
//    println(fibonacci.take(10).joinToString())
//}
