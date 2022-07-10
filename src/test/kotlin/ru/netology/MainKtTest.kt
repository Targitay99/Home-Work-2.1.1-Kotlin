package ru.netology

import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun transferFeeByCard_WrongCard() {
        val typeCreditCard = "VKPa"
        val amountMonthlyTransfers = 1_000_00
        val amount = 10_000_00
        val amountDayTransfers = 5_000_00

        val result = transferFeeByCard(typeCreditCard, amountMonthlyTransfers, amount, amountDayTransfers)

        assertEquals(-1, result)
    }

    @Test
    fun transferFeeByCard_LikeMastercard() {
        val typeCreditCard = "MasterCard"
        val amountMonthlyTransfers = 70_000_00
        val amount = 10_000_00
        val amountDayTransfers = 5_000_00

        val result = transferFeeByCard(typeCreditCard, amountMonthlyTransfers, amount, amountDayTransfers)

        assertEquals(80_00, result)
    }

    @Test
    fun transferFeeByCard_LikeMastercardFalse() {
        val typeCreditCard = "MasterCard"
        val amountMonthlyTransfers = 70_000_00
        val amount = 160_000_00
        val amountDayTransfers = 5_000_00

        val result = transferFeeByCard(typeCreditCard, amountMonthlyTransfers, amount, amountDayTransfers)

        assertEquals(-1, result)
    }

    @Test
    fun transferFeeByCard_LikeMastercardMinimumPayment() {
        val typeCreditCard = "MasterCard"
        val amountMonthlyTransfers = 0
        val amount = 100_00
        val amountDayTransfers = 0

        val result = transferFeeByCard(typeCreditCard, amountMonthlyTransfers, amount, amountDayTransfers)

        assertEquals(2060, result)
    }

    @Test
    fun transferFeeByCard_LikeVisa() {
        val typeCreditCard = "Visa"
        val amountMonthlyTransfers = 70_000_00
        val amount = 10_000_00
        val amountDayTransfers = 5_000_00

        val result = transferFeeByCard(typeCreditCard, amountMonthlyTransfers, amount, amountDayTransfers)

        assertEquals(75_00, result)
    }

    @Test
    fun transferFeeByCard_LikeVisaFalse() {
        val typeCreditCard = "Visa"
        val amountMonthlyTransfers = 70_000_00
        val amount = 160_000_00
        val amountDayTransfers = 5_000_00

        val result = transferFeeByCard(typeCreditCard, amountMonthlyTransfers, amount, amountDayTransfers)

        assertEquals(-1, result)
    }

    @Test
    fun transferFeeByCard_LikeVisaMinimumPayment() {
        val typeCreditCard = "Visa"
        val amountMonthlyTransfers = 0
        val amount = 100_00
        val amountDayTransfers = 0

        val result = transferFeeByCard(typeCreditCard, amountMonthlyTransfers, amount, amountDayTransfers)

        assertEquals(3500, result)
    }

    @Test
    fun transferFeeByCard_LikeVKPay() {
        val typeCreditCard = "VKPay"
        val amountMonthlyTransfers = 1_000_00
        val amount = 10_000_00
        val amountDayTransfers = 5_000_00

        val result = transferFeeByCard(typeCreditCard, amountMonthlyTransfers, amount, amountDayTransfers)

        assertEquals(0, result)
    }

    @Test
    fun transferFeeByCard_LikeVKPayFalse() {
        val typeCreditCard = "VKPay"
        val amountMonthlyTransfers = 1_000_00
        val amount = 16_000_00
        val amountDayTransfers = 5_000_00

        val result = transferFeeByCard(typeCreditCard, amountMonthlyTransfers, amount, amountDayTransfers)

        assertEquals(-1, result)
    }

    @Test
    fun transferFeeByCard_ByDefault() {
        val amount = 10_000_00

        val result = transferFeeByCard(amount = amount)

        assertEquals(0, result)
    }

    @Test
    fun checkingTransferLimit_True() {
        val limitDay = 100_00
        val limitMonth = 1_000_00
        val amount = 50_00
        val amountMonthlyTransfers = 500_00
        val amountDayTransfers = 10_00

        val result = checkingTransferLimit(limitDay, limitMonth, amount, amountMonthlyTransfers, amountDayTransfers)

        assertEquals(true, result)
    }

    @Test
    fun checkingTransferLimit_FalseDay() {
        val limitDay = 100_00
        val limitMonth = 1_000_00
        val amount = 150_00
        val amountMonthlyTransfers = 500_00
        val amountDayTransfers = 10_00

        val result = checkingTransferLimit(limitDay, limitMonth, amount, amountMonthlyTransfers, amountDayTransfers)

        assertEquals(false, result)
    }

    @Test
    fun checkingTransferLimit_FalseMonth() {
        val limitDay = 100_00
        val limitMonth = 1_000_00
        val amount = 510_00
        val amountMonthlyTransfers = 500_00
        val amountDayTransfers = 10_00

        val result = checkingTransferLimit(limitDay, limitMonth, amount, amountMonthlyTransfers, amountDayTransfers)

        assertEquals(false, result)

    }
}