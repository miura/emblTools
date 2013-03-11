package emblcmci.macro;

/**
 * This Run_MyMacro is a duplicate of a class in "QuickPALM" plugin
 * Written by Ricardo Henriques
 *  * http://code.google.com/p/quickpalm/ 
 */

import ij.IJ;
import ij.plugin.PlugIn;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;

public class RunMyMacro implements PlugIn 
{

	public void run(String arg) 
	{
        IJ.runMacro(getText(arg));   
    }
    
    private String getText(String path) {
        String text = "";
        try {
            // get the text resource as a stream
            InputStream is = getClass().getResourceAsStream(path);
            if (is==null) {
                //IJ.showMessage("JAR Demo", "File not found in JAR at "+path);
                return "";
            }
            InputStreamReader isr = new InputStreamReader(is);
            StringBuffer sb = new StringBuffer();
            char [] b = new char [8192];
            int n;
            //read a block and append any characters
            while ((n = isr.read(b)) > 0)
                sb.append(b,0, n);
            // display the text in a TextWindow
            text = sb.toString();
        }
        catch (IOException e) {
            String msg = e.getMessage();
            if (msg==null || msg.equals(""))
                msg = "" + e;	
            //IJ.showMessage("JAR Demo", msg);
        }
        return text;
    }
}