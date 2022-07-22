import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestGreet{

    @Test//аннотацией помечаем что функция для тестирования
    //fun greetAslanBolurov(){    ---функция до 5ой версии JUnit
    fun `greeting for Aslan Bolurov`(){
        //TO BE: (Aslan,Bolurov) -> Hello, Aslan Bolurov
        assertEquals("Hello, Aslan Bolurov",greeting4("Aslan","Bolurov"))



    }







}