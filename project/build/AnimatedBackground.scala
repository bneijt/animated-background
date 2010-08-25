import sbt._

class AnimatedBackgroundProject(info: ProjectInfo) extends DefaultProject(info)
{
    val dep = "net.sf" % "jargs" % "1.0"
    val jbossRepository = "Scala-Tools Maven2 Snapshots Repository" at "http://repository.jboss.org/maven2"
}

