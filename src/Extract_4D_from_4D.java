

import emblcmci.Extractfrom4D;
import ij.plugin.PlugIn;

public class Extract_4D_from_4D implements PlugIn {

	@Override
	public void run(String arg) {
		// TODO Auto-generated method stub
		Extractfrom4D ext3D = new Extractfrom4D();
		ext3D.run(4);
	}

}
