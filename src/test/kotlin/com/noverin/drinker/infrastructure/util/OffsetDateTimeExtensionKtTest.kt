package com.noverin.drinker.infrastructure.util

import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneOffset

class OffsetDateTimeExtensionKtTest {
    @Test
    fun `when first if greater then second then answer is positive`() {
        val threeHoursAfter = LocalDateTime.now().atOffset(ZoneOffset.UTC).plusHours(3)
        assert(threeHoursAfter.secondFromNow() > 0)
    }
}