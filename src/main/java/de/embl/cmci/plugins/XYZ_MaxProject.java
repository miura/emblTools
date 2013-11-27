package de.embl.cmci.plugins;
import de.embl.cmci.XYZMaxProject;
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
