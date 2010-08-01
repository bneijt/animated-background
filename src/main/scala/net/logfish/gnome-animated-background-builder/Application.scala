import scala.collection.mutable.ArrayOps
import scala.xml.{XML, Elem}

object Application {
    def main(args: Array[String]) = {
        //Create an XML file containing every given image using 
        //TODO Create GUI :)
        if(args.length < 1) {
            printf("Usage: program <image <image <image ... >>>\n");
            System.exit(1)
        }
        
        //printf("Loading template: %s\n", args.head)
        //val template = XML.load(args.head).descendant
        val transitionDuration = 3
        val staticDuration = 2
        val images = args
        val shiftedImages = images.tail ++ List(images.head)
        val imageNodes = images map (fileName => <static><duration>{staticDuration}</duration><file>{fileName}</file></static> )
        val transitionNodes = (images zip shiftedImages) map ( i => <transition><duration>{transitionDuration}</duration><from>{i._1}</from><to>{i._2}</to></transition> )
        val nodes = imageNodes zip (transitionNodes) flatten()
        val background = <background>{ nodes }</background>
        println(background)
        System.exit(0)
    }
}
