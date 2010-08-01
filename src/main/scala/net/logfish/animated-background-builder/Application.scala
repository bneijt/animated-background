/*
    Copyright 2010, A. Bram Neijt <bneijt@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

import scala.collection.mutable.ArrayOps
import scala.xml.{XML, Elem}
import java.io.File

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
        val imageNames = args
        val images = imageNames map (new File(_)) filter(_ exists)
        //Images is a list of File s which exist
        if( images.length < 1) {
            println("No images left after filtering out non-existing filenames")
            System.exit(1)
        }
        val shiftedImages = images.tail ++ List(images.head)
        val imageNodes = images map (file => <static>
                <duration>{staticDuration}</duration>
                <file>{file getAbsolutePath}</file>
            </static> )
        val transitionNodes = (images zip shiftedImages) map ( i => <transition>
                <duration>{transitionDuration}</duration>
                <from>{i._1}</from>
                <to>{i._2}</to>
            </transition> )
        val nodes = (imageNodes zip transitionNodes) flatMap ( i => List(i._1, i._2))
        val background = <background>
        { nodes }
        </background>
        println(background)
        System.exit(0)
    }
}
