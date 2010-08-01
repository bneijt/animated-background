Animated background
===================

The GNOME desktop has the ability to change your desktop background by reading an XML definition.

And example of this is /usr/share/backgrounds/cosmos/background-1.xml.


This project is my first Scala XML experience, so don't expect anything mayor.
There are already multiple tools that do this, but as I'm testing out Scala, this seemed like a good project to try.

Requirements
------------
* gconf-tool: currently used to set the background using Runtime.getRuntime.exec


Usage example
-------------
sbt "run a.svg b.svg c.svg"


License
-------

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



See also
--------
[http://gnome-look.org/content/show.php/XML+animated+background+creator?content=118074](http://gnome-look.org/content/show.php/XML+animated+background+creator?content=118074)
[http://gtk-apps.org/content/show.php/XML+slideshow+creator?content=119728](http://gtk-apps.org/content/show.php/XML+slideshow+creator?content=119728)
