import org.jsoup.*
import org.jsoup.nodes.Element
import thoth.*
import org.apache.commons.codec.binary.Base64
import java.nio.file.Files
import java.nio.file.Paths

fun main(args : Array<String>){
    println("hello world")
    /**
    val url = "http://krytykal.org/only-sense/volume-1/prologu/"
    var doc = download(url)
    if (doc != null){
        val t = FindContent(doc)
        val p = ParentByStringContent(t)
        println(p?.text() ?: "broke yo")
    }*/
    val url = "http://krytykal.org/wp-content/uploads/2014/09/IMG_0008-177x250.jpg"
    val string = downloadImage(url)
    var real = ""
    if (string != null)
        real = string
    val thing = listOf(real)
    Files.write(Paths.get("test.txt"), thing)
}

