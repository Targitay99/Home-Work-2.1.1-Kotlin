package ru.netology

fun main() {

    var commissionForTransfer = transferFeeByCard("MasterCard", 0, 10_000_00)
    if (commissionForTransfer >= 0) {
        println(commissionForTransfer)
    }
}

fun transferFeeByCard(
    typeCreditCard: String = "VKPay",
    amountMonthlyTransfers: Int = 0,
    amount: Int,
    amountDayTransfers: Int = 0
): Int {
    var commissionForTransfer = -1
    when (typeCreditCard) {

        "MasterCard", "Maestro" -> {
            val limitDay = 150_000_00
            val limitMonth = 600_000_00
            commissionForTransfer =
                if (checkingTransferLimit(limitDay, limitMonth, amount, amountMonthlyTransfers, amountDayTransfers)) {
                    if (amount in 300_00..75_000_00 && amountMonthlyTransfers + amount <= 75_000_00) {
                        0
                    } else {
                        (amount / 100 * 0.6 + 20_00).toInt()
                    }
                } else {
                    -1
                }
        }

        "Visa", "МИР" -> {
            val limitDay = 150_000_00
            val limitMonth = 600_000_00
            commissionForTransfer =
                if (checkingTransferLimit(limitDay, limitMonth, amount, amountMonthlyTransfers, amountDayTransfers)) {
                    if (amount / 100 * 0.75 <= 35_00) {
                        35_00
                    } else {
                        (amount / 100 * 0.75).toInt()
                    }
                } else {
                    -1
                }
        }

        "VKPay" -> {
            val limitDay = 15_000_00
            val limitMonth = 40_000_00
            commissionForTransfer =
                if (checkingTransferLimit(limitDay, limitMonth, amount, amountMonthlyTransfers, amountDayTransfers)) {
                    0
                } else {
                    -1
                }
        }
        else -> {
            println("Не правильный тип карты")
        }
    }

    return commissionForTransfer
}

fun checkingTransferLimit(
    limitDay: Int,
    limitMonth: Int,
    amount: Int,
    amountPreviousTransfers: Int,
    amountDayTransfers: Int
): Boolean {
    if (amountPreviousTransfers + amount <= limitMonth && amountDayTransfers + amount <= limitDay) return true
    if (amount + amountDayTransfers >= limitDay) println("Превышен размер суточного перевода")
    if (amount + amountPreviousTransfers >= limitMonth) println("Превышен размер месячного перевода")
    return false
}