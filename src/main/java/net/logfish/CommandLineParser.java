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
/** Command line parser with automatic usage documentation.
    
    Based on the example from the jargs commandline parser library
    http://github.com/purcell/jargs/blob/master/src/jargs/examples/gnu/AutoHelpParser.java

*/
package net.logfish;

import jargs.gnu.CmdLineParser;
import java.util.ArrayList;

class CommandLineParser implements Runnable {
    ArrayList<String> optionHelpStrings;
    CmdLineParser parser;
    String[] args;
    private boolean ran; //Quality control value
    


    public CommandLineParser(String[] argv)
    {
      optionHelpStrings = new ArrayList<String>();
      args = argv;
      parser = new CmdLineParser();
      ran = false;
    }
    public String[] getRemainingArgs() {
        assert ran;//You must have used the parser before calling this method
        return parser.getRemainingArgs();
    }
    public void run()
    {
        try {
            parser.parse(args);
        } catch(CmdLineParser.IllegalOptionValueException e)
        {
            System.err.println("The given option \"" + e.getValue() + "\" is not a legal option.");
            printUsage();
            System.exit(1);
        } catch(CmdLineParser.UnknownOptionException e)
        {
            System.err.println("The given option \"" + e.getOptionName() + "\" is not a known option.");
            printUsage();
            System.exit(1);
        }
    }
    /* Tempting.. I would love to write a better interface, but that is to much work for now...
    CmdLineParser.Option flag(String flag)
    {
        //Adds the given flag
    }
    public void printUsage() {
        System.err.println("Usage: prog [options]");
    }
    class BooleanOption {
        
    }
    class Option {
        private String[] flags;
        Option(String[] flags)
        {
            this.flags = flags;
        }
    }
    */
}



