package emblcmci.plugins;
import emblcmci.XYZMaxProject;
import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;

public class XYZ_MaxProject implements PlugIn {

	@Override
	public void run(String arg) {
		ImagePlus imp = IJ.getImage();
		if (imp != null){
			XYZMaxProject mp = new XYZMaxProject(imp);
			ImagePlus impxyz = mp.getXYZProject();
			impxyz.show();
		} else {
			IJ.log("no image, as it seems...");
		}
	}

}
