package jp.co.qoncept.tensorkotlin

import org.testng.Assert.*
import org.testng.annotations.Test

class TensorTest {
    @Test
    fun testIndex() {
        run {
            val a = Tensor(Shape())
            assertEquals(0, a.index(intArrayOf()))
        }

        run {
            val a = Tensor(Shape(7))
            assertEquals(3, a.index(intArrayOf(3)))
        }

        run {
            val a = Tensor(Shape(5, 7))
            assertEquals(9, a.index(intArrayOf(1, 2)))
        }

        run {
            val a = Tensor(Shape(5, 7, 11))
            assertEquals(244, a.index(intArrayOf(3, 1, 2)))
        }
    }

    @Test
    fun testGetByRanges() {
        run {
            val a = Tensor(Shape(5, 5, 5), (1..125).map { it.toFloat() }.toFloatArray())
            val b = a[1..3, 2..4, 1..2]
            assertEquals(Tensor(Shape(3, 3, 2), floatArrayOf(37, 38, 42, 43, 47, 48, 62, 63, 67, 68, 72, 73, 87, 88, 92, 93, 97, 98)), b)
        }
    }

    @Test
    fun testPlus() {
        run {
            val a = Tensor(Shape(2, 3), floatArrayOf(1, 2, 3, 4, 5, 6))
            val b = Tensor(Shape(2, 3), floatArrayOf(7, 8, 9, 10, 11, 12))
            assertEquals(Tensor(Shape(2, 3), floatArrayOf(8, 10, 12, 14, 16, 18)), a + b)
            assertEquals(Tensor(Shape(2, 3), floatArrayOf(8, 10, 12, 14, 16, 18)), b + a)
        }

        run {
            val a = Tensor(Shape(2, 3, 2), floatArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12))
            val b = Tensor(Shape(2), floatArrayOf(100, 200))
            assertEquals(Tensor(Shape(2, 3, 2), floatArrayOf(101, 202, 103, 204, 105, 206, 107, 208, 109, 210, 111, 212)), a + b)
            assertEquals(Tensor(Shape(2, 3, 2), floatArrayOf(101, 202, 103, 204, 105, 206, 107, 208, 109, 210, 111, 212)), b + a)
        }

        run {
            val a = Tensor(Shape(2, 1, 3, 2), floatArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12))
            val b = Tensor(Shape(3, 2), floatArrayOf(100, 200, 300, 400, 500, 600))
            assertEquals(Tensor(Shape(2, 1, 3, 2), floatArrayOf(101, 202, 303, 404, 505, 606, 107, 208, 309, 410, 511, 612)), a + b)
            assertEquals(Tensor(Shape(2, 1, 3, 2), floatArrayOf(101, 202, 303, 404, 505, 606, 107, 208, 309, 410, 511, 612)), b + a)
        }
    }

    @Test
    fun testMinus() {
        run {
            val a = Tensor(Shape(2, 3), floatArrayOf(1, 2, 3, 4, 5, 6))
            val b = Tensor(Shape(2, 3), floatArrayOf(12, 11, 10, 9, 8, 7))
            assertEquals(Tensor(Shape(2, 3), floatArrayOf(-11, -9, -7, -5, -3, -1)), a - b)
            assertEquals(Tensor(Shape(2, 3), floatArrayOf(11, 9, 7, 5, 3, 1)), b - a)
        }

        run {
            val a = Tensor(Shape(2, 3, 2), floatArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12))
            val b = Tensor(Shape(2), floatArrayOf(100, 200))
            assertEquals(Tensor(Shape(2, 3, 2), floatArrayOf(-99, -198, -97, -196, -95, -194, -93, -192, -91, -190, -89, -188)), a - b)
            assertEquals(Tensor(Shape(2, 3, 2), floatArrayOf(99, 198, 97, 196, 95, 194, 93, 192, 91, 190, 89, 188)), b - a)
        }

        run {
            val a = Tensor(Shape(2, 1, 3, 2), floatArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12))
            val b = Tensor(Shape(3, 2), floatArrayOf(100, 200, 300, 400, 500, 600))
            assertEquals(Tensor(Shape(2, 1, 3, 2), floatArrayOf(-99, -198, -297, -396, -495, -594, -93, -192, -291, -390, -489, -588)), a - b)
            assertEquals(Tensor(Shape(2, 1, 3, 2), floatArrayOf(99, 198, 297, 396, 495, 594, 93, 192, 291, 390, 489, 588)), b - a)
        }
    }

    @Test
    fun testTimes() {
        run {
            val a = Tensor(Shape(2, 3), floatArrayOf(1, 2, 3, 4, 5, 6))
            val b = Tensor(Shape(2, 3), floatArrayOf(7, 8, 9, 10, 11, 12))
            val r = a * b
            assertEquals(Tensor(Shape(2, 3), floatArrayOf(7, 16, 27, 40, 55, 72)), r)
        }

        run {
            val a = Tensor(Shape(2, 3, 2), floatArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12))
            val b = Tensor(Shape(2), floatArrayOf(10, 100))
            assertEquals(Tensor(Shape(2, 3, 2), floatArrayOf(10, 200, 30, 400, 50, 600, 70, 800, 90, 1000, 110, 1200)), a * b)
            assertEquals(Tensor(Shape(2, 3, 2), floatArrayOf(10, 200, 30, 400, 50, 600, 70, 800, 90, 1000, 110, 1200)), b * a)
        }

        run {
            val a = Tensor(Shape(2, 1, 3, 2), floatArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12))
            val b = Tensor(Shape(3, 2), floatArrayOf(10, 100, 1000, -10, -100, -1000))
            assertEquals(Tensor(Shape(2, 1, 3, 2), floatArrayOf(10, 200, 3000, -40, -500, -6000, 70, 800, 9000, -100, -1100, -12000)), a * b)
            assertEquals(Tensor(Shape(2, 1, 3, 2), floatArrayOf(10, 200, 3000, -40, -500, -6000, 70, 800, 9000, -100, -1100, -12000)), b * a)
        }
    }

    @Test
    fun testDiv() {
        run {
            val a = Tensor(Shape(2, 3), floatArrayOf(1, 2, 3, 4, 5, 6))
            val b = Tensor(Shape(2, 3), floatArrayOf(2, 4, 8, 16, 32, 64))
            val r = a / b
            assertEquals(Tensor(Shape(2, 3), floatArrayOf(0.5f, 0.5f, 0.375f, 0.25f, 0.15625f, 0.09375f)), r)
        }

        run {
            val a = Tensor(Shape(2, 3, 2), floatArrayOf(2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096))
            val b = Tensor(Shape(2), floatArrayOf(8, 2))
            assertEquals(Tensor(Shape(2, 3, 2), floatArrayOf(0.25f, 2.0f, 1.0f, 8.0f, 4.0f, 32.0f, 16.0f, 128.0f, 64.0f, 512.0f, 256.0f, 2048.0f)), a / b)
            assertEquals(Tensor(Shape(2, 3, 2), floatArrayOf(4.0f, 0.5f, 1.0f, 0.125f, 0.25f, 0.03125f, 0.0625f, 0.0078125f, 0.015625f, 0.001953125f, 0.00390625f, 0.00048828125f)), b / a)
        }

        run {
            val a = Tensor(Shape(3, 1, 2, 2), floatArrayOf(2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096))
            val b = Tensor(Shape(2, 2), floatArrayOf(8, 2, -8, -2))
            assertEquals(Tensor(Shape(3, 1, 2, 2), floatArrayOf(0.25f, 2.0f, -1.0f, -8.0f, 4.0f, 32.0f, -16.0f, -128.0f, 64.0f, 512.0f, -256.0f, -2048.0f)), a / b)
            assertEquals(Tensor(Shape(3, 1, 2, 2), floatArrayOf(4.0f, 0.5f, -1.0f, -0.125f, 0.25f, 0.03125f, -0.0625f, -0.0078125f, 0.015625f, 0.001953125f, -0.00390625f, -0.00048828125f)), b / a)
        }
    }

    @Test
    fun testMatmul() {
        run {
            val a = Tensor(Shape(2, 3), floatArrayOf(1, 2, 3, 4, 5, 6))
            val b = Tensor(Shape(3, 4), floatArrayOf(7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18))
            val r = a.matmul(b)
            assertEquals(Tensor(Shape(2, 4), floatArrayOf(74, 80, 86, 92, 173, 188, 203, 218)), r)
        }
    }

    @Test
    fun testEquals() {
        run {
            val a = Tensor(Shape(2, 3), floatArrayOf(2, 3, 5, 7, 11, 13))
            val b = Tensor(Shape(2, 3), floatArrayOf(2, 3, 5, 7, 11, 13))
            assertTrue(a == b)
        }

        run {
            val a = Tensor(Shape(2, 3), floatArrayOf(2, 3, 5, 7, 11, 13))
            val b = Tensor(Shape(2, 3), floatArrayOf(2, 3, 5, 7, 11, 17))
            assertFalse(a == b)
        }

        run {
            val a = Tensor(Shape(2, 3), floatArrayOf(2, 3, 5, 7, 11, 13))
            val b = Tensor(Shape(3, 2), floatArrayOf(2, 3, 5, 7, 11, 17))
            assertFalse(a == b)
        }

        run {
            val a = Tensor(Shape(2, 3), floatArrayOf(2, 3, 5, 7, 11, 13))
            val b = Tensor(Shape(2, 2), floatArrayOf(2, 3, 5, 7))
            assertFalse(a == b)
        }

        run {
            val a = Tensor(Shape(2, 3), floatArrayOf(2, 3, 5, 7, 11, 13))
            val b = Tensor(Shape(), floatArrayOf())
            assertFalse(a == b)
        }

        run {
            val a = Tensor(Shape(), floatArrayOf())
            val b = Tensor(Shape(), floatArrayOf())
            assertTrue(a == b)
        }
    }
}
