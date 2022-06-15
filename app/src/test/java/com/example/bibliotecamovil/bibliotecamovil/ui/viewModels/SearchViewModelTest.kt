package com.example.bibliotecamovil.bibliotecamovil.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookAPIClient
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.SaleInfo
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.VolumeInfo
import com.example.bibliotecamovil.bibliotecamovil.domain.model.BookResponse
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var bookAPIClient: BookAPIClient

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @After
    fun tearDown() {
        // Exception in thread "main" java.lang.IllegalStateException 対策で必要
        Dispatchers.resetMain()
    }

    @Test
    fun givenBookName_retrieveBooks_willRetrieveFromClient() {
        // Given
        val givenBookName = "El señor de los Anillos"
        val volumeInfo = mockk<VolumeInfo>(relaxed = true)
        val saleInfoMock = mockk<SaleInfo>(relaxed = true)
        val bookResponseBody = BookResponse(
            items = mutableListOf(
                Book(
                    kind = "Novela",
                    id = "1",
                    libroInfo = volumeInfo,
                    libroVenta = saleInfoMock
                )
            )
        )
        val bookResponse = mockk<Response<BookResponse>>(relaxed = true) {
            every { body() } returns bookResponseBody
            every { isSuccessful } returns true
        }
        bookAPIClient = mockk(relaxed = true) {
            coEvery { getLibros(givenBookName) } returns bookResponse
        }
        val subject = SearchViewModel(bookAPIClient)
        // when
        subject.retrieveBooks(givenBookName)
        // then
        coVerify(exactly = 1) { bookAPIClient.getLibros(givenBookName) }
        coVerify(exactly = 0) { bookResponse.errorBody() }
    }

    @Test
    fun givenBookName_retrieveBooks_willSetErrorOnFailure() {
        // Given
        val expectedErrorString = "x.x"
        val errorBody = mockk<ResponseBody>(relaxed = true) {
            // every { toString() } returns expectedErrorString
        }
        val givenBookName = "El señor de los Anillos"
        val volumeInfo = mockk<VolumeInfo>(relaxed = true)
        val saleInfoMock = mockk<SaleInfo>(relaxed = true)
        val bookResponseBody = BookResponse(
            items = mutableListOf(
                Book(
                    kind = "Novela",
                    id = "1",
                    libroInfo = volumeInfo,
                    libroVenta = saleInfoMock
                )
            )
        )
        val bookResponse = mockk<Response<BookResponse>>(relaxed = true) {
            every { body() } returns bookResponseBody
            every { errorBody() } returns errorBody
            every { isSuccessful } returns false
        }
        bookAPIClient = mockk(relaxed = true) {
            coEvery { getLibros(givenBookName) } returns bookResponse
        }
        val subject = SearchViewModel(bookAPIClient)
        // when
        subject.retrieveBooks(givenBookName)
        // then
        coVerify(exactly = 1) { bookAPIClient.getLibros(givenBookName) }
        coVerify(exactly = 1) { bookResponse.errorBody() }
    }

    @Test
    fun getBooks() {

    }
}