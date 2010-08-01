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
package net.logfish.animated_background_builder

import scala.collection.mutable.ArrayOps
import scala.xml.{XML, Elem}
import java.io.{File, PrintWriter}

object Application {
    def staticNodes(files: Array[File], staticDuration: Int) = {
        files map (file => <static>
                <duration>{staticDuration}</duration>
                <file>{file getAbsolutePath}</file>
            </static> )
    }
    def main(args: Array[String]) = {
        //Create an XML file containing every given image using 
        //TODO Create GUI :)
        if(args.length < 1) {
            printf("Usage: program <image <image <image ... >>>\n");
            System.exit(1)
        }
        
        val transitionDuration = 3
        val staticDuration = 2
        val imageNames = args
        val (images, notFound) = imageNames map (new File(_)) partition(_ exists)
        notFound foreach (printf("Could not find: %s\n", _))
        //Images is a list of File s which exist
        if( images.length < 1) {
            println("No images left after filtering out non-existing filenames")
            System.exit(1)
        }
        val shiftedImages = images.tail ++ List(images.head)
        val imageNodes = staticNodes(images, staticDuration)
        val transitionNodes = (images zip shiftedImages) map ( i => <transition>
                <duration>{transitionDuration}</duration>
                <from>{i._1 getAbsolutePath}</from>
                <to>{i._2 getAbsolutePath}</to>
            </transition> )
        val nodes = (imageNodes zip transitionNodes) flatMap ( i => List(i._1, i._2))
        val background = <background>
        { nodes }
        </background>

        //Write background XML to disk
        val xmlBackgroundFile = new File("background.xml")
        val out = new PrintWriter(xmlBackgroundFile)
        out write background.mkString
        out close()

        //Inform GNOME
        val p = Runtime.getRuntime().exec(
            List(
                "gconftool-2",
                "--type", "string", 
                "--set", "/desktop/gnome/background/picture_filename",
                xmlBackgroundFile.getAbsolutePath
                ).toArray
            )
        p.waitFor
        if(p.exitValue > 0)
            println("gconftool subprocess exited with an error status (%i)\n", p.exitValue)
        //Set zoom option. This should be part of the XML (I think) but I'm not sure how yet.
        val ap = Runtime.getRuntime().exec(
            List(
                "gconftool-2",
                "--type", "string", 
                "--set", "/desktop/gnome/background/picture_options",
                "zoom"
                ).toArray
            )
        ap.waitFor
        if(ap.exitValue > 0)
            println("gconftool subprocess exited with an error status (%i)\n", ap.exitValue)
        System.exit(0)
    }
}
